package clients;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import domain.Book;

public class HibernateClient 
{
	private static SessionFactory sessionFactory = null;

	public static void main(String[] args)
	{
		// make the book persistent
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Book foundBook = (Book)session.get(Book.class, 1);
		System.out.println(foundBook);
		
		foundBook.setTitle("Java Fundamentals");

		// as the book is persistent, this updated value should be reflected in the database....

		tx.commit();
		session.close();

		closeSessionFactory();
	}

	// if you're new to Hibernate, you can ignore this "boilerplate" code!
	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			Configuration configuration = new Configuration();
			configuration.configure();

			ServiceRegistry serviceRegistry = new 
					ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();        

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}			

	// This is a workaround to a hibernate bug that causes the connection to be not closed
	// properly. It's a problem because this will lock our hsqldb database. Don't worry about this
	// for the spring course. See http://stackoverflow.com/questions/15298437/
	public static void closeSessionFactory()
	{
		// this is needed for file based database.
		SessionFactoryImpl impl = (SessionFactoryImpl)getSessionFactory();
		ConnectionProvider conn = impl.getConnectionProvider();
		try {
			conn.getConnection().close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		getSessionFactory().close();
	}

}
