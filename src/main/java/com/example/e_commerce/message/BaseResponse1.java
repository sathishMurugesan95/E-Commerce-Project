package com.example.e_commerce.message;

public abstract class BaseResponse1 {
	
	public int statuscode=200;
	public String description="Listed Succesfully" ;
	
	
	
	
	public BaseResponse1() {
	
	}
	
	public BaseResponse1(int statuscode, String description) {
	
		this.statuscode=statuscode;
		this.description=description;
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
