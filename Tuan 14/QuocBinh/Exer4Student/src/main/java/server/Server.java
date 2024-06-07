package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.CourseImpl;
import dao.impl.DepartmentImpl;
import dao.impl.StudentImpl;

public class Server {

	private static final String URL = "rmi://DESKTOP-K2I7FKM:1801/";

	public static void main(String[] args) throws RemoteException, NamingException {
		
		Context context = new InitialContext();
		
		CourseDAO courseDAO = new CourseImpl(); // Java Remote Object
		StudentDAO studentDAO = new StudentImpl(); // Java Remote Object
		DepartmentDAO departmentDAO = new DepartmentImpl(); // Java Remote Object
		
		LocateRegistry.createRegistry(1801);
		
		context.bind(URL +"courseDAO", courseDAO);
		context.bind(URL +"studentDAO", studentDAO);
		context.bind(URL +"departmentDAO", departmentDAO);
		
		System.out.println("Server is running...");
	}

}
