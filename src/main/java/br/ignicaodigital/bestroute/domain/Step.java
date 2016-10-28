package br.ignicaodigital.bestroute.domain;

public class Step {

	final Location origin;
	final Location target;
	final Direction direction;
	
	public Step(Location origin, Location target) {
		this.origin = origin;
		this.target = target;
		this.direction = Direction.connecting(origin, target);
	}

}
