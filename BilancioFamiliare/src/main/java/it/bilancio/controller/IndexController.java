package it.bilancio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.bilancio.model.Bilancio;
import it.bilancio.service.BilancioService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private BilancioService bilancioService;
	
	@GetMapping()
	public String getPage(Model model, @RequestParam(name="id", required = false) Integer id) {
		List<Bilancio> operazioni = bilancioService.getOperazioni();
		Bilancio operazione = id == null ? new Bilancio() : bilancioService.getOperazioneById(id);
		model.addAttribute("operazioni", operazioni);
		model.addAttribute("operazione",operazione);
		model.addAttribute("totale",bilancioService.getTotaleBilancio());
		return "index";
	}
	
	@GetMapping("/elimina")
	public String eliminaOperazione(Model model, @RequestParam(name = "id", required = true) int id) {
		bilancioService.cancellaOperazione(id);
		return "redirect:/";
	}
	
}
