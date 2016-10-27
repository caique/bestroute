package br.ignicaodigital.bestroute.domain;

import java.util.ArrayList;
import java.util.List;


public class Location implements Comparable<Location>{

	int x;
	int y;
	int acummulatedSpeed;

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

	public int compareTo(Location other) {
		if(this.acummulatedSpeed <= other.acummulatedSpeed) return -1;
		if(this.acummulatedSpeed >= other.acummulatedSpeed) return 1;

		return 0;
	}
	
	public void identifyNeighbours() {
		this.northNeighbour = new Location(x, (y+1));
		
		if(y > 0) this.southNeighbour = new Location(x, (y-1));
		
		this.eastNeighbour = new Location((x+1), y);
		
		if(x > 0) this.westNeighbour = new Location((x-1), y);
	}
	
	public void identifyNeighboursInside(City city) {
		for(Location location : city.locations()){
			if(this.hasNorthNeighbourAt(location)) this.northNeighbour = location;
			if(this.hasSouthNeighbourAt(location)) this.southNeighbour = location;
			if(this.hasEastNeighbourAt(location)) this.eastNeighbour = location;
			if(this.hasWestNeighbourAt(location)) this.westNeighbour = location;
		}
	}

	private boolean hasWestNeighbourAt(Location location) {
		return this.intersectsInYWith(location)
				&& this.x > location.x
				&& ((this.westNeighbour == null) || (this.westNeighbour.x < location.x));
	}

	private Boolean hasEastNeighbourAt(Location location) {
		return this.intersectsInYWith(location)
				&& this.x < location.x
				&& ((this.eastNeighbour == null) || (this.eastNeighbour.x > location.x));
	}

	private Boolean hasSouthNeighbourAt(Location location) {
		return this.intersectsInXWith(location)
				&& this.y > location.y
				&& ((this.southNeighbour == null) || (this.southNeighbour.y < location.y));
	}

	private Boolean hasNorthNeighbourAt(Location location) {
		return this.intersectsInXWith(location)
			&& this.y < location.y
			&& ((this.northNeighbour == null) || (this.northNeighbour.y > location.y));
	}

	private Boolean intersectsInXWith(Location location) {
		return this.x == location.x
			&& this.y != location.y;
	}
	
	private Boolean intersectsInYWith(Location location) {
		return this.y == location.y
			&& this.x != location.x;
	}

	public String print(){
		return new StringBuilder("(")
		.append(this.x)
		.append(",")
		.append(this.y)
		.append(")").toString();
	}
	
	public Location goingToNorthNeighbour() {
		return nextLocationGoingTo(northNeighbour);
	}

	public Location goingToSouthNeighbour() {
		return nextLocationGoingTo(southNeighbour);
	}
	
	public Location goingToEastNeighbour() {
		return nextLocationGoingTo(eastNeighbour);
	}
	
	public Location goingToWestNeighbour() {
		return nextLocationGoingTo(westNeighbour);
	}

	private Location nextLocationGoingTo(Location target) {
		if(this.has(target)){
			return target;
		}
		
		return this;
	}
	
	public Boolean has(Location neighbour) {
		return neighbour != null;
	}
	
	public Boolean is(Location target) {
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
	
	public Direction comesFrom(){
		return Direction.connecting(this, previous);
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

	public static Location at(int x, int y) {
		return new Location(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.is((Location) obj);
	}
	
	@Override
	public String toString() {
		return this.print();
	}

}
