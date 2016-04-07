package it.dobrowolski.invoicer.service;

import java.util.List;

import it.dobrowolski.invoicer.model.Customer;
import it.dobrowolski.invoicer.model.Invoice;

public interface CustomerService {

	public void addCustomer(Customer customer);
	public void removeCustomer(int id);
	public void updateCustomer(Customer customer);
	public List<Customer> listCustomers();
	public Customer getCustomerById(int id);
	public List<Invoice> listInvoicesByCustomerId(int id);
	
}
