package it.dobrowolski.invoicer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "invoice")
@Proxy(lazy = false)
public class Invoice implements Serializable {

	private static final long serialVersionUID = -8522087462810611458L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	@NotNull
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_sale")
	@DateTimeFormat(pattern = "dd-MM-yyyy" )
	private Date dateOfSale;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_issue")
	@DateTimeFormat(pattern = "dd-MM-yyyy" )
	private Date dateOfIssue;

	@Temporal(TemporalType.DATE)
	@Column(name = "payment_due_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy" )
	private Date paymentDueDate;

	@Column(name = "total_amount_net")
	private BigDecimal totalAmountNet;

	@Column(name = "total_amount_gross")
	private BigDecimal totalAmountGross;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.invoice", cascade=CascadeType.ALL)
	private Set<InvoiceLine> invoiceLines = new HashSet<InvoiceLine>();

	@ManyToOne
	@JoinColumn(name = "customer_customer_id")
	private Customer customer;
	
	@Transient
	private Product selectedProduct;
	
	@Transient
	private Map<String, String> productsListMap;

	public Invoice() {
		this.setDateOfSale(new Date());
		this.setDateOfIssue(new Date());
		this.setPaymentDueDate(new Date());
		this.setTotalAmountNet(new BigDecimal(0.0));
		this.setTotalAmountGross(new BigDecimal(0.0));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public BigDecimal getTotalAmountNet() {
		return totalAmountNet;
	}

	public void setTotalAmountNet(BigDecimal totalAmountNet) {
		this.totalAmountNet = totalAmountNet;
	}

	public BigDecimal getTotalAmountGross() {
		return totalAmountGross;
	}

	public void setTotalAmountGross(BigDecimal totalAmountGross) {
		this.totalAmountGross = totalAmountGross;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Set<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public Map<String, String> getProductsListMap() {
		return productsListMap;
	}

	public void setProductsListMap(Map<String, String> productsListMap) {
		this.productsListMap = productsListMap;
	}

}
