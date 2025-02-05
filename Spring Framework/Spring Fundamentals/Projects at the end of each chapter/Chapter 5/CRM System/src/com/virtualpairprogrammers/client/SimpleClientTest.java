package com.virtualpairprogrammers.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;

public class SimpleClientTest {

	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		
		CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
		
		List<Customer> allCustomers = customerService.getAllCustomers();
		
		for (Customer next: allCustomers)
		{
			System.out.println(next);
		}
		
		container.close();
	}

}
