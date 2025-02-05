package com.virtualpairprogrammers.restcontrollers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

@RestController()
public class CustomerRestController 
{
	@Autowired
	private CustomerManagementService customerService;
	
	@Autowired
	private CallHandlingService callHandling;
	
	@Autowired
	private DiaryManagementService diaryService;
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> rulesForCustomerNotFound(HttpServletRequest req, Exception e) 
	{
		ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_FOUND);
		
		// spring will pick up the return and CONVERT IT!
	}
	
	@ExceptionHandler(javax.persistence.OptimisticLockException.class)
	@ResponseStatus(value=HttpStatus.CONFLICT)
	public void handleConflicts() {}

	// we want to support GETs to /customer/373737
	@RequestMapping(value="/rest/customer/{id}", method=RequestMethod.GET)
	public Customer findCustomerById(@PathVariable String id,
									 @RequestParam(required=false, defaultValue="true") boolean fullDetail) throws CustomerNotFoundException
	{
		if (fullDetail)
		{
			return customerService.getFullCustomerDetail(id);
		}
		else
		{
			Customer customer = customerService.findCustomerById(id);
			customer.setCalls(null);
			return customer;
		}
	}


	@Autowired
	private HttpServletRequest request;
	
	/**
	 * Requirement: ONLY return customers.
	 * @return
	 * @throws CustomerNotFoundException 
	 */
	@RequestMapping(value="/rest/customers", method=RequestMethod.GET)
	public CustomerCollectionRepresentation returnAllCustomers(@RequestParam(required=false) Integer first, 
			 												   @RequestParam(required=false) Integer last) throws CustomerNotFoundException
	{			
		
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements())
		{
			String nextName = headerNames.nextElement().toString();
			
			String value = request.getHeader(nextName);
			
			System.out.println(nextName + " : " + value);
		}
		
		List<Customer> allCustomers = customerService.getAllCustomers();
		for (Customer next: allCustomers)
		{
			next.setCalls(null);
			
			Link link = linkTo(methodOn(CustomerRestController.class).findCustomerById(next.getCustomerId(),true)).withSelfRel();
			next.add(link);
		}
		
		if (first != null && last != null)
		{
			CustomerCollectionRepresentation page = new CustomerCollectionRepresentation(allCustomers.subList(first-1, last));
			page.add(linkTo(methodOn(CustomerRestController.class).returnAllCustomers(last+1, last+10)).withRel("next"));
			return page;
		}
		else
		{
			return new CustomerCollectionRepresentation(allCustomers);
		}
		// sent to the message converter
	}
	
	@RequestMapping(value="/rest/customers", method=RequestMethod.POST)
	public ResponseEntity<Customer> createNewCustomer(@RequestBody @Valid Customer newCustomer) throws CustomerNotFoundException
	{	
		Customer createdCustomer = customerService.newCustomer(newCustomer);		
		HttpHeaders headers = new HttpHeaders();
		URI uri = linkTo(methodOn(CustomerRestController.class).findCustomerById(createdCustomer.getCustomerId(),false)).toUri();
		headers.setLocation(uri);
				
		createdCustomer.add(linkTo(methodOn(CustomerRestController.class).getAllCallsForCustomer(createdCustomer.getCustomerId())).withRel("calls"));
	
		return new ResponseEntity<Customer>(createdCustomer, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/rest/customer/{id}", method=RequestMethod.DELETE)
	public void deleteById(@PathVariable String id) throws CustomerNotFoundException
	{
		Customer oldCustomer = customerService.findCustomerById(id);
		customerService.deleteCustomer(oldCustomer);
	}
	
	@RequestMapping(value="/rest/customer/{id}", method=RequestMethod.PATCH)
	public void partiallyUpdateCustomer(@RequestBody Customer customerToUpdate,
			 				   @PathVariable String id) throws CustomerNotFoundException
	{
		Customer existingCustomer = customerService.findCustomerById(id);
		
		// check every field, if it's null, go with the value the customer had before....
		if (customerToUpdate.getCustomerId() == null)
		{
			customerToUpdate.setCustomerId(id);
		}
		
		if (customerToUpdate.getNotes() == null)
		{
			customerToUpdate.setNotes(existingCustomer.getNotes());
		}
		
		customerService.updateCustomer(customerToUpdate);
	}
	
	@RequestMapping(value="/rest/customer/{id}", method=RequestMethod.PUT)
	public void updateCustomer(@RequestBody Customer customerToUpdate) throws CustomerNotFoundException
	{
		customerService.updateCustomer(customerToUpdate);
	}
	
	@RequestMapping(value="/rest/customer/{id}/calls", method=RequestMethod.POST)
	public ResponseEntity<CallActionRepresentation> recordCallBusinessProcess(@RequestBody @Valid CallActionRepresentation incomingCall, @PathVariable String id) throws CustomerNotFoundException
	{
		callHandling.recordCall(id, incomingCall.getCall(), incomingCall.getActions());
		
		incomingCall.add(linkTo(methodOn(CustomerRestController.class).getAllCallsForCustomer(id)).withRel("calls"));
		
		Action firstAction = incomingCall.getActions().iterator().next();
		
		incomingCall.add(linkTo(methodOn(CustomerRestController.class).getAllIncompleteActionsForUser(firstAction.getOwningUser())).withRel("actions"));
		
		return new ResponseEntity<CallActionRepresentation>(incomingCall, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/rest/customer/{id}/calls", method=RequestMethod.GET)
	public CallCollection getAllCallsForCustomer(@PathVariable String id) throws CustomerNotFoundException
	{
		Customer customer = customerService.getFullCustomerDetail(id);
		return new CallCollection(customer.getCalls());
	}
	
	@RequestMapping(value="/rest/user/{userid}/actions", method=RequestMethod.GET)
	public ActionCollection getAllIncompleteActionsForUser(@PathVariable String userid)
	{
		return new ActionCollection(diaryService.getAllIncompleteActions(userid));
	}
		
}
