package com.example.e_commerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	

//	List<CartItem> findById(User user);

//	@Query("SELECT c FROM CartItem c WHERE c.user.id = :userId")
	List<CartItem> findAllByUserId(Integer id);

//	void delete(Integer id);

}
