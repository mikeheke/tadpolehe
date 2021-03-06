/**
 * 
 */
package mikeheke.tadpole.frm.report.entity;

import java.io.Serializable;
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

import mikeheke.tadpole.frm.base.util.AppConstant;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 *
 * 2011-9-6 下午03:08:37
 */
@Entity(name="ReportSql")
@Table(name="MSTB_REPORT_SQL", schema=AppConstant.APP_DEAULT_SCHEMA)
public class ReportSql implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7947318486674784285L;

	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name="REPORT_SQL_ID")
	private String reportSqlId;
	
	@ManyToOne()
	@JoinColumn(name="REPORT_INFO_ID")
	private ReportInfo reportInfo;
	
	@Column(name="SQL_SELECT")
	private String sqlSelect;
	
	@Column(name="SQL_WHERE")
	private String sqlWhere;
	
	@Column(name="SQL_ORDER")
	private String sqlOrder;
	
	@Column(name="SQL_TYPE")
	private String sqlType;
	
	@Column(name="MAP_KEY")
	private String mapKey;
	
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

	public String getReportSqlId() {
		return reportSqlId;
	}

	public void setReportSqlId(String reportSqlId) {
		this.reportSqlId = reportSqlId;
	}

	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}

	public String getSqlSelect() {
		return sqlSelect;
	}

	public void setSqlSelect(String sqlSelect) {
		this.sqlSelect = sqlSelect;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getSqlOrder() {
		return sqlOrder;
	}

	public void setSqlOrder(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
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
	
}
