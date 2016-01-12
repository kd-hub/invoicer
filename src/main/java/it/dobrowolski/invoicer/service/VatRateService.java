package it.dobrowolski.invoicer.service;

import java.util.List;

import it.dobrowolski.invoicer.model.VatRate;

public interface VatRateService {

	public void addVatRate(VatRate rate);
	public VatRate getVatRateById(int id);
	public List<VatRate> listVatRates();
}
