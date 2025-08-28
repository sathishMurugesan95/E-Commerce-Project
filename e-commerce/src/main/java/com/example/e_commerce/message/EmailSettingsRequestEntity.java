package com.example.e_commerce.message;

public class EmailSettingsRequestEntity {

	
	private Integer emailsettingsId;
    private String emailid;
	private String password;
	private String smtpmailhostname;
	private String smtpport;
	
	  

	
	public EmailSettingsRequestEntity() {
		super();
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
