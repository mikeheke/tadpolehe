/**
 * 
 */
package com.amway.frm.base.service;

import java.util.List;

import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：Service基类
 */
public interface BaseService<T extends Object> {
	
	/**
	 * Declare：取得Spring上下文Bean
	 * 
	 * @param name 名称
	 * @return Object 上下文Bean
	 */
	Object getBean(String name);
	
	/**
	 * Declare：取得当前系统信息
	 * 
	 * @param void
	 * @return SysInfoBean 系统信息
	 */
	SysInfoBean getSysInfo();
	
	/**
	 * Declare：增加对象
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> add(T obj);
	
	/**
	 * Declare：增加复合对象
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> addCom(T obj);
	
	/**
	 * Declare：增加或更新对象,根据ID存在则修改,不存在则新增
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> addOrUpdate(T obj);
	
	/**
	 * Declare：删除对象
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> delete(T obj);
	
	/**
	 * Declare：逻辑删除对象
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> logicDelete(T obj);
	
	/**
	 * Declare：更新对象
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> update(T obj);
	
	/**
	 * Declare：以对象为条件查询
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> query(T obj);
	
	/**
	 * Declare：以对象为条件查询
	 * 
	 * @param obj 对象
	 * @return List<T> 返回对象列表
	 */
	List<T> queryList(T obj);
	
	/**
	 * Declare：以对象为条件查询
	 * 
	 * @param obj 对象
	 * @return T 返回对象
	 */
	T querySingle(T obj);
	
	/**
	 * Declare：删除对象集合
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> deleteList(List<T> objs);
	
	/**
	 * Declare：逻辑删除对象集合
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> logicDeleteList(List<T> objs);
	
	/**
	 * Declare：更新对象集合
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> updateList(List<T> objs);
	
	/**
	 * Declare：增加对象集合
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> addList(List<T> objs);
	
	/**
	 * Declare：增加复合对象集合
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> addComList(List<T> objs);
	
	/**
	 * Declare：增加或更新对象,根据对象Id存在则修改,不存在则新增
	 * 
	 * @param obj 对象
	 * @return ReturnMessage<T> 返回信息
	 */
	ReturnMessage<T> addOrUpdateList(List<T> objs);
}
