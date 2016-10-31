package br.ignicaodigital.bestroute.domain;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class City {
	
	private List<Street> streets;
	private List<Interdiction> interdictions;
	private List<Location> locations;

	public City() {
		this.streets = new ArrayList<Street>();
		this.interdictions = new ArrayList<Interdiction>();
		this.locations = new ArrayList<Location>();
	}

	public void update(){
		for(Street street : streets){
			if(!this.locations.contains(street.start)) this.locations.add(street.start);
			if(!this.locations.contains(street.end)) this.locations.add(street.end);
		}
	}
	
	public City connectedBy(String definition) throws Exception {
		this.streets.add(new Street(definition));
		return this;
	}

	public City and(String definition) throws Exception {
		return this.connectedBy(definition);
	}
	
	public City interditedAt(String definition) throws Exception {
		this.interdictions.add(Interdiction.from(definition));
		return this;
	}

	public int speedBetween(Location origin, Location target) {
		Street street = findStreetConnecting(origin, target);
		
		if(street == null) return 0;
		
		return street.maxSpeed;
	}
	
	public List<Street> connections() {
		return streets;
	}

	public List<Location> locations() {
		return locations;
	}

	public List<Interdiction> interdictions() {
		return interdictions;
	}

	public String nameOfConnectionBetween(Location origin, Location target) {
		Street street = findStreetConnecting(origin, target);
		
		if(street == null) return "";
		
		return street.name;
	}

	private Street findStreetConnecting(Location origin, Location target) {
		for(Street street : this.streets){
			if(street.connects(origin, target)){
				return street;
			} else {
				
			}
		}
		
		return null;
	}

	public static City from(String filepath) throws Exception{
		City city = new City();
		
		Files.lines(Paths.get("streets.txt")).forEach((line)->{
			String pattern = "I-\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)";
			Matcher matcher = Pattern.compile(pattern).matcher(line);
			
			if(matcher.find()) {
				try {
					city.interditedAt(line);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					city.connectedBy(line);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		city.update();

		return city;
	}
	
}
