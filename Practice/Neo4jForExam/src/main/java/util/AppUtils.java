package util;

import java.util.Map;

import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;
import com.google.gson.reflect.TypeToken;

import entity.Address;
import entity.Contact;
import entity.Customer;

import com.google.gson.Gson;


public class AppUtils {
	
	private static final Gson gson = new Gson();
	
	public static Driver initDriver() {
		return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
	}
	
	public static Customer nodeToCustomer(Node node) {
		Map<String, Object> properties = node.asMap();		
		String json = gson.toJson(properties);
		System.out.println(json);
		Address address = gson.fromJson(json, Address.class);
		Contact contact = gson.fromJson(json, Contact.class);		
		Customer customer = gson.fromJson(json, Customer.class);		
		customer.setAddress(address);
		customer.setContact(contact);		
		return customer;
	}
	
	public static <T> T convert(Node node, Class<T> clazz) {
		Map<String, Object> map = node.asMap();		
		String json = gson.toJson(map);		
		return gson.fromJson(json, clazz);
	}
	
	public static<T> Map<String, Object> convertProperties(T t) {		
		String json = gson.toJson(t);
		Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){});		
		return map;
	}
}
