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
//mappiamo la risposta alla richieste /operation
@RequestMapping("/operation")
public class OperationController {

	// istanziamo il servizio che ci permette di fare tutte le operazioni sul
	// database
	@Autowired
	BilancioService bilancioService;

	// rispondiamo alle richieste get ritornando la pagina
	@GetMapping
	public String getPage(Model model) {
		//istanzio il nuovo oggetto in classe bilancio e lo setto come attributo del model
		model.addAttribute("bilancio", new Bilancio());
		return "operation";
	}

	// alle richieste post, controlliamo la validità dei dati e la recuperiamo
	// formando un oggetto bilancio, se i dati contengono errori reindirizziamo alla
	// pagina del form per immettere nuovamente i dati, altrimenti registriamo
	// l'oggetto e quindi l'operazione
	@PostMapping
	String formManager(@Valid @ModelAttribute("bilancio") Bilancio bilancio, BindingResult result) {
		if (result.hasErrors())
			return "operation";
		bilancioService.registraOperazione(bilancio);
		//registrata l'operazione facciamo un redirect alla pagina principale dove viene visualizzata anche la nuova operazione
		return "redirect:/";
	}
}
