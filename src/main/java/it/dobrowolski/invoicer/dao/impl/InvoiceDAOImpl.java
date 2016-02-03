package it.dobrowolski.invoicer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import it.dobrowolski.invoicer.dao.InvoiceDAO;
import it.dobrowolski.invoicer.exception.InvoiceNotFoundException;
import it.dobrowolski.invoicer.model.Invoice;

@Repository
public class InvoiceDAOImpl implements InvoiceDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addInvoice(Invoice invoice) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(invoice);
	}

	public void updateInvoice(Invoice invoice) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(invoice);
	}
	
	@SuppressWarnings("unchecked")
	public List<Invoice> listInvoices() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Invoice> invoicesList = session.createQuery("from Invoice").list();
		return invoicesList;
	}

	public Invoice getInvoiceById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Invoice invoice = (Invoice) session.get(Invoice.class, new Integer(id));
		if (invoice == null) {
			throw new InvoiceNotFoundException(id);
		}
		invoice.getInvoiceLines().size();
		return invoice;
	}
}
