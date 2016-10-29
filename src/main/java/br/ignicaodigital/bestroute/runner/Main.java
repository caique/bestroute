package br.ignicaodigital.bestroute.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import br.ignicaodigital.bestroute.domain.City;
import br.ignicaodigital.bestroute.domain.Location;
import br.ignicaodigital.bestroute.domain.Step;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
		
		System.out.println("Best Route ----");
		
		// TODO: Recover filepath from system.in and create city from file
		City city = new City().connectedBy("Street A-(0,0);(50,0):100");
		city.update();
		
		//TODO: Make it abstract with two implementations
		TrafficManager trafficManager = new TrafficManager(city);

		System.out.println("Please insert the ORIGIN location in the 'x,y' (without quotes):");
		String originAsString = reader.readLine();
		Location origin = Location.from(originAsString);
		if(origin.isNotInside(city)){
			System.out.println("\n Error: ORIGIN do not exists in this city. Check your entries.");
			System.exit(0);
		}
		
		System.out.println("Please insert the TARGET location in the 'x,y' (without quotes):");
		String targetAsString = reader.readLine();
		Location target = Location.from(targetAsString);
		if(target.isNotInside(city)){
			System.out.println("\n Error: TARGET do not exists in this city. Check your entries.");
			System.exit(0);
		}
		
		System.out.println("\nChoose your preference:");
		System.out.println("1 - Fastest path");
		System.out.println("2 - Shortest path");
		
		int preference = Integer.parseInt(reader.readLine());
		
		if(preference == 1){
			System.out.println("\nFastest Path from" + origin + " to " + target + ":");
			List<Step> path = trafficManager.forFastestPathBetween(origin, target);
			String pathAsString = trafficManager.draw(path);
			System.out.println(pathAsString);
		} else if(preference == 2){
			System.out.println("\nShortest Path from" + origin + " to " + target + ":");
			System.out.println("\n TODO");
		} else {
			System.out.println("Your preference is unavailable.");
			System.exit(0);
		}
	}
	
}
