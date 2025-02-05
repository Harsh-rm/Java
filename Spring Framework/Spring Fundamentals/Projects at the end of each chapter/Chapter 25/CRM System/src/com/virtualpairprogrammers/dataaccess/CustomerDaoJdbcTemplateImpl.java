package com.virtualpairprogrammers.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

	private static final String INSERT_CALL_SQL = "INSERT INTO TBL_CALL(NOTES, TIME_AND_DATE, CUSTOMER_ID) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_CUSTOMERS_SQL = "SELECT * FROM CUSTOMER";
	private static final String UPDATE_CUSTOMER_SQL = "UPDATE CUSTOMER SET COMPANY_NAME=?, EMAIL=?, TELEPHONE=?, NOTES=? WHERE CUSTOMER_ID = ?";
	private static final String SELECT_CUSTOMER_BY_ID_SQL = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
	private static final String INSERT_CUSTOMER_SQL = "INSERT INTO CUSTOMER (CUSTOMER_ID, COMPANY_NAME, EMAIL, TELEPHONE, NOTES) VALUES (?,?,?,?,?)";
	private static final String CREATE_CALL_TABLE_SQL = "CREATE TABLE TBL_CALL(NOTES VARCHAR(255), TIME_AND_DATE DATE, CUSTOMER_ID VARCHAR(20))";
	private static final String CREATE_CUSTOMER_TABLE_SQL = "CREATE TABLE CUSTOMER(CUSTOMER_ID VARCHAR(20), COMPANY_NAME VARCHAR(50), EMAIL VARCHAR(50), TELEPHONE VARCHAR(20), NOTES VARCHAR(255))";
	private JdbcTemplate template;
	
	@Autowired
	public CustomerDaoJdbcTemplateImpl(JdbcTemplate template)
	{
		this.template = template;
	}
	
	@PostConstruct
	private void createTables()
	{
		try
		{
			template.update(CREATE_CUSTOMER_TABLE_SQL);			
		}
		catch (BadSqlGrammarException e)
		{
			System.out.println("Assuming the Customer table already exists.");
		}

		try
		{
			template.update(CREATE_CALL_TABLE_SQL);
		}
		catch (BadSqlGrammarException e)
		{
			System.out.println("Assuming the Call table already exists.");			
		}
	}
	
	@Override
	public void create(Customer customer) 
	{
		template.update(INSERT_CUSTOMER_SQL,
						customer.getCustomerId(),
						customer.getCompanyName(),
						customer.getEmail(), 
						customer.getTelephone(), 
						customer.getNotes());
	}

	@Override
	public Customer getById(String customerId) throws RecordNotFoundException 
	{
		try
		{
			return template.queryForObject(SELECT_CUSTOMER_BY_ID_SQL, new CustomerRowMapper(), customerId);			
		}
		catch (IncorrectResultSizeDataAccessException e)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<Customer> getByName(String name) 
	{
		return template.query("SELECT * FROM CUSTOMER WHERE COMPANY_NAME = ?", new CustomerRowMapper(), name);
	}

	@Override
	public void update(Customer customerToUpdate) throws RecordNotFoundException 
	{
		int rowsUpdated = template.update(UPDATE_CUSTOMER_SQL,
										  customerToUpdate.getCompanyName(), 
										  customerToUpdate.getEmail(), 
										  customerToUpdate.getTelephone(),
										  customerToUpdate.getNotes(),
										  customerToUpdate.getCustomerId());
		
		if (rowsUpdated != 1)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public void delete(Customer oldCustomer) throws RecordNotFoundException 
	{
		int rowsAffected = template.update("DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?", oldCustomer.getCustomerId());
		if (rowsAffected != 1)
		{
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<Customer> getAllCustomers() 
	{
		return template.query(SELECT_ALL_CUSTOMERS_SQL, new CustomerRowMapper());
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException 
	{
		Customer customer = this.getById(customerId);
		
		List<Call> allCallsForTheCustomer = template.query("SELECT * FROM TBL_CALL WHERE CUSTOMER_ID=?", 
																 new CallRowMapper(),
																 customerId);
		customer.setCalls(allCallsForTheCustomer);
		
		return customer;
	}

	@Override
	public void addCall(Call newCall, String customerId)
			throws RecordNotFoundException 
	{
		Customer foundCustomer = this.getById(customerId);
		template.update(INSERT_CALL_SQL,newCall.getNotes(), newCall.getTimeAndDate(), customerId);
	}

}

class CustomerRowMapper implements RowMapper<Customer>
{

	@Override
	public Customer mapRow(ResultSet rs, int rowNumber) throws SQLException 
	{
		String customerId = rs.getString("CUSTOMER_ID");
		String companyName = rs.getString("COMPANY_NAME");
		String email = rs.getString("EMAIL");
		String telephone = rs.getString("TELEPHONE");
		String notes = rs.getString("NOTES");
		return new Customer(customerId, companyName, email, telephone, notes);
	}
	
}

class CallRowMapper implements RowMapper<Call>
{

	@Override
	public Call mapRow(ResultSet rs, int rowNumber) throws SQLException 
	{
		String notes = rs.getString("NOTES");
		Date dateAndTime = rs.getDate("TIME_AND_DATE");
		
		return new Call(notes, dateAndTime);
	}
	
}
