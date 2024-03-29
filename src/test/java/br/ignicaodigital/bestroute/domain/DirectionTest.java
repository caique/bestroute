package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;

public class DirectionTest {

	@Test
	public void shouldReturnNorthDirection() throws Exception{
		Location origin = Location.at(0, 0);
		Location north = Location.at(0, 1);
		
		Street street = new Street("Street Test-(0,0);(0,1):1");
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, north));
		when(mockedCity.routeBetween(origin, north)).thenReturn(street);
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.NORTH, Direction.connecting(origin, north));
	}
	
	@Test
	public void shouldReturnSouthDirection() throws Exception{
		Location origin = Location.at(0, 1);
		Location south = Location.at(0, 0);
		
		Street street = new Street("Street Test-(0,1);(0,0):1");
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, south));
		when(mockedCity.routeBetween(origin, south)).thenReturn(street);
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.SOUTH, Direction.connecting(origin, south));
	}
	
	@Test
	public void shouldReturnEastDirection() throws Exception{
		Location origin = Location.at(0, 0);
		Location east = Location.at(1, 0);
		
		Street street = new Street("Street Test-(0,0);(1,0):1");
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, east));
		when(mockedCity.routeBetween(origin, east)).thenReturn(street);
		
		origin.identifyNeighboursInside(mockedCity);
		
		assertEquals(Direction.EAST, Direction.connecting(origin, east));
	}
	
	@Test
	public void shouldReturnWestDirection() throws Exception{
		Location origin = Location.at(1, 0);
		Location west = Location.at(0, 0);
		
		Street street = new Street("Street Test-(1,0);(0,0):1");
		
		City mockedCity = mock(City.class);
		when(mockedCity.locations()).thenReturn(Arrays.asList(origin, west));
		when(mockedCity.routeBetween(origin, west)).thenReturn(street);
		
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
