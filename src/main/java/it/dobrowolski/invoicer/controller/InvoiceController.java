package it.dobrowolski.invoicer.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import it.dobrowolski.invoicer.exception.InvoiceNotFoundException;
import it.dobrowolski.invoicer.model.Customer;
import it.dobrowolski.invoicer.model.Invoice;
import it.dobrowolski.invoicer.model.InvoiceLine;
import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.service.CustomerService;
import it.dobrowolski.invoicer.service.InvoiceService;
import it.dobrowolski.invoicer.service.ProductService;

@Controller
@SessionAttributes("newInvoice")
public class InvoiceController {

	private InvoiceService invoiceService;

	@Autowired(required = true)
	@Qualifier(value = "invoiceService")
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	private ProductService productService;

	@Autowired(required = true)
	@Qualifier(value = "productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private CustomerService customerService;

	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private Map<String, String> customersListMap;

	@RequestMapping(value = "/invoice/list", method = RequestMethod.GET)
	public String product(Model model) {
		model.addAttribute("invoicesList", invoiceService.listInvoices());
		return "invoicesList";
	}
	
	@RequestMapping(value="/invoice/view/{id}", method = RequestMethod.GET)
    public String viewInvoice(@PathVariable("id") int id, Model model) {
		model.addAttribute("invoice", this.invoiceService.getInvoiceById(id));	
        return "viewInvoice";
	}

	@RequestMapping(value = "/invoice/add", method = RequestMethod.GET)
	public String addNewInvoiceForm(Model model) {
		Invoice newInvoice = new Invoice();
		newInvoice.setProductsListMap(getProducts());
		model.addAttribute("newInvoice", newInvoice);
		customersListMap = getCustomers();
		model.addAttribute("customersList", customersListMap);
		boolean noCustomerError = false;
		if (customersListMap.size() == 0) {
			noCustomerError = true;
		}
		model.addAttribute("noCustomerError", noCustomerError);
		boolean noProductError = false;
		if (getProducts().size() == 0) {
			noProductError = true;
		}
		model.addAttribute("noProductError", noProductError);
		return "addInvoice";
	}

	@RequestMapping(value = "/invoice/add", method = RequestMethod.POST)
	public String processAddNewInvoiceForm(@ModelAttribute("newInvoice") @Valid Invoice newInvoice,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("customersList", customersListMap);
			return "addInvoice";
		}
		return "redirect:/invoice/add/items";
	}

	@RequestMapping(value = "/invoice/add/items", method = RequestMethod.GET)
	public String addNewInvoiceItemForm(@ModelAttribute("newInvoice") @Valid Invoice newInvoice, Model model,
			HttpServletRequest request) {
		if (!model.containsAttribute("newInvoice")) {
			return "redirect:/invoice/add/";
		}
		model.addAttribute("productsList", newInvoice.getProductsListMap());
		return "addInvoiceItem";
	}

	@RequestMapping(value = "/invoice/add/items", method = RequestMethod.POST)
	public String processAddNewInvoiceItemForm(@ModelAttribute("newInvoice") @Valid Invoice newInvoice,
			BindingResult result, Model model) {
		if (result.hasErrors() || !productService.validateSelectedProduct(newInvoice.getSelectedProduct(), result)) {
			model.addAttribute("productsList", newInvoice.getProductsListMap());
			return "addInvoiceItem";
		}
		InvoiceLine invoiceLine = new InvoiceLine();
		invoiceLine.setProduct(newInvoice.getSelectedProduct());
		invoiceLine.setInvoice(newInvoice);
		invoiceLine.setQuantity(newInvoice.getSelectedProduct().getItemSellingQuantity());
		invoiceLine.setVatRate(newInvoice.getSelectedProduct().getVatRate().getVatRate());
		invoiceLine.setPrice(newInvoice.getSelectedProduct().getItemSellingPrice());
		invoiceLine.calculateLineValues();
		newInvoice.setTotalAmountNet(newInvoice.getTotalAmountNet().add(invoiceLine.getNetValue()));
		newInvoice.setTotalAmountGross(newInvoice.getTotalAmountGross().add(invoiceLine.getGrossValue()));
		newInvoice.getInvoiceLines().add(invoiceLine);
		newInvoice.getProductsListMap().remove(Integer.toString(newInvoice.getSelectedProduct().getId()));
		newInvoice.setSelectedProduct(null);
		model.addAttribute("newInvoice", newInvoice);
		return "redirect:/invoice/add/items";
	}

	@RequestMapping(value = "/invoice/add/comlete", method = RequestMethod.POST)
	public String processAddNewInvoiceFormComplete(@ModelAttribute("newInvoice") @Valid Invoice newInvoice,
			BindingResult result, Model model) {
		if (newInvoice.getInvoiceLines().size() > 0) {
			if(invoiceService.addInvoice(newInvoice)) {
				int id = newInvoice.getId();
				model.addAttribute("invoice", this.invoiceService.getInvoiceById(id));
				model.addAttribute("invoiceAddSuccess", true);
		        return "viewInvoice";
			} else {
				newInvoice.getInvoiceLines().clear();
				model.addAttribute("newInvoice", newInvoice);
				newInvoice.setProductsListMap(getProducts());
				model.addAttribute("productsList", newInvoice.getProductsListMap());
				model.addAttribute("invoiceAddErrors", true);
				return "addInvoiceItem";
			}
		} else {
			return "redirect:/invoice/add/items";
		}
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Customer.class, "customer", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Customer customer = customerService.getCustomerById(Integer.parseInt(text));
				setValue(customer);
			}
		});
		binder.registerCustomEditor(Product.class, "selectedProduct", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Product selectedProduct = productService.getProductById(Integer.parseInt(text));
				setValue(selectedProduct);
			}
		});
	}

	@ExceptionHandler(InvoiceNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, InvoiceNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidInvoiceId", exception.getInvoiceId());
		mav.addObject("exception", exception);
		mav.setViewName("invoiceNotFound");
		return mav;
	}

	private LinkedHashMap<String, String> getCustomers() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		List<Customer> customersList = new ArrayList<Customer>();
		customersList = customerService.listCustomers();
		for (Customer customer : customersList) {
			map.put(Integer.toString(customer.getId()),
					(customer.getCompanyName() + " (" + customer.getVatIdNumber() + ")"));
		}
		return map;
	}

	private LinkedHashMap<String, String> getProducts() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		List<Product> productsList = new ArrayList<Product>();
		productsList = productService.listProducts();
		for (Product product : productsList) {
			map.put(Integer.toString(product.getId()), (product.getProductName() + " (cena zakupu: "
					+ product.getPurchasePrice() + " / magazyn: " + product.getStockQuantity() + " szt.)"));
		}
		return map;
	}

}
