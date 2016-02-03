package it.dobrowolski.invoicer.exception;

public class InvoiceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6710978377254049463L;
	private int invoiceId;

	public InvoiceNotFoundException(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getInvoiceId() {
		return invoiceId;
	}
}
