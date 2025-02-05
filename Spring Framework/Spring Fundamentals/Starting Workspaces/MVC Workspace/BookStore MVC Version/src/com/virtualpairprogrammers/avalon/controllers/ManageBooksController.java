package com.virtualpairprogrammers.avalon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.BookService;

@Controller
public class ManageBooksController 
{
	@Autowired
	private BookService books;
	
	@RequestMapping("/all-books")
	public ModelAndView displayAllBooksOnWebPage()
	{
		List<Book> allBooks = books.getEntireCatalogue();
		System.out.println(allBooks);
		return new ModelAndView("/allBooksJSPPage.jsp","books", allBooks);
	}
}
