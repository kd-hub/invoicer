package it.dobrowolski.invoicer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import it.dobrowolski.invoicer.dao.VatRateDAO;
import it.dobrowolski.invoicer.model.VatRate;

@Repository
public class VatRateDAOImpl implements VatRateDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void addVatRate(VatRate rate) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(rate);
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
		return vatRate;
	}

}
