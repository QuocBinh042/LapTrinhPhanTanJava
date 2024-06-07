package jom;

import entity.Restaurant;

public class Main {
	public static void main(String[] args) {
		Restaurant r = JsonHandler2.getRestaurantJson("data/restaurant.json");
		System.out.println(r);
	}
}
