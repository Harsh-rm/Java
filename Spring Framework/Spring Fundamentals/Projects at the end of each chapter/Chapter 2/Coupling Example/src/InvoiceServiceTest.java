import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvoiceServiceTest {

	@Test
	void testThatSendingAnInvalidInvoiceWilThrowAnException() {
		InvoiceService service = new InvoiceService();
		Invoice invalidInvoice = new Invoice("38378373X", "Jack Jones");
		service.raiseInvoice(invalidInvoice);
		
		// test that an exception is thrown.
	}

}
