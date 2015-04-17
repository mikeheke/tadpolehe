/**
 * 
 */
package com.amway.frm.exception.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.exception.entity.ExceptionCfg;
import com.amway.frm.exception.service.ExceptionCfgService;
import com.amway.frm.exception.util.ExceptionConstant;
import com.amway.frm.exception.vo.ExceptionCfgVo;

/**
 * @author huangweijin
 *
 * 2011-4-15 上午09:47:57
 */
public class ExceptionCfgAction extends BaseAction {
 
	private static final long serialVersionUID = -7946839381013927478L;
	
	private ExceptionCfgService exceptionCfgService;
	
	private ExceptionCfgVo exceptionCfgVo;
	
	
	@Override
	public String initAdd(){
		
		String result = INIT_ADD_SUCCESS;
		
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		return result;
	}
	
	@Override
	public String initModify() {
		
		String result = INIT_MODIFY_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String[] exceptionIds = null;
		if(null != exceptionCfgVo){
			exceptionIds = exceptionCfgVo.getExceptionIds();
		}
		if(!validateIds0(exceptionIds)){
			return INIT_MODIFY_INPUT;
		}
		
		ExceptionCfg exceptionCfg = this.getEntity(exceptionIds[0]);
		
		ReturnMessage<ExceptionCfg> returnMessage = null;
		returnMessage = exceptionCfgService.query(exceptionCfg);
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}

	@Override
	public String add() {

		String result = ADD_SUCCESS;
		
		if(!validateData()){
			return ADD_INPUT;
		}
		ExceptionCfg exceptionCfg = this.getEntity();
		
		ReturnMessage<ExceptionCfg> returnMessage = null;
		returnMessage = exceptionCfgService.add(exceptionCfg);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);

		clearVo();
		
		return result;
	}
	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		
		if(!validateData()){
			return MDF_INPUT;
		}
		
		String[] exceptionIds = exceptionCfgVo.getExceptionIds();
		if(!validateIds(exceptionIds)){
			return MDF_INPUT;
		}
		
		ExceptionCfg exceptionCfg = this.getEntity();
		
		ReturnMessage<ExceptionCfg> returnMessage = null;
		returnMessage = exceptionCfgService.update(exceptionCfg);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		clearVo();
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] exceptionIds = null;
		if(null != exceptionCfgVo){
			exceptionIds = exceptionCfgVo.getExceptionIds();
		}
		if(validateIds(exceptionIds)){

			List<ExceptionCfg> exceptionCfgs = this.getEntities(exceptionIds);
			ReturnMessage<ExceptionCfg> returnMessage = exceptionCfgService.logicDeleteList(exceptionCfgs);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
		
	}
	
	public String send(){
		
		Application application = SysInfoBean.getSysInfoBean().getApplication();
		if(null == application){
			application = SysInfoBean.getSysInfoBean().getSysApplication();
		}
		exceptionCfgVo.setApplication(application);
		exceptionCfgService.send(exceptionCfgVo);
		
		return JSON;
	}
	
//	public List<UserProfile> getUserProfiles(){
//		return exceptionCfgService.queryList(new UserProfile());
//	}
	
	@Override
	protected ExceptionCfg getEntity() {
		
		ExceptionCfg exceptionCfg = new ExceptionCfg();
		exceptionCfg.setExceptionId(DataConverter.stringToLong(exceptionCfgVo.getExceptionId()));
		exceptionCfg.setExceptionCode(exceptionCfgVo.getExceptionCode());
		exceptionCfg.setExceptionName(exceptionCfgVo.getExceptionName());
		Application app = new Application();
		app.setApplicationId(DataConverter.stringToLong(exceptionCfgVo.getApplicationId()));
		exceptionCfg.setApplication(app);
		exceptionCfg.setIsSendEmail(DataConverter.stringToInteger(exceptionCfgVo.getIsSendEmail()));
		exceptionCfg.setEmailUsers(exceptionCfgVo.getEmailUserArr());
		exceptionCfg.setUseState(DataConverter.stringToInteger(exceptionCfgVo.getUseState()));
		exceptionCfg.setRecordState(AppConstant.START);
		exceptionCfg.setRemark(exceptionCfgVo.getRemark());
		exceptionCfg.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		exceptionCfg.setCreatedTime(new Date());
		exceptionCfg.setUpdatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		exceptionCfg.setUpdatedTime(new Date());
		
		return exceptionCfg;
	}
	
	
	@Override
	protected ExceptionCfg getEntity(String exceptionId) {
		
		ExceptionCfg exceptionCfg = new ExceptionCfg();
		exceptionCfg.setExceptionId(DataConverter.stringToLong(exceptionId));
		
		return exceptionCfg;
	}

	@Override
	protected List<ExceptionCfg> getEntities(String[] exceptionIds){
		
		List<ExceptionCfg> exceptionCfgs = new ArrayList<ExceptionCfg>(); 
		
		for(String exceptionId: exceptionIds){
			ExceptionCfg exceptionCfg = new ExceptionCfg();
			exceptionCfg.setExceptionId(DataConverter.stringToLong(exceptionId));
			exceptionCfgs.add(exceptionCfg);
		}
		
		return exceptionCfgs;
	}

	/**
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
		
		if(DataValidater.isStrEmpty(exceptionCfgVo.getExceptionCode())){
			result = this.setInputMessage(ExceptionConstant.EXCEPTION_CODE_KEY, getText(ExceptionConstant.NO_EMPTY_KEY));
		}
		if(DataValidater.isStrEmpty(exceptionCfgVo.getExceptionName())){
			result = this.setInputMessage(ExceptionConstant.EXCEPTION_NAME_KEY, getText(ExceptionConstant.NO_EMPTY_KEY));
		}
		if(!DataValidater.isStrLong(exceptionCfgVo.getApplicationId())){
			result = this.setInputMessage(ExceptionConstant.APPLICATION_ID_KEY, getText(ExceptionConstant.YES_LONG_KEY));
		}
		if(!DataValidater.isStrInteger(exceptionCfgVo.getIsSendEmail())){
			result = this.setInputMessage(ExceptionConstant.IS_SEND_EMAIL_KEY, getText(ExceptionConstant.YES_INT_KEY));
		}
		if(!DataValidater.isStrInteger(exceptionCfgVo.getUseState())){
			result = this.setInputMessage(ExceptionConstant.USE_STATE_KEY, getText(ExceptionConstant.YES_INT_KEY));
		}
		
		return result;
	}
	
	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
	private void clearVo() {
		this.exceptionCfgVo = new ExceptionCfgVo();
	}
	
	public void setExceptionCfgService(ExceptionCfgService exceptionCfgService) {
		this.exceptionCfgService = exceptionCfgService;
	}

	public ExceptionCfgVo getExceptionCfgVo() {
		return exceptionCfgVo;
	}

	public void setExceptionCfgVo(ExceptionCfgVo exceptionCfgVo) {
		this.exceptionCfgVo = exceptionCfgVo;
	}

}
