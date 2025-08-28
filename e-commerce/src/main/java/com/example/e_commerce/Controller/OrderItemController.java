package com.example.e_commerce.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.OrderItem;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.CartItemRepository;
import com.example.e_commerce.Repository.OrderItemRepository;
import com.example.e_commerce.Repository.UserRepository;
import com.example.e_commerce.Service.CartItemService;
import com.example.e_commerce.Service.OrderItemService;
import com.example.e_commerce.Service.ProductService;
import com.example.e_commerce.Service.UserService;
import com.example.e_commerce.message.OrderItemRequestEntity;
import com.example.e_commerce.message.OrderItemResponseEntity;
import com.example.e_commerce.message.UserResponseEntity;

@SuppressWarnings({ "rawtypes", "unused", "unchecked", "static-access" })
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce")
public class OrderItemController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductService productService;

	OrderItemResponseEntity OrderItemRequestEntity = new OrderItemResponseEntity();

	@PostMapping("/addOrderItem")
	public ResponseEntity<String> createOrderItem(@RequestBody OrderItemRequestEntity orderItemRequestEntity) {

//	        // 1️⃣ Find the user
//	        User user = userService.findById(orderItemRequestEntity.getId())
//	                .orElseThrow(() -> new RuntimeException("User not found!"));

		// 2️⃣ Find the product
		Product product = productService.findById(orderItemRequestEntity.getProduct().getId())
				.orElseThrow(() -> new RuntimeException("Product not found!"));

		OrderItem orderItem = new OrderItem(orderItemRequestEntity);

		OrderItem orderItemSave = orderItemService.save(orderItem);

		if (orderItemSave != null) {
			OrderItemResponseEntity OrderItemRequestEntity = new OrderItemResponseEntity();
			OrderItemRequestEntity.setStatusCode(200);
			OrderItemRequestEntity.setDescription("OrderItem Created Successfully");
			return new ResponseEntity(OrderItemRequestEntity, HttpStatus.OK);
		} else {
			OrderItemResponseEntity OrderItemRequestEntity = new OrderItemResponseEntity();
			OrderItemRequestEntity.setStatusCode(200);
			OrderItemRequestEntity.setDescription("Unable to Create OrderItem");
			return new ResponseEntity(OrderItemRequestEntity, HttpStatus.OK);
		}

	}
}
