package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Doctor;

public interface DoctorMapper {

	List<Doctor> findAll(Map<String, String> map);

	List<Doctor> findAll();
	
	void add(Doctor doctor);

	Doctor findDoctorById(int id);

	void update(Doctor doctor);
}