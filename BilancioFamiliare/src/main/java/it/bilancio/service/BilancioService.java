package it.bilancio.service;

import java.util.List;

import it.bilancio.model.Bilancio;

public interface BilancioService {
	void registraOperazione(Bilancio bilancio);
	List<Bilancio> getOperazioni();
	Bilancio getOperazioneById(int id);
	void cancellaOperazione(int id);
	double getTotaleBilancio();

}
