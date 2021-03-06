/**
 * 
 */
package mikeheke.tadpole.frm.query.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.query.dao.IQueryDao;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.entity.Select;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.vo.Row;
import mikeheke.tadpole.frm.query.vo.SQL;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * 2011-2-16 上午11:16:38
 */
public class QueryDao extends BaseDao<Query, String> implements IQueryDao {
	
	private static final String SELECT_FUNCARRAY[]={"DISTINCT"};
	private static final String WHERE_FUNCARRAY[]={"GROUP"};
	
	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.query.dao.IQueryDao#checkDsJndi(java.lang.String)
	 */
	@Override
	public boolean checkDsJndi(String dsJndi) {
		
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(dsJndi);
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (jdbcHelper != null) {
				try {
					jdbcHelper.closeAll();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
		
		return true;
	}

	/*
	 * 取数据
	 */
	@Override
	public List<Row> getQueryDatas(Query query, SQL sql)
			throws SQLException {
		
		List<Row> datas = new ArrayList<Row>();
		
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = getJdbcHelper(query);
			boolean result = jdbcHelper.getFirstDocument(sql.getSql(), 
					sql.getParamterValue(), true);
			
			int totalRow=0;
			
			if(isDefaultCount(sql)){
				totalRow=getTotalRow(query, sql);
			}else{
				totalRow = jdbcHelper.getTotalRow();
			}	
			
//			int totalRow = jdbcHelper.getTotalRow();
			
//			int totalRow=getTotalRow(query, sql);
			
			query.getTable().setTotalRow(totalRow);
			int colNum = jdbcHelper.getColumnCount();
			int rowNum = sql.getStartRow();
			jdbcHelper.moveToRowNum(rowNum);
			int endRow = sql.getEndRow();
			if(endRow > totalRow){
				endRow = totalRow;
			}
			while(result && rowNum<=endRow){
				Row data = new Row();
				for(int i=0; i<colNum; i++){			
					//String colValue =jdbcHelper.getItemTrueValue(i+1);
					Object colValue = null;
					String dataType = getDataType(query, i);
					if(!DataValidater.isStrEmpty(dataType)
							&& QueryConstant.DATA_TYPE_DATE.equals(dataType)){
						try{
							colValue = jdbcHelper.getItemDateTimeValue(i+1);
						}catch(Exception e){
							colValue = jdbcHelper.getItemObjTrueValue(i+1);
						}
					}else{
						colValue = jdbcHelper.getItemObjTrueValue(i+1);
					}
					String colName = getColName(query, jdbcHelper, i);
					data.put(colName, colValue);
				}
				datas.add(data);
				result = jdbcHelper.getNextDocument();
				rowNum++;
			}
			query.getTable().setDatas(datas);
		} catch (NumberFormatException e) {
			return datas;
		} catch (Exception e) {
			throw new SQLException(e);
		}finally{
			jdbcHelper.closeAll();
		}
		
		return datas;
	}
	
	
	/**
	 *  取得数据条数
	 *  @author Zhuang Shao Bin
	 *  @version 2012-7-27
	 *  @param query
	 *  @param sqlObj
	 *  @return
	 *  @throws SQLException
	 *  @see
	 *  @since
	 *  
	 *  @modify by Mike He
	 *  @modify date 2013-1-18
	 */
	private int getTotalRow(Query query,SQL sqlObj) throws SQLException{
		
		JDBCHelper jdbcHelper=null;
		
		final String SELECT=" SELECT COUNT(*) as COUNT ";
		final String FROM=" FROM ";
		final String COUNT="COUNT";
		final String ORDERBY = "ORDER BY";
		
		String sql=sqlObj.getSql().toUpperCase();
		String from_Sql=StringUtils.substringAfter(sql, FROM);
		
		//取数据条数不需要order by 语句
		if (StringUtils.contains(from_Sql, ORDERBY)) {//是否包含ORDER BY 
			//去除ORDER BY 子句
			from_Sql  = StringUtils.substringBeforeLast(from_Sql, "ORDER BY");
		}
		
		StringBuffer buffer=new StringBuffer();
		buffer.append(SELECT).append(FROM).append(from_Sql);
		
		try{
			jdbcHelper=getJdbcHelper(query);
			boolean result = jdbcHelper.getFirstDocument(buffer.toString(),sqlObj.getParamterValue());
			if(result){
				return jdbcHelper.getItemIntegerValue(COUNT);
			}
		}catch (Exception e) {
			throw new SQLException(e);
		}finally{
			jdbcHelper.closeAll();
		}
		
		return 0;
	}
	
	
	// 是否用 默认
	private boolean isDefaultCount(SQL sqlObj){
		
		final String SELECT="SELECT";
		final String FROM="FROM";
		final String WHERE="WHERE";
		
		boolean isDefault=true;
		
		String sql=sqlObj.getSql().toUpperCase();
		String betweenStr=StringUtils.substringBetween(sql, SELECT, FROM);
		String afterWhereStr=StringUtils.substringAfterLast(sql, WHERE);
		
		if(StringUtils.isNotBlank(betweenStr)){
			for(String searchStr:SELECT_FUNCARRAY){
				if(StringUtils.contains(betweenStr, searchStr)){
					isDefault=false;
					break;
				}
			}
		}
		
		if(StringUtils.isNotBlank(afterWhereStr)){
			for(String searchStr:WHERE_FUNCARRAY){
				if(StringUtils.contains(afterWhereStr, searchStr)){
					isDefault=false;
					break;
				}
			}
		}
		
		return isDefault;
	}
	
	private String getDataType(Query query, int index){
		
		if(null == query){
			return null;
		}
		List<Select> selects = query.getSelects();
		if(DataValidater.isCollectionEmpty(selects)){
			return null;
		}
		Select select = selects.get(index);
		return select.getDataType();
	}
	
	protected String getColName(Query query, JDBCHelper jdbcHelper, int index)
			throws SQLException {
		
		Select select = query.getSelects().get(index);
		String colName = select.getTableName() + select.getFieldName()
				+ select.getFieldAlias();
		
		return colName;
	}
	
	protected JDBCHelper getJdbcHelper(Query query) throws NamingException, SQLException{
		
		JDBCHelper jdbcHelper = new JDBCHelper(query.getDsJndi());
		
		return jdbcHelper;
	}
}
