package it.dobrowolski.invoicer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.dobrowolski.invoicer.exception.CustomerNotFoundException;
import it.dobrowolski.invoicer.model.Customer;
import it.dobrowolski.invoicer.service.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;

	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public String customer(Model model) {
		model.addAttribute("customersList", customerService.listCustomers());
		return "customersList";
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.GET)
	public String addNewCustomerForm(Model model) {
		Customer newCustomer = new Customer();
		model.addAttribute("newCustomer", newCustomer);
		return "addCustomer";
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST)
	public String processAddNewCustomerForm(@ModelAttribute("newCustomer") @Valid Customer newCustomer,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addCustomer";
		}
		customerService.addCustomer(newCustomer);
		return "redirect:/customer/list";
	}
	
	@RequestMapping(value="/customer/edit/{id}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable("id") int id, Model model){
        model.addAttribute("customer", this.customerService.getCustomerById(id));
        return "editCustomer";
    }
	
	@RequestMapping(value="/customer/edit/{id}", method = RequestMethod.POST)
	public String processEditCustomerForm(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
		if (result.hasErrors()) {
			return "editCustomer";
		}
		customerService.updateCustomer(customer);
		return "redirect:/customer/list";
	}

	@RequestMapping("/customer/remove/{id}")
	public String removeCustomer(@PathVariable("id") int id) {
		this.customerService.removeCustomer(id);
		return "redirect:/customer/list";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {

	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, CustomerNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidCustomerId", exception.getCustomerId());
		mav.addObject("exception", exception);
		mav.setViewName("customerNotFound");
		return mav;
	}

}
