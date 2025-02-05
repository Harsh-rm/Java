
public class Client 
{
	public static void main(String[] args)
	{
		InvoiceService invoices = new InvoiceService();
		
		Invoice newInvoice = new Invoice("11075", "Bill McLarane");
		
		invoices.raiseInvoice(newInvoice);
	}
}
