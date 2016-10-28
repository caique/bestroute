package br.ignicaodigital.bestroute.domain;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST,
	ORIGIN,
	UNDEFINED;

	public static Direction connecting(Location origin, Location location) {
		if(origin.hasNorthNeighbourAt(location)) return NORTH;
		if(origin.hasSouthNeighbourAt(location)) return SOUTH;
		if(origin.hasEastNeighbourAt(location)) return EAST;
		if(origin.hasWestNeighbourAt(location)) return WEST;
		
		return UNDEFINED;
	}
}
