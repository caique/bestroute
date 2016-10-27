package br.ignicaodigital.bestroute.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class WalkerThroughFastestPath {

	private static final int INVALID_SPEED = Integer.MAX_VALUE;
	private List<Direction> directions;
	private Location origin;
	private City city;
	
	public WalkerThroughFastestPath(City city, Location origin) throws Exception {
		city.update();
		
		if(origin.isNotInside(city)){
			throw new Exception("Origin is not part of this city. Check your entries.");
		}
		
		this.city = city;
		this.directions = new ArrayList<Direction>();
		this.origin = origin;
	}

	public Map<Location, Direction> directionsTo(Location target) {
		Location lastMatch = null;
		
		NavigableSet<Location> locationsToBeVisited = new TreeSet<Location>();
		
		for (Location location : city.locations()) {
			if(location.isNot(origin)){
				location.acummulatedSpeed = INVALID_SPEED;
				location.comesThrough(null);
			} else {
				location.acummulatedSpeed = 0;
				location.comesThrough(origin);
			}
			
			locationsToBeVisited.add(location);
		}
		
		while(!locationsToBeVisited.isEmpty()){
			Location current = locationsToBeVisited.pollFirst();
			
//			if(current.acummulatedSpeed == INVALID_SPEED) break;
			
			for(Location direction : current.possibleDirectionsInside(city)){
				int speed = current.acummulatedSpeed + this.city.speedBetween(current, direction);
				
				if(speed < direction.acummulatedSpeed){
					direction.acummulatedSpeed = speed;
					direction.comesThrough(current);
					lastMatch = direction;
				}
			}
		}
		
		return buildPathBetween(origin, lastMatch);
	}

	private Map<Location, Direction> buildPathBetween(Location origin, Location lastMatch) {
		Location current = lastMatch;
		
		Map<Location, Direction> locationsThroughDirection = new LinkedHashMap<Location, Direction>();
		
		while(current.isNot(origin)){
			locationsThroughDirection.put(current, current.comesFrom());
			current = current.comesThrough();
		}
		
		return locationsThroughDirection;
	}

	public List<Direction> shouldWalkThrough() {
		return directions;
	}

}
