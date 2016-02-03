package it.dobrowolski.invoicer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "customer")
@Proxy(lazy = false)
public class Customer implements Serializable {

	private static final long serialVersionUID = 797159961564980833L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	@NotNull
	private int id;

	@Column(name = "vat_id_number")
	@Size(min = 8, max = 16, message = "{Customer.vatIdNumber.validation}")
	private String vatIdNumber;

	@Column(name = "company_name")
	@Size(min = 2, max = 256, message = "{Customer.companyName.validation}")
	private String companyName;

	@Column(name = "street_name")
	@Size(min = 2, max = 256, message = "{Customer.streetName.validation}")
	private String streetName;

	@Column(name = "street_number")
	@Size(min = 1, max = 8, message = "{Customer.streetNumber.validation}")
	private String streetNumber;

	@Column(name = "apartment_number")
	@Size(max = 8, message = "{Customer.apartmentNumber.validation}")
	private String apartmentNumber;

	@Column(name = "postal_code")
	@Size(min = 5, max = 8, message = "{Customer.postalCode.validation}")
	private String postalCode;

	@Column(name = "city_name")
	@Size(min = 2, max = 256, message = "{Customer.cityName.validation}")
	private String cityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVatIdNumber() {
		return vatIdNumber;
	}

	public void setVatIdNumber(String vatIdNumber) {
		this.vatIdNumber = vatIdNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
