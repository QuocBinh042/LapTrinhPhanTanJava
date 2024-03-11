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

public class CourseDao {
	private Driver driver;
	private SessionConfig sessionConfig;
	
	public CourseDao(Driver driver) {
		this.driver = driver;
		this.sessionConfig = SessionConfig
				.builder()
				.withDatabase("course")
				.build();
	}
	
	/**
	 * Update the course's information
	 * @param course The course to be updated
	 * @return course if updated successfully, null otherwise
	 */
	
	
	/**
	 * Delete a course by its ID
	 * @param courseId The ID of the course
	 * 
	 */
	public void deleteCourse(String courseId) {
		String query = "MATCH (c:Course {courseID: $id}) DETACH DELETE c";
		Map<String, Object> pars = Map.of("id", courseId);

		try (Session session = driver.session(sessionConfig)) {
			session.executeWrite(tx -> tx.run(query, pars).consume());
		}
	}
	
	
	
	/**
	 * Add a course to a department
	 * @param courseId
	 * @param deptId
	 * String query = "MATCH (c: Course {courseID: $courseId})
	 *  MATCH (d: Department {deptID: $deptId}) 
	 *  MERGE (c)-[:BELONGS_TO]->(d)";
	 */
	public void addCourseToDept(String courseId, String deptId) {
		String query = "MATCH (c: Course {courseID: $courseId}) " +
				 "MATCH (d: Department {deptID: $deptId}) " +
				"MERGE (c)-[:BELONGS_TO]->(d)";
		Map<String, Object> pars = Map.of("courseId", courseId, "deptId", deptId);
		
		try (Session session = driver.session(sessionConfig)) {
			session.executeWrite(tx -> tx.run(query, pars).consume());
		}
		
	}
	
	/**
	 * Find list of courses by department's ID
	 * @param deptId The ID of the department
	 * @return List of courses if found, empty list otherwise
	 * String query = "MATCH (c: Course)-[:BELONGS_TO]->(d: Department) WHERE d.deptID = $id RETURN c";
	 */
	
	public List<Course> listOfCourses(String deptId) {
		deptId = deptId.toUpperCase();
		String query = "MATCH (c: Course)-[:BELONGS_TO]->(d: Department) WHERE toUpper(d.deptID) = $id RETURN c";
		Map<String, Object> pars = Map.of("id", deptId);
		
		try (Session session = driver.session(sessionConfig)) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				
				return result.stream() //Stream<Record>
						.map(record -> {
							Node node = record.get("c").asNode();
							return AppUtils.convert(node, Course.class);
						}).toList(); //List<Course>
			});
		}
	}
	
	/**
	 * Find a course by its ID
	 * @param id The ID of the course
	 * @return The course if found, null otherwise
     * 
	 */
	
	public Course findCourseById(String courseId) {
		String query = "MATCH (c:Course) WHERE c.courseID = $id  RETURN c";
//		String query = "MATCH (c:Course {courseID: $id}) RETURN c";
		Map<String, Object> pars = Map.of("id", courseId);
		
		try(Session session = driver.session(sessionConfig)){
			return session.executeRead(tx -> {
				Result result = tx.run(query, pars);
				if (!result.hasNext()) 
					return null;
				Record record = result.stream().findFirst().get();
				Node node = record.get("c").asNode();
				
//				String id = node.get("courseID").asString();
//				String name = node.get("name").asString();
//				int hours = node.get("hours").asInt();
//				
//				return new Course(id, name, hours);
				
				return AppUtils.convert(node, Course.class);
			});
		}
	}
	
	
	/**
	 * Add new course to the database
	 * 
	 * @param course The course to be added
	 * @return True if the course is added successfully, false otherwise
	 *
	 */
	public String addCourse2(Course course) {
		
		String query = "CREATE (c:Course {courseID: $id, name: $name, hours:$hours}) "
				+ "RETURN c.courseID";
		Map<String, Object> parameters = Map.of(
				"id", course.getCourseId(),
				"name", course.getName(),
				"hours", course.getHours());
		
		try(Session session = driver.session(sessionConfig)){
			return session.executeWrite(tx -> {
				Result result = tx.run(query, parameters);
				Optional<Record> first = result.stream().findFirst();
				return first.isPresent() ? first.get().get("c.courseID").asString() : null;
			});
		}
	}
	
	
	/**
	 * Add new course to the database
	 * 
	 * @param course The course to be added
	 * @return True if the course is added successfully, false otherwise
	 *
	 */
	public boolean addCourse(Course course) {
		
		String query = "CREATE (c:Course {courseID: $id, name: $name, hours:$hours})";
		Map<String, Object> parameters = Map.of(
				"id", course.getCourseId(),
				"name", course.getName(),
				"hours", course.getHours());
		
		try(Session session = driver.session(sessionConfig)){
            
			Transaction tx = session.beginTransaction();
			try {
				
				tx.run(query, parameters).consume();
				
				tx.commit();
				 return true;
			}catch (Exception e) {
				e.printStackTrace();
//				System.out.println(e.getMessage());
				tx.rollback();
			}
			
		}
		
//		Session session = null;
//		try {
//			session = driver.session(sessionConfig);
////			...
//		}finally {
//			session.close();
//		}
		
        return false;
	}
	
	/**
	 * Close the driver
	 */
	
	public void close() {
		driver.close();
	}
	
}
