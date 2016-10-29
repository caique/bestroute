package br.ignicaodigital.bestroute.domain;

public enum Direction {
	NORTH("N"),
	SOUTH("S"),
	EAST("E"),
	WEST("W"),
	ORIGIN("O"),
	UNDEFINED("U");

	private String description;
	
	private Direction(String description) {
		this.description = description;
	}
	
	public static Direction connecting(Location origin, Location location) {
		if(origin.hasNorthNeighbourAt(location)) return NORTH;
		if(origin.hasSouthNeighbourAt(location)) return SOUTH;
		if(origin.hasEastNeighbourAt(location)) return EAST;
		if(origin.hasWestNeighbourAt(location)) return WEST;
		
		return UNDEFINED;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
