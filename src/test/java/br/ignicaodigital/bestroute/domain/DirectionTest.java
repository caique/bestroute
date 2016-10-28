package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;

public class DirectionTest {

	@Test
	public void shouldReturnNorthDirection(){
		Location origin = Location.at(0, 0);
		Location north = Location.at(0, 1);
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, north));
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.NORTH, Direction.connecting(origin, north));
	}
	
	@Test
	public void shouldReturnSouthDirection(){
		Location origin = Location.at(0, 1);
		Location south = Location.at(0, 0);
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, south));
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.SOUTH, Direction.connecting(origin, south));
	}
	
	@Test
	public void shouldReturnEastDirection(){
		Location origin = Location.at(0, 0);
		Location east = Location.at(1, 0);
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, east));
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.EAST, Direction.connecting(origin, east));
	}
	
	@Test
	public void shouldReturnWestDirection(){
		Location origin = Location.at(1, 0);
		Location west = Location.at(0, 0);
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, west));
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.WEST, Direction.connecting(origin, west));
	}
	
	@Test
	public void shouldReturnUndefinedDirectionWhenLocationIsNotInTheCity(){
		Location origin = Location.at(1, 0);
		Location undefined = Location.at(0, 0);
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin));
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.UNDEFINED, Direction.connecting(origin, undefined));
	}
	
}
