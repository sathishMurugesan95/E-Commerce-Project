package com.example.e_commerce.DTO;

public class OrderItemDTO {

	 Integer orderitemId;
	 ProductDTO product;
	 Double priceAtPurchase;
	 Integer quantity;
	 
	 public OrderItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	 }

	 public OrderItemDTO(Integer id2, ProductDTO productDTO, double priceAtPurchase2, int quantity2) {
		// TODO Auto-generated constructor stub
		 
		 this.orderitemId = id2;
		 this.product = productDTO;
		 this.priceAtPurchase = priceAtPurchase2;
		 this.quantity = quantity2;
	}

	

	 public Integer getOrderitemId() {
		return orderitemId;
	}

	 public void setOrderitemId(Integer orderitemId) {
		 this.orderitemId = orderitemId;
	 }

	 public ProductDTO getProduct() {
		 return product;
	 }

	 public void setProduct(ProductDTO product) {
		 this.product = product;
	 }

	 public Double getPriceAtPurchase() {
		 return priceAtPurchase;
	 }

	 public void setPriceAtPurchase(Double priceAtPurchase) {
		 this.priceAtPurchase = priceAtPurchase;
	 }

	 public Integer getQuantity() {
		 return quantity;
	 }

	 public void setQuantity(Integer quantity) {
		 this.quantity = quantity;
	 }

	 @Override
	public String toString() {
		return "OrderItemDTO [orderitemId=" + orderitemId + ", product=" + product + ", priceAtPurchase="
				+ priceAtPurchase + ", quantity=" + quantity + "]";
	}
	
	
}
