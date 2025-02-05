package com.virtualpairprogrammers.avalon.services;

import java.util.List;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

public class BookServiceTimingProxy implements BookService {

	private BookService originalBookService;
	
	public void setOriginalBookService(BookService original)
	{
		this.originalBookService = original;
	}
	
	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllRecommendedBooks(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookNotFoundException {
		long nanoSecondsInAMillisecond = 1000000; 
		
		// start the clock!
		long timeThen = System.nanoTime();
		
		try
		{
			Book foundBook = originalBookService.getBookByIsbn(isbn);
			return foundBook;			
		}
		finally
		{
			// stop the clock
			long timeNow = System.nanoTime();
			
			// report (actually would be done using a logger)
			long timeTaken = timeNow - timeThen;
			System.out.println("getBookByIsbn took " + timeTaken / nanoSecondsInAMillisecond + " milliseconds");			
		}			
	}

	@Override
	public List<Book> getEntireCatalogue() {
		long nanoSecondsInAMillisecond = 1000000; 
		
		// start the clock!
		long timeThen = System.nanoTime();
		
		// do the work - but how???
		List<Book> allBooks = originalBookService.getEntireCatalogue();
		
		// stop the clock
		long timeNow = System.nanoTime();
		
		// report (actually would be done using a logger)
		long timeTaken = timeNow - timeThen;
		System.out.println("getEntireCatalogue took " + timeTaken / nanoSecondsInAMillisecond + " milliseconds");
		
		// get out
		return allBooks;
	}

	@Override
	public void registerNewBook(Book newBook) {
		long nanoSecondsInAMillisecond = 1000000; 
		
		// start the clock!
		long timeThen = System.nanoTime();
		
		originalBookService.registerNewBook(newBook);
		
		// stop the clock
		long timeNow = System.nanoTime();
		
		// report (actually would be done using a logger)
		long timeTaken = timeNow - timeThen;
		System.out.println("registerNewBook took " + timeTaken / nanoSecondsInAMillisecond + " milliseconds");		
	}

}
