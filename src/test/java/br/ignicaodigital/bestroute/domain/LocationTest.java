package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
	public void oneOneShouldNotBeEqualToOneTwo(){
		Location a = new Location(1, 1);
		Location b = new Location(1, 2);
		
		assertEquals(Boolean.TRUE, a.isNot(b));
	}
	
	@Test
	public void oneOneShouldBeEqualToOneOne(){
		Location a = new Location(1, 1);
		Location b = new Location(1, 1);
		
		assertEquals(Boolean.FALSE, a.isNot(b));
	}
	
	@Test
	public void zeroZeroShouldBeEastNeighbourOfFifthZeroWhenThereIsOnlyThemInTheMap(){
		Location origin = Location.at(0, 0);
		Location target = Location.at(50, 0);
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, target));
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertTrue(origin.hasEastNeighbourAt(target));
		assertTrue(origin.isNeighbourOf(target));
		
	}
	
	@Test
	public void shouldReturnANewLocationFromAValidString() throws Exception{
		Location.from("0,0");
		Location.from("0, 0");
		Location.from("(0,0)");
		Location.from("(0, 0)");
	}
	
	@Test(expected = Exception.class)
	public void shouldRaiseExceptionFromAInvalidString() throws Exception{
		Location.from("0.0");
	}
	
}
