
public class Invoice 
{
	private String invoiceId;
	private String customerName;
	private String status;
	
	public Invoice(String invoiceId, String customerName) 
	{
		this.status = "Raised";
		this.invoiceId = invoiceId;
		this.customerName = customerName;
	}
	
	public String toString()
	{
		return this.invoiceId + " for " + this.customerName + " status " + this.status;
	}

	public String getInvoiceId() 
	{
		return this.invoiceId;
	}
	
	public String getCustomerName()
	{
		return this.customerName;
	}

	public void cancel() 
	{
		this.status = "Cancelled";		
	}
}
