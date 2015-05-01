package mikeheke.tadpole.frm.afw.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import mikeheke.tadpole.frm.afw.dao.IExportConfigDao;
import mikeheke.tadpole.frm.afw.service.ExportConfigService;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

import org.springframework.transaction.annotation.Transactional;

/**
 * 配置数据导入导出Service实现类
 * @author hyc
 */
public class ExportConfigImpl  extends BaseImpl implements ExportConfigService{
	
	private IExportConfigDao exportConfigDao;
	
	private static final String TABLE_MSTB_APPLICATION="MSTB_APPLICATION";
	
	private static final String TABLE_MSTB_MODULE="MSTB_MODULE";
	
	private static final String TABLE_MSTB_ROLE="MSTB_ROLE";	
	
	private static final String TABLE_MSTB_ROLE_RIGHT="MSTB_ROLE_RIGHT";
	
	private static final String TABLE_MSTB_QUERY_INDEX="MSTB_QUERY_INDEX";	
	
	private static final String TABLE_MSTB_QUERY_SELECT="MSTB_QUERY_SELECT";
	
	private static final String TABLE_MSTB_QUERY_FROM="MSTB_QUERY_FROM";
	
	private static final String TABLE_MSTB_QUERY_WHERE="MSTB_QUERY_WHERE";	
	
	private static final String TABLE_MSTB_QUERY_GROUPBY="MSTB_QUERY_GROUPBY";	
	
	private static final String TABLE_MSTB_QUERY_ORDERBY="MSTB_QUERY_ORDERBY";	
	
	private static final String TABLE_MSTB_QUERY_BUTTON="MSTB_QUERY_BUTTON";
	
	private static final String TABLE_MSTB_BDS_SCHEMAINFOR="MSTB_BDS_SCHEMAINFOR";
	
	private static final String TABLE_MSTB_BDS_XML_DATA="MSTB_BDS_XML_DATA";
	
	/**
     * Declare：生成导出sql
     *
     * @param String module
     * @param String applicationid
     * @param String startdate
     * @param String enddate
     * @return List
     * @throws java.sql.SQLException
     */	
	@Override
	public List<String> generateExportSqls(String module,String applicationid,String startdate,String enddate){
		
		List<String> sqls=new ArrayList<String>();
		
		try {
			
			List<HashMap> columns=exportConfigDao.fetchColumnByTableName(module);
			
			String condition=null;
			
			//根据表的不同生成不同的检索条件
			if(TABLE_MSTB_APPLICATION.equals(module)||
					TABLE_MSTB_MODULE.equals(module)||
					TABLE_MSTB_ROLE.equals(module)||
					TABLE_MSTB_QUERY_INDEX.equals(module)||
					TABLE_MSTB_BDS_SCHEMAINFOR.equals(module)){
				
				condition="t.application_id="+applicationid;
				
			}
			else if(TABLE_MSTB_ROLE_RIGHT.equals(module)){
				condition="exists (select 1 from mstb_role t2 where t.role_id = t2.role_id and t2.application_id ="+applicationid+")";
			}
			else if(TABLE_MSTB_QUERY_SELECT.equals(module)||
					TABLE_MSTB_QUERY_FROM.equals(module)||
					TABLE_MSTB_QUERY_WHERE.equals(module)||
					TABLE_MSTB_QUERY_GROUPBY.equals(module)||
					TABLE_MSTB_QUERY_ORDERBY.equals(module)||
					TABLE_MSTB_QUERY_BUTTON.equals(module)){
				condition="exists (select 1 from mstb_query_index t2 where t2.query_id=t.query_id and t2.application_id="+applicationid+")";

			}
			else if(TABLE_MSTB_BDS_XML_DATA.equals(module)){
				condition="exists (select 1 from mstb_bds_schemainfor t2 where t2.bds_schemainfor_id=t.bds_schemainfor_id and t2.application_id="+applicationid+")";

			}
			
			if(startdate!=null&&!"".equals(startdate)){
				condition+="and (t.updated_time >= to_timestamp('"+startdate+"', 'yyyy-mm-dd hh24:mi:ss.ff') or (t.created_time >=to_timestamp('"+startdate+"', 'yyyy-mm-dd hh24:mi:ss.ff') and t.updated_time is null ))";
			}
			
			if(enddate!=null&&!"".equals(enddate)){
				condition+="and (t.updated_time <= to_timestamp('"+enddate+"', 'yyyy-mm-dd hh24:mi:ss.ff') or (t.created_time <=to_timestamp('"+enddate+"', 'yyyy-mm-dd hh24:mi:ss.ff') and t.updated_time is null ))";
			}
			
			List<HashMap> valueList=exportConfigDao.fetchDataByTableName(module, condition, columns);

			for(HashMap valueMap:valueList){
				
				//如果更新时间为空则生成insert语句，否则生成update语句
				if(valueMap.get("UPDATED_TIME")!=null){
					sqls.add(generateUpdateSql(valueMap,columns,module));
				}
				else{
					sqls.add(generateInsertSql(valueMap,columns,module));
				}	
				
			}
			
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return sqls;
		
	}
	
	/**
     * Declare：生成update语句
     *
     * @param HashMap valueMap
     * @param List<HashMap> columns
     * @param String module
     * @return String
     */	
	private String generateUpdateSql(HashMap valueMap,List<HashMap> columns,String module){
		StringBuffer sql = new StringBuffer();
		
		String sqlUpdateF ="update "+module+" set ";
		
		sql.append(sqlUpdateF);
		
		for(HashMap column : columns){
			
			if("0".equals(column.get("isPK").toString())){
				sql.append(column.get("columnName"));

				sql.append(" = ");
				
				getColumnValue(sql,valueMap.get(column.get("columnName")));				
				
				sql.append(",");

			}
			
		}
		
		sql.deleteCharAt(sql.length()-1);
		
		sql.append(" where 1=1 ");
		
		for(HashMap column : columns){
			
			if("1".equals(column.get("isPK").toString())){
				sql.append("and ");
				
				sql.append(column.get("columnName"));
				
				sql.append(" = ");
				
				getColumnValue(sql,valueMap.get(column.get("columnName")));		
			}
			
		}
		
		return sql.toString();
		
	}
	
	/**
     * Declare：生成insert语句
     *
     * @param HashMap valueMap
     * @param List<HashMap> columns
     * @param String module
     * @return String
     */	
	private String generateInsertSql(HashMap valueMap,List<HashMap> columns,String module){
		
		StringBuffer sql = new StringBuffer();
		
		String sqlInsertF ="insert into "+module+" (";
		
		String sqlInsertM =") values (";		
		
		String sqlInsertB =")";
		
		sql.append(sqlInsertF);
		
		int index=0;
		
		for(HashMap column : columns){
			
			sql.append(column.get("columnName"));
			
			if(index!=(columns.size()-1)){
				sql.append(",");
			}
			
			index++;
		}
		
		index=0;
		
		sql.append(sqlInsertM);
		
		for(HashMap column : columns){
			
			getColumnValue(sql,valueMap.get(column.get("columnName")));
			
			if(index!=(columns.size()-1)){
				sql.append(",");
			}
			
			index++;
		}
		
		sql.append(sqlInsertB);

		return sql.toString();
		
	}
	
	/**
     * Declare：根据数据库列的不同类型获得sql
     *
     * @param StringBuffer sql
     * @param Object value
     * @return
     */	
	private void getColumnValue(StringBuffer sql,Object value){
		
		if(value instanceof String){
			sql.append("'");
			
			//转义sql语句里面的单引号
			sql.append(((String) value).replaceAll("'", "''"));
			
			sql.append("'");
		}					
		else if(value instanceof Long){
			sql.append(value);
		}
		else if(value instanceof Date){
			
			SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSSSS");
			
			sql.append("to_timestamp('");
			
			Date dateValue=(Date)value;
			
			String strDate=f.format(dateValue);
			
			sql.append(strDate);
			
			sql.append("', 'dd-mm-yyyy hh24:mi:ss.ff')");
			
		}
		
		else if(value == null){
			sql.append("null");
		}
		
	}
	
	/**
     * Declare： 记录应用的导出时间
     *
     * @param String appid
     * @return
     */	
	@Override
	@Transactional
	public void markExportTime(String appid){
		
		exportConfigDao.markExportTime(appid);

	}
	
	/**
     * Declare：导入sql语句
     *
     * @param List<String> sqlList
     * @return List<HashMap>
     */
	@Override
	@Transactional
	public List<HashMap> importSql(List<String> sqlList){
		
		return exportConfigDao.importSqlList(sqlList);

	}
	
	/**
     * Declare：根据应用id获得导出时间
     *
     * @param String appid
     * @return ReturnMessage<String>
     */	
	@Override
	public ReturnMessage<String> fetchExportDate(String appid){
		
		ReturnMessage<String> returnMessage = new ReturnMessage<String>();
		
		try{
			
			returnMessage.setReturnObject(exportConfigDao.fetchExportDate(appid));
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			
			return returnMessage;

		}
		catch(Exception e){
			return returnMessage;
		}
		
	}

	public IExportConfigDao getExportConfigDao() {
		return exportConfigDao;
	}


	public void setExportConfigDao(IExportConfigDao exportConfigDao) {
		this.exportConfigDao = exportConfigDao;
	}

}
