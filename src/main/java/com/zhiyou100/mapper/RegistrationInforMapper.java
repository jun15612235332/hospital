package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou100.model.RegistrationInfor;

public interface RegistrationInforMapper {

	List<RegistrationInfor> findAll(Map<String, String> map);

	RegistrationInfor findRegistrationById(String medical_record);

	void add(RegistrationInfor reg);

	void retreat(String medicalRecord);

	void update(RegistrationInfor reg);

	void deleteByUidArr(String[] ids);

	List<RegistrationInfor> findAll();

	
}