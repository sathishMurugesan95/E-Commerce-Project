package com.example.e_commerce.Entity;

import java.util.List;

import com.example.e_commerce.message.OrderItemRequestEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderitem_id") 
	private Integer orderitemId;

//	@ManyToOne
//	@JoinColumn(name = "order_id", nullable = false)
//	private OrderEntity orderEntity;
	
//	@ManyToOne
//	@JoinColumn(name = "order_id")
//	private OrderEntity orderEntity;
	
	
	
	//new
	@ManyToOne()
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private OrderEntity orderEntity;

	
	
	
	
     //old
//	@ManyToOne
//    @JoinColumn(name = "order_id", nullable = false)
////    @JsonBackReference  //  Prevents looping back
//    private OrderEntity orderEntity;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "priceatpurchase", nullable = false) // ✅ match DB column
	private Double priceAtPurchase;

	@Column(nullable = false)
	private int quantity;

	public OrderItem() {

	}

	// Create
	public OrderItem(OrderItemRequestEntity orderItemRequestEntity) {
		this.orderitemId = orderItemRequestEntity.getOrderitemId();
		this.orderEntity = orderItemRequestEntity.getOrderEntity();
		this.product = orderItemRequestEntity.getProduct();
		this.quantity = orderItemRequestEntity.getQuantity();
		this.priceAtPurchase = orderItemRequestEntity.getPriceAtPurchase();
	}

//	public OrderItem(OrderEntity order2, Product product2, int quantity2, double priceAtPurchase2) {
//		// TODO Auto-generated constructor stub
//		this.order = order2;
//		this.product = product2;
//		this.quantity = quantity2;
//		this.priceAtPurchase = priceAtPurchase2;
//	}

	
	
//    //getOrderItemFrom_Order_Entity
//	public OrderItem(OrderItem item) {
//		
//		this.id = item.getId();
//		this.priceAtPurchase= item.getPriceAtPurchase();
//		this.product = item.getProduct();
//		this.quantity = item.getQuantity();
//	}

	//getOrderItemFrom_Order_Entity
	public OrderItem(Integer id2, Product productDTO, double priceAtPurchase2, int quantity2) {
		// TODO Auto-generated constructor stub
		this.orderitemId = id2;
		this.product = productDTO;
		this.priceAtPurchase = priceAtPurchase2;
		this.quantity = quantity2;
	}

	//getOrderItemFrom_Order_Entity
	public OrderItem(OrderItem item) {
		// TODO Auto-generated constructor stub
		this.orderitemId = item.getOrderitemId();
		this.priceAtPurchase= item.getPriceAtPurchase();
		this.product = item.getProduct();
		this.quantity = item.getQuantity();
	}

	

	public Integer getOrderitemId() {
		return orderitemId;
	}

	public void setOrderitemId(Integer orderitemId) {
		this.orderitemId = orderitemId;
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

	

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	public void setPriceAtPurchase(Double priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderItem [orderitemId=" + orderitemId + ", orderEntity=" + orderEntity + ", product=" + product
				+ ", priceAtPurchase=" + priceAtPurchase + ", quantity=" + quantity + "]";
	}

}
