package com.virtualpairprogrammers.avalon.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.avalon.data.BookDao;
import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.AccountsService;
import com.virtualpairprogrammers.avalon.services.BookService;
import com.virtualpairprogrammers.avalon.services.CustomerCreditExcededException;
import com.virtualpairprogrammers.avalon.services.PurchasingService;

public class Client 
{
	public static void main(String[] args)
	{	
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

		try
		{
			PurchasingService purchasing = container.getBean(PurchasingService.class);
			BookService bookService = (BookService)container.getBean("bookService");
			
			bookService.registerNewBook(new Book("494949484748", "Java Programming", "Gary Cornell", 10.99));
			
			List<Book> allBooks = bookService.getAllBooksByAuthor("Josh Bloch");
			
			for (Book book: allBooks)
			{
				System.out.println(book);
			}
		}
		finally
		{		
			container.close();
		}
	}
}
