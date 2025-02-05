
public class InvoiceService 
{	
	public void raiseInvoice(Invoice newInvoice)
	{
		InvoicingDAO dao = new InvoicingDAO();
		dao.save(newInvoice);
	}
	
}