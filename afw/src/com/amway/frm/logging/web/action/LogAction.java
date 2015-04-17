/**
 * 
 */
package com.amway.frm.logging.web.action;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogConstant;
import com.amway.frm.logging.vo.LogVo;

/**
 * @author huangweijin
 *
 * 2011-4-15 上午10:20:18
 */
public class LogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3876686108187730401L;

	private LogService logService;
	
	private LogVo logVo;

	@Override
	public String init() {
		return super.init();
	}

	@Override
	public String query() {
		
		String result = QRY_INPUT;
		
		Application application = this.getEntity();
		ReturnMessage<LogVo> returnMessage = logService.query(application);
		result = setReturnMessage(returnMessage, QRY_SUCCESS, QRY_INPUT);
		
		return result;
	}

	@Override
	public String initModify() {
		
		Application application = (Application) logService.querySingle(getEntity());
		logVo.setApplicationName(application.getApplicationName());
		
		return super.initModify();
	}

	@Override
	public String modify() {
		
		String result = MDF_INPUT;
		
		if(!validateData()){
			return result;
		}
		ReturnMessage<LogVo> returnMessage = logService.update(logVo);
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}

	@Override
	protected Application getEntity() {
		Application application = new Application();
		String applicationId = logVo.getApplicationId();
		if(DataValidater.isStrLong(applicationId)){
			application.setApplicationId(DataConverter.stringToLong(applicationId));
		}
		return application;
	}

	@Override
	protected boolean validateData() {
		
		boolean result = true;
		
		if(DataValidater.isStrEmpty(logVo.getFixWay())){
			result = setInputMessage(LogConstant.APPLICATION_NAME_KEY, LogConstant.APPLICATION_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(logVo.getLogLevel())){
			result = setInputMessage(LogConstant.LOG_LEVEL_KEY, LogConstant.LOG_LEVEL_MSG);
		}
		if(DataValidater.isStrEmpty(logVo.getLogFileAdr())){
			result = setInputMessage(LogConstant.LOG_FILE_ADR_KEY, LogConstant.LOG_FILE_ADR_MSG);
		}
		
		return result;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public LogVo getLogVo() {
		return logVo;
	}

	public void setLogVo(LogVo logVo) {
		this.logVo = logVo;
	}
}
