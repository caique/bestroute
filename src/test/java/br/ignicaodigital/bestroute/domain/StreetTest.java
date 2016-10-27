package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StreetTest {
	
	@Test(expected = Exception.class)
	public void shouldNotCreateANewStreetFromAEmptyString() throws Exception{
		new Street("");
	}
	
	@Test(expected = Exception.class)
	public void shouldNotCreateANewStreetFromAStringWithoutStartLocation() throws Exception{
		new Street("Av. Axis-;(50,0):110");
	}
	
	@Test(expected = Exception.class)
	public void shouldNotCreateANewStreetFromAStringWithoutEndLocation() throws Exception{
		new Street("Av. Axis-(0,0);:110");
	}
	
	@Test(expected = Exception.class)
	public void shouldNotCreateANewStreetFromAStringWithoutMaxSpeedLocation() throws Exception{
		new Street("Av. Axis-(0,0);(50,0):");
	}
	
	@Test
	public void shouldCreateANewStreetFromAGoodString() throws Exception{
		Street streetFromString = new Street("Av. Axis-(0,0);(50,0):110");
		assertEquals("Av. Axis", streetFromString.name);
		assertEquals("(0,0)", streetFromString.start.print());
		assertEquals("(50,0)", streetFromString.end.print());
		assertEquals(110, streetFromString.maxSpeed);
	}

}
