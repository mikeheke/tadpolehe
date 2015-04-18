/**
 * 
 */
package com.amway.frm.query.service;

import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.vo.Row;

/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：展示Service接口
 */
public interface ShowService extends QueryService {

	/**
	 * Declare：生成查询数据
	 * 
	 * @param query 查询
	 * @param limit limit
	 * @param parNamesValues 参数值
	 * @param userInfo 当前登录者信息
	 * @return ReturnMessage 返回returnMessage
	 */
	ReturnMessage<Row> getQueryShowDatas(Query query, Limit limit,
			Map<String, String[]> parNamesValues, Map<String, Object> userInfo);

}