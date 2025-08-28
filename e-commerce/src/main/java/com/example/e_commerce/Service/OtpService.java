package com.example.e_commerce.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	// In-memory storage for OTPs: email -> otp
    private final Map<String, String> otpStorage = new HashMap<>();

    /**
     * Generates a 6-digit random OTP
     */
    public String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 000000 - 999999
    }

    /**
     * Stores the OTP for a specific email
     */
    public void storeOtpForUser(String email, String otp) {
        otpStorage.put(email, otp);
    }

    /**
     * Generates and stores OTP in one step
     */
    public String generateOTP(String email) {
        String otp = generateOtp();
        storeOtpForUser(email, otp);
        return otp;
    }

    /**
     * Verifies if the provided OTP matches the stored one
     */
    public boolean verifyOtp(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }

    /**
     * Clears the stored OTP for an email (after successful use)
     */
    public void clearOtp(String email) {
        otpStorage.remove(email);
    }
}
