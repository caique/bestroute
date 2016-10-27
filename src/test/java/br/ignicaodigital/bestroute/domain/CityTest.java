package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class CityTest {

	@Test
	public void shouldCreateACityWithOneStreetAndOneInterdiction() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(2,2):100").interditedAt("I-(2,2)");
		assertEquals(1, city.connections().size());
		assertEquals(1, city.interdictions().size());
	}
	
	@Test
	public void givenTwoLocationsShouldIdentifyIfTheyAreNeighbours() throws Exception{
		new City().connectedBy("Street A-(0,0);(0,2):100").and("Street B-(0,0);(0,1):100");
	}
	
	@Test
	public void speedShouldBe100BetweenStartAndEndLocationsOfAStreetWithMaxSpeedEqualsTo199() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(0,2):100");
		assertEquals(Integer.valueOf(100), city.speedBetween(Location.at(0, 0), Location.at(0, 2)));
	}
	
	@Ignore
	@Test
	public void speedShouldBeInvalidBetweenLocationsWithoutAStreet() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(0,2):100").and("Street B-(0,0);(0,1):100");
		assertTrue(city.speedBetween(Location.at(0, 2), Location.at(0, 1)) < 0);
	}
	
	@Test
	public void speedShouldBe110BetweenLocationsWithoutAStreet() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(0,2):100").and("Street B-(0,0);(2,0):10");
		assertEquals(Integer.valueOf(110), city.speedBetween(Location.at(0, 2), Location.at(2, 0)));
	}
	
}
