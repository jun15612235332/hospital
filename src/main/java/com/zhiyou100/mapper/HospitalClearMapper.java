package com.zhiyou100.mapper;


public interface HospitalClearMapper {


	void updateStatus(String medicalRecord);

	void addStatus(String medicalRecord);
	

	void outHospital(String medicalRecord);

}