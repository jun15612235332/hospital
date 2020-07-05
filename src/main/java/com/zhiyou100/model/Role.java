package com.zhiyou100.model;

import java.io.Serializable;

public class Role implements Serializable{
    private Integer id;

    private String roleNum;

    private String roleName;

    private Integer statuss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum == null ? null : roleNum.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getStatuss() {
        return statuss;
    }

    public void setStatuss(Integer statuss) {
        this.statuss = statuss;
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleNum=" + roleNum + ", roleName=" + roleName + ", statuss=" + statuss + "]";
	}
    
}