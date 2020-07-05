package com.zhiyou100.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhiyou100.model.User;
import com.zhiyou100.service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 通过身份参数,获得身份即用户名
		String username = (String) principals.getPrimaryPrincipal();
		// 根据用户名查数据库,查询对应的所有角色
		List<String> roles = userService.findRolesByUsername(username);
		// 根据角色查询数据库,查询每个角色对应的所有权限
		List<String> permis = userService.findPermisByRoles(roles);
		// 特别注意,有可能角色与角色有重复的权限,所以在存储时要注意去重
		//将权限转化为set集合去重
		Set<String> permissions=new HashSet<>(permis);
		// 将角色信息,授权信息赋值给AuthorizationInfo即可
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);
		info.addStringPermissions(permissions);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从令牌中得到用户名密码
		// 得到用户名
		String username = (String) token.getPrincipal();
		// 得到密码 --> 得到是字符数组
		// 所以需要再变为字符串
		String password = new String((char[]) token.getCredentials());
		// 根据用户名查询数据库
		User user = userService.findUserByUname(username);
		System.out.println("根据用户名查询数据库的对象 : "+user);
		// 判断用户存在不存在,密码是否正确
		if (user == null) {
			System.out.println("出错了");
			throw new AuthenticationException();
		}
//		System.out.println("Realm中输出 :"+user);
		//获得盐值
		String salt = user.getSalt();
		//将盐值转换为shiro需要的数据类型ByteSource
		ByteSource byteSource = ByteSource.Util.bytes(salt);
		//把数据库中的用户名和密码,盐值都交给shiro,shiro会进行对比
		
		// 如果都正确,将信息放入AuthenticationInfo返回
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),byteSource, getName());
	}

}
