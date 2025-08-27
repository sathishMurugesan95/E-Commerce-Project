package com.example.e_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
