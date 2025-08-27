package com.example.e_commerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.ListTypes;


public interface ListTypesRepository extends JpaRepository<ListTypes, Integer>{

	Optional<ListTypes> findBylistTypeName(String status);
}
