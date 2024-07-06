package it.bilancio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bilancio.dao.BilancioDao;
import it.bilancio.model.Bilancio;

@Service
public class BilancioServiceImpl implements BilancioService {
	
	@Autowired
	private BilancioDao bilancioDao;

	@Override
	public void registraOperazione(Bilancio bilancio) {
		bilancioDao.save(bilancio);
	}

	@Override
	public List<Bilancio> getOperazioni() {
		return (List<Bilancio>) bilancioDao.findAll();
	}

	@Override
	public Bilancio getOperazioneById(int id) {
		return bilancioDao.findById(id).get();
	}

	@Override
	public void cancellaOperazione(int id) {
		bilancioDao.deleteById(id);

	}
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
