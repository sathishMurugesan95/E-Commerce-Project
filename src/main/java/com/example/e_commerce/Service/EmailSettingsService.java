package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import com.example.e_commerce.Entity.EmailSettings;


public interface EmailSettingsService {

	EmailSettings save(EmailSettings emailSetting);

	List<EmailSettings> findAll();

	Optional<EmailSettings> findbyId(Integer emailsettingsId);

}
