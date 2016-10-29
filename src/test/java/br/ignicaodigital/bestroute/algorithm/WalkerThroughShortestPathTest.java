package br.ignicaodigital.bestroute.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.ignicaodigital.bestroute.algorithm.Walker;
import br.ignicaodigital.bestroute.algorithm.WalkerThroughShortestPath;
import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Direction;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class WalkerThroughShortestPathTest {
	
	@Test
	public void shouldWalkThroughEastNorth() throws Exception{
		City city = new City().connectedBy("Av. Axis-(0,0);(50,0):10")
				.and("Av. Love-(50,0);(50,20):10")
				.and("Av. Awesome-(50,20);(0,20):10")
				.and("Av. New-(0,20);(0,0):100");
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(5, 2);

		Walker walker = new WalkerThroughShortestPath(city);
		List<Step> directions = walker.directionsBetween(origin, target);
		
		assertEquals(2, directions.size());
		assertEquals(Direction.EAST, directions.get(0).direction);
		assertEquals(Direction.NORTH, directions.get(1).direction);
	}
	
	@Test
	public void shouldWalkOnlyOneEast() throws Exception{
		City city = new City()
				.connectedBy("Av. Axis-(0,0);(50,0):10")
				.and("Av. Awesome-(0,0);(0,20):50")
				.and("Av. Love-(0,20);(50,20):40")
				.and("Av. Down-(50,20);(50,0):100");

		Location origin = Location.at(0, 0);
		Location target = Location.at(50, 0);
		
		Walker walker = new WalkerThroughShortestPath(city);
		List<Step> directions = walker.directionsBetween(origin, target);
		
		assertEquals(1, directions.size());
		assertEquals(Direction.EAST, directions.get(0).direction);
	}
	
}