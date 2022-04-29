package com.bootcamp.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.client.entity.Client;

import reactor.core.publisher.Flux;

@Repository
public interface IClientRepository  extends ReactiveMongoRepository<Client,String>{
	  Flux<Client>  findPersonByIdTypeClient(String idTypeClient);
	    Flux<Client> findAllByRegistrationStatus(String registrationStatus);
}
