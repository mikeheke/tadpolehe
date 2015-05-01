package mikeheke.tadpole.frm.bds.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.naming.NamingException;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.dao.IBdsXmlDataDao;
import mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor;
import mikeheke.tadpole.frm.bds.entity.BdsXmlData;
import mikeheke.tadpole.frm.bds.exception.BdsBizException;
import mikeheke.tadpole.frm.bds.exception.BdsSysException;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.service.BdsSchemaInforService;
import mikeheke.tadpole.frm.bds.service.BdsXmlDataService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.util.BdsXmlUntil;
import mikeheke.tadpole.frm.bds.util.CacheFactory;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.jdom.JDOMException;

/**
 * 供内外部调用的基础数据服务实现类
 * 
 */
public class BaseDataSourceServiceImpl extends BaseImpl<Object> implements
		BaseDataSourceService {

	// 基础数据服务数据持久类
	private IBdsXmlDataDao bdsXmlDataDao;

	// 基础数据服务schema接口类
	private BdsSchemaInforService bdsSchemaInforService;

	// 基础数据服务数据持久类
	private BdsXmlDataService bdsXmlDataService;
	
	// 缓存
	private static Cache cacheBds = CacheFactory.getCache();

	/**
	 * 基础数据服务对外以JSON格式提供数据的方法
	 * 
	 * @param bdsKey
	 *            基础数据代码
	 * @param filterMap
	 *            过滤字段名:过滤字段值
	 * @param joinOperator
	 *            sql语句条件连接符and或者or
	 * @return ReturnMessage 返回JSON字符串
	 */
	@Override
	public ReturnMessage<String> getJSONData(String bdsKey,
			Map<String, String[]> filterMap, String joinOperator) {

		ReturnMessage<String> returnMessage = new ReturnMessage<String>();

		ReturnMessage<BaseDataSourceVo> returnMBds = this.getBdsVoData(bdsKey,
				filterMap, joinOperator);

		BaseDataSourceVo bdsVo = returnMBds.getReturnObject();
		String jsonStr = BdsXmlUntil.assembleJsonStr(bdsVo);

		returnMessage.setReturnObject(jsonStr);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);

		return returnMessage;
	}

	/**
	 * 基础数据服务对外以BaseDataSourceVo格式提供数据的方法
	 * 
	 * @param bdsKey
	 *            基础数据代码
	 * @param filterMap
	 *            过滤字段名:过滤字段值
	 * @param joinOperator
	 *            sql语句条件连接符and或者or
	 * @return ReturnMessage 返回BaseDataSourceVo对象
	 * @throws BdsBizException
	 */
	@Override
	public ReturnMessage<BaseDataSourceVo> getBdsVoData(String bdsKey,
			Map<String, String[]> filterMap, String joinOperator) {
		BaseDataSourceVo baseDataSourceVo = null;
		ReturnMessage<BaseDataSourceVo> returnMessage = new ReturnMessage<BaseDataSourceVo>();
		BdsSchemaInfor bdsSchemaInfor = getBdsSchemaInforByCode(bdsKey);
		if (null == bdsSchemaInfor) {
			returnMessage.setReturnMsg(BdsConstant.SCHEMA_NO_FOUND_MSG);
			return returnMessage;
		}
		
		// 先从缓存中取数据
		if (filterMap.isEmpty()) {
			Element element = cacheBds.get(bdsKey);
			if (element != null) {
				returnMessage.setReturnObject((BaseDataSourceVo) element.getObjectValue());
				returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
				return returnMessage;
			}
		}
		String bdsSchemaInfoType = bdsSchemaInfor.getBdsSchemaInforType();

		if (BdsConstant.SCHEMAINFO_TYPE_SQL.equals(bdsSchemaInfoType)) {
			baseDataSourceVo = getJDBCData(bdsSchemaInfor, filterMap,
					joinOperator);
		} else if (BdsConstant.SCHEMAINFO_TYPE_WS.equals(bdsSchemaInfoType)) {
			baseDataSourceVo = getWSData(bdsSchemaInfor, filterMap);
		} else if (BdsConstant.SCHEMAINFO_TYPE_LOCAL.equals(bdsSchemaInfoType)) {
			baseDataSourceVo = getXMLTypeData(bdsSchemaInfor, filterMap,
					joinOperator);
		}
		
		//加进缓存
		if (filterMap.isEmpty() && baseDataSourceVo != null) {
			cacheBds.put(new Element(bdsKey, baseDataSourceVo));
		}
		
		returnMessage.setReturnObject(baseDataSourceVo);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);

		return returnMessage;
	}

	private BdsSchemaInfor getBdsSchemaInforByCode(String schemaCode) {

		BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
		bdsSchemaInfor.setBdsSchemaInforCode(schemaCode);
		bdsSchemaInfor = bdsSchemaInforService
				.getSchemaInforByJDBC(bdsSchemaInfor);

		return bdsSchemaInfor;
	}

	/**
	 * 以JDBC的方式取数据
	 * 
	 * @param bdsSchemaInforVo
	 *            bdsSchemaINfor信息
	 * @param filterMap
	 *            过滤字段名:过滤字段值
	 * @param joinOperator
	 *            sql语句条件连接符and或者or
	 * @return BaseDataSourceVo 返回BaseDataSourceVo对象
	 * @throws NamingException
	 * @throws SQLException
	 */
	private BaseDataSourceVo getJDBCData(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterMap, String joinOperator) {

		BaseDataSourceVo returnBdsVo = null;
		List<String> colNameList = BdsXmlUntil
				.getColNamesFromXmlDoc(bdsSchemaInfor.getDataStructureXml());

		try {
			Map<String, String[]> filterMapRet = this.getActualFilterParams(
					filterMap, bdsSchemaInfor.getSql());
			returnBdsVo = bdsXmlDataDao.getBdsVo(bdsSchemaInfor, colNameList,
					filterMapRet, joinOperator);
			returnBdsVo.setBdsColNames(colNameList);
		} catch (Exception e) {
			throw new BdsSysException(e);
		}

		return returnBdsVo;
	}

	/**
	 * 取WebService数据集
	 * 
	 * @param bdsSchemaInforVo
	 *            bdsSchemaINfor信息
	 * @param filterMap
	 *            过滤字段名:过滤字段值
	 * @param joinOperator
	 *            sql语句条件连接符and或者or
	 * @return BaseDataSourceVo 返回BaseDataSourceVo对象
	 * @throws NamingException
	 * @throws SQLException
	 */
	private BaseDataSourceVo getWSData(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams) {

		BaseDataSourceVo returnBdsVo = null;
		List<String> colNameList = BdsXmlUntil
				.getColNamesFromXmlDoc(bdsSchemaInfor.getDataStructureXml());

		try {
			String requestXml = getWsRequestXml(bdsSchemaInfor, filterParams);
			List<Map<String, String>> bdsWsXmlDataList = bdsXmlDataDao
					.getBdsWsXmlDataList(bdsSchemaInfor, requestXml);
			returnBdsVo = bdsXmlDataDao.getBdsVo2(colNameList, bdsWsXmlDataList);
			returnBdsVo.setBdsColNames(colNameList);
		} catch (Exception e) {
			throw new BdsSysException(e);
		}

		return returnBdsVo;
	}

	/**
	 * 取本地数据集
	 * 
	 * @param bdsSchemaInforVo
	 *            bdsSchemaINfor信息
	 * @param filterMap
	 *            过滤字段名:过滤字段值
	 * @param joinOperator
	 *            sql语句条件连接符and或者or
	 * @return BaseDataSourceVo 返回BaseDataSourceVo对象
	 * @throws JDOMException
	 * @throws IOException
	 */
	private BaseDataSourceVo getXMLTypeData(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams, String joinOperator) {

		BaseDataSourceVo returnBdsVo = null;

		List<String> colNames = BdsXmlUntil
				.getColNamesFromXmlDoc(bdsSchemaInfor.getDataStructureXml());

		List<BdsXmlData> bdsXmlDataList = null;
		bdsXmlDataList = bdsXmlDataService.getBdsXmlDataList(bdsSchemaInfor,
				filterParams, joinOperator);

		try {
			returnBdsVo = bdsXmlDataDao.getBdsVo(colNames, bdsXmlDataList);
			returnBdsVo.setBdsColNames(colNames);
		} catch (Exception e) {
			throw new BdsSysException(e);
		}

		return returnBdsVo;
	}

	/**
	 * 把别名和真实字段名进行转换
	 * 
	 * @param filterParams
	 *            过滤字段：过滤字段值
	 * @param pStmtValues
	 * @param joinOperator
	 *            操作符（目前仅支持"or","and" 两种）
	 * @return String
	 */
	private Map<String, String[]> getActualFilterParams(
			Map<String, String[]> filterParams, String sql) {

		Map<String, String[]> params = new HashMap<String, String[]>();

		if (null == filterParams) {
			return params;
		}

		Set<Entry<String, String[]>> filterParamKeyValues = filterParams
				.entrySet();
		for (Entry<String, String[]> filterParamKeyValue : filterParamKeyValues) {
			
			
			String aliasName = filterParamKeyValue.getKey();
			
			//黄波 2013-10-22
			if(BdsConstant.LIMIT_NAME.equals(aliasName)){
				params.put(BdsConstant.LIMIT_NAME, filterParams.get(BdsConstant.LIMIT_NAME));
			}else{
				Map<String, String> aliasActualColNames = getAliasActualColNames(sql);
				String actualColName = aliasActualColNames.get(aliasName);

				String[] value = filterParamKeyValue.getValue();
				params.put(actualColName, value);
			}
			
		}

		return params;
	}

	private Map<String, String> getAliasActualColNames(String sql) {

		Map<String, String> aliasActualColNames = new HashMap<String, String>();

		String sql2 = sql.substring(sql.toLowerCase().indexOf(BdsConstant.SQL_SELECT) + 6
				, sql.toLowerCase().indexOf(BdsConstant.SQL_FROM));
		String[] colNameArr = sql2.split(BdsConstant.DOU_SIGN);
		final String asSep = BdsConstant.EMPTY_ONE_STR+BdsConstant.SQL_AS+BdsConstant.EMPTY_ONE_STR;
		for (String colName : colNameArr) {
			
			String actualColName = colName;
			String aliasColName = colName;
			int index = colName.toLowerCase().indexOf(asSep);
			if (index != -1) {
				actualColName = colName.substring(0, index);
				aliasColName = colName.substring(index + asSep.length());
			} else {
				index = aliasColName.indexOf(BdsConstant.DOT_SIGN);
				if (index != -1) {
					aliasColName = aliasColName.substring(index + 1);
				}
			}
			aliasActualColNames.put(aliasColName.trim(), actualColName);
		}

		return aliasActualColNames;
	}

	private String getWsRequestXml(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams) {

		String namespace = bdsSchemaInfor.getWebserviceNamespace();
		String function = bdsSchemaInfor.getWebserviceFunction();
		String reqRoot = bdsSchemaInfor.getWebserviceReqRoot();
		StringBuffer requestXml = new StringBuffer();
		requestXml.append(BdsConstant.WS_ENVELOPE_BEGIN);
		requestXml.append(namespace).append(BdsConstant.QUO_D_SIGN).append(BdsConstant.RIGHT_J_KUO);
		requestXml.append(BdsConstant.WS_HEADER);
		requestXml.append(BdsConstant.WS_BODY_BEGIN);
		requestXml.append(BdsConstant.EMPTY_ONE_STR).append(BdsConstant.WS_XSD_BEGIN);
		requestXml.append(function).append(BdsConstant.RIGHT_J_KUO);
		if (!DataValidater.isStrEmpty(reqRoot)) {
			requestXml.append(BdsConstant.LEFT_J_KUO).append(reqRoot).append(BdsConstant.RIGHT_J_KUO);
		}
		if (!DataValidater.isMapEmpty(filterParams)) {
			Set<Entry<String, String[]>> paramNameValues = filterParams
					.entrySet();
			for (Entry<String, String[]> paramNameValue : paramNameValues) {
				String name = paramNameValue.getKey();
				String[] value = paramNameValue.getValue();
				requestXml.append(BdsConstant.WS_XSD_BEGIN).append(name).append(BdsConstant.RIGHT_J_KUO);
				requestXml.append(DataConverter.valueOfWN(value));
				requestXml.append(BdsConstant.WS_XSD_END).append(name).append(BdsConstant.RIGHT_J_KUO);	
			}
		}
		if (!DataValidater.isStrEmpty(reqRoot)) {
			requestXml.append(BdsConstant.LEFT_J_KUO).append(BdsConstant.UNIX_SEP);
			requestXml.append(reqRoot).append(BdsConstant.RIGHT_J_KUO);
		}
		requestXml.append(BdsConstant.WS_XSD_END).append(function).append(BdsConstant.RIGHT_J_KUO);
		requestXml.append(BdsConstant.WS_BODY_END);
		requestXml.append(BdsConstant.WS_ENVELOPE_END);

		return requestXml.toString();
	}

	public void setBdsSchemaInforService(
			BdsSchemaInforService bdsSchemaInforService) {
		this.bdsSchemaInforService = bdsSchemaInforService;
	}

	public void setBdsXmlDataService(BdsXmlDataService bdsXmlDataService) {
		this.bdsXmlDataService = bdsXmlDataService;
	}

	public void setBdsXmlDataDao(IBdsXmlDataDao bdsXmlDataDao) {
		this.bdsXmlDataDao = bdsXmlDataDao;
	}
}