package com.example.e_commerce.DTO;

public class PaymentRequest {
	 private String paymentMethod;
	 private Double amount;
	 public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 public String getPaymentMethod() {
		 return paymentMethod;
	 }
	 public void setPaymentMethod(String paymentMethod) {
		 this.paymentMethod = paymentMethod;
	 }
	 public Double getAmount() {
		 return amount;
	 }
	 public void setAmount(Double amount) {
		 this.amount = amount;
	 }
	 @Override
	 public String toString() {
		return "PaymentRequest [paymentMethod=" + paymentMethod + ", amount=" + amount + "]";
	 }
	 
	 
}
