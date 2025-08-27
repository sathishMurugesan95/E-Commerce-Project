package com.example.e_commerce.DTO;

public class ProductDTO {
	 Integer id;
	 String name;
	 Double price;
	 
	 public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	 }

	 public ProductDTO(Integer id2, String name2, double price2) {
		// TODO Auto-generated constructor stub
		 
		 this.id = id2;
		 this.name = name2;
		 this.price= price2;
	}

	 public Integer getId() {
		 return id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public Double getPrice() {
		 return price;
	 }

	 public void setPrice(Double price) {
		 this.price = price;
	 }

	 @Override
	 public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + "]";
	 }
	 
	 

}
