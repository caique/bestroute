package br.ignicaodigital.bestroute.domain;

import java.util.ArrayList;
import java.util.List;

public class WalkerThroughShortestPath implements Walker {
	
	private Location current;
	private List<Direction> directions;

	public WalkerThroughShortestPath(Location origin) {
		this.directions = new ArrayList<Direction>();
		this.current = origin;
	}
	
	public List<Direction> directionsTo(Location target) {
		if(current.isNot(target)){
			current.identifyNeighbours();
			
			if(current.y < target.y){
				goingNorthUntil(target);
				return this.directionsTo(target);
			}
			
			if(current.y > target.y){
				goingSouthUntil(target);
				return this.directionsTo(target);
			}
			
			if(current.x < target.x){
				goingEastUntil(target);
				return this.directionsTo(target);
			}
			
			if(current.x > target.x){
				goingWestUntil(target);
				return this.directionsTo(target);
			}
		}
		
		return directions;
	}

	private void goingNorthUntil(Location target) {
		this.current = current.goingToNorthNeighbour();
		this.directions.add(Direction.NORTH);
	}

	private void goingSouthUntil(Location target) {
		this.current = current.goingToSouthNeighbour();
		this.directions.add(Direction.SOUTH);
	}

	private void goingEastUntil(Location target) {
		this.current = current.goingToEastNeighbour();
		this.directions.add(Direction.EAST);
	}
	
	private void goingWestUntil(Location target) {
		this.current = current.goingToWestNeighbour();		
		this.directions.add(Direction.WEST);
	}
	
	public List<Direction> getDirections() {
		return directions;
	}
}
