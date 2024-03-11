package jom;

import java.util.List;
import jom.JsonHandler;
import entity.Product;

public class T {
	public static void main(String[] args) {
		List<Product> p = jom.JsonHandler.fromJson("data/products.json");
		System.out.println(p);
		System.out.println("\n Tim theo sku: " + jom.JsonHandler.findBySku(43900));
		System.out.println("\n Tim theo price: " + jom.JsonHandler.findByPrice(3.4,10));
	}
}
