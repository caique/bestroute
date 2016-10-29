package br.ignicaodigital.bestroute.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interdiction extends Location {

	public Interdiction(int x, int y) throws Exception {
		super(x, y);
	}
	
	public static Interdiction from(String definition) throws Exception {
		String pattern = "I-\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)";
		Matcher matcher = Pattern.compile(pattern).matcher(definition);
		
		if(matcher.find()) {
			return new Interdiction(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
		} else {
			throw new Exception("Invalid interdiction definition! Check your entries.");
		}
	}
	
}
