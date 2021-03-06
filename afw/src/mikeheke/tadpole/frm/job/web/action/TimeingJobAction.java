/**
 * 
 */
package mikeheke.tadpole.frm.job.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.job.entity.TimeingJob;
import mikeheke.tadpole.frm.job.service.TimeingJobService;
import mikeheke.tadpole.frm.job.util.JobConstant;
import mikeheke.tadpole.frm.job.vo.TimeingJobVo;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 *
 * 2011-9-27 下午04:50:04
 */
public class TimeingJobAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8118490841707804631L;

	private TimeingJobService timeingJobService;
	
	private TimeingJobVo timeingJobVo;

	@Override
	public String initAdd() {

		String result = INIT_ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);
		
		return result;
	}

	@Override
	public String initModify() {
		
		String result = INIT_MODIFY_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		String[] timeingJobIds = null;
		if(null != timeingJobVo){
			timeingJobIds = timeingJobVo.getTimeingJobIds();
		}
		if(!validateIds0(timeingJobIds)){
			return INIT_MODIFY_INPUT;
		}
		ReturnMessage<TimeingJob> returnMessage = null;
		TimeingJob timeingJob = this.getEntity(timeingJobIds[0]);
		returnMessage = timeingJobService.query(timeingJob);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}

	@Override
	public String add() {
		
		init();
		
		String result = ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);

		TimeingJob timeingJob = this.getEntity();
		ReturnMessage<TimeingJob> returnMessage = new ReturnMessage<TimeingJob>();
		
		if(!validateData()){
			returnMessage.setReturnObject(timeingJob);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage = timeingJobService.addCom(timeingJob);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		return result;
	}

	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] timeingJobIds = null;
		if(null != timeingJobVo){
			timeingJobIds = timeingJobVo.getTimeingJobIds();
		}
		if(validateIds(timeingJobIds)){

			List<TimeingJob> timeingJobs = this.getEntities(timeingJobIds);
			ReturnMessage<TimeingJob> returnMessage = timeingJobService.logicDeleteList(timeingJobs);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	
	public String reset() {
		
		String result = DEL_INPUT;
		
		String[] timeingJobIds = null;
		if(null != timeingJobVo){
			timeingJobIds = timeingJobVo.getTimeingJobIds();
		}
		if(validateIds(timeingJobIds)){

			List<TimeingJob> timeingJobs = this.getEntities(timeingJobIds);
			ReturnMessage<TimeingJob> returnMessage = timeingJobService.resetList(timeingJobs);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}

	@Override
	public String modify() {
		
		init();
		
		String result = MDF_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		TimeingJob timeingJob = this.getEntity();
		ReturnMessage<TimeingJob> returnMessage = new ReturnMessage<TimeingJob>();
		
		if(!validateData()){
			returnMessage.setReturnObject(timeingJob);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String timeingJobId = timeingJobVo.getTimeingJobId();
		if(!validateId(timeingJobId)){
			returnMessage.setReturnObject(timeingJob);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = timeingJobService.update(timeingJob);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}
	
	

	@Override
	protected TimeingJob getEntity() {
		
		TimeingJob timeingJob = new TimeingJob();
		timeingJob.setTimeingJobId((timeingJobVo.getTimeingJobId()));
		timeingJob.setTimeingJobCode(timeingJobVo.getTimeingJobCode());
		timeingJob.setTimeingJobName(timeingJobVo.getTimeingJobName());
		Application application = new Application();
		application.setApplicationId((timeingJobVo.getApplicationId()));
		timeingJob.setApplication(application);
		timeingJob.setClassName(timeingJobVo.getClassName());
		timeingJob.setMethodName(timeingJobVo.getMethodName());
		timeingJob.setStartTime(DataConverter.fmtStrToDatetime(timeingJobVo.getStartTime()));
		timeingJob.setEndTime(DataConverter.fmtStrToDatetime(timeingJobVo.getEndTime()));
		timeingJob.setCycle(DataConverter.stringToInteger(timeingJobVo.getCycle()));
		timeingJob.setCycleUnit(DataConverter.stringToInteger(timeingJobVo.getCycleUnit()));
		timeingJob.setCronJobBunch(timeingJobVo.getCronJobBunch());
		timeingJob.setFailNum(DataConverter.stringToInteger(timeingJobVo.getFailNum()));
		timeingJob.setState(DataConverter.stringToInteger(timeingJobVo.getState()));
		timeingJob.setRemark(timeingJobVo.getRemark());
		timeingJob.setCreatedTime(new Date());
		timeingJob.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		timeingJob.setUpdatedTime(new Date());
		timeingJob.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		return timeingJob;
	}

	@Override
	protected TimeingJob getEntity(String id) {
		
		TimeingJob timeingJob = new TimeingJob();
		timeingJob.setTimeingJobId((id));
		
		return timeingJob;
	}

	@Override
	protected List<TimeingJob> getEntities(String[] ids) {
		
		List<TimeingJob> timeingJobs = new ArrayList<TimeingJob>(); 
		
		for(String id: ids){
			TimeingJob timeingJob = getEntity(id);
			timeingJobs.add(timeingJob);
		}
		
		return timeingJobs;
	}

	@Override
	protected boolean validateData() {
		
		boolean result = true;
		
		if(DataValidater.isFalse(DataValidater.isStrEmpty(timeingJobVo.getStartTime()))
				&& DataValidater.isFalse(DataValidater.isDatetime(timeingJobVo.getStartTime()))){
			result = this.setInputMessage(JobConstant.START_TIME_KEY, JobConstant.START_TIME_MSG);
		}
		if(DataValidater.isFalse(DataValidater.isStrEmpty(timeingJobVo.getEndTime())) 
				&& DataValidater.isFalse(DataValidater.isDatetime(timeingJobVo.getEndTime()))){
			result = this.setInputMessage(JobConstant.END_TIME_KEY, JobConstant.END_TIME_MSG);
		}
		if(DataValidater.isFalse(DataValidater.isStrEmpty(timeingJobVo.getCycle())) 
				&& DataValidater.isFalse(DataValidater.isStrInteger(timeingJobVo.getCycle()))){
			result = this.setInputMessage(JobConstant.CYCLE_KEY, JobConstant.CYCLE_MSG);
		}
		if(DataValidater.isFalse(DataValidater.isStrEmpty(timeingJobVo.getCycleUnit())) 
				&& DataValidater.isFalse(DataValidater.isStrInteger(timeingJobVo.getCycleUnit()))){
			result = this.setInputMessage(JobConstant.CYCLE_UNIT_KEY, JobConstant.CYCLE_UNIT_MSG);
		}
		if(DataValidater.isFalse(DataValidater.isStrEmpty(timeingJobVo.getFailNum())) 
				&& DataValidater.isFalse(DataValidater.isStrInteger(timeingJobVo.getFailNum()))){
			result = this.setInputMessage(JobConstant.FAIL_NUM_KEY, JobConstant.FAIL_NUM_MSG);
		}
		
		return result;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
	public TimeingJobVo getTimeingJobVo() {
		return timeingJobVo;
	}

	public void setTimeingJobVo(TimeingJobVo timeingJobVo) {
		this.timeingJobVo = timeingJobVo;
	}

	public void setTimeingJobService(TimeingJobService timeingJobService) {
		this.timeingJobService = timeingJobService;
	}

	@JSON(serialize=false)
	public TimeingJobService getTimeingJobService() {
		return timeingJobService;
	}
	
}
