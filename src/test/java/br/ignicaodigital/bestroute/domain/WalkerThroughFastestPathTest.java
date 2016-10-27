package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class WalkerThroughFastestPathTest {

	@Test
	public void shouldWalkOnlyOneEastAndOneNorth() throws Exception{
		City city = new City().connectedBy("Av. Axis-(0,0);(50,0):110")
				.and("Av. Love-(50,0);(50,20):80")
				.and("Av. Awesome-(50,20);(0,20):60")
				.and("Av. New-(0,20);(0,0):10");
		
		Location origin = Location.at(0, 0);
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city, origin);
		
		Map<Location, Direction> directions = walker.directionsTo(Location.at(5, 2));
		
		List<Direction> directionsAsList = new ArrayList<Direction>();
		directionsAsList.addAll(directions.values());
		Collections.reverse(directionsAsList);
		
		for(Direction direction : directionsAsList){
			System.out.println(direction);
		}
		
		assertEquals(2, directions.size());
		assertTrue(directions.containsValue(Direction.EAST));
		assertTrue(directions.containsValue(Direction.NORTH));
	}
	
}
