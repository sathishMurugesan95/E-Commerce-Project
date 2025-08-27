package com.example.e_commerce.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.e_commerce.message.UserRequestEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLL")
	private String role; // ROLE_USER, ROLE_ADMIN

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderEntity> orders ;

	@Column(name = "reset_otp")
	private String resetOtp;

	@Column(name = "otp_requested_time")
	private LocalDateTime otpRequestedTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_TYPE")
	private UserType userType;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_STATUS")
	private UserStatus userStatus;

	public User() {

	}

	// Create User
	public User(UserRequestEntity userRequestEntity) {

		this.id = userRequestEntity.getId();
		this.username = userRequestEntity.getUsername();
		this.email = userRequestEntity.getEmail();
		this.password = userRequestEntity.getPassword();
		this.role = userRequestEntity.getRole();
		this.userType = userRequestEntity.getUserType();
		this.userStatus = userRequestEntity.getUserStatus();

	}

	// Update User
	public User(Optional<User> findUsrId, UserRequestEntity userRequestEntity) {

		this.id = findUsrId.get().id;
		this.username = userRequestEntity.getUsername();
		this.email = userRequestEntity.getEmail();
		this.password = userRequestEntity.getPassword();
		this.role = userRequestEntity.getRole();
		this.userType = userRequestEntity.getUserType();
		this.userStatus = userRequestEntity.getUserStatus();

	}

	// List From CartItem
	public User(User user) {
		// TODO Auto-generated constructor stub
		this.id = user.id;
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
	}

	//orderItem
	public User(Integer id2, String username2) {
		
		this.id = id2;
		this.username = username2;
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

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public String getResetOtp() {
		return resetOtp;
	}

	public void setResetOtp(String resetOtp) {
		this.resetOtp = resetOtp;
	}

	public LocalDateTime getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(LocalDateTime otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + ", cartItems=" + cartItems + ", orders=" + orders + ", resetOtp=" + resetOtp
				+ ", otpRequestedTime=" + otpRequestedTime + ", userType=" + userType + ", userStatus=" + userStatus
				+ "]";
	}

}
