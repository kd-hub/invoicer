package it.dobrowolski.invoicer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import it.dobrowolski.invoicer.dao.VatRateDAO;
import it.dobrowolski.invoicer.exception.VatRateNotFoundException;
import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.model.VatRate;

@Repository
public class VatRateDAOImpl implements VatRateDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addVatRate(VatRate rate) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(rate);
	}

	public void removeVatRate(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		VatRate vatRate = (VatRate) session.get(VatRate.class, new Integer(id));
		if (vatRate != null) {
			session.delete(vatRate);
		} else {
			throw new VatRateNotFoundException(id);
		}
	}

	@SuppressWarnings("unchecked")
	public List<VatRate> listVatRates() {
		Session session = this.sessionFactory.getCurrentSession();
		List<VatRate> vatRatesList = session.createQuery("from VatRate").list();
		return vatRatesList;
	}

	public VatRate getVatRateById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		VatRate vatRate = (VatRate) session.get(VatRate.class, new Integer(id));
		if (vatRate != null) {
			return vatRate;
		} else {
			throw new VatRateNotFoundException(id);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByVatRateId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> productsByVatRateIdList = session.createQuery("from Product where vat_rate_vat_rate_id = :id")
				.setParameter("id", id).list();
		return productsByVatRateIdList;
	}

}
