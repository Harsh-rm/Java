package com.virtualpairprogrammers.avalon.domain;

public class Book 
{
	private String isbn;
	private String title;
	private String author;
	private double price;

	public Book(String isbn, String title, String author, double price) 
	{
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public String toString()
	{
		return this.title + " by " + this.author;
	}

	public String getIsbn() 
	{
		return this.isbn;
	}
}
