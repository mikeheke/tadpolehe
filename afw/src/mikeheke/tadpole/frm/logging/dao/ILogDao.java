/**
 * 
 */
package mikeheke.tadpole.frm.logging.dao;

import java.sql.SQLException;

import mikeheke.tadpole.frm.base.dao.IBaseDao;
import mikeheke.tadpole.frm.logging.entity.LogOperation;
import mikeheke.tadpole.frm.logging.entity.LogPerformance;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：日志Dao接口
 */
public interface ILogDao extends IBaseDao {
	
	void savePerformace(LogPerformance lopPerformance)throws SQLException;
	
	void saveOperation(LogOperation lopOperation)throws SQLException;
}
