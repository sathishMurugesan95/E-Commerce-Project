package com.example.e_commerce.message;

import java.time.LocalDateTime;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.OrderItem;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemResponseEntity extends BaseResponse{

	private Integer id;

	private User user;

	private Product product;

	private int quantity;

	public CartItemResponseEntity() {
		
	}

	//ListAll
	public CartItemResponseEntity(CartItem cartData) {
		// TODO Auto-generated constructor stub
		
		this.id = cartData.getId();
		this.quantity = cartData.getQuantity();
		this.product = cartData.getProduct();
		this.user = cartData.getUser();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	
	
	
}
