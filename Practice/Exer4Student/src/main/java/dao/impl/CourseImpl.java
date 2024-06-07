package dao.impl;

import java.util.List;

import dao.CourseDAO;
import entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CourseImpl implements CourseDAO {

	private EntityManager em;

	public CourseImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public boolean add(Course course) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(course);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Course course) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(course);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int id) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Course course = em.find(Course.class, id);
			em.remove(course);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Course findByID(int id) {
		return em.find(Course.class, id);
	}

	@Override
	public List<Course> findAll() {
		return em.createNamedQuery("Course.findAll", Course.class).getResultList();
	}

	@Override
	public List<Course> findByTitle(String title) {
		return em.createNamedQuery("Course.findByTitle", Course.class)
				.setParameter("title", "%"+title+"%")
				.getResultList();
	}

	@Override
	public Course findByTitle2(String title) {
		return em.createQuery("select c from Course c where c.title = :title", Course.class)
				.setParameter("title", title)
//				.getSingleResult();
				.getResultList()
				.stream()
				.findFirst()
				.orElse(null);
	}

}
