package dhkhmt17ctt.jom;

import dhkhmt17ctt.entity.Person;

public class JSonDemo1 {
	public static void main(String[] args) {
		Person p = JsonHandler.fromJson("data/person.json");
	
		System.out.println(p);
	}
}
