package dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.types.Node;

import entity.Course;
import util.AppUtils;

public class CourseDAO {
	private Driver driver;
	private SessionConfig sessionConfig;

	public CourseDAO(Driver driver) {
		this.driver = driver;
		this.sessionConfig = SessionConfig.builder().withDatabase("coursedb").build();
	}

	public void close() {
		driver.close();
	}

	public void deleteCourse(String courseId) {
		String query = "MATCH (c:Course {courseID: $id}) DETACH DELETE c";
		Map<String, Object> pars = Map.of("id", courseId);
		try (Session session = driver.session(sessionConfig)) {
			session.executeWrite(tx -> tx.run(query, pars).consume());
		}
	}

	public void addCourseToDept(String courseId, String deptId) {
		String query = "MATCH (c: Course {courseID: $courseId}) " + "MATCH (d: Department {deptID: $deptId}) "
				+ "MERGE (c)-[:BELONGS_TO]->(d)";
		Map<String, Object> pars = Map.of("courseId", courseId, "deptId", deptId);
		try (Session session = driver.session(sessionConfig)) {
			session.executeWrite(tx -> tx.run(query, pars).consume());
		}
	}

	public boolean addCourse(Course course) {
		String query = "CREATE (c:Course {courseID: $id, name: $name, hours:$hours})";
		Map<String, Object> parameters = Map.of("id", course.getCourseId(), "name", course.getName(), "hours",
				course.getHours());
		try (Session session = driver.session(sessionConfig)) {
			Transaction tx = session.beginTransaction();
			try {
				tx.run(query, parameters).consume();
				tx.commit();
				return true;
			} catch (Exception e) {
				tx.rollback();
			}
		}
		return false;
	}

	public List<Course> listOfCourses(String deptId) {
		deptId = deptId.toUpperCase();
		String query = "MATCH (c: Course)-[:BELONGS_TO]->(d: Department) WHERE toUpper(d.deptID) = $id RETURN c";
		Map<String, Object> pars = Map.of("id", deptId);
		try (Session session = driver.session(sessionConfig)) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				return result.stream().map(record -> {
					Node node = record.get("c").asNode();
					return AppUtils.convert(node, Course.class);
				}).toList();
			});
		}
	}

	public Course findCourseById(String courseId) {
		String query = "MATCH (c:Course) WHERE c.courseID = $id  RETURN c";
		Map<String, Object> pars = Map.of("id", courseId);
		try (Session session = driver.session(sessionConfig)) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				if (!result.hasNext())
					return null;
				Record record = result.stream().findFirst().get();
				Node node = record.get("c").asNode();
				return AppUtils.convert(node, Course.class);
			});
		}
	}

	public String addCourse2(Course course) {
		String query = "CREATE (c:Course {courseID: $id, name: $name, hours:$hours}) " + "RETURN c.courseID";
		Map<String, Object> parameters = Map.of("id", course.getCourseId(), "name", course.getName(), "hours",
				course.getHours());
		try (Session session = driver.session(sessionConfig)) {
			return session.executeWrite(tx -> {
				Result result = tx.run(query, parameters);
				Optional<Record> first = result.stream().findFirst();
				return first.isPresent() ? first.get().get("c.courseID").asString() : null;
			});
		}
	}

	public boolean updateCourseById(String courseId, Course updatedCourse) {
	    String query = "MATCH (c:Course {courseID: $id}) SET c.name = $name, c.hours = $hours";
	    Map<String, Object> parameters = Map.of(
	            "id", courseId,
	            "name", updatedCourse.getName(),
	            "hours", updatedCourse.getHours()
	    );
	    
	    try (Session session = driver.session(sessionConfig)) {
	        Transaction tx = session.beginTransaction();
	        tx.run(query, parameters).consume();
	        tx.commit();
	        return true;
	    } catch (Exception e) {
	        // Handle exception
	        return false;
	    }
	}



}
