package dao;

import java.util.List;

import javax.swing.JOptionPane;

import database.Hibernate;
import entity.Course;

import jakarta.persistence.TypedQuery;

public class CourseDAO {
	private Hibernate hibernate = new Hibernate("TranLeQuocBinh_21081801");

	public CourseDAO() {
	}

	public int addCourse(Course course) {
		try {
			hibernate.getTransaction().begin();
			if (hibernate.getEntityManager().find(Course.class, Integer.toString(course.getCourseID())) != null) {
				System.out.println("CourseID already exists in the database.");
				return 0;
			}
			hibernate.getEntityManager().persist(course);
			hibernate.getTransaction().commit();
			System.out.println("Successfully added Course data!");
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

	public List<Course> getListCourse() {
		try {
			TypedQuery<Course> query = hibernate.getEntityManager().createQuery("SELECT c FROM Course c", Course.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	// Update
	public int updateCourse(Course course) {
		try {
			hibernate.getTransaction().begin();
			hibernate.getEntityManager().merge(course);
			hibernate.getTransaction().commit();
			System.out.println("Successfully updated Course data!");
			return 1;
		} catch (Exception e) {
			if (hibernate.getTransaction() != null && hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
			}
			System.err.println("Error updating course: " + e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	// Delete
	public int deleteCourse(int courseId) {
		try {
			hibernate.getTransaction().begin();
			Course course = hibernate.getEntityManager().find(Course.class, courseId);
			if (course != null) {
				hibernate.getEntityManager().remove(course);
				hibernate.getTransaction().commit();
				System.out.println("Successfully deleted Course data!");
				return 1;
			} else {
				System.out.println("Course not found with ID: " + courseId);
				return 0;
			}
		} catch (Exception e) {
			if (hibernate.getTransaction() != null && hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
			}
			System.err.println("Error deleting course: " + e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	public List<Course> findByTitle(String title) {
		TypedQuery<Course> query = hibernate.getEntityManager()
				.createQuery("SELECT c FROM Course c WHERE c.title = :title", Course.class);
		query.setParameter("title", title);
		return query.getResultList();
	}
}
