package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dao.CourseDAO;
import entity.Course;
import util.AppUtils;

@TestInstance(Lifecycle.PER_CLASS)
public class CourseDaoTest {
	private CourseDAO courseDao;
	
	@BeforeAll
	public void setUp() {
		courseDao = new CourseDAO(AppUtils.initDriver());
	}
	
	@Test
	public void testAddCourseToDept() {
		String courseId = "CS505";
		String deptId = "CS";
		courseDao.addCourseToDept(courseId, deptId);
	}
	
//	│(:Department {dean: "Carson",name: "Mathemagics",deptID: "Math",buildi│
//		│ng: "Acme",room: "300"})  
	@Test
	public void testListOfCourses() {
		String deptId = "MATH";
		List<Course> courses = courseDao.listOfCourses(deptId);
		courses.forEach(c -> System.out.println(c));
		assertEquals(3, courses.size());
	}
	
	
//	│(:Course {hours: 4,name: "Programming",courseID: "CS101"}) 
	@Test
	public void testFindCourseById() {
		Course course = courseDao.findCourseById("CS101");
		assertNotNull(course);
		assertEquals("CS101", course.getCourseId());
		assertEquals("Programming", course.getName());
		assertEquals(4, course.getHours());
	}
	
	@Test
	public void testFindCourseById_Null() {
		Course course = courseDao.findCourseById("CS1000");
		assertNull(course, "Course not found");
	}
	
	@Test
	public void testAddCourse() {
		Course course = new Course("CS505", "Java Programming", 3);
		String rs = courseDao.addCourse2(course);
//		System.out.println(rs);
//		assertEquals(true, rs);
		assertEquals("CS505", rs);
	}
	
	
	@AfterAll
	public void tearDown() {
		courseDao.deleteCourse("CS505");
		courseDao.close();
		courseDao = null;
	}
	
}
