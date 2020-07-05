package com.zhiyou100.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou100.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	User findUserByUnameAndUpassword(@Param("username")String username, @Param("password")String password);

	List<String> findRolesByUsername(String username);

	List<String> findPermisByRoles(List<String> roles);

	User findUserByUname(String username);

	List<User> findAll(Map<String, String> map);

	void add(User user);

	int findIdByUsername(String userName);

	User findUserByUsername(String userName);
}