package it.bilancio.dao;

import org.springframework.data.repository.CrudRepository;

import it.bilancio.model.Bilancio;

public interface BilancioDao extends CrudRepository<Bilancio, Integer>{

}
