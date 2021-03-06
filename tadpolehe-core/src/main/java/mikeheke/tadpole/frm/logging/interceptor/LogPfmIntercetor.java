/**
 * 
 */
package mikeheke.tadpole.frm.logging.interceptor;

import java.util.Date;

import javax.annotation.Resource;

import mikeheke.tadpole.frm.afw.vo.SysInfoBean;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.logging.entity.LogPerformance;
import mikeheke.tadpole.frm.logging.service.LogService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：性能日志拦截器接口
 */
@Aspect
@Component
public class LogPfmIntercetor {

	@Resource(name="LogService")
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	/**
	 * Declare：拦截业务方法
	 * 
	 * @param point 业务上下文
	 * @param monitor monitor
	 * @return Object 返回对象
	 * @throws Throwable 
	 */
	@Around("@annotation(monitor)")
	public Object handle(ProceedingJoinPoint point, Monitor monitor)
			throws Throwable {
		
		Object objectRet = new Object();
		
		ReturnMessage<LogPerformance> returnMessage = null;
		
		try{
			returnMessage = start(point, monitor);
		
			objectRet = doProceed(point);
			
			returnMessage = finish(returnMessage.getReturnObject());
			
		}finally{
			//logService.writePerfLog(returnMessage.getReturnObject());
			logService.info("写性能日志...");
		}
		
		return objectRet;
	}
	
	private ReturnMessage<LogPerformance> start(ProceedingJoinPoint point,
			Monitor monitor) {
		
		LogPerformance performance = getEntity(point.getTarget());
		performance.setJobName(monitor.name());
		performance.setStartTime(getStartTime());
		performance.setRunFlag(AppConstant.START);
		
		ReturnMessage<LogPerformance> returnMessage = new ReturnMessage<LogPerformance>();
		returnMessage.setReturnObject(performance);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		
		return returnMessage;
	}
	
	private ReturnMessage<LogPerformance> finish(LogPerformance performance){
		
		performance.setEndTime(getEndTime());
		performance.setRunTime(getEndTime().getTime()-performance.getStartTime().getTime());
		performance.setRunFlag(AppConstant.STOP);
		performance.setUpdatedTime(new Date());
		performance.setUpdatedUserId(performance.getCreatedUserId());
		final String msg = "完成";
		performance.setRemark(msg);
		
		ReturnMessage<LogPerformance> returnMessage = new ReturnMessage<LogPerformance>();
		returnMessage.setReturnObject(performance);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		
		return returnMessage;
	}
	
	private LogPerformance getEntity(Object object) {
		
		LogPerformance performance = new LogPerformance();
		
		if(object instanceof BaseService){
			BaseService baseService = (BaseService) object;
			SysInfoBean sysInfoBean = baseService.getSysInfo();
			performance.setApplication(sysInfoBean.getApplication());
			performance.setModule(sysInfoBean.getCurModule());
			performance.setCreatedUserId((sysInfoBean
					.getUserProfile().getUserProfileId()));
		}
		performance.setCreatedTime(new Date());
		
		return performance;
	}
	
	private Date getStartTime(){
		
		return new Date();
	}
	
	private Date getEndTime(){
		
		return new Date();
	}
	
/*	private long getRunTime(long startTime, long endTime){
		return endTime - startTime;
	}*/
	
	private Object doProceed(ProceedingJoinPoint point) throws Throwable {
		
		return point.proceed();
	}
}
