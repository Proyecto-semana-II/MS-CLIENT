package com.bootcamp.client.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bootcamp.client.bean.AuditBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document

public class Client {
	  @Id
	    private String id;
	    private String idPerson;
	    private String idTypeClient;
	    private String idAgency;
	    private String specialClientIndicator;
	    private String accountableExecutive;
	    private String registrationStatus;	
		private AuditBean audit;
}
