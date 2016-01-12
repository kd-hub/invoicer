package it.dobrowolski.invoicer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import it.dobrowolski.invoicer.dao.CustomerDAO;
import it.dobrowolski.invoicer.exception.CustomerNotFoundException;
import it.dobrowolski.invoicer.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> listCustomers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customersList = session.createQuery("from Customer").list();
		return customersList;
	}

	public void addCustmer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer);	
	}

	public void removeCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, new Integer(id));
		if (customer != null) {
			session.delete(customer);
		} else {
			throw new CustomerNotFoundException(id);
		}
	}

	public void updateCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer);
		
	}

	public Customer getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, new Integer(id));
		if (customer == null) {
			throw new CustomerNotFoundException(id);
		}
		return customer;
	}

}
