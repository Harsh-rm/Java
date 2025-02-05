package com.virtualpairprogrammers.avalon.services;

import java.util.List;

import com.virtualpairprogrammers.avalon.domain.Book;

public interface BookService 
{
	   public List<Book> getAllBooksByAuthor(String author);
	   public List<Book> getAllRecommendedBooks(String userId);
	   public Book getBookByIsbn(String isbn);
	   public List<Book> getEntireCatalogue();
	   public void registerNewBook(Book newBook);
}
