package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class WalkerThroughFastestPathTest {

	@Test
	public void shouldWalkThroughEastNorth() throws Exception{
		City city = new City().connectedBy("Av. Axis-(0,0);(50,0):10")
				.and("Av. Love-(50,0);(50,20):10")
				.and("Av. Awesome-(50,20);(0,20):10")
				.and("Av. New-(0,20);(0,0):100");
		
		Location origin = Location.at(0, 0);
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city, origin);
		
		List<Step> directions = walker.directionsTo(Location.at(5, 2));
		
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
		
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city, origin);
		
		List<Step> directions = walker.directionsTo(target);
		
		assertEquals(1, directions.size());
		assertEquals(Direction.EAST, directions.get(0).direction);
	}
	
	@Ignore
	@Test
	public void shouldWalkThroughNorthEastNorthEast() throws Exception{
		City city = new City()
				.connectedBy("Av. Axis-(0,0);(50,0):10")
				.and("Av. Fast Awesome-(0,0);(0,10):100")
				.and("Av. Slow Awesome Two-(0,10);(0,20):10")
				.and("Av. Slow Awesome Two-(0,20);(10,20):10")
				.and("Av. fast Love-(0,10);(10,10):100")
				.and("Av. fast Up-(10,10);(10,20):100")
				.and("Av. Fast Axis-(10,20);(50,20):40")
				.and("Av. Down-(50,20);(50,0):10");

		Location origin = Location.at(0, 0);
		Location target = Location.at(50, 20);
		
		/*
		 * TODO: INVERT LOGIC OF DIJKSTRA ALGORITMH
		 */
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city, origin);
		
		List<Step> directions = walker.directionsTo(target);
		
		assertEquals(4, directions.size());
		assertEquals(Direction.EAST, directions.get(0).direction);
	}
	
}
