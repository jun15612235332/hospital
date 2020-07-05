package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.HospitalInfor;

public interface HospitalInforMapper {

	List<HospitalInfor> findAll(Map<String, String> map);

	HospitalInfor findHospitalInforById(String medical_record);

	void update(HospitalInfor hos);

	void add(HospitalInfor hos);

	
}