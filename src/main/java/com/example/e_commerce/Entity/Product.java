package com.example.e_commerce.Entity;

import java.util.ArrayList;
import java.util.List;

import com.example.e_commerce.message.ProductRequestEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "PRODUCT_NAME", nullable = false)
	private String name;

	@Column(name = "PRODUCT_DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "PRICE", nullable = false)
	private double price;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems;

	public Product() {

	}

	public Product(ProductRequestEntity productRequestEntity) {
		
		this.id = productRequestEntity.getId();
		this.description = productRequestEntity.getDescription();
		this.name = productRequestEntity.getName();
		this.price = productRequestEntity.getPrice();
		this.quantity = productRequestEntity.getQuantity();
		
	}

	//List From CartItem
	public Product(Product product) {
		// TODO Auto-generated constructor stub
		this.id = product.getId();
		this.description = product.getDescription();
		this.name = product.getName();
		this.price = product.getPrice();
	}
 
	//GetProduct From Order_Entity
//	public Product(Product product, Product product2) {
//		// TODO Auto-generated constructor stub
//		this.id = product.getId();
//		this.description = product.getDescription();
//		this.name = product.getName();
//		this.price = product.getPrice();
//	}

	//order_Entity
	public Product(Integer id2, String name2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.name = name2;
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

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", cartItems=" + cartItems + ", orderItems=" + orderItems + "]";
	}

}
