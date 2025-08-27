package com.example.e_commerce.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Service.UserService;
import com.example.e_commerce.message.UserRequestEntity;
import com.example.e_commerce.message.UserResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@SuppressWarnings({ "rawtypes", "unused", "unchecked", "static-access" })
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ChangePasswordController {
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	private Logger logger = LoggerFactory.getLogger("ChangePasswordController");

	UserResponseEntity res = new UserResponseEntity();

	@PutMapping("/change_password/{id}")
	public ResponseEntity changePassword(@PathVariable("id") Integer id,
			@RequestBody UserRequestEntity userRequestEntity) throws IOException {
		Optional<User> user = userService.findById(id);
		boolean result = passwordEncoder.matches(userRequestEntity.getOldPassword(), user.get().getPassword());
		if (result) {
//			user.get().setPassword(bCryptPasswordEncoder.encode(userRequestEntity.getNewPassword()));
			
			user.get().setPassword(passwordEncoder.encode(userRequestEntity.getNewPassword()));

			userService.save(user.get());
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setStatusCode(200);
			userResponseEntity.setDescription("Password Changed Successfully");
		
			return new ResponseEntity(userResponseEntity, HttpStatus.OK);
		} else {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setStatusCode(404);
			userResponseEntity.setDescription("Password Missmatch");
			return new ResponseEntity(userResponseEntity, HttpStatus.BAD_REQUEST);
		}
	}
   
//	@PutMapping("/forget_password/{id}")
//	public ResponseEntity ForgetPassword(@PathVariable("id") Integer id,
//			@RequestBody UserRequestEntity userRequestEntity) throws IOException {
//		Optional<User> user = userService.findById(id);
////		boolean result = passwordEncoder.matches(employeeRequestEntity.getOldPassword(), emp.get().getPassword());
//		if (user.isPresent()) 
//		{
//			user.get().setPassword(bCryptPasswordEncoder.encode(userRequestEntity.getPassword()));
//			userService.save(user.get());
//			UserResponseEntity userResponseEntity = new UserResponseEntity();
//			userResponseEntity.setStatusCode(200);
//			userResponseEntity.setDescription("Password Changed Successfully");
//		
//			return new ResponseEntity(userResponseEntity, HttpStatus.OK);
//		} 
//		else 
//		{
//			UserResponseEntity userResponseEntity = new UserResponseEntity();
//			userResponseEntity.setStatusCode(404);
//			userResponseEntity.setDescription("User Id Not Found");
//			return new ResponseEntity(userResponseEntity, HttpStatus.BAD_REQUEST);
//		}
//	}
}
