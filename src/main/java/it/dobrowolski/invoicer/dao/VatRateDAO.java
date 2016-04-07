package it.dobrowolski.invoicer.dao;

import java.util.List;

import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.model.VatRate;

public interface VatRateDAO {

	public void addVatRate(VatRate rate);
	public void removeVatRate(int id);
	public List<VatRate> listVatRates();
	public VatRate getVatRateById(int id);
	public List<Product> getProductsByVatRateId(int id);
	
}
