/**
 * 
 */
package com.amway.frm.logging.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：系统日志
 */
//@Entity(name="log_LogSystem")
@javax.persistence.Table(name="HSTB_LOG_SYSTEM", schema=AppConstant.APP_DEAULT_SCHEMA)
public class LogSystem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044727068513220647L;

	//主键
	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name="LOG_SYSTEM_ID")
	private String logSystemId;
	
	@Column(name="EMP_NUMBER")
	private String empNumber;
	
	@Column(name="USER_LOCATION")
	private String userLocation;
	
	@Column(name="STACK_MSG")
	private String stackMsg;
	
	@Column(name="EXCEPTION_TYPE")
	private String exceptionType;
	
	@Column(name="EXCEPTION_CLASS")
	private String exceptionClass;
	
	@Column(name="LOG_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;
	
	@Column(name="LOG_CONTENT")
	private String logContent;
	
	@ManyToOne()
	@JoinColumn(name="MODULE_ID")
	private String moduleId;
	
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	private String applicationId;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="RECORD_STATE")
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

	public String getLogSystemId() {
		return logSystemId;
	}

	public void setLogSystemId(String logSystemId) {
		this.logSystemId = logSystemId;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getStackMsg() {
		return stackMsg;
	}

	public void setStackMsg(String stackMsg) {
		this.stackMsg = stackMsg;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		StringBuffer msg = new StringBuffer();
		
		final String tmp1 = "LogSystem [exceptionClass=";
		msg.append(tmp1).append(exceptionClass);
		final String tmp2 = ", exceptionType=";
		msg.append(tmp2).append(exceptionType);
		final String tmp3 = ", logContent=";
		msg.append(tmp3).append(logContent);
		final String tmp4 = ", logTime=";
		msg.append(tmp4).append(logTime);
		final String tmp5 = ", stackMsg=";
		msg.append(tmp5).append(stackMsg);
		final String tmp6 = "]";
		msg.append(tmp6);
		
		return msg.toString();
	}
	
	
}
