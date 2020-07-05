package com.zhiyou100.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.HospitalClearMapper;
import com.zhiyou100.service.HospitalClearService;
@Service
public class HospitalClearServiceImpl implements HospitalClearService{

	@Autowired
	private HospitalClearMapper mapper;
	@Override
	public void updateStatus(String medicalRecord) {
		mapper.updateStatus(medicalRecord);
	}
	@Override
	public void addStatus(String medicalRecord) {
		mapper.addStatus(medicalRecord);
	}

	@Override
	public void outHospital(String medicalRecord) {
		mapper.outHospital(medicalRecord);
	}

}
