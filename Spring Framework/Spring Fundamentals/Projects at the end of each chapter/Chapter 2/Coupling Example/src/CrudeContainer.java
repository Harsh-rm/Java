/**
 * 
 * A Very basic demonstration of what a container does.
 * Don't worry, we'll use a "real" one in the next chapter!
 *
 */
public class CrudeContainer {
	
	//VERY CRUDE!!
	public static InvoiceService getInvoiceService()
	{
		//set up the Data Access Object
		InvoicingDAO dao = new InvoicingDAOJdbcImplementation();
		
		//create the service
		InvoiceService service = new InvoiceService();
		
		//configure it
		service.setDao(dao);
		
		return service;
	}

}
