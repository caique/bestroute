package br.ignicaodigital.bestroute.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Street {

	String name;
	Location start;
	Location end;
	int maxSpeed;
	
	public Street(String definition) throws Exception {
		// (.+)-\(([0-9]+),([0-9]+)\);\(([0-9]+),([0-9]+)\):([0-9]+)
		String pattern = "(.+)-\\(([0-9]+),([0-9]+)\\);\\(([0-9]+),([0-9]+)\\):([0-9]+)";
		Matcher matcher = Pattern.compile(pattern).matcher(definition);
		
		if(matcher.find()) {
			this.name = matcher.group(1);
			this.start = new Location(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
			this.end = new Location(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));
			this.maxSpeed = new Integer(matcher.group(6));
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
			.append(maxSpeed).toString();
	}

	public Boolean connects(Location source, Location target) {
		return (this.start.is(source) && this.end.is(target)) || (this.start.is(target) && this.end.is(source));
	}
	
}
