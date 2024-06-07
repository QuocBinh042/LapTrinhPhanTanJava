/*
 * @ (#) OrderDao.java       1.0     Mar 17, 2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import entity.Order;
import util.AppUtils;

/*
 * @description: 
 * @author: Khanh Nguyen
 * @date:   Mar 17, 2024
 * @version:    1.0
 */
public class OrderDao {
private Driver driver;
	/*
	 * Constructor
	 */
	public OrderDao() {
		driver = AppUtils.getDriver();
	}
//	https://community.neo4j.com/t/timestamps-between-java-and-neo/63160/15
	/**
	 * Find order by orderID
	 * Tìm đơn hàng theo orderID
	 * @param orderID
	 * @return Order
	 */
	public Order findOrderByID(String orderID) {
		String query = "MATCH (o:Order) where o.orderID= $id RETURN o";
		Map<String, Object> pars = Map.of("id", orderID);

		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				if (!result.hasNext())
					return null;
				Node node = result.single().get("o").asNode();
				
				return AppUtils.nodeToOrder(node);
			});
		}
	}

	/**
	 * Get the total amount of an order by orderID
	 * Tính tổng số tiền của một đơn hàng khi biết orderID
	 * MATCH (o:Order)-[r:ORDERS]->(p:Product) where o.orderID="11005"  RETURN sum(toFloat(r.unitPrice)*r.quantity) as totalAmount
	 * @param orderID
	 * @return
	 */
	public double totalAmountOfAnOrder(String orderID) {
		String query = "MATCH (o:Order)-[r:ORDERS]->(p:Product) where o.orderID= $id  RETURN sum(toFloat(r.unitPrice)*r.quantity) as totalAmount";
		Map<String, Object> pars = Map.of("id", orderID);

		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				if (!result.hasNext())
					return 0.0;
				return result.single().get("totalAmount").asDouble();
			});
		}
	}
	
	/**
	 * Get the total amount of an order by orderDate Tính tổng số tiền của một đơn
	 * hàng khi biết orderDate 
	 * MATCH (o:Order)-[r:ORDERS]->(p:Product) where o.orderDate= datetime("1996-07-10T00:00:00Z") RETURN sum(toFloat(r.unitPrice)*r.quantity) as totalAmount
	 * @param orderDate
	 * @return
	 */
	
	public double totalAmountOfAnOrderByOrderDate(String orderDate) {
		String query = "MATCH (o:Order)-[r:ORDERS]->(p:Product) where o.orderDate= datetime($date) RETURN sum(toFloat(r.unitPrice)*r.quantity) as totalAmount";
		Map<String, Object> pars = Map.of("date", orderDate);

		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				if (!result.hasNext())
					return 0.0;
				return result.single().get("totalAmount").asDouble();
			});
		}
	}
	
	/**
	 * Get the total amount of an order by order year
	 * Thống kê tổng tiền hóa đơn theo tháng / năm
	 * Match (o:Order)-[r:ORDERS]->(p:Product) where o.orderDate.year=1996 and o.orderDate.month=7 RETURN sum(toFloat(r.unitPrice)*r.quantity) as totalAmount
	 * @param year
	 * @return
	 */
	public double totalAmountOfAnOrderByYear(int year, int month) {
		String query = "Match (o:Order)-[r:ORDERS]->(p:Product) "
				+ "where o.orderDate.year=$year "
				+ "and o.orderDate.month=$month "
				+ "RETURN sum(toFloat(r.unitPrice)*r.quantity) as totalAmount";
		Map<String, Object> pars = Map.of("year", year, "month", month);

		try (Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				if (!result.hasNext())
					return 0.0;
				return result.single().get("totalAmount").asDouble();
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
		OrderDao dao = new OrderDao();
		Order order = dao.findOrderByID("10248");
		System.out.println(order);
		
		System.out.println(dao.totalAmountOfAnOrder("10248"));
		System.out.println(dao.totalAmountOfAnOrderByOrderDate("1996-08-16"));
		System.out.println(dao.totalAmountOfAnOrderByYear(1996, 7));
		dao.close();
	}
}




