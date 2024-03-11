package dhkhmt17ctt.jom;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;
import dhkhmt17ctt.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class Test {
	public static void toJsonFile(List<Person> people, String fileName) {
		try(JsonWriter writer = Json.createWriter(new FileWriter(fileName))){
			JsonObjectBuilder oBuilder = Json.createObjectBuilder();
			JsonArrayBuilder aBuilder = Json.createArrayBuilder();
			people.forEach(p->{
				Address adr = p.getAddress();
				JsonObject joAdr = null;
				if (adr!=null) {
					joAdr = oBuilder.add("city", adr.getCity()).add("state", adr.getState()).build();
				}
				oBuilder.add("firstName", p.getFirstName()).add("age", p.getAge());
				if (joAdr!=null) {
					oBuilder.add("address", joAdr);
				}
				JsonObject jo = oBuilder.build();
				aBuilder.add(jo);
			});
			JsonArray ja = aBuilder.build();
			writer.write(ja);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Person fromJson(String fileName) {
		Person p = null;
		try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
			JsonObject jo = reader.readObject();
			if (jo != null) {
				p = new Person();
				p.setFirstName(jo.getString("firstName"));
				p.setLastName(jo.getString("lastName"));
				p.setAge(jo.getInt("age"));

				JsonObject joAddress = jo.getJsonObject("address");
				Address adr = new Address(joAddress.getString("streetAddress"), joAddress.getString("city"),
						joAddress.getString("state"), joAddress.getInt("postalCode"));
				
				p.setAddress(adr);
				
				List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
				JsonArray ja = jo.getJsonArray("phoneNumbers");
				for (JsonValue jv: ja) {
					if (jv instanceof JsonObject) {
						JsonObject joP = (JsonObject) jv;
						phones.add(new PhoneNumber(joP.getString("type"), joP.getString("number")));
					}
				}
				p.setPhoneNumbers(phones);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return p;
	}

}
