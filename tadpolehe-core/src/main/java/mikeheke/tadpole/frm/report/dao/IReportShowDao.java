/**
 * 
 */
package mikeheke.tadpole.frm.report.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.base.dao.IBaseDao;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.vo.SQL;

/**
 * 
 *
 * 2011-9-17 下午02:55:47
 */
public interface IReportShowDao extends IBaseDao<ReportInfo, String> {

	List<Map<String, Object>> excuteSql(SQL sql)throws SQLException;

}
