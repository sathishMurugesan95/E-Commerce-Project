package com.example.e_commerce.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.OrderEntityRepository;

@Service
public class OrderEntityServiceImp implements OrderEntityService {

	@Autowired
	OrderEntityRepository orderEntityRepository;

	@Override
	public OrderEntity save(OrderEntity orderEntity) {
		// TODO Auto-generated method stub
		return orderEntityRepository.save(orderEntity);
	}

	@Override
	public List<OrderEntity> getOrdersByDateAndStatus(LocalDate orders, String status) {
		return orderEntityRepository.findByOrdersAndStatus(orders, status);
	}

	@Override
	public Optional<OrderEntity> findAllById(Integer id) {
		// TODO Auto-generated method stub
		return orderEntityRepository.findById(id);
	}

	public List<OrderEntity> getOrdersByUser(User user) {
	    return orderEntityRepository.findByUserOrderByOrdersDesc(user);
	}

	

}
