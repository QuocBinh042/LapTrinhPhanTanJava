package test;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

import entity.Course;

public class Main {
	public static void main(String[] args) {
		
		String ps = "12345678";
		String us = "neo4j";
		String uri = "neo4j://localhost:7687";
		// Create a connection to the database
		Driver driver = GraphDatabase.driver(uri , AuthTokens.basic(us , ps ));
		
		Session session = driver.session(SessionConfig.forDatabase("coursedb"));
		
		Transaction tx = session.beginTransaction();
		
		// Find one course by course id
		
		String courseId = "MA301";
		String query = "MATCH (c: Course) "
				+ "WHERE c.courseID = $id "
				+ "RETURN c;";
		
		
		Result result = tx.run(query, Values.parameters("id", courseId));
		
//		session.executeRead(tx -> {
//			Result result = tx.run(query, Values.parameters("id", courseId));
		
			Record record = result.single();
			
			Node node = record.get("c").asNode();
					
			Course course = new Course();
			course.setCourseId(node.get("courseID").asString());
			course.setName(node.get("name").asString());
			course.setHours(node.get("hours").asInt());
			
			System.out.println(course);
			
//			return course;
//		});
	}
}
