package it.bilancio.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


import it.bilancio.model.Bilancio;
import it.bilancio.service.BilancioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/operation")
public class OperationController {

	@Autowired
	BilancioService bilancioService;
	
	@GetMapping
	public String getPage(Model model) {
		model.addAttribute("bilancio", new Bilancio());
		return "operation";
	}

	@PostMapping String formManager(@Valid @ModelAttribute("bilancio") Bilancio bilancio, 
			BindingResult result, Model model)
	{
		if(result.hasErrors())
			return "operation";
		bilancioService.registraOperazione(bilancio);
		return "redirect:/";
	}
		
	
}
