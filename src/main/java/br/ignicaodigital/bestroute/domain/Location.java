package br.ignicaodigital.bestroute.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Location implements Comparable<Location>{

	final int x;
	final int y;
	private Double accumulatedTimeToReach;

	private Location northNeighbour;
	private Location southNeighbour;
	private Location eastNeighbour;
	private Location westNeighbour;
	private Location previous;
	
	public Location(int x, int y) {
		if (x < 0) {
			this.x = 0;
		} else{
			this.x = x;
		}
		
		if (y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}
	
	public static Location at(int x, int y) {
		return new Location(x, y);
	}
	
	public void identifyNeighbours() {
		this.northNeighbour = new Location(x, (y+1));
		
		if(y > 0) this.southNeighbour = new Location(x, (y-1));
		
		this.eastNeighbour = new Location((x+1), y);
		
		if(x > 0) this.westNeighbour = new Location((x-1), y);
	}
	
	public void identifyNeighboursInside(City city) {
		for(Location location : city.locations()){
			if(this.shouldHaveNorthNeighbourAt(location, city)) this.northNeighbour = location;
			if(this.shouldHaveSouthNeighbourAt(location, city)) this.southNeighbour = location;
			if(this.shouldHaveEastNeighbourAt(location, city)) this.eastNeighbour = location;
			if(this.shouldHaveWestNeighbourAt(location, city)) this.westNeighbour = location;
		}
	}

	private Boolean shouldHaveWestNeighbourAt(Location location, City city) {
		return this.intersectsInYWith(location)
				&& this.x > location.x
				&& ((this.westNeighbour == null) || (this.westNeighbour.x < location.x))
				&& (city.routeBetween(this, location) != null);
	}

	private Boolean shouldHaveEastNeighbourAt(Location location, City city) {
		return this.intersectsInYWith(location)
				&& this.x < location.x
				&& ((this.eastNeighbour == null) || (this.eastNeighbour.x > location.x))
				&& (city.routeBetween(this, location) != null);
	}

	private Boolean shouldHaveSouthNeighbourAt(Location location, City city) {
		return this.intersectsInXWith(location)
				&& this.y > location.y
				&& ((this.southNeighbour == null) || (this.southNeighbour.y < location.y))
				&& (city.routeBetween(this, location) != null);
	}

	private Boolean shouldHaveNorthNeighbourAt(Location location, City city) {
		return this.intersectsInXWith(location)
			&& this.y < location.y
			&& ((this.northNeighbour == null) || (this.northNeighbour.y > location.y))
			&& (city.routeBetween(this, location) != null);
	}
	
	private Boolean intersectsInXWith(Location location) {
		return this.x == location.x;
	}
	
	private Boolean intersectsInYWith(Location location) {
		return this.y == location.y;
	}

	public String print(){
		return new StringBuilder("(")
		.append(this.x)
		.append(",")
		.append(this.y)
		.append(")").toString();
	}
	
	public Boolean isNeighbourOf(Location location){
		return this.hasNorthNeighbourAt(location)
				|| this.hasSouthNeighbourAt(location)
				|| this.hasEastNeighbourAt(location)
				|| this.hasWestNeighbourAt(location);
	}
	
	public Boolean hasNorthNeighbourAt(Location location){
		return this.has(northNeighbour) && this.northNeighbour.is(location);
	}
	
	public Boolean hasSouthNeighbourAt(Location location){
		return this.has(southNeighbour) && this.southNeighbour.is(location);
	}
	
	public Boolean hasEastNeighbourAt(Location location){
		return this.has(eastNeighbour) && this.eastNeighbour.is(location);
	}
	
	public Boolean hasWestNeighbourAt(Location location){
		return this.has(westNeighbour) && this.westNeighbour.is(location);
	}
	
	public Boolean has(Location neighbour) {
		return neighbour != null;
	}
	
	public Boolean is(Location target) {
		if(target == null) return Boolean.FALSE;
		return (this.x == target.x) && (this.y == target.y);
	}
	
	public Boolean isNot(Location target) {
		return (this.x != target.x) || (this.y != target.y);
	}

	public void comesThrough(Location previous) {
		this.previous = previous;
	}
	
	public Location comesThrough() {
		return previous;
	}
	
	public List<Location> possibleDirectionsInside(City city) {
		this.identifyNeighboursInside(city);
		
		List<Location> neighbours = new ArrayList<Location>();
		
		if(this.has(northNeighbour)) neighbours.add(northNeighbour);
		if(this.has(southNeighbour)) neighbours.add(southNeighbour);
		if(this.has(eastNeighbour)) neighbours.add(eastNeighbour);
		if(this.has(westNeighbour)) neighbours.add(westNeighbour);
		
		return neighbours;
	}

	public Boolean isNotInside(City city) {
		return !city.locations().contains(this);
	}
	
	/*
	 * DEFAULT METHODS
	 */
	public int compareTo(Location other) {
		return this.accumulatedTimeToReach.compareTo(other.accumulatedTimeToReach);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.is((Location) obj);
	}
	
	@Override
	public String toString() {
		return this.print();
	}

	public static Location from(String definition) throws Exception {
		// \s*(\d+)\s*,\s*(\d+)\s*
		String pattern = "\\s*(\\d+)\\s*,\\s*(\\d+)\\s*";
		Matcher matcher = Pattern.compile(pattern).matcher(definition);
		
		if(matcher.find()){
			int x = Integer.parseInt(matcher.group(1));
			int y = Integer.parseInt(matcher.group(2));
			return Location.at(x, y);
		} else {
			throw new Exception("Invalid location definition! Check your entries.");
		}
	}

	public void canBeReachedIn(Double time) {
		this.accumulatedTimeToReach = time;
	}

	public Double timeToReach() {
		return accumulatedTimeToReach;
	}

	public static Double calculateTimeBetween(Location start, Location end, int speed) {
		Double cartesianDistance = 0.0;
		
		if(start.intersectsInXWith(end)){
			cartesianDistance = (double) Math.abs(start.y - end.y);
		}
		
		if(start.intersectsInYWith(end)){
			cartesianDistance = (double) Math.abs(start.x - end.x);
		}
		
		return new BigDecimal(cartesianDistance / (double) speed)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}

	public static void generateMultipleFrom(String stopsAsString) {
		
		
	}
	
}
