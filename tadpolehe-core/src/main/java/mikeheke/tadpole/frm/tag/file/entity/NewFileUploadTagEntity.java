package mikeheke.tadpole.frm.tag.file.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mikeheke.tadpole.frm.base.util.AppConstant;

import org.hibernate.annotations.GenericGenerator;

/**
 * 文件上传标签实体类
 * 
 * @author Mike He
 * @createDate 2013-11-28
 *
 */
@Entity(name="NewFileUploadTagEntity")
@Table(name="MSTB_TAG_FILEUPLOAD", schema=AppConstant.APP_DEAULT_SCHEMA)
public class NewFileUploadTagEntity implements Comparable  {
	
	//自动生成ID
	@Id
	//@GenericGenerator(name="UUID_TAG_NEW_FILEUPLOAD_OR", strategy="uuid")
	//@GeneratedValue(generator="UUID_TAG_NEW_FILEUPLOAD_OR")
	@Column(name="UUID")
	private String uuid;
	
	@Column(name="BIZ_ID")
	private Long bizId;
	
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

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
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

	@Override
	public int compareTo(Object o) {
		if (o == null) {
			return -1;
		}
		
		if (!(o instanceof NewFileUploadTagEntity)) {
			return -1;
		}
		
		NewFileUploadTagEntity tt = (NewFileUploadTagEntity)o;
			
		if (tt.getCreatedTime() == null) {
			return -1;
		}
		
		//return this.getCreatedTime().compareTo(tt.getCreatedTime());
		return tt.getCreatedTime().compareTo(this.getCreatedTime());
	}
}
