/**
 * 
 */
package com.amway.frm.query.dao;

import java.sql.SQLException;
import java.util.List;

import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.vo.Row;
import com.amway.frm.query.vo.SQL;



/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询DAO接口
 */
public interface IQueryDao extends IBaseDao<Query, String> {

	/**
	 * 检查数据源JNDI连结
	 * 
	 * @param dsJndi 数据源JNDI
	 * @return boolean 是否连接
	 */
	boolean checkDsJndi(String dsJndi);
	
	/**
	 * Declare：取得查询数据结果集
	 * 
	 * @param query 查询
	 * @param limit Limit对象
	 * @param parNamesValues 参数值
	 * @return List<Data> 数据结果集
	 * @throws SQLException
	 */
	List<Row> getQueryDatas(Query query, SQL sql)throws SQLException;
	
}
