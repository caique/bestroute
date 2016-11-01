package br.ignicaodigital.bestroute.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Direction;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class WalkerThroughFastestPathTest {

	@Test
	public void shouldWalkThroughEastNorth() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):100")
				.and("Street B-(4,0);(4,4):100")
				.and("Street C-(4,4);(0,4):10")
				.and("Street D-(0,4);(0,0):10");
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 4);

		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city);
		List<Step> directions = walker.directionsBetween(origin, target);
		
		assertEquals(2, directions.size());
		assertEquals(Direction.EAST, directions.get(0).direction);
		assertEquals(Direction.NORTH, directions.get(1).direction);
	}
	
	@Test
	public void shouldWalkOnlyOneEast() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):100")
				.and("Street B-(4,0);(4,4):10")
				.and("Street C-(4,4);(0,4):10")
				.and("Street D-(0,4);(0,0):10");
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 0);
		
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city);
		List<Step> directions = walker.directionsBetween(origin, target);
		
		assertEquals(1, directions.size());
		assertEquals(Direction.EAST, directions.get(0).direction);
	}
	
	@Test
	public void shouldWalkThroughNorthEastSouth() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):1")
				.and("Street B-(4,0);(4,4):100")
				.and("Street C-(4,4);(0,4):100")
				.and("Street D-(0,4);(0,0):100");
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 0);
		
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city);
		List<Step> directions = walker.directionsBetween(origin, target);
		
		assertEquals(3, directions.size());
		assertEquals(Direction.NORTH, directions.get(0).direction);
		assertEquals(Direction.EAST, directions.get(1).direction);
		assertEquals(Direction.SOUTH, directions.get(2).direction);
	}
	
	@Test
	public void shouldWalkThroughNorthEastNorthEast() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):1")
				.and("Street B-(4,0);(4,3):1")
				.and("Street C-(4,3);(2,3):100")
				.and("Street D-(2,3);(2,2):100")
				.and("Street C-(2,2);(0,2):100")
				.and("Street D-(0,2);(0,0):100");
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 3);
		
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city);
		List<Step> directions = walker.directionsBetween(origin, target);
		
		assertEquals(4, directions.size());
		assertEquals(Direction.NORTH, directions.get(0).direction);
		assertEquals(Direction.EAST, directions.get(1).direction);
		assertEquals(Direction.NORTH, directions.get(2).direction);
		assertEquals(Direction.EAST, directions.get(3).direction);
	}
	
	@Test(expected = Exception.class)
	public void shouldThroughExceptionWhenPointAreDisconnected() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):100")
				.and("Street C-(4,4);(0,4):10");

		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 4);
		
		WalkerThroughFastestPath walker = new WalkerThroughFastestPath(city);
		walker.directionsBetween(origin, target);
	}
	
}
