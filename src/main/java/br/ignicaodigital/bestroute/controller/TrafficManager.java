package br.ignicaodigital.bestroute.controller;

import java.util.List;

import br.ignicaodigital.bestroute.algorithm.Walker;
import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class TrafficManager {
	
	private City city;
	private Walker walker;
	
	private TrafficManager(City city) {
		this.city = city;
	}

	public static TrafficManager to(City city){
		return new TrafficManager(city);
	}
	
	public TrafficManager thinkingAs(Walker walker){
		this.walker = walker;
		this.walker.to(city);
		return this;
	}
	
	public String describeBestPathBetween(Location origin, Location target) throws Exception {
		if (this.walker == null){
			throw new Exception("Walker wasn't defined. Please use method 'thinkingAs' before run this method.");
		}
		
		List<Step> path = walker.directionsBetween(origin, target);
		
		StringBuilder pathAsString = new StringBuilder();
		
		for(Step step : path){
			pathAsString
			.append(step.direction)
			.append("-")
			.append(city.routeBetween(step.origin, step.target).nameOfRoute());
			
			if(!path.get(path.size()-1).equals(step)) pathAsString.append(", ");
		}
		
		return pathAsString.toString();
	}
	
	public String describeBestPathBetween(Location origin, Location target, List<Location> stops) throws Exception{
		if(stops.isEmpty()) return describeBestPathBetween(origin, target);
		
		StringBuilder completePath = new StringBuilder();
		
		completePath.append(describeBestPathBetween(origin, stops.get(0))).append(" ");
		
		for(int stopId = 0; stopId < stops.size()-1; stopId++){
			completePath.append(describeBestPathBetween(stops.get(stopId), stops.get(stopId+1))).append(" ");
		}
		
		completePath.append(describeBestPathBetween(stops.get(stops.size()-1), target));
		
		return completePath.toString();
	}

}
