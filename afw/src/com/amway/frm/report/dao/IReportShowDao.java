/**
 * 
 */
package com.amway.frm.report.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.report.vo.SQL;

/**
 * @author huangweijin
 *
 * 2011-9-17 下午02:55:47
 */
public interface IReportShowDao extends IBaseDao<ReportInfo, Long> {

	List<Map<String, Object>> excuteSql(SQL sql)throws SQLException;

}