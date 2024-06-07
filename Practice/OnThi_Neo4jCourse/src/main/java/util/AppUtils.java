package util;

import java.net.URI;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import com.google.gson.Gson;

public class AppUtils {
	private static final Gson gson = new Gson();

	public static Driver initDriver() {
		URI uri = URI.create("neo4j://localhost:7687");
		return GraphDatabase.driver(uri, AuthTokens.basic("neo4j", "12345678"));
	}

	public static <T> T convert(Node node, Class<T> clazz) {
		String json = gson.toJson(node.asMap());
		return gson.fromJson(json, clazz);
	}
}
