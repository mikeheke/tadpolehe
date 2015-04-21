/**
 * 
 */
package com.amway.frm.job.entity;

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

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.UniqueKey;
import com.amway.frm.job.util.JobConstant;

/**
 * 
 *
 * 2011-9-26 下午03:25:44
 */
@Entity(name="TimeingJob") 
@Table(name="MSTB_TIMEING_JOB", schema=AppConstant.APP_DEAULT_SCHEMA)
public class TimeingJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4606611395241860023L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="TIMEING_JOB_ID")
	private String timeingJobId;
	
	@Column(name="TIMEING_JOB_CODE")
	@UniqueKey
	private String timeingJobCode;
	
	@Column(name="TIMEING_JOB_NAME")
	private String timeingJobName;
	
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	private Application application;
	
	@Column(name="CLASS_NAME")
	private String className;
	
	@Column(name="METHOD_NAME")
	private String methodName;
	
	@Column(name="START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name="END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column(name="CYCLE")
	private Integer cycle;
	
	@Column(name="CYCLE_UNIT")
	private Integer cycleUnit;
	
	@Column(name="CRON_JOB_BUNCH")
	private String cronJobBunch;
	
	@Column(name="FAIL_NUM")
	private Integer failNum;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="RECORD_STATE")
    @UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="LAST_START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastStartTime;
	
	@Column(name="LAST_END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastEndTime;
	
	@Column(name="RUN_TIME")
	private Long runTime;
	
	@Column(name="LAST_FAIL_NUM")
	private Integer lastFailNum;
	
	@Column(name="RUN_STATE")
	private Integer runState;

	public String getTimeingJobId() {
		return timeingJobId;
	}

	public void setTimeingJobId(String timeingJobId) {
		this.timeingJobId = timeingJobId;
	}

	public String getTimeingJobCode() {
		return timeingJobCode;
	}

	public void setTimeingJobCode(String timeingJobCode) {
		this.timeingJobCode = timeingJobCode;
	}

	public String getTimeingJobName() {
		return timeingJobName;
	}

	public void setTimeingJobName(String timeingJobName) {
		this.timeingJobName = timeingJobName;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
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

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getCycleUnit() {
		return cycleUnit;
	}

	public void setCycleUnit(Integer cycleUnit) {
		this.cycleUnit = cycleUnit;
	}

	public String getCronJobBunch() {
		return cronJobBunch;
	}

	public void setCronJobBunch(String cronJobBunch) {
		this.cronJobBunch = cronJobBunch;
	}

	public Integer getFailNum() {
		return failNum;
	}
	
	public Integer getFailNum2() {
		if(failNum==null){
			return JobConstant.DEFAULT_FAIL_NUM;
		}
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
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

	public Date getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(Date lastStartTime) {
		this.lastStartTime = lastStartTime;
	}

	public Date getLastEndTime() {
		return lastEndTime;
	}

	public void setLastEndTime(Date lastEndTime) {
		this.lastEndTime = lastEndTime;
	}

	public Long getRunTime() {
		return runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public Integer getLastFailNum() {
		return lastFailNum;
	}

	public void setLastFailNum(Integer lastFailNum) {
		this.lastFailNum = lastFailNum;
	}

	public Integer getRunState() {
		return runState;
	}

	public void setRunState(Integer runState) {
		this.runState = runState;
	}
	
	
}
