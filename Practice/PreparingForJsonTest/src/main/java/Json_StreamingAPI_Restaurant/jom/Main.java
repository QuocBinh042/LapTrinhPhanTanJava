package Json_StreamingAPI_Restaurant.jom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Json_StreamingAPI_Restaurant.entity.Restaurant;
import Json_StreamingAPI_Restaurant.jom.JsonHandler;

public class Main {
    public static void main(String[] args) {
        Restaurant r = JsonHandler.fromJson("data/restaurant.json");
        System.out.println(r);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(r);
        List<Restaurant> japaneseRestaurants = restaurants.stream()
            .filter(restaurant -> "Japanese".equals(restaurant.getCuisine()))
            .limit(3)
            .collect(Collectors.toList());
        japaneseRestaurants.forEach(System.out::println);
    }
}
