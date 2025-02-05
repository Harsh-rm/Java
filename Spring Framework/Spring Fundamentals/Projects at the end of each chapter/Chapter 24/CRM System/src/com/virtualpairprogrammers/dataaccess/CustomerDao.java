package com.virtualpairprogrammers.dataaccess;

import java.util.List;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

// FOR USE IN A LATER CHAPTER - PLEASE IGNORE UNTIL THEN

public interface CustomerDao 
{
	/**
	 * Creates a new customer record in the database
	 */
	public void create(Customer customer);

	/**
	 * Finds a customer based on their ID
	 */
	public Customer getById(String customerId) throws RecordNotFoundException;

	/**
	 * Finds all customers whose company name matches the specified name
	 */
	public List<Customer> getByName(String name);

	/**
	 * Updates the specified customer in the database.
	 */
	public void update(Customer customerToUpdate) throws RecordNotFoundException;
	
	/**
	 * Deletes the specified customer from the database.
	 */
	public void delete(Customer oldCustomer) throws RecordNotFoundException;

	/**
	 * Returns a complete collection of customer objects. Note that it is NOT necessary
	 * to for this method to also return the associated calls (ie getCalls() will return null). 
	 * 
	 * This is for efficiency reasons - we may not be interested in the calls for ALL customers
	 * in ths system.
	 * @return
	 */
	public List<Customer> getAllCustomers();
	
	/**
	 * Returns the full detail for this customer - ie the customer object and ALL
	 * calls associated with this customer
	 */
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException;
	
	/**
	 * Links the specifed call to the customer in the database.
	 */
	public void addCall (Call newCall, String customerId) throws RecordNotFoundException;
}
