/**
 * 
 */
package com.amway.frm.query.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amway.frm.base.util.AppConstant;

/**
 * @author huangweijin
 *
 * 2011-5-20 上午10:16:18
 */
@Entity
@Table(name = "MSTB_BDS_SCHEMAINFOR", schema = AppConstant.APP_DEAULT_SCHEMA)
public class DataCoding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1467280606097799354L;

	@Id
	@Column(name = "BDS_SCHEMAINFOR_ID")
	private Long bdsSchemainforId;
	
	@Column(name = "BDS_SCHEMAINFOR_CODE")
	private String bdsSchemainforCode;
	
	@Column(name = "BDS_SCHEMAINFOR_NAME_CNA")
	private String bdsSchemainforNameCna;

	public Long getBdsSchemainforId() {
		return bdsSchemainforId;
	}

	public void setBdsSchemainforId(Long bdsSchemainforId) {
		this.bdsSchemainforId = bdsSchemainforId;
	}

	public String getBdsSchemainforCode() {
		return bdsSchemainforCode;
	}

	public void setBdsSchemainforCode(String bdsSchemainforCode) {
		this.bdsSchemainforCode = bdsSchemainforCode;
	}

	public String getBdsSchemainforNameCna() {
		return bdsSchemainforNameCna;
	}

	public void setBdsSchemainforNameCna(String bdsSchemainforNameCna) {
		this.bdsSchemainforNameCna = bdsSchemainforNameCna;
	}
	
	
}
