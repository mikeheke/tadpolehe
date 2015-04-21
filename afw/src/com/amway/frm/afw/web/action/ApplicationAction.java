package com.amway.frm.afw.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.service.ApplicationService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.ApplicationVo;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 *  应用系统信息Action
 *  
 */
public class ApplicationAction extends BaseAction {
	
	private static final long serialVersionUID = -2891775900107049963L;
	
	private ApplicationService applicationService;
	
	private ApplicationVo applicationVo;
	
	private LogService logger = LogFactory.getLogger(ApplicationAction.class);
	
	@Override
	public String initAdd(){
		
		String result = INIT_ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);
		
		return result;
	}
	
	@Override
	public String initModify() {

		String result = INIT_MODIFY_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		String[] applicationIds = null;
		if(null != applicationVo){
			applicationIds = applicationVo.getApplicationIds();
		}
		if(!validateIds0(applicationIds)){
			return INIT_MODIFY_INPUT;
		}
		ReturnMessage<Application> returnMessage = null;
		Application application = this.getEntity(applicationIds[0]);
		returnMessage = applicationService.query(application);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	
	@Override
	public String add() {

		String result = ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);

		Application application = this.getEntity();
		ReturnMessage<Application> returnMessage = new ReturnMessage<Application>();
		
		if(!validateData()){
			returnMessage.setReturnObject(application);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage = applicationService.addCom(application);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		final String msg = "新增应用设置";
		logger.writeOperLog(AppConstant.ADD_OPRT, msg+application.getApplicationCode());
		
		return result;
	}
	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		Application application = this.getEntity();
		ReturnMessage<Application> returnMessage = new ReturnMessage<Application>();
		
		if(!validateData()){
			returnMessage.setReturnObject(application);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String  applicationId = applicationVo.getApplicationId();
		if(!validateId(applicationId)){
			returnMessage.setReturnObject(application);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = applicationService.update(application);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		final String msg = "应用设置修改";
		logger.writeOperLog(AppConstant.MDF_OPRT, msg+application.getApplicationCode());
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] applicationIds = null;
		if(null != applicationVo){
			applicationIds = applicationVo.getApplicationIds();
		}
		if(validateIds(applicationIds)){

			List<Application> applications = this.getEntities(applicationIds);
			ReturnMessage<Application> returnMessage = applicationService.logicDeleteList(applications);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	@Override
	public String popup() {
		
		setApplicationVo(null);
		
		this.query();
		
		return super.popup();
	}
	
	@Override
	public String query() {
		
		String result = QRY_INPUT;
		
		Application application = getQryEntity();
		ReturnMessage<Application> returnMessage = applicationService.getApplications(application);
		
		result = setReturnMessage(returnMessage, QRY_SUCCESS, QRY_INPUT);
		
		return result;
	}
	
	@Override
	protected Application getQryEntity(){
		
		Application application = new Application();
		if(null != applicationVo){
			application.setApplicationCode(applicationVo.getApplicationCode());
			application.setApplicationName(applicationVo.getApplicationName());
		}
		application.setState(AppConstant.START);
		
		return application;
	}
	
	@Override
	protected Application getEntity() {
		
		Application application = new Application();
		application.setApplicationId((applicationVo.getApplicationId()));
		application.setApplicationCode(applicationVo.getApplicationCode());
		application.setApplicationName(applicationVo.getApplicationName());
		final Integer depLen = 2;
		if (applicationVo.getDepartmentCode() != null && applicationVo.getDepartmentCode().length == depLen) {
			application.setDepartmentName(applicationVo.getDepartmentCode()[0]);
			application.setDepartmentCode(applicationVo.getDepartmentCode()[1]);
			if (AppConstant.EMPTY_STR.equals(applicationVo.getDepartmentCode()[0])) {
				application.setDepartmentCode(null);
			}
		}
		final Integer empLen = 2;
		if (applicationVo.getFaultHandlerEmpNumber() != null && applicationVo.getFaultHandlerEmpNumber().length == empLen) {
			application.setEmpNumber(applicationVo.getFaultHandlerEmpNumber()[0]);
			application.setFaultHandlerEmpNumber(applicationVo.getFaultHandlerEmpNumber()[1]);
			if (AppConstant.EMPTY_STR.equals(applicationVo.getFaultHandlerEmpNumber()[0])) {
				application.setFaultHandlerEmpNumber(null);
			}
		}
		application.setIsCheckCode(DataConverter.stringToInteger(applicationVo.getIsCheckCode()));
		application.setApplicationLayout(DataConverter.stringToInteger(applicationVo.getApplicationLayout()));
		application.setApplicationStyle(applicationVo.getApplicationStyle());
		application.setFixWay(applicationVo.getFixWay());
		application.setFailNum(DataConverter.stringToInteger(applicationVo.getFailNum()));
		application.setDeployServer(applicationVo.getDeployServer());
		application.setPort(applicationVo.getPort());
		application.setContext(applicationVo.getContext());
		application.setOrderNo(DataConverter.stringToInteger(applicationVo.getOrderNo()));
		application.setTimeZone(applicationVo.getTimeZone());
		application.setLanguange(applicationVo.getLanguange());
		application.setState(DataConverter.stringToInteger(applicationVo.getState()));
		application.setSessionTimeOut(applicationVo.getSessionTimeOut());
		application.setDefaultPage(applicationVo.getDefaultPage());
		application.setRemark(applicationVo.getRemark());
		application.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		application.setCreatedTime(new Date());
		application.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		application.setUpdatedTime(new Date());

		/*
		 * 增加应用的认证方式，并当是外部认证时，
		 * 给应用绑定一个用户
		 * 增加人：黄波
		 * 时    间：2013-11-14
		 */
		application.setAuthenticateType(applicationVo.getAuthenticateType());
		application.setMatchInnerUser(applicationVo.getMatchInnerUser());
		application.setEjbAuthStateless(applicationVo.getEjbAuthStateless());
		return application;
	}

	@Override
	protected List<Application> getEntities(String[] applicationIds){
		
		List<Application> applications = new ArrayList<Application>(); 
		
		for(String applicationId: applicationIds){
			Application application = new Application();
			application.setApplicationId((applicationId));
			applications.add(application);
		}
		
		return applications;
	}

	@Override
	protected Application getEntity(String applicationId) {

		Application application = new Application();
		application.setApplicationId(applicationId);
		
		return application;
	}

	/**
	 * 表单输入校验
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
		if(DataValidater.isStrEmpty(applicationVo.getApplicationCode())){
			result = this.setInputMessage(AfwConstant.APPLICATION_CODE_KEY, 
					AfwConstant.APPLICATION_CODE_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getApplicationName())){
			result = this.setInputMessage(AfwConstant.APPLICAITON_NAME_KEY, 
					AfwConstant.APPLICATION_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getIsCheckCode())){
			result = this.setInputMessage(AfwConstant.IS_CHECK_CODE_KEY, 
					AfwConstant.IS_CHECK_CODE_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getApplicationLayout())){
			result = this.setInputMessage(AfwConstant.APPLICATION_LAYOUT_KEY,
					AfwConstant.APPLICATION_LAYOUT_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getApplicationStyle())){
			result = this.setInputMessage(AfwConstant.APPLICATION_STYLE_KEY,
					AfwConstant.APPLICATION_STYLE_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getTimeZone())){
			result = this.setInputMessage(AfwConstant.TIME_ZONE_KEY,
					AfwConstant.TIME_ZONE_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getLanguange())){
			result = this.setInputMessage(AfwConstant.LANGUANGE_KEY,
					AfwConstant.LANGUANGE_MSG);
		}
		if(DataValidater.isStrEmpty(applicationVo.getState())){
			result = this.setInputMessage(AfwConstant.STATE_KEY,
					AfwConstant.STATE_MSG);
		}
		
		if(AppConstant.AUTHENTICATE_EXTERNAL == applicationVo.getAuthenticateType()){
			if(DataValidater.isStrEmpty(applicationVo.getMatchInnerUser())){
				result = this.setInputMessage(AfwConstant.MATHINNERUSER_KEY,
						AfwConstant.MATHINNERUSER_MSG);
			}
			if(DataValidater.isStrEmpty(applicationVo.getEjbAuthStateless())){
				result = this.setInputMessage(AfwConstant.EJBAUTHSTATE_KEY,
						AfwConstant.EJBAUTHSTATE_MSG);
			}
		}
		
		return result;
	}
	
	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		
		List<Application> applications = applicationService.queryList(new Application());
		Collections.sort(applications);
		
		int size = applications.size();
		if(size > 0){
			orderNo += applications.get(size-1).getOrderNo();
		}
		
		setJsonValue(DataConverter.IntegerToString(orderNo));
		
		return JSON;
	}

	public ApplicationVo getApplicationVo() {
		return applicationVo;
	}

	public void setApplicationVo(ApplicationVo applicationVo) {
		this.applicationVo = applicationVo;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
}
