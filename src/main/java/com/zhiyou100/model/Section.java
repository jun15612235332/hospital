package com.zhiyou100.model;

import java.io.Serializable;

//科室
public class Section implements Serializable{
    private Integer id;

    private String seccpNum;

    private String seccoName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeccpNum() {
        return seccpNum;
    }

    public void setSeccpNum(String seccpNum) {
        this.seccpNum = seccpNum == null ? null : seccpNum.trim();
    }

    public String getSeccoName() {
        return seccoName;
    }

    public void setSeccoName(String seccoName) {
        this.seccoName = seccoName == null ? null : seccoName.trim();
    }

	@Override
	public String toString() {
		return "Section [id=" + id + ", seccpNum=" + seccpNum + ", seccoName=" + seccoName + "]";
	}
    
}