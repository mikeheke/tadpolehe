package com.amway.frm.bds.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.dao.IBdsXmlDataDao;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.entity.BdsXmlData;
import com.amway.frm.bds.exception.BdsSysException;
import com.amway.frm.bds.service.BdsXmlDataService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.util.BdsXmlUntil;

/**
 * 本地数据编辑
 * @author huangweijin
 * 
 */
public class BdsXmlDataImpl extends BaseImpl implements BdsXmlDataService {

	private IBdsXmlDataDao bdsXmlDataDao;

	@Transactional(readOnly = true)
	public ReturnMessage<BdsXmlData> getBdsXmlData(BdsXmlData bdsXmlData) {
		
		ReturnMessage<BdsXmlData> returnMessage = new ReturnMessage<BdsXmlData>();
		
		if(null == bdsXmlData){
			return returnMessage;
		}
		try {
			List<BdsXmlData> bdsXmlDatas = bdsXmlDataDao.getBdsXmlDataList(bdsXmlData);
			
			returnMessage.setReturnObjects(bdsXmlDatas);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		} catch (SQLException e) {
			throw new BdsSysException(e);
		} 
		return returnMessage;
	}

	@Transactional(readOnly = true)
	public List<Object[]> getXmlDataList(BdsXmlData bdsXmlData,
			List<String> colNameList) {
		List<Object[]> returnList = new ArrayList<Object[]>();
		List<BdsXmlData> bdsXmlDataList = new ArrayList<BdsXmlData>();
		
		if(bdsXmlData == null){
			return returnList;
		}
		try {
			bdsXmlDataList = bdsXmlDataDao.getBdsXmlDataList(bdsXmlData);
		}catch (SQLException e) {
			throw new BdsSysException(e);
		}

		for(BdsXmlData reBdsXmlData: bdsXmlDataList) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put(BdsConstant.ID, DataConverter.LongToString(reBdsXmlData.getBdsXmlDataId()));
			map.putAll(getBdsXmlDataMap(reBdsXmlData, colNameList));
			// 列名长度加一，用于数组第一位多存了一个xmlData的id值,供页面的增删改查
			Object[] objs = new Object[colNameList.size() + 1];
			objs[0] = reBdsXmlData.getBdsXmlDataId();
			for (int i = 0; i < colNameList.size(); i++) {
				String colName = colNameList.get(i);
				objs[i + 1] = map.get(colName);
			}
			returnList.add(objs);
		}

		return returnList;
	}

	@Override
	public List<BdsXmlData> getBdsXmlDataList(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams, String joinOperator) {
		List<BdsXmlData> bdsXmlDataList = null;
		try {
			bdsXmlDataList = bdsXmlDataDao.getBdsXmlDataList(bdsSchemaInfor,
					filterParams, joinOperator);

		} catch (Exception e) {
			throw new BdsSysException(e);
		}
		return bdsXmlDataList;
	}

	public Map<String, String> getBdsXmlDataMap(BdsXmlData bdsXmlData, 
			List<String> colNames) {
		
		Map<String, String> xmDataMap = new LinkedHashMap<String, String>();

		xmDataMap.putAll(getBdsXmlDataFixMap(bdsXmlData));
		
		xmDataMap.putAll(getBdsXmlDataNoFixMap(bdsXmlData, colNames));
		
		return xmDataMap;
	}
	
	private Map<String, String> getBdsXmlDataFixMap(BdsXmlData bdsXmlData) {
		
		Map<String, String> fixNameValues = new LinkedHashMap<String, String>();
		if(null == bdsXmlData){
			return fixNameValues;
		}
		
		fixNameValues.put(BdsConstant.FIXED_COL_NAME_CODE, bdsXmlData.getCode());
		fixNameValues.put(BdsConstant.FIXED_COL_NAME_DN, bdsXmlData.getDisplayname());
		fixNameValues.put(BdsConstant.FIXED_COL_NAME_DN_EN, bdsXmlData.getDisplaynameEn());
		fixNameValues.put(BdsConstant.FIXED_COL_NAME_DN_TC, bdsXmlData.getDisplaynameTc());
		
		return fixNameValues;
	}
	
	private Map<String, String> getBdsXmlDataNoFixMap(BdsXmlData bdsXmlData,
			List<String> colNames) {
		
		Map<String, String> noFixNameValues = new LinkedHashMap<String, String>();
		
		String xmlDoc = bdsXmlData.getBdsData();
		if(!DataValidater.isStrEmpty(xmlDoc)){
			noFixNameValues =  BdsXmlUntil.tranXmlDocToMap(xmlDoc);
		}
		
		for(String colName: colNames){
			if(DataValidater.isFalse(BdsConstant.FIXED_COL_NAME_CODE.equals(colName))
					&& DataValidater.isFalse(BdsConstant.FIXED_COL_NAME_DN.equals(colName))
					&& DataValidater.isFalse(BdsConstant.FIXED_COL_NAME_DN_EN.equals(colName))
					&& DataValidater.isFalse(BdsConstant.FIXED_COL_NAME_DN_TC.equals(colName))){
				noFixNameValues.put(colName, noFixNameValues.get(colName));
			}
		}
		
		return noFixNameValues;
	}
	
	public ReturnMessage<BdsXmlData> assembleBdsXmlData(BdsXmlData bdsXmlData, 
			String[] xmlDatas, List<String> colNames){
		
		ReturnMessage<BdsXmlData> returnMessage = new ReturnMessage<BdsXmlData>();
		
		StringBuffer xmlStr = new StringBuffer();
		xmlStr.append(BdsConstant.LEFT_J_KUO).append(BdsConstant.XML_DATA_ROOT)
			.append(BdsConstant.RIGHT_J_KUO);
		for(int i=0;i<colNames.size();i++){  
			String colName = colNames.get(i);
			if(BdsConstant.FIXED_COL_NAME_CODE.equals(colName)){
				bdsXmlData.setCode(xmlDatas[i]);
			}else if(BdsConstant.FIXED_COL_NAME_DN.equals(colName)){
				bdsXmlData.setDisplayname(xmlDatas[i]);
			}else if(BdsConstant.FIXED_COL_NAME_DN_EN.equals(colName)){
				bdsXmlData.setDisplaynameEn(xmlDatas[i]);
			}else if(BdsConstant.FIXED_COL_NAME_DN_TC.equals(colName)){
				bdsXmlData.setDisplaynameTc(xmlDatas[i]);
			}else {
				xmlStr.append(BdsConstant.LEFT_J_KUO).append(colName).append(BdsConstant.RIGHT_J_KUO);
				xmlStr.append(xmlDatas[i]);
				xmlStr.append(BdsConstant.LEFT_J_KUO).append(BdsConstant.UNIX_SEP)
					.append(colName).append(BdsConstant.RIGHT_J_KUO);
			}
		}
		xmlStr.append(BdsConstant.LEFT_J_KUO).append(BdsConstant.UNIX_SEP)
			.append(BdsConstant.XML_DATA_ROOT).append(BdsConstant.RIGHT_J_KUO);
		
		bdsXmlData.setBdsData(xmlStr.toString());
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnObject(bdsXmlData);
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.BdsXmlDataService#addBdsXmlData(com.amway.frm.afw.entity.BdsXmlData)
	 */
	@Override
	@Transactional
	public ReturnMessage<BdsXmlData> addBdsXmlData(BdsXmlData bdsXmlData) {
		
		bdsXmlData.setBdsXmlDataId(bdsXmlDataDao.generateSequence("MSTB_BDS_XML_DATA"));
		
		ReturnMessage<BdsXmlData> returnMessage = addCom(bdsXmlData);

		return returnMessage;
	}
	
	public void setBdsXmlDataDao(IBdsXmlDataDao bdsXmlDataDao) {
		this.bdsXmlDataDao = bdsXmlDataDao;
	}

}
