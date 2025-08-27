package com.example.e_commerce.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PasswordResetOTP {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
        private String email;
	    private String otp;
	    private LocalDateTime expiryTime;
	    
	    
	    public PasswordResetOTP() {
	    	
	    }


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getOtp() {
			return otp;
		}


		public void setOtp(String otp) {
			this.otp = otp;
		}


		public LocalDateTime getExpiryTime() {
			return expiryTime;
		}


		public void setExpiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
		}
	    
	    

}
