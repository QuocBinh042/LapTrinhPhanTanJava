package jom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Address;
import entity.Date;
import entity.Grade;
import entity.Restaurant;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

public class JsonHandler2 {
	public static Restaurant getRestaurantJson(String fileName) {
		Restaurant r = new Restaurant();
		try (JsonParser parser = Json.createParser(new FileInputStream(fileName))) {
			String key = null;
			while (parser.hasNext()) {
				JsonParser.Event event = parser.next();
				switch (event) {
				case KEY_NAME:
					key = parser.getString();
					break;
				case START_OBJECT:
					if (key != null && key.equals("address")) {
						Address adr = new Address();
						JsonParser.Event addressEvent = null;
						while (parser.hasNext() && addressEvent != JsonParser.Event.END_OBJECT) {
							addressEvent = parser.next();
							if (addressEvent == JsonParser.Event.KEY_NAME) {
								key = parser.getString();
								parser.next();
								switch (key) {
								case "building":
									adr.setBuilding(parser.getString());
									break;
								case "coord":
									List<Double> coordList = new ArrayList<Double>();
									while (parser.hasNext() && parser.next() != parser.currentEvent().END_ARRAY) {
										coordList.add(parser.getBigDecimal().doubleValue());
									}
									adr.setCoord(coordList);
									break;
								case "street":
									adr.setStreet(parser.getString());
									break;
								case "zipcode":
									adr.setZipcode(parser.getString());
								}
							}
						}
						r.setAddress(adr);
					}
					break;
				case START_ARRAY:
					if ("grades".equals(key)) {
						r.setGrade(parseGrades(parser));
					}
					break;
				case VALUE_STRING:
					switch (key) {
					case "restaurant_id":
						r.setRestaurant_id(parser.getString());
						break;
					case "name":
						r.setName(parser.getString());
						break;
					case "borough":
						r.setBorough(parser.getString());
						break;
					case "cuisine":
						r.setCuisine(parser.getString());
						break;
					}
					break;
				case VALUE_TRUE:
					r.setIs_closed(true);
					break;
				}
			}

		} catch (

		IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}

	private static List<Grade> parseGrades(JsonParser parser) {
		List<Grade> grades = new ArrayList<>();
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			if (event == JsonParser.Event.START_OBJECT) {
				grades.add(parseGrade(parser));
			}
		}
		return grades;
	}

	private static Grade parseGrade(JsonParser parser) {
		Grade grade = new Grade();
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			if (event == JsonParser.Event.KEY_NAME) {
				String key = parser.getString();
				parser.next();
				switch (key) {
				case "date":
					JsonParser.Event dateEvent = parser.next();
					int year = 0, month = 0, day = 0;
					while (dateEvent != JsonParser.Event.END_OBJECT) {
						String name = parser.getString();
						parser.next();
						switch (name) {
						case "year":
							year = parser.getInt();
							break;
						case "month":
							month = parser.getInt();
							break;
						case "day":
							day = parser.getInt();
							break;
						}
						dateEvent = parser.next();
					}
					grade.setDate(new Date(year, month, day));
					break;
				case "grade":
					grade.setGrade(parser.getString());
					break;
				case "score":
					grade.setScore(parser.getInt());
					break;
				}
			}
			if (event == JsonParser.Event.END_OBJECT) {
				break;
			}
		}
		return grade;
	}
}
