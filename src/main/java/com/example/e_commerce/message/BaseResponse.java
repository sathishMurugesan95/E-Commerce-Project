package com.example.e_commerce.message;

public abstract class BaseResponse {

	
	public static final int SUCCESS = 200;
	public static final int FAILURE = 400;
	private Integer statusCode;
	private String description;
	private Integer errorCode;
	private String message;
	
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static int getSuccess() {
		return SUCCESS;
	}
	public static int getFailure() {
		return FAILURE;
	}
	
	
	
	
}
