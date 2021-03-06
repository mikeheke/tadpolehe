/**
 * 
 */
package mikeheke.tadpole.frm.job.service;

import java.util.List;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.job.entity.TimeingJob;

/**
 * 
 *
 * 2011-9-27 下午04:51:51
 */
public interface TimeingJobService extends BaseService {

	void run(TimeingJob job);

	ReturnMessage<TimeingJob> resetList(List<TimeingJob> timeingJobs);
}
