package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
//	 @Autowired
//	    private PasswordEncoder passwordEncoder;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;

	@Override
	public User save(User userData) {
		// TODO Auto-generated method stub
		
		
//		 // Hash the raw password
//		String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt());
//		// Set the hashed password back to user object
//	    userData.setPassword(hashedPassword);
//	    // Save and return the user
		
		
		

//		String encodedPassword = passwordEncoder.encode(userData.getPassword());
//		userData.setPassword(encodedPassword);
//	    return userRepository.save(userData);
		
//	    userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		
		
//		List<User> emailValid = userRepository.findByEmail(userData.getEmail());
//		if (!emailValid.isEmpty()) {
//		    throw new EmailAlreadyExistsException("Email already exists: " + userData.getEmail());
//		}
        return userRepository.save(userData);
		    

	}

	@Override
	public List<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail( email);
	}

	@Override
	public Optional<User> findById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public boolean checkPasswordIsSame(String password, String password2) {
		// TODO Auto-generated method stub
		return BCrypt.checkpw(password, password2);
	}

	@Override
	public User findAllByEmail(String email) {
		return userRepository.findAllByEmail(email);
	}

	@Override
	public User findByEmailSingle(String email) {
		 return userRepository.findByEmail(email)
                 .stream()
                 .findFirst()
                 .orElse(null);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail( email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		return;
	}

	

}
