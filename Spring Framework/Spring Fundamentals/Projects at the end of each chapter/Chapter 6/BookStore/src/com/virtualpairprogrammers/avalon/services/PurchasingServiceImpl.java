package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.domain.Book;

public class PurchasingServiceImpl implements PurchasingService
{
	private AccountsService accounts;
	private BookService books;
	
	public PurchasingServiceImpl(AccountsService accountsService, BookService booksService)
	{
		this.accounts = accountsService;
		this.books = booksService;
	}

	public void buyBook(String isbn)
	{
		// find the correct book
		Book requiredBook = books.getBookByIsbn(isbn);

		// now raise the invoice
		accounts.raiseInvoice(requiredBook); 
	}
}
