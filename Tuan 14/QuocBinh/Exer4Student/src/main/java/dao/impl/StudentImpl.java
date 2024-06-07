package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.StudentDAO;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class StudentImpl extends UnicastRemoteObject implements StudentDAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 197695353671653690L;
	private EntityManager em;

	public StudentImpl() throws RemoteException{
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public List<Student> findStudentsEnrolledIn(int year) throws RemoteException{
		return em.createNamedQuery("Student.findByEnrollmentYear", Student.class)
				.setParameter("year", year)
				.getResultList();
	}

	@Override
	public List<Student> findStudentsEnrolledInCourse(String title) throws RemoteException{
		return em.createNamedQuery("Student.findByEnrollmentCourse", Student.class)
				.setParameter("title", "%"+title+"%")
				.getResultList();
	}

	@Override
	public Map<Student, Double> findStudentsGPAs(int year) throws RemoteException{
//		Map<Student, Double> map = new HashMap<>();
		Map<Student, Double> map = new LinkedHashMap<>();
		List<?> list = em.createNamedQuery("Student.findGPAs")
				.setParameter("year", year)
				.getResultList();
		
		list.stream()
		.map(o -> (Object[]) o)
		.forEach(a -> {
			int studentID = (int) a[0];
			Student student = em.find(Student.class, studentID);
			double gpa = (double) a[1];
			map.put(student, gpa);
		});
		
		return map;
	}
	
	@Override
	public Map<Student, Double> findStudentsGPAs2(int year) throws RemoteException{
//		Map<Student, Double> map = new HashMap<>();
//		Map<Student, Double> map = new LinkedHashMap<>();
		Map<Student, Double> map = new TreeMap<>(
					Comparator.comparing(Student::getFirstName)
					.thenComparing(Student::getLastName)
				);
		List<?> list = em.createNamedQuery("Student.findGPAs2")
				.setParameter("year", year)
				.getResultList();
		
		list.stream()
		.map(o -> (Object[]) o)
		.forEach(a -> {
			int studentID = (int) a[0];
			Student student = em.find(Student.class, studentID);
			double gpa = (double) a[1];
			map.put(student, gpa);
		});
		
		return map;
	}
	
	
	@Override
	public Map<Student, Double> findStudentsMaxGPAs(int year) throws RemoteException{
		Map<Student, Double> map = new HashMap<>();
//		Map<Student, Double> map = new LinkedHashMap<>();
		List<?> list = em.createNamedQuery("Student.findMaxGPAs")
				.setParameter("year", year)
				.getResultList();
		
		list.stream()
		.map(o -> (Object[]) o)
		.forEach(a -> {
			int studentID = (int) a[0];
			Student student = em.find(Student.class, studentID);
			double gpa = (double) a[1];
			map.put(student, gpa);
		});
		
		return map;
	}

	@Override
	public boolean add(Student student) throws RemoteException {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(student);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> findAll() throws RemoteException {
		return em.createQuery("select s from Student s", Student.class).getResultList();
	}


}
