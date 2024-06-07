package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import entity.Student;

public class Client {
	
	private static final String URL = "rmi://H31M44:7878/";
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		CourseDAO courseDAO = (CourseDAO) Naming.lookup(URL + "courseDAO");
		StudentDAO studentDAO = (StudentDAO) Naming.lookup(URL + "studentDAO");
		DepartmentDAO departmentDAO = (DepartmentDAO) Naming.lookup(URL + "departmentDAO");		
		studentDAO.findAll().forEach(System.out::println);		
		departmentDAO.findDepartmentsNotOwnerCourse().forEach(System.out::println);
//		studentDAO.add(new Student("Tran Le Quoc", "Binh", null));		
		courseDAO.findByTitle("Training");
	}
}
