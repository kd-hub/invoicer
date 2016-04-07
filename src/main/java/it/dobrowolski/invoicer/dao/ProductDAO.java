package it.dobrowolski.invoicer.dao;

import java.util.List;

import it.dobrowolski.invoicer.model.Invoice;
import it.dobrowolski.invoicer.model.Product;

public interface ProductDAO {
	
	public void addProduct(Product product);
	public void mergeProduct(Product product);
	public void removeProduct(int id);
	public void updateProduct(Product product);
	public List<Product> lisProducts();
	public Product getProductById(int id);
	public List<Invoice> getInvoicessByProductId(int id);
}
