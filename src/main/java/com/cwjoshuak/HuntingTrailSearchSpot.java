package com.cwjoshuak;

import java.util.Map;
import lombok.Value;
import net.runelite.api.coords.WorldPoint;

@Value
public class HuntingTrailSearchSpot
{
	int varbit;
	Map<Integer, WorldPoint> activeStates;

	HuntingTrailSearchSpot(int varbit, Map<Integer, WorldPoint> activeStates)
	{
		this.varbit = varbit;
		this.activeStates = activeStates;
	}

	HuntingTrailSearchSpot(int varbit, WorldPoint worldPoint)
	{
		this(
			varbit,
			Map.of(
				1, worldPoint,
				2, worldPoint
			)
		);
	}

	WorldPoint getWorldPoint(int value)
	{
		switch (value)
		{
			case 1:
			case 3:
				return activeStates.get(1);
			case 2:
			case 4:
				return activeStates.get(2);
			default:
				return null;
		}
	}
}
