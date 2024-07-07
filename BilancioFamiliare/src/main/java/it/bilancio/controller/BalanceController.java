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
public class BalanceController {

	//istanziamo il servizio che ci permette di fare CRUD
	@Autowired
	private BilancioService bilancioService;
	
	//rispondiamo alle richieste get visualizzando la pagina dopo aver recuperato e assegnato tutti i dati delle operazione dal db
	@GetMapping()
	public String getPage(Model model, @RequestParam(name="id", required = false) Integer id) {
		//recuperiamo l'intera lista delle operazioni facendo una chiamata al db
		List<Bilancio> operazioni = bilancioService.getOperazioni();
		//settiamo gli attributi del model con i dati delle variabili operazioni e operazione
		model.addAttribute("operazioni", operazioni);
		//assegniamo al model la variabile totale recuperando dal service il totale delle operazioni
		model.addAttribute("totale",bilancioService.getTotaleBilancio());
		//passiamo il tutto alla pagina balance
		return "balance";
	}
	
	//funzione utilizzata per eliminare un'operazione
	@GetMapping("/elimina")
	public String eliminaOperazione(Model model, @RequestParam(name = "id", required = true) int id) {
		
		bilancioService.cancellaOperazione(id);
		//facciamo un reindirizzamento alla pagina principale dopo aver cancellato l'operazione per aggiornare la pagina
		return "redirect:/";
	}
	
}
