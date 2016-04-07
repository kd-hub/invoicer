package it.dobrowolski.invoicer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dobrowolski.invoicer.dao.VatRateDAO;
import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.model.VatRate;
import it.dobrowolski.invoicer.service.VatRateService;

@Service("vatRateService")
public class VatRateServiceImpl implements VatRateService {

	private VatRateDAO vatRateDAO;

	@Autowired
	public void setVatRateDAO(VatRateDAO vatRateDAO) {
		this.vatRateDAO = vatRateDAO;
	}

	@Transactional
	public void addVatRate(VatRate rate) {
		this.vatRateDAO.addVatRate(rate);
	}

	@Transactional
	public void removeVatRate(int id) {
		this.vatRateDAO.removeVatRate(id);
	}
	
	@Transactional
	public List<VatRate> listVatRates() {
		return this.vatRateDAO.listVatRates();
	}

	@Transactional
	public VatRate getVatRateById(int id) {
		return this.vatRateDAO.getVatRateById(id);
	}

	@Transactional
	public List<Product> listProductsByVatRateId(int id) {
		return this.vatRateDAO.getProductsByVatRateId(id);
	}
	
}
