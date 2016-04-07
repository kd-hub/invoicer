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

import it.dobrowolski.invoicer.exception.VatRateNotFoundException;
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
		return "vatratesList";
	}

	@RequestMapping(value = "/vatrate/remove/{id}", method = RequestMethod.GET)
	public String removeVatRateForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("vatRate", this.vatRateService.getVatRateById(id));
		model.addAttribute("productsListByVatRateId", vatRateService.listProductsByVatRateId(id));
		return "removeVatRate";
	}

	@RequestMapping(value = "/vatrate/remove/{id}", method = RequestMethod.POST)
	public String processremoveVatRateForm(@ModelAttribute("vatRate") @Valid VatRate vatRate, BindingResult result) {
		vatRateService.removeVatRate(vatRate.getId());
		return "redirect:/vatrate/list";
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
			System.out.println(result.getFieldErrors());
			return "addVatRate";
		}
		vatRateService.addVatRate(newVatRate);
		return "redirect:/vatrate/list";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {

	}

	@ExceptionHandler(VatRateNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, VatRateNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidVatRateId", exception.getVatRateId());
		mav.addObject("exception", exception);
		mav.setViewName("vatRateNotFound");
		return mav;
	}
}
