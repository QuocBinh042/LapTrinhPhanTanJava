package Test;

import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

import dao.CourseDAO;
import entity.Course;

public class Main {
	public static void main(String[] args) {
		String uri = "neo4j://localhost:7687";
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic("neo4j", "12345678"));
		CourseDAO courseDAO = new CourseDAO(driver);
		testListOfCourses(courseDAO);
		testFindCourseById(courseDAO);
		testAddCourse(courseDAO);
		testDeleteCourse(courseDAO);
		testAddCourse2(courseDAO);
		testUpdateCourseById(courseDAO);
	}

	public static void testListOfCourses(CourseDAO courseDAO) {
		String departmentId = "CS";
		List<Course> courses = courseDAO.listOfCourses(departmentId);
		System.out.println("Courses for department " + departmentId + ": " + courses);
	}

	public static void testFindCourseById(CourseDAO courseDAO) {
		String courseId = "CS101";
		Course course = courseDAO.findCourseById(courseId);
		System.out.println("Course with ID " + courseId + ": " + course);
	}

	public static void testAddCourse(CourseDAO courseDAO) {
		Course course = new Course();
		course.setCourseId("NEW_COURSE_ID2");
		course.setName("New Course");
		course.setHours(3);

		boolean success = courseDAO.addCourse(course);
		System.out.println("Course added successfully: " + success);
	}

	private static void testDeleteCourse(CourseDAO courseDAO) {
		courseDAO.deleteCourse("CS2S");
	}

	public static void testAddCourse2(CourseDAO courseDAO) {
		Course course = new Course();
		course.setCourseId("CS2S");
		course.setName("New Course2");
		course.setHours(3);

		String courseId = courseDAO.addCourse2(course);
		System.out.println("Added course ID: " + courseId);
	}

	
	public static void testUpdateCourseById(CourseDAO courseDAO) {
		Course course = new Course();
		course.setName("New Course3");
		course.setHours(3);
		System.out.println(course);
		boolean success = courseDAO.updateCourseById("CS101", course);
		
		System.out.println("Course added successfully: " + success);
		System.out.println(courseDAO.findCourseById("CS101"));
	}

}
