package br.ignicaodigital.bestroute.runner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class TrafficManagerTest {

	@Test
	public void shouldPrintStepsSeparetedByCommas(){
		Step stepA = new Step(Location.at(0, 0), Location.at(0, 1));
		Step stepB = new Step(Location.at(0, 1), Location.at(1, 1));
		
		List<Step> path = new ArrayList<Step>();
		path.add(stepA);
		path.add(stepB);
		
		City city = mock(City.class);
		when(city.nameOfConnectionBetween(stepA.origin, stepA.target)).thenReturn("StreetA");
		when(city.nameOfConnectionBetween(stepB.origin, stepB.target)).thenReturn("StreetB");
		
		String pathAsString = new TrafficManager(city).draw(path);
		
		assertEquals("U-StreetA, U-StreetB", pathAsString);
	}
	
	@Test
	public void shouldPrintASingleStepWithoutCommas(){
		Step stepA = new Step(Location.at(0, 0), Location.at(0, 1));
		
		List<Step> path = new ArrayList<Step>();
		path.add(stepA);
		
		City city = mock(City.class);
		when(city.nameOfConnectionBetween(stepA.origin, stepA.target)).thenReturn("StreetA");
		
		String pathAsString = new TrafficManager(city).draw(path);
		
		assertEquals("U-StreetA", pathAsString);
	}
	
}
