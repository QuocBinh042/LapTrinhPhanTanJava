package dao;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import entity.Product;
import util.AppUtils;

public class ProductDao {
	private Driver driver;
	
	public ProductDao() {
		driver = AppUtils.initDriver();
	}
	
	public Product findProduct(String id) {
		String query = "MATCH (n:Product) WHERE n.productID = $id return n";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try(Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, map);
				if(!result.hasNext()) 
					return null;
				
				return AppUtils.convert(result.next().get("n").asNode(), Product.class);
			});
		}
	}
	
	public void addProduct(Product product) {
		String query = "CREATE (n:Product {productID : $productID,"
				+ " productName: $productName,"
				+ " unitPrice : $unitPrice,"
				+ " unitOnOrder : $unitOnOrder}) return n";
		Map<String, Object> map = AppUtils.convertProperties(product);
		
		try(Session session = driver.session()) {
			session.executeWrite(tx -> {
				return tx.run(query, map).consume();
			});
		}
	}
	
	public Map<String, Integer> getTotalProduct() {
		String query = "MATCH (n:Product) where n.unitOnOrder is null return  n.productName as name, n.unitsOnOrder as total order by total";
		try(Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query);
				return result.stream()
							 .collect(Collectors
									 .toMap(record -> record.get("name").asString(),
											 record -> record.get("total").asInt()
											 ));
			});	
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new ProductDao().findProduct("10000000"));
		Map<String, Integer> map = new ProductDao().getTotalProduct();
		System.out.println(map);
	}
}
