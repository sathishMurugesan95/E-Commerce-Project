package com.example.e_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findById(Product product);

}
