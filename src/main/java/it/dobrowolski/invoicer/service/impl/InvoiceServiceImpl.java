package it.dobrowolski.invoicer.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dobrowolski.invoicer.dao.InvoiceDAO;
import it.dobrowolski.invoicer.dao.ProductDAO;
import it.dobrowolski.invoicer.model.Invoice;
import it.dobrowolski.invoicer.model.InvoiceLine;
import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.service.InvoiceService;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

	private InvoiceDAO invoiceDAO;

	@Autowired
	public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
		this.invoiceDAO = invoiceDAO;
	}

	private ProductDAO productDAO;

	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Transactional
	public boolean addInvoice(Invoice invoice) {
		Set<InvoiceLine> invoiceLines = invoice.getInvoiceLines();
		for (InvoiceLine invoiceLine : invoiceLines) {
			if (invoiceLine.getProduct().getItemSellingQuantity() > this.productDAO.getProductById(invoiceLine.getProduct().getId()).getStockQuantity()) {
				return false;
			}
		}
		for (InvoiceLine invoiceLine : invoiceLines) {
			Product product = invoiceLine.getProduct();
			product.setStockQuantity(product.getStockQuantity() - invoiceLine.getProduct().getItemSellingQuantity());
			this.productDAO.mergeProduct(product);
		}
		this.invoiceDAO.addInvoice(invoice);
		return true;
	}

	@Transactional
	public void updateInvoice(Invoice invoice) {
		this.invoiceDAO.updateInvoice(invoice);
	}

	@Transactional
	public List<Invoice> listInvoices() {
		return this.invoiceDAO.listInvoices();
	}

	@Transactional
	public Invoice getInvoiceById(int id) {
		return this.invoiceDAO.getInvoiceById(id);
	}

}
