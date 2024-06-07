package test;

import java.util.HashSet;
import java.util.List;

import entity.Group;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Test {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserGroupJPA MariaDB");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
//			Group g = new Group("Admin");
//			User u = new User("admin", "admin","admin@gmail.com");
//			User u2 = new User("manager", "manager","manager@gmail.com");
//			
//			em.persist(g);
//			em.persist(u);
//			em.persist(u2);
			
			Group g = em.find(Group.class, 1);
			User u = em.find(User.class, 1);
			User u2 = em.find(User.class, 2);
			
			u.setGroups(new HashSet<>(List.of(g)));
			u2.setGroups(new HashSet<>(List.of(g)));
			
			em.merge(u);
			em.merge(u2);
            
            tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		em.close();
		emf.close();
	}
}
