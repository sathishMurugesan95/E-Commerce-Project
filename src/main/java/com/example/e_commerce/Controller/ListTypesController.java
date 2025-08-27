package com.example.e_commerce.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.ListTypes;
import com.example.e_commerce.Repository.ListTypesRepository;
import com.example.e_commerce.message.ListTypesResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce")
public class ListTypesController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ListTypesRepository listTypesRepository;

	ListTypesResponseEntity res = new ListTypesResponseEntity();

	@GetMapping("/list-types")
	public ResponseEntity listListTypes() {
		List<ListTypesResponseEntity> resEntity = new ArrayList<ListTypesResponseEntity>();
		List<ListTypes> listTypes = listTypesRepository.findAll();
		if (listTypes.isEmpty()) {

		} else {
			for (ListTypes l : listTypes) {
				ListTypesResponseEntity managementResponseEntity = new ListTypesResponseEntity(l);
				resEntity.add(managementResponseEntity);
			}
		}
		if (resEntity.isEmpty()) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", 204); // Just directly set the value
			jsonObject.put("message", "no data"); // Directly put string

			return new ResponseEntity<ObjectNode>(jsonObject, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<ListTypesResponseEntity>>(resEntity, HttpStatus.OK);
		}
	}

}
