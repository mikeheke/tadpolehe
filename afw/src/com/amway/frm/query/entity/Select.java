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
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：选择
 */
@Entity(name="query_Select")
@javax.persistence.Table(name="MSTB_QUERY_SELECT", schema=QueryConstant.APP_DEAULT_SCHEMA)
public class Select implements Serializable, Comparable<Select> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4816388487909843661L;

	//主键
	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name="SELECT_ID")
	private String selectId;
	
	//查询ID
	@ManyToOne()
	@JoinColumn(name="QUERY_ID")
	private Query query;
	
	//表名
	@Column(name="TABLE_NAME")
	private String tableName;
	
	//字段名
	@Column(name="FIELD_NAME")
	private String fieldName;
	
	//字段别名
	@Column(name="FIELD_ALIAS")
	private String fieldAlias;
	
	//数据类型
	@Column(name="DATA_TYPE")
	private String dataType;
	
	//描述
	@Column(name="DES")
	private String des;
	
	//排序
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	//数据转码
	@Column(name="DATA_CODING")
	private String dataCoding;
	
	//输出格式
	@Column(name="OUT_FORMAT")
	private Integer outFormat;
	
	//按钮类型
	@Column(name="BTN_TYPE")
	private Integer btnType;
	
	//是否隐藏
	@Column(name="IS_HIDDEN")
	private Integer isHidden;
	
	//数据输入
	@Column(name="DATA_INPUT")
	private String dataInput;
	
	//打开类型
	@Column(name="OPEN_TYPE")
	private Integer openType;
	
	//关联URL
	@Column(name="LINK_URL")
	private String linkUrl;
	
	//列宽
	@Column(name="COL_WIDTH")
	private String colWidth;
	
	//组合结果
	@Column(name="SELECT_RESULT")
	private String selectResult;
	
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
	
	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldAlias() {
		return fieldAlias;
	}

	public void setFieldAlias(String fieldAlias) {
		this.fieldAlias = fieldAlias;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getDataCoding() {
		return dataCoding;
	}

	public void setDataCoding(String dataCoding) {
		this.dataCoding = dataCoding;
	}

	public Integer getOutFormat() {
		return outFormat;
	}

	public void setOutFormat(Integer outFormat) {
		this.outFormat = outFormat;
	}

	public Integer getBtnType() {
		return btnType;
	}

	public void setBtnType(Integer btnType) {
		this.btnType = btnType;
	}

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

	public String getDataInput() {
		return dataInput;
	}

	public void setDataInput(String dataInput) {
		this.dataInput = dataInput;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getColWidth() {
		return colWidth;
	}

	public void setColWidth(String colWidth) {
		this.colWidth = colWidth;
	}

	public String getSelectResult() {
		return selectResult;
	}

	public void setSelectResult(String selectResult) {
		this.selectResult = selectResult;
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
				+ ((oprtFlag == null) ? 0 : oprtFlag.hashCode());
		result = prime * result
				+ ((selectId == null) ? 0 : selectId.hashCode());
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
		Select other = (Select) obj;
		if (oprtFlag == null) {
			if (other.oprtFlag != null){
				return false;
			}
		} else if (!oprtFlag.equals(other.oprtFlag)){
			return false;
		}
		if (selectId == null) {
			if (other.selectId != null){
				return false;
			}
		} else if (!selectId.equals(other.selectId)){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Select o) {
		if(null == o){
			return 0;
		}
		return this.getOrderNo().compareTo(o.getOrderNo());
	}
	
}
