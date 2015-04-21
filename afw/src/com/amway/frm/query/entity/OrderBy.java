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
 * Declare：排序
 */
@Entity(name="query_OrderBy")
@javax.persistence.Table(name="MSTB_QUERY_ORDERBY", schema=QueryConstant.APP_DEAULT_SCHEMA)
public class OrderBy implements Serializable, Comparable<OrderBy> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -964188298311888304L;

	//主键
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="ORDERBY_ID")
	private String orderById;
	
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
	
	//排序ASC,DESC
	@Column(name="ORDER_BY")
	private Integer orderBy;
	
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
	
	// 对应orderBy的字符串，值为ASC或DESC
	@Transient
	private String orderByName;

	public String getOrderById() {
		return orderById;
	}

	public void setOrderById(String orderById) {
		this.orderById = orderById;
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

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
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
				+ ((oprtFlag == null) ? 0 : oprtFlag.hashCode());
		result = prime * result
				+ ((orderById == null) ? 0 : orderById.hashCode());
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
		OrderBy other = (OrderBy) obj;
		if (oprtFlag == null) {
			if (other.oprtFlag != null){
				return false;
			}
		} else if (!oprtFlag.equals(other.oprtFlag)){
			return false;
		}
		if (orderById == null) {
			if (other.orderById != null){
				return false;
			}
		} else if (!orderById.equals(other.orderById)){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(OrderBy o) {
		if(null == o){
			return 0;
		}
		return this.getOrderNo().compareTo(o.getOrderNo());
	}

	public String getOrderByName() {
		return orderByName;
	}

	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}

}
