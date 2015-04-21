package com.amway.frm.afw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;

/**
 * 应用系统配置Entity
 * @author hyc
 */
@Entity(name="Config")
@Table(name="MSTB_CFG", schema=AppConstant.APP_DEAULT_SCHEMA)
public class Config implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8739363693671654127L;
	
	
	//自动生成ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "ID")
	private String id;
	
	//key
	@Column(name = "CFG_KEY")
	private String cfgKey;
	
	//值
	@Column(name = "CFG_VALUE")
	private String cfgValue;
	
	//备注
	@Column(name = "REMARK")
	private String remark;
	
	
	@Column(name = "CREATE_USER",updatable=false)
	private String createdUserId;
	
	@Column(name = "CREATE_TIME",updatable=false)
	private Date createdTime;
	
	@Column(name = "EDIT_USER")
	private String updatedUserId;
	
	@Column(name = "UPDATE_TIME")
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCfgKey() {
		return cfgKey;
	}

	public void setCfgKey(String cfgKey) {
		this.cfgKey = cfgKey;
	}

	public String getCfgValue() {
		return cfgValue;
	}

	public void setCfgValue(String cfgValue) {
		this.cfgValue = cfgValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
