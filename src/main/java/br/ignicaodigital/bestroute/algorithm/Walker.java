package br.ignicaodigital.bestroute.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public abstract class Walker {

	protected static final int INVALID_SPEED = Integer.MAX_VALUE;
	
	protected City city;
	
	public Walker() {
	}
	
	public Walker(City city) {
		this.to(city);
	}
	
	public void to(City city) {
		city.update();
		this.city = city;
	}

	public abstract List<Step> directionsBetween(Location origin, Location target) throws Exception;
	
	protected List<Step> stepsBetween(Location origin, Location lastMatch) {
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

	public Location nextNeighbourIn(List<Location> locationsToBeVisited) {
		Collections.sort(locationsToBeVisited);

		Location current = locationsToBeVisited.get(0);
		
		for(Location location : locationsToBeVisited){
			if(location.acummulatedSpeed() < current.acummulatedSpeed()){
				current = location;
			}
		}
		
		return current;
	}
	
}
