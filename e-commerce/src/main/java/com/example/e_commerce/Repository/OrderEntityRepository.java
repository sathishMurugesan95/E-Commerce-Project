package com.example.e_commerce.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.User;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer>{


	 @Query("SELECT o FROM OrderEntity o WHERE o.orders = :orders AND o.status = :status")
	List<OrderEntity> findByOrdersAndStatus(LocalDate orders, String status);

//	 List<OrderEntity> findByOrderByUser(User user);

	 List<OrderEntity> findByUserOrderByOrdersDesc(User user);
	
	
	
//	    List<OrderEntity> findOrdersByDateAndStatus(
//	            @Param("orders") LocalDate orders,
//	            @Param("status") String status);

}
