package com.zhiyou100.util;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhiyou100.model.Log;
import com.zhiyou100.service.LogService;
import com.zhiyou100.util.MyLog;

@Component
@Aspect
public class MyAspect {
	
	@Autowired
	private LogService logService;

	/*
	 * 后置通知
	 * 注意 : 注解的切入点表达式
	 * 需求 : 记录操作时间,操作人,操作的方法,操作的描述,操作的ip 数据
	 * 时间 : new Date()
	 * 人,ip : 需要请求对象
	 * 方法 : 通过AOP的切入点得到方法名
	 * 		JoinPoint 就是目标方法对象
	 * 描述 : 通过反射读取注解
	 * ===================================
	 * 以上属性可以设计成数据库的一种表,用于记录日志信息.
	 * 对应的,在创建一个java实体类用于封装数据
	 */
	@After("@annotation(com.zhiyou100.util.MyLog)")
	public void after(JoinPoint joinPoint) {
		//创建log对象
		Log log = new Log();
		// 时间
		Date time = new Date();
		log.setOpeDate(time);
		System.out.println("日志-时间 : "+time);
		// 使用Spring提供的方法,获得HttpServletRequest对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// ip
		String ip = request.getRemoteAddr();
		log.setOpeIp(ip);
		System.out.println("日志-ip : "+ip);
		// 人名
		String name = (String) request.getSession().getAttribute("username");
		log.setOpeName(name);
		System.out.println("日志-人名 : "+name);
		// 使用joinPoint得到方法名
		String methodName = joinPoint.getSignature().getName();
		log.setOpeMethod(methodName);
		System.out.println("日志-方法 : "+methodName);
		// 得到方法上注解中的参数值
		
		// 获得目标文件的字节码对象
		// 获得方法对象
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName)) {
				// 获得方法上的注解
				String desc = method.getAnnotation(MyLog.class).value();
				log.setOpeDesc(desc);
				System.out.println("日志-描述 : "+desc);
			}
		}
		logService.add(log);
		
	}
}
