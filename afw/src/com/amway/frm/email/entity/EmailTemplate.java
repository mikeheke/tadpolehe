package com.amway.frm.email.entity;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-4-12
 * Time: 11:10:03
 * Declare:
 */


@Table(name = "MSTB_EMAIL_TEMPLATES", schema = AppConstant.APP_DEAULT_SCHEMA)
@Entity
public class EmailTemplate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6449584503172935984L;

	//自动生成ID
	@Column(name = "EMAIL_TEMPLATES_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSSQ_EMAIL_TEMPLATES_OR")
    @SequenceGenerator(name = "MSSQ_EMAIL_TEMPLATES_OR", sequenceName = "MSSQ_EMAIL_TEMPLATES",
            initialValue = 1, allocationSize = 1)
    private Long emailTemplateId;

	@Column(name = "TEMPLATES_CODE", unique=true)
    private String templateCode;

	@Column(name = "TEMPLATES_NAME")
    private String templateName;

	@Column(name = "EMAIL_TEMPLATES_SUBJECT")
    private String emailTemplateSubject;

	@Column(name = "EMAIL_TEMPLATES_CONTENT")
    private String emailTemplateContent;

	@Column(name = "ACCESSORY_FLAG")
    private Integer accessoryFlag;

	@Column(name = "SEND_FLAG")
    private Integer sendFlag;

	@Column(name = "REMARK")
    private String remark;

	@Column(name = "STATE")
    private Integer state;

	@Column(name="RECORD_STATE")
	private Integer recordState = AppConstant.START;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	@ManyToOne()
	@JoinColumn(name = "APPLICATION_ID")
    private Application application;

	public Long getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(Long emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getEmailTemplateSubject() {
		return emailTemplateSubject;
	}

	public void setEmailTemplateSubject(String emailTemplateSubject) {
		this.emailTemplateSubject = emailTemplateSubject;
	}

	public String getEmailTemplateContent() {
		return emailTemplateContent;
	}

	public void setEmailTemplateContent(String emailTemplateContent) {
		this.emailTemplateContent = emailTemplateContent;
	}

	public Integer getAccessoryFlag() {
		return accessoryFlag;
	}

	public void setAccessoryFlag(Integer accessoryFlag) {
		this.accessoryFlag = accessoryFlag;
	}

	public Integer getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
