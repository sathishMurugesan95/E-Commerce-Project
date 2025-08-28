package com.example.e_commerce.message;

import java.util.List;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Entity.UserStatus;
import com.example.e_commerce.Entity.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserResponseEntity extends BaseResponse {

	private Integer id;

	private String username;

	private String email;

	private String password;

	private String role; // ROLE_USER, ROLE_ADMIN

//	private CartItem cartItems;
	private List<CartItem> cartItems;

	private OrderEntity orders;

	private UserType userType;
	
	private UserStatus userStatus;



	public UserResponseEntity() {

	}

	// Login
	public UserResponseEntity(Integer id2, User employee) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.username = employee.getUsername();
		this.email = employee.getEmail();
		this.role = employee.getRole();
		this.userType = employee.getUserType();
		this.userStatus = employee.getUserStatus();

	}

	// ListAll
	public UserResponseEntity(User user) {
		// TODO Auto-generated constructor stub

		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.userType = user.getUserType();
		this.userStatus = user.getUserStatus();
	}

	public UserResponseEntity(Integer id2, String username2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.username = username2;
	}

	//try New
	public UserResponseEntity(User savedUser, int i, String string) {
		// TODO Auto-generated constructor stub
		this.id = savedUser.getId();
		this.username = savedUser.getUsername();
		this.email = savedUser.getEmail();
		this.role = savedUser.getRole();
		this.userType = savedUser.getUserType();
		this.userStatus = savedUser.getUserStatus();
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	
	

}
