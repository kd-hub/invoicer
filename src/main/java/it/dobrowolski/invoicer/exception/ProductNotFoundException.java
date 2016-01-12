package it.dobrowolski.invoicer.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5911572728791274888L;
	private int productId;
	
	public ProductNotFoundException(int productId) {
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}
}
