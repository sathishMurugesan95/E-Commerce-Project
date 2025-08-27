package com.example.e_commerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.Entity.ListTypeValues;
import com.example.e_commerce.Entity.ListTypes;
import com.example.e_commerce.Repository.ListTypeValuesRepository;



@Service
public class ListTypeValuesServiceImpl implements ListTypeValuesService{

	@Autowired
	ListTypeValuesRepository listTypevaluesRepository;
	
//	@Override
//	public Optional<ListTypeValues> findByListTypes(ListTypes listType) {
//		// TODO Auto-generated method stub
//		return listTypevaluesRepository.findByListTypes(listType);
//	}
//	@Override
//	public ListTypeValues findByListTypeValueId(Integer bloodGroup) {
//		// TODO Auto-generated method stub
//		return listTypevaluesRepository.findByListTypeValueId(bloodGroup);
//	}
	
	
	@Override
	public ListTypeValues save(ListTypeValues listTypeValues) {
		// TODO Auto-generated method stub
		return listTypevaluesRepository.save(listTypeValues);
	}
//	
//	
	@Override
	public Optional<ListTypeValues> findById(Integer listTypeValueId) {
		return listTypevaluesRepository.findById(listTypeValueId);
	}
	@Override
	public List<ListTypeValues> findByListTypesAndListTypeValueNameAndStatusNot(ListTypes listTypes,
			String listTypeValueName, int i) {
		return listTypevaluesRepository.findByListTypesAndListTypeValueNameAndStatusNot(listTypes, listTypeValueName, i);
	}
//	@Override
//	public List<ListTypeValues> findByListTypesAndListTypeValueNameAndListTypeValueIdNot(ListTypes listTypes,
//			String listTypeValueName, Integer listTypeValueId) {
//		return listTypevaluesRepository.findByListTypesAndListTypeValueNameAndListTypeValueIdNot(listTypes, listTypeValueName, listTypeValueId);
//	}
////	@Override
////	public List<ListTypeValues> findByListTypeValueName(String string) {
////		return listTypevaluesRepository.findByListTypeValueName(string);
////	}
////	@Override
////	public List<ListTypeValues> findByListTypeValueName(String string, String string2) {
////		// TODO Auto-generated method stub
////		return listTypevaluesRepository.findByListTypeValueName(string, string2);
////	}
//	@Override
//	public List<ListTypeValues> findByListTypeValueName(String string) {
//		// TODO Auto-generated method stub
//		return listTypevaluesRepository.findAllByListTypeValueName(string);
//	}
//	@Override
//	public Optional<ListTypeValues> findAllListTypeValueName(String osType) {
//		// TODO Auto-generated method stub
//		return listTypevaluesRepository.findByListTypeValueName(osType);
//	}
//	
//	@Override
//	public Optional<ListTypeValues> findById(Integer listTypeValueId) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
	@Override
	public List<ListTypeValues> findByListTypesAndListTypeValueNameAndListTypeValueIdNot(ListTypes listTypes,
			String listTypeValueName, Integer listTypeValueId) {
		// TODO Auto-generated method stub
		return listTypevaluesRepository.findByListTypesAndListTypeValueNameAndListTypeValueIdNot( listTypes,
				 listTypeValueName,  listTypeValueId);
	}

//	@Override
//	public List<ListTypeValues> findByListTypesAndListTypeValueNameAndStatusNot(ListTypes listTypes,
//			String listTypeValueName, int i) {
//		
//		return null;
//	}
	
	
	
	
	
}
