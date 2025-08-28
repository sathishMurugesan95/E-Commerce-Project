package com.example.e_commerce.message;

import com.example.e_commerce.Entity.ListTypeValues;

public class ListTypeValuesResponseEntity extends BaseResponse{
	
	private Integer listTypeValueId;
	private String listTypeValueName;
	private String description;

	private Integer status;
	

	public ListTypeValuesResponseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ListTypeValuesResponseEntity(ListTypeValues l) {
		super();
		this.listTypeValueId = l.getListTypeValueId();
//		System.out.println("this.listTypeValueId:"+this.listTypeValueId);
		this.listTypeValueName = l.getListTypeValueName();
//		System.out.println("this.listTypeValueName:"+this.listTypeValueName);
		this.description = l.getDescription();
//		System.out.println("this.description:"+this.description);
		this.status = l.getStatus();
//		System.out.println("this.status:"+this.status);
	}



	


	public Integer getListTypeValueId() {
		return listTypeValueId;
	}

	public void setListTypeValueId(Integer listTypeValueId) {
		this.listTypeValueId = listTypeValueId;
	}

	public String getListTypeValueName() {
		return listTypeValueName;
	}

	public void setListTypeValueName(String listTypeValueName) {
		this.listTypeValueName = listTypeValueName;
	}

	public String getDescription() {
		return description;
	}
	
	
	

//	public String setDescription(String description) {
//		return this.description = description;
//	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
