package com.virtualpairprogrammers.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;

@Controller
public class ManageCustomersController 
{
	@Autowired
	private CustomerManagementService customers;
	
	@RequestMapping("/website/all-customers.html")
	public ModelAndView displayAllCustomersOnWebPage()
	{
		List<Customer> allCustomers = customers.getAllCustomers();
		return new ModelAndView("/allCustomersJSPPage.jsp","customers", allCustomers);
	}
}
