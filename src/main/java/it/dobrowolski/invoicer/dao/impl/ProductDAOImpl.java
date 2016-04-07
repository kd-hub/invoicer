package it.dobrowolski.invoicer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import it.dobrowolski.invoicer.dao.ProductDAO;
import it.dobrowolski.invoicer.exception.ProductNotFoundException;
import it.dobrowolski.invoicer.model.Invoice;
import it.dobrowolski.invoicer.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
	}

	public void removeProduct(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, new Integer(id));
		if (product != null) {
			session.delete(product);
		} else {
			throw new ProductNotFoundException(id);
		}
	}

	public void updateProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(product);
	}

	public void mergeProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> lisProducts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> productsList = session.createQuery("from Product").list();
		return productsList;
	}

	public Product getProductById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, new Integer(id));
		if (product == null) {
			throw new ProductNotFoundException(id);
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> getInvoicessByProductId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Invoice> invoicesByProductIdList = (List<Invoice>) session
				.createSQLQuery(
						"Select invoice_id, date_of_sale, payment_due_date, date_of_issue, total_amount_net, total_amount_gross, customer_customer_id from invoice, invoice_line_item where invoice.invoice_id = invoice_line_item.invoice_invoice_id and invoice_line_item.product_product_id = :id")
				.addEntity(Invoice.class).setParameter("id", id).list();
		return invoicesByProductIdList;
	}
}
