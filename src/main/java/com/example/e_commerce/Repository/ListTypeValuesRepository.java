package com.example.e_commerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce.Entity.ListTypeValues;
import com.example.e_commerce.Entity.ListTypes;


public interface ListTypeValuesRepository extends JpaRepository<ListTypeValues, Integer>{

	List<ListTypeValues> findByListTypesAndListTypeValueNameAndStatusNot(ListTypes listTypes, String listTypeValueName,
			int i);

	List<ListTypeValues> findByListTypesAndListTypeValueNameAndListTypeValueIdNot(ListTypes listTypes,
			String listTypeValueName, Integer listTypeValueId);

	List<ListTypeValues> findAllByListTypesOrderByListTypeValueIdDesc(ListTypes listTypes);


}
