package com.zhiyou100.service;

public interface HospitalClearService {


	void updateStatus(String medicalRecord);

	void addStatus(String medicalRecord);


	void outHospital(String medicalRecord);
}
