package com.zhiyou100.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.RoleMapper;
import com.zhiyou100.model.Role;
import com.zhiyou100.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper mapper;
	@Override
	public List<Role> findAll() {
		List<Role> roles=mapper.findAll();
		return roles;
	}

}
