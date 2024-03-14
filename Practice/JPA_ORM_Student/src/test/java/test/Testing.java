package test;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import entity.Clazz;
import entity.Course;
import entity.Enrollment;
import entity.Student;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Testing {
	public EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("JPA_ORM_Student MSSQL");
	}

	@Test
	void testAddStudent() {
		doInJPA(this::entityManagerFactory, entityManager -> {
			Student student = new Student("S001", "John", "john@gmail.com", LocalDate.of(2000, 1, 1));
			entityManager.persist(student); // Create
		});
	}

	@Test
	void testAddStudent2Clazz() {
		doInJPA(this::entityManagerFactory, entityManager -> {
			Student st = entityManager.find(Student.class, "S001");
			Clazz clazz = new Clazz("DHKHMT17CTT", "Kỹ thuật khoa học máy tính 17C tiên tiến", 50);
			st.setClazz(clazz);

			entityManager.persist(clazz);
			entityManager.merge(st);

		});
	}

	@Test
	void testAddEnrollment() {
		doInJPA(this::entityManagerFactory, em -> {
			Student student = em.find(Student.class, "S001");
			Course course = new Course("AJava", "Advanced Java Programming", 4);
			Enrollment enrollment = new Enrollment(student, course, "Spring", 2024, 85);

			em.persist(course);
			em.persist(enrollment);
		});
	}
	
	@Test
	void testFindByID() {
		doInJPA(this::entityManagerFactory, em -> {
			Student student = em.find(Student.class, "S001");
			Course course = em.find(Course.class, "AJava");
			System.out.println(student);
			System.out.println(course);
		});
	}
}
