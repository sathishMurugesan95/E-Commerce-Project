package com.example.e_commerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByEmail(String email);

	User findAllByEmail(String email);

	Optional<User> findUserByEmail(String email);

//	User findByEmailSingle(String email);

}
