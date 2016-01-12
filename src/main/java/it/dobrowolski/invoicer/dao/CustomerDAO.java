package it.dobrowolski.invoicer.dao;

import java.util.List;

import it.dobrowolski.invoicer.model.Customer;

public interface CustomerDAO {
	
	public void addCustmer(Customer customer);
	public void removeCustomer(int id);
	public void updateCustomer(Customer customer);
	public List<Customer> listCustomers();
	public Customer getCustomerById(int id);
}
