package com.example.e_commerce.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce.Entity.ListTypeValues;
import com.example.e_commerce.Entity.ListTypes;
import com.example.e_commerce.Repository.ListTypeValuesRepository;
import com.example.e_commerce.Service.ListTypeValuesService;
import com.example.e_commerce.message.ListTypeValuesRequestEntity;
import com.example.e_commerce.message.ListTypeValuesResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/e_commerce")
public class ListTypeValuesController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ListTypeValuesRepository listTypeValuesRepository;

	@Autowired
	ListTypeValuesService listTypeValuesService;

	ListTypeValuesResponseEntity res = new ListTypeValuesResponseEntity();

	@PostMapping("/list-type-values")
	private ResponseEntity createListTypeValue(@RequestBody ListTypeValuesRequestEntity listTypeValuesRequestEntity) {

//		List<ListTypeValues> listTypeValueList = new ArrayList<ListTypeValues>();
//		for(ListTypeValuesRequestEntity listTypeValuesData : listTypeValuesRequestEntity) {
		ListTypeValues listTypeValues = new ListTypeValues(listTypeValuesRequestEntity);
		List<ListTypeValues> list = listTypeValuesService.findByListTypesAndListTypeValueNameAndStatusNot(
				listTypeValues.getListTypes(), listTypeValues.getListTypeValueName(), 4);

		System.out.println("address" + list);
		if (!list.isEmpty()) {
			ListTypeValuesResponseEntity menuResponseEntity = new ListTypeValuesResponseEntity();
			menuResponseEntity.setStatusCode(409);
			menuResponseEntity
					.setDescription("Designation Name" + listTypeValues.getListTypeValueName() + " Already exist");
			return new ResponseEntity(menuResponseEntity, HttpStatus.OK);
		}

		ListTypeValues menuDetails = listTypeValuesService.save(listTypeValues);

//		}

		ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity();
		listTypeValuesResponseEntity.setStatusCode(200);
		listTypeValuesResponseEntity.setDescription("Designation Created Successfully");
		return new ResponseEntity(listTypeValuesResponseEntity, HttpStatus.OK);
	}

	@PutMapping("/list-type-values/{listTypeValueId}")
	public ResponseEntity uploadListTypeValue(@PathVariable("listTypeValueId") Integer listTypeValueId,
			@RequestBody ListTypeValuesRequestEntity listTypeValuesRequestEntity) {

		Optional<ListTypeValues> list = listTypeValuesService.findById(listTypeValueId);

		if (listTypeValueId == 0) {
			ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity();
			listTypeValuesResponseEntity.setStatusCode(409);
			listTypeValuesResponseEntity.setMessage("DesignationId Null");
			return new ResponseEntity(listTypeValuesResponseEntity, HttpStatus.OK);
		}
		if (list.isPresent()) {

			List<ListTypeValues> listTypeValueList = new ArrayList<ListTypeValues>();
//			for(ListTypeValuesRequestEntity listTypeValuesData : listTypeValuesRequestEntity) {
			ListTypeValues listTypeValues = new ListTypeValues(list, listTypeValuesRequestEntity);

			List<ListTypeValues> listValue = listTypeValuesService
					.findByListTypesAndListTypeValueNameAndListTypeValueIdNot(listTypeValues.getListTypes(),
							listTypeValues.getListTypeValueName(), listTypeValueId);

			if (listValue.size() != 0) {
				ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity();
				listTypeValuesResponseEntity.setStatusCode(409);
				listTypeValuesResponseEntity
						.setDescription("Designation Name" + listTypeValues.getListTypeValueName() + " Already exist");
				return new ResponseEntity(listTypeValuesResponseEntity, HttpStatus.OK);
			}

			ListTypeValues menuDetails = listTypeValuesService.save(listTypeValues);

//		}
			ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity();
			listTypeValuesResponseEntity.setStatusCode(200);
			listTypeValuesResponseEntity.setDescription("Designation Updated Successfully");
			return new ResponseEntity(listTypeValuesResponseEntity, HttpStatus.OK);
		} else {
			ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity();
			listTypeValuesResponseEntity.setStatusCode(409);
			listTypeValuesResponseEntity.setDescription("DesignationId Not Found");
			return new ResponseEntity(listTypeValuesResponseEntity, HttpStatus.OK);
		}
	}

	@GetMapping("/list-type-values/{listTypeId}")
	public ResponseEntity listListTypeValues(@PathVariable("listTypeId") Integer listTypesId) {
		ListTypes listTypes = new ListTypes(listTypesId);
		System.out.println("list type Id:"+listTypes.getListTypeId());

		System.out.println("list type:"+listTypes.getListTypeName());
		List<ListTypeValuesResponseEntity> resEntity = new ArrayList<ListTypeValuesResponseEntity>();
		List<ListTypeValues> listTypeValues = listTypeValuesRepository
				.findAllByListTypesOrderByListTypeValueIdDesc(listTypes);
		if (listTypeValues.isEmpty()) {

		} else {
			for (ListTypeValues l : listTypeValues) {
				if (l.getStatus() == null || l.getStatus().equals(1) || l.getStatus().equals(2)
						|| l.getStatus().equals(3)) {
					ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity(l);
					resEntity.add(listTypeValuesResponseEntity);
				}
			}
		}
		if (resEntity.isEmpty()) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", 204); // Just directly set the value
			jsonObject.put("message", "no data"); // Directly put string

			return new ResponseEntity<ObjectNode>(jsonObject, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<ListTypeValuesResponseEntity>>(resEntity, HttpStatus.OK);
		}
	}

	@GetMapping("/list-type-value/{listTypeValueId}")
	public ResponseEntity listTypeValues(@PathVariable("listTypeValueId") Integer listTypeValueId) {
		ListTypeValues listTypeValues = new ListTypeValues(listTypeValueId);
//		List<ListTypeValuesResponseEntity> resEntity = new ArrayList<ListTypeValuesResponseEntity>();
		Optional<ListTypeValues> listTypeValuesList = listTypeValuesRepository.findById(listTypeValueId);
		if (listTypeValuesList.isPresent() == false) {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("statusCode", 204); // Just directly set the value
			jsonObject.put("message", "no data"); // Directly put string

			return new ResponseEntity<ObjectNode>(jsonObject, HttpStatus.BAD_REQUEST);
		} else {
//			for (ListTypeValues l : listTypeValuesList) {
			ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity();
			ListTypeValues listTypeValues1 = listTypeValuesList.get();
			if (listTypeValues1.getStatus() == null || listTypeValues1.getStatus().equals(1)
					|| listTypeValues1.getStatus().equals(2) || listTypeValues1.getStatus().equals(3)) {
				listTypeValuesResponseEntity = new ListTypeValuesResponseEntity(listTypeValues1);
//				ListTypeValuesResponseEntity listTypeValuesResponseEntity = new ListTypeValuesResponseEntity(listTypeValues1);
//				resEntity.add(listTypeValuesResponseEntity);
			}
//			}
			return new ResponseEntity(listTypeValuesResponseEntity, HttpStatus.OK);
		}
//		if (resEntity.isEmpty()) {
//			ObjectNode jsonObject = objectMapper.createObjectNode();
//			jsonObject.put("statusCode", res.setStatusCode(204));
//			jsonObject.put("message", res.setDescription("No Data"));
//			return new ResponseEntity(jsonObject, HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<List<ListTypeValuesResponseEntity>>(resEntity, HttpStatus.OK);
//		}
	}

}
