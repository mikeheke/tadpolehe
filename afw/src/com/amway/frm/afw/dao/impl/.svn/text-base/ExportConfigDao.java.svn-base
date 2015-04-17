package com.amway.frm.afw.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.amway.frm.afw.dao.IExportConfigDao;
import com.amway.frm.base.dao.impl.BaseDao;
import com.amway.frm.base.util.JDBCHelper;

/**
 * Created by 
 * @author hyc
 * Date: 2013-12-11
 * Time: 11:11:33
 * Declare: 配置导入 导出
 */
public class ExportConfigDao extends BaseDao<Object, Long> implements IExportConfigDao {
   
	/**
     * Declare：根据表名获得列信息
     *
     * @param String table
     * @return List
     * @throws java.sql.SQLException
     */	
	@Override
	public List<HashMap> fetchColumnByTableName(String table) throws SQLException{
		
		List<HashMap> resultData=new ArrayList<HashMap>();
		
		JDBCHelper jdbcHelper = new JDBCHelper(getDataSource());
		StringBuffer sql = new StringBuffer();
		//根据表名取得该表所有列的名称以及类型
		final String sql1 = "select COLUMN_NAME,DATA_TYPE from user_tab_columns where Table_Name='"+table+"'";

		sql.append(sql1);
		
		try{
			boolean result = jdbcHelper.getFirstDocument(sql.toString());
			while(result){
				String columnName = jdbcHelper.getItemValue("COLUMN_NAME");
				
				String dataType = jdbcHelper.getItemValue("DATA_TYPE");
				
				HashMap data = new HashMap();
				
				data.put("columnName", columnName);
				data.put("dataType", dataType);
				
				resultData.add(data);
				
				result = jdbcHelper.getNextDocument();
			}
		}finally{
			jdbcHelper.closeAll();
		}
		
		List<String> pkData=new ArrayList<String>();
		//根据表名取得该表的主键用于update
		final String sql2 = "select COLUMN_NAME from user_cons_columns where table_name ='"+table+"'"
							+" and constraint_name =  (select constraint_name  from user_constraints where table_name = '"+table+"' and constraint_type = 'P')";
		
		sql= new StringBuffer();
		sql.append(sql2);
		
		jdbcHelper = new JDBCHelper(getDataSource());
		
		try{
			boolean result = jdbcHelper.getFirstDocument(sql.toString());
			while(result){
				String columnName = jdbcHelper.getItemValue("COLUMN_NAME");
				
				pkData.add(columnName);
				
				result = jdbcHelper.getNextDocument();
			}
		}finally{
			jdbcHelper.closeAll();
		}
		
		for(HashMap hm :resultData){
			String columnName=hm.get("columnName").toString();
			
			if(pkData.contains(columnName)){
				hm.put("isPK", "1");
			}
			else{
				hm.put("isPK", "0");
			}
			
		}
		
		return resultData;
		
	}
	
	/**
     * 根据表名获取数据
     *
     * @param String table
     * @param String condition
     * @param List columns
     * @return List
     * @throws java.sql.SQLException
     */	
	@Override
	public List<HashMap> fetchDataByTableName(String table,String condition,List<HashMap> columns) throws SQLException{
		
		List<HashMap> resultData=new ArrayList<HashMap>();
		
		JDBCHelper jdbcHelper = new JDBCHelper(getDataSource());
		StringBuffer sql = new StringBuffer();
		final String sql1 = "select * from "+table+" t where 1=1 ";

		sql.append(sql1);
		
		if(condition!=null&&!"".equals(condition)){
			sql.append("and ");
			sql.append(condition);
		}
		
		try{
			boolean result = jdbcHelper.getFirstDocument(sql.toString());
			
			while(result){
				
				HashMap data = new HashMap();
				
				for(HashMap column:columns){
					
					if(column.get("dataType").toString().startsWith("NUMBER")){
						
						String columnkey=column.get("columnName").toString();
						
						Long numberVal=jdbcHelper.getItemLongValueNullable(columnkey);
						
						data.put(columnkey, numberVal);
					}
					else if(column.get("dataType").toString().startsWith("TIMESTAMP")){
						String columnkey=column.get("columnName").toString();
						
						Date dateVal=jdbcHelper.getItemTimeStampValue(columnkey);
						
						data.put(columnkey, dateVal);
					}
					else if(column.get("dataType").toString().startsWith("DATE")){
						String columnkey=column.get("columnName").toString();
						
						Date dateVal=jdbcHelper.getItemDateValue(columnkey);
						
						data.put(columnkey, dateVal);
					}
					else if(column.get("dataType").toString().startsWith("XMLTYPE")){
						String columnkey=column.get("columnName").toString();
						try{
							
							String stringVal=jdbcHelper.getItemXMLValue(columnkey);
							
							data.put(columnkey, stringVal);
						}
						catch(Exception e){
							data.put(columnkey, null);
						}
					}
					else{
						
						String columnkey=column.get("columnName").toString();
						
						String stringVal=jdbcHelper.getItemValueNullable(columnkey);
						
						data.put(columnkey, stringVal);

					}

				}
				
				resultData.add(data);
				
				result = jdbcHelper.getNextDocument();
			}
		}finally{
			jdbcHelper.closeAll();
		}
		
		return resultData;
	}
	
	/**
     * 导入sql语句
     *
     * @param List sqlList
     * @return List
     */
	@Override
	public List<HashMap> importSqlList(List<String> sqlList){
		
		List<HashMap> importResultList=new ArrayList<HashMap>();
		
		JDBCHelper jdbcHelper=null;
		
		try {			 

	        for(String sqltemp:sqlList){
	        	
	        	jdbcHelper = new JDBCHelper(getDataSource());
	        	
	        	HashMap result=new HashMap();
	        	
	        	try{
	        		
	        		jdbcHelper.executeUpdate(sqltemp);
	        		
	        		result.put("sql", sqltemp.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll(" ", "&nbsp;"));
	        		
	        		result.put("result", "success");
	
	        		
		        } catch (Exception e) {
		        	result.put("sql",sqltemp);
		        	result.put("result", "<font color='red'>".concat(e.getMessage().concat("</font>")));
		        }
	        	finally{
	        		jdbcHelper.closeAll();
	        		importResultList.add(result);
	        	}
	        }
		} 
		
        catch (SQLException e1) {
        	e1.printStackTrace();
		}
	
        return importResultList;
	}
	
	/**
     * 记录应用的导出时间
     *
     * @param String appid
     * @return
     */
	@Override
	public void markExportTime(String appid){
		JDBCHelper jdbcHelper =null;
		try{
			jdbcHelper = new JDBCHelper(getDataSource());
			
			jdbcHelper.executeUpdate("insert into mstb_exporttime(APPID,EXPORT_TIME) values ("+appid+",SYSDATE)");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
            try {
				jdbcHelper.closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
     * 根据应用id获得导出时间
     *
     * @param String appid
     * @return
     */
	@Override
	public String fetchExportDate(String appid)  throws SQLException{
		
		String resultData="";
		
		JDBCHelper jdbcHelper = new JDBCHelper(getDataSource());
		StringBuffer sql = new StringBuffer();
		final String sql1 = "select code from(select rownum,to_char(export_time,'yyyy-mm-dd hh24:mi:ss.ff') as code,to_char(export_time,'yyyy-mm-dd hh24:mi:ss.ff') as displayname from mstb_exporttime where appid="+appid+" order by export_time desc ) where rownum<11 order by code desc";

		sql.append(sql1);
		
		try{
			boolean result = jdbcHelper.getFirstDocument(sql.toString());
			while(result){
				String code = jdbcHelper.getItemValue("CODE");
				
				resultData=resultData.concat(",").concat(code);
				
				result = jdbcHelper.getNextDocument();
			}
		}finally{
			jdbcHelper.closeAll();
		}
		
		return resultData.substring(1);
		
	}
}
