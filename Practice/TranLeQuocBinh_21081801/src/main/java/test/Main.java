package test;

import java.time.LocalDateTime;
import java.util.List;

import dao.CourseDAO;
import dao.DepartmentDAO;
import entity.*;

public class Main {

	public static void main(String[] args) {
		Department department1 = new Department(1, 1, 1, "name", LocalDateTime.of(2022, 3, 20, 10, 30, 0));
		Course course1 = new Course(1, 30, "English");
		Course course2 = new Course(2, 30, "Math");
		OnlineCourse onlineCourse1 = new OnlineCourse("www.123.com");
		OnsiteCourse onsiteCourse1 = new OnsiteCourse("Mon", "HCM", LocalDateTime.of(2022, 3, 20, 10, 30, 0));

		course1.setOnlineCourse(onlineCourse1);
		onlineCourse1.setCourse(course1);
		onsiteCourse1.setCourse(course1);
		course1.setOnsiteCourse(onsiteCourse1);
		department1.getListCourse().add(course1);
		department1.getListCourse().add(course2);
		course1.setDepartment(department1);
		course2.setDepartment(department1);

		CourseDAO courseDAO = new CourseDAO();
		DepartmentDAO departmentDAO = new DepartmentDAO();

		// Add Department to database
		if (departmentDAO.addDepartment(department1) == 1) {
			System.out.println("Department created successfully.");
		} else {
			System.out.println("Failed to create Department.");
		}

		// Add Courses to database
		int addResult1 = courseDAO.addCourse(course1);
		int addResult2 = courseDAO.addCourse(course2);

		if (addResult1 == 1 && addResult2 == 1) {
			System.out.println("Courses added successfully.");
		} else {
			System.out.println("Failed to add courses.");
		}

		// FIND
        List<Course> courses = courseDAO.findByTitle("English");
        if (courses != null && !courses.isEmpty()) {
            System.out.println("Courses with the title \"English\":");
            for (Course course : courses) {
                System.out.println("ID: " + course.getCourseID() + ", Title: " + course.getTitle());
            }
        } else {
            System.out.println("No courses found with the title \"English\".");
        }
    
		
		// Update Course
		course1.setCredits(40);
		int updateResult = courseDAO.updateCourse(course1);
		if (updateResult == 1) {
			System.out.println("Course updated successfully.");
		} else {
			System.out.println("Failed to update course.");
		}

		// Delete Course
		int deleteResult = courseDAO.deleteCourse(2);
		if (deleteResult == 1) {
			System.out.println("Course deleted successfully.");
		} else if (deleteResult == 0) {
			System.out.println("Course not found for deletion.");
		} else {
			System.out.println("Failed to delete course.");
		}
	}
}
