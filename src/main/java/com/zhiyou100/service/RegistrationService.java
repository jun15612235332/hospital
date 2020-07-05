package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.RegistrationInfor;

public interface RegistrationService {

	RegistrationInfor findRegistrationById(String medical_record);

	void add(RegistrationInfor reg);

	List<RegistrationInfor> findAll(Map<String, String> map);

	void retreat(String medicalRecord);

	void update(RegistrationInfor reg);

	void deleteByUidArr(String[] ids);

	List<RegistrationInfor> findAll();

	


}
