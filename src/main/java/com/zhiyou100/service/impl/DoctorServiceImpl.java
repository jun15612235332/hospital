package com.zhiyou100.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.DoctorMapper;
import com.zhiyou100.model.Doctor;
import com.zhiyou100.service.DoctorService;
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorMapper mapper;
	@Override
	public List<Doctor> findAll(Map<String, String> map) {
		List<Doctor> doctors = mapper.findAll(map);
		return doctors;
	}
	@Override
	public void add(Doctor doctor) {
		mapper.add(doctor);
	}
	@Override
	public Doctor findDoctorById(int id) {
		Doctor doctor=mapper.findDoctorById(id);
		return doctor;
	}
	@Override
	public void update(Doctor doctor) {
		mapper.update(doctor);
	}
	@Override
	public List<Doctor> findAll() {
		List<Doctor> doctors = mapper.findAll();
		return doctors;
	}

}
