package com.virtualpairprogrammers.avalon.data;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.virtualpairprogrammers.avalon.domain.Book;

@Repository
public class BookDaoHibernateVersion implements BookDao {
	
	@Autowired
	private HibernateTemplate template;

	@Override
	public List<Book> allBooks() {
		//return (List<Book>)template.find("from Book");
		return template.execute(session -> session.createQuery("from Book").list());
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
		 //List<Book> results = (List<Book>)template.find("from Book where isbn=?", isbn);
		List<Book> results = template.execute(session -> {
			Query<Book> q  = session.createQuery("find Book where isbn=?");
			q.setParameter(0, isbn);
			return q.list();
		});
		 
		 if (results.isEmpty())
		 {
			 throw new BookNotFoundException();
		 }		 
		 return results.get(0);
	}

	@Override
	public void create(Book newBook) {
		template.save(newBook);
	}

	@Override
	public void delete(Book redundantBook) {
		Book foundBook = template.get(Book.class, redundantBook.getId());		
		template.delete(foundBook);
	}

	@Override
	public List<Book> findBooksByAuthor(String author) 
	{
		//return (List<Book>)template.findByNamedParam("from Book where author=:author", "author", author);
		return template.execute( session -> {
			Query<Book> q = session.createQuery("from Book where author=:author");
			q.setParameter("author", author);
			return q.list();
		});
	}

}
