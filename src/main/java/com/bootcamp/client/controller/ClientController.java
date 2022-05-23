package com.bootcamp.client.controller;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.client.util.Constants;
import com.bootcamp.client.util.Shared;

import com.bootcamp.client.entity.Client;
import com.bootcamp.client.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ClientController  extends Shared{
	  @Autowired
	    IClientService service;

	    @GetMapping("/getClient")   
	    public Mono<ResponseEntity<Flux<Client>>> getCustomer(){
	       return Mono.just(ResponseEntity.ok()  
	    		   .contentType(MediaType.APPLICATION_JSON)
	    		   .body(service.findAll()));
	    		    
	    		   
	   }
	    
	    @GetMapping()   
	    public Mono<ResponseEntity<Flux<Client>>> getAllActives(){
	       return  Mono.just(ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(service.findAllByRegistrationStatus(Constants.ESTADO_VIGENTE))
					);
	    		   
	   }
	    

	    @GetMapping("/getCustomerByIdTypeClient/{idTypeClient}")
	    public Flux<Client> getCustomerByIdTypeClient(@PathVariable("idTypeClient") String idTypeClient){
	        return 	service.findPersonByIdTypeClient(idTypeClient);
	    }

	    @PostMapping("/postClient")
	    Mono<ResponseEntity<Client>> postCustomer(@RequestBody Client client){
	    	client.setRegistrationStatus(Constants.ESTADO_VIGENTE);
	    	client.setAudit(getBeanCreationParameters());
	        return service.save(client)
	        		.map(p-> ResponseEntity
	    					.created(URI.create(Constants.URL_PERSON.concat(p.getId())))
	    					.contentType(MediaType.APPLICATION_JSON)
	    					.body(p));
	        }

	   
	    @PutMapping("/update/{id}")
	   public Mono<ResponseEntity<Client>> updCustomer(@RequestBody Client client, @PathVariable String id){
	    	return service.findById(id).flatMap(p -> {
				p.setAccountableExecutive(client.getAccountableExecutive() );
				p.setIdAgency(client.getIdAgency() );
				p.setIdPerson(client.getIdPerson());
				p.setIdTypeClient(client.getIdTypeClient());
				p.setSpecialClientIndicator(client.getSpecialClientIndicator());				
				p.setAudit(getBeanModificationParameters(p.getAudit()));
				return service.save(p);
			}).map(p -> ResponseEntity.created(URI.create(Constants.URL_PERSON.concat(p.getId())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(p))
					.defaultIfEmpty(ResponseEntity.noContent().build());
	    
	    }

	    @DeleteMapping("/delete/{id}")
	    public Mono<ResponseEntity<Client>> delete(@PathVariable("id") String id){
	    	return service.findById(id).flatMap(p -> {
				p.setRegistrationStatus(Constants.ESTADO_NO_VIGENTE);
				p.setAudit(getBeanModificationParameters(p.getAudit()));
				return service.save(p);
			}).map(p -> ResponseEntity.created(URI.create(Constants.URL_PERSON.concat(p.getId())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(p))
					.defaultIfEmpty(ResponseEntity.noContent().build());
	    }
}
