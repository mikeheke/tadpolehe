/**
 * 
 */
package mikeheke.tadpole.frm.job.vo;


/**
 * 
 *
 * 2011-9-26 下午03:25:44
 */
public class TimeingJobVo {

	private String timeingJobId;
	
	private String[] timeingJobIds;

	private String timeingJobCode;
	
	private String timeingJobName;
	
	private String applicationId;
	
	private String className;
	
	private String methodName;
	
	private String startTime;
	
	private String endTime;
	
	private String cycle;
	
	private String cycleUnit;
	
	private String cronJobBunch;
	
	private String failNum;
	
	private String remark;
	
	private String state;

	public String getTimeingJobId() {
		return timeingJobId;
	}

	public void setTimeingJobId(String timeingJobId) {
		this.timeingJobId = timeingJobId;
	}

	public String[] getTimeingJobIds() {
		return timeingJobIds;
	}

	public void setTimeingJobIds(String[] timeingJobIds) {
		this.timeingJobIds = timeingJobIds;
	}

	public String getTimeingJobCode() {
		return timeingJobCode;
	}

	public void setTimeingJobCode(String timeingJobCode) {
		this.timeingJobCode = timeingJobCode;
	}

	public String getTimeingJobName() {
		return timeingJobName;
	}

	public void setTimeingJobName(String timeingJobName) {
		this.timeingJobName = timeingJobName;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getCycleUnit() {
		return cycleUnit;
	}

	public void setCycleUnit(String cycleUnit) {
		this.cycleUnit = cycleUnit;
	}

	public String getCronJobBunch() {
		return cronJobBunch;
	}

	public void setCronJobBunch(String cronJobBunch) {
		this.cronJobBunch = cronJobBunch;
	}

	public String getFailNum() {
		return failNum;
	}

	public void setFailNum(String failNum) {
		this.failNum = failNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
