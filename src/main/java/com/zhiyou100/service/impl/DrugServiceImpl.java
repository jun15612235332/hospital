package com.zhiyou100.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.DrugMapper;
import com.zhiyou100.model.Drug;
import com.zhiyou100.service.DrugService;
@Service
public class DrugServiceImpl implements DrugService {

	@Autowired
	private DrugMapper mapper;
	@Override
	public List<Drug> findAll(Map<String, String> map) {
		List<Drug> list=mapper.findAll(map);
		return list;
	}
	@Override
	public void add(Drug drug) {
		mapper.add(drug);
	}
	@Override
	public Drug findMedicineById(String drugNum) {
		Drug drug=mapper.findMedicineById(drugNum);
		return drug;
	}
	@Override
	public void update(Drug drug) {
		mapper.update(drug);
	}

}
