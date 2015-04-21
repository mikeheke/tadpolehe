/**
 * 
 */
package com.amway.frm.report.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;

/**
 * 
 *
 * 2011-9-6 下午03:07:51
 */
@Entity(name="ReportCache") 
@Table(name="MSTB_REPORT_CACHE", schema=AppConstant.APP_DEAULT_SCHEMA)
public class ReportCache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4542790545928354039L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="REPORT_CACHE_ID")
	private String reportCacheId;
	
	@Column(name="REPORT_CODE")
	private String reportCode;

	@Column(name="REPORT_PARAS")
	private String reportParas;
	
	@Column(name="RECORD_STATE")
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
	
	@Column(name="FILE_PATH")
	private String filePath;
	
	@Column(name="FILE_R_PATH")
	private String fileRPath;

	public String getReportCacheId() {
		return reportCacheId;
	}

	public void setReportCacheId(String reportCacheId) {
		this.reportCacheId = reportCacheId;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getReportParas() {
		return reportParas;
	}

	public void setReportParas(String reportParas) {
		this.reportParas = reportParas;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileRPath() {
		return fileRPath;
	}

	public void setFileRPath(String fileRPath) {
		this.fileRPath = fileRPath;
	}

}
