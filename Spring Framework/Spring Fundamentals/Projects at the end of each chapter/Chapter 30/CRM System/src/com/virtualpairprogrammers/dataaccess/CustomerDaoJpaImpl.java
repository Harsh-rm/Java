package com.virtualpairprogrammers.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class CustomerDaoJpaImpl implements CustomerDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(Customer customer) {
		em.persist(customer);
	}

	@Override
	public Customer getById(String customerId) throws RecordNotFoundException {
		try
		{
			return (Customer)em.createQuery("select customer from Customer as customer where customer.customerId=:customerId")
				.setParameter("customerId", customerId)
				.getSingleResult();
		}
		catch (NoResultException e)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<Customer> getByName(String name) {
		return em.createQuery("select customer from Customer where customer.companyName=:companyName")
				 .setParameter("companyName", name)
				 .getResultList();
	}

	@Override
	public void update(Customer customerToUpdate)
			throws RecordNotFoundException 
	{
		em.merge(customerToUpdate);
	}

	@Override
	public void delete(Customer oldCustomer) throws RecordNotFoundException 
	{
		oldCustomer = em.merge(oldCustomer);
		em.remove(oldCustomer);
	}

	@Override
	public List<Customer> getAllCustomers() 
	{
		return em.createQuery("select customer from Customer as customer").getResultList();
	}

	@Override
	public Customer getFullCustomerDetail(String customerId)
			throws RecordNotFoundException 
	{
		return (Customer)em.createQuery("select customer from Customer as customer left join fetch customer.calls where customer.customerId=:customerId")
				.setParameter("customerId", customerId)
				.getSingleResult();
	}

	@Override
	public void addCall(Call newCall, String customerId)
			throws RecordNotFoundException 
	{
		Customer customer = em.find(Customer.class, customerId);
		customer.addCall(newCall);
	}

}
