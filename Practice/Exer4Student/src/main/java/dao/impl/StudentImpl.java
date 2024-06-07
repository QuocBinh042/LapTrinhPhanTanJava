package dao.impl;

import java.util.List;

import dao.StudentDAO;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class StudentImpl implements StudentDAO{
	
	private EntityManager em;

	public StudentImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public List<Student> findStudentsEnrolledIn(int year) {
		return em.createNamedQuery("Student.findByEnrollmentYear", Student.class)
				.setParameter("year", year)
				.getResultList();
	}

	@Override
	public List<Student> findStudentsEnrolledInCourse(String title) {
		return em.createNamedQuery("Student.findByEnrollmentCourse", Student.class)
				.setParameter("title", "%"+title+"%")
				.getResultList();
	}


}
