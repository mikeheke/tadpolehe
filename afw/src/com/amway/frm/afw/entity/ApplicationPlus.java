package com.amway.frm.afw.entity;

import java.sql.Timestamp;
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
 * 应用系统扩展信息表
 * @author Feng Hanhao
 * Created date: 2011-5-31
 */
@Entity(name="ApplicationPlus")
@Table(name = "MSTB_APPLICATION_PLUS", schema=AppConstant.APP_DEAULT_SCHEMA)
public class ApplicationPlus implements java.io.Serializable {

	private static final long serialVersionUID = 3102423902018337530L;
	//自动生成ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "APPLICATION_PLUS_ID")
	private String applicationPlusId;
	
	//所属应用
	@ManyToOne
	@UniqueKey
	@JoinColumn(name = "APPLICATION_ID",nullable = true)
	private Application application;
	
	//参数编码
	@Column(name = "PARAMETER_CODE")
	@UniqueKey
	private String parameterCode;
	
	//参数名
	@Column(name = "PARAMETER_NAME")
	private String parameterName;
	
	//参数值
	@Column(name = "PARAMETER_VALUE")
	private String parameterValue;
	
	//备注
	@Column(name = "REMARK")
	private String remark;
	
	//使用状态
	@Column(name = "STATE")
	private Integer state;
	
	//记录状态
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


	// Property accessors


	public Application getApplication() {
		return application;
	}

	public String getApplicationPlusId() {
		return applicationPlusId;
	}

	public void setApplicationPlusId(String applicationPlusId) {
		this.applicationPlusId = applicationPlusId;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getParameterCode() {
		return this.parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}


	public String getParameterName() {
		return this.parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}


	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}


	public String getRemark() {
		return this.remark;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}


	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}


	public String getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}


	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

}