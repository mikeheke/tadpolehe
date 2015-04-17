/**
 * 
 */
package com.amway.frm.query.vo;

/**
 * @author huangweijin
 *
 * 2011-4-29 下午02:23:06
 */
public class OrderByVo {

	//主键ID
	private String id;
	
	//主键ID数组
	private String[] ids;

	//表名
	private String tableName;

	//字段名
	private String fieldName;
	
	//升降序
	private String orderBy;
	
	//排序
	private String orderNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
