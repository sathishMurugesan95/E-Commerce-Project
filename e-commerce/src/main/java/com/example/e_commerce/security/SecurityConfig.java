package com.example.e_commerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;


//import com.example.e_commerce.Entity.User;

@Configuration

public class SecurityConfig {
	
	

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    http
//	        .csrf().disable()
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/api/e_commerce/**").permitAll()
//	            .anyRequest().permitAll() // 👈 allow everything
//	        );
//	    return http.build();
//	}

	
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/e_commerce/**").authenticated() // 🔒 Now requires login
	                .anyRequest().permitAll() // Keep everything else open
	            )
	            .httpBasic(); //  Enable Basic Authentication
	        return http.build();
	    }
	
	    @Bean
	    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
	        var user = User.builder()
	                .username("ecommerce")
	                .password(encoder.encode("ecommerce$admin#"))
	                .roles("ADMIN")
	                .build();
	        return new InMemoryUserDetailsManager(user);
	    }
	
	

	
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	

}
