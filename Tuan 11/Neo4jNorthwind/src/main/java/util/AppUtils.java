package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import entity.Address;
import entity.Contact;
import entity.Customer;
import entity.Order;

public class AppUtils<T> {


//	private static final Gson GSON = new Gson();
	private static final Gson GSON = new GsonBuilder()
		    .create();

	public static Driver getDriver() {
		return GraphDatabase.driver("neo4j://localhost:7687", 
				AuthTokens.basic("neo4j", "12345678"));
	}

	public static <T> T nodeToPOJO(Node node, Class<T> clazz) { //Generic method
		
		Map<String, Object> properties = node.asMap(); // HashMap<String, Object>
		System.out.println(properties);
		
		String json = GSON.toJson(properties);
//		System.out.println(json);
		
		T obj = GSON.fromJson(json, clazz);
		
		return obj;
	}

	public static <T> Map<String, Object> getProperties(T t) {
		
		String json = GSON.toJson(t);
//		System.out.println(json);
		
//		Map<String, Object> map = GSON.fromJson(json, Map.class);
		Map<String, Object> map = GSON.fromJson(json,  new TypeToken<Map<String, Object>>(){});
//		System.out.println(map);
		
		return map;
	}

	public static Customer nodeToCustomer(Node node) {
		Map<String, Object> properties = node.asMap();
		
		String json = GSON.toJson(properties);
		System.out.println(json);
		Address address = GSON.fromJson(json, Address.class);
//		System.out.println(address);
		
		Contact contact = GSON.fromJson(json, Contact.class);
		
		Customer customer = GSON.fromJson(json, Customer.class);
		
		customer.setAddress(address);
		customer.setContact(contact);
//		System.out.println(customer);
		
		return customer;
	}

	public static Order nodeToOrder(Node node) {
		
//		System.out.println(node.asMap());

		String orderID = node.get("orderID").toString().replace("\"", "");
		String rds =node.get("requiredDate").toString().replace("\"", "") .substring(0, 10);
		String ods = node.get("orderDate").toString().replace("\"", "") .substring(0, 10);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate requiredDate =  LocalDate.parse(rds, formatter);
		LocalDate orderDate = LocalDate.parse(ods, formatter);
		
		return new Order(orderID, orderDate, requiredDate);
	}
	
}
