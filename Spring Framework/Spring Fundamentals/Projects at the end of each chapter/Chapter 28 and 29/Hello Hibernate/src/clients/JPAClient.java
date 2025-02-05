package clients;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Book;

public class JPAClient 
{
	public static void main(String[] args)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Book newBook = new Book("191919919", "Spring Fundamentals", "Rod Johnson", 19.99);
		em.persist(newBook);
		newBook.setTitle("Spring Advanced");
		
		tx.commit();
		em.close();
		emf.close();
	}
}
