package com.zhiyou100.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.PayItemsMapper;
import com.zhiyou100.model.PayItems;
import com.zhiyou100.service.PayItemsService;
@Service
public class PayItemsServiceImpl implements PayItemsService {

	@Autowired
	private PayItemsMapper mapper;
	@Override
	public List<PayItems> findAll() {
		List<PayItems> list=mapper.findAll();
		return list;
	}
	@Override
	public List<String> findAllName() {
		List<String> itemNames=mapper.findAllName();
		return itemNames;
	}

}
