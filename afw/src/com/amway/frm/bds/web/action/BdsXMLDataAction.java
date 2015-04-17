package com.amway.frm.bds.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.entity.BdsXmlData;
import com.amway.frm.bds.service.BdsXmlDataService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.util.BdsXmlUntil;
import com.amway.frm.bds.vo.BdsXmlDataVo;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * 
 * @author lenovo
 *
 */
public class BdsXMLDataAction extends BdsXmlStructureAction{

	private static final long serialVersionUID = 8833940272385961715L;
	
	private static LogService logger = LogFactory.getLogger(BdsXMLDataAction.class);
	
	private BdsXmlDataVo bdsXmlDataVo;
	private String[] xmlDatas;

	//xml本地数据service
	private BdsXmlDataService bdsXmlDataService;

	private final String XML_COLS = "colNames";
	private final String XML_SCHEMA = "xmlSchema";
	private final String XML_COL_ATT = "colAtts";
	private final String XML_DATA = "xmlData";
	private final String XML_DATAS = "xmlDatas";
	
	@Override
	public String query(){
		
		String result = QRY_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.QRY_OPRT);
		
		BdsSchemaInfor bdsSchemaInfor = super.getEntity(bdsXmlDataVo.getBdsSchemaInforId());
		bdsSchemaInfor = getBdsSchemaInforService().getSchemaInforByJDBC(bdsSchemaInfor);
		
		if(!BdsConstant.SCHEMAINFO_TYPE_LOCAL.equals(
				bdsSchemaInfor.getBdsSchemaInforType())){
			final String msg = "非本地数据结构，不能添加数据";
			this.setMessage(BdsConstant.ID_MSG , msg);
			return QRY_INPUT;
		}
		
		List<String> colNames = BdsXmlUntil.getColNamesFromXmlDoc(
				bdsSchemaInfor.getDataStructureXml());
		
		BdsXmlData bdsXmlData = this.getEntity(bdsSchemaInfor.getBdsSchemaInforId());
		List<Object[]> xmlDatas = bdsXmlDataService.getXmlDataList(bdsXmlData, colNames);
		
		this.setMessage(XML_SCHEMA, bdsSchemaInfor);
		this.setMessage(XML_COLS, colNames);
		this.setMessage(XML_DATAS, xmlDatas);
		
		return result;
	}

	/**
	 * 进入增加页面时的初始化方法
	 * @return String
	 */
	@Override
	public String initAdd(){
		
		String result = INIT_ADD_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		BdsSchemaInfor bdsSchemaInfor = super.getEntity(bdsXmlDataVo.getBdsSchemaInforId());
		bdsSchemaInfor = getBdsSchemaInforService().getSchemaInforByJDBC(bdsSchemaInfor);
		
		List<String> colNames = BdsXmlUntil.getColNamesFromXmlDoc(
				bdsSchemaInfor.getDataStructureXml());
		
		List<Map<String, String>> colAtts = BdsXmlUntil.getColAttValuesFromXmlDoc(
				bdsSchemaInfor.getDataStructureXml());
		
		BdsXmlData bdsXmlData = new BdsXmlData();
		Map<String, String> xmlDatas = bdsXmlDataService.getBdsXmlDataMap(bdsXmlData, colNames);
		
		this.setMessage(XML_SCHEMA, bdsSchemaInfor);
		this.setMessage(XML_COL_ATT, colAtts);
		this.setMessage(XML_DATA, bdsXmlData);
		this.setMessage(XML_DATAS, xmlDatas);
		
		return result;
	}	
	/**
	 * 进入修改页面时的初始化方法
	 * @return String
	 */
	@Override
	public String initModify() {

		String result = INIT_MODIFY_INPUT;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String[] bdsXmlDataIds = null;
		if(null != bdsXmlDataVo){
			bdsXmlDataIds = bdsXmlDataVo.getBdsXmlDataIds();
		}
		
		BdsSchemaInfor bdsSchemaInfor = null;
		BdsXmlData bdsXmlData = null;
		Map<String, String> xmlDatas = null;
		List<Map<String, String>> colAtts = null;
		if(validateIds0(bdsXmlDataIds)){
			
			bdsSchemaInfor = super.getEntity(bdsXmlDataVo.getBdsSchemaInforId());
			bdsSchemaInfor = getBdsSchemaInforService().getSchemaInforByJDBC(bdsSchemaInfor);
		
			List<String> colNames = BdsXmlUntil.getColNamesFromXmlDoc(
				bdsSchemaInfor.getDataStructureXml());
			
			colAtts = BdsXmlUntil.getColAttValuesFromXmlDoc(
					bdsSchemaInfor.getDataStructureXml());
		
			bdsXmlData = this.getEntity(bdsSchemaInfor.getBdsSchemaInforId());
			bdsXmlData.setBdsXmlDataId(DataConverter.stringToLong(bdsXmlDataIds[0]));
			bdsXmlData = bdsXmlDataService.getBdsXmlData(bdsXmlData).getReturnObject();
			xmlDatas = bdsXmlDataService.getBdsXmlDataMap(bdsXmlData, colNames);
			
			result = INIT_MODIFY_SUCCESS;
		}
		
		this.setMessage(XML_SCHEMA, bdsSchemaInfor);
		this.setMessage(XML_COL_ATT, colAtts);
		this.setMessage(XML_DATA, bdsXmlData);
		this.setMessage(XML_DATAS, xmlDatas);
		
		return result;
	}

	/**
	 * 增加数据
	 * @return
	 */
	public String add(){
		
		String result = ADD_INPUT;
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		BdsSchemaInfor bdsSchemaInfor = super.getEntity(bdsXmlDataVo.getBdsSchemaInforId());
		bdsSchemaInfor = getBdsSchemaInforService().getSchemaInforByJDBC(bdsSchemaInfor);
		
		if(validateData(bdsSchemaInfor.getDataStructureXml())){
			
			ReturnMessage<BdsXmlData> returnMessage = assembleBdsXmlData(bdsSchemaInfor);
			if(returnMessage.isSuccess()){
				returnMessage = bdsXmlDataService.addBdsXmlData(returnMessage.getReturnObject());
			}

			result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
			
			final String msg = "新增基础数据";
			logger.writeOperLog(AppConstant.ADD_OPRT, msg + returnMessage.getReturnObject().getCode());
		}
		
		this.initAdd();
		
		return result;
	}
	
	/**
	 * 增加数据
	 * @return
	 */
	public String modify(){
		
		String result = MDF_INPUT;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String bdsXmlDataId = bdsXmlDataVo.getBdsXmlDataId();
		if(validateId(bdsXmlDataId)){
		
			BdsSchemaInfor bdsSchemaInfor = super.getEntity(bdsXmlDataVo.getBdsSchemaInforId());
			bdsSchemaInfor = getBdsSchemaInforService().getSchemaInforByJDBC(bdsSchemaInfor);
		
			if(validateData(bdsSchemaInfor.getDataStructureXml())){
				ReturnMessage<BdsXmlData> returnMessage = assembleBdsXmlData(bdsSchemaInfor);
				
				if(returnMessage.isSuccess()){
					BdsXmlData bdsXmlData = returnMessage.getReturnObject();
					bdsXmlData.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
					bdsXmlData.setUpdatedTime(new Date());
					returnMessage = bdsXmlDataService.update(bdsXmlData);
				}
				
				result = this.setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
				
				final String msg = "修改基础数据";
				logger.writeOperLog(AppConstant.MDF_OPRT, msg+returnMessage.getReturnObject().getCode());
			}
		}
		
		this.initModify();

		return result;
	}
	
	
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] bdsXmlDataIds = null;
		if(null != bdsXmlDataVo){
			bdsXmlDataIds = bdsXmlDataVo.getBdsXmlDataIds();
		}
		if(validateIds(bdsXmlDataIds)){
			
			List<BdsXmlData> bdsXmlDatas = this.getEntities(bdsXmlDataIds);
			ReturnMessage<BdsXmlData> returnMessage = bdsXmlDataService.logicDeleteList(bdsXmlDatas);
			
			result = this.setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
			
			final String msg = "删除基础数据";
			logger.writeOperLog(AppConstant.DEL_OPRT, msg);
		}
		
		this.query();
		
		return result;
	}

	private ReturnMessage<BdsXmlData> assembleBdsXmlData(BdsSchemaInfor bdsSchemaInfor) {
		
		List<String> colNames = BdsXmlUntil.getColNamesFromXmlDoc(
				bdsSchemaInfor.getDataStructureXml());
		
		BdsXmlData bdsXmlData = new BdsXmlData();
		ReturnMessage<BdsXmlData> returnMessage = bdsXmlDataService.assembleBdsXmlData(
				bdsXmlData, xmlDatas, colNames);
		if(!returnMessage.isSuccess()){
			return returnMessage;
		}
		
		bdsXmlData.setBdsSchemaInfor(bdsSchemaInfor);
		bdsXmlData.setBdsXmlDataId(DataConverter.stringToLong(bdsXmlDataVo.getBdsXmlDataId()));
		bdsXmlData.setState(AppConstant.START);
		bdsXmlData.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		bdsXmlData.setCreatedTime(new Date());
		
		return returnMessage;
	}
	
	@Override
	public List getEntities(String[] bdsXmlDataIds){
		
		List<BdsXmlData> bdsXmlDatas = new ArrayList<BdsXmlData>(); 
		
		for(String bdsXmlDataId: bdsXmlDataIds){
			BdsXmlData bdsXmlData = new BdsXmlData();
			bdsXmlData.setBdsXmlDataId((DataConverter.stringToLong(bdsXmlDataId)));
			bdsXmlDatas.add(bdsXmlData);
		}
		
		return bdsXmlDatas;
	}
	
	protected BdsXmlData getEntity(Long bdsSchemaInforId) {

		BdsXmlData bdsXmlData = new BdsXmlData();
		BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
		bdsSchemaInfor.setBdsSchemaInforId(bdsSchemaInforId);
		bdsSchemaInfor.setRecordState(BdsConstant.START);
		bdsXmlData.setBdsSchemaInfor(bdsSchemaInfor);
		bdsXmlData.setRecordState(BdsConstant.START);
		
		return bdsXmlData;
	}
	
	protected boolean validateData(String xmlSchemaDoc) {
		
		boolean result = true;
		
		Map<String, Map<String, String>> colAtts = null;
		colAtts = BdsXmlUntil.getColAttNameValuesFromXmlDoc(xmlSchemaDoc);
		Set<Entry<String, Map<String, String>>> entrySet = colAtts.entrySet();
		int i = 0;
		for(Entry<String, Map<String, String>> entry: entrySet){
			
			String colName = entry.getKey();
			Map<String, String> colAtt = entry.getValue();
			
			if(DataValidater.isFalse(validateRequired(colName, colAtt, (xmlDatas[i])))){
				result = false;
			}
			if(DataValidater.isFalse(validateColType(colName, colAtt, (xmlDatas[i])))){
				result = false;
			}
			if(DataValidater.isFalse(validateRegex(colName, colAtt, (xmlDatas[i])))){
				result = false;
			}
			
			if(!result){
				return false;
			}
			
			i++;
		}
		
		return result;
	}
	
	private boolean validateRequired(String colName, Map<String, String> colAtt,
			String xmlData){
		
		boolean result = true;
		String required = colAtt.get(BdsConstant.REQUIRED);
		if(BdsConstant.YES_CN.equals(required)){
			if(DataValidater.isStrEmpty(xmlData)){
				final String msg  = "不能为空";
				result = setInputMessage(BdsConstant.REQUIRED_MSG, colName+msg);
			}
		}
		
		return result;
	}
	
	private boolean validateColType(String colName, Map<String, String> colAtt,
			String xmlData){
		
		boolean result = true;
		
		if(DataValidater.isStrEmpty(xmlData)){
			return true;
		}
		String colType = colAtt.get(BdsConstant.COL_TYPE);
		if(BdsConstant.CHAR_TYPE.equals(colType)){
			;
		}else if(BdsConstant.NUMBER_TYPE.equals(colType)){
			if(!DataValidater.isStrNumber((xmlData))){
				final String msg = "应为数字";
				result = setInputMessage(BdsConstant.COL_TYPE_MSG, colName + msg);
			}
		}else if(BdsConstant.DATE_TYPE.equals(colType)){
			if(!DataValidater.isDate((xmlData))){
				final String msg = "应为日期";
				result = setInputMessage(BdsConstant.COL_TYPE_MSG, colName + msg);
			}
		}
		
		return result;
	}
	
	private boolean validateRegex(String colName, Map<String, String> colAtt,
			String xmlData){
		
		boolean result = true;
		String regx = colAtt.get(BdsConstant.REGEX);
		if(!DataValidater.checkRegx(regx, xmlData)){
			final String msg = "格式不正确";
			result = setInputMessage(BdsConstant.REGEX_MSG, colName + msg);
		}
		
		return result;
	}

	public String[] getXmlDatas() {
		return xmlDatas;
	}

	public void setXmlDatas(String[] xmlDatas) {
		this.xmlDatas = xmlDatas;
	}

	public void setBdsXmlDataService(BdsXmlDataService bdsXmlDataService) {
		this.bdsXmlDataService = bdsXmlDataService;
	}

	public BdsXmlDataVo getBdsXmlDataVo() {
		return bdsXmlDataVo;
	}

	public void setBdsXmlDataVo(BdsXmlDataVo bdsXmlDataVo) {
		this.bdsXmlDataVo = bdsXmlDataVo;
	}
}
