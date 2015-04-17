/**
 * 
 */
package com.amway.frm.tag.service;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.tag.vo.AutoComplete;


/**
 * 自动充填标签
 * @author  fenghanhao
 * CreateTime: 2011-3-12 下午03:09:19
 */
public interface AutoCompleteTagService extends BaseService{
	
	/**
	 * 获得自动充填的下拉提示列表数据
	 * @param bdsKey  数据编码
	 * @param filterColNames  过滤列名
	 * @param filterColValue  过滤列值
	 * @param fillColKey  充填到页面input元素的本列名指定的列值
	 * @return  json数据
	 */
	ReturnMessage<String> getAutoCompleteDatas(AutoComplete autoComplete);

}
