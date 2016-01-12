package it.dobrowolski.invoicer.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4127658435289447129L;
	private int customerId;

	public CustomerNotFoundException(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

}
