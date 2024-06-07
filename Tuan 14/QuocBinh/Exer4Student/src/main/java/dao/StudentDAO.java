package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import entity.Student;

public interface StudentDAO extends Remote{
	
	public boolean add(Student student) throws RemoteException;
	public List<Student> findAll() throws RemoteException;
	
	public List<Student> findStudentsEnrolledIn(int year) throws RemoteException;
	public List<Student> findStudentsEnrolledInCourse(String title) throws RemoteException;
	public Map<Student, Double> findStudentsGPAs(int year) throws RemoteException; // Sorted by GPA (value)
	public Map<Student, Double> findStudentsGPAs2(int year) throws RemoteException; // Sorted by firstName (key)
	public Map<Student, Double> findStudentsMaxGPAs(int year) throws RemoteException; 
}
