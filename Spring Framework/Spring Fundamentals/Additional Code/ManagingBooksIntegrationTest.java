package com.virtualpairprogrammers.avalon.integrationtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.BookService;

/**
 * A JUnit4 test to integration test the Spring Container setup
 *
 * @author Richard Chesterwood
 */
public class ManagingBooksIntegrationTest 
{	
	@Test
	public void testFindingByIsbn()
	{
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("application.xml");
		BookService books = factory.getBean(BookService.class);
		
		// arrange
		String isbn = "1933988134";
		Book testBook = new Book(isbn, "Spring in Action", "Craig Walls", 23.99);
		books.registerNewBook(testBook);

		// act
		Book foundBook = null;
		try 
		{
			foundBook = books.getBookByIsbn(isbn);
			assertEquals("The book returned is the wrong one!", testBook, foundBook);
		} 
		catch (BookNotFoundException e) 
		{
			fail("No book was found when one should have been!");
		}		
	}
	
	@Test
	public void testAddingBooks()
	{
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("application.xml");
		BookService books = factory.getBean(BookService.class);
		
		// act
		books.registerNewBook(new Book("1933988134", "Spring in Action", "Craig Walls", 23.99));
		books.registerNewBook(new Book("0764543857", "Expert One-on-one J2EE Design and Development", "Rod Johnson", 28.59));

		// assert
		int booksInDb = books.getEntireCatalogue().size();
		assertEquals("There should be two books in the database!", 2, booksInDb);

	}
	
	@Test(expected = BookNotFoundException.class)  
	public void testFindingNonExistentBook() throws BookNotFoundException
	{
		ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("application.xml");
		BookService books = factory.getBean(BookService.class);
 	    Book foundBook = books.getBookByIsbn("some garbage isbn");
	}
}
