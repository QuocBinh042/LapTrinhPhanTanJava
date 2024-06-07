package test;

import java.util.HashSet;

import entity.Group;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import static java.util.Set.*;

public class Main {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserGroupJPA MSSQL");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

//			I. Add data
			Group group1 = new Group("admin");
			Group group2 = new Group("user");
			
			User user1 = new User("user1", "123","user1@gmail.com");
			User user2 = new User("user2", "123","user2@gmail.com");
			User user3 = new User("admin", "admin","admin@gmail.com");
			User user4 = new User("manager", "manager","manager@gmail.com");
			
			user1.setGroups(new HashSet<>(of(group2)));
			user2.setGroups(new HashSet<>(of(group2)));
			
			user3.setGroups(new HashSet<>(of(group1, group2)));
			user4.setGroups(new HashSet<>(of(group1, group2)));
			
			em.persist(group1);
			em.persist(group2);
			
			em.persist(user1);
			em.persist(user2);
			em.persist(user3);
			em.persist(user4);

//			II. Select data
			
//			1. Get total users 			
			String sql = "SELECT COUNT(u) FROM User u ";
			Long count = em.createQuery(sql, Long.class)
					.getSingleResult();
			
			System.out.println("Total users is " + count);
			
//			 2. Get all users in a group where groupID = ?
			int groupID = 1;
			String sql2 = "SELECT COUNT(u) "
					+ "FROM User u inner join u.groups g "
					+ "WHERE g.id = :id";
			
			Long count2 = em.createQuery(sql2, Long.class)
					.setParameter("id", groupID)
					.getSingleResult();
			System.out.println("Total users in group where groupID = " + groupID + " is " + count2);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		em.close();
		emf.close();
	}
}
