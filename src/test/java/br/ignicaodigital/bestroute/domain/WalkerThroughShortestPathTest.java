package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WalkerThroughShortestPathTest {

	@Test
	public void shouldWalkOnlyOneEast(){
		Location origin = new Location(1, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(2, 1));
		
		assertEquals(1, walker.getDirections().size());
		assertEquals(Direction.EAST, walker.getDirections().get(0));
	}
	
	@Test
	public void shouldWalkOnlyOneWest(){
		Location origin = new Location(1, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(0, 1));
		
		assertEquals(1, walker.getDirections().size());
		assertEquals(Direction.WEST, walker.getDirections().get(0));
	}
	
	@Test
	public void shouldWalkOnlyOneNorth(){
		Location origin = new Location(1, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(1, 2));
		
		assertEquals(1, walker.getDirections().size());
		assertEquals(Direction.NORTH, walker.getDirections().get(0));
	}
	
	@Test
	public void shouldWalkOnlyOneSouth(){
		Location origin = new Location(1, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(1, 0));
		
		assertEquals(1, walker.getDirections().size());
		assertEquals(Direction.SOUTH, walker.getDirections().get(0));
	}
	
	@Test
	public void shouldWalkOnlyOneSouthAndOneEast(){
		Location origin = new Location(1, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(2, 0));
		
		assertEquals(2, walker.getDirections().size());
		assertEquals(Direction.SOUTH, walker.getDirections().get(0));
		assertEquals(Direction.EAST, walker.getDirections().get(1));
	}
	
	@Test
	public void shouldWalkOnlyFiveNorthsAndTwoEast(){
		Location origin = new Location(1, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(3, 6));
		
		assertEquals(7, walker.getDirections().size());
		assertEquals(Direction.NORTH, walker.getDirections().get(0));
		assertEquals(Direction.NORTH, walker.getDirections().get(1));
		assertEquals(Direction.NORTH, walker.getDirections().get(2));
		assertEquals(Direction.NORTH, walker.getDirections().get(3));
		assertEquals(Direction.NORTH, walker.getDirections().get(4));
		assertEquals(Direction.EAST, walker.getDirections().get(5));
		assertEquals(Direction.EAST, walker.getDirections().get(6));
	}
	
	@Test
	public void shouldIdentifyTheZeroLimitOnY(){
		Location origin = new Location(4, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(2, -1));
		
		assertEquals(3, walker.getDirections().size());
		assertEquals(Direction.SOUTH, walker.getDirections().get(0));
		assertEquals(Direction.WEST, walker.getDirections().get(1));
		assertEquals(Direction.WEST, walker.getDirections().get(2));
	}
	
	@Test
	public void shouldIdentifyTheZeroLimitOnX(){
		Location origin = new Location(2, 1);
		origin.identifyNeighbours();
		
		WalkerThroughShortestPath walker = new WalkerThroughShortestPath(origin);
		walker.directionsTo(new Location(-1, 0));
		
		assertEquals(3, walker.getDirections().size());
		assertEquals(Direction.SOUTH, walker.getDirections().get(0));
		assertEquals(Direction.WEST, walker.getDirections().get(1));
		assertEquals(Direction.WEST, walker.getDirections().get(2));
	}
	
}
