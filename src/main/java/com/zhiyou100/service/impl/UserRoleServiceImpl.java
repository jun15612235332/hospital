package com.zhiyou100.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.UserRoleMapper;
import com.zhiyou100.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper mapper;
	@Override
	public void add(int userId, int roleId) {
		mapper.add(userId,roleId);
	}

}
