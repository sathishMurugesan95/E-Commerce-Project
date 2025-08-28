package com.example.e_commerce.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.PasswordResetOTP;
import com.example.e_commerce.Entity.User;
import com.example.e_commerce.Repository.PasswordResetOTPRepository;
import com.example.e_commerce.Repository.UserRepository;
import com.example.e_commerce.Service.UserService;

@RestController
//@RequestMapping("/api/auth")
@RequestMapping("/api/e_commerce")

public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordResetOTPRepository otpRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // STEP 1: Send OTP
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Optional<User> user = userService.findUserByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate 6-digit OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        PasswordResetOTP resetOtp = new PasswordResetOTP();
        resetOtp.setEmail(email);
        resetOtp.setOtp(otp);
        resetOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepo.save(resetOtp);
         System.out.println("forgot Email");
        // Send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for Password Reset");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);

        return ResponseEntity.ok("OTP sent to email");
    }

    
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");

        PasswordResetOTP resetOtp = otpRepo.findByEmailAndOtp(email, otp);

        Map<String, String> response = new HashMap<>();

        if (resetOtp == null || resetOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            response.put("message", "Invalid or expired OTP");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<User> userOpt = userService.findUserByEmail(email);
        if (userOpt.isEmpty()) {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.save(user);

        otpRepo.delete(resetOtp);

        response.put("message", "Password reset successfully");
        return ResponseEntity.ok(response);
    }

    
    
    
    // STEP 2: Verify OTP and Set New Password
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        String otp = request.get("otp");
//        String newPassword = request.get("newPassword");
//
//        PasswordResetOTP resetOtp = otpRepo.findByEmailAndOtp(email, otp);
//
//        if (resetOtp == null || resetOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
//        }
//
//        Optional<User> userOpt = userService.findUserByEmail(email);
//        System.out.println("user Email:"+userOpt.get().getEmail());
//        System.out.println("user password:"+userOpt.get().getPassword());
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        User user = userOpt.get();
//        user.setPassword(passwordEncoder.encode(newPassword));
//      User  usave =  userService.save(user);
//      System.out.println("user email:"+usave.getEmail());
//
//      System.out.println("userPasword:"+usave.getPassword());
//
//        otpRepo.delete(resetOtp);
//
//        return ResponseEntity.ok("Password reset successfully");
//    }


}
