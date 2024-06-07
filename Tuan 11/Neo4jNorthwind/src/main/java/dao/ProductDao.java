package dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import entity.Product;
import util.AppUtils;

public class ProductDao {
	private Driver driver;

	public ProductDao() {
		driver = AppUtils.getDriver();
	}

	/**
	 * List of products by category name Tìm các sản phẩm được cung cấp bởi một nhà
	 * cung cấp nào đó khi biết tên nhà cung cấp
	 * 
	 * @param categoryName - Beverages
	 * @return List of products 
	 */
	public List<Product> listOfProductsByCategory(String categoryName) {
		String query = "MATCH  (p:Product)-[r:PART_OF]->(c:Category) where c.categoryName= $name RETURN p";
		Map<String, Object> pars = Map.of("name", categoryName);

		try (Session session = driver.session()) {

			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				return result.stream().map(Record -> Record.get("p").asNode())
						.map(node -> AppUtils.nodeToPOJO(node, Product.class)).toList();
			});
		}

	}

	/**
	 * Find one product by id
	 * 
	 * @param id
	 * @return Product
	 */
	public Product findOne(String id) {

		String query = "MATCH (n:Product) WHERE n.productID= $id RETURN n";
		Map<String, Object> pars = Map.of("id", id);

		try (Session session = driver.session()) {

			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);

				if (!result.hasNext())
					return null;

				Record record = result.next();
				Node node = record.get("n").asNode();
				
				return AppUtils.nodeToPOJO(node, Product.class);
			});
		}
	}

	/**
	 * List of products with max price Tìm danh sách sản phẩm có giá cao nhất
	 * 
	 * @return List of products
	 * 
	 */

	public List<Product> listOfProductsWithMaxPrice() {

		String query = "MATCH (p:Product) "
				+ "WITH max(p.unitPrice) as maxPrice "
				+ "MATCH (p:Product) "
				+ "WHERE p.unitPrice = maxPrice RETURN p";

		try (Session session = driver.session()) {

			return session.executeRead(tx -> {
				Result result = tx.run(query);
				return result.stream().map(Record -> Record.get("p").asNode())
						.map(node -> AppUtils.nodeToPOJO(node, Product.class)).toList();
			});
		}
	}

//	MATCH (o:Order)-[r:ORDERS]->(p:Product) return p.productName, sum(r.quantity) as totalQuantity
	/**
	 * List of products with the total quantity of orders
	 * 
	 * @return Map<String, Long>
	 */
	public Map<String, Integer> listOfProductsWithTheTotalQuantityOfOrders() {
		String query = "MATCH (o:Order)-[r:ORDERS]->(p:Product) return p.productName as productName, sum(r.quantity) as totalQuantity";

		try (Session session = driver.session()) {
			return session.executeRead(tx -> {

				Result result = tx.run(query);
				return result.stream().collect(Collectors.toMap(record -> record.get("productName").asString(),
						record -> record.get("totalQuantity").asInt()));
			});
		}
	}
	
//	Create text index for Product.productName
//  CREATE FULLTEXT INDEX text_index_pName for (p:Product) on EACH [p.productName]
	/**
	 * Search products by name
	 * 
	 * @param name
	 * @return List of products
	 */
	public List<Product> searchProductByName(String name) {
		
		String query = "CALL db.index.fulltext.queryNodes('text_index_pName', $name) YIELD node RETURN node";
		Map<String, Object> pars = Map.of("name", name);

		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				return result
						.stream()
						.map(Record -> Record.get("node").asNode())
						.map(node -> AppUtils.nodeToPOJO(node, Product.class))
						.toList();
			});
		}
	}

	/**
	 * Close the driver
	 */

	public void close() {
		driver.close(); // Closing the driver	
	}

	public static void main(String[] args) {
		ProductDao dao = new ProductDao();

//		List<Product> products = dao.listOfProductsByCategory("Beverages");
//		List<Product> products = dao.listOfProductsWithMaxPrice()	;
//		System.out.println(products.size());
//		products.forEach(System.out::println);

		dao
		.listOfProductsWithTheTotalQuantityOfOrders()
		.entrySet()
		.stream()
		.forEach(System.out::println);
		
//		dao
//		.listOfProductsWithTheTotalQuantityOfOrders()
//		.forEach((k, v) -> System.out.println(k + " : " + v));
		
//		dao.searchProductByName("Hot").forEach(System.out::println);

		dao.close();
	}

}
