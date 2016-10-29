package br.ignicaodigital.bestroute.runner;

import java.util.List;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;
import br.ignicaodigital.bestroute.domain.WalkerThroughFastestPath;

public class TrafficManager {
	
	private City city;
	
	public TrafficManager(City city) {
		this.city = city;
	}

	public String draw(List<Step> path) {
		StringBuilder pathAsString = new StringBuilder();
		
		for(Step step : path){
			pathAsString
			.append(step.direction)
			.append("-")
			.append(city.nameOfConnectionBetween(step.origin, step.target));
			
			if(!path.get(path.size()-1).equals(step)) pathAsString.append(", ");
		}
		
		return pathAsString.toString();
	}

	public List<Step> forFastestPathBetween(Location origin, Location target) throws Exception {
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city, origin);
		return walker.directionsTo(target);
	}
	
	
	
}
