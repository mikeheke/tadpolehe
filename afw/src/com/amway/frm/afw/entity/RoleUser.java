package com.amway.frm.afw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.UniqueKey;

/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色用户
 */
@Entity(name="RoleUser")
@Table(name="MSTB_ROLE_USER", schema=AppConstant.APP_DEAULT_SCHEMA)
public class RoleUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8141094960697711124L;

	//ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="ROLE_USER_ID")
	private String roleUserId;
	
	@Column(name="ROLE_ID")
	@UniqueKey
	private String roleId;
	
	@Column(name="USERPROFILE_ID")
	@UniqueKey
	private String userProfileId;
	
	@Column(name="RECORD_STATE")
	@UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="CREATED_USER_ID")
	private String createdUserId;
	
	@Column(name="IS_LOCAL_RIGHT")
	private int isLocalRight;
	
	@Transient
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRoleUserId() {
		return roleUserId;
	}

	public void setRoleUserId(String roleUserId) {
		this.roleUserId = roleUserId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
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

	public int getIsLocalRight() {
		return isLocalRight;
	}

	public void setIsLocalRight(int isLocalRight) {
		this.isLocalRight = isLocalRight;
	}


}
