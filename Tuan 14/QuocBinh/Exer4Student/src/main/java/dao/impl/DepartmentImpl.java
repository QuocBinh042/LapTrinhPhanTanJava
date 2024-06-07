package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.DepartmentDAO;
import entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DepartmentImpl extends UnicastRemoteObject implements DepartmentDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2098711990791171461L;
	private EntityManager em;

	public DepartmentImpl() throws RemoteException{
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public List<Department> findDepartmentsNotOwnerCourse() throws RemoteException{
		return em.createNamedQuery("Department.findDepartmentsNotOwnerCourse", Department.class).getResultList();
	}
}
