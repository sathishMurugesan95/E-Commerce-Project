package com.example.e_commerce.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.DTO.OrderItemDTO;
import com.example.e_commerce.DTO.OrderResponseDTO;
import com.example.e_commerce.DTO.ProductDTO;
import com.example.e_commerce.DTO.UserDTO;
import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.OrderItem;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.CartItemRepository;
import com.example.e_commerce.Repository.OrderEntityRepository;
import com.example.e_commerce.Repository.OrderItemRepository;
import com.example.e_commerce.Repository.UserRepository;
import com.example.e_commerce.Service.CartItemService;
import com.example.e_commerce.Service.EmailService;
import com.example.e_commerce.Service.OrderEntityService;
import com.example.e_commerce.Service.OrderItemService;
import com.example.e_commerce.Service.ProductService;
import com.example.e_commerce.Service.UserService;
import com.example.e_commerce.exception.OutOfStockException;
import com.example.e_commerce.exception.ResourceNotFoundException;
import com.example.e_commerce.message.OrderEntityRequestEntity;
import com.example.e_commerce.message.OrderEntityResponseEntity;
import com.example.e_commerce.message.OrderItemRequestEntity;
import com.example.e_commerce.message.OrderItemResponseEntity;
import com.example.e_commerce.message.ProductResponseEntity;
import com.example.e_commerce.message.UserResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SuppressWarnings({ "rawtypes", "unused", "unchecked", "static-access" })
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce")
public class OrderEntityController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private OrderEntityService orderEntityService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EmailService emailService;

	
	// Places Order API
	@PostMapping("/placeOrderItem")
	public ResponseEntity<ObjectNode> createOrderEntity(
			@RequestBody OrderEntityRequestEntity orderEntityRequestEntity) {

		User user = userService.findById(orderEntityRequestEntity.getUser().getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"User not found with ID: " + orderEntityRequestEntity.getUser().getId()));

		List<CartItem> cartItems = cartItemService.findAllByUserId(user.getId());
		if (cartItems.isEmpty()) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", HttpStatus.BAD_REQUEST.value());
			jsonObject.put("message", "Cart is empty!");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
		}

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUser(user);
		System.out.println("orderEntity in user:" + orderEntity.getUser().getId());
		orderEntity.setStatus(orderEntityRequestEntity.getStatus());
		orderEntity.setOrders(LocalDate.now());
		System.out.println("orderEntity in OrderDate:" + orderEntity.getOrders());

		double totalAmount = 0.0;

		for (CartItem cartItem : cartItems) {
			Product product = cartItem.getProduct();

//			System.out.println("Cart Item Product :"+product);

			System.out.println("Crtitem in Product:" + product.getId());
			int quantity = cartItem.getQuantity();
			double price = Optional.ofNullable(product.getPrice()).orElse(0.0);
			System.out.println("Cart Item Price :" + price);
			totalAmount += quantity * price;
			System.out.println("Total amoint :" + totalAmount);

			// ✅ Check if enough stock is available
			if (product.getQuantity() <= quantity) {
				throw new OutOfStockException("Not enough stock for product: " + product.getName());
			}

			// ✅ Reduce stock
			product.setQuantity(product.getQuantity() - quantity);
			productService.save(product); // Save updated stock in DB

			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(product);
			orderItem.setQuantity(quantity);
			orderItem.setPriceAtPurchase(price);

			// adding Order_Item into Order_Entity
			orderEntity.addOrderItem(orderItem); // Proper linking
		}

		orderEntity.setTotalAmount(totalAmount);

		// CascadeType.ALL ensures child is saved AFTER parent
		orderEntityService.save(orderEntity);

		cartItemService.deleteAll(cartItems);
		
		
		
		 // If order is PAID or PLACED, trigger post-payment actions
		if ("PAID".equalsIgnoreCase(orderEntityRequestEntity.getStatus()) 
		        || "PLACED".equalsIgnoreCase(orderEntityRequestEntity.getStatus())) {
		    
		    // Send confirmation email
		    emailService.sendOrderConfirmation(user.getEmail(), orderEntity);
	        // You can also mark order as COMPLETED or store a payment reference
	    }

		ObjectNode response = objectMapper.createObjectNode();
		response.put("statusCode", HttpStatus.CREATED.value());
		response.put("message", "Order placed successfully! Total:" + totalAmount);
		response.put("totalAmount", totalAmount);
		response.put("Order Your mail", user.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}
	
	
      //	Update Order Items Quantity And Product
	@PutMapping("/orders/{orderId}/items/{orderItemId}")
	public ResponseEntity<ObjectNode> updateOrderItem(
	        @PathVariable("orderId") Integer id,
	        @PathVariable("orderItemId") Integer orderitemId,
	        @RequestParam Integer quantity) {

	    OrderEntity order = orderEntityService.findAllById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	    OrderItem item = order.getOrderItems().stream()
	            .filter(i -> i.getOrderitemId().equals(orderitemId))
	            .findFirst()
	            .orElseThrow(() -> new ResourceNotFoundException("Order item not found"));

	    int difference = quantity - item.getQuantity();
	    Product product = item.getProduct();

	    if (product.getQuantity() < difference) {
	        throw new OutOfStockException("Not enough stock to increase quantity");
	    }

	    product.setQuantity(product.getQuantity() - difference);
	    productService.save(product);

	    item.setQuantity(quantity);
	    item.setPriceAtPurchase(item.getPriceAtPurchase()); // update price if needed

	    double totalAmount = order.getOrderItems().stream()
	            .mapToDouble(i -> i.getQuantity() * i.getPriceAtPurchase())
	            .sum();
	    order.setTotalAmount(totalAmount);

	    orderEntityService.save(order);

	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Order item updated successfully");
	    response.put("totalAmount", totalAmount);

	    return ResponseEntity.ok(response);
	}

	
	
	
	
	
	//Order_Paid API
	@PutMapping("/orders/{orderId}/paid/status")
	public ResponseEntity<ObjectNode> updateOrderStatus(
			 @PathVariable("orderId") Integer id,
	        @RequestParam  String status) {

		 OrderEntity order = orderEntityService.findAllById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	    order.setStatus(status.toUpperCase());
	    orderEntityService.save(order);

	    // Send email based on new status
	    if ("PAID".equalsIgnoreCase(status)) {
	        emailService.sendPaymentSuccessEmail(order.getUser().getEmail(), order);
	    }

	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Order status updated to " + status);
	    response.put("emailSentTo", order.getUser().getEmail());

	    return ResponseEntity.ok(response);
	}

	
	
	// 
	@PutMapping("/orders/{orderId}/pay")
	public ResponseEntity<ObjectNode> payForOrder(@PathVariable("orderId")  Integer id) {
	    OrderEntity order = orderEntityService.findAllById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	    order.setStatus("PAID");
	    orderEntityService.save(order);

	    // Send email
	    emailService.sendOrderConfirmation(order.getUser().getEmail(), order);

	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Payment successful, order marked as PAID");
	    return ResponseEntity.ok(response);
	}

	
	@PutMapping("/cancel/{orderId}")
	public ResponseEntity<ObjectNode> cancelOrder(@PathVariable("orderId") Integer id) {
	    
	    OrderEntity orderEntity = orderEntityService.findAllById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

	    
	    if ("CANCELLED".equalsIgnoreCase(orderEntity.getStatus())) {
	        ObjectNode response = objectMapper.createObjectNode();
	        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
	        response.put("message", "Order is already cancelled.");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }

	    if ("COMPLETED".equalsIgnoreCase(orderEntity.getStatus())) {
	        ObjectNode response = objectMapper.createObjectNode();
	        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
	        response.put("message", "Completed orders cannot be cancelled.");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }

	    //  Restore stock for each product
	    for (OrderItem orderItem : orderEntity.getOrderItems()) {
	        Product product = orderItem.getProduct();
	        System.out.println("for restore item:"+product.getQuantity() + orderItem.getQuantity());
	        product.setQuantity(product.getQuantity() + orderItem.getQuantity());
	        productService.save(product);
	    }

	    // Update orderEntity status
	    orderEntity.setStatus("CANCELLED");
	    orderEntityService.save(orderEntity);

	   
	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Order cancelled successfully.");
	    response.put("orderId", id);

	    return ResponseEntity.ok(response);
	}

	
	
//	@GetMapping("/byDateAndStatus/{date}/{status}")
//	public ResponseEntity<ObjectNode> getByDateAndStatus(
//	        @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
//	        @PathVariable("status") String status) throws JsonProcessingException {
//
//	    List<OrderEntity> ordersList = orderEntityService.getOrdersByDateAndStatus(date, status);
//
//	    if (ordersList.isEmpty()) {
//	        ObjectNode response = objectMapper.createObjectNode();
//	        response.put("statusCode", HttpStatus.NOT_FOUND.value());
//	        response.put("message", "No orders found for date: " + date + " and status: " + status);
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//	    }
//
//	    List<OrderEntityResponseEntity> orderResponseList = ordersList.stream().map(order -> {
//	        // Map User
//	        User userDTO = new User(order.getUser().getId(), order.getUser().getUsername());
//
//	        // Map Order Items
//	        List<OrderItem> orderItems = order.getOrderItems().stream().map(item -> {
//	            Product productDTO = new Product(item.getProduct().getId(), item.getProduct().getName());
//	            return new OrderItem(item.getId(), productDTO, item.getPriceAtPurchase(), item.getQuantity());
//	        }).toList();
//
//	        return new OrderEntityResponseEntity(
//	                order.getId(),
//	                userDTO,
//	                order.getOrders(),
//	                order.getTotalAmount(),
//	                order.getStatus(),
//	                orderItems
//	        );
//	    }).toList();
//
//	    ObjectNode response = objectMapper.createObjectNode();
//	    response.put("statusCode", HttpStatus.OK.value());
//	    response.put("message", "Orders fetched successfully");
//	    response.putPOJO("orders", orderResponseList);
//
//	    return ResponseEntity.ok(response);
//	}	

	
	
	@GetMapping("/byDateAndStatus/{date}/{status}")
	public ResponseEntity<ObjectNode> getByDateAndStatus(
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
	        @PathVariable String status) {

	    List<OrderResponseDTO> orderResponseList = orderEntityService.getOrdersByDateAndStatus(date, status)
	            .stream().map(order -> new OrderResponseDTO(
	                    order.getId(),
	                    new UserDTO(order.getUser().getId(), order.getUser().getUsername(), order.getUser().getEmail()),
	                    order.getOrders(),
	                    order.getTotalAmount(),
	                    order.getStatus(),
	                    order.getOrderItems().stream().map(item -> new OrderItemDTO(
	                            item.getOrderitemId(),
	                            new ProductDTO(item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getPrice()),
	                            item.getPriceAtPurchase(),
	                            item.getQuantity()
	                    )).toList()
	            )).toList();

	    ObjectNode response = objectMapper.createObjectNode();
	    if (orderResponseList.isEmpty()) {
	        response.put("statusCode", HttpStatus.NOT_FOUND.value());
	        response.put("message", "No orders found for date: " + date + " and status: " + status);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Orders fetched successfully");
	    response.putPOJO("orders", orderResponseList);

	    return ResponseEntity.ok(response);
	}

	
	
//	@GetMapping("/byDateAndStatus/{date}/{status}")
//	public ResponseEntity<ObjectNode> getByDateAndStatus(
//	        @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
//	        @PathVariable("status") String status) {
//
//	    List<OrderEntity> ordersList = orderEntityService.getOrdersByDateAndStatus(date, status);
//
//	    if (ordersList.isEmpty()) {
//	        ObjectNode response = objectMapper.createObjectNode();
//	        response.put("statusCode", HttpStatus.NOT_FOUND.value());
//	        response.put("message", "No orders found for date: " + date + " and status: " + status);
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//	    }
//
//	    List<OrderResponseDTO> orderResponseList = ordersList.stream().map(order -> {
//	        // Map User
//	        UserDTO userDTO = new UserDTO(
//	            order.getUser().getId(),
//	            order.getUser().getUsername(),
//	            order.getUser().getEmail()
//	        );
//
//	        // Map Items
//	        List<OrderItemDTO> orderItems = order.getOrderItems().stream().map(item -> {
//	            ProductDTO productDTO = new ProductDTO(
//	                item.getProduct().getId(),
//	                item.getProduct().getName(),
//	                item.getProduct().getPrice()
//	            );
//	            return new OrderItemDTO(
//	                item.getId(),
//	                productDTO,
//	                item.getPriceAtPurchase(),
//	                item.getQuantity()
//	            );
//	        }).toList();
//
//	        return new OrderResponseDTO(
//	            order.getId(),
//	            userDTO,
//	            order.getOrders(),
//	            order.getTotalAmount(),
//	            order.getStatus(),
//	            orderItems
//	        );
//	    }).toList();
//
//	    ObjectNode response = objectMapper.createObjectNode();
//	    response.put("statusCode", HttpStatus.OK.value());
//	    response.put("message", "Orders fetched successfully");
//	    response.putPOJO("orders", orderResponseList);
//
//	    return ResponseEntity.ok(response);
//	}

	
	
	// Get Orders by User ID
	@GetMapping("/orders/user/{userId}")
	public ResponseEntity<ObjectNode> getOrdersByUser(@PathVariable("userId") Integer id) {
	    // Find User
	    User user = userService.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

	    // Fetch Orders
	    List<OrderEntity> orders = orderEntityService.getOrdersByUser(user);

	    // Convert to DTOs
	    List<OrderResponseDTO> orderResponseList = orders.stream().map(order -> new OrderResponseDTO(
	            order.getId(),
	            new UserDTO(order.getUser().getId(), order.getUser().getUsername(), order.getUser().getEmail()),
	            order.getOrders(),
	            order.getTotalAmount(),
	            order.getStatus(),
	            order.getOrderItems().stream().map(item -> new OrderItemDTO(
	                    item.getOrderitemId(),
	                    new ProductDTO(item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getPrice()),
	                    item.getPriceAtPurchase(),
	                    item.getQuantity()
	            )).toList()
	    )).toList();

	    // Build Response
	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Orders fetched successfully for user: " + user.getUsername());
	    response.putPOJO("orders", orderResponseList);

	    return ResponseEntity.ok(response);
	}


	//Order Refund API
	@PostMapping("/orders/{orderId}/refund")
	public ResponseEntity<ObjectNode> refundOrder(@PathVariable("orderId") Integer id) {
	    OrderEntity order = orderEntityService.findAllById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	    // Restore stock to Product Table
	    for (OrderItem item : order.getOrderItems()) {
	        Product product = item.getProduct();
	        product.setQuantity(product.getQuantity() + item.getQuantity());
	        productService.save(product);
	    }

	    // Update order status
	    order.setStatus("REFUNDED");
	    orderEntityService.save(order);

	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Order refunded successfully");
	    response.put("orderId", id);

	    return ResponseEntity.ok(response);
	}
	
	
//	@PostMapping("/orders/{orderId}/items/{orderItemId}/refund")
//	public ResponseEntity<ObjectNode> refundSingleItem(
//	        @PathVariable("orderId") Integer id,
//	        @PathVariable("orderItemId") Integer orderitemId) {
//
//	    // 🔍 Get the order
//	    OrderEntity order = orderEntityService.findAllById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//
//	    // 🔍 Find the specific item in the order
//	    OrderItem orderItem = order.getOrderItems().stream()
//	            .filter(item -> item.getOrderitemId().equals(orderitemId))
//	            .findFirst()
//	            .orElseThrow(() -> new ResourceNotFoundException("Order Item not found"));
//
//	    //  Restore stock for this product
//	    Product product = orderItem.getProduct();
//	    product.setQuantity(product.getQuantity() + orderItem.getQuantity());
//	    productService.save(product);
//
//	    //  Update total amount
//	    double refundAmount = orderItem.getQuantity() * orderItem.getPriceAtPurchase();
//	    order.setTotalAmount(order.getTotalAmount() - refundAmount);
//
//	    // Option 1: Remove the item from the order
//	    order.getOrderItems().remove(orderItem);
//
//	    //  Option 2 (optional): Keep the item but mark it as refunded
//	    // orderItem.setStatus("REFUNDED");
//
//	    orderEntityService.save(order);
//
//	    ObjectNode response = objectMapper.createObjectNode();
//	    response.put("statusCode", HttpStatus.OK.value());
//	    response.put("message", "Item refunded successfully");
//	    response.put("orderId", id);
//	    response.put("orderItemId", orderitemId);
//	    response.put("refundedAmount", refundAmount);
//
//	    return ResponseEntity.ok(response);
//	}
	
	@PostMapping("/orders/{orderId}/items/{orderItemId}/refund")
	public ResponseEntity<ObjectNode> refundSingleItem(
	        @PathVariable("orderId") Integer orderId,
	        @PathVariable("orderItemId") Integer orderItemId) {

	    // Get the order
	    OrderEntity order = orderEntityService.findAllById(orderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	    // Find the specific item in the order
	    OrderItem orderItem = order.getOrderItems().stream()
	            .filter(item -> item.getOrderitemId().equals(orderItemId))
	            .findFirst()
	            .orElseThrow(() -> new ResourceNotFoundException("Order Item not found"));

	    //  Restore stock for this product
	    Product product = orderItem.getProduct();
	    product.setQuantity(product.getQuantity() + orderItem.getQuantity());
	    productService.save(product);

	    //  Deduct refunded amount from order total
	    double refundAmount = orderItem.getQuantity() * orderItem.getPriceAtPurchase();
	    order.setTotalAmount(order.getTotalAmount() - refundAmount);

	    //  Remove the item from order (or mark as refunded if you prefer)
	    order.getOrderItems().remove(orderItem);

	    // 🔹Save updated order
	    orderEntityService.save(order);

	    //  Send refund email to user
	    emailService.sendItemRefundEmail(order.getUser().getEmail(), orderItem, order);

	    // 🔹 Build response
	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("message", "Item refunded successfully");
	    response.put("orderId", orderId);
	    response.put("orderItemId", orderItemId);
	    response.put("refundedAmount", refundAmount);
	    response.put("email", order.getUser().getEmail());

	    return ResponseEntity.ok(response);
	}


	
	//Order Traking API
	@GetMapping("/orders/{orderId}/track")
	public ResponseEntity<ObjectNode> trackOrder(@PathVariable("orderId") Integer id) {
	    OrderEntity order = orderEntityService.findAllById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	    ObjectNode response = objectMapper.createObjectNode();
	    response.put("statusCode", HttpStatus.OK.value());
	    response.put("orderId", order.getId());
	    response.put("status", order.getStatus());
	    response.put("estimatedDelivery", LocalDate.now().plusDays(3).toString()); 
	    response.put("message", "Order tracking information");

	    return ResponseEntity.ok(response);
	}


	
 

}
