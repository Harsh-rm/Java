package com.virtualpairprogrammers.avalon.services;

import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

@Transactional
public class PurchasingServiceImpl implements PurchasingService
{
	private AccountsService accounts;
	private BookService books;
	
	public PurchasingServiceImpl(AccountsService accountsService, BookService booksService)
	{
		this.accounts = accountsService;
		this.books = booksService;
	}

	public void buyBook(String isbn) throws BookNotFoundException
	{
		// find the correct book
		Book requiredBook = books.getBookByIsbn(isbn);

		// now raise the invoice
		accounts.raiseInvoice(requiredBook); 
	}
}
