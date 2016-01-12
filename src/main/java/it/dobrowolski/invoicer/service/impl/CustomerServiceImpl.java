package it.dobrowolski.invoicer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dobrowolski.invoicer.dao.CustomerDAO;
import it.dobrowolski.invoicer.model.Customer;
import it.dobrowolski.invoicer.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO;

	@Autowired
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Transactional
	public List<Customer> listCustomers() {
		return this.customerDAO.listCustomers();
	}

	@Transactional
	public void addCustomer(Customer customer) {
		this.customerDAO.addCustmer(customer);
	}

	@Transactional
	public void removeCustomer(int id) {
		this.customerDAO.removeCustomer(id);
	}

	@Transactional
	public void updateCustomer(Customer customer) {
		this.customerDAO.updateCustomer(customer);
	}

	@Transactional
	public Customer getCustomerById(int id) {
		return this.customerDAO.getCustomerById(id);
	}

}
