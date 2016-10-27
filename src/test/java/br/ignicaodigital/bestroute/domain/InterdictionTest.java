package br.ignicaodigital.bestroute.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class InterdictionTest {

	@SuppressWarnings("unused")
	@Test(expected=Exception.class)
	public void shouldNotCreateAInterdictionWithoutLocation() throws Exception{
		Interdiction interdiction = Interdiction.from("I-");
	}
	
	@Test
	public void shouldCreateAInterdictionFromAGoodString() throws Exception{
		Interdiction interdiction = Interdiction.from("I-(0,0)");
		assertEquals("(0,0)", interdiction.print());
	}
}
