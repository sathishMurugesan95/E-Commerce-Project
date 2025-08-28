package com.example.e_commerce.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDTO {
	private Integer id;
    private UserDTO user;
    private LocalDate orders;
    private Double totalAmount;
    private String status;
    private List<OrderItemDTO> items;
    
	public OrderResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderResponseDTO(Integer id2, UserDTO userDTO, LocalDate orders2, double totalAmount2, String status2,
			List<OrderItemDTO> orderItems) {
		// TODO Auto-generated constructor stub
		
		this.id = id2;
		this.user = userDTO;
		this.orders = orders2;
		this.totalAmount = totalAmount2;
		this.status = status2;
		this.items = orderItems;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public LocalDate getOrders() {
		return orders;
	}

	public void setOrders(LocalDate orders) {
		this.orders = orders;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderResponseDTO [id=" + id + ", user=" + user + ", orders=" + orders + ", totalAmount=" + totalAmount
				+ ", status=" + status + ", items=" + items + "]";
	}
    
    
}
