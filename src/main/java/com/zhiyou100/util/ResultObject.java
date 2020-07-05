package com.zhiyou100.util;

import java.util.Map;

/*
 * 用于前后端返回数据
 */
public class ResultObject {

	private String code; // 状态码
	private String msg; // 信息
	private Object data; // 返回的数据
	


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultObject [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}


	public ResultObject(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultObject() {
		super();
	}
}
