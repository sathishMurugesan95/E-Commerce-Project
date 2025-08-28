package com.example.e_commerce.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.OrderItem;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEntityResponseEntity extends BaseResponse {

	private Integer id;

	private User user;

	private LocalDate orderDate;

	private double totalAmount;

	private String status; // e.g., PLACED, PAID, SHIPPED, CANCELLED

	private List<OrderItem> orderItems;

	public OrderEntityResponseEntity() {

	}

//	//GetByorderDateAndstatus
//	public OrderEntityResponseEntity(OrderEntity orderEntity) {
//		
//		this.id = orderEntity.getId();
//		this.orderDate = orderEntity.getOrders();
//		this.totalAmount = orderEntity.getTotalAmount();
//		this.orderItems = orderEntity.getOrderItems();
//		this.user = orderEntity.getUser();
//		this.status = orderEntity.getStatus();
//
//		
//	}

	
	//GetByorderDateAndstatus
	public OrderEntityResponseEntity(Integer id2, User userDTO, LocalDate orders, double totalAmount2, String status2,
			List<OrderItem> orderItems2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.user = userDTO;
		this.orderDate = orders;
		this.totalAmount = totalAmount2;
		this.status = status2;
		this.orderItems = orderItems2;
	}
    
	//GetByorderDateAndstatus
	public OrderEntityResponseEntity(OrderEntity orderEntity) {
		// TODO Auto-generated constructor stub
		
		this.id = orderEntity.getId();
		this.orderDate = orderEntity.getOrders();
		this.totalAmount = orderEntity.getTotalAmount();
		this.orderItems = orderEntity.getOrderItems();
		this.user = orderEntity.getUser();
		this.status = orderEntity.getStatus();
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

	

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	

}
