package com.virtualpairprogrammers.avalon.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.virtualpairprogrammers.avalon.domain.Book;

public class BookDaoHibernateVersion implements BookDao {
	
	@Autowired
	private HibernateTemplate template;

	@Override
	public List<Book> allBooks() {
		return (List<Book>)template.find("from Book");
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException {
		 List<Book> results = (List<Book>)template.find("from Book where isbn=?", isbn);
		 
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
		return (List<Book>)template.findByNamedParam("from Book where author=:author", "author", author);
	}

}
