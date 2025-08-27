package com.example.e_commerce.message;

import com.example.e_commerce.Entity.EmailSettings;

public class EmailSettingsResponseEntity extends BaseResponse {

	
	private Integer emailsettingsId;
    private String emailid;
	private String password;
	private String smtpmailhostname;
	private String smtpport;
	
	
	
	
	
	
	public EmailSettingsResponseEntity() {
		super();
	}
	
	
	public EmailSettingsResponseEntity(EmailSettings emailSetting, EmailSettings emailSetting2) {
		  this.emailsettingsId = emailSetting.getEmailsettingsId();
		  this.emailid = emailSetting.getEmailid();
		  this.password = emailSetting.getPassword();
		  this.smtpmailhostname = emailSetting.getSmtpmailhostname();
		  this.smtpport = emailSetting.getSmtpport();
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
