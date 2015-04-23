/**
 * 
 */
package com.amway.frm.exception.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.exception.util.ExceptionConstant;

/**
 * 
 *
 * 2011-4-20 下午03:01:15
 */
@Entity(name="exceptionCfg")
@javax.persistence.Table(name="MSTB_EXCEPTION", schema=AppConstant.APP_DEAULT_SCHEMA)
public class ExceptionCfg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3278962513989292387L;

	//主键
	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name="EXCEPTION_ID")
	private String exceptionId;
	
	@Column(name="EXCEPTION_CODE", unique=true)
	private String exceptionCode;
	
	@Column(name="EXCEPTION_NAME")
	private String exceptionName;
	
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	private Application application;
	
	@Column(name="IS_SEND_EMAIL")
	private Integer isSendEmail;
	
	@Column(name="EMAIL_USERS")
	private String emailUsers;
	
	@Column(name="USE_STATE")
	private Integer useState;
	
	@Column(name="REMARK")
	private String remark;
	
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

	public String getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Integer getIsSendEmail() {
		return isSendEmail;
	}

	public void setIsSendEmail(Integer isSendEmail) {
		this.isSendEmail = isSendEmail;
	}

	public String getEmailUsers() {
		return emailUsers;
	}

	public void setEmailUsers(String emailUsers) {
		this.emailUsers = emailUsers;
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
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

	public List<String[]> getEmailUserArr(){
		List<String[]> emailUserList = new ArrayList<String[]>();
		if(DataValidater.isStrEmpty(emailUsers)){
			return null;
		}
		
		String[] emailUserArr = emailUsers.split(ExceptionConstant.FEN_SIGN);
		if(DataValidater.isArrEmpty(emailUserArr)){
			return emailUserList;
		}
		for(String emailUser: emailUserArr){
			if(DataValidater.isStrEmpty(emailUser)){
				continue;
			}
			String[] emailUserArr2 = emailUser.split(AppConstant.SPILT_OPER);
			if(DataValidater.isArrEmpty(emailUserArr2)){
				continue;
			}
			emailUserList.add(emailUserArr2);
		}
		
		return emailUserList;
	}
}
