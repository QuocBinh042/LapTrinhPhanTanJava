package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Department;

public interface DepartmentDAO extends Remote{
	public List<Department> findDepartmentsNotOwnerCourse() throws RemoteException;
}
