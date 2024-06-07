package Json_ObjectModelAPI_Product.jom;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Json_ObjectModelAPI_Product.entity.Category;
import Json_ObjectModelAPI_Product.entity.Product;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JsonHandler {
	private static List<Product> p = JsonHandler.fromJson("data/products.json");

	public static Product findBySku(int sku) {
		for (Product product : p) {
			if (product.getSku() == sku) {
				return product;
			}
		}
		return null;
	}

	public static List<Product> findByPrice(double from, double to) {
		List<Product> result = new ArrayList<>();
		for (Product product : p) {
			double price = product.getPrice();
			if (price >= from && price <= to) {
				result.add(product);
			}
		}
		return result;
	}

	public static List<Product> fromJson(String fileName) {
		List<Product> products = new ArrayList<>();
		try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
			JsonArray jsonArray = reader.readArray();
			for (JsonValue jsonValue : jsonArray) {
				if (jsonValue instanceof JsonObject) {
					JsonObject jo = jsonValue.asJsonObject();
					Product p = new Product();
					p.setSku(jo.getInt("sku"));
					p.setName(jo.getString("name"));
					p.setType(jo.getString("type"));
					p.setPrice(jo.getJsonNumber("price").doubleValue());
					p.setUpc(jo.getString("upc"));
					List<Category> categories = new ArrayList<>();
					JsonArray categoryArray = jo.getJsonArray("category");
					for (JsonValue jv : categoryArray) {
						if (jv instanceof JsonObject) {
							JsonObject joC = (JsonObject) jv;
							categories.add(new Category(joC.getString("id"), joC.getString("name")));
						}
					}
					p.setCategory(categories);
					p.setShipping(jo.getJsonNumber("shipping").doubleValue());
					p.setDescription(jo.getString("description"));
					p.setUrl(jo.getString("url"));
					products.add(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;

	}

	public static void toJsonFile(List<Product> products, String fileName) {
		try (JsonWriter writer = Json.createWriter(new FileWriter(fileName))) {
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (Product product : products) {
				JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("sku", product.getSku())
						.add("name", product.getName()).add("type", product.getType()).add("price", product.getPrice())
						.add("upc", product.getUpc()).add("shipping", product.getShipping())
						.add("description", product.getDescription()).add("url", product.getUrl());

				JsonArrayBuilder categoryBuilder = Json.createArrayBuilder();
				for (Category category : product.getCategory()) {
					JsonObject categoryObject = Json.createObjectBuilder().add("id", category.getId())
							.add("name", category.getName()).build();
					categoryBuilder.add(categoryObject);
				}
				objectBuilder.add("category", categoryBuilder);
				JsonObject productObject = objectBuilder.build();
				arrayBuilder.add(productObject);
				arrayBuilder.add("\n");
			}
			JsonArray jsonArray = arrayBuilder.build();
			writer.writeArray(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
