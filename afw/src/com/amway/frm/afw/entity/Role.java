package com.amway.frm.afw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.UniqueKey;

/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色
 */
@Entity(name="Role")
@Table(name="MSTB_ROLE", schema=AppConstant.APP_DEAULT_SCHEMA)
public class Role implements Serializable{

	private static final long serialVersionUID = -2212668359427388733L;
	
	//角色ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="ROLE_ID")
	private String roleId;
	
	//角色代码
	@Column(name="ROLE_CODE")
	@UniqueKey
	private String roleCode;
	
	//角色名称
	@Column(name="ROLE_NAME")
	private String roleName;
	
	//应用ID
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	private Application application;
	
	//外部SQL用户
	@Column(name="USER_SQL")
	private String userSql;
	
	//备注
	@Column(name="REMARK")
	private String remark;
	
	//使用状态
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="RECORD_STATE")
	@UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getUserSql() {
		return userSql;
	}

	public void setUserSql(String userSql) {
		this.userSql = userSql;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}
}
