/**
 * 
 */
package mikeheke.tadpole.frm.job.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.job.dao.ITimeingJobDao;
import mikeheke.tadpole.frm.job.entity.TimeingJob;

/**
 * 
 *
 * 2011-9-27 下午04:54:30
 */
public class TimeingJobDao extends BaseDao<TimeingJob, String> implements
		ITimeingJobDao {

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.job.dao.ITimeingJobDao#updateJobRunningState()
	 */
	@Override
	public void updateJobRunningState(TimeingJob job)throws SQLException {
		
		JDBCHelper jdbcHelper = null;
		try{
			jdbcHelper = new JDBCHelper(getDataSource());
			
			final String sql = "update MSTB_TIMEING_JOB set LAST_START_TIME=?,LAST_END_TIME=?,RUN_TIME=?,LAST_FAIL_NUM=?,RUN_STATE=? where TIMEING_JOB_ID=?";
			Object[] pStmtValues = new Object[]{
											new Timestamp(job.getLastStartTime().getTime()),
											new Timestamp(job.getLastEndTime().getTime()),
											job.getRunTime(),
											job.getLastFailNum(),
											job.getRunState(),
											job.getTimeingJobId()};
			jdbcHelper.executeUpdate(sql, pStmtValues);
		}finally{
			jdbcHelper.closeAll();
		}
		
	}

}
