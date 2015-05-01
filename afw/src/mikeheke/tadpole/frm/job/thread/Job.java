/**
 * 
 */
package mikeheke.tadpole.frm.job.thread;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.job.dao.ITimeingJobDao;
import mikeheke.tadpole.frm.job.entity.TimeingJob;
import mikeheke.tadpole.frm.job.exception.JobSysException;
import mikeheke.tadpole.frm.job.util.JobConstant;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * 2011-9-28 上午10:22:24
 */
public class Job implements Runnable {
	
	private ITimeingJobDao timeingJobDao;
	private TimeingJob timeingJob;
	
	public Job(ITimeingJobDao timeingJobDao, TimeingJob timeingJob){
		this.timeingJobDao = timeingJobDao;
		this.timeingJob = timeingJob;
	}
	
	@Override
	public synchronized void run() {

		synchronized (timeingJob.getTimeingJobCode()) {
			
			String className = timeingJob.getClassName();
			String methodName = timeingJob.getMethodName();
			Class cla = getClass(className);
			Method method = getMethod(cla, methodName);
	
			timeingJob.setLastStartTime(new Date());
			timeingJob.setLastFailNum(0);
			
			Integer failNum = timeingJob.getFailNum2();
			for(int i=0; i<=failNum; i++){
				try{
					method.invoke(cla.newInstance());
					timeingJob.setRunState(JobConstant.YES);
					break;
				}catch(Exception e){
					timeingJob.setLastFailNum(i+1);
					timeingJob.setRunState(JobConstant.NO);
				}
			}
			
			timeingJob.setLastEndTime(new Date());
			timeingJob.setRunTime(timeingJob.getLastEndTime().getTime()
					- timeingJob.getLastStartTime().getTime());
			
			updateJob();
		}
	}
	
	@Transactional
	private void updateJob(){
		try {
			timeingJobDao.updateJobRunningState(timeingJob);
		} catch (SQLException e) {
			throw new JobSysException(e);
		}
	}

	private Class getClass(String className){
		
		Class cla = null;
		try {
			cla = Class.forName(className);
		} catch (ClassNotFoundException e1) {
			throw new JobSysException(e1);
		}
		
		return cla;
	}
	
	private Method getMethod(Class cla, String methodNameIn){
		
		String methodName = methodNameIn;
		if(DataValidater.isStrEmpty(methodName)){
			methodName = JobConstant.DEFAULT_RUN_METHOD;
		}
		Method method = null;
		try {
			method = cla.getDeclaredMethod(methodName);
		} catch (SecurityException e) {
			throw new JobSysException(e);
		} catch (NoSuchMethodException e) {
			throw new JobSysException(e);
		}
		
		return method;
	}
}
