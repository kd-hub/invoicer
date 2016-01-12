package it.dobrowolski.invoicer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dobrowolski.invoicer.dao.ProductDAO;
import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Transactional
	public void addProduct(Product product) {
		this.productDAO.addProduct(product);
	}

	@Transactional
	public void removeProduct(int id) {
		this.productDAO.removeProduct(id);
	}

	@Transactional
	public void updateProduct(Product product) {
		this.productDAO.updateProduct(product);
	}

	@Transactional
	public List<Product> listProducts() {
		return this.productDAO.lisProducts();
	}

	@Transactional
	public Product getProductById(int id) {
		return this.productDAO.getProductById(id);
	}
}
