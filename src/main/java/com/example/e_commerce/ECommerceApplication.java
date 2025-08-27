package com.example.e_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =	SpringApplication.run(ECommerceApplication.class, args);
	
	System.out.println("main class:"+context.getId());
	System.out.println("main class:"+context.getClass().getName());

	}

}
