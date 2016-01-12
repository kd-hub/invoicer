package it.dobrowolski.invoicer.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "vat_rate")
@Proxy(lazy = false)
public class VatRate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vat_rate_id")
	@NotNull
	private Integer id;

	@Column(name = "rate")
	@Range(min = 0, max = 30, message = "{VatRate.vatRate.validation}")
	@NotNull(message = "{VatRate.vatRate.validation}")
	private Integer vatRate;

	@OneToMany(mappedBy="vatRate")
	private Set<Product> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVatRate() {
		return vatRate;
	}

	public void setVatRate(Integer vatRate) {
		this.vatRate = vatRate;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
