package br.ignicaodigital.bestroute.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Street {

	String name;
	Location start;
	Location end;
	Double timeToCross;
	
	public Street(String definition) throws Exception {
		// (.+)\s*-\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)\s*;\s*\(\s*(\d+)\s*,\s*(\d+)\s*\)\s*:\s*(\d+)
		String pattern = "(.+)\\s*-\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*;\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*:\\s*(\\d+)";
		Matcher matcher = Pattern.compile(pattern).matcher(definition);
		
		if(matcher.find()) {
			this.name = matcher.group(1);
			this.start = new Location(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
			this.end = new Location(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));
			
			int maxSpeed = new Integer(matcher.group(6));
			this.timeToCross = Location.calculateTimeBetween(start, end, maxSpeed);
		} else {
			throw new Exception("Invalid street definition! Check your entries.");
		}
	}
	
	@Override
	public String toString() {
		return new StringBuilder(name)
			.append("-")
			.append(start.print())
			.append(";")
			.append(end.print())
			.append(":")
			.append(timeToCross).toString();
	}

	public Boolean connects(Location source, Location target) {
		return (this.start.is(source) && this.end.is(target)) 
				|| (this.start.is(target) && this.end.is(source));
	}

	public Boolean passThrough(Location location) {
		return this.start.is(location) || this.end.is(location);
	}

	public String nameOfRoute() {
		return this != null ? this.name : "";
	}

}
