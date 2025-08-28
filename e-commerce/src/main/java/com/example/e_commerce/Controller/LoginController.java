package com.example.e_commerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.Product;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Entity.UserType;
import com.example.e_commerce.Service.UserService;
import com.example.e_commerce.message.Response;
import com.example.e_commerce.message.UserLoginRequestEntity;
import com.example.e_commerce.message.UserResponseEntity;

import jakarta.persistence.criteria.Order;

@RestController
@RequestMapping("/api/e_commerce")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	


	    @PostMapping("/login")
	    public ResponseEntity<UserResponseEntity> loginUser(@RequestBody UserLoginRequestEntity userRequestEntity) {
	        System.out.println("Login request received.");
	        User user = userService.findAllByEmail(userRequestEntity.getEmail());

	        if (user == null) {
	            UserResponseEntity response = new UserResponseEntity();
	            response.setMessage("No such user found");
	            response.setStatusCode(409);
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	        }

	        System.out.println("Request password: " + userRequestEntity.getPassword());
	        System.out.println("Db password: " + user.getPassword());

	        if (!passwordEncoder.matches(userRequestEntity.getPassword(), user.getPassword())) {
	            UserResponseEntity response = new UserResponseEntity();
	            response.setMessage("Password invalid");
	            response.setStatusCode(400);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }

	        UserResponseEntity response = new UserResponseEntity(user.getId(), user);
	        response.setStatusCode(200);

	        if (user.getUserType() == UserType.ADMIN) {
	            response.setDescription("Welcome Admin!");
	        } else if (user.getUserType() == UserType.CUSTOMER) {
	            response.setDescription("Welcome Customer!");
	        } else {
	            response.setDescription("Login successful");
	        }

	        return ResponseEntity.ok(response);
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	@PostMapping("/login")
//	public ResponseEntity<UserResponseEntity> loginuser(@RequestBody UserLoginRequestEntity userRequestEntity) {
//
//	    System.out.println("Login request received.");
//	    User user = userService.findAllByEmail(userRequestEntity.getEmail());
//
//	    if (user == null) {
//	        UserResponseEntity response = new UserResponseEntity();
//	        response.setMessage("No such user found");
//	        response.setStatusCode(409);
//	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//	    }
//
//	    System.out.println("Request password: " + userRequestEntity.getPassword());
//	    System.out.println("Db password: " + user.getPassword());
//
//	    if (!passwordEncoder.matches(userRequestEntity.getPassword(), user.getPassword())) {
//	        UserResponseEntity response = new UserResponseEntity();
//	        response.setMessage("Password invalid");
//	        response.setStatusCode(400);
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//	    }
//
//	    // Successful login
//	    UserResponseEntity response = new UserResponseEntity(user.getId(), user);
//	    response.setStatusCode(200);
//
//	    if (user.getUserType() == UserType.ADMIN) {
//	        response.setDescription("Welcome Admin!");
//	    } else if (user.getUserType() == UserType.CUSTOMER) {
//	        response.setDescription("Welcome Customer!");
//	    } else {
//	        response.setDescription("Login successful");
//	    }
//
//	    return ResponseEntity.ok(response);
//	}

	
	
	
	
	
	
	
	
	
	

	
	
//	@PostMapping("/login")
//	public UserResponseEntity loginuser(@RequestBody UserLoginRequestEntity userRequestEntity) {
//
//	    System.out.println("Login ,");
//	    User user = userService.findAllByEmail(userRequestEntity.getEmail());
//	    System.out.println("Request password: " + userRequestEntity.getPassword());
//	    System.out.println("Db password: " + user.getPassword());
//
//	    if (user != null) {
//	    	
//	    	
//	    	if (user != null && passwordEncoder.matches(userRequestEntity.getPassword(), user.getPassword())) {
//	    		
//	    		
//	    		 if (user.getUserType() == UserType.ADMIN) {
////	 	            return ResponseEntity.ok("Welcome Admin!");
//	    			 
//	    			 UserResponseEntity employeeData = new UserResponseEntity(user.getId(), user);
//			            employeeData.setDescription("Welcome Admin!");
//			            employeeData.setStatusCode(200);
//	 	        } else if (user.getUserType() == UserType.CUSTOMER) {
////	 	            return ResponseEntity.ok("Welcome Customer!");
//	 	        	UserResponseEntity employeeData = new UserResponseEntity(user.getId(), user);
//		            employeeData.setDescription("Welcome Customer!");
//		            employeeData.setStatusCode(200);
//					return new ResponseEntity(employeeData, HttpStatus.OK);
//
//	 	        }
//	    		
//	    		
//	    	    // success
//	    		 UserResponseEntity employeeData = new UserResponseEntity(user.getId(), user);
//		            employeeData.setDescription("Login successful");
//		            employeeData.setStatusCode(200);
//					//return new ResponseEntity(employeeData, HttpStatus.OK);
//
//		            return employeeData;
//	    	}
//	    	
////	        if (passwordEncoder.matches(userRequestEntity.getPassword(), employee.getPassword())) {
////	            UserResponseEntity employeeData = new UserResponseEntity(employee.getId(), employee);
////	            employeeData.setDescription("Login successful");
////	            employeeData.setStatusCode(200);
////	            return employeeData;
////	        } 
//	    	else {
//	            UserResponseEntity employeeData = new UserResponseEntity();
//	            employeeData.setMessage("Password invalid");
//	            employeeData.setStatusCode(400);
//	            return employeeData;
//	        }
//	    }
//
//	    UserResponseEntity response = new UserResponseEntity();
//	    response.setMessage("No such user found");
//	    response.setStatusCode(409);
//	    return response;
//	}
                                  
	    
	
	
	
	
	
	
	
//	@PostMapping("/login")
//	public UserResponseEntity loginuser(@RequestBody UserLoginRequestEntity userRequestEntity) {
//		
//		System.out.println("Login ,");
//		Response response = new Response(409,"No such User Found");
//		User employee = userService.findAllByEmail(userRequestEntity.getEmail());
//		System.out.println("Request password:"+userRequestEntity.getPassword());
//
//		System.out.println("Db password:"+employee.getPassword());
//		if(employee!=null)
//		{
//			if(userService.checkPasswordIsSame(userRequestEntity.getPassword(), employee.getPassword())) {
//				UserResponseEntity employeeData = new UserResponseEntity(employee.getId(),employee);
//				
//				employeeData.setDescription("Login successfull");
//				employeeData.setStatusCode(200);
//				return employeeData;
//				}else {
//					UserResponseEntity employeeData = new UserResponseEntity();
//					employeeData.setMessage("Password invalid");
//					employeeData.setStatusCode(400);
//					return employeeData;
//				}
//			
//		}
//		return null;
//		
//
//	}	
//	$2a$10$nYmj6OcpN40eUt//XPjnnOci/9wU11HLGRszjyhmRuZi/k9SNjUFu
//	@PostMapping("/check-otp")
//	public List<OtpResponseEntity> checkOTP(@RequestBody List<OtpRequestEntity> emailOtpRequest){
//		List<OtpResponseEntity> responseList = employeeService.validateOTP(emailOtpRequest);
//		return responseList;
//	}
	


