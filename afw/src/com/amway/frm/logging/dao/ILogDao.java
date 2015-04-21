/**
 * 
 */
package com.amway.frm.logging.dao;

import java.sql.SQLException;

import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.logging.entity.LogOperation;
import com.amway.frm.logging.entity.LogPerformance;

/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：日志Dao接口
 */
public interface ILogDao extends IBaseDao {
	
	void savePerformace(LogPerformance lopPerformance)throws SQLException;
	
	void saveOperation(LogOperation lopOperation)throws SQLException;
}
