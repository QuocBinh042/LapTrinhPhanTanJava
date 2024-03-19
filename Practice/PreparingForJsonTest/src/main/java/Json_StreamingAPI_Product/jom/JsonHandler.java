package Json_StreamingAPI_Product.jom;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Json_ObjectModelAPI_Product.entity.Category;
import Json_ObjectModelAPI_Product.entity.Product;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonCollectors;
import jakarta.json.stream.JsonParser;

public class JsonHandler {
	public static Product findBySku(List<Product> products, int sku) {
		return products.stream().filter(product -> product.getSku() == sku).findFirst().orElse(null);
	}

	public static List<Product> findByPrice(List<Product> products, double from, double to) {
		return products.stream().filter(product -> product.getPrice() >= from && product.getPrice() <= to)
				.collect(Collectors.toList());
	}

	public static List<Product> fromJson(String fileName) {
		List<Product> products = new ArrayList<>();
		try (JsonParser parser = Json.createParser(new FileInputStream(fileName))) {
			Product currentProduct = null;
			String currentKey = null;
			while (parser.hasNext()) {
				JsonParser.Event event = parser.next();
				switch (event) {
				case START_OBJECT:
					currentProduct = new Product();
					break;
				case END_OBJECT:
					if (currentProduct != null) {
						products.add(currentProduct);
					}
					currentProduct = null;
					break;
				case KEY_NAME:
					currentKey = parser.getString();
					break;
				case VALUE_STRING:
					if (currentProduct != null && currentKey != null) {
						setValue(currentProduct, currentKey, parser.getString());
					}
					break;
				case VALUE_NUMBER:
					if (currentProduct != null && currentKey != null) {
						if (parser.isIntegralNumber()) {
							setValue(currentProduct, currentKey, parser.getInt());
						} else {
							setValue(currentProduct, currentKey, parser.getBigDecimal().doubleValue());
						}
					}
					break;
				case START_ARRAY:
					if ("category".equals(currentKey) && currentProduct != null) {
						List<Category> categories = readCategories(parser);
						currentProduct.setCategory(categories);
					}
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

	private static List<Category> readCategories(JsonParser parser) {
		List<Category> categories = new ArrayList<>();
		Category category = null;
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			switch (event) {
			case START_OBJECT:
				category = new Category(null, null);
				break;
			case KEY_NAME:
				String key = parser.getString();
				event = parser.next();
				if ("id".equals(key)) {
					category.setId(parser.getString());
				} else if ("name".equals(key)) {
					category.setName(parser.getString());
				}
				break;
			case END_OBJECT:
				categories.add(category);
				break;
			case END_ARRAY:
				return categories;
			default:
				break;
			}
		}
		return categories;
	}

	private static void setValue(Product product, String key, Object value) {
		switch (key) {
		case "sku":
			product.setSku((int) value);
			break;
		case "name":
			product.setName((String) value);
			break;
		case "type":
			product.setType((String) value);
			break;
		case "price":
			product.setPrice((double) value);
			break;
		case "upc":
			product.setUpc((String) value);
			break;
		case "shipping":
			product.setShipping((double) value);
			break;
		case "description":
			product.setDescription((String) value);
			break;
		case "url":
			product.setUrl((String) value);
			break;
		}
	}

	public static void toJsonFile(List<Product> products, String fileName) {
		try (FileWriter writer = new FileWriter(fileName); JsonWriter jsonWriter = Json.createWriter(writer)) {
			JsonArray jsonArray = products.stream().map(JsonHandler::mapProductToJson)
					.collect(JsonCollectors.toJsonArray());
			jsonWriter.writeArray(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static JsonObject mapProductToJson(Product product) {
		return Json.createObjectBuilder()
				.add("sku", product.getSku())
				.add("name", product.getName())
				.add("type", product.getType())
				.add("price", product.getPrice())
				.add("upc", product.getUpc())
				.add("shipping", product.getShipping())
				.add("description", product.getDescription())
				.add("url", product.getUrl())
				.add("category", product.getCategory().stream()
								.map(category -> Json.createObjectBuilder()
										.add("id", category.getId())
										.add("name", category.getName()).build())
								.collect(JsonCollectors.toJsonArray()))
				.build();
	}

}
