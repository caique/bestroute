package br.ignicaodigital.bestroute.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.ignicaodigital.bestroute.algorithm.WalkerThroughFastestPath;
import br.ignicaodigital.bestroute.algorithm.WalkerThroughShortestPath;
import br.ignicaodigital.bestroute.controller.TrafficManager;
import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
		TrafficManager trafficManager = null;
		
		System.out.println("Best Route ----");
		
		System.out.println("Please provide the filepath to the city configurations:");
		String cityConfigurations = reader.readLine();
		City city = City.from(cityConfigurations);

		System.out.println("Please insert the ORIGIN location in the 'x,y' format (without quotes):");
		String originAsString = reader.readLine();
		Location origin = Location.from(originAsString);
		if(origin.isNotInside(city)){
			System.out.println("\n Error: ORIGIN do not exists in this city. Check your entries.");
			System.exit(0);
		}
		
		System.out.println("Please insert the TARGET location in the 'x,y' format (without quotes):");
		String targetAsString = reader.readLine();
		Location target = Location.from(targetAsString);
		if(target.isNotInside(city)){
			System.out.println("\n Error: TARGET do not exists in this city. Check your entries.");
			System.exit(0);
		}
		
		String option = "y";
		List<Location> stops = new ArrayList<Location>();
		
		while(!option.toLowerCase().equals("n")){
			System.out.println("Do you want to add a stop? (y/n):");
			option = reader.readLine();
			
			if(option.toLowerCase().equals("y")){
				System.out.println("Please insert the STOP location in the 'x,y' format (without quotes):");
				String stopAsString = reader.readLine();
				Location stop = Location.from(stopAsString);
		
				if(stop.isNotInside(city)){
					System.out.println("\n Error: STOP do not exists in this city. Check your entries.");
					System.exit(0);
				}
				
				stops.add(stop);
			}
		}
		
		System.out.println("\nChoose your preference:");
		System.out.println("1 - Fastest path");
		System.out.println("2 - Shortest path");
		
		int preference = Integer.parseInt(reader.readLine());
		
		if(preference == 1){
			System.out.println("\nFastest Path from" + origin + " to " + target + ":");
			
			trafficManager = TrafficManager
					.to(city)
					.thinkingAs(new WalkerThroughFastestPath(city));
			
		} else if(preference == 2){
			System.out.println("\nShortest Path from" + origin + " to " + target + ":");
			
			trafficManager = TrafficManager
					.to(city)
					.thinkingAs(new WalkerThroughShortestPath(city));
			
		} else {
			System.out.println("Your preference is unavailable, please try another option.");
			System.exit(0);
		}
		
		String path = trafficManager.describeBestPathBetween(origin, target, stops);
		System.out.println(path);
	}
	
}
