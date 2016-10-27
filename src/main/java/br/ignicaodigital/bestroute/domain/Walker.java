package br.ignicaodigital.bestroute.domain;

import java.util.List;

public interface Walker {

	public List<Direction> directionsTo(Location target);
	
	public List<Direction> getDirections();
	
}
