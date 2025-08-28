package com.example.e_commerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Service.ProductService;
import com.example.e_commerce.Service.UserService;
import com.example.e_commerce.message.ProductRequestEntity;
import com.example.e_commerce.message.ProductResponseEntity;
import com.example.e_commerce.message.UserRequestEntity;
import com.example.e_commerce.message.UserResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce/product")
public class ProductController {
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductService productService;
	
	ProductResponseEntity productResponseEntity = new ProductResponseEntity();
	
	@PostMapping("/addproduct")
	public ResponseEntity<UserResponseEntity> createUser(@RequestBody ProductRequestEntity productRequestEntity) {
		System.out.println("add user");
		Product productData = new Product(productRequestEntity);
		Product proSave = productService.save(productData);

		if (proSave != null ) {
			ProductResponseEntity productResponseEntity = new ProductResponseEntity();
			productResponseEntity.setStatusCode(200);
			productResponseEntity.setDescription("Product Created Successfully");
			return new ResponseEntity(productResponseEntity, HttpStatus.OK);
		}else {
			ProductResponseEntity productResponseEntity = new ProductResponseEntity();
			productResponseEntity.setStatusCode(200);
			productResponseEntity.setDescription("Unable to Create Product");
			return new ResponseEntity(productResponseEntity, HttpStatus.OK);
		}

		

	}

}
