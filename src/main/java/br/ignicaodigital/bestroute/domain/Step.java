package br.ignicaodigital.bestroute.domain;

public class Step {

	public final Location origin;
	public final Location target;
	public final Direction direction;
	
	public Step(Location origin, Location target) {
		this.origin = origin;
		this.target = target;
		this.direction = Direction.connecting(origin, target);
	}

}
