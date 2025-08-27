package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.CartItemRepository;

@Service
public class CartItemServiceImp implements CartItemService{
	
	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public CartItem save(CartItem cartItems) {
		return cartItemRepository.save(cartItems);
	}

	@Override
	public Optional<CartItem> findById(Integer id) {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(id);
	}

	@Override
	public List<CartItem> findAll() {
		// TODO Auto-generated method stub
		return cartItemRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		cartItemRepository.deleteById(id);
		return;
	}

	@Override
	public Optional<CartItem> findByUserId(Integer id) {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(id);
	}

	@Override
	public List<CartItem> findAllByUserId(Integer id) {
		// TODO Auto-generated method stub
		return cartItemRepository.findAllByUserId(id);
	}

	@Override
	public void deleteAll(List<CartItem> cartItems) {
		// TODO Auto-generated method stub
		cartItemRepository.deleteAll( cartItems);
	}

//	@Override
//	public List<CartItem> findById(User user) {
//		// TODO Auto-generated method stub
//		return cartItemRepository.findById(user) ;
//	}


	

}
