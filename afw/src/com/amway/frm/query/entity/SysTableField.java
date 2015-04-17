/**
 * 
 */
package com.amway.frm.query.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author huangweijin
 *
 * 2011-5-19 下午03:34:31
 */
@Entity
@javax.persistence.Table(name="USER_TAB_COLUMNS")
public class SysTableField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8809656104169225314L;

	@Id
	@Column(name="COLUMN_NAME")
	private String columnName;
	
	@Column(name="TABLE_NAME")
	private String tableName;
	
	@Column(name="DATA_TYPE")
	private String dataType;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
