package it.dobrowolski.invoicer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.dobrowolski.invoicer.model.VatRate;
import it.dobrowolski.invoicer.service.VatRateService;

@Controller
public class VatRateController {

	private VatRateService vatRateService;

	@Autowired(required = true)
	@Qualifier(value = "vatRateService")
	public void setVatRateService(VatRateService vatRateService) {
		this.vatRateService = vatRateService;
	}

	@RequestMapping(value = "/vatrate/list", method = RequestMethod.GET)
	public String vatrate(Model model) {
		model.addAttribute("vatRateList", vatRateService.listVatRates());
		return "vatrate";
	}
	
	@RequestMapping(value = "/vatrate/add", method = RequestMethod.GET)
	public String addNewVatRateForm(Model model) {
		VatRate newVatRate = new VatRate();
		model.addAttribute("newVatRate", newVatRate);
		return "addVatRate";
	}

	@RequestMapping(value = "/vatrate/add", method = RequestMethod.POST)
	public String processAddNewVatRateForm(@ModelAttribute("newVatRate") @Valid VatRate newVatRate,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addVatRate";
		}
		vatRateService.addVatRate(newVatRate);
		return "redirect:/vatrate/list";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {

	}
}
