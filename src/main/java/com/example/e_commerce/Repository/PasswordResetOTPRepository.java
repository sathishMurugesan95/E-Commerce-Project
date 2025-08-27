package com.example.e_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.PasswordResetOTP;

public interface PasswordResetOTPRepository extends JpaRepository<PasswordResetOTP, Integer>{
	PasswordResetOTP findByEmail(String email);
    PasswordResetOTP findByEmailAndOtp(String email, String otp);
}
