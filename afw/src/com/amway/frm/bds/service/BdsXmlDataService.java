package com.amway.frm.bds.service;

import java.util.List;
import java.util.Map;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.entity.BdsXmlData;

/**
 * 基础数据服务本地数据接口
 * 
 * 
 */
public interface BdsXmlDataService extends BaseService {

	ReturnMessage<BdsXmlData> getBdsXmlData(BdsXmlData bdsXmlData);

	List<Object[]> getXmlDataList(BdsXmlData bdsXmlData, List<String> colNames);

	List<BdsXmlData> getBdsXmlDataList(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> params, String joinOperator);
	
	Map<String, String> getBdsXmlDataMap(BdsXmlData bdsXmlData, List<String> colNames);
	
	ReturnMessage<BdsXmlData> assembleBdsXmlData(BdsXmlData bdsXmlData, 
			String[] xmlDatas, List<String> colNames);

	ReturnMessage<BdsXmlData> addBdsXmlData(BdsXmlData bdsXmlData);

}
