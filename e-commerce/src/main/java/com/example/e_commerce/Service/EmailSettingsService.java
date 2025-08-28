package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import com.example.e_commerce.Entity.EmailSettings;
import com.example.e_commerce.Entity.OrderEntity;
import com.example.e_commerce.Entity.OrderItem;


public interface EmailSettingsService {

	EmailSettings save(EmailSettings emailSetting);

	List<EmailSettings> findAll();

	Optional<EmailSettings> findbyId(Integer emailsettingsId);
	
//    void sendPartialRefundEmail(String email, List<OrderItem> itemsToRemove, double totalRefund, OrderEntity order);


}
