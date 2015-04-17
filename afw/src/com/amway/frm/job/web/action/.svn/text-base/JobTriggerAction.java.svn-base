/**
 * 
 */
package com.amway.frm.job.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.amway.frm.base.util.DataValidater;
import com.amway.frm.job.entity.TimeingJob;
import com.amway.frm.job.exception.JobInfoException;
import com.amway.frm.job.util.JobConstant;


/**
 * @author huangweijin
 *
 * 2011-9-28 上午09:26:54
 */
public class JobTriggerAction extends TimeingJobAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2390446157086544208L;
	
	private String jobCode;

	@Override
	public String execute() throws Exception {
		
		TimeingJob timeingJob = getEntity();
		
		getTimeingJobService().run(timeingJob);
		
		this.setReturnMessage();
		
		return NONE;
	}
	
	private void setReturnMessage()throws IOException {

		StringBuffer buf = new StringBuffer();
		buf.append(JobConstant.JS_BEGIN);
		buf.append(JobConstant.WINDOW_CLOSE_JS);
		buf.append(JobConstant.JS_END);

		PrintWriter writer = getWriter();
		writer.write(buf.toString());

	}

	private PrintWriter getWriter() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		return response.getWriter();
	}

	@Override
	protected TimeingJob getEntity() {
		
		TimeingJob timeingJob = new TimeingJob();
		timeingJob.setTimeingJobCode(jobCode);
		
		if(DataValidater.isStrEmpty(jobCode)){
			throw new JobInfoException(JobConstant.JOB_INFO_EXP_0001);
		}
		
		timeingJob = (TimeingJob) getTimeingJobService().querySingle(timeingJob);
		
		if(null == timeingJob){
			throw new JobInfoException(JobConstant.JOB_INFO_EXP_0002);
		}
		
		if(null == timeingJob.getState()
				|| timeingJob.getState().intValue() != JobConstant.START.intValue()){
			throw new JobInfoException(JobConstant.JOB_INFO_EXP_0003);
		}
		
		return timeingJob;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
}
