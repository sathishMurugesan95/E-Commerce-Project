package com.example.e_commerce.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "USR_LIST_TYPES")
public class ListTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIST_TYPE_ID", unique = true , nullable = false, updatable = false)
	private Integer listTypeId;
	
	@Column(name = "LIST_TYPE_NAME")
	@Size(max = 100)
	private String listTypeName;
	
	@Column(name = "DESCRIPTION")
	@Size(max = 250)
	private String description;

	@Column(name = "STATUS")
	private Integer status;

	@OneToMany(mappedBy = "listTypes", cascade = CascadeType.ALL)
	private List<ListTypeValues> listTypeValues;

	public ListTypes() {
		super();
	}

	public ListTypes(Integer listTypesId) {
		this.listTypeId = listTypesId;
	}

	public Integer getListTypeId() {
		return listTypeId;
	}

	public void setListTypeId(Integer listTypeId) {
		this.listTypeId = listTypeId;
	}

	public String getListTypeName() {
		return listTypeName;
	}

	public void setListTypeName(String listTypeName) {
		this.listTypeName = listTypeName;
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

	public List<ListTypeValues> getListTypeValues() {
		return listTypeValues;
	}

	public void setListTypeValues(List<ListTypeValues> listTypeValues) {
		this.listTypeValues = listTypeValues;
	}

	@Override
	public String toString() {
		return "ListTypes [listTypeId=" + listTypeId + ", listTypeName=" + listTypeName + ", description=" + description
				+ ", status=" + status + ", listTypeValues=" + listTypeValues + "]";
	}

	
}
