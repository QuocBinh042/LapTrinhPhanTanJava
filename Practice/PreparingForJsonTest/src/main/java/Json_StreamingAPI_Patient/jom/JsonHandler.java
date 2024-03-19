package Json_StreamingAPI_Patient.jom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Json_StreamingAPI_Patient.entity.Patient;
import Json_StreamingAPI_Patient.entity.Testt;
import Json_StreamingAPI_Patient.entity.Address;
import Json_StreamingAPI_Patient.entity.Datee;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JsonHandler {
	public static List<Patient> getListOfPatients(String fileName) {
		List<Patient> patientsList = new ArrayList<>();
		try (JsonParser parser = Json.createParser(new FileInputStream(fileName))) {
			String key = null;
			Patient p = new Patient();
			while (parser.hasNext()) {
				JsonParser.Event event = parser.next();
				switch (event) {
				case KEY_NAME:
					key = parser.getString();
					break;
				case START_ARRAY:
					if (key != null) {
						switch (key) {
						case "telephones":
							List<String> t = new ArrayList<>();
							while (parser.hasNext() && parser.next() != JsonParser.Event.END_ARRAY) {
								t.add(parser.getString());
							}
							p.setTelephones(t);
							break;
						case "tests":
							List<Testt> tests = new ArrayList<>();
							while (parser.hasNext() && parser.next() != JsonParser.Event.END_ARRAY) {
								Testt test = new Testt();
								while (parser.next() != JsonParser.Event.END_OBJECT) {
									String key1 = parser.getString();
									parser.next();
									switch (key1) {
									case "date":
										Datee d = new Datee();
										while (parser.next() != JsonParser.Event.END_OBJECT) {
											String dateKey = parser.getString();
											parser.next();
											switch (dateKey) {
											case "day":
												d.setDay(parser.getInt());
												break;
											case "month":
												d.setMonth(parser.getInt());
												break;
											case "year":
												d.setYear(parser.getInt());
												break;
											}
										}
										test.setDate(d);
										break;
									case "result":
										test.setResult(parser.getString());
										break;
									case "test_id":
										test.setTest_id(parser.getInt());
										break;
									case "test_type":
										test.setTest_type(parser.getString());
										break;
									case "cost":
										test.setCost(Double.parseDouble(parser.getString()));
										break;
									}
									tests.add(test);
								}
								break;
							}
							p.setTest(tests);
						}
					}
					break;
				case START_OBJECT:
					if (key != null) {
						if (key.equals("address")) {
							Address adr = new Address();
							while (parser.hasNext() && parser.next() != JsonParser.Event.END_OBJECT) {
								key = parser.getString();
								parser.next();
								switch (key) {
								case "city":
									adr.setCity(parser.getString());
									break;
								case "district":
									adr.setDistrict(parser.getString());
									break;
								case "street":
									adr.setStreet(parser.getString());
									break;
								case "ward":
									adr.setWard(parser.getString());
									break;
								default:
									break;
								}
							}
							p.setAddress(adr);
						}
					}
					break;
				case END_OBJECT:
					patientsList.add(p);
					break;
				case VALUE_STRING:
					switch (key) {
					case "_id":
						p.setId(parser.getString());
						break;
					case "first_name":
						p.setFirstName(parser.getString());
						break;
					case "last_name":
						p.setLastName(parser.getString());
						break;
					case "blood_type":
						p.setBloodType(parser.getString());
						break;
					case "gender":
						p.setGender(parser.getString());
						break;
					}
					break;
				case VALUE_NUMBER:
					if (key.equals("year_of_birth")) {
						p.setYear_of_birth(parser.getInt());
					}
					break;
				default:
					break;
				}
			}
		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return patientsList;
	}
}
