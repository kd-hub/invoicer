package it.dobrowolski.invoicer.service;

import java.util.List;

import it.dobrowolski.invoicer.model.Product;

public interface ProductService {
	
	public void addProduct(Product product);
	public void removeProduct(int id);
	public void updateProduct(Product product);
	public List<Product> listProducts();
	public Product getProductById(int id);
}
