package br.ignicaodigital.bestroute.controller;

import org.junit.Test;

import br.ignicaodigital.bestroute.algorithm.WalkerThroughFastestPath;
import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

public class TrafficManagerTest {

	@Test
	public void shouldPrintCompletePathWithOneStop() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):100")
				.and("Street B-(4,0);(4,4):100")
				.and("Street C-(4,4);(0,4):100")
				.and("Street D-(0,4);(0,0):100");
		
		TrafficManager trafficManager = TrafficManager.to(city);
		trafficManager.thinkingAs(new WalkerThroughFastestPath());
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 4);
		Location stopA = Location.at(0, 4);
		
		String path = trafficManager.describeBestPathBetween(origin, target, Arrays.asList(stopA));
		assertEquals("N-Street D E-Street C", path);
	}
	
	@Test
	public void shouldPrintCompletePathWithMultipleStops() throws Exception{
		City city = new City().connectedBy("Street A-(0,0);(4,0):100")
				.and("Street B-(4,0);(4,4):100")
				.and("Street C-(4,4);(0,4):100")
				.and("Street D-(0,4);(0,0):100");
		
		TrafficManager trafficManager = TrafficManager.to(city);
		trafficManager.thinkingAs(new WalkerThroughFastestPath());
		
		Location origin = Location.at(0, 0);
		Location target = Location.at(4, 0);
		Location stopA = Location.at(0, 4);
		Location stopB = Location.at(4, 4);
		
		String path = trafficManager.describeBestPathBetween(origin, target, Arrays.asList(stopA, stopB));
		assertEquals("N-Street D E-Street C S-Street B", path);
	}
	
	@Test(expected = Exception.class)
	public void shouldThrowsExceptionWhenWalkerIsNotDefined() throws Exception{
		City city = mock(City.class);
		
		TrafficManager trafficManager = TrafficManager.to(city);
		trafficManager.describeBestPathBetween(Location.at(0, 0), Location.at(0, 1));
	}
	
}
