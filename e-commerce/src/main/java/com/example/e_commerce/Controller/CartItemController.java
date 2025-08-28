package com.example.e_commerce.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.CartItem;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Entity.UserStatus;
import com.example.e_commerce.Service.CartItemService;
import com.example.e_commerce.message.CartItemRequestEntity;
import com.example.e_commerce.message.CartItemResponseEntity;
import com.example.e_commerce.message.UserRequestEntity;
import com.example.e_commerce.message.UserResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import aj.org.objectweb.asm.TypeReference;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce")
public class CartItemController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	CartItemService cartItemService;

	CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity();

//	@PostMapping("/addCart")
//	public ResponseEntity<CartItemResponseEntity> createCartItem(
//			@RequestBody CartItemRequestEntity cartItemRequestEntity) {
//		CartItem cartData = new CartItem(cartItemRequestEntity);
//
//		CartItem cartSave = cartItemService.save(cartData);
//
//		if (cartSave != null) {
//			CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity();
//			cartItemResponseEntity.setStatusCode(200);
//			cartItemResponseEntity.setDescription("CartItem Created Successfully");
//			return new ResponseEntity(cartItemResponseEntity, HttpStatus.OK);
//		} else {
//			CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity();
//			cartItemResponseEntity.setStatusCode(200);
//			cartItemResponseEntity.setDescription("Unable to Create CartItem");
//			return new ResponseEntity(cartItemResponseEntity, HttpStatus.OK);
//		}
//
//	}

	@PostMapping("/addCart")
	public ResponseEntity<CartItemResponseEntity> createCartItem(
	        @RequestBody Object requestBody) {

	    CartItemResponseEntity response = new CartItemResponseEntity();

	    try {
	        List<CartItem> savedItems = new ArrayList<>();
	        ObjectMapper mapper = new ObjectMapper();

	        if (requestBody instanceof List) {
	            //Multiple Cart Items
	            List<?> requestList = (List<?>) requestBody;
	            for (Object obj : requestList) {
	                CartItemRequestEntity request = mapper.convertValue(obj, CartItemRequestEntity.class);
	                CartItem cartItem = new CartItem(request);
	                savedItems.add(cartItemService.save(cartItem));
	            }
	            response.setDescription("Multiple CartItems Created Successfully");
	        } else {
	            //Single Cart Item
	            CartItemRequestEntity request = mapper.convertValue(requestBody, CartItemRequestEntity.class);
	            CartItem cartItem = new CartItem(request);
	            savedItems.add(cartItemService.save(cartItem));
	            response.setDescription("Single CartItem Created Successfully");
	        }

	        response.setStatusCode(200);
	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.setStatusCode(500);
	        response.setDescription("Error while creating cart item(s): " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	
	
	@PutMapping("/updatecart/{id}")
	public ResponseEntity<?> updateCartItem(@PathVariable("id") Integer id,
			@RequestBody CartItemRequestEntity cartItemRequestEntity) {

		Optional<CartItem> existingItem = cartItemService.findById(id);

		if (existingItem.isPresent()) {
			CartItem cartItem = existingItem.get();

			// Update fields from request to entity
			cartItem.setQuantity(cartItemRequestEntity.getQuantity());
			cartItem.setProduct(cartItemRequestEntity.getProduct());
			cartItem.setUser(cartItemRequestEntity.getUser());
			// Add any other fields that need updating

			cartItemService.save(cartItem);

			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", 200); // Just directly set the value
			jsonObject.put("message", "Cart item updated successfully"); // Directly put string
			return new ResponseEntity<ObjectNode>(jsonObject, HttpStatus.OK);

//			return ResponseEntity.ok("Cart item updated successfully");
		} else {

			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", 404); // Just directly set the value
			jsonObject.put("message", "Cart item not found"); // Directly put string
			return new ResponseEntity<ObjectNode>(jsonObject, HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/cartItem")
	public ResponseEntity<List<CartItemResponseEntity>> ListCart() {
		List<CartItemResponseEntity> cartEntity = new ArrayList<CartItemResponseEntity>();
		List<CartItem> cartList = cartItemService.findAll();
		System.out.println("Client All");
		if (cartList == null) {
			CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity();
			cartItemResponseEntity.setStatusCode(209);
			cartItemResponseEntity.setMessage("No Data");
			return new ResponseEntity(cartItemResponseEntity, HttpStatus.OK);
		} else {
			for (CartItem cartData : cartList) {
				CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity(cartData);
				
				if(cartItemResponseEntity.getUser()!=null) {
					User user = new User(cartItemResponseEntity.getUser());
					cartItemResponseEntity.setUser(user);
				}
				
				if(cartItemResponseEntity.getProduct()!=null) {
					Product product = new Product(cartItemResponseEntity.getProduct());
					cartItemResponseEntity.setProduct(product);
				}

				cartEntity.add(cartItemResponseEntity);
			}
			return new ResponseEntity<List<CartItemResponseEntity>>(cartEntity, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/cartItem/{cartId}")
	public ResponseEntity<CartItemResponseEntity> listCartById(@PathVariable("cartId") Integer id) {
	    Optional<CartItem> cartItemOptional = cartItemService.findById(id);

	    if (cartItemOptional.isEmpty()) {
	        CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity();
	        cartItemResponseEntity.setStatusCode(209);
	        cartItemResponseEntity.setMessage("No Data");
	        return new ResponseEntity<>(cartItemResponseEntity, HttpStatus.OK);
	    }

	    CartItem cartData = cartItemOptional.get();
	    CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity(cartData);

	    if (cartItemResponseEntity.getUser() != null) {
	        User user = new User(cartItemResponseEntity.getUser());
	        cartItemResponseEntity.setUser(user);
	    }

	    if (cartItemResponseEntity.getProduct() != null) {
	        Product product = new Product(cartItemResponseEntity.getProduct());
	        cartItemResponseEntity.setProduct(product);
	    }

	    return new ResponseEntity<>(cartItemResponseEntity, HttpStatus.OK);
	}

	
	
	@GetMapping("/cartItem/user/{userId}")
	public ResponseEntity<CartItemResponseEntity> listUserById(@PathVariable("userId") Integer id) {
	    Optional<CartItem> cartItemOptional = cartItemService.findByUserId(id);

	    if (cartItemOptional.isEmpty()) {
	        CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity();
	        cartItemResponseEntity.setStatusCode(209);
	        cartItemResponseEntity.setMessage("No Data");
	        return new ResponseEntity<>(cartItemResponseEntity, HttpStatus.OK);
	    }

	    CartItem cartData = cartItemOptional.get();
	    CartItemResponseEntity cartItemResponseEntity = new CartItemResponseEntity(cartData);

	    if (cartItemResponseEntity.getUser() != null) {
	        User user = new User(cartItemResponseEntity.getUser());
	        cartItemResponseEntity.setUser(user);
	    }

	    if (cartItemResponseEntity.getProduct() != null) {
	        Product product = new Product(cartItemResponseEntity.getProduct());
	        cartItemResponseEntity.setProduct(product);
	    }

	    return new ResponseEntity<>(cartItemResponseEntity, HttpStatus.OK);
	}
	
//	@DeleteMapping("delete/{id}")
//	public ResponseEntity<UserResponseEntity> deleteCartItem(@PathVariable("id") Integer id) {
//	    Optional<CartItem> cartItem = cartItemService.findById(id);
//
//	    UserResponseEntity userResponseEntity = new UserResponseEntity();
//
//	    if (cartItem.isPresent()) {
//	    	CartItem existingCart = cartItem.get();
//
//	    	cartItemService.delete(existingCart.getId());
//	            userResponseEntity.setStatusCode(200);
//	            userResponseEntity.setMessage(existingCart.getId() + " Id Deleted Successfully");
//	            return new ResponseEntity<>(userResponseEntity, HttpStatus.OK);
//	       
//	    } else {
//	        userResponseEntity.setStatusCode(404);
//	        userResponseEntity.setMessage("User Not Found");
//	        return new ResponseEntity<>(userResponseEntity, HttpStatus.NOT_FOUND);
//	    }
//	}

}
