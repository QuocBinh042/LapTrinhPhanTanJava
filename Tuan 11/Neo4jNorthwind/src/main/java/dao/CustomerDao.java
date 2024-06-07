/*
 * @ (#) CustomerDao.java       1.0     Mar 17, 2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import entity.Customer;
import util.AppUtils;

/*
 * @description: 
 * @author: Khanh Nguyen
 * @date:   Mar 17, 2024
 * @version:    1.0
 */
public class CustomerDao {
private Driver driver;
	
	public CustomerDao() {
		driver = AppUtils.getDriver();
	}
	
//	MATCH (c:Customer)-[r:PURCHASED]->(o:Order) RETURN c, count(o) as count LIMIT 25
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
	
//	MATCH (c:Customer)-[r:PURCHASED]->(o:Order) RETURN c, count(o) as count LIMIT 25
	/**
	 * List customers with the number of orders, ordered by the customer's id
	 * @return
	 */
	public Map<Customer, Long> listCustomersWithTheNumberOfOrders2() {
        String query = "MATCH (c:Customer)-[r:PURCHASED]->(o:Order) RETURN c, count(o) as count ORDER BY count";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query);
                return result.stream()
                        .collect(Collectors.toMap(
                                record -> AppUtils.nodeToCustomer(record.get("c").asNode()),
                                record -> record.get("count").asLong(),
                                (v1, v2) -> v1,
                                LinkedHashMap::new
                                ));
            });
        }
	}
	
	/**
	 * List customers with the number of orders, ordered by the customer's id
	 * @return
	 */
	public Map<Customer, Long> listCustomersWithTheNumberOfOrders4() {
		String query = "MATCH (c:Customer)-[r:PURCHASED]->(o:Order) RETURN c, count(o) as count ";
		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query);
				return result.stream()
						.collect(Collectors.toMap(
								record -> AppUtils.nodeToCustomer(record.get("c").asNode()),
								record -> record.get("count").asLong(),
								(v1, v2) -> v1,
								() -> new TreeMap<>(Comparator.comparing(Customer::getId))
								));
			});
		}
	}
	
	/**
	 * Number of customers by city, sorted by number of customers descending
	 * Thống kê số khách hàng theo từng thành phố (sắp xếp theo số khách hàng giảm dần).
	 * @return Map<String, Long>
	 */
	public Map<String, Long> numberOfCustomersByCity() {
		String query = "MATCH (c:Customer) RETURN c.city as city, count(c) as count ORDER BY count DESC";
		
		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query);
				return result
						.stream()
						.map(record -> record.asMap())
						.collect(Collectors.toMap(p -> (String) p.get("city"), 
								p -> (Long) p.get("count"),
								(a, b) -> a,
                                LinkedHashMap::new));
			});
		}
	}
	
	/**
	 * Number of customers by city, sorted by city name ascending
	 * Thống kê số khách hàng theo từng thành phố (sắp xếp theo city).
	 * @return Map<String, Long>
	 */
	public Map<String, Long> numberOfCustomersByCity2() {
		String query = "MATCH (c:Customer) RETURN c.city as city, count(c) as count";
		
		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query);
				return result
						.stream()
						.map(record -> record.asMap())
						.collect(Collectors.toMap(p -> (String) p.get("city"), 
								p -> (Long) p.get("count"),
								(a, b) -> a,
								TreeMap::new));
			});
		}
	}
	
	/**
	 * Close the driver
	 */
	public void close() {
		driver.close();
	}

	public static void main(String[] args) {
		CustomerDao dao = new CustomerDao();
		
		
		dao.listCustomersWithTheNumberOfOrders4()
		.entrySet()
		.stream()
		.forEach(entry -> {
			System.out.println("Customer: " + entry.getKey());
			System.out.println("Number of orders: " + entry.getValue());
		});
		
		
//		dao.listCustomersWithTheNumberOfOrders2().forEach((k, v) -> System.out.println(k.getAddress() + " : " + v));
	}
}

