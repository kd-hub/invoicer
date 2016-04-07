package it.dobrowolski.invoicer.service;

import java.util.List;

import org.springframework.validation.Errors;

import it.dobrowolski.invoicer.model.Invoice;
import it.dobrowolski.invoicer.model.Product;

public interface ProductService {
	
	public void addProduct(Product product);
	public void removeProduct(int id);
	public void updateProduct(Product product);
	public boolean validateSelectedProduct(Product product, Errors errors);
	public List<Product> listProducts();
	public Product getProductById(int id);
	public List<Invoice> listInvoicesByProductId(int id);
}
