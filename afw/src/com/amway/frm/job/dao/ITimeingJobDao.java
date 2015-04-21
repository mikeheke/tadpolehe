/**
 * 
 */
package com.amway.frm.job.dao;

import java.sql.SQLException;

import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.job.entity.TimeingJob;

/**
 * 
 *
 * 2011-9-27 下午04:53:41
 */
public interface ITimeingJobDao extends IBaseDao<TimeingJob, String> {

	void updateJobRunningState(TimeingJob job)throws SQLException;
}
