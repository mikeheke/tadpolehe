package com.amway.frm.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NoInitialContextException;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.sql.DataSource;

import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.JDBCHelper;
import com.amway.frm.base.vo.UniqueKey;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * 
 * @author lenovo
 *
 */
public class BaseDao<T extends Object, PK extends Serializable> implements
		IBaseDao<T, PK> {
	
	//private LogService logger = LogFactory.getLogger(BaseDao.class);

	private Class<T> entityClass;
	
	@PersistenceContext(unitName = AppConstant.JPA_DB_UNIT)
	private EntityManager entityManager;

	public BaseDao() {
		//this.entityClass = null;
		Class c = getClass();
		Type t = getGenericSuperclass(c);
		if (t instanceof ParameterizedType) {
			Type[] p = getActualTypeArguments(t);
			this.entityClass = (Class<T>) p[0];
		}
	}
	
	private static Type getGenericSuperclass(Class c){
		
		Type t = c.getGenericSuperclass();
		
		return t;
	}
	
	private static Type[] getActualTypeArguments(Type t){
		
		Type[] p = ((ParameterizedType) t).getActualTypeArguments();
		
		return p;
	}

	@Override
	public Boolean isEntityExist(T entity) throws SQLException{

		try{
			if (null != getUniqueResult(entity)) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			throw new SQLException(e);
		}
	}
	
	@Override
	public Boolean isReEntityExist(T entity) throws SQLException{

		try{
			if (null != getReUniqueResult(entity)) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			throw new SQLException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#getUniqueResult(java.lang.Object)
	 */
	public T getUniqueResult(T entity) throws SQLException {
		
		try{
			
			Map<String, Object> parVals = generateUniqueParVals(entity);
			Query query = generateQuery(entity.getClass(), parVals, AfwConstant.SQL_OR);
			
			List<T> list = query.getResultList();
			
			return getSingleResult(list);
		}catch(Exception e){
			throw new SQLException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#getUniqueResult(java.lang.Object)
	 */
	public T getReUniqueResult(T entity) throws SQLException {
		
		try{
			
			Map<String, Object> parVals = generateReUniqueParVals(entity);
			Query query = generateQuery(entity.getClass(), parVals, AfwConstant.SQL_AND);
			
			List<T> list = query.getResultList();
			
			return getSingleResult(list);
		}catch(Exception e){
			throw new SQLException(e);
		}
	}
	
	@Override
	public Boolean isEntitiesExist(List<T> entities) throws SQLException{

		for(T entity: entities){
			if(isEntityExist(entity)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean isReEntitiesExist(List<T> entities) throws SQLException{

		for(T entity: entities){
			if(isReEntityExist(entity)){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<T> getResultList(T entity) throws SQLException{

		Map<String, Object> parVals = generateConParVals(entity);
		Query query = generateQuery(entity.getClass(), parVals, AfwConstant.SQL_AND);

		return query.getResultList();
	}

	@Override
	public List<T> getResultList(String jql) throws SQLException {

		return entityManager.createQuery(jql).getResultList();
	}

	@Override
	public T getSingleResult(String jql) throws SQLException {
		List<T> list = getResultList(jql);

		return getSingleResult(list);
	}

	@Override
	public T getSingleResult(T entity) throws SQLException {

		List<T> list = this.getResultList(entity);
		
		return getSingleResult(list);
	}

	private T getSingleResult(List<T> list){
		
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public T update(T entity) throws SQLException {
		return (T) entityManager.merge(entity);
	}
	
	@Override
	public T saveOrUpdate(T entity) throws SQLException {
		
		T entityRet = null;
		try {
			if(!isEntityIdExist(entity)){
				entityRet = this.create(entity);
			}else{
				entityRet = this.update(entity);
			}
		} catch (IllegalArgumentException e) {
			throw new SQLException(e);
		} catch (IllegalAccessException e) {
			throw new SQLException(e);
		}
		return entityRet;
	}

	@Override
	public T remove(T entity) throws SQLException {
		entityManager.remove(entity);

		return entity;
	}

	@Override
	public T getEntityById(PK id) throws SQLException {

		return entityManager.find(entityClass, id);
	}

	@Override
	public T create(T entity) throws SQLException {

		entityManager.persist(entity);

		return entity;
	}

	@Override
	public Boolean isEntityExist(String jql) throws SQLException {

		boolean result = false;
		
		if (null != getSingleResult(jql)) {
			result = true;
		}else{
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#createList(java.util.List)
	 */
	@Override
	public List<T> createList(List<T> entities) throws SQLException {
		List<T> list = new ArrayList<T>();
		for (T entity : entities) {
			T entityRet = this.create(entity);
			list.add(entityRet);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#saveOrUpdateList(java.util.List)
	 */
	@Override
	public List<T> saveOrUpdateList(List<T> entities) throws SQLException {
		List<T> list = new ArrayList<T>();
		for (T entity : entities) {
			T entityRet = this.saveOrUpdate(entity);
			list.add(entityRet);
		}
		return list;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#getEntitiesByIds(java.util.List)
	 */
	@Override
	public List<T> getEntitiesByIds(List<PK> ids) throws SQLException {

		List<T> list = new ArrayList<T>();
		for (PK id : ids) {
			T entityRet = this.getEntityById(id);
			list.add(entityRet);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#removeList(java.util.List)
	 */
	@Override
	public List<T> removeList(List<T> entities) throws SQLException {

		List<T> list = new ArrayList<T>();
		for (T entity : entities) {
			T entityRet = this.remove(entity);
			list.add(entityRet);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#updateList(java.util.List)
	 */
	@Override
	public List<T> updateList(List<T> entities) throws SQLException {
		List<T> list = new ArrayList<T>();
		for (T entity : entities) {
			T entityRet = this.update(entity);
			list.add(entityRet);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#isEntityExist(java.io.Serializable)
	 */
	@Override
	public Boolean isEntityIdExist(PK id) throws SQLException {

		T entity = this.getEntityById(id);
		if (null == entity) {
			return true;
		} else {
			return false;
		}

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#isEntityExist(java.io.Serializable)
	 */
	@Override
	public Boolean isEntityIdsExist(List<PK> ids) throws SQLException {

		for(PK id: ids){
			if(isEntityIdExist(id)){
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#getResultList(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public List<T> getResultList(String jql, Map<String, Object> parVals)
			throws SQLException {

		Query query = entityManager.createQuery(jql);
		setQueryParamters(query, parVals);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#getSingleResult(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public T getSingleResult(String jql, Map<String, Object> parVals)
			throws SQLException {

		List<T> list = this.getResultList(jql, parVals);

		return getSingleResult(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.amway.frm.base.dao.IBaseDao#isEntityExist(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public Boolean isEntityExist(String jql, Map<String, Object> parVals)
			throws SQLException {

		if (null != getSingleResult(jql, parVals)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Integer getQryMaxResultNum() {
		//Application app = (Application) ContextFactory.getFromSession(AppConstant.APPLICATION_NAME);
		//return app.get
		
		return AppConstant.RET_MAX_NUM;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#logicRemove(java.lang.Object)
	 */
	@Override
	public T logicRemove(T entity) throws SQLException {
		
		T entityRet = this.getUniqueResult(entity);
		try {
			Field recordState = entityRet.getClass().getDeclaredField(AppConstant.RECORD_STATE);
			Field updatedTime = entityRet.getClass().getDeclaredField(AppConstant.UPDATED_TIME);
			Field updatedUserId = entityRet.getClass().getDeclaredField(AppConstant.UPDATED_USER_ID);
			recordState.setAccessible(true);
			recordState.set(entityRet, AppConstant.STOP);
			updatedTime.setAccessible(true);
			updatedTime.set(entityRet, new Date());
			updatedUserId.setAccessible(true);
			updatedUserId.set(entityRet, SysInfoBean.getSysInfoBean()
					.getUserProfile().getEmpNumber());
		} catch (SecurityException e) {
			throw new SQLException(e);
		} catch (NoSuchFieldException e) {
			throw new SQLException(e);
		} catch (IllegalArgumentException e) {
			throw new SQLException(e);
		} catch (IllegalAccessException e) {
			throw new SQLException(e);
		} catch (Exception e){
			throw new SQLException(e);
		}
		
		return entityRet;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#logicRemoveList(java.util.List)
	 */
	@Override
	public List<T> logicRemoveList(List<T> entities) throws SQLException {
		
		List<T> list = new ArrayList<T>();
		
		for (T entity : entities) {
			T entityRet = this.logicRemove(entity);
			list.add(entityRet);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#excuteJpl(java.lang.String)
	 */
	@Override
	public int executeJpl(String jql) {
		return this.entityManager.createQuery(jql).executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.dao.IBaseDao#excuteJpl(java.lang.String, java.util.Map)
	 */
	@Override
	public int executeJpl(String jql, Map<String, Object> parVals) {
		Query query = entityManager.createQuery(jql);
		setQueryParamters(query, parVals);
		return query.executeUpdate();
	}
	
//	public Long generateSequence(String module) {
//		
//		Long sequence=null;
//		
//		try {
//		
//			final String jql = "from Config t where t.cfgKey = 'environment' ";
//		
//
//			Config cfg=(Config)this.getSingleResult(jql);
//			
//			String enviorment=cfg.getCfgValue();
//			
//			final String jql2 = "from Sequence t where t.moduleName = '"+module+"' and t.env='"+enviorment+"'";
//			
//			T tempate=this.getSingleResult(jql2);
//			
//			if(tempate!=null){
//				Sequence seq=(Sequence)this.getSingleResult(jql2);
//				
//				sequence=seq.getCurrentSequence()+seq.getIncrementVal();
//				
//				seq.setCurrentSequence(sequence);
//				
//				seq.setUpdatedTime(new Date());
//				
//				this.saveOrUpdate((T) seq);
//				
//			}
//			else{
//				if("qa".equals(enviorment)){
//					sequence=100000l;
//				}
//				else{
//					sequence=100001l;
//				}
//				
//				Sequence seq=new Sequence();
//				
//				seq.setCreatedTime(new Date());
//				
//				seq.setCurrentSequence(sequence);
//				
//				seq.setEnv(enviorment);
//				
//				seq.setIncrementVal(2);
//				
//				seq.setModuleName(module);
//				
//				this.saveOrUpdate((T) seq);
//				
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			return sequence;
//		}
//		
//				
//		return sequence;
//		
//	}
	
	
	public Long generateSequence(String module) {
		
		Long sequence=0l;
		
		JDBCHelper jdbcHelper = null;
		CallableStatement stmt = null;
		
		try {
		
			jdbcHelper = new JDBCHelper(getDataSource());
			
			
			stmt =jdbcHelper.getCallabelStatement("Call getnextid(?,?)");

			stmt.setString(1,module);
			
			stmt.registerOutParameter(2, Types.NUMERIC);
			
			stmt.execute();
			
			sequence=stmt.getLong(2);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//return sequence;
			
			throw new RuntimeException(e);
			
			//throw e;
		} finally {
			try {
				stmt.close();
				jdbcHelper.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
				
		return sequence;
		
	}


	
	private Boolean isEntityIdExist(T entity) throws IllegalArgumentException,
			IllegalAccessException {

		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {

			Id id = field.getAnnotation(Id.class);
			if (null == id) {
				continue;
			}
			Object fieldValue = null;
			field.setAccessible(true);

			fieldValue = field.get(entity);

			if (null != fieldValue) {
				return true;
			}
		}
		return false;
	}
	
	private Map<String, Object> generateConParVals(T entity) throws SQLException{

		Map<String, Object> parVals = new HashMap<String, Object>();

		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {
			Transient tra = field.getAnnotation(Transient.class);
			if (null != tra) {
				continue;
			}
			Object fieldValue = null;
			field.setAccessible(true);

			try {
				Column col = field.getAnnotation(Column.class);
				if (null != col) {
					fieldValue = field.get(entity);
				}else{
					JoinColumn joinCol = field.getAnnotation(JoinColumn.class);
					if(null != joinCol){
						fieldValue = field.get(entity);
					}
				}	
			} catch (IllegalArgumentException e) {
				throw new SQLException(e);
			} catch (IllegalAccessException e) {
				throw new SQLException(e);
			}

			if (null != fieldValue) {
				parVals.put(field.getName(), fieldValue);
			}
		}

		return parVals;
	}
	
	private Map<String, Object> generateUniqueParVals(T entity)
			throws SQLException {

		Map<String, Object> parVals = new HashMap<String, Object>();

		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {
			Object fieldValue = null;
			field.setAccessible(true);
			Id id = field.getAnnotation(Id.class);
			try {
				if (null != id) {
						fieldValue = field.get(entity);
				} else {
					Column col = field.getAnnotation(Column.class);
					if (null == col) {
						continue;
					}
					if (!col.unique()) {
						continue;
					}
					fieldValue = field.get(entity);
				}
				if (null != fieldValue) {
					parVals.put(field.getName(), fieldValue);
				}
			} catch (IllegalArgumentException e) {
				throw new SQLException(e);
			} catch (IllegalAccessException e) {
				throw new SQLException(e);
			}
		}

		return parVals;
	}
	
	private Map<String, Object> generateReUniqueParVals(T entity)
			throws SQLException {

		Map<String, Object> parVals = new HashMap<String, Object>();

		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {
			Object fieldValue = null;
			field.setAccessible(true);
			try {
				
				UniqueKey uniqueKey = field.getAnnotation(UniqueKey.class);
				if (null == uniqueKey) {
					continue;
				}
				fieldValue = field.get(entity);
				if (null != fieldValue) {
					parVals.put(field.getName(), fieldValue);
				}
			} catch (IllegalArgumentException e) {
				throw new SQLException(e);
			} catch (IllegalAccessException e) {
				throw new SQLException(e);
			}
		}

		return parVals;
	}

	private Query generateQuery(Class c, Map<String, Object> parVals,
			String operator) {

		String jql = generateJql(c, parVals.keySet(), operator);
		Query query = entityManager.createQuery(jql);
		setQueryParamters(query, parVals);

		return query;
	}

	private String generateJql(Class c, Set<String> fieldNames, String operator) {

		StringBuffer jql = new StringBuffer();
		jql.append(AppConstant.SQL_FROM).append(AppConstant.EMPTY_ONE_STR);
		jql.append(c.getName());
		StringBuffer whereJql = new StringBuffer();
		String whereKey = AppConstant.EMPTY_ONE_STR
			+ AppConstant.SQL_WHERE + AppConstant.EMPTY_ONE_STR;

		for (String fieldName : fieldNames) {
			whereJql.append(AppConstant.EMPTY_ONE_STR)
					.append(operator)
					.append(AppConstant.EMPTY_ONE_STR)
					.append(fieldName)
					.append(AppConstant.EQUAL_SIGN).append(AppConstant.MAO_SIGN)
					.append(fieldName);
		}
		
		if (whereJql.length() != 0) {
			jql.append(whereKey).append(
					whereJql.substring(whereJql.indexOf(operator)
							+ operator.length()));
		}

		return jql.toString();
	}
	  
	private void setQueryParamters(Query query, Map<String, Object> parVals) {

		for (String fieldName : parVals.keySet()) {
			Object fieldValue = parVals.get(fieldName);
			query.setParameter(fieldName, fieldValue);
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	//-------------------------------------------------------------------------------------------------------------
	//modify by Mike He 20140418 (不使用自动注入datasource)
	//@Resource(mappedName = AppConstant.DATASOURCE_NAME)
	//@Resource(name = AppConstant.DATASOURCE_NAME)
	private DataSource dataSource;

	//public DataSource getDataSource() {
	//	return dataSource;
	//}
	
	public DataSource getDataSource() {

		String jndi = AppConstant.DATASOURCE_NAME;

		try {
			Context initCtx = new InitialContext();
			Object obj = null;
			try {
				//一般是测试或生产环境
				obj = (Object) initCtx.lookup(jndi);
				LogFactory.getLogger(this.getClass()).info("get dataSource success! jndi: "+jndi);
			} catch (NameNotFoundException e) {
				//一般适用与开发环境
				obj = (Object) initCtx.lookup("java:comp/env/" + jndi);// for tomcat	
				LogFactory.getLogger(this.getClass()).info("get dataSource from tomcat success! jndi: "+"java:comp/env/" + jndi);
			} catch (NoInitialContextException ee) {
				//从spring容器中获取，适用于单元测试 thanks
				obj = ContextFactory.getBean(AppConstant.DATASOURCE_NAME);  //for java app(not web app)
				LogFactory.getLogger(this.getClass()).info("get dataSource from spring context success! jndi: "+jndi);
			}
			
			if (obj == null) {
				throw new Exception("Can't lookup " + jndi);
			}
			
			//javax.sql.DataSource ds = (javax.sql.DataSource) obj;
			dataSource = (javax.sql.DataSource) obj;

		} catch (Exception e) {
			LogFactory.getLogger(this.getClass()).info("get dataSource fail! jndi: "+jndi+"; error: "+e.getMessage());
			throw new RuntimeException("can't get dataSource! "+e.getMessage());
		}

		return dataSource;
	}
	//-------------------------------------------------------------------------------------------------------------

}
