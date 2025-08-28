package com.example.e_commerce.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.EmailSettings;
import com.example.e_commerce.Service.EmailSettingsService;
import com.example.e_commerce.message.EmailSettingsRequestEntity;
import com.example.e_commerce.message.EmailSettingsResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EmailSettingsController {

	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Autowired
	private EmailSettingsService emailSettingsService;
	
	
	EmailSettingsResponseEntity res = new EmailSettingsResponseEntity();
	
	
	@PostMapping("/emailsettings")
	public ResponseEntity CreateCalendarWorkingdays(@RequestBody EmailSettingsRequestEntity EmailSettingsRequestEntity) {
		
		
			EmailSettings emailSetting = new EmailSettings(EmailSettingsRequestEntity);
			 
			EmailSettings emailSettings = emailSettingsService.save(emailSetting);
			if (emailSettings != null) {
				EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
				emailSettingsResponseEntity.setStatusCode(200);
				emailSettingsResponseEntity.setDescription("EmailSettings Created Successfully");
				
				return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.OK);
			} else {
				EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
				emailSettingsResponseEntity.setStatusCode(409);
				emailSettingsResponseEntity.setDescription("Unable to Create EmailSettings");
				
				return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.CONFLICT);
			}
		
		
	}
	
	
	@GetMapping("/emailsettings")
	public ResponseEntity<List<EmailSettingsResponseEntity>> ListcalendarEmailSettings() {
		List<EmailSettingsResponseEntity> resEntity = new ArrayList<EmailSettingsResponseEntity>();
		List<EmailSettings> EmailSettings = emailSettingsService.findAll();
		if (EmailSettings.isEmpty()) {
			EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
			emailSettingsResponseEntity.setStatusCode(204);
			emailSettingsResponseEntity.setDescription("no data");
			return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.BAD_REQUEST);
			
			
		} else {
			for (EmailSettings emailSetting : EmailSettings) {
				  
					  EmailSettingsResponseEntity EmailSettingsResponseEntity = new EmailSettingsResponseEntity(emailSetting, emailSetting);		  
							resEntity.add(EmailSettingsResponseEntity);
						
				  
			
			}
			if (resEntity.isEmpty()) {
				EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
				emailSettingsResponseEntity.setStatusCode(204);
				emailSettingsResponseEntity.setDescription("no data");
				return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<EmailSettingsResponseEntity>>(resEntity, HttpStatus.OK);
			}
		}
	}
	
	
//	@PutMapping("/emailsettings/{emailsettingId}")
//	public ResponseEntity<Calender> updateLeave(@RequestBody EmailSettingsRequestEntity EmailSettingsRequestEntity) {
//		Optional<EmailSettings> Id = emailSettingsService.findbyId(EmailSettingsRequestEntity.getEmailsettingsId());
//		if (Id.isPresent()) {
//			EmailSettings EmailSettings =   new EmailSettings(EmailSettingsRequestEntity);
//			EmailSettings EmailSettings2 =   emailSettingsService.save(EmailSettings);
//			 if(EmailSettings2 != null)
//			 {
//					EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
//					emailSettingsResponseEntity.setStatusCode(200);
//					emailSettingsResponseEntity.setDescription("EmailSettings Updated Successfully");
//					return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.OK);
//			 }else {
//				 
//				 EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
//					emailSettingsResponseEntity.setStatusCode(409);
//					emailSettingsResponseEntity.setDescription("Unable to Update EmailSettings");
//					return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.CONFLICT);
//			 }
//			
//		} else {
//			
//			 EmailSettingsResponseEntity emailSettingsResponseEntity = new EmailSettingsResponseEntity();
//				emailSettingsResponseEntity.setStatusCode(404);
//				emailSettingsResponseEntity.setDescription(" EmailSettings with id =" + EmailSettingsRequestEntity.getEmailsettingsId() + " not found");
//			
//		
//			return new ResponseEntity(emailSettingsResponseEntity, HttpStatus.NOT_FOUND);
//		}
//	}
}
