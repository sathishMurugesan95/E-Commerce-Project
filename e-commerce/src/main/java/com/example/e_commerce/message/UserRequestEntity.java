package com.example.e_commerce.message;

import java.util.ArrayList;
import java.util.List;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.UserStatus;
import com.example.e_commerce.Entity.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class UserRequestEntity {

	private Integer id;

	private String username;

	private String email;

	private String password;
	
	private String oldPassword;
	
	private String newPassword;



	private String role; // ROLE_USER, ROLE_ADMIN
	
    private UserType userType;
    
	private UserStatus userStatus;



//	private CartItem cartItems;
	private List<CartItem> cartItems;


	private OrderEntity orders;

	public UserRequestEntity() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public OrderEntity getOrders() {
		return orders;
	}

	public void setOrders(OrderEntity orders) {
		this.orders = orders;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	
	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	
	
	
}
