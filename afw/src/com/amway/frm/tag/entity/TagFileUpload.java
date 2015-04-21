/**
 * 
 */
package com.amway.frm.tag.entity;

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
 * 2011-6-23 下午07:12:17
 */
@Entity(name="TagFileUpload")
@Table(name="MSTB_TAG_FILEUPLOAD", schema=AppConstant.APP_DEAULT_SCHEMA)
public class TagFileUpload implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457676070429528327L;
	
	//自动生成ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="UUID")
	private String uuid;
	
	@Column(name="BIZ_ID")
	private Integer bizId;
	
	@Column(name="CN_NAME")
	private String cnName;
	
	@Column(name="FILE_ENCRYPT_NAME")
	private String fileEncryptName;
	
	@Column(name="SAVE_PATH")
	private String savePath;
	
	@Column(name="MODULE_ID")
	private String moduleId;
	
	@Column(name="APPLICATION_ID")
	private String applicationId;
	
	@Column(name="UPLOAD_USER")
	private String uploadUser;
	
	@Column(name="FILE_TYPE")
	private String fileType;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="STATE")
	private Integer state;

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
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

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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

	public String getFileEncryptName() {
		return fileEncryptName;
	}

	public void setFileEncryptName(String fileEncryptName) {
		this.fileEncryptName = fileEncryptName;
	}
	
}
