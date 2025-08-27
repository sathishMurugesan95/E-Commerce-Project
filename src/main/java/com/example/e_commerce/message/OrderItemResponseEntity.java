package com.example.e_commerce.message;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.OrderItem;
import com.example.e_commerce.Entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResponseEntity extends BaseResponse {

	private Integer id;

	private OrderEntity orderEntity;

	private Product product;

	private int quantity;

	private double priceAtPurchase;

	public OrderItemResponseEntity() {

	}

	

//	public OrderItemResponseEntity(OrderItem item) {
//		// TODO Auto-generated constructor stub
//		this.id = item.getId();
//		this.product = item.getProduct();
//		this.quantity = item.getQuantity();
//		this.priceAtPurchase = item.getPriceAtPurchase();
//		
//	}



	



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
