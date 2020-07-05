package com.zhiyou100.util;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {

	// 参数 : 描述方法
	String value() default "";
	
	/**
	 * @see value()
	 * @return
	 */
	String desc() default "";
}