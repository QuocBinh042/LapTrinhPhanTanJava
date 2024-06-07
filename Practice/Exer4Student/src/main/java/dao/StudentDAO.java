package dao;

import java.util.List;

import entity.Student;

public interface StudentDAO {
	public List<Student> findStudentsEnrolledIn(int year);
	public List<Student> findStudentsEnrolledInCourse(String title);
}
