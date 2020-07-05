package com.zhiyou100.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.UserMapper;
import com.zhiyou100.model.User;
import com.zhiyou100.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	@Override
	public User findUserByUnameAndUpassword(String username, String password) {
		User user = mapper.findUserByUnameAndUpassword(username, password);
		return user;
	}
	@Override
	public List<String> findRolesByUsername(String username) {
		List<String> roles=mapper.findRolesByUsername(username);
		return roles;
	}
	@Override
	public List<String> findPermisByRoles(List<String> roles) {
		List<String> permis=mapper.findPermisByRoles(roles);
		return permis;
	}
	@Override
	public User findUserByUname(String username) {
		System.out.println("业务层进来了 : "+username);
		User user=mapper.findUserByUname(username);
		System.out.println("查询得到的user : "+user);
		return user;
	}
	@Override
	public List<User> findAll(Map<String, String> map) {
		List<User> users=mapper.findAll(map);
		return users;
	}
	@Override
	public void add(User user) {
		mapper.add(user);
	}
	@Override
	public int findIdByUsername(String userName) {
		int id=mapper.findIdByUsername(userName);
		return id;
	}
	@Override
	public User findUserByUsername(String userName) {
		User user=mapper.findUserByUsername(userName);
		return user;
	}

}
