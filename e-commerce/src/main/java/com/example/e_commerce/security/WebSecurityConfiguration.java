package com.example.e_commerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.e_commerce.Entity.User;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
	
////	 @Bean
////	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////	        http
////	            .csrf().disable()
////	            .authorizeHttpRequests(auth -> auth
////	                .requestMatchers("/api/auth/**").permitAll() // Allow forgot-password, register, login, etc.
////	                .anyRequest().authenticated()
////	            )
////	            .httpBasic(); // Or formLogin()
////
////	        return http.build();
////	    }
//	
////	protected void configure(HttpSecurity http) throws Exception {
////	    http
////	        .csrf().disable()
////	        .authorizeHttpRequests()
////	        .requestMatchers("/api/e_commerce/adduserr").permitAll() // 👈 Make public
////	        .anyRequest().authenticated()
////	        .and()
////	        .httpBasic();
////	}
//	
//	protected void configure(HttpSecurity http) throws Exception {
//	    http
//	        .authorizeHttpRequests()
//	        .requestMatchers("/api/e_commerce/adduserr**").authenticated()  // 👈 Requires login
//	        .anyRequest().permitAll()
//	        .and()
//	        .httpBasic(); // 👈 This enables Basic Auth
//	}
//	
//	
////	  @Bean
////	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////		  http
////	        .csrf().disable()
////	        .authorizeHttpRequests(auth -> auth
////	            .requestMatchers(
////	                "/api/auth/**",  // open endpoints: register, login, forgot-password
////	                "/products/**", 
////	                "/orders/**",
////	                "/carts/**"
////	            ).permitAll()
////	            .anyRequest().authenticated()  // all other routes require authentication
////	        )
////	        .httpBasic(); // Or use .formLogin() if using a login form
////
////	    return http.build();
////	}
//	
//	
////	    @Bean
////	    public PasswordEncoder passwordEncoder() {
////	        return new BCryptPasswordEncoder(); // Needed for password encoding
////	    }
//	    
//	    
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
}
