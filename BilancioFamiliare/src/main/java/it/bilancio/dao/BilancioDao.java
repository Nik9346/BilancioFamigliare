package it.bilancio.dao;

import org.springframework.data.repository.CrudRepository;

import it.bilancio.model.Bilancio;

//interfaccia che estende crudRepository e ci permette di fare le operazioni CRUD
public interface BilancioDao extends CrudRepository<Bilancio, Integer>{

}
