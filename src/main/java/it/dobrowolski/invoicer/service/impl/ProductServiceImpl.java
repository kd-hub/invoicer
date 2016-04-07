package it.dobrowolski.invoicer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import it.dobrowolski.invoicer.dao.ProductDAO;
import it.dobrowolski.invoicer.model.Invoice;
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
	
	@Transactional
	public List<Invoice> listInvoicesByProductId(int id) {
		return this.productDAO.getInvoicessByProductId(id);
	}

	public boolean validateSelectedProduct(Product product, Errors errors) {
		if (product.getItemSellingPrice() == null) {
			errors.rejectValue("selectedProduct.itemSellingPrice", "Product.itemSellingPrice.notnull");
		}
		if (product.getItemSellingQuantity() == null) {
			errors.rejectValue("selectedProduct.itemSellingQuantity", "Product.itemSellingQuantity.notnull");
		}
		if (!errors.getAllErrors().isEmpty()) {
			return false;	
		}
		if (product.getItemSellingPrice().compareTo(product.getPurchasePrice()) < 0) {
			errors.rejectValue("selectedProduct.itemSellingPrice", "Product.itemSellingPrice.error");
		}
		if (product.getItemSellingQuantity().compareTo(product.getStockQuantity()) > 0) {
			errors.rejectValue("selectedProduct.itemSellingQuantity", "Product.itemSellingQuantity.error");
		}
		if (!errors.getAllErrors().isEmpty()) {
			return false;	
		} else {
			return true;			
		}
	}

}
