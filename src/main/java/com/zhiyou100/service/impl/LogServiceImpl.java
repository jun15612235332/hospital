package com.zhiyou100.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.LogMapper;
import com.zhiyou100.model.Log;
import com.zhiyou100.service.LogService;
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper mapper;
	
	@Override
	public void add(Log log) {
		mapper.add(log);
	}

}
