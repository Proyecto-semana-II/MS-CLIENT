package com.bootcamp.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bootcamp.client.entity.Client;
import com.bootcamp.client.repository.IClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements IClientService{
	 @Autowired
	 IClientRepository repository;

	    @Override
	    public Flux<Client> findAll() {        
	        return repository.findAll();
	    }

	    @Override
		public Mono<Client> findById(String id) {
			return repository.findById(id);
		}
	    
	    @Override
	    public Mono<Client> save(Client client) {
	        return repository.save(client);
	    }
	   
	    @Override
	    public Flux<Client> findPersonByIdTypeClient(String idTypeClient) {
	        
	        return repository.findPersonByIdTypeClient(idTypeClient);
	    }

	    @Override
	    public Flux<Client> findAllByRegistrationStatus(String registrationStatus)
	    {
	    	return repository.findAllByRegistrationStatus(registrationStatus);
	    }
	    @Override
	    public Mono<Client> update(Client client) {
	       
	        return repository.save(client);
	    }

	    @Override
	      public void delete(String id) {
	       
	         repository.deleteById(id).subscribe();
	    }
}
