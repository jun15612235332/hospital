package com.zhiyou100.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.ChargeManagerMapper;
import com.zhiyou100.model.ChargeManager;
import com.zhiyou100.service.ChargeManagerService;
@Service
public class ChargeManagerServiceImpl implements ChargeManagerService{

	@Autowired
	private ChargeManagerMapper mapper;
	@Override
	public List<ChargeManager> findAll(Map<String, String> map) {
		List<ChargeManager> list=mapper.findAll(map);
		return list;
	}
	@Override
	public void add(ChargeManager ch) {
		mapper.add(ch);
	}
	@Override
	public ChargeManager findChargeById(int id) {
		ChargeManager ch=mapper.findChargeById(id);
		return ch;
	}
	@Override
	public void update(ChargeManager ch) {
		mapper.update(ch);
	}
	@Override
	public void delete(int id) {
		mapper.delete(id);
	}

}
