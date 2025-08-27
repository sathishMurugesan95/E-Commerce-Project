package com.example.e_commerce.Entity;

import java.util.Optional;

import com.example.e_commerce.message.CartItemRequestEntity;

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
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	public CartItem() {

	}

//	//Create CartItem From USer
//	public CartItem(CartItem cartItems) {
//		
//		this.id= cartItems.getId();
//		this.user = cartItems.getUser();
////		this.product = cartItems.getProduct();
//		this.quantity = cartItems.getQuantity();
//		
//		
//	}

//	public CartItem(CartItemRequestEntity cartItemReq) {
//		this.id= cartItemReq.getId();
//		this.user = cartItemReq.getUser();
////		this.product = cartItems.getProduct();
//		this.quantity = cartItemReq.getQuantity();
//		
//		
//	}

//	//Create CartItem From USer
//	public CartItem(CartItem cartData, User userSave) {
//		
//		this.id = cartData.getId();
//		this.quantity =cartData.getQuantity();
//		this.product = cartData.getProduct();
//		this.user =userSave;
//		
//	}

    //Create Cart
	public CartItem(CartItemRequestEntity cartItemRequestEntity) {
		
		this.id = cartItemRequestEntity.getId();
		this.quantity = cartItemRequestEntity.getQuantity();
		this.user = cartItemRequestEntity.getUser();
		this.product = cartItemRequestEntity.getProduct();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}

}
