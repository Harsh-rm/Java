package com.virtualpairprogrammers.avalon.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.avalon.domain.Book;

public class AccountsServiceMockImpl implements AccountsService
{	
	public void raiseInvoice(Book requiredBook) 
	{
		// a very basic implementation for testing
		System.out.println("Raised the invoice for " + requiredBook);
	}
}
