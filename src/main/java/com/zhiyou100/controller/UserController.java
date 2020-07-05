package com.zhiyou100.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.Role;
import com.zhiyou100.model.User;
import com.zhiyou100.service.RoleService;
import com.zhiyou100.service.UserRoleService;
import com.zhiyou100.service.UserService;
import com.zhiyou100.util.MyLog;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	/*
	 * 登录判断
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@MyLog("登录")
	public String login(String username, String password, Model model, HttpSession session) {
		System.out.println("登录进来了 :username:"+username+",password: "+password);
		// 获得主体
		Subject subject = SecurityUtils.getSubject();
		try {
			System.out.println("try层");
			// 登录认证成功,返回主页面
			subject.login(new UsernamePasswordToken(username, password));
			session.setAttribute("username", username);
			return "redirect:/index.jsp#1/2";
		} catch (AuthenticationException e) {
			// 登录认证失败,返回登录页面
			// System.out.println("用户名或密码错误");
			model.addAttribute("message", "用户名或密码错误");
			return "forward:/login.jsp";
		}
	}

	/*
	 * 退出
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	@MyLog("登出")
	public String exit(HttpServletRequest request) {
		request.getSession().removeAttribute("uname");
		return "redirect:/login.jsp";
	}

	/*
	 * 查询全部用户信息
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(@RequestParam Map<String, String> map, @RequestParam(defaultValue = "1") int pageNum,
			Model model) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 3);
		// 查询全部的用户信息
		List<User> users = userService.findAll(map);
		// 获取全部的分页信息
		PageInfo<User> pageInfo = new PageInfo<>(users);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("users", users);
		model.addAttribute("map", map);
		model.addAttribute("pageNum", pageNum);
		return "/User/index";
	}

	/*
	 * 根据用户名获取信息
	 */
	 @RequestMapping(value="/detail",method=RequestMethod.GET)
	 public String findUserByUsername(String userName,Model model) {
	 User user=userService.findUserByUsername(userName);
	 List<Role> roles=roleService.findAll();
	 System.out.println(user);
	 System.out.println(roles);
	 model.addAttribute("roles", roles);
	 model.addAttribute("user", user);
	 return "/User/edit";
	 }
	/*
	 * 修改对象信息
	 */
	// @RequestMapping(value="/user/update",method=RequestMethod.POST)
	// public String update(User user,Model model) {
	//// System.out.println(user);
	// int hang=userService.update(user);
	//// System.out.println("修改"+hang+"行数据成功");
	// model.addAttribute("message", "修改成功");
	// return "forward:/user/main.do";
	// }
	/*
	 * 根据id删除信息
	 */
	// @RequestMapping(value="/user/delete",method=RequestMethod.GET)
	// public String deleteByUid(int uid,Model model) {
	// int hang=userService.deleteByUid(uid);
	// model.addAttribute("message", "删除成功");
	// return "forward:/user/main.do";
	// }
	/*
	 * 跳转至添加用户信息
	 */
	@RequestMapping(value="/forwardAdd",method=RequestMethod.GET)
	public String forwardAdd(Model model) {
		//查询全部角色
		List<Role> roles=roleService.findAll();
		model.addAttribute("roles", roles);
		return "/User/add";
	}
	/*
	 * 添加用户信息
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user,int roleId, Model model) {
		//先对密码进行加密处理
		//获得密码参数
		String password=user.getPassword();
		//设置盐值
		user.setSalt("com65456zhi456you");
		//获得加密后的密码
		String pwd = new Md5Hash(password, user.getSalt(), 10).toString();
		//设置user对象中的密码
		user.setPassword(pwd);
		//添加用户信息
		userService.add(user);
		//根据刚添加的用户名查询id值
		int userId=userService.findIdByUsername(user.getUserName());
		//插入用户和角色关联表
		userRoleService.add(userId,roleId);
		return "forward:/user/findAll.do";
	}
}
