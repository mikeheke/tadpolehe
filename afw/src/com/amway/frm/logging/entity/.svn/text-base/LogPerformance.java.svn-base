/**
 * 
 */
package com.amway.frm.logging.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.base.util.AppConstant;

/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：性能日志
 */
@Entity(name="log_LogPerformance")
@javax.persistence.Table(name="HSTB_LOG_PERFORMANCE", schema=AppConstant.APP_DEAULT_SCHEMA)
public class LogPerformance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7392663002276200377L;

	//主键
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HSSQ_LOG_PERFORMANCE_OR")
	@SequenceGenerator(name="HSSQ_LOG_PERFORMANCE_OR", sequenceName="HSSQ_LOG_PERFORMANCE", 
			initialValue=1, allocationSize=1)
	@Column(name="LOG_PERFORMANCE_ID")
	private Long logPerformanceId;
	
	@Column(name="JOB_NAME")
	private String jobName;
	
	@Column(name="RUN_TIME")
	private Long runTime;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="END_TIME")
	private Date endTime;
	
	@Column(name="RUN_FLAG")
	private Integer runFlag;

	@ManyToOne()
	@JoinColumn(name="MODULE_ID")
	private Module module;
	
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	private Application application;
	
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

	public Long getLogPerformanceId() {
		return logPerformanceId;
	}

	public void setLogPerformanceId(Long logPerformanceId) {
		this.logPerformanceId = logPerformanceId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Long getRunTime() {
		return runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getRunFlag() {
		return runFlag;
	}

	public void setRunFlag(Integer runFlag) {
		this.runFlag = runFlag;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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
	
}
