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
		String uri = "neo4j://localhost:7687";
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic("neo4j", "12345678"));

		Session session = driver.session(SessionConfig.forDatabase("coursedb"));
		Transaction tx = session.beginTransaction();

		String courseId = "MA301";
		String query = "MATCH (c: Course) " + "WHERE c.courseID = $id " + "RETURN c;";
		Result result = tx.run(query, Values.parameters("id", courseId));
		Record record = result.single();
		Node node = record.get("c").asNode();

		Course course = new Course();
		course.setCourseId(node.get("courseID").asString());
		course.setName(node.get("name").asString());
		course.setHours(node.get("hours").asInt());

		System.out.println(course);

	}
}
