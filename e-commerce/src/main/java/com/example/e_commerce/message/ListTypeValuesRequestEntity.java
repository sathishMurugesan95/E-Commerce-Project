package com.example.e_commerce.message;

import com.example.e_commerce.Entity.ListTypes;

public class ListTypeValuesRequestEntity {
	
	private Integer listTypeValueId;
	private String listTypeValueName;
	private String description;

	private Integer status;
	
	private ListTypes listTypes;

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

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ListTypes getListTypes() {
		return listTypes;
	}

	public void setListTypes(ListTypes listTypes) {
		this.listTypes = listTypes;
	}

}
