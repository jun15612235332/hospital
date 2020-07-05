package com.zhiyou100.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.HospitalInforMapper;
import com.zhiyou100.model.HospitalInfor;
import com.zhiyou100.service.HospitalInforService;
@Service
public class HospitalInforServiceImpl implements HospitalInforService {


	@Autowired
	private HospitalInforMapper mapper;
	
	@Override
	public List<HospitalInfor> findAll(Map<String, String> map) {
		List<HospitalInfor> list=mapper.findAll(map);
		return list;
	}

	@Override
	public HospitalInfor findHospitalInforById(String medical_record) {
		HospitalInfor hos=mapper.findHospitalInforById(medical_record);
		return hos;
	}

	@Override
	public void update(HospitalInfor hos) {
		mapper.update(hos);
	}

	@Override
	public void add(HospitalInfor hos) {
		mapper.add(hos);
	}

	

}
