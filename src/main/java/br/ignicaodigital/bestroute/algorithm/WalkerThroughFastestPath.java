package br.ignicaodigital.bestroute.algorithm;

import java.util.ArrayList;
import java.util.List;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class WalkerThroughFastestPath extends Walker{
	
	public WalkerThroughFastestPath(City city) throws Exception {
		super(city);
	}

	public WalkerThroughFastestPath() {
	}
	
	@Override
	public List<Step> directionsBetween(Location origin, Location target) throws Exception {
		city.update();
		
		Location lastMatch = null;
		List<Location> locationsToBeVisited = new ArrayList<Location>();
		
		for (Location location : city.locations()) {
			if(location.isNot(origin)){
				location.canBeReachedIn(INVALID_TIME);
				location.comesThrough(null);
			} else {
				location.canBeReachedIn(0.0);
				location.comesThrough(origin);
			}
			
			locationsToBeVisited.add(location);
		}
		
		while(!locationsToBeVisited.isEmpty()){
			Location current = nextNeighbourIn(locationsToBeVisited);
			locationsToBeVisited.remove(current);
			
			if(current.is(target)) break;
			
			for(Location direction : current.possibleDirectionsInside(city)){
				Double timeToReachNeighbour = 
						current.timeToReach()
						+ this.city.timeBetween(current, direction);
				
				if(timeToReachNeighbour < direction.timeToReach()){
					direction.canBeReachedIn(timeToReachNeighbour);
					direction.comesThrough(current);
					lastMatch = direction;
				}
			}
			
		}
		
		return stepsBetween(origin, lastMatch);
	}

}
