package com.amway.frm.job.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.amway.frm.base.util.AppConstant;

/**
 * 
 * @author Xie Jinnan
 *
 */
@Entity
@Table(name = "MSTB_SA_USER_AUTH_TMP", schema = AppConstant.APP_DEAULT_SCHEMA)
public class UserAuth implements java.io.Serializable {

	private static final long serialVersionUID = 7964381508497317886L;

	@Id
	@Basic(optional = false)
	@Column(name = "USER_AUTH_ID", unique = true, nullable = false, precision = 8, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSSQ_SA_USER_AUTH_IC")
	@SequenceGenerator(name = "MSSQ_SA_USER_AUTH_IC", sequenceName = "MSSQ_SA_USER_AUTH", initialValue = 1, allocationSize = 1)
	private Long userAuthId;

	// 店铺编码
	@Column(name = "SHOP_CODE")
	private String shopCode;

	// 应用编码
	@Column(name = "APPLICATION_CODE")
	private String applicationCode;

	// 权限编码
	@Column(name = "RIGHT_CODE")
	private String rightCode;

	// 用户账号
	@Column(name = "EMP_NUMBER")
	private String empNumber;

	// 记录状态
	@Column(name = "RECORD_STATE", nullable = false)
	private Integer recordState = AppConstant.START;
	// 创建人
	@Column(name = "CREATED_USER_ID", updatable = false)
	private String createdUserId;
	// 创建时间
	@Column(name = "CREATED_TIME", updatable = false)
	private Date createdTime;
	// 更新人
	@Column(name = "UPDATED_USER_ID")
	private String updatedUserId;
	// 更新时间
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	public Long getUserAuthId() {
		return userAuthId;
	}

	public void setUserAuthId(Long userAuthId) {
		this.userAuthId = userAuthId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof UserAuth)) {
			return false;
		}
		UserAuth auth = (UserAuth) o;
		if (this.shopCode != null && this.applicationCode != null
				&& this.empNumber != null && this.rightCode != null) {
			return this.shopCode.equals(auth.getShopCode())
					&& this.applicationCode.equals(auth.getApplicationCode())
					&& this.empNumber.equals(auth.getEmpNumber())
					&& this.rightCode.equals(auth.rightCode);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return shopCode.hashCode() * applicationCode.hashCode() * empNumber.hashCode() * rightCode.hashCode();
	}
}