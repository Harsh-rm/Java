package com.virtualpairprogrammers.avalon.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.avalon.services.PurchasingService;

public class Client 
{
	public static void main(String[] args)
	{
		System.out.println("Testing buying a book....");
		String requiredIsbn = "ISBN1"; // we know this isbn is present in the mock
		
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		PurchasingService purchasing = container.getBean(PurchasingService.class);
		
		PurchasingService purchasing2 = container.getBean(PurchasingService.class);
		PurchasingService purchasing3 = container.getBean(PurchasingService.class);
		PurchasingService purchasing4 = container.getBean(PurchasingService.class);
		PurchasingService purchasing5 = container.getBean(PurchasingService.class);
		PurchasingService purchasing6 = container.getBean(PurchasingService.class);
		
		
		purchasing.buyBook(requiredIsbn);
		
		container.close();
	}
}
