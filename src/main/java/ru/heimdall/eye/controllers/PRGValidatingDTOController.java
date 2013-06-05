package ru.heimdall.eye.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.heimdall.eye.domain.Address;

@Controller
public class PRGValidatingDTOController {

	@RequestMapping(value="/validation/address.html", method=RequestMethod.GET)
	public String getAddress() {
	
		return "validation/address";
	}
	
	@RequestMapping(value="/validation/address.html", method=RequestMethod.POST)
	public String postAddress(RedirectAttributes flash, @Valid @ModelAttribute("address") Address address, BindingResult result) {

		String view = null;
		if ( result.hasErrors() ) {
			view = "validation/address";
		} else {
			flash.addFlashAttribute("success", "yay it worked!");
			view = "redirect:/validation/address.html";
		}
		
		return view;
		
	}

}
