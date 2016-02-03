package it.dobrowolski.invoicer.dao;

import java.util.List;

import it.dobrowolski.invoicer.model.Invoice;

public interface InvoiceDAO {

	public void addInvoice(Invoice invoice);
	public void updateInvoice(Invoice invoice);
	public List<Invoice> listInvoices();
	public Invoice getInvoiceById(int id);
}
