package com.example.e_commerce.message;

import java.util.ArrayList;
import java.util.List;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class ProductRequestEntity {

	private Integer id;

	private String name;

	private String description;

	private double price;

	private int quantity;

	private CartItem cartItems;

	private OrderItem orderItems;

	public ProductRequestEntity() {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartItem getCartItems() {
		return cartItems;
	}

	public void setCartItems(CartItem cartItems) {
		this.cartItems = cartItems;
	}

	public OrderItem getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItem orderItems) {
		this.orderItems = orderItems;
	}

}
