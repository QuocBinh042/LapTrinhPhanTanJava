package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dao.StudentDAO;
import dao.impl.StudentImpl;
import entity.Student;

@TestInstance(Lifecycle.PER_CLASS)
class StudentDAOTest {
	
	private StudentDAO studentDAO;

	@BeforeAll
	void setUpBeforeClass() throws Exception {
		studentDAO = new StudentImpl();
	}

	@AfterAll
	void tearDownAfterClass() throws Exception {
		studentDAO = null;
	}

	@Test
	void testFindStudentsEnrolledIn() throws RemoteException {
		
		List<Student> students = studentDAO.findStudentsEnrolledInCourse("po");
		assertEquals(5, students.size());
		Student student = students.stream().filter(s -> s.getId()==3).findFirst().orElse(null);
		assertNotNull(student);
		assertEquals("Peggy", student.getFirstName());
	}

}
