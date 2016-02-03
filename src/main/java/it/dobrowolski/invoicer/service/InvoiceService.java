package it.dobrowolski.invoicer.service;

import java.util.List;

import it.dobrowolski.invoicer.model.Invoice;

public interface InvoiceService {

	public void addInvoice(Invoice invoice);
	public void updateInvoice(Invoice invoice);
	public List<Invoice> listInvoices();
	public Invoice getInvoiceById(int id);
}
