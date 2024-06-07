/*
 * @ (#) Main.java	1.0	Apr 1, 2024
 * 
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package test;

import java.util.List;

import dao.CourseDAO;
import dao.StudentDAO;
import dao.impl.CourseImpl;
import dao.impl.StudentImpl;
import entity.Student;

public class Main {

	public static void main(String[] args) {
//		Persistence.createEntityManagerFactory("jpa-mssql");
		
		CourseDAO courseDAO = new CourseImpl();
		StudentDAO studentDAO = new StudentImpl();
		
		
//		List<Course> courses = courseDAO.findByTitle("po");
//		courses.forEach(System.out::println);
		
//		List<Student> students = studentDAO.findStudentsEnrolledIn(2005);
//		students.forEach(System.out::println);
		
		List<Student> students = studentDAO.findStudentsEnrolledInCourse("po");
		students.forEach(System.out::println);
	}
}
