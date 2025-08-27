package com.example.e_commerce.Service;

import java.util.Optional;

import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;

public interface ProductService {

	Product findById(Product product);

	Product save(Product productData);

	Optional<Product> findById(Integer id);


	

}
