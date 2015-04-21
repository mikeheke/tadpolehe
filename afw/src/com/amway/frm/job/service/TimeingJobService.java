/**
 * 
 */
package com.amway.frm.job.service;

import java.util.List;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.job.entity.TimeingJob;

/**
 * 
 *
 * 2011-9-27 下午04:51:51
 */
public interface TimeingJobService extends BaseService {

	void run(TimeingJob job);

	ReturnMessage<TimeingJob> resetList(List<TimeingJob> timeingJobs);
}
