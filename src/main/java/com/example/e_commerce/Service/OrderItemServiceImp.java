package com.example.e_commerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.OrderItem;
import com.example.e_commerce.Repository.OrderItemRepository;

@Service
public class OrderItemServiceImp implements OrderItemService{
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItem save(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return orderItemRepository.save(orderItem);
	}

}
