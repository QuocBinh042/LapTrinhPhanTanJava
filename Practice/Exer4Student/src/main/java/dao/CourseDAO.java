package dao;

import java.util.List;

import entity.Course;

public interface CourseDAO {
	
	public boolean add(Course course);
	public boolean update(Course course);
	public boolean delete(int id);
	public Course findByID(int id);
	public List<Course> findAll();
	public List<Course> findByTitle(String title); // tim tuong doi
	public Course findByTitle2(String title); // tim tuyet doi
	
}
