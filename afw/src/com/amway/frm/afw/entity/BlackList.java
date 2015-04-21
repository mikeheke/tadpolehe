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

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.UniqueKey;

/**
 * 应用系统黑名单信息表Entity
 * 
 */
@Entity(name="BlackList")
@Table(name="MSTB_BLACKLIST", schema=AppConstant.APP_DEAULT_SCHEMA)
public class BlackList implements Serializable{
	private static final long serialVersionUID = 8102612510192093373L;
	
	//自动生成ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "BLACKLIST_ID")
	private String blackListId;
	//应用ID
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	@UniqueKey
	private Application application;
	
	//用户ID
	@ManyToOne()
	@JoinColumn(name="USERPROFILE_ID")
	@UniqueKey
	private UserProfile userProfile;
	
	//设置时间
	@Column(name = "BLACKLIST_TIME")
	private Date blackListTime;
	//备注
	@Column(name = "REMARK")
	private String remark;
	//使用状态
	@Column(name = "STATE")
	private Integer state;
	
	@Column(name = "RECORD_STATE")
	@UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name = "CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	@Column(name = "CREATED_TIME",updatable=false)
	private Date createdTime;
	
	@Column(name = "UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	public String getBlackListId() {
		return blackListId;
	}

	public void setBlackListId(String blackListId) {
		this.blackListId = blackListId;
	}


	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Date getBlackListTime() {
		return blackListTime;
	}

	public void setBlackListTime(Date blackListTime) {
		this.blackListTime = blackListTime;
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

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	
}
