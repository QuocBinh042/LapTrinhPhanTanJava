package dao;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import entity.Customer;
import util.AppUtils;

public class CustomerDao {
	
	private Driver driver;
	public CustomerDao() {
		driver = AppUtils.initDriver();
	}
	
	public Customer findOne(String id) {
		String query = "MATCH (n:Customer) WHERE n.customerID = $id return n";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try(Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, map);
				if(!result.hasNext())
					return null;
				
				return AppUtils.convert(result.next().get("n").asNode(), Customer.class);
			});
		}
	}
	
	public void addOne(Customer customer) {
		String query = "CREATE (n:Customer {customerID : $customerID, companyName : $companyName})";
		Map<String, Object> map = AppUtils.convertProperties(customer);
		try(Session session = driver.session()) {
			session.executeWrite(tx -> {
				return tx.run(query, map).consume();
			});
		}
	}
	
	public Map<Customer, Long> listCustomerWithNumberOfOrders() {
		String query = "match (c:Customer) -[p:PURCHASED]->(o:Order) return c, count(0) as total";
		try(Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query);
				return result.stream()
							 .collect(Collectors.toMap(
									 			record -> AppUtils.convert(record.get("c").asNode(), Customer.class),
									 			record -> record.get("total").asLong())
									 );
			});
		}
	}
	
	public Map<Customer, Long> listCustomersWithTheNumberOfOrders() {
        String query = "MATCH (c:Customer)-[r:PURCHASED]->(o:Order) RETURN c, count(o) as count";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query);
                return result.stream()
                        .collect(Collectors.toMap(
                                record -> AppUtils.nodeToCustomer(record.get("c").asNode()),
                                record -> record.get("count").asLong()
                                ));
            });
        }
	}
	
	
	
	public static void main(String[] args) {	
		Map<Customer, Long> map = new CustomerDao().listCustomerWithNumberOfOrders();
		System.out.println(map);
	}
}
