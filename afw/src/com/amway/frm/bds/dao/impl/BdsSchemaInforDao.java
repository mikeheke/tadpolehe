package com.amway.frm.bds.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.dao.impl.BaseDao;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.JDBCHelper;
import com.amway.frm.bds.dao.IBdsSchemaInforDao;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.util.BdsConstant;

/**
 * 基础数据服务SchemaInfor持久化实现类
 * 
 */

public class BdsSchemaInforDao extends BaseDao<BdsSchemaInfor, String> implements
		IBdsSchemaInforDao {
	/**
	 * 以JDBC方式查询并返回List<BdsSchemaInforVo>
	 * 
	 * @param bdsSchemaInfor
	 *            查询条件
	 * @return List<BdsSchemaInforVo>
	 * @throws SQLException
	 * @throws NamingException
	 */
	public List<BdsSchemaInfor> getListByJdbc(
			BdsSchemaInfor bdsSchemaInfor) throws SQLException {

		List<BdsSchemaInfor> bdsSchemaInfors = new ArrayList<BdsSchemaInfor>();
		
		String whereParams = BdsConstant.EMPTY_STR;
		HashMap<String, Object> params = new HashMap<String, Object>();
		ArrayList<Object> pStmtValues = new ArrayList<Object>();
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());
			params.put(BdsConstant.BDS_SCHEMAINFOR_ID, bdsSchemaInfor.getBdsSchemaInforId());
			params.put(BdsConstant.BDS_SCHEMAINFOR_CODE, bdsSchemaInfor.getBdsSchemaInforCode());
			whereParams = jdbcHelper.getWhereParam(params, pStmtValues);
			StringBuffer strSql = new StringBuffer();
			//final String sql1 = " select t.*,t.DATA_STRUCTURE_XML.getclobval() xml ,";
			final String sql1 = " select t.*,t.DATA_STRUCTURE_XML_STR xml ,"; //modify by Mike He 20150428
			strSql.append(sql1);
			final String sql2 = "a.APPLICATION_ID ,a.APPLICATION_CODE, a.APPLICATION_NAME  ";
			strSql.append(sql2);
			final String sql3 = "from ";
			strSql.append(sql3);
			strSql.append(AppConstant.APP_DEAULT_SCHEMA);
			final String sql4 = ".MSTB_BDS_SCHEMAINFOR t inner join ";
			strSql.append(sql4);
			strSql.append(AppConstant.APP_DEAULT_SCHEMA);
			final String sql5 = ".MSTB_APPLICATION a  on t.APPLICATION_ID=a.APPLICATION_ID ";
			strSql.append(sql5);
			strSql.append(whereParams);
			
			boolean result = jdbcHelper.getFirstDocument(strSql.toString(), pStmtValues.toArray());
			while (result) {
				BdsSchemaInfor bdsSchemaInforRet = getBdsSchemaInfor(jdbcHelper);
				bdsSchemaInfors.add(bdsSchemaInforRet);
				result = jdbcHelper.getNextDocument();
			}
		} finally {
			jdbcHelper.closeAll();
		}

		return bdsSchemaInfors;
	}

	/**
	 * 将每条记录装入vo
	 * 
	 * @param jdbcHelper
	 *            jdbcHelper处理类
	 * @return BdsSchemaInforVo实体
	 * @throws SQLException  
	 * @throws SQLException
	 */
	private BdsSchemaInfor getBdsSchemaInfor(JDBCHelper jdbcHelper)
			 throws SQLException {
		
		BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
		
		bdsSchemaInfor.setBdsSchemaInforId(jdbcHelper.getItemTrueValue(BdsConstant.BDS_SCHEMAINFOR_ID));
		bdsSchemaInfor.setBdsSchemaInforCode(jdbcHelper.getItemTrueValue(BdsConstant.BDS_SCHEMAINFOR_CODE));
		bdsSchemaInfor.setBdsSchemaInforNameEng(jdbcHelper.getItemTrueValue(BdsConstant.BDS_SCHEMAINFOR_NAME_ENG));
		bdsSchemaInfor.setBdsSchemaInforNameCna(jdbcHelper.getItemTrueValue(BdsConstant.BDS_SCHEMAINFOR_NAME_CNA));
		bdsSchemaInfor.setBdsSchemaInforType(jdbcHelper.getItemTrueValue(BdsConstant.BDS_SCHEMAINFOR_TYPE));
		bdsSchemaInfor.setSql(jdbcHelper.getItemTrueValue(BdsConstant.SQL));
		bdsSchemaInfor.setJndi(jdbcHelper.getItemTrueValue(BdsConstant.JNDI));
		bdsSchemaInfor.setWebserviceAddress(jdbcHelper.getItemTrueValue(BdsConstant.WEBSERVICE_ADDRESS));
		bdsSchemaInfor.setWebserviceFunction(jdbcHelper.getItemTrueValue(BdsConstant.WEBSERVICE_FUNCTION));
		bdsSchemaInfor.setWebserviceUser(jdbcHelper.getItemTrueValue(BdsConstant.WEBSERVICE_USER));
		bdsSchemaInfor.setWebservicePwd(jdbcHelper.getItemTrueValue(BdsConstant.WEBSERVICE_PWD));
		bdsSchemaInfor.setDataStructureXml(jdbcHelper.getItemTrueValue(BdsConstant.XML));
		bdsSchemaInfor.setRemark(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_REMARK));
		bdsSchemaInfor.setState(jdbcHelper.getItemIntegerValue(BdsConstant.FIELD_STATE));
		bdsSchemaInfor.setRecordState(jdbcHelper.getItemIntegerValue(BdsConstant.FIELD_RECORD_STATE));
		bdsSchemaInfor.setCreatedUserId(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_CREATED_USER_ID));
		bdsSchemaInfor.setCreatedTime(jdbcHelper.getItemDateTimeValue(BdsConstant.FIELD_CREATED_TIME));
		bdsSchemaInfor.setUpdatedUserId(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_UPDATED_USER_ID));
		bdsSchemaInfor.setUpdatedTime(jdbcHelper.getItemDateTimeValue(BdsConstant.FIELD_UPDATED_TIME));
		Application application = new Application();
		application.setApplicationId(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_APPLICATION_ID));
		application.setApplicationName(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_APPLICATION_NAME));
		application.setApplicationCode(jdbcHelper.getItemTrueValue(BdsConstant.FIELD_APPLICATION_CODE));
		bdsSchemaInfor.setApplication(application);

		return bdsSchemaInfor;
	}

}
