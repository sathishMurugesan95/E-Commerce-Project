package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.EmailSettings;
import com.example.e_commerce.Repository.EmailSettingsRepository;



@Service
public class EmailSettingsServiceImpl implements EmailSettingsService {

	
	@Autowired
	EmailSettingsRepository emailSettingsRepository;
	
	@Override
	public EmailSettings save(EmailSettings emailSetting) {
		
		return emailSettingsRepository.save(emailSetting);
	}

	@Override
	public List<EmailSettings> findAll() {
		
		return emailSettingsRepository.findAll();
	}

	@Override
	public Optional<EmailSettings> findbyId(Integer emailsettingsId) {
		// TODO Auto-generated method stub
		return emailSettingsRepository.findById(emailsettingsId);
	}

	

	
}
