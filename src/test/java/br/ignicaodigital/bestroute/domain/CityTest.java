package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class CityTest {

	@Test
	public void shouldCreateACityWithOneStreetAndOneInterdiction() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(2,2):100").interditedAt("I-(2,2)");
		assertEquals(0, city.connections().size());
		assertEquals(1, city.interdictions().size());
	}
	
	@Test
	public void givenTwoLocationsShouldIdentifyIfTheyAreNeighbours() throws Exception{
		new City().connectedBy("Street A-(0,0);(0,2):100").and("Street B-(0,0);(0,1):100");
	}
	
	@Test
	public void timeToReachShouldBe0dot02BetweenStartAndEndLocationsOfAStreetWithMaxSpeedEqualsTo199() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(0,2):100");
		assertEquals(0.02, city.timeBetween(Location.at(0, 0), Location.at(0, 2)), 0);
	}
	
	@Test
	public void timeToReachShouldBe0BetweenLocationsWithoutAStreet() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(0,2):100").and("Street B-(0,0);(2,0):10");
		assertEquals(0.0, city.timeBetween(Location.at(0, 2), Location.at(2, 0)), 0);
	}
	
	@Ignore
	@Test
	public void shouldCreateACityFromAFilePath() throws Exception{
		/* Check if streets.txt exists in the project root with the following content:
			
			Street A-(0,0);(0,2):100
			I-(0,0)
			Street A-(2,2);(0,2):100
			
		 */
		
		City city = City.from("streets.txt");
		assertEquals(1, city.connections().size());
		assertEquals(2, city.locations().size());
		assertEquals(1, city.interdictions().size());
	}
	
}
