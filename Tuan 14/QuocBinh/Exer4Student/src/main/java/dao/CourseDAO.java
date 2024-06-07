package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Course;

public interface CourseDAO extends Remote{
	
	public boolean add(Course course) throws RemoteException;
	public boolean update(Course course) throws RemoteException;
	public boolean delete(int id) throws RemoteException;
	public Course findByID(int id) throws RemoteException;
	public List<Course> findAll() throws RemoteException;
	public List<Course> findByTitle(String title) throws RemoteException; // tim tuong doi
	public Course findByTitle2(String title) throws RemoteException; // tim tuyet doi
	
}
