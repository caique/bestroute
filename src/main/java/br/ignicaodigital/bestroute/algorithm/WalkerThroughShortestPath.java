package br.ignicaodigital.bestroute.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class WalkerThroughShortestPath extends Walker {

	public WalkerThroughShortestPath(City city) throws Exception {
		super(city);
	}

	public WalkerThroughShortestPath() {
	}
	
	@Override
	public List<Step> directionsBetween(Location origin, Location target) throws Exception {	
		if(origin.isNeighbourOf(target)) return Arrays.asList(new Step(origin, target));
		
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
			
			for(Location direction : current.possibleDirectionsInside(city)){
				Double timeToReachNeighbour = 
						current.timeToReach()
						+ 1.0;
				
				if(timeToReachNeighbour < direction.timeToReach()){
					direction.canBeReachedIn(timeToReachNeighbour);
					direction.comesThrough(current);
					lastMatch = direction;
					
					if(lastMatch.is(target)){
						locationsToBeVisited.clear();
						break;
					}
				}
			}
			
		}
		
		if(lastMatch.isNot(target)){
			throw new Exception("Impossible to calculate route. The locations are not connected.");
		}
		
		return stepsBetween(origin, lastMatch);
	}

}
