package com.amway.frm.bds.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.entity.Role;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.dao.IBdsSchemaInforDao;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.exception.BdsSysException;
import com.amway.frm.bds.service.BdsSchemaInforService;
import com.amway.frm.bds.util.BdsXmlUntil;

/**
 * 基础数据服务实现类
 * 
 */
public class BdsSchemaInforImpl extends BaseImpl implements
		BdsSchemaInforService {
	
	// 基础数据服务DAO
	private IBdsSchemaInforDao bdsSchemaInforDao;

	/**
	 * 根据主键，获得基础数据服务实体
	 * 
	 * @param bdsSchemaInfor
	 * @return ReturnMessage<BdsSchemaInfor>
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ReturnMessage<BdsSchemaInfor> getSchemaInforListByJDBC(
			BdsSchemaInfor bdsSchemaInfor) {
		
		ReturnMessage<BdsSchemaInfor> returnMessage = new ReturnMessage<BdsSchemaInfor>();
		try {
			List<BdsSchemaInfor> bdsSchemaInfors = bdsSchemaInforDao.getListByJdbc(bdsSchemaInfor);
			
			returnMessage.setReturnObjects(bdsSchemaInfors);
			returnMessage.setReturnMsg(ReturnMessage.QRY_SUCCESS_MSG);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			return returnMessage;
		} catch (SQLException e) {
			throw new BdsSysException(e);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.bds.service.BdsSchemaInforService#getSchemaInforByJDBC(com.amway.frm.bds.entity.BdsSchemaInfor)
	 */
	@Override
	public BdsSchemaInfor getSchemaInforByJDBC(BdsSchemaInfor bdsSchemaInfor) {
		
		ReturnMessage<BdsSchemaInfor> returnMessage = null;
		returnMessage = this.getSchemaInforListByJDBC(bdsSchemaInfor);
		
		return returnMessage.getReturnObject();
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.bds.service.BdsSchemaInforService#getSchemaStructColNames(java.lang.String)
	 */
	@Override
	public List<String> getSchemaStructColNames(String bdsSchemaInforCode) {
		
		List<String> colNames = null;
		BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
		bdsSchemaInfor.setBdsSchemaInforCode(bdsSchemaInforCode);
		BdsSchemaInfor bdsSchemaInforRet = getSchemaInforListByJDBC(bdsSchemaInfor).getReturnObject();
		if(null == bdsSchemaInforRet){
			return new ArrayList<String>();
		}
		colNames = BdsXmlUntil.getColNamesFromXmlDoc(bdsSchemaInforRet.getDataStructureXml());
		
		return colNames;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.BdsSchemaInforService#addBdsSchemaInfor(com.amway.frm.afw.entity.bdsSchemaInfor)
	 */
	@Override
	@Transactional
	public ReturnMessage<BdsSchemaInfor> addBdsSchemaInfor(BdsSchemaInfor bdsSchemaInfor) {
		
		//bdsSchemaInfor.setBdsSchemaInforId(bdsSchemaInforDao.generateSequence("MSTB_BDS_SCHEMAINFOR"));
		
		ReturnMessage<BdsSchemaInfor> returnMessage = add(bdsSchemaInfor);

		return returnMessage;
	}
	
	public void setBdsSchemaInforDao(IBdsSchemaInforDao bdsSchemaInforDao) {
		this.bdsSchemaInforDao = bdsSchemaInforDao;
	}

}
