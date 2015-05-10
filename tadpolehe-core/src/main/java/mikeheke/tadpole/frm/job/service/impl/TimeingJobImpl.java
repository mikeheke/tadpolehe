/**
 * 
 */
package mikeheke.tadpole.frm.job.service.impl;

import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.job.dao.ITimeingJobDao;
import mikeheke.tadpole.frm.job.entity.TimeingJob;
import mikeheke.tadpole.frm.job.service.TimeingJobService;
import mikeheke.tadpole.frm.job.thread.Job;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * 2011-9-27 下午04:52:43
 */
public class TimeingJobImpl extends BaseImpl implements TimeingJobService {

	private ITimeingJobDao timeingJobDao;

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.job.service.TimeingJobService#run(mikeheke.tadpole.frm.job.entity.TimeingJob)
	 */
	@Override
	public void run(TimeingJob timeingJob) {

		Job job = new Job(timeingJobDao, timeingJob);
		new Thread(job).start();

	}
	
	@Override
	@Transactional
	public ReturnMessage<TimeingJob> update(Object obj) {
		
		TimeingJob timeingJob = (TimeingJob)obj;
		TimeingJob timeingJobRet = new TimeingJob();
		timeingJobRet.setTimeingJobId(timeingJob.getTimeingJobId());
		timeingJobRet = (TimeingJob) querySingle(timeingJobRet);
		
		if(null != timeingJobRet){
			timeingJob.setLastStartTime(timeingJobRet.getLastStartTime());
			timeingJob.setEndTime(timeingJobRet.getLastEndTime());
			timeingJob.setRunTime(timeingJobRet.getRunTime());
			timeingJob.setLastFailNum(timeingJobRet.getLastFailNum());
			timeingJob.setRunState(timeingJobRet.getRunState());
		}
		
		return super.update(timeingJob);
	}

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.job.service.TimeingJobService#resetList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<TimeingJob> resetList(List<TimeingJob> timeingJobs) {
		
		List<TimeingJob> timeingJobList = new ArrayList<TimeingJob>();
		for(TimeingJob timeingJob: timeingJobs){
			TimeingJob timeingJobRet = (TimeingJob) querySingle(timeingJob);
			if(null == timeingJobRet){
				continue;
			}
			timeingJobRet.setFailNum(0);
			timeingJobList.add(timeingJobRet);
		}
		
		return updateList(timeingJobList);
	}
	
	public void setTimeingJobDao(ITimeingJobDao timeingJobDao) {
		this.timeingJobDao = timeingJobDao;
	}

}
