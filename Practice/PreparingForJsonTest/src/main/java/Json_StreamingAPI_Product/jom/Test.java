package Json_StreamingAPI_Product.jom;

import java.util.List;

import Json_ObjectModelAPI_Product.entity.Product;

public class Test {
	public static void main(String[] args) {
		List<Product> p = JsonHandler.fromJson("data/products.json");
		System.out.println(p);
		System.out.println("\n Tim theo sku: " + JsonHandler.findBySku(p, 43900));
		System.out.println("\n Tim theo price: " + JsonHandler.findByPrice(p, 3.4,10));
	}
}
