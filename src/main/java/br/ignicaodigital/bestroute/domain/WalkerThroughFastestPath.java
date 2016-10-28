package br.ignicaodigital.bestroute.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

	public List<Step> directionsTo(Location target) {
		Location lastMatch = null;
		origin.identifyNeighboursInside(city);
		
		if(origin.isNeighbourOf(target)){
			target.acummulatedSpeed = city.speedBetween(origin, target);
			target.comesThrough(origin);
			lastMatch = target;
		} else {
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
				
				for(Location direction : current.possibleDirectionsInside(city)){
					int speed = current.acummulatedSpeed + this.city.speedBetween(current, direction);
					
					if(speed < direction.acummulatedSpeed){
						direction.acummulatedSpeed = speed;
						direction.comesThrough(current);
						lastMatch = direction;
					}
				}
				
			}
		}
		
		return buildPathBetween(origin, lastMatch);
	}

	private List<Step> buildPathBetween(Location origin, Location lastMatch) {
		Location current = lastMatch;
		
		List<Step> steps = new ArrayList<Step>(); 
		
		while(current.isNot(origin)){
			Location previous = current.comesThrough();
			steps.add(new Step(previous, current));
			current = previous;
		}
		
		Collections.reverse(steps);
		return steps;
	}

	public List<Direction> shouldWalkThrough() {
		return directions;
	}

}
