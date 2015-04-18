/**
 * 
 */
package com.amway.frm.base.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.exception.exception.AmwaySysException;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * @author huangweijin
 *
 * 2011-4-13 上午10:13:34
 */
public class BaseImpl<T extends Object> implements BaseService<T> {
	
	//private static LogService logger = LogFactory.getLogger(BaseImpl.class);
	
	private IBaseDao<T, Serializable> baseDao;

	@Override
	public Object getBean(String name){
		return ContextFactory.getBean(name);
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#getSysInfo()
	 */
	@Override
	public SysInfoBean getSysInfo() {
		return SysInfoBean.getSysInfoBean();
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#add(java.lang.Object)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> add(T obj) {
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		
		try {
			Boolean isExist  = baseDao.isEntityExist(obj);
			if(isExist){
				returnMessage.setReturnObject(obj);
				returnMessage.setReturnMsg(AppConstant.RECORD_YEXISTS);
				return returnMessage;
			}
			T objRet = baseDao.create(obj);
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.ADD_SUCCESS_MSG);
			returnMessage.setReturnObject(objRet);
		} catch (Exception e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#add(java.lang.Object)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> addCom(T obj) {
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		
		try {
			Boolean isExist  = baseDao.isReEntityExist(obj);
			if(isExist){
				returnMessage.setReturnObject(obj);
				returnMessage.setReturnMsg(AppConstant.RECORD_YEXISTS);
				return returnMessage;
			}
			T objRet = baseDao.create(obj);
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.ADD_SUCCESS_MSG);
			returnMessage.setReturnObject(objRet);
		} catch (Exception e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#addList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> addList(List<T> objs) {
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == objs){
			return returnMessage;
		}
		try {
			for(T obj: objs){
				Boolean isExist = baseDao.isEntityExist(obj);
				if(isExist){
					returnMessage.setReturnObject(obj);
					returnMessage.setReturnMsg(AppConstant.RECORD_YEXISTS);
					return returnMessage;
				}
			}
			List<T> objsRet = new ArrayList<T>();
			for(T obj: objs){
				T objRet = baseDao.create(obj);
				objsRet.add(objRet);
			}
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.ADD_SUCCESS_MSG);
			returnMessage.setReturnObjects(objsRet);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#addList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> addComList(List<T> objs) {
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == objs){
			return returnMessage;
		}
		try {
			for(T obj: objs){
				Boolean isExist = baseDao.isReEntityExist(obj);
				if(isExist){
					returnMessage.setReturnObject(obj);
					returnMessage.setReturnMsg(AppConstant.RECORD_YEXISTS);
					return returnMessage;
				}
			}
			List<T> objsRet = new ArrayList<T>();
			for(T obj: objs){
				T objRet = baseDao.create(obj);
				objsRet.add(objRet);
			}
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.ADD_SUCCESS_MSG);
			returnMessage.setReturnObjects(objsRet);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}


	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> delete(T obj) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		
		try {
			T objDB = baseDao.getUniqueResult(obj);
			if(null == objDB){
				returnMessage.setReturnObject(obj);
				returnMessage.setReturnMsg(AppConstant.RECORD_NEXISTS);
				return returnMessage;
			}
			T objRet = baseDao.remove(objDB);
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.DEL_SUCCESS_MSG);
			returnMessage.setReturnObject(objRet);
		} catch (Exception e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#deleteList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> deleteList(List<T> objs) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == objs){
			return returnMessage;
		}
		try {
			
			List<T> objsRet = new ArrayList<T>();
			List<T> objDBsRet = new ArrayList<T>();
			for(T obj: objs){
				T objDB = baseDao.getUniqueResult(obj);
				if(null == objDB){
					returnMessage.setReturnObject(obj);
					returnMessage.setReturnMsg(AppConstant.RECORD_NEXISTS);
					return returnMessage;
				}
				objDBsRet.add(objDB);
			}
			for(T obj: objDBsRet){
				T objRet = baseDao.remove(obj);
				objsRet.add(objRet);
			}
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.DEL_SUCCESS_MSG);
			returnMessage.setReturnObjects(objsRet);
			
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#query(java.lang.Object)
	 */
	@Override
	@Transactional(readOnly=true)
	public ReturnMessage<T> query(T obj) {
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		try {
			List<T> objsRet = baseDao.getResultList(obj);
			returnMessage.setReturnObjects(objsRet);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setQryReturnMsg();
			
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#queryList(java.lang.Object)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<T> queryList(T obj) {
		List<T> objsRet = new ArrayList<T>();
		try {
			objsRet = baseDao.getResultList(obj);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		return objsRet;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#querySingle(java.lang.Object)
	 */
	@Override
	public T querySingle(T obj) {
		T t = null;
		try {
			t = baseDao.getSingleResult(obj);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		return t;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#update(java.lang.Object)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> update(T obj) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		try {
			T objRet = baseDao.update(obj);
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnMsg(ReturnMessage.MDF_SUCCESS_MSG);
			returnMessage.setReturnObject(objRet);
		} catch (Exception e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#updateList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> updateList(List<T> objs) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == objs){
			return returnMessage;
		}
		List<T> objsRet = new ArrayList<T>();
		for(T obj: objs){
			try {
				T objRet = baseDao.update(obj);
				objsRet.add(objRet);
			} catch (SQLException e) {
				throw new AmwaySysException(e);
			}
		}
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg(ReturnMessage.MDF_SUCCESS_MSG);
		returnMessage.setReturnObjects(objsRet);
		
		return returnMessage;
	}
	
	@Override
	@Transactional
	public ReturnMessage<T> addOrUpdate(T obj) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		T objRet = null;
		try {
			objRet = baseDao.saveOrUpdate(obj);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg(ReturnMessage.OPRT_SUCCESS_MSG);
		returnMessage.setReturnObject(objRet);
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#saveOrUpdateListById(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> addOrUpdateList(List<T> objs) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == objs){
			return returnMessage;
		}
		List<T> objsRet = new ArrayList<T>();
		for(T obj: objs){
			try {
				T objRet = baseDao.saveOrUpdate(obj);
				objsRet.add(objRet);
			} catch (SQLException e) {
				throw new AmwaySysException(e);
			}
		}
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg(ReturnMessage.OPRT_SUCCESS_MSG);
		returnMessage.setReturnObjects(objsRet);
		
		return returnMessage;
		
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#logicDelete(java.lang.Object)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> logicDelete(T obj) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == obj){
			return returnMessage;
		}
		T objRet = null;
		
		try {
			objRet = baseDao.logicRemove(obj);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		} 
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg(ReturnMessage.DEL_SUCCESS_MSG);
		returnMessage.setReturnObject(objRet);
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.base.service.BaseService#logicDeleteList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<T> logicDeleteList(List<T> objs) {
		
		ReturnMessage<T> returnMessage = new ReturnMessage<T>();
		if(null == objs){
			return returnMessage;
		}
		List<T> objsRet = new ArrayList<T>();
		for(T obj: objs){
			try {
				T objRet = baseDao.logicRemove(obj);
				objsRet.add(objRet);
			} catch (SQLException e) {
				throw new AmwaySysException(e);
			}
		}
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg(ReturnMessage.DEL_SUCCESS_MSG);
		returnMessage.setReturnObjects(objsRet);
		
		return returnMessage;
	}

	public void setBaseDao(IBaseDao<T, Serializable> baseDao) {
		
		this.baseDao = baseDao;
	}

}
