package com.example.e_commerce.Service;

import java.util.Optional;

import com.example.e_commerce.Entity.ListTypes;


public interface ListTypesService {

	Optional<ListTypes> findBylistTypeName(String status);

}
