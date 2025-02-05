package com.virtualpairprogrammers.avalon.domain.test;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import com.virtualpairprogrammers.avalon.domain.Book;

public class BookTest {

	@Test
	public void testBooksWithTheSameISBNNumberAreConsideredTheSameProduct() {
		// arrange
		Book bookOne = new Book("7326837268237", "Java Puzzles", "Josh Bloch", 10.99);
		Book bookTwo = new Book("7326837268237", "Java Puzzles", "Josh Bloch", 10.99);
		
		// act
		boolean areTheyEqual = bookOne.equals(bookTwo);
		
		// assert
		assertTrue(areTheyEqual);
	}
	
	@Test
	public void testFormatOfTheToStringIsCorrect()
	{
		// arrange
		Book bookOne = new Book("7326837268237", "Java Puzzles", "Josh Bloch", 10.99);

		// act
		String toStringResult = bookOne.toString();
		
		// assert
		assertEquals("Java Puzzles by Josh Bloch", toStringResult);
	}

}
