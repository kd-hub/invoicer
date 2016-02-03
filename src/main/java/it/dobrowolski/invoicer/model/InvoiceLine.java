package it.dobrowolski.invoicer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "invoice_line_item")
@Proxy(lazy = false)
@AssociationOverrides({ @AssociationOverride(name = "pk.invoice", joinColumns = @JoinColumn(name = "invoice_invoice_id", nullable = false) ),
		@AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_product_id", nullable = false) ) })
public class InvoiceLine implements Serializable{

	private static final long serialVersionUID = -3567682409658812280L;

	private InvoiceLineId pk = new InvoiceLineId();
	
	@EmbeddedId
	public InvoiceLineId getPk() {
		return pk;
	}
	public void setPk(InvoiceLineId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Invoice getInvoice() {
		return getPk().getInvoice();
	}
	
	public void setInvoice(Invoice invoice) {
		getPk().setInvoice(invoice);
	}
	
	@Transient
	public Product getProduct() {
		return getPk().getProduct();
	}
	
	public void setProduct(Product product) {
		getPk().setProduct(product);
	}

	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "vatRate")
	private Integer vatRate;
	
	@Column(name = "netValue")
	private BigDecimal netValue;
	
	@Column(name = "vatAmount")
	private BigDecimal vatAmount;
	
	@Column(name = "grossValue")
	private BigDecimal grossValue;

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getVatRate() {
		return vatRate;
	}
	public void setVatRate(Integer vatRate) {
		this.vatRate = vatRate;
	}
	public BigDecimal getNetValue() {
		return netValue;
	}
	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}
	public BigDecimal getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
	public BigDecimal getGrossValue() {
		return grossValue;
	}
	public void setGrossValue(BigDecimal grossValue) {
		this.grossValue = grossValue;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		InvoiceLine other = (InvoiceLine) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public void calculateLineValues() {
		setNetValue(this.price.multiply(new BigDecimal(this.quantity)));
		double vat = (double)this.vatRate / 100;
		setVatAmount(this.netValue.multiply(new BigDecimal(vat)));
		setGrossValue(this.netValue.add(vatAmount));
	}
	
	
}
