package jom;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Address;
import entity.Date;
import entity.Grade;
import entity.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class JsonHandler {
	public static Restaurant getRestaurantJson(String fileName) {
		Restaurant r = new Restaurant();
		try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
			JsonObject jo = reader.readObject();
			r.setRestaurant_id(jo.getString("restaurant_id"));
			r.setIs_closed(jo.getBoolean("is_closed"));
			r.setName(jo.getString("name"));
			r.setBorough(jo.getString("borough"));
			r.setCuisine(jo.getString("cuisine"));
			JsonObject joAdr = jo.getJsonObject("address");
			Address adr = new Address();
			adr.setBuilding(joAdr.getString("building"));
			adr.setStreet(joAdr.getString("street"));
			adr.setZipcode(joAdr.getString("zipcode"));
			JsonArray jaCoord = joAdr.getJsonArray("coord");
			List<Double> coordList = new ArrayList<Double>();
			for (JsonValue jv : jaCoord) {
				coordList.add(Double.valueOf(jv.toString()));
			}
			List<Grade> gradeList = new ArrayList<Grade>();
			JsonArray jaG = jo.getJsonArray("grades");
			for (JsonValue jv : jaG) {
				JsonObject joG = (JsonObject) jv;
				Grade g = new Grade();
				g.setGrade(joG.getString("grade"));
				g.setScore(joG.getInt("score"));
				
				Date d = new Date(null, null, null);
				JsonObject joD = joG.getJsonObject("date");
				d.setDay(joD.getInt("day"));
				d.setMonth(joD.getInt("month"));
				d.setYear(joD.getInt("year"));
				g.setDate(d);
				gradeList.add(g);
			}
			adr.setCoord(coordList);
			r.setGrade(gradeList);
			r.setAddress(adr);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}
}
