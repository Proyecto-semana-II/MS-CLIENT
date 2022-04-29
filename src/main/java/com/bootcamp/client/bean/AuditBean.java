package com.bootcamp.client.bean;

import java.util.Date;
import lombok.Data;

@Data
public class AuditBean {
	private String creationUser;
	private Date creationDate;
	private String creationTerminal;
	private String modificationUser;
	private Date modificationDate;
	private String modificationTerminal;
}
