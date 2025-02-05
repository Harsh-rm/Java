package com.virtualpairprogrammers.integrationtests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/daos.xml", "/services.xml", "/misc-beans.xml", "/datasource-test.xml" })
@Transactional
public class CustomerManagementIntegrationTests 
{
	@Autowired
	private CustomerManagementService customerService;
	
	@Autowired
	private CallHandlingService callsService;
	
	@Test
	public void testCreatingACustomerRecord()
	{
		Customer testCustomer = new Customer("9191", "VPP", "email", "telephone","notes"); 
		customerService.newCustomer(testCustomer);
		
		List<Customer> allCustomers = customerService.getAllCustomers();
		int numberOfCustomers = allCustomers.size();
		
		assertEquals(1, numberOfCustomers);
	}
	
	@Test
	public void testFindingACustomer()
	{
		Customer testCustomer = new Customer("9191", "VPP", "email", "telephone","notes"); 
		customerService.newCustomer(testCustomer);
		
		try 
		{
			Customer foundCustomer = customerService.findCustomerById("9191");
			assertEquals(testCustomer, foundCustomer);
		} 
		catch (CustomerNotFoundException e) 
		{
			fail("Customer not found");
		}
	}
	
	@Test
	public void testAddingACallToACustomer()
	{
		Customer testCustomer = new Customer("9191", "VPP", "email", "telephone","notes"); 
		customerService.newCustomer(testCustomer);
		
		Call testCall = new Call("Just a test call");
		List<Action> actions = new ArrayList<Action>();
		
		try 
		{
			callsService.recordCall("9191", testCall, actions);
			
			Customer foundCustomer = customerService.getFullCustomerDetail("9191");
			Call foundCall = foundCustomer.getCalls().get(0);
			assertEquals(testCall, foundCall);
		} 
		catch (CustomerNotFoundException e) 
		{
			fail("Customer was not found");
		}
	}
}
