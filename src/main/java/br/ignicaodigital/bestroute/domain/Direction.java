package br.ignicaodigital.bestroute.domain;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;

	public static Direction connecting(Location location, Location origin) {
		if(origin.goingToNorthNeighbour().is(location)) return NORTH;
		if(origin.goingToSouthNeighbour().is(location)) return SOUTH;
		if(origin.goingToEastNeighbour().is(location)) return EAST;
		if(origin.goingToWestNeighbour().is(location)) return WEST;
		
		return null;
	}
}
