package jom;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import entity.Address;
import entity.Patient;
import entity.Datee;
import entity.Testt;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
 
public class JsonHandle {
	public static List<Patient> getListOfPatients(String fileName) {
		List<Patient> patient = null;
		try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
			patient = new ArrayList<Patient>();
			JsonArray ja = reader.readArray();
			for (JsonValue jv : ja) {
				if (jv instanceof JsonObject) {
					JsonObject joBN = jv.asJsonObject();
					Patient bn = new Patient();
					bn.setId(joBN.getString("_id"));
					bn.setFirstName(joBN.getString("first_name"));
					bn.setLastName(joBN.getString("last_name"));
					bn.setBloodType(joBN.getString("blood_type"));
					bn.setGender(joBN.getString("gender"));
					JsonObject joAdr = joBN.getJsonObject("address");
					Address adr = new Address();
					adr.setCity(joAdr.getString("city"));
					adr.setDistrict(joAdr.getString("district"));
					adr.setStreet(joAdr.getString("street"));
					adr.setWard(joAdr.getString("ward"));
					bn.setAddress(adr);
					JsonArray jaT = joBN.getJsonArray("telephones");
					List<String> t = new ArrayList<String>();
					for (JsonValue jv2 : jaT) {
						t.add(jv2.toString());
					}
					bn.setTelephones(t);
					List<Testt> tests = new ArrayList<>();
					JsonArray jaTest = joBN.getJsonArray("tests");
					for (JsonValue jv2 : jaTest) {
					    if (jv2 instanceof JsonObject) {
					        JsonObject joTest = (JsonObject) jv2;
					        Datee date = new Datee();					        
					        JsonObject joD = joTest.getJsonObject("date");
					        date.setDay(joD.getInt("day"));
					        date.setMonth(joD.getInt("month"));
					        date.setYear(joD.getInt("year"));					        
					        Testt test = new Testt(date,
					                               joTest.getString("result"),
					                               joTest.getInt("test_id"),
					                               joTest.getString("test_type"),
					                               joTest.getJsonNumber("cost").doubleValue());
					        
					        tests.add(test);
					    }
					}
					bn.setTest(tests);
					bn.setYear_of_birth(joBN.getInt("year_of_birth"));
					patient.add(bn);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return patient;
	}
}
