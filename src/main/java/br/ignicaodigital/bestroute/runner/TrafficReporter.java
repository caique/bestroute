package br.ignicaodigital.bestroute.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.ignicaodigital.bestroute.domain.Direction;
import br.ignicaodigital.bestroute.domain.Location;

public class TrafficReporter {
	
	public static void drawStepsFrom(Collection<Location> locations){
		List<Location> locationsAsList = new ArrayList<Location>();
		locationsAsList.addAll(locations);
		Collections.reverse(locationsAsList);
		
		for(Location location : locationsAsList){
			System.out.println(location);
		}
	}
	
	public static void drawPathFrom(Collection<Direction> directions){
		List<Direction> directionsAsList = new ArrayList<Direction>();
		directionsAsList.addAll(directions);
		Collections.reverse(directionsAsList);
		
		for(Direction direction : directionsAsList){
			System.out.println(direction);
		}
	}
	
}
