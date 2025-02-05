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
			
			// begin
			bookService.registerNewBook(new Book("10003993939", "Test Title", "Author", 10.99));
			// commit
			
			// begin
			try
			{
				purchasing.buyBook("10003993939");
			}
			catch (BookNotFoundException e)
			{
				System.out.println("Sorry, that book doesn't exist");
			}
			catch (CustomerCreditExcededException e)
			{
				System.out.println("Sorry, you can't afford it, go away");
			}
			// commit								
			
		}
		finally
		{		
			container.close();
		}
	}
}
