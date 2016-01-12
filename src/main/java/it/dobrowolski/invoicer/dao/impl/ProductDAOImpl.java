package it.dobrowolski.invoicer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import it.dobrowolski.invoicer.dao.ProductDAO;
import it.dobrowolski.invoicer.exception.ProductNotFoundException;
import it.dobrowolski.invoicer.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
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
}
