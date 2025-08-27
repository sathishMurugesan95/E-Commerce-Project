package com.example.e_commerce.Entity;

import java.util.Random;

public class OtpUtil {
	
	 public static String generateOTP() {
	        Random random = new Random();
	        return String.format("%06d", random.nextInt(999999));
	    }

}
