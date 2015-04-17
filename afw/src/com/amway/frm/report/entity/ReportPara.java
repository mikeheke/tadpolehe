/**
 * 
 */
package com.amway.frm.report.entity;

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

import com.amway.frm.base.util.AppConstant;

/**
 * @author huangweijin
 *
 * 2011-9-6 下午03:09:02
 */
@Entity(name="ReportPara")
@Table(name="MSTB_REPORT_PARA", schema=AppConstant.APP_DEAULT_SCHEMA)
public class ReportPara implements Serializable, Comparable<ReportPara>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1772312452143562328L;

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MSSQ_REPORT_PARA_OR")
	@SequenceGenerator(name="MSSQ_REPORT_PARA_OR", sequenceName="MSSQ_REPORT_PARA",
			initialValue=1, allocationSize=1)
	@Column(name="REPORT_PARA_ID")
	private Long reportParaId;
	
	@ManyToOne()
	@JoinColumn(name="REPORT_INFO_ID")
	private ReportInfo reportInfo;
	
	@Column(name="DATA_TYPE")
	private String dataType;
	
	@Column(name="PARA_DISPLAY_NAME")
	private String paraDisplayName;
	
	@Column(name="DATA_DISPLAY_TYPE")
	private String dataDisplayType;
	
	@Column(name="PARA_NAME")
	private String paraName;
	
	@Column(name="DATA_CODING")
	private String dataCoding;
	
	@Column(name="PARA_ORDER")
	private Integer paraOrder;
	
	@Column(name="REMARK")
	private String remark;
	
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

	public Long getReportParaId() {
		return reportParaId;
	}

	public void setReportParaId(Long reportParaId) {
		this.reportParaId = reportParaId;
	}

	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getParaDisplayName() {
		return paraDisplayName;
	}

	public void setParaDisplayName(String paraDisplayName) {
		this.paraDisplayName = paraDisplayName;
	}

	public String getDataDisplayType() {
		return dataDisplayType;
	}

	public void setDataDisplayType(String dataDisplayType) {
		this.dataDisplayType = dataDisplayType;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getDataCoding() {
		return dataCoding;
	}

	public void setDataCoding(String dataCoding) {
		this.dataCoding = dataCoding;
	}

	public Integer getParaOrder() {
		return paraOrder;
	}

	public void setParaOrder(Integer paraOrder) {
		this.paraOrder = paraOrder;
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ReportPara o) {

		return paraOrder.compareTo(o.paraOrder);
	}
	
}
