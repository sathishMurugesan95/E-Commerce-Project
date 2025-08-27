package com.example.e_commerce.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Entity.UserStatus;
import com.example.e_commerce.Service.CartItemService;
import com.example.e_commerce.Service.OrderEntityService;
import com.example.e_commerce.Service.ProductService;
import com.example.e_commerce.Service.UserService;
import com.example.e_commerce.message.CartItemRequestEntity;
import com.example.e_commerce.message.UserRequestEntity;
import com.example.e_commerce.message.UserResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({ "rawtypes", "unused", "unchecked", "static-access" })
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce")

public class UserController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderEntityService OrderEntityService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	UserResponseEntity userResponse = new UserResponseEntity();

	@PostMapping("/adduserr")
	public ResponseEntity<UserResponseEntity> createUser(@RequestBody UserRequestEntity userRequestEntity) {
		System.out.println("add user");
		User UserData = new User(userRequestEntity);

		List<User> emailValid = userService.findByEmail(userRequestEntity.getEmail());
		if (emailValid.size() != 0) {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setStatusCode(409);
			userResponseEntity.setDescription("User Email Already Exist");
			return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
		}
		System.out.println("UserData:" + UserData.getEmail());
		
		String encodedPassword = passwordEncoder.encode(userRequestEntity.getPassword());
		UserData.setPassword(encodedPassword); // Set encoded password
		
		
		User userSave = userService.save(UserData);

		if (userSave != null) {
			UserResponseEntity userResponse = new UserResponseEntity();
			userResponse.setStatusCode(200);
			userResponse.setDescription("User Created Successfully");
			return new ResponseEntity(userResponse, HttpStatus.OK);
		} else {
			UserResponseEntity userResponse = new UserResponseEntity();
			userResponse.setStatusCode(200);
			userResponse.setDescription("Unable to Create User");
			return new ResponseEntity(userResponse, HttpStatus.OK);
		}

	}

	@PutMapping("/updateuser/{userId}")
	public ResponseEntity uploadUser(@PathVariable("userId") Integer id,
			@RequestBody UserRequestEntity userRequestEntity) {
		Optional<User> findUsrId = userService.findById(id);
		if (id == 0) {
			UserResponseEntity userResponse = new UserResponseEntity();
			userResponse.setStatusCode(409);
			userResponse.setMessage("UsersId is Null");
			return new ResponseEntity(userResponse, HttpStatus.OK);
		}
		if (findUsrId.isPresent()) {

			User UserData = new User(findUsrId, userRequestEntity);

			List<User> emailValid = userService.findByEmail(userRequestEntity.getEmail());
			if (emailValid.size() != 0) {
				UserResponseEntity userResponseEntity = new UserResponseEntity();
				userResponseEntity.setStatusCode(409);
				userResponseEntity.setDescription("User Email Already Exist");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}
			String encodedPassword = passwordEncoder.encode(userRequestEntity.getPassword());
			UserData.setPassword(encodedPassword); // Set encoded password
			
			User userSave = userService.save(UserData);

			if (userSave != null) {

				UserResponseEntity userResponseEntity = new UserResponseEntity();
				userResponseEntity.setStatusCode(200);
				userResponseEntity.setDescription("User Update Successfully");
				return new ResponseEntity(userResponseEntity, HttpStatus.OK);

			} else {
				// unable to
				UserResponseEntity userResponseEntity = new UserResponseEntity();
				userResponseEntity.setStatusCode(409);
				userResponseEntity.setDescription("Unable to Update user");
				return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
			}

		}
		UserResponseEntity userResponseEntity = new UserResponseEntity();
		userResponseEntity.setStatusCode(409);
		userResponseEntity.setDescription("Not found" + findUsrId + "user");
		return new ResponseEntity(userResponseEntity, HttpStatus.CONFLICT);
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserResponseEntity>> getUser() {
		List<UserResponseEntity> userEntity = new ArrayList<UserResponseEntity>();
		List<User> userList = userService.findAll();
		System.out.println("Client All");
		if (userList == null) {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setStatusCode(209);
			userResponseEntity.setMessage("No Data");
			return new ResponseEntity(userResponseEntity, HttpStatus.OK);
		} else {
			for (User user : userList) {
				
				 if (user.getUserStatus() != UserStatus.INACTIVE) {
				UserResponseEntity userResponseEntity = new UserResponseEntity(user);
				
//				System.out.println("user Type:"+userResponseEntity);

				userEntity.add(userResponseEntity);
				 }
			}
			return new ResponseEntity<List<UserResponseEntity>>(userEntity, HttpStatus.OK);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponseEntity> getUserId(@PathVariable("id") Integer id) {
	    Optional<User> user = userService.findById(id);
	    UserResponseEntity userResponseEntity = new UserResponseEntity();

	    if (user.isPresent()) {
	        if (user.get().getUserStatus() == UserStatus.INACTIVE) {
	            userResponseEntity.setStatusCode(403);
	            userResponseEntity.setMessage("User is inactive and cannot be accessed.");
	            return new ResponseEntity<>(userResponseEntity, HttpStatus.FORBIDDEN);
	        }

	        userResponseEntity = new UserResponseEntity(user.get());
	        userResponseEntity.setStatusCode(200);
	        userResponseEntity.setMessage("User found");
	        return new ResponseEntity<>(userResponseEntity, HttpStatus.OK);
	    } else {
	        userResponseEntity.setStatusCode(404);
	        userResponseEntity.setMessage("User with ID: " + id + " not found.");
	        return new ResponseEntity<>(userResponseEntity, HttpStatus.NOT_FOUND);
	    }
	}


	@DeleteMapping("delete/{id}")
	public ResponseEntity<UserResponseEntity> deleteUser(@PathVariable("id") Integer id) {
	    Optional<User> user = userService.findById(id);

	    UserResponseEntity userResponseEntity = new UserResponseEntity();

	    if (user.isPresent()) {
	        User existingUser = user.get();

	        if (user.get().getUserStatus() == UserStatus.INACTIVE) {
	            userService.delete(existingUser.getId());
	            userResponseEntity.setStatusCode(200);
	            userResponseEntity.setMessage(existingUser.getId() + " Id Deleted Successfully");
	            return new ResponseEntity<>(userResponseEntity, HttpStatus.OK);
	        } else {
	            userResponseEntity.setStatusCode(400);
	            userResponseEntity.setMessage("User is ACTIVE. Only INACTIVE users can be deleted.");
	            return new ResponseEntity<>(userResponseEntity, HttpStatus.BAD_REQUEST);
	        }
	    } else {
	        userResponseEntity.setStatusCode(404);
	        userResponseEntity.setMessage("User Not Found");
	        return new ResponseEntity<>(userResponseEntity, HttpStatus.NOT_FOUND);
	    }
	}


}
