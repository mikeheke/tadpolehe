
package com.amway.frm.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：Dao基类
 */
public interface IBaseDao <T extends Object, PK extends Serializable> {

	/**
	 * Declare：按jql取得结果集
	 * 
	 * @param jql jql
	 * @return List<T> 结果集
	 * @throws SQLException
	 */
	List<T> getResultList(String jql) throws SQLException;
	
	/**
	 * Declare：按参数jql取得结果集
	 * 
	 * @param jql jql
	 * @param parVals 参数值
	 * @return List<T> 结果集
	 * @throws SQLException
	 */
	List<T> getResultList(String jql, Map<String, Object> parVals)
			throws SQLException;
	
	/**
	 * Declare：按jql取得单对象
	 * 
	 * @param jql jql
	 * @return T 对象
	 * @throws SQLException
	 */
	T getSingleResult(String jql) throws SQLException;
	
	/**
	 * Declare：按参数jql取得单对象
	 * 
	 * @param jql jql
	 * @param parVals 参数值
	 * @return T 对象
	 * @throws SQLException
	 */
	T getSingleResult(String jql, Map<String, Object> parVals)
			throws SQLException;
	
	/**
	 * Declare：按实体条件取得结果集
	 * 
	 * @param entity 实体条件
	 * @return List<T> 结果集
	 * @throws SQLException
	 */
	List<T> getResultList(T entity) throws SQLException;
    
	/**
	 * Declare：按实体条件取得单对象
	 * 
	 * @param entity 实体条件
	 * @return T 对象
	 * @throws SQLException
	 */
	T getSingleResult(T entity) throws SQLException;
	
	/**
	 * Declare：按实体条件取得唯一单对象
	 * 
	 * @param entity 实体条件
	 * @return T 对象
	 * @throws SQLException
	 */
	T getUniqueResult(T entity) throws SQLException;
    
	/**
	 * Declare：更新实体对象
	 * 
	 * @param entity 更新实体
	 * @return T 更新后实体
	 * @throws SQLException
	 */
	T update(T entity) throws SQLException;
	
	/**
	 * Declare：更新实体对象集合
	 * 
	 * @param entities 更新实体集合
	 * @return List<T> 更新后实体集合
	 * @throws SQLException
	 */
	List<T> updateList(List<T> entities) throws SQLException;
	
    
	/**
	 * Declare：删除实体对象
	 * 
	 * @param entity 待删除实体
	 * @return T 删除后实体
	 * @throws SQLException
	 */
    T remove(T entity) throws SQLException;
    
    /**
	 * Declare：逻辑删除实体对象
	 * 
	 * @param entity 待删除实体
	 * @return T 删除后实体
	 * @throws SQLException
	 */
    T logicRemove(T entity) throws SQLException;
    
    /**
	 * Declare：删除实体对象集合
	 * 
	 * @param entities 待删除实体集合
	 * @return List<T> 删除后实体集合
	 * @throws SQLException
	 */
    List<T> removeList(List<T> entities) throws SQLException;
    
    /**
	 * Declare：逻辑删除实体对象集合
	 * 
	 * @param entities 待删除实体集合
	 * @return List<T> 删除后实体集合
	 * @throws SQLException
	 */
    List<T> logicRemoveList(List<T> entities) throws SQLException;
    
    
	/**
	 * Declare：创建实体对象
	 * 
	 * @param entity 新实体
	 * @return T 新建后实体
	 * @throws SQLException
	 */
    T create(T entity) throws SQLException;
    
    /**
	 * Declare：增加或更新对象,根据ID判断存在则修改,不存在则新增
	 * 
	 * @param entity 实体对象
	 * @return T
	 */
    T saveOrUpdate(T entity) throws SQLException;
    
    /**
	 * Declare：创建实体对象集合
	 * 
	 * @param entities 新实体集合
	 * @return List<T> 新建后实体集合
	 * @throws SQLException
	 */
    List<T> createList(List<T> entities) throws SQLException;
    
    /**
	 * Declare：增加或更新对象,根据ID判断存在则修改,不存在则新增
	 * 
	 * @param entity 实体对象
	 * @return List<T>
	 */
    List<T> saveOrUpdateList(List<T> entities) throws SQLException;
    
	/**
	 * Declare：按主键找实体对象
	 * 
	 * @param id 主键
	 * @return T 实体
	 * @throws SQLException
	 */
    T getEntityById(PK id) throws SQLException;
    
    /**
	 * Declare：按主键找实体对象集合
	 * 
	 * @param ids 主键集合
	 * @return List<T> 实体集合
	 * @throws SQLException
	 */
    List<T> getEntitiesByIds(List<PK> ids) throws SQLException;
    
    /**
	 * Declare：按实体条件，判断实体是否存在
	 * 
	 * @param entity 实体
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
	Boolean isEntityExist(T entity) throws SQLException;
	
	 /**
	 * Declare：按实体条件，判断复合实体是否存在
	 * 
	 * @param entity 实体
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
	Boolean isReEntityExist(T entity) throws SQLException;
	
	 /**
	 * Declare：按实体条件，判断实体结果集任一实体是否存在
	 * 
	 * @param entities 实体结果集
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
	Boolean isEntitiesExist(List<T> entities) throws SQLException;
	
	 /**
	 * Declare：按实体条件，判断复合实体结果集任一实体是否存在
	 * 
	 * @param entities 实体结果集
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
	Boolean isReEntitiesExist(List<T> entities) throws SQLException;
    
    /**
	 * Declare：按jql条件，判断实体是否存在
	 * 
	 * @param jql jql
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
    Boolean isEntityExist(String jql) throws SQLException;
    
    /**
	 * Declare：按参数jql条件，判断实体是否存在
	 * 
	 * @param jql jql
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
	Boolean isEntityExist(String jql, Map<String, Object> parVals)
			throws SQLException;
    
    /**
	 * Declare：按ID条件，判断实体是否存在
	 * 
	 * @param id id
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
    Boolean isEntityIdExist(PK id) throws SQLException;
    
    /**
	 * Declare：按ID条件，判断实体结果集任一ID是否存在
	 * 
	 * @param ids ids
	 * @return Boolean 是否存在
	 * @throws SQLException
	 */
    Boolean isEntityIdsExist(List<PK> ids) throws SQLException;
    
    /**
	 * Declare：取查询最大结果行数
	 * 
	 * @param void
	 * @return Integer 结果行数
	 */
    Integer getQryMaxResultNum();
    
    /**
	 * Declare：取得实体管理器
	 * 
	 * @param void
	 * @return EntityManager 实体管理器
	 */
    EntityManager getEntityManager();

    /**
	 * Declare：取得数据源
	 * 
	 * @param void
	 * @return DataSource 数据源
	 */
	DataSource getDataSource();
	
	/**
	 * 执行JQL
	 * @param jql
	 * @return
	 */
	int executeJpl(String jql);
	
	/**
	 * 执行参数JQL
	 * @param jql
	 * @return
	 */
	int executeJpl(String jql, Map<String, Object> parVals);
	
	//Long generateSequence(String module);
}

