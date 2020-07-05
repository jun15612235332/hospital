package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Drug;

public interface DrugService {

	List<Drug> findAll(Map<String, String> map);

	void add(Drug drug);

	Drug findMedicineById(String drugNum);

	void update(Drug drug);

}
