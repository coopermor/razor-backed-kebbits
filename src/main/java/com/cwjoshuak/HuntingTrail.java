package com.cwjoshuak;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.ObjectID;
import net.runelite.api.gameval.VarbitID;

@Getter
public enum HuntingTrail
{
	RAZOR_BACKED_KEBBIT(
		9272,
		VarbitID.HUNTING_TRAIL_STATE2_9,
		Set.of("Plant", "Bush", "Burrow"),
		Set.of(
			ObjectID.HUNTING_TRAIL_SPAWN1,
			ObjectID.HUNTING_TRAIL_SPAWN3,
			ObjectID.HUNTING_TRAIL_SPAWN2
		),
		List.of(
			new WorldPoint(2358, 3620, 0),
			new WorldPoint(2351, 3619, 0),
			new WorldPoint(2362, 3615, 0),
			new WorldPoint(2354, 3609, 0),
			new WorldPoint(2357, 3607, 0),
			new WorldPoint(2349, 3604, 0),
			new WorldPoint(2360, 3602, 0),
			new WorldPoint(2355, 3601, 0)
		),
		List.of(
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_0, new WorldPoint(2362, 3598, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_1, new WorldPoint(2355, 3598, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_2, new WorldPoint(2347, 3603, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_3, new WorldPoint(2358, 3599, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_4, new WorldPoint(2352, 3603, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_5, new WorldPoint(2358, 3603, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_6, new WorldPoint(2363, 3602, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_7, new WorldPoint(2358, 3607, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_8, new WorldPoint(2355, 3608, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE1_9, new WorldPoint(2351, 3608, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_0, new WorldPoint(2363, 3617, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_1, new WorldPoint(2349, 3620, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_2, new WorldPoint(2356, 3620, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_3, new WorldPoint(2344, 3612, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_4, new WorldPoint(2352, 3612, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_5, new WorldPoint(2349, 3617, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_6, new WorldPoint(2352, 3618, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_7, new WorldPoint(2362, 3614, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE2_8, new WorldPoint(2360, 3618, 0))
		)
	),
	FELDIP_WEASEL(
		10029,
		VarbitID.HUNTING_TRAIL_ENDS_JUNGLE,
		Set.of("Jungle plant", "Bush", "Burrow"),
		Set.of(
			ObjectID.HUNTING_TRAIL_SPAWN_JUNGLE1,
			ObjectID.HUNTING_TRAIL_SPAWN_JUNGLE2
		),
		List.of(
			new WorldPoint(2525, 2882, 0),
			new WorldPoint(2531, 2890, 0),
			new WorldPoint(2533, 2885, 0),
			new WorldPoint(2540, 2886, 0),
			new WorldPoint(2542, 2881, 0),
			new WorldPoint(2553, 2888, 0)
		),
		List.of(
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE6_3, Map.of(
				1, new WorldPoint(2522, 2881, 0),
				2, new WorldPoint(2524, 2891, 0)
			)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE6_4, new WorldPoint(2524, 2886, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE6_5, new WorldPoint(2527, 2890, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE6_6, new WorldPoint(2530, 2887, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE6_7, Map.of(
				1, new WorldPoint(2531, 2881, 0),
				2, new WorldPoint(2526, 2882, 0)
			)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE6_8, new WorldPoint(2533, 2882, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_0, Map.of(
				1, new WorldPoint(2533, 2889, 0),
				2, new WorldPoint(2539, 2890, 0)
			)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_1, Map.of(
				1, new WorldPoint(2534, 2886, 0),
				2, new WorldPoint(2540, 2884, 0)
			)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_2, new WorldPoint(2537, 2880, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_3, new WorldPoint(2541, 2883, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_4, new WorldPoint(2540, 2887, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_5, Map.of(
				1, new WorldPoint(2543, 2890, 0),
				2, new WorldPoint(2551, 2888, 0)
			)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_6, new WorldPoint(2551, 2881, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_7, new WorldPoint(2552, 2885, 0)),
			new HuntingTrailSearchSpot(VarbitID.HUNTING_TRAIL_STATE7_8, new WorldPoint(2554, 2888, 0))
		)
	),
	;

	private final int region;
	private final int finishVarbit;
	private final Set<String> menuTargets;
	private final Set<Integer> startObjectIds;
	private final List<WorldPoint> endLocations;
	private final Map<Integer, HuntingTrailSearchSpot> searchSpots;

	HuntingTrail(
		int region,
		int finishVarbit,
		Set<String> menuTargets,
		Set<Integer> startObjectIds,
		List<WorldPoint> endLocations,
		List<HuntingTrailSearchSpot> searchSpots
	)
	{
		this.region = region;
		this.finishVarbit = finishVarbit;
		this.menuTargets = menuTargets;
		this.startObjectIds = startObjectIds;
		this.endLocations = endLocations;
		this.searchSpots = searchSpots.stream()
			.collect(Collectors.toMap(
				HuntingTrailSearchSpot::getVarbit,
				Function.identity()
			));
	}
	boolean isSearchSpot(WorldPoint worldPoint)
	{
		return searchSpots.values().stream()
			.flatMap(spot -> spot.getActiveStates().values().stream())
			.anyMatch(worldPoint::equals);
	}

	WorldPoint getSearchSpot(int varbit, int value)
	{
		HuntingTrailSearchSpot spot = searchSpots.get(varbit);

		if (spot == null)
		{
			return null;
		}

		return spot.getWorldPoint(value);
	}

	boolean hasSearchVarbit(int varbit)
	{
		return searchSpots.containsKey(varbit);
	}
}
