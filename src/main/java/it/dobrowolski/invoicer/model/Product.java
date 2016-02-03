package it.dobrowolski.invoicer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "product")
@Proxy(lazy = false)
public class Product implements Serializable{

	private static final long serialVersionUID = -5466952822343374082L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "product_id")
	@NotNull
	private int id;

	@Column(name = "product_name")
	@Size(min = 2, max = 256, message = "{Product.productName.validation}")
	private String productName;

	@Column(name = "purchase_price")
	@Range(min = 0, max = 1000000, message = "{Product.purchasePrice.validation}")
	@NotNull(message = "{Product.purchasePrice.validation}")
	private BigDecimal purchasePrice;

	@Column(name = "selling_price")
	@Range(min = 0, max = 1000000, message = "{Product.sellingPrice.validation}")
	@NotNull(message = "{Product.sellingPrice.validation}")
	private BigDecimal sellingPrice;

	@Column(name = "stock_quantity")
	@Range(min = 0, max = 1000000, message = "{Product.stockQuantity.validation}")
	@NotNull(message = "{Product.stockQuantity.validation}")
	private Integer stockQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vat_rate_vat_rate_id")
	private VatRate vatRate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.product", cascade=CascadeType.ALL)
	private Set<InvoiceLine> invoiceLines = new HashSet<InvoiceLine>();

	@Transient
	private Integer itemSellingQuantity;
	
	@Transient 
	private BigDecimal itemSellingPrice;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public VatRate getVatRate() {
		return vatRate;
	}

	public void setVatRate(VatRate vatRate) {
		this.vatRate = vatRate;
	}

	public Set<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public Integer getItemSellingQuantity() {
		return itemSellingQuantity;
	}

	public void setItemSellingQuantity(Integer itemSellingQuantity) {
		this.itemSellingQuantity = itemSellingQuantity;
	}

	public BigDecimal getItemSellingPrice() {
		return itemSellingPrice;
	}

	public void setItemSellingPrice(BigDecimal itemSellingPrice) {
		this.itemSellingPrice = itemSellingPrice;
	}
	
}
