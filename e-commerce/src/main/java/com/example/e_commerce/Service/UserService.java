package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import com.example.e_commerce.Entity.User;
import com.example.e_commerce.message.UserRequestEntity;

public interface UserService {

	User save(User userData);

	List<User> findByEmail(String email);

	Optional<User> findById(Integer id);

	boolean checkPasswordIsSame(String password, String password2);

	User findAllByEmail(String email);

	User findByEmailSingle(String email);

	Optional<User> findUserByEmail(String email);

	List<User> findAll();

	void delete(Integer id);





}
