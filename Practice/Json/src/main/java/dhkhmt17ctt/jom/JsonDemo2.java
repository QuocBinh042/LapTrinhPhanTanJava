package dhkhmt17ctt.jom;

import java.util.Arrays;
import java.util.List;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;

public class JsonDemo2 {
	public static void main(String[] args) {
		
		List<Person> list = List.of(
					new Person("Ti", "Tran", 20),
					new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
					new Person("Tung", "Le", 27)
				);
		
		JsonHandler.toJsonFile(list, "data/people.json");
		System.out.println("Done");
	}
}
