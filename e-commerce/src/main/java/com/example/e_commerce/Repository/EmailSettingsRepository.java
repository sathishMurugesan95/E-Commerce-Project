package com.example.e_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.EmailSettings;


public interface EmailSettingsRepository  extends JpaRepository<EmailSettings, Integer>{

}
