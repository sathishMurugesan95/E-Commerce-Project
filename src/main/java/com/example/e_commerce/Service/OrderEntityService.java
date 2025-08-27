package com.example.e_commerce.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.User;

public interface OrderEntityService {

	OrderEntity save(OrderEntity orderEntity);

	List<OrderEntity> getOrdersByDateAndStatus(LocalDate orders, String status);

	Optional<OrderEntity> findAllById(Integer id);

	List<OrderEntity> getOrdersByUser(User user);

	


}
