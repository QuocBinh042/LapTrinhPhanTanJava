package test;

import entity.Course;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ORM_Student MSSQL");
		EntityManager em = emf.createEntityManager();
		Student student = em.find(Student.class, "S001");
		Course course = em.find(Course.class, "AJava");
		System.out.println(student);
		System.out.println(course);

		//	EntityTransaction tx = em.getTransaction();
		//	Student st = new Student("S002", "Raj", "raj@gmail.com", LocalDate.of(2000, 1, 1));
		//	try {
		//		tx.begin();
		//		em.persist(st);
		//		tx.commit();
		//	}catch (Exception e) {
		//		e.printStackTrace();
		//		tx.rollback();
		//	}
	}
}
