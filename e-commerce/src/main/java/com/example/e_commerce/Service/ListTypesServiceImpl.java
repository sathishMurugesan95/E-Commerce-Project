package com.example.e_commerce.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.ListTypes;
import com.example.e_commerce.Repository.ListTypesRepository;



@Service
public class ListTypesServiceImpl implements ListTypesService{

	@Autowired
	ListTypesRepository listTypesRepository;
	
	@Override
	public Optional<ListTypes> findBylistTypeName(String status) {
		// TODO Auto-generated method stub
		return listTypesRepository.findBylistTypeName(status);
	}

}
