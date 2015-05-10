/**
 * 
 */
package mikeheke.tadpole.frm.logging.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.logging.dao.ILogDao;
import mikeheke.tadpole.frm.logging.entity.LogOperation;
import mikeheke.tadpole.frm.logging.entity.LogPerformance;
import mikeheke.tadpole.frm.logging.util.LogConstant;

/**
 * 
 *
 * 2011-4-14 下午02:22:07
 */
public class LogDao extends BaseDao implements ILogDao {

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.logging.dao.ILogDao#saveOperation(mikeheke.tadpole.frm.logging.entity.LogOperation)
	 */
	@Override
	public void saveOperation(LogOperation lopOperation) throws SQLException {
		
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());
			//long id = getOperationTableRecordId(jdbcHelper);
			String id = ContextFactory.getUUID();
			Object[] val = getOperationParamValues(lopOperation);

			String tableName = getOperationTableName();
			StringBuffer sql = new StringBuffer();
			final String sql1 = "insert into ";
			sql.append(sql1).append(tableName);
			final String sql2 = "(LOG_OPERATION_ID,EMP_NUMBER,USER_LOCATION,EVENT_TYPE,";
			sql.append(sql2);
			final String sql3 = "EVENT_ACTION,LOG_TIME,LOG_CONTENT,MODULE_ID,";
			sql.append(sql3);
			final String sql4 = "APPLICATION_ID,REMARK,RECORD_STATE,UPDATED_TIME,";
			sql.append(sql4);
			final String sql5 = "UPDATED_USER_ID,CREATED_TIME,CREATED_USER_ID)" + " values(";
			sql.append(sql5);
			final String sql6 = ",?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql.append("'"+id+"'").append(sql6);

			jdbcHelper.executeUpdate(sql.toString(), val);
			
		} finally {
			jdbcHelper.closeAll();
		}
	}

	private long getOperationTableRecordId(JDBCHelper jdbcHelper)
			throws SQLException {
		
		final String jql = "select HSSQ_LOG_OPERATION.NEXTVAL LOG_OPERATION_ID From DUAL";
		boolean result = jdbcHelper.getFirstDocument(jql);
		long id = 0;
		if (result) {
			id = jdbcHelper.getItemLongValue(LogConstant.LOG_OPERATION_ID);
		}
		
		return id;
	}

	private String getOperationTableName(){
		
		final String tableName = "HSTB_LOG_OPERATION";
		final String timeStr = "_yyyyMM";
		
		return tableName+DataConverter.fmtDateToDateStr(new Date(), timeStr);
	}
	
	private Object[] getOperationParamValues(LogOperation logOperation){
		
		Object[] val = new Object[] { 
				logOperation.getEmpNumber(),
				logOperation.getUserLocation(),
				logOperation.getEventType(),
				logOperation.getEventAction(),
				new Timestamp(logOperation.getLogTime().getTime()),
				logOperation.getLogContent(),
				logOperation.getModule()!=null?logOperation.getModule().getModuleName():null,
				logOperation.getApplication()!=null?logOperation.getApplication().getApplicationId():null,
				logOperation.getRemark(),
				logOperation.getRecordState(),
				logOperation.getUpdatedTime()!=null?new Timestamp(logOperation.getUpdatedTime().getTime()):null,
				logOperation.getUpdatedUserId(),
				new Timestamp(logOperation.getCreatedTime().getTime()),
				logOperation.getCreatedUserId()
		};
		
		return val;
	}
	
	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.logging.dao.ILogDao#savePerformace(mikeheke.tadpole.frm.logging.entity.LogPerformance)
	 */
	@Override
	public void savePerformace(LogPerformance lopPerformance)
			throws SQLException {
		
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());
			//long id = getPerformanceTableRecordId(jdbcHelper);
			String id = ContextFactory.getUUID();
			Object[] val = getPerformanceParamValues(lopPerformance);

			String tableName = getPerformanceTableName();
			StringBuffer sql = new StringBuffer();
			final String sql1 = "insert into ";
			sql.append(sql1).append(tableName);
			final String sql2 = "(LOG_PERFORMANCE_ID,JOB_NAME,RUN_TIME,START_TIME,";
			sql.append(sql2);
			final String sql3 = "END_TIME,RUN_FLAG,MODULE_ID,";
			sql.append(sql3);
			final String sql4 = "APPLICATION_ID,REMARK,RECORD_STATE,UPDATED_TIME,";
			sql.append(sql4);
			final String sql5 = "UPDATED_USER_ID,CREATED_TIME,CREATED_USER_ID)" + "values(";
			sql.append(sql5);
			final String sql6 = ",?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql.append("'"+id+"'").append(sql6);

			jdbcHelper.executeUpdate(sql.toString(), val);
			
		} finally {
			jdbcHelper.closeAll();
		}
	}

	private long getPerformanceTableRecordId(JDBCHelper jdbcHelper)
			throws SQLException {

		final String jql = "select HSSQ_LOG_PERFORMANCE.NEXTVAL LOG_PERFORMANCE_ID From DUAL";
		boolean result = jdbcHelper.getFirstDocument(jql);
		long id = 0;
		if (result) {
			id = jdbcHelper.getItemLongValue(LogConstant.LOG_PERFORMANCE_ID);
		}

		return id;
	}

	private String getPerformanceTableName() {

		final String tableName = "HSTB_LOG_PERFORMANCE";
		final String timeStr = "_yyyyMM";

		return tableName+DataConverter.fmtDateToDateStr(new Date(), timeStr);
	}
	
	private Object[] getPerformanceParamValues(LogPerformance logPerformance) {

		Object[] val = new Object[] {
				logPerformance.getJobName(),
				logPerformance.getRunTime(),
				new Timestamp(logPerformance.getStartTime().getTime()),
				logPerformance.getEndTime() != null ? new Timestamp(logPerformance.getEndTime().getTime()) : null,
				logPerformance.getRunFlag(),
				logPerformance.getModule() != null ? logPerformance.getModule().getModuleName() : null,
				logPerformance.getApplication() != null ? logPerformance.getApplication().getApplicationId() : null,
				logPerformance.getRemark(), 
				logPerformance.getRecordState(),
				logPerformance.getUpdatedTime()!=null?new Timestamp(logPerformance.getUpdatedTime().getTime()):null,
				logPerformance.getUpdatedUserId(),
				new Timestamp(logPerformance.getCreatedTime().getTime()),
				logPerformance.getCreatedUserId() };

		return val;
	}
}
