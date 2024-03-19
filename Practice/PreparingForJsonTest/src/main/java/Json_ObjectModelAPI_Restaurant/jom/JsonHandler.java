package Json_ObjectModelAPI_Restaurant.jom;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Json_ObjectModelAPI_Restaurant.entity.Address;
import Json_ObjectModelAPI_Restaurant.entity.Date;
import Json_ObjectModelAPI_Restaurant.entity.Grades;
import Json_ObjectModelAPI_Restaurant.entity.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JsonHandler {
	public static Restaurant fromJson(String fileName) {
		Restaurant r = new Restaurant();
		try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
			JsonObject jo = reader.readObject();
			r.setRestaurant_id(jo.getString("restaurant_id"));
			r.setBorough(jo.getString("borough"));
			r.setName(jo.getString("name"));
			r.setIs_closed(jo.getBoolean("is_closed"));
			r.setCuisine(jo.getString("cuisine"));
			JsonObject joAddress = jo.getJsonObject("address");
 
			Address address = new Address();
			address.setBuilding(joAddress.getString("building"));
			address.setStreet(joAddress.getString("street"));
			address.setZipcode(joAddress.getString("zipcode"));
			JsonArray coordArray = joAddress.getJsonArray("coord");
			List<Double> coordList = new ArrayList<>();
			for (JsonValue value : coordArray) {
				coordList.add(Double.valueOf(value.toString()));
			}
			address.setCoord(coordList);
			r.setAddress(address);

			JsonArray gradesArray = jo.getJsonArray("grades");
			List<Grades> grades = new ArrayList<>();
			for (JsonValue value : gradesArray) {
				JsonObject gradeObject = (JsonObject) value;
				Grades grade = new Grades();
				JsonObject dateObject = gradeObject.getJsonObject("date");
				int year = dateObject.getInt("year");
				int month = dateObject.getInt("month");
				int day = dateObject.getInt("day");
				Date date = new Date(year - 1900, month - 1, day);
				grade.setDate(date);
				grade.setGrade(gradeObject.getString("grade"));
				grade.setScore(gradeObject.getInt("score"));
				grades.add(grade);
			}
			r.setGrade(grades);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public static void toJson(String fileName, Restaurant restaurant) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(fileName))) {
			JsonObjectBuilder joBuilder = Json.createObjectBuilder();
			joBuilder.add("restaurant_id", restaurant.getRestaurant_id()).add("is_closed", restaurant.isIs_closed())
					.add("name", restaurant.getName()).add("borough", restaurant.getBorough())
					.add("cuisine", restaurant.getCuisine());

			Address adr = restaurant.getAddress();
			JsonObjectBuilder joBuilderAdr = Json.createObjectBuilder();
			joBuilderAdr.add("building", adr.getBuilding()).add("street", adr.getStreet()).add("zipcode",
					adr.getZipcode());

			List<Double> listCoord = adr.getCoord();
			JsonArrayBuilder jaBuilderCoord = Json.createArrayBuilder();
			listCoord.forEach(c -> jaBuilderCoord.add(c));
			joBuilderAdr.add("coord", jaBuilderCoord);

			joBuilder.add("address", joBuilderAdr);

			List<Grades> grade = restaurant.getGrade();
			JsonArrayBuilder jaBuilderGrade = Json.createArrayBuilder();
			grade.forEach(g -> {
				JsonObjectBuilder joBuilderG = Json.createObjectBuilder();
				joBuilderG.add("grade", g.getGrade()).add("score", g.getScore());

				JsonObjectBuilder joBuilderDate = Json.createObjectBuilder();
				Date d = g.getDate();
				joBuilderDate.add("year", d.getYear()).add("month", d.getMonth()).add("day", d.getDay());

				joBuilderG.add("date", joBuilderDate);
				jaBuilderGrade.add(joBuilderG);
			});
			joBuilder.add("grades", jaBuilderGrade);

			JsonObject jsonObject = joBuilder.build();

			writer.writeObject(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
