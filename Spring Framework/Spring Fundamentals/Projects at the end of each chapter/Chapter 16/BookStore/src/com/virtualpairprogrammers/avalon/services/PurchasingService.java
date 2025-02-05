package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;

public interface PurchasingService 
{
	public void buyBook(String isbn) throws BookNotFoundException;
}