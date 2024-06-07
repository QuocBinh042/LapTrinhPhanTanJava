package dao;

import java.util.ArrayList;
import java.util.List;

import database.Hibernate;
import entity.Department;

public class DepartmentDAO {
	private List<Department> listDepartment = new ArrayList<>();
	private Hibernate hibernate = new Hibernate("TranLeQuocBinh_21081801");

	public DepartmentDAO() {
		// Constructor
	}

	public int addDepartment(Department department) {
		try {
			hibernate.getTransaction().begin();
			if (hibernate.getEntityManager().find(Department.class,
					Integer.toString(department.getDepartmentID())) != null) {
				System.out.println("DepartmentID already exists in the database.");
				return 0;
			}
			hibernate.getEntityManager().persist(department);
			hibernate.getTransaction().commit();
			System.out.println("Successfully added Department data!");
			return 1;
		} catch (Exception e) {
			if (hibernate.getTransaction() != null && hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
			}
			e.printStackTrace();
			System.out.println(e);
			return -1;
		}
	}

	public String getListDepartmentToString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Department department : listDepartment) {
			stringBuilder.append(department.toString()).append("\n");
		}
		return stringBuilder.toString();
	}
}
