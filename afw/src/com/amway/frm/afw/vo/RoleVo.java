/**
 * 
 */
package com.amway.frm.afw.vo;

import com.amway.frm.afw.entity.Application;

/**
 * @author huangweijin
 *
 * 2011-5-10 下午03:47:30
 */
public class RoleVo {

	//角色ID
	private String roleId;
	
	//角色ID数组
	private String[] roleIds;
	
	//角色编码
	private String roleCode;
	
	//角色名称
	private String roleName;
	
	//所属应用
	private String  applicationId;
	
	//状态
	private String state;
	
	//默认角色数据来源
	private String bdsCode;
	
	//备注
	private String remark;
	
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getBdsCode() {
		return bdsCode;
	}

	public void setBdsCode(String bdsCode) {
		this.bdsCode = bdsCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
}
