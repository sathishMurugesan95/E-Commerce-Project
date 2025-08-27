package com.example.e_commerce.message;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderItemRequestEntity {

	private Integer orderitemId;

	private OrderEntity orderEntity;

	private Product product;

	private int quantity;

	private double priceAtPurchase;

	public OrderItemRequestEntity() {

	}

	public Integer getOrderitemId() {
		return orderitemId;
	}

	public void setOrderitemId(Integer orderitemId) {
		this.orderitemId = orderitemId;
	}

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
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

	public double getPriceAtPurchase() {
		return priceAtPurchase;
	}

	public void setPriceAtPurchase(double priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}

}
