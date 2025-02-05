
public class HibernateDAO implements InvoicingDAO {

	public void save(Invoice invoice) {
		System.out.println("Saving an invoice using Hibernate");
	}

	@Override
	public void delete(Invoice oldInvoice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Invoice invoiceToCancel) {
		// TODO Auto-generated method stub
		
	}
}
