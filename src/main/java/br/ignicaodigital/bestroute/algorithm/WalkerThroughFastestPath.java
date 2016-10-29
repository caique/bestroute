package br.ignicaodigital.bestroute.algorithm;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

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
		Location lastMatch = null;
		origin.identifyNeighboursInside(city);
		
		if(origin.isNeighbourOf(target)){
			target.with(city.speedBetween(origin, target));
			target.comesThrough(origin);
			lastMatch = target;
		} else {
			NavigableSet<Location> locationsToBeVisited = new TreeSet<Location>();
			
			for (Location location : city.locations()) {
				if(location.isNot(origin)){
					location.with(INVALID_SPEED);
					location.comesThrough(null);
				} else {
					location.with(0);
					location.comesThrough(origin);
				}
				
				locationsToBeVisited.add(location);
			}
			
			while(!locationsToBeVisited.isEmpty()){
				Location current = locationsToBeVisited.pollFirst();
				
				for(Location direction : current.possibleDirectionsInside(city)){
					int speedToNeighbour = 
							current.acummulatedSpeed()
							+ this.city.speedBetween(current, direction);
					
					if(speedToNeighbour < direction.acummulatedSpeed()){
						direction.with(speedToNeighbour);
						direction.comesThrough(current);
						lastMatch = direction;
					}
				}
				
			}
		}
		
		return stepsBetween(origin, lastMatch);
	}

}
