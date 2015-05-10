package mikeheke.tadpole.frm.bds.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.bds.dao.IBdsXmlDataDao;
import mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor;
import mikeheke.tadpole.frm.bds.entity.BdsXmlData;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.util.BdsXmlUntil;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.bds.vo.UTF8PostMethod;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * 
 * @author lenovo
 *
 */
public class BdsXmlDataDao extends BaseDao<BdsXmlData, String> implements
		IBdsXmlDataDao {

	@Override
	public List<BdsXmlData> getBdsXmlDataList(BdsXmlData bdsXmlData)
			throws SQLException {
		
		List<BdsXmlData> xmlDatas = new ArrayList<BdsXmlData>();
		
		String whereParams = BdsConstant.EMPTY_STR;
		Map<String, Object> params = new HashMap<String, Object>();
		ArrayList<Object> pStmtValues = new ArrayList<Object>();
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());
			BdsSchemaInfor bdsSchemaInfor = bdsXmlData.getBdsSchemaInfor();
			if(null != bdsSchemaInfor){
				params.put(BdsConstant.BDS_SCHEMAINFOR_ID, bdsSchemaInfor.getBdsSchemaInforId());
			}
			params.put(BdsConstant.BDS_XML_DATA_ID, bdsXmlData.getBdsXmlDataId());
			params.put(BdsConstant.FIELD_RECORD_STATE, bdsXmlData.getRecordState());
			whereParams = jdbcHelper.getWhereParam(params, pStmtValues);
			StringBuffer strSql = new StringBuffer();
			final String sql1 = " select BDS_XML_DATA_ID,BDS_SCHEMAINFOR_ID,CODE,";
			strSql.append(sql1);
			final String sql2 = "DISPLAYNAME,DISPLAYNAME_EN,DISPLAYNAME_TC,";
			strSql.append(sql2);
			//final String sql3 = "t.BDS_DATA.getclobval() xmltypeData ,REMARK,STATE,RECORD_STATE,";
			final String sql3 = "t.BDS_DATA_STR xmltypeData ,REMARK,STATE,RECORD_STATE,"; //20150428
			strSql.append(sql3);
			final String sql4 = "CREATED_USER_ID,CREATED_TIME,UPDATED_USER_ID,UPDATED_TIME ";
			strSql.append(sql4);
			final String sql5 = "from ";
			strSql.append(sql5).append(AppConstant.APP_DEAULT_SCHEMA);
			final String sql6 = ".MSTB_BDS_XML_DATA t ";
			strSql.append(sql6).append(whereParams);
			boolean result = jdbcHelper.getFirstDocument(strSql.toString(), pStmtValues.toArray());
			while (result) {
				xmlDatas.add(getXmlData(jdbcHelper));
				result = jdbcHelper.getNextDocument();
			}
		} finally {
			jdbcHelper.closeAll();
		}
		return xmlDatas;
	}

	@Override
	public List<BdsXmlData> getBdsXmlDataList(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams, String joinOperator)
			throws SQLException, NamingException {
		List<BdsXmlData> returnList = new ArrayList<BdsXmlData>();
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(this.getDataSource());
			
			Map<String, Object> params = new HashMap<String, Object>();
			//addFilterParams(filterParams, params);  //modify by Mike He 20150426
			
			List<String> pStmtValues = new ArrayList<String>();
			pStmtValues.add((bdsSchemaInfor.getBdsSchemaInforId()));
			String whereParams = jdbcHelper.getWhereParamUsedLikeCompare(params,
					pStmtValues, BdsConstant.EMPTY_STR, joinOperator);
			String linker = DataValidater.isStrEmpty(whereParams)?BdsConstant.EMPTY_STR
					:(BdsConstant.EMPTY_ONE_STR+BdsConstant.SQL_AND+BdsConstant.EMPTY_ONE_STR);
			StringBuffer sql = new StringBuffer();
			final String sql1 = " select  BDS_XML_DATA_ID,BDS_SCHEMAINFOR_ID,CODE,";
			sql.append(sql1);
			final String sql2 = "DISPLAYNAME,DISPLAYNAME_EN,DISPLAYNAME_TC,";
			sql.append(sql2);
			//final String sql3 = "t.BDS_DATA.getclobval() xmltypeData ,REMARK,STATE,RECORD_STATE,";
			final String sql3 = "t.BDS_DATA_STR xmltypeData ,REMARK,STATE,RECORD_STATE,"; //20150428
			sql.append(sql3);
			final String sql4 = "CREATED_USER_ID,CREATED_TIME,UPDATED_USER_ID,UPDATED_TIME   from ";
			sql.append(sql4);
			final String sql5 = ".MSTB_BDS_XML_DATA t  ";
			sql.append(AppConstant.APP_DEAULT_SCHEMA).append(sql5);
			final String sql6 = "WHERE RECORD_STATE=1 AND BDS_SCHEMAINFOR_ID=?";
			sql.append(sql6).append(linker).append(whereParams);
			boolean result = jdbcHelper.getFirstDocument(sql.toString(), pStmtValues
					.toArray());
			while (result) {
				returnList.add(getXmlData(jdbcHelper));
				result = jdbcHelper.getNextDocument();
			}
		} finally {
			jdbcHelper.closeAll();
		}
		
		//add by Mike He 20150426
		//根据查询条件，从内存中过滤数据
		returnList = this.filterBdsXmlDataList(returnList, filterParams, joinOperator);
		
		System.out.println("=====> returnList.size: "+returnList.size());
		
		return returnList;
	}
	
	/**
	 * 
	 * @Title: filterBdsXmlDataList   
	 * @Description: 从内存中过滤数据
	 * 
	 * add by Mike He 20150426
	 * 
	 * @param: @param returnList
	 * @param: @param filterParams
	 * @param: @param joinOperator
	 * @param: @return      
	 * @return: List<BdsXmlData>      
	 * @throws
	 */
	private List<BdsXmlData> filterBdsXmlDataList(List<BdsXmlData> returnList,
			Map<String, String[]> filterParams, String joinOperator) {
		
		//二维(表结构形式)-内存中的表结构
		//add by Mike He 20150425
		//从内存中过滤数据(针对xml..的数据)   
		System.out.println("bds test...");
		if (filterParams != null && filterParams.keySet().size() > 0) {			
			
			List<BdsXmlData> returnFilterList = new ArrayList<BdsXmlData>();
			//迭代结果集(类似于游标)
			for (BdsXmlData bds : returnList) {//每条记录
				Map<String,String> bdsXmlDataMap = bds.getBdsDataMap();
				boolean isOrOk = false;//标识该记录是否满足条件 or
				boolean isAndOk = true;
				
				for (String qColName : filterParams.keySet()) {//迭代查询字段
					String[] qColValues = filterParams.get(qColName);
					if (qColValues == null || qColValues.length == 0) {//查询字段的值为空
						continue;
					}
					//查询条件中-对应字段:查询的值
					String qColValue = qColValues[0];
					//结果集合中－对应字段：实际的值
					String aColValue = bdsXmlDataMap.get(qColName);
					
					if (StringUtils.isBlank(qColValue) || StringUtils.isBlank(aColValue)) {
						continue;
					}
					
					if (BdsConstant.SQL_OR.equals(joinOperator)) {//or 查询
						if (qColValue.equals(aColValue)) {//只要有一个条件满足就ok
							isOrOk = true;
						}
					}
					
					if (BdsConstant.SQL_AND.equals(joinOperator)) {//and 查询
						if (!qColValue.equals(aColValue)) {//只要有一个条件不满足
							isAndOk = false;
						}
					}
				}
				
				//迭代完记录
				if (BdsConstant.SQL_OR.equals(joinOperator)) {//or 查询
					if (isOrOk) {
						returnFilterList.add(bds);
					}
				} else if (BdsConstant.SQL_AND.equals(joinOperator)) {//and 查询
					if (isAndOk) {
						returnFilterList.add(bds);
					}
				}	
			}
			
			return returnFilterList;
		}
		
		return returnList;
	}
	

	private void addFilterParams(Map<String, String[]> filterParams,
			Map<String, Object> params) {
		
		if (DataValidater.isMapEmpty(filterParams)) {
			return;
		}
		for (String key : filterParams.keySet()) {
			String[] values = filterParams.get(key);
			for(String value: values){
				if (BdsConstant.FIXED_COL_NAME_CODE.equalsIgnoreCase(key)) {//忽略大小写
					params.put(BdsConstant.CODE, value);
				} else if (BdsConstant.FIXED_COL_NAME_DN.equalsIgnoreCase(key)) {
					params.put(BdsConstant.DISPLAYNAME, value);
				} else if (BdsConstant.FIXED_COL_NAME_DN_EN.equalsIgnoreCase(key)) {
					params.put(BdsConstant.DISPLAYNAME_EN, value);
				} else if (BdsConstant.FIXED_COL_NAME_DN_TC.equalsIgnoreCase(key)) {
					params.put(BdsConstant.DISPLAYNAME_TC, value);
				} else {
					//modify by Mike He 20150426
//					StringBuffer keyTmp = new StringBuffer();
//					final String keyTmp1 = "t.bds_data.extract('//data/";
//					keyTmp.append(keyTmp1);
//					keyTmp.append(DataConverter.valueOf(key));
//					final String keyTmp2 = "/text()').getclobval()";
//					keyTmp.append(keyTmp2);
//					
//					params.put(keyTmp.toString(), value);
				}
			}
		}
	}

	private BdsXmlData getXmlData(JDBCHelper jdbcHelper) throws SQLException {
		
		BdsXmlData xmlData = new BdsXmlData();
		xmlData.setBdsXmlDataId(jdbcHelper.getItemTrueValue(BdsConstant.BDS_XML_DATA_ID));
		xmlData.setCode(jdbcHelper.getItemTrueValue(BdsConstant.CODE));
		xmlData.setDisplayname(jdbcHelper.getItemTrueValue(BdsConstant.DISPLAYNAME));
		xmlData.setDisplaynameEn(jdbcHelper.getItemTrueValue(BdsConstant.DISPLAYNAME_EN));
		xmlData.setDisplaynameTc(jdbcHelper.getItemTrueValue(BdsConstant.DISPLAYNAME_TC));
		xmlData.setBdsData(jdbcHelper.getItemTrueValue(BdsConstant.XML_TYPE_DATA));
		xmlData.setRemark(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_REMARK));
		xmlData.setState(jdbcHelper.getItemIntegerValue(BdsConstant.FIELD_STATE));
		xmlData.setRecordState(jdbcHelper.getItemIntegerValue(BdsConstant.FIELD_RECORD_STATE));
		xmlData.setCreatedUserId(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_CREATED_USER_ID));
		xmlData.setCreatedTime(jdbcHelper.getItemDateTimeValue(BdsConstant.FIELD_CREATED_TIME));
		xmlData.setUpdatedUserId(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_UPDATED_USER_ID));
		xmlData.setUpdatedTime(jdbcHelper.getItemDateTimeValue(BdsConstant.FIELD_UPDATED_TIME));
		
//		//add by Mike He 20150425
//		xmlData.setBdsDataMap(this.bdsXmlDataToMap(xmlData.getBdsData()));
		
		return xmlData;
	}

	/**
	 * 针对autocompelete标签增加一次性从数据库查询数据条数，
	 * 需求提出人 gary long&mark hu。
	 * 
	 * 如果查询参数里包含autoCompleteLimit 键，则重新包装传入的sql语句.
	 * 针对oracle db2
	 * @param filterParams 
	 * 				查询条件
	 * @param sql
	 * 			配置的查询语句
	 * @return
	 * @throws SQLException 
	 * @author 黄波 2013-10-25
	 */
	private String limitSQL(String[] limit, String sql,JDBCHelper jdbcHelper) throws SQLException {


		String newsql = null;
		if (limit != null) {

			String tempsql = sql.toLowerCase();
			String dbname = jdbcHelper.getConn().getMetaData().getDatabaseProductName().toUpperCase();
			
			if (dbname.indexOf(BdsConstant.DB2_DATABASE_NAME) > -1) {
				final String vsql = "SELECT ${queryCol} "
						+ "FROM (SELECT B.*, ROWNUMBER() OVER() AS RN"
						+ " FROM (${oldSQL}) AS B) AS A WHERE A.RN <= "
						+ limit[0];

				String queryCol = null;
				int s = tempsql.indexOf(BdsConstant.SQL_SELECT);
				int e = tempsql.indexOf(BdsConstant.SQL_FROM);

				queryCol = sql.substring(
						(s + BdsConstant.SQL_SELECT.length() + 1), e);
				newsql = vsql.replaceFirst("\\$\\{queryCol\\}", queryCol);
				newsql = newsql.replaceFirst("\\$\\{oldSQL\\}", sql);
			} else if(dbname.indexOf(BdsConstant.ORACLE_DATABASE_NAME) > -1){
				final String vsql = " select * from (${oldSQL}) autocomplete_temp where rownum <=  "
						+ limit[0];
				newsql = vsql.replaceFirst("\\$\\{oldSQL\\}", sql);
			}
			
		}

		return newsql;
	}
	
	/**
	 * 将autoComplete标签的查询条件转为大写
	 * 黄波 2013-10-25
	 * @param filterParams
	 * @param sql
	 * @return
	 */
	private String toUpperSQLWhere(Map<String, String[]> filterParams,String sql){
		
		for (String key : filterParams.keySet()) {
			sql = sql.replaceAll(key, "UPPER(".concat(key).concat( ")"));
		}
		sql = sql.replaceAll("\\?", "UPPER(?)");
		return sql;
	}
	
	@Override
	public BaseDataSourceVo getBdsVo(BdsSchemaInfor bdsSchemaInfor,
			List<String> colNameList, Map<String, String[]> filterParams,
			String joinOperator) throws NamingException, SQLException {
		
		JDBCHelper jdbcHelper = null;
		BaseDataSourceVo bdsVo = new BaseDataSourceVo();
		try {
			jdbcHelper = new JDBCHelper(bdsSchemaInfor.getJndi());
			List<String> pStmtValues = new ArrayList<String>();
			
			boolean isAutoComplete = filterParams.containsKey(BdsConstant.LIMIT_NAME);
			String[] limit = null;
			String newSql = null;
			if(isAutoComplete){
				limit = filterParams.remove(BdsConstant.LIMIT_NAME);
				newSql =  getSql(jdbcHelper, bdsSchemaInfor, filterParams,
						pStmtValues, joinOperator,true);
			}else{
				newSql =  getSql(jdbcHelper, bdsSchemaInfor, filterParams,
						pStmtValues, joinOperator);
			}
			
			//黄波 2013-10-23
			String tempSql = limitSQL(limit, newSql,jdbcHelper);
			newSql = StringUtils.isEmpty(tempSql) ? newSql : tempSql;
			
			boolean result = jdbcHelper.getFirstDocument(newSql, pStmtValues
					.toArray(), true);

			int columnCount = colNameList.size();
			int rowCount = jdbcHelper.getTotalRow();
			Object[][] bdsDatas = new Object[rowCount][columnCount];
			Object[][] bdsReversalDatas = new Object[columnCount][rowCount];
			int row = 0;
			while (result) {
				int column = 0;
				for (String colName : colNameList) {
					String value = null;
					try{
						value = jdbcHelper.getItemTrueValue(colName);
					}catch(Exception e){
						value = BdsConstant.NULL_VALUE;
					}
					bdsDatas[row][column] = value;
					bdsReversalDatas[column][row] = value;
					column++;
				}
				row++;
				result = jdbcHelper.getNextDocument();
			}
			bdsVo.setBdsDatas(bdsDatas);
			bdsVo.setBdsReversalDatas(bdsReversalDatas);
		} finally {
			jdbcHelper.closeAll();
		}
		return bdsVo;
	}
	
	/**
	 * 重载getSql方法
	 * 黄波 2013-10-25
	 * @param jdbcHelper
	 * @param bdsSchemaInfor
	 * @param filterParams
	 * @param pStmtValues
	 * @param joinOperator
	 * @param autoComplete
	 * @return
	 */
	private String getSql(JDBCHelper jdbcHelper, BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams, List<String> pStmtValues, 
			String joinOperator,boolean autoComplete) {
		
		String sql = bdsSchemaInfor.getSql();
		if(DataValidater.isMapEmpty(filterParams)){
			return sql;
		}
		
		String whereParams = jdbcHelper.getWhereParamUsedLikeCompare(
				filterParams, pStmtValues, joinOperator);
		if(DataValidater.isStrEmpty(whereParams)){
			return sql;
		}
		
		if(autoComplete){
			whereParams = toUpperSQLWhere(filterParams, whereParams);
		}
		
		String newSql = BdsConstant.EMPTY_STR;
		int index = sql.toLowerCase().indexOf(BdsConstant.SQL_ORDERBY);
		if(index != -1){
			newSql = sql.substring(index);
			sql = sql.substring(0, index);
		}

		index = sql.toLowerCase().indexOf(BdsConstant.SQL_GROUPBY);
		if(index != -1){
			newSql = sql.substring(index) + BdsConstant.EMPTY_ONE_STR + newSql;
			sql = sql.substring(0, index);
		}
		index = sql.toLowerCase().indexOf(BdsConstant.SQL_WHERE);
		if(index != -1){
			newSql = sql+BdsConstant.EMPTY_ONE_STR+BdsConstant.SQL_AND+BdsConstant.EMPTY_ONE_STR
				+whereParams.replace(BdsConstant.SQL_WHERE, BdsConstant.EMPTY_ONE_STR)+newSql;
		}else{
			newSql = sql + whereParams + newSql;
		}
		
		return newSql;
	}

	private String getSql(JDBCHelper jdbcHelper, BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams, List<String> pStmtValues, 
			String joinOperator) {
		
		String sql = bdsSchemaInfor.getSql();
		if(DataValidater.isMapEmpty(filterParams)){
			return sql;
		}
		
		String whereParams = jdbcHelper.getWhereParamUsedLikeCompare(
				filterParams, pStmtValues, joinOperator);
		if(DataValidater.isStrEmpty(whereParams)){
			return sql;
		}
		
		String newSql = BdsConstant.EMPTY_STR;
		int index = sql.toLowerCase().indexOf(BdsConstant.SQL_ORDERBY);
		if(index != -1){
			newSql = sql.substring(index);
			sql = sql.substring(0, index);
		}

		index = sql.toLowerCase().indexOf(BdsConstant.SQL_GROUPBY);
		if(index != -1){
			newSql = sql.substring(index) + BdsConstant.EMPTY_ONE_STR + newSql;
			sql = sql.substring(0, index);
		}
		index = sql.toLowerCase().indexOf(BdsConstant.SQL_WHERE);
		if(index != -1){
			newSql = sql+BdsConstant.EMPTY_ONE_STR+BdsConstant.SQL_AND+BdsConstant.EMPTY_ONE_STR
				+whereParams.replace(BdsConstant.SQL_WHERE, BdsConstant.EMPTY_ONE_STR)+newSql;
		}else{
			newSql = sql + whereParams + newSql;
		}
		
		return newSql;
	}

	@Override
	public BaseDataSourceVo getBdsVo(List<String> colNameList,
			List<BdsXmlData> bdsXmlDataList) throws JDOMException, IOException {
		
		BaseDataSourceVo bdsVo = new BaseDataSourceVo();
		
		int columnCount = colNameList.size();
		int rowCount = bdsXmlDataList.size();
		Object[][] bdsDatas = new Object[rowCount][columnCount];
		Object[][] bdsReversalDatas = new Object [columnCount][rowCount];
		
	    int row=0;
		for(BdsXmlData bdsXmlData : bdsXmlDataList){
			
			bdsDatas[row][0] = bdsXmlData.getCode();
			bdsDatas[row][1] = bdsXmlData.getDisplayname();
			bdsDatas[row][2] = bdsXmlData.getDisplaynameEn();
			bdsDatas[row][3] = bdsXmlData.getDisplaynameTc();
			
			bdsReversalDatas[0][row] = bdsXmlData.getCode();
			bdsReversalDatas[1][row] = bdsXmlData.getDisplayname();
			bdsReversalDatas[2][row] = bdsXmlData.getDisplaynameEn();
			bdsReversalDatas[3][row] = bdsXmlData.getDisplaynameTc();
			
         	String xmlDoc = bdsXmlData.getBdsData();
         	if(null == xmlDoc){
         		continue;
         	}
			Document doc = BdsXmlUntil.getDocument(xmlDoc);
			Element root = doc.getRootElement();

			int column = -1;
			for (String colName : colNameList) {
				column++;
				if ((BdsConstant.FIXED_COL_NAME_CODE.equals(colName))
						|| (BdsConstant.FIXED_COL_NAME_DN.equals(colName))
						|| (BdsConstant.FIXED_COL_NAME_DN_EN.equals(colName))
						|| (BdsConstant.FIXED_COL_NAME_DN_TC.equals(colName))
						){
					continue;
				}
				if ((BdsConstant.FIXED_COL_NAME_DN_EN2.equals(colName))
						|| (BdsConstant.FIXED_COL_NAME_DN_TC2.equals(colName))){
					continue;
				}
				bdsDatas[row][column] = root.getChild(colName).getText();
				bdsReversalDatas[column][row] = root.getChild(colName).getText();
			}
			row++;
		}
		
		bdsVo.setBdsDatas(bdsDatas);
		bdsVo.setBdsReversalDatas(bdsReversalDatas);
		
		return bdsVo;
	}

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.bds.dao.IBdsXmlDataDao#getBdsVo(mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor, java.util.List, java.util.Map)
	 */
	@Override
	public BaseDataSourceVo getBdsVo2(List<String> colNameList,
			List<Map<String, String>> bdsWsXmlDataList) {
		
		BaseDataSourceVo bdsVo = new BaseDataSourceVo();
		
		int columnCount = colNameList.size();
		int rowCount = bdsWsXmlDataList.size();
		Object[][] bdsDatas = new Object[rowCount][columnCount];
		Object[][] bdsReversalDatas = new Object [columnCount][rowCount];
		
	    int row = AppConstant._0;
		for(Map<String, String> bdsWsXmlData : bdsWsXmlDataList){		
			int column = AppConstant._0;
			for (String colName : colNameList) {	
				String value = bdsWsXmlData.get(colName);
				if(DataValidater.isStrEmpty(value)){
					continue;
				}
				bdsDatas[row][column] = value;
				bdsReversalDatas[column][row] = value;
				column++;
			}
			row++;
		}
		
		bdsVo.setBdsDatas(bdsDatas);
		bdsVo.setBdsReversalDatas(bdsReversalDatas);
		
		return bdsVo;

	}
	
	@Override
	public List<Map<String, String>> getBdsWsXmlDataList(
			BdsSchemaInfor bdsSchemaInfor, String requestXml)
			throws HttpException, IOException {
		
		String uri = bdsSchemaInfor.getWebserviceAddress();
		PostMethod postMethod = new UTF8PostMethod(uri);
	    postMethod.setRequestBody(requestXml);
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);
		final int _200 = 200; 
		if(statusCode != _200){
			return null;
		}
		String responseXml =  postMethod.getResponseBodyAsString();
		String resRoot = bdsSchemaInfor.getWebserviceResRoot();
		List<Map<String, String>> xmlDataMap = BdsXmlUntil.tranXmlNodeToMap(
				responseXml, resRoot);
		
		return xmlDataMap;
	}
	
	public static void main(String[] args) {
		BdsXmlDataDao bdao = new BdsXmlDataDao();
		//Map<String,String> dataMap = bdao.bdsXmlDataToMap("<aaa><bb>mike</bb><cc>he</cc></aaa>");
		//System.out.println(dataMap);
	}
}
