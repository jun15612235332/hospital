package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Doctor;

public interface DoctorService {

	List<Doctor> findAll(Map<String, String> map);
	
	List<Doctor> findAll();

	void add(Doctor doctor);

	Doctor findDoctorById(int id);

	void update(Doctor doctor);

}
