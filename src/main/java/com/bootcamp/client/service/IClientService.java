package com.bootcamp.client.service;
import com.bootcamp.client.entity.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
	   Flux<Client> findAll();
	    Mono<Client> findById(String id);
	    Flux<Client> findPersonByIdTypeClient(String idTypeClient);
	    Flux<Client> findAllByRegistrationStatus(String registrationStatus);
	    Mono<Client> save(Client client);
	    Mono<Client> update(Client client);
	    void delete(String id);
}
