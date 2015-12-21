package it.dobrowolski.invoicer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.dobrowolski.invoicer.model.Customer;
import it.dobrowolski.invoicer.service.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;

	@Autowired(required=true)
	@Qualifier(value="customerService")
	public void setCustomerService(CustomerService cs){
		this.customerService = cs;
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String welcome(Model model) {
		//model.addAttribute("customer", new Customer());
		model.addAttribute("customersList", this.customerService.listCustomers());
		//System.out.println(this.customerService.listCustomers().size());
		return "customer";
	}
}
