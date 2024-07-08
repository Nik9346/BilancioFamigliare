package it.bilancio.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bilancio.dao.BilancioDao;
import it.bilancio.model.Bilancio;

@Service
public class BilancioServiceImpl implements BilancioService {
	
	//collego l'interfaccia dao che ci permettere di fare Crud
	@Autowired
	private BilancioDao bilancioDao;

	//funzione utilizzata per registrare una nuova operazione passando un oggetto bilancio ricavato dal form
	@Override
	public void registraOperazione(Bilancio bilancio) {
		bilancioDao.save(bilancio);
	}
	
	//funzione utilizzata per recuperare l'intera lista delle operazioni presenti nel db
	@Override
	public List<Bilancio> getOperazioni() {
		List<Bilancio> bilancio = (List<Bilancio>) bilancioDao.findAll();
		//funzione utilizzata per ordinare le operazioni in base alla data
		Comparator<Bilancio> byDate = Comparator.comparing(Bilancio::getData).reversed();
		bilancio = bilancio.stream().sorted(byDate).toList();
		return bilancio;
	}
	//funzione utilizzata per recuperare un'operazione passato l'id
	@Override
	public Bilancio getOperazioneById(int id) {
		return bilancioDao.findById(id).get();
	}
	//funzione utilizzata per cancellare un'operazione passando l'id dell'operazione
	@Override
	public void cancellaOperazione(int id) {
		bilancioDao.deleteById(id);
	}
	//funzione utilizzata per calcolare il totale delle operazioni
	@Override
	public double getTotaleBilancio() {
		List<Bilancio> operazioni = getOperazioni();
		if(operazioni != null)
		{
			return operazioni.stream().mapToDouble(operazione -> operazione.getValore()).reduce(0.0, (p1,p2)-> p1+p2);
		}
		return 0;
	}

}
