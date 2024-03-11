package dhkhmt17ctt.jom;

import java.io.FileNotFoundException;
import java.io.FileReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class JsonDemo {

	public static void main(String[] args) {
		String json = "{'name':'Lan'}";
		
//		Doc tu json file
		try (JsonReader reader = Json.createReader(new FileReader("data/person.json"));){
			
			JsonObject jo = reader.readObject();
			
			jo.entrySet()
			.forEach(entry -> {
				System.out.println("Key: " + entry.getKey());
				System.out.println("Value: " + entry.getValue());
				System.out.println("===");
			});
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

//Exception

//Checked Exception
//Unchecked Exception - RuntimeException

