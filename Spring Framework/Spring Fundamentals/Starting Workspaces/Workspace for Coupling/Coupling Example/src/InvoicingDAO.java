import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is a very (VERY!) basic implementation of a DAO, just to illustrate the problems
 * that coupling can cause. Later in the course we will implement production standard DAOs.
 * 
 * Not to be used in a real application! In particular, the exception handling is not complete.
 *
 * Author Richard Chesterwood
 * Date 31 October 2013
 * Reviewed 30 June 2020
 */
public class InvoicingDAO
{
	private static final String INSERT_INVOICES_SQL = "insert into INVOICES (INVOICE_ID, CUSTOMER_NAME) values (?, ?)";
	private static final String DRIVER_URL = "org.hsqldb.jdbcDriver";
	private static final String DATABASE_URL = "jdbc:hsqldb:file:invoicedb.dat;shutdown=true";
	private static final String CREATE_TABLES_SQL = "create table INVOICES(INVOICE_ID VARCHAR(255), CUSTOMER_NAME VARCHAR(255))";

	public InvoicingDAO()
	{
		try 
		{
			Class.forName(DRIVER_URL);
			createTables();
		} 
		catch (ClassNotFoundException e) 
		{
			throw new DatabaseUnavailableException();
		}
	}
	
	private static void createTables()
	{
		try 
		{
			Connection con = null;
			PreparedStatement createTable = null;

			try
			{
				con = DriverManager.getConnection(DATABASE_URL, "sa", "");
				createTable = con.prepareStatement(CREATE_TABLES_SQL);
				createTable.executeUpdate();
			}
			finally
			{
				if (createTable != null)
					createTable.close();
				if (con != null)
					con.close();
			}
		} 
		catch (SQLException e) 
		{
			// we will assume that the table already exists...
			// this isn't a very good assumption - later in the course
			// we'll see how Spring helps us handle this case.
		}		
	}

	public void save(Invoice newInvoice)
	{
		try 
		{
			Connection con = null;
			PreparedStatement insertInvoice = null;

			try
			{
				con = DriverManager.getConnection(DATABASE_URL, "sa", "");
				insertInvoice = con.prepareStatement(INSERT_INVOICES_SQL);
				insertInvoice.setString(1, newInvoice.getInvoiceId());
				insertInvoice.setString(2, newInvoice.getCustomerName());
				insertInvoice.executeUpdate();
			}
			finally
			{
				if (insertInvoice != null)
					insertInvoice.close();
				if (con != null)
					con.close();
			}
		} 
		catch (SQLException e) 
		{
			throw new DatabaseUnavailableException(e);
		}
	}

	public void delete(Invoice oldInvoice) {
		System.out.println("This is a stub of the delete method");
	}

	public void update(Invoice invoiceToCancel) {
		System.out.println("This is a stub of the update method");		
	}
}
