package com.zhiyou100.model;

import java.io.Serializable;

public class Image implements Serializable{

	String path;//路径
	String id;//id
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Image [path=" + path + ", id=" + id + "]";
	}

	
}
