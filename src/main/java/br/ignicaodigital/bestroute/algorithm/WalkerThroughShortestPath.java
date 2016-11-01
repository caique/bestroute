package br.ignicaodigital.bestroute.algorithm;

import java.util.List;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class WalkerThroughShortestPath extends WalkerThroughFastestPath {

	public WalkerThroughShortestPath(City city) throws Exception {
		super(city);
	}

	public WalkerThroughShortestPath() {
	}
	
	@Override
	public List<Step> directionsBetween(Location origin, Location target) throws Exception {	
		city.equalizeSpeedOfAllStreets();
		return super.directionsBetween(origin, target);
	}

}
