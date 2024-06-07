/*
 * @ (#) Main.java	1.0	Apr 1, 2024
 * 
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package test;

import java.rmi.RemoteException;
import java.util.List;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.CourseImpl;
import dao.impl.DepartmentImpl;
import dao.impl.StudentImpl;
import entity.Student;

public class Main {

	public static void main(String[] args) throws RemoteException {
//		Persistence.createEntityManagerFactory("jpa-mssql");
		
		CourseDAO courseDAO = new CourseImpl();
		StudentDAO studentDAO = new StudentImpl();
		DepartmentDAO departmentDao = new DepartmentImpl();
		
		departmentDao.findDepartmentsNotOwnerCourse()
		.forEach(System.out::println);
		
//		studentDAO.findStudentsMaxGPAs(2005)
//		.entrySet()
//		.forEach(entry -> {
//			System.out.println("Student: " + entry.getKey());
//			System.out.println("GPA: " + entry.getValue());
//		});
//		
		
//		List<Course> courses = courseDAO.findByTitle("po");
//		courses.forEach(System.out::println);
		
//		List<Student> students = studentDAO.findStudentsEnrolledIn(2005);
//		students.forEach(System.out::println);
		
//		List<Student> students = studentDAO.findStudentsEnrolledInCourse("po");
//		students.forEach(System.out::println);
	}
}
