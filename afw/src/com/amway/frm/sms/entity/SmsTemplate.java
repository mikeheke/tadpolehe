package com.amway.frm.sms.entity;

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

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-4-6
 * Time: 15:37:12
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "MSTB_SMS_TEMPLATES", schema = AppConstant.APP_DEAULT_SCHEMA)
@Entity
public class SmsTemplate {
	
	//自动生成ID
	@Id
    @Column(name = "SMS_TEMPLATES_ID")
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
    private String smsTemplateId;

	//模板编号
	@Column(name = "TEMPLATES_CODE", unique=true)
    private String templateCode;

    
	//短信模板内容
	@Column(name = "SMS_TEMPLATES_CONTENT")
    private String smsTemplateContent;

	//备注
	@Column(name = "REMARK")
    private String remark;

	//使用状态
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

	public String getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSmsTemplateContent() {
		return smsTemplateContent;
	}

	public void setSmsTemplateContent(String smsTemplateContent) {
		this.smsTemplateContent = smsTemplateContent;
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
