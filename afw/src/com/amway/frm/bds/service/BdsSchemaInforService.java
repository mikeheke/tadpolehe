package com.amway.frm.bds.service;

import java.util.List;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.exception.BdsBizException;

/**
 * 基础数据服务接口
 * @author huangweijin
 * 
 */
public interface BdsSchemaInforService extends BaseService {

	/**
	 * 根据id或者数据服务编码查找基础服务类实体
	 * 
	 * @param bdsSchemainforId
	 *            基础服务类ID
	 * @return
	 * @throws BdsBizException
	 * @throws Exception
	 */
	ReturnMessage<BdsSchemaInfor> getSchemaInforListByJDBC(BdsSchemaInfor bdsSchemaInfor);
	
	BdsSchemaInfor getSchemaInforByJDBC(BdsSchemaInfor bdsSchemaInfor);

	List<String> getSchemaStructColNames(String bdsSchemaInforCode);

	ReturnMessage<BdsSchemaInfor> addBdsSchemaInfor(
			BdsSchemaInfor bdsSchemaInfor);
}
