package com.virtualpairprogrammers.avalon.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;

@Transactional
@Named // usually you wouldn't mix these (we've used @Service elsewhere), but I wanted to leave in an example
public class PurchasingServiceImpl implements PurchasingService
{
	@Inject
	private AccountsService accounts;
	
	@Inject
	private BookService books;
	
	@Transactional(rollbackFor= {CustomerCreditExcededException.class, BookNotFoundException.class},
			       timeout=10, isolation=Isolation.SERIALIZABLE)
	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExcededException
	{
		// find the correct book
		Book requiredBook = books.getBookByIsbn(isbn);

		// delete the book from stock
		books.deleteFromStock(requiredBook);
		
		// now raise the invoice
		accounts.raiseInvoice(requiredBook);
	}
}
