package it.dobrowolski.invoicer.dao;

import java.util.List;

import it.dobrowolski.invoicer.model.Customer;

public interface CustomerDAO {
	
	public List<Customer> listCustomers();
}
