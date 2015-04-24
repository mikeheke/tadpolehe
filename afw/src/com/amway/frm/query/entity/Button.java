/**
 * 
 */
package com.amway.frm.query.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.query.util.QueryConstant;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：按钮
 */
@Entity(name="query_Button")
@javax.persistence.Table(name="MSTB_QUERY_BUTTON", schema=QueryConstant.APP_DEAULT_SCHEMA)
public class Button implements Serializable, Comparable<Button> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7366775263658300195L;

	//主键
	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name="BUTTON_ID")
	private String buttonId;
	
	//查询ID
	@ManyToOne()
	@JoinColumn(name="QUERY_ID")
	private Query query;
	
	//按钮名称
	@Column(name="BUTTON_NO")
	private String buttonNo;
	
	//按钮描述
	@Column(name="BUTTON_NAME")
	private String buttonName;
	
	//提交URL
	@Column(name="SUB_URL")
	private String subUrl;
	
	//打开类型
	@Column(name="OPEN_TYPE")
	private Integer openType;
	
	//执行JS
	@Column(name="EXECUTE_JS")
	private String executeJs;
	
	//排序
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
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
	
	//操作标志
	@Transient
	private String oprtFlag = QueryConstant.QRY_OPRT;

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getButtonNo() {
		return buttonNo;
	}

	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getSubUrl() {
		return subUrl;
	}

	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public String getExecuteJs() {
		return executeJs;
	}

	public void setExecuteJs(String executeJs) {
		this.executeJs = executeJs;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getOprtFlag() {
		return oprtFlag;
	}

	public void setOprtFlag(String oprtFlag) {
		this.oprtFlag = oprtFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buttonId == null) ? 0 : buttonId.hashCode());
		result = prime * result
				+ ((oprtFlag == null) ? 0 : oprtFlag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (DataValidater.isFalse(getClass().equals(obj.getClass()))){
			return false;
		}
		Button other = (Button) obj;
		if (buttonId == null) {
			if (other.buttonId != null){
				return false;
			}
		} else if (!buttonId.equals(other.buttonId)){
			return false;
		}
		if (oprtFlag == null) {
			if (other.oprtFlag != null){
				return false;
			}
		} else if (!oprtFlag.equals(other.oprtFlag)){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Button o) {
		if(null == o){
			return 0;
		}
		return this.getOrderNo().compareTo(o.getOrderNo());
	}
}
