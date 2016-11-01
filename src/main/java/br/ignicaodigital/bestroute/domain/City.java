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
		excludeInterditedStreets();
		
		for(Street street : streets){
			if(!this.locations.contains(street.start)) this.locations.add(street.start);
			if(!this.locations.contains(street.end)) this.locations.add(street.end);
		}
	}
	
	public City connectedBy(String definition) throws Exception {
		this.streets.add(new Street(definition));
		excludeInterditedStreets();
		return this;
	}

	public City and(String definition) throws Exception {
		return this.connectedBy(definition);
	}
	
	public City interditedAt(String definition) throws Exception {
		this.interdictions.add(Interdiction.from(definition));
		excludeInterditedStreets();
		return this;
	}

	public void equalizeSpeedOfAllStreets() {
		for(Street street : streets){
			street.timeToCross = 1.0;
//			street.timeToCross = Location.calculateTimeBetween(street.start, street.end, 1);
		}
	}
	
	private void excludeInterditedStreets() {
		List<Street> streetsWithInterdictions = new ArrayList<Street>();
		
		for(Street street : streets){
			for(Interdiction interdiction : interdictions){
				if(street.passThrough(interdiction)){
					streetsWithInterdictions.add(street);
				}
			}
		}
		
		streets.removeAll(streetsWithInterdictions);
	}

	public Double timeBetween(Location origin, Location target) {
		Street street = routeBetween(origin, target);
		if(street == null) return 0.0;
		
		return street.timeToCross;
	}

	public Street routeBetween(Location origin, Location target) {
		for(Street street : this.streets){
			if(street.connects(origin, target)){
				return street;
			}
		}
		
		return null;
	}
	
	public static City from(String filepath) throws Exception{
		City city = new City();
		
		Files.lines(Paths.get(filepath)).forEach((line)->{
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
	
	public List<Street> connections() {
		return streets;
	}

	public List<Location> locations() {
		return locations;
	}

	public List<Interdiction> interdictions() {
		return interdictions;
	}
	
}
