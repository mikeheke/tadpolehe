/**
 * 
 */
package mikeheke.tadpole.frm.report.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.report.dao.IReportShowDao;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.vo.SQL;

/**
 * 
 *
 * 2011-9-17 下午02:56:49
 */
public class ReportShowDao extends BaseDao<ReportInfo, String> implements
		IReportShowDao {

	public List<Map<String, Object>> excuteSql(SQL sql)throws SQLException {
		
		//System.out.println("rql:" + sql.getSql().toString());
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		JDBCHelper jdbcHelper = null;
		
		try{
			jdbcHelper = new JDBCHelper(getDataSource());
			boolean result = jdbcHelper.getFirstDocument(sql.getSql().toString(),
					sql.getValues().toArray());
			int rowNum = jdbcHelper.getColumnCount();
			while(result){
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				for(int i=1; i<=rowNum; i++){
					String colName = jdbcHelper.getColumnName(i);
					Object colValue = jdbcHelper.getItemObjTrueValue(i);
					map.put(colName, colValue);
				}
				returnList.add(map);
				
				result = jdbcHelper.getNextDocument();
			}
		}finally{
			jdbcHelper.closeAll();
		}
		
		return returnList;
	}


}
