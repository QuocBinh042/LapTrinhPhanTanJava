package Json_StreamingAPI_Restaurant.jom;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import Json_StreamingAPI_Restaurant.entity.Address;
import Json_StreamingAPI_Restaurant.entity.Date;
import Json_StreamingAPI_Restaurant.entity.Grade;
import Json_StreamingAPI_Restaurant.entity.Restaurant;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

public class JsonHandler {
    public static Restaurant fromJson(String fileName) {
        Restaurant r = new Restaurant();
        try (JsonParser parser = Json.createParser(new FileInputStream(fileName))) {
            String currentKey = null;
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                Address adr = new Address();
                switch (event) {
                    case KEY_NAME:
                        currentKey = parser.getString();
                        break;
                    case START_OBJECT:
                        if ("address".equals(currentKey)) {                           
                            while (parser.hasNext()) {
                                JsonParser.Event addressEvent = parser.next();
                                if (addressEvent == JsonParser.Event.KEY_NAME) {
                                    String addressKey = parser.getString();
                                    parser.next();
                                    switch (addressKey) {
                                        case "building":
                                            adr.setBuilding(parser.getString());
                                            break;
                                        case "coord":
                                            adr.setCoord(parseCoord(parser));
                                            break;
                                        case "street":
                                            adr.setStreet(parser.getString());
                                            break;
                                        case "zipcode":
                                            adr.setZipcode(parser.getString());
                                            break;
                                    }
                                }
                                if (addressEvent == JsonParser.Event.END_OBJECT) {
                                    break;
                                }
                            }
                            r.setAddress(adr);
                        }
                        break;

                    case END_OBJECT:
                        if ("address".equals(currentKey)) {
                            r.setAddress(adr);
                        }
                        break;
                    case VALUE_STRING:
                        if (currentKey != null) {
                            switch (currentKey) {
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
                        }
                        break;
                    case VALUE_TRUE:
                        if ("is_closed".equals(currentKey)) {
                            r.setIs_closed(true);
                        }
                        break;
                    case VALUE_FALSE:
                        if ("is_closed".equals(currentKey)) {
                            r.setIs_closed(false);
                        }
                        break;
                    case START_ARRAY:
                        if ("grades".equals(currentKey)) {
                            r.setGrade(parseGrades(parser));
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    private static List<Double> parseCoord(JsonParser parser) {
        List<Double> coord = new ArrayList<>();
        parser.next(); // Move to START_ARRAY
        while (parser.hasNext() && parser.next() != JsonParser.Event.END_ARRAY) {
        	coord.add(parser.getBigDecimal().doubleValue());
        }
        return coord;
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

    public static void toJson(String fileName, Restaurant restaurant) {
        // Implement JSON serialization from Restaurant object to file
    }
}
