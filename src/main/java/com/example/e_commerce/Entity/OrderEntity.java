package com.example.e_commerce.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.e_commerce.message.OrderEntityRequestEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderEntity")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	// old
//	@ManyToOne
//	@JoinColumn(name = "user_id", nullable = false)
//	private User user;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEntity")
//	private List<OrderItem> orderItems = new ArrayList<>();
	
	 @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<OrderItem> orderItems = new ArrayList<>();  // ✅ FIXED


	@Column(name = "status")
	private String status;

	@Column(name = "order_date")
	private LocalDate orders;

	@Column(name = "total_amount")
	private Double totalAmount;

	public OrderEntity() {

	}

	// create
	public OrderEntity(OrderEntityRequestEntity orderEntityRequestEntity) {

		this.user = orderEntityRequestEntity.getUser();
		this.orders = LocalDate.now();
		this.totalAmount = orderEntityRequestEntity.getTotalAmount();
		this.status = orderEntityRequestEntity.getStatus();
		this.orderItems = orderEntityRequestEntity.getOrderItems();
	}

	public OrderEntity(OrderEntity order) {
		this.id = order.getId();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOrders() {
		return orders;
	}

	public void setOrders(LocalDate orders) {
		this.orders = orders;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	// Helper method for bidirectional relationship
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrderEntity(this);

//	public void addOrderItem(OrderItem orderItem) {
//		// TODO Auto-generated method stub
//		 this.orderItems.add(orderItem);
//		    orderItem.setOrder(this); // maintain bidirectional relation
//	}

//	@Override
//	public String toString() {
//		return "OrderEntity [id=" + id + ", user=" + user + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount
//				+ ", status=" + status + ", orderItems=" + orderItems + "]";
//	}

	}
}
