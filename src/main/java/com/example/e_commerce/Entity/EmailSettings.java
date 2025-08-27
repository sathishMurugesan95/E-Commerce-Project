package com.example.e_commerce.Entity;

import java.io.Serializable;

import com.example.e_commerce.message.EmailSettingsRequestEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;





@Entity
@Table(name = "ORG_EMAILSETTINGS")
public class EmailSettings  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@Column(name = "ID",unique = true , nullable = false, updatable = false)
	private Integer emailsettingsId;
	
	
	      @Column
         private String emailid;
	     private String password;
	     private String smtpmailhostname;
	     private String smtpport;
	     
	     
	     
	     
		public EmailSettings() {
			super();
		}
		
		public EmailSettings(EmailSettingsRequestEntity emailSettingsRequestEntity) {
			  this.emailsettingsId = emailSettingsRequestEntity.getEmailsettingsId();
			  this.emailid = emailSettingsRequestEntity.getEmailid();
			  this.password = emailSettingsRequestEntity.getPassword();
			  this.smtpmailhostname = emailSettingsRequestEntity.getSmtpmailhostname();
			  this.smtpport = emailSettingsRequestEntity.getSmtpport();
		}

		public Integer getEmailsettingsId() {
			return emailsettingsId;
		}
		public void setEmailsettingsId(Integer emailsettingsId) {
			this.emailsettingsId = emailsettingsId;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getSmtpmailhostname() {
			return smtpmailhostname;
		}
		public void setSmtpmailhostname(String smtpmailhostname) {
			this.smtpmailhostname = smtpmailhostname;
		}
		public String getSmtpport() {
			return smtpport;
		}
		public void setSmtpport(String smtpport) {
			this.smtpport = smtpport;
		}
	     
	     
	     
	
}
