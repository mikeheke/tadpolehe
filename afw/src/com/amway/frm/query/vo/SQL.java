/**
 * 
 */
package com.amway.frm.query.vo;

import java.util.ArrayList;
import java.util.List;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.query.util.QueryConstant;

/**
 * 
 *
 * 2011-4-30 上午11:47:45
 */
public class SQL {

	private StringBuffer sql = new StringBuffer();
	
	private List<Parameter> paramters = new ArrayList<Parameter>();
	
	private int startRow = 1;
	
	private int endRow = 0;
	
	public SQL(){}
	
	public SQL(int startRow, int endRow){
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public String getSql() {
		return sql.toString();
	}

	public void setSql(StringBuffer sql) {
		this.sql = sql;
	}
	
	public void addSql(String sql) {
		this.sql.append(sql);
	}

	public List<Parameter> getParamters() {
		return this.paramters;
	}
	
	public Object[] getParamterValue() {
		
		List<Object> objs = new ArrayList<Object>();
		
		for(Parameter paramter: paramters){
			
			Object value = getParameterValue(paramter);
			objs.add(value);
		}
		
		return objs.toArray();
	}
	
	private Object getParameterValue(Parameter paramter){
		
		Object objValue = null;
		String type = paramter.getType();
		String value = paramter.getValue();
		if(QueryConstant.DATA_TYPE_STRING.equals(type)){
			objValue = new String(value);
		}else if(QueryConstant.DATA_TYPE_LONG.equals(type)){
			objValue = new Long(value);
		}else if(QueryConstant.DATA_TYPE_NUMBER.equals(type)){
			objValue = new Double(value);
		}else if(QueryConstant.DATA_TYPE_DATE.equals(type)){
			objValue = DataConverter.fmtStrToTimestamp(value);
		}else{
			objValue = new String();
		}
	
		return objValue;
	}
	
	public void addParamter(String type, String value) {
		this.paramters.add(new Parameter(type, value));
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
}
