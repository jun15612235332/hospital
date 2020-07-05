package com.zhiyou100.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.SectionMapper;
import com.zhiyou100.model.Section;
import com.zhiyou100.service.SectionService;
@Service
public class SectionServiceImpl implements SectionService{

	@Autowired
	private SectionMapper mapper;
	@Override
	public List<Section> findAll() {
		List<Section> sections=mapper.findAll();
		return sections;
	}

}
