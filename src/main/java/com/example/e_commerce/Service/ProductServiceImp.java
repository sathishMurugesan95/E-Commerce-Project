package com.example.e_commerce.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product findById(Product product) {
		// TODO Auto-generated method stub
		return productRepository.findById(product);
	}

	@Override
	public Product save(Product productData) {
		// TODO Auto-generated method stub
		return productRepository.save(productData);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		
		return productRepository.findById( id);
	}

	

	

}
