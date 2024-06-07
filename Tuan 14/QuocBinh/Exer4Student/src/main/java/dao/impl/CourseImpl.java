package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.CourseDAO;
import entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CourseImpl extends UnicastRemoteObject implements CourseDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8269594680429191882L;
	private EntityManager em;

	public CourseImpl() throws RemoteException {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public boolean add(Course course) throws RemoteException {
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
	public boolean update(Course course) throws RemoteException{
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
	public boolean delete(int id) throws RemoteException{
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
	public Course findByID(int id) throws RemoteException{
		return em.find(Course.class, id);
	}

	@Override
	public List<Course> findAll() throws RemoteException{
		return em.createNamedQuery("Course.findAll", Course.class).getResultList();
	}

	@Override
	public List<Course> findByTitle(String title) throws RemoteException{
		return em.createNamedQuery("Course.findByTitle", Course.class)
				.setParameter("title", "%"+title+"%")
				.getResultList();
	}

	@Override
	public Course findByTitle2(String title) throws RemoteException{
		return em.createQuery("select c from Course c where c.title = :title", Course.class)
				.setParameter("title", title)
//				.getSingleResult();
				.getResultList()
				.stream()
				.findFirst()
				.orElse(null);
	}

}
