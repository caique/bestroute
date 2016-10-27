package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void shouldNotAllowNegativeCoordinates(){
		Location location = new Location(-1, -1);
		assertEquals(0, location.x);
		assertEquals(0, location.y);
	}
	
	@Test
	public void shouldReturnCoordinateAsFormattedString(){
		assertEquals("(1,2)", new Location(1, 2).print());
	}
	
	@Test
	public void shouldIdentifyNeighbours(){
		Location location = new Location(1, 1);
		location.identifyNeighbours();
		assertEquals("(1,2)", location.goingToNorthNeighbour().print());
		assertEquals("(1,0)", location.goingToSouthNeighbour().print());
		assertEquals("(2,1)", location.goingToEastNeighbour().print());
		assertEquals("(0,1)", location.goingToWestNeighbour().print());
	}
	
	@Test
	public void originShouldNotHaveWestOrSouthNeighbours(){
		Location location = new Location(0, 0);
		location.identifyNeighbours();
		assertEquals(location, location.goingToSouthNeighbour());
		assertEquals(location, location.goingToWestNeighbour());
	}
	
	@Test
	public void originShouldHaveEastAndNorthNeighbours(){
		Location location = new Location(0, 0);
		location.identifyNeighbours();
		assertNotNull(location.goingToNorthNeighbour());
		assertNotNull(location.goingToEastNeighbour());
	}
	
	@Test
	public void OneOneShouldNotBeEqualToOneTwo(){
		Location a = new Location(1, 1);
		Location b = new Location(1, 2);
		
		assertEquals(Boolean.TRUE, a.isNot(b));
	}
	
	@Test
	public void OneOneShouldBeEqualToOneOne(){
		Location a = new Location(1, 1);
		Location b = new Location(1, 1);
		
		assertEquals(Boolean.FALSE, a.isNot(b));
	}
	
}
