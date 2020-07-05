package com.zhiyou100.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.RegistrationInforMapper;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.service.RegistrationService;
import com.zhiyou100.util.MyLog;
@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationInforMapper mapper;
	@Override
	public RegistrationInfor findRegistrationById(String medical_record) {
		RegistrationInfor reg=mapper.findRegistrationById(medical_record);
		return reg;
	}
	@Override
	public void add(RegistrationInfor reg) {
		mapper.add(reg);
	}
	@Override
	@MyLog("查询全部的挂号信息")
	public List<RegistrationInfor> findAll(Map<String, String> map) {
		List<RegistrationInfor> list=mapper.findAll(map);
		return list;
	}
	@Override
	public void retreat(String medicalRecord) {
		mapper.retreat(medicalRecord);
	}
	@Override
	public void update(RegistrationInfor reg) {
		mapper.update(reg);
	}
	@Override
	public void deleteByUidArr(String[] ids) {
		mapper.deleteByUidArr(ids);
	}
	@Override
	public List<RegistrationInfor> findAll() {
		List<RegistrationInfor> regs=mapper.findAll();
		return regs;
	}
	

}
