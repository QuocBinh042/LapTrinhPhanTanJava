package jom;

import java.util.ArrayList;
import java.util.List;

import entity.Restaurant;

public class Main {
	public static void main(String[] args) {
		
		Restaurant r = JsonHandler.fromJson("data/restaurant.json");
		JsonHandler.toJson("data/restaurant2.json", r);
		System.out.println(r);
	}
	
	 private static List<Restaurant> findJapaneseRestaurants(List<Restaurant> restaurants) {
	        List<Restaurant> japaneseRestaurants = new ArrayList<>();
	        for (Restaurant restaurant : restaurants) {
	            if ("Japanese".equalsIgnoreCase(restaurant.getCuisine())) {
	                japaneseRestaurants.add(restaurant);
	            }
	        }
	        return japaneseRestaurants;
	    }
}
