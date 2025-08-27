package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import com.example.e_commerce.Entity.ListTypeValues;
import com.example.e_commerce.Entity.ListTypes;



public interface ListTypeValuesService {

	List<ListTypeValues> findByListTypesAndListTypeValueNameAndStatusNot(ListTypes listTypes, String listTypeValueName,
			int i);

	ListTypeValues save(ListTypeValues listTypeValues);

	Optional<ListTypeValues> findById(Integer listTypeValueId);

	List<ListTypeValues> findByListTypesAndListTypeValueNameAndListTypeValueIdNot(ListTypes listTypes,
			String listTypeValueName, Integer listTypeValueId);

//	Optional<ListTypeValues> findByListTypes(ListTypes listType);
//
//	ListTypeValues findByListTypeValueId(Integer bloodGroup);
//
//	ListTypeValues save(ListTypeValues listTypeValues);
//
//	
//
//	Optional<ListTypeValues> findById(Integer listTypeValueId);
//
//	//new
//	List<ListTypeValues> findByListTypesAndListTypeValueNameAndStatusNot(ListTypes listTypes, String listTypeValueName,
//			int i);
//
//	//new
//	List<ListTypeValues> findByListTypesAndListTypeValueNameAndListTypeValueIdNot(ListTypes listTypes,
//			String listTypeValueName, Integer listTypeValueId);
//
//	List<ListTypeValues> findByListTypeValueName(String string);
//
//	Optional<ListTypeValues> findAllListTypeValueName(String osType);
//
////	//new 1
////	List<ListTypeValues> findByListTypeValueName(String string);

	

	
	
	
}
