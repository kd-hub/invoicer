package it.dobrowolski.invoicer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dobrowolski.invoicer.dao.CustomerDAO;
import it.dobrowolski.invoicer.model.Customer;

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
	

}
