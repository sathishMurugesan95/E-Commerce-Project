package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.User;

public interface CartItemService {

	CartItem save(CartItem cartItems);

	Optional<CartItem> findById(Integer id);

	List<CartItem> findAll();

	void delete(Integer id);

	Optional<CartItem> findByUserId(Integer id);

	List<CartItem> findAllByUserId(Integer id);

	void deleteAll(List<CartItem> cartItems);

//	List<CartItem> findById(User user);

	


}
