package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Drug;

public interface DrugMapper {

	List<Drug> findAll(Map<String, String> map);

	void add(Drug drug);

	Drug findMedicineById(String drugNum);

	void update(Drug drug);
}