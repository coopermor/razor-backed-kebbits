package com.cwjoshuak;


import com.google.common.collect.Lists;
import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.callback.ClientThread;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

@Slf4j
@PluginDescriptor(
	name = "Kebbit Tracking",
	description = "Track kebbits like you would Herbiboar.",
	tags = {"razor", "kebbit", "backed", "razorback", "razorbacked", "razor-backed", "kebbits", "hunter", "rumour", "feldip", "weasel"}
)
@Getter
public class RazorKebbitPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private RazorKebbitConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private RazorKebbitOverlay overlay;

	private HuntingTrail huntingTrail;
	@Getter
	private final List<WorldPoint> currentPath = Lists.newArrayList();

	@Getter
	private final Map<WorldPoint, TileObject> trailObjects = new HashMap<>();

	@Getter
	private final Map<WorldPoint, TileObject> burrows = new HashMap<>();

	@Getter
	private final Map<WorldPoint, TileObject> bushes = new HashMap<>();
	private int finishId;

	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);

		if (client.getGameState() == GameState.LOGGED_IN) {
			clientThread.invokeLater(() ->
			{
				huntingTrail = checkArea();
			});
		}
	}

	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
		resetTrailData();
		clearCache();
		huntingTrail = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event) {
		switch (event.getGameState()) {
			case HOPPING:
			case LOGGING_IN:
				resetTrailData();
				huntingTrail = null;
				break;
			case LOADING:
				clearCache();
				huntingTrail =  checkArea();
				break;
			default:
				break;
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event) {
		updateTrailData(event);
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {

		onTileObject(null, event.getGameObject());
	}

	@Subscribe
	public void onGameObjectDespawned(GameObjectDespawned event) {
		onTileObject(event.getGameObject(), null);
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event) {

		onTileObject(null, event.getGroundObject());
	}

	@Subscribe
	public void onGroundObjectDespawned(GroundObjectDespawned event) {

		onTileObject(event.getGroundObject(), null);
	}

	private HuntingTrail checkArea() {
		final int[] mapRegions = client.getMapRegions();
		for (HuntingTrail trail : HuntingTrail.values())
		{
			if (ArrayUtils.contains(mapRegions, trail.getRegion()))
			{
				return trail;
			}
		}

		return null;
	}

	// Store relevant GameObjects
	private void onTileObject(TileObject oldObject, TileObject newObject) {
		if (oldObject != null) {
			WorldPoint oldLocation = oldObject.getWorldLocation();
			burrows.remove(oldLocation);
			trailObjects.remove(oldLocation);
			bushes.remove(oldLocation);
		}

		if (newObject == null) {
			return;
		}
		if (huntingTrail == null) {
			return;
		}

		if (huntingTrail.getStartObjectIds().contains(newObject.getId())) {
			burrows.put(newObject.getWorldLocation(), newObject);
			return;
		}
		if (huntingTrail.isSearchSpot(newObject.getWorldLocation())) {
			trailObjects.put(newObject.getWorldLocation(), newObject);
			return;
		}
		if (huntingTrail.getEndLocations().contains(newObject.getWorldLocation())) {
			bushes.put(newObject.getWorldLocation(), newObject);
			return;
		}
	}


	private void updateTrailData(VarbitChanged event)
	{
		if (huntingTrail == null || event == null)
		{
			return;
		}

		finishId = client.getVarbitValue(huntingTrail.getFinishVarbit());

		if (huntingTrail.hasSearchVarbit(event.getVarbitId()))
		{
			WorldPoint wp = huntingTrail.getSearchSpot(event.getVarbitId(), event.getValue());

			if (wp == null) {
				return;
			}

			switch (event.getValue()) {
				case 1:
				case 2:
					if (!currentPath.contains(wp))
					{
						currentPath.add(wp);
					}
					break;
				case 3:
				case 4:
					currentPath.remove(wp);
					break;
			}
		}
		else if (event.getVarbitId() == huntingTrail.getFinishVarbit()
			&& event.getValue() == 0)
		{
			resetTrailData();
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event) {
		if (huntingTrail == null) {
			return;
		}

		if (config.dynamicMenuEntrySwap()) {
			swapTrailMenuEntries(event);
		}
	}


	private void swapTrailMenuEntries(MenuEntryAdded event) {
		String target = event.getTarget();
		for (String menuTarget : huntingTrail.getMenuTargets()) {
			if (target.contains(menuTarget)) {
				MenuEntry entry = event.getMenuEntry();
				WorldPoint entryTargetPoint = WorldPoint.fromScene(client, entry.getParam0(), entry.getParam1(), client.getPlane());

				if (finishId == 0) {
					if (currentPath.isEmpty()) {
						if (burrows.get(entryTargetPoint) == null)
						{
							entry.setDeprioritized(true);
						}
						else if (config.deprioritizeBurrowWithoutPursuit() && entry.getOption().equals("Inspect") && !isWearingRingOfPursuit()) {
							entry.setDeprioritized(true);
						}
					} else if (!entryTargetPoint.equals(currentPath.get(currentPath.size() - 1))) {
						entry.setDeprioritized(true);
					}
				} else {
					WorldPoint finishLocation = huntingTrail.getEndLocations().get(finishId - 1);
					if (!entryTargetPoint.equals(finishLocation)) {
						entry.setDeprioritized(true);
					} else {
						if (!entry.getOption().equals("Attack")) {
							entry.setDeprioritized(true);
						}
					}
				}

				return;
			}
		}
	}


	@Provides
	RazorKebbitConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(RazorKebbitConfig.class);
	}

	private void resetTrailData() {
		finishId = 0;
		currentPath.clear();
	}

	private void clearCache() {
		burrows.clear();
		trailObjects.clear();
		bushes.clear();
	}

	private boolean isWearingRingOfPursuit()
	{
		ItemContainer equipment = client.getItemContainer(InventoryID.WORN);
		if (equipment == null) {
			return false;
		}

		final Item ring = equipment.getItem(EquipmentInventorySlot.RING.getSlotIdx());
		if (ring == null) {
			return false;
		}

		return ring.getId() == ItemID.RING_OF_PURSUIT;
	}
}
