package com.example.e_commerce.Entity;

import java.util.Optional;

import com.example.e_commerce.message.ListTypeValuesRequestEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;




@Entity
@Table(name = "USR_LIST_TYPE_VALUES")
public class ListTypeValues {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIST_TYPE_VALUE_ID", unique = true, nullable = false, updatable = false)
	private Integer listTypeValueId;

	@Column(name = "LIST_TYPE_VALUE_NAME")
	@Size(max = 100)
	private String listTypeValueName;

	@Column(name = "DESCRIPTION")
	@Size(max = 250)
	private String description;

	private Integer status;

	@ManyToOne
	@JoinColumn(name = "LIST_TYPE_ID") // use clean FK name
	private ListTypes listTypes;

	public ListTypeValues() {
		// TODO Auto-generated constructor stub
	}

	public ListTypeValues(Integer listTypeValueId2) {
		super();
		this.listTypeValueId = listTypeValueId2;
		System.out.println(this.listTypeValueId);
	}
//
//	
//    //from InOutLine Controller
//	public ListTypeValues(ListTypeValues listVal) {
//		this.listTypeValueId  = listVal.getListTypeValueId();
//		this.listTypeValueName = listVal.getListTypeValueName();
//		this.description = listVal.getDescription();
//		this.status = listVal.getStatus();
//		
//	}
//
//    //Create_API
	public ListTypeValues(ListTypeValuesRequestEntity listTypeValuesData) {
		this.listTypeValueId = listTypeValuesData.getListTypeValueId();
//		System.out.println(this.listTypeValueId);
		this.listTypeValueName = listTypeValuesData.getListTypeValueName();
//		System.out.println(this.listTypeValueName);
		this.description = listTypeValuesData.getDescription();
//		System.out.println(this.description);
		this.listTypes = listTypeValuesData.getListTypes();
		System.out.println(this.listTypes);
		this.setStatus(1);
	}

	//Update_API
	public ListTypeValues(Optional<ListTypeValues> list, ListTypeValuesRequestEntity listTypeValuesData) {
		
		this.listTypeValueId = list.get().getListTypeValueId();
		this.listTypeValueName = listTypeValuesData.getListTypeValueName();
		this.description = listTypeValuesData.getDescription();
		this.listTypes = listTypeValuesData.getListTypes();
		this.status = listTypeValuesData.getStatus();
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
