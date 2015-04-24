/**
 * 
 */
package com.amway.frm.query.service;

import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.query.entity.DataCoding;
import com.amway.frm.query.entity.Query;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询Service接口
 */
public interface ConfigService extends QueryService {

	ReturnMessage<Query> saveQuery(Query query);

	boolean checkDsJndi(String dsJndi);
	
	List<Application> getApplications();
	
	String getSysTables(Query query);
	
	List<String> getSysTables(String sysTableDictCode); 
	
	String getSysTableFields(Query query);
	
	String getSysTableFieldType(String fieldDictCode, String tableName,
			String fieldName);
	
	List<DataCoding> getDataCodings();
	
	/**
	 * 复制Query
	 * @param oldQueryCode
	 * @param newQueryCode
	 * @return
	 */
	ReturnMessage<Query> copyQuery(String oldQueryCode,String newQueryCode);
}
