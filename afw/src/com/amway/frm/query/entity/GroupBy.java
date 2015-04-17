/**
 * 
 */
package com.amway.frm.query.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.query.util.QueryConstant;

/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：分组
 */
@Entity(name="query_GroupBy")
@javax.persistence.Table(name="MSTB_QUERY_GROUPBY", schema=QueryConstant.APP_DEAULT_SCHEMA)
public class GroupBy implements Serializable, Comparable<GroupBy> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193780185233493722L;

	//主键
	@Id
	@Column(name="GROUPBY_ID")
	private Long groupById;
	
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
	
	public Long getGroupById() {
		return groupById;
	}

	public void setGroupById(Long groupById) {
		this.groupById = groupById;
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
				+ ((groupById == null) ? 0 : groupById.hashCode());
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
		GroupBy other = (GroupBy) obj;
		if (groupById == null) {
			if (other.groupById != null){
				return false;
			}
		} else if (!groupById.equals(other.groupById)){
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
	public int compareTo(GroupBy o) {
		if(null == o){
			return 0;
		}
		return this.getOrderNo().compareTo(o.getOrderNo());
	}

}
