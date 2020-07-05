package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.User;

public interface UserService {

	User findUserByUnameAndUpassword(String username, String password);

	List<String> findRolesByUsername(String username);

	List<String> findPermisByRoles(List<String> roles);

	User findUserByUname(String username);

	List<User> findAll(Map<String, String> map);

	void add(User user);

	int findIdByUsername(String userName);

	User findUserByUsername(String userName);

}
