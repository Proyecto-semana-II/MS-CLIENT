

package com.bootcamp.client.util;
import java.net.InetAddress;
import java.util.Date;

import com.bootcamp.client.bean.AuditBean;

public class Shared {
	protected AuditBean getBeanCreationParameters() {

		AuditBean auditBean = new AuditBean();

		try {
			auditBean.setCreationUser(InetAddress.getLocalHost().getHostName());
			auditBean.setCreationTerminal(InetAddress.getLocalHost().getHostAddress());
			auditBean.setCreationDate(new Date());

		} catch (Exception e) {
			auditBean = new AuditBean();
			auditBean.setCreationUser("ADMIN");
			auditBean.setCreationTerminal("0.0.0.0");
			auditBean.setCreationDate(new Date());
		}

		return auditBean;
	}

	protected AuditBean getBeanModificationParameters(AuditBean auditBean) {

		try {
			auditBean.setModificationUser(InetAddress.getLocalHost().getHostName());
			auditBean.setModificationTerminal(InetAddress.getLocalHost().getHostAddress());
			auditBean.setModificationDate(new Date());

		} catch (Exception e) {
			auditBean = new AuditBean();
			auditBean.setModificationUser("ADMIN");
			auditBean.setModificationTerminal("0.0.0.0");
			auditBean.setModificationDate(new Date());
		}

		return auditBean;
	}
}
