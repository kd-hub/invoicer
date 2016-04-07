package it.dobrowolski.invoicer.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.ModelAndView;

import it.dobrowolski.invoicer.exception.ProductNotFoundException;
import it.dobrowolski.invoicer.model.Product;
import it.dobrowolski.invoicer.model.VatRate;
import it.dobrowolski.invoicer.service.ProductService;
import it.dobrowolski.invoicer.service.VatRateService;

@Controller
public class ProductController {
	
	private ProductService productService;

	@Autowired(required = true)
	@Qualifier(value = "productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private VatRateService vatRateService;

	@Autowired(required = true)
	@Qualifier(value = "vatRateService")
	public void setVatRateService(VatRateService vatRateService) {
		this.vatRateService = vatRateService;
	}
	
	private Map<String,String> vatRatesListMap;
	
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public String product(Model model) {
		model.addAttribute("productsList", productService.listProducts());
		return "productsList";
	}
	
	@RequestMapping(value = "/product/add", method = RequestMethod.GET)
	public String addNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		vatRatesListMap = getVatRates();
		model.addAttribute("vatRatesList", vatRatesListMap);
		return "addProduct";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("vatRatesList", vatRatesListMap);
			return "addProduct";
		}
		productService.addProduct(newProduct);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/product/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", this.productService.getProductById(id));
		vatRatesListMap = getVatRates();
		model.addAttribute("vatRatesList", vatRatesListMap);
        return "editProduct";
    }
	
	@RequestMapping(value="/product/edit/{id}", method = RequestMethod.POST)
	public String processEditProductForm(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("vatRatesList", vatRatesListMap);
			return "editProduct";
		}
		productService.updateProduct(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/product/remove/{id}", method = RequestMethod.GET)
	public String removeProductForm(@PathVariable("id") int id, Model model) {
		model.addAttribute("product", this.productService.getProductById(id));
		model.addAttribute("invoicesListByProductId", productService.listInvoicesByProductId(id));
		System.out.println(productService.listInvoicesByProductId(id).size());
		return "removeProduct";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    binder.registerCustomEditor(VatRate.class, "vatRate", new PropertyEditorSupport() {
	    @Override
	    public void setAsText(String text) {
	        VatRate vatRate = vatRateService.getVatRateById(Integer.parseInt(text));
	        setValue(vatRate);
	    }
	    });
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.setViewName("productNotFound");
		return mav;
	}
	
	private LinkedHashMap<String,String> getVatRates() {
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		List<VatRate> vatRatesList = new ArrayList<VatRate>();
		vatRatesList = vatRateService.listVatRates();
		for (VatRate vatRate : vatRatesList) {
			map.put(Integer.toString(vatRate.getId()), (Integer.toString(vatRate.getVatRate())));
		}
		return map;
	}
}
