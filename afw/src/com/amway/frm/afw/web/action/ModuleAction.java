package com.amway.frm.afw.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.service.ModuleService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.ModuleVo;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * 模块信息Action
 * @author huangweijin
 */

public class ModuleAction extends BaseAction{
	
	private static LogService logger = LogFactory.getLogger(ModuleAction.class);

	private static final long serialVersionUID = -6918529476870299418L;
	
	//模块信息业务Service
	private ModuleService moduleService;
	
	//表单封装Vo类
	private ModuleVo moduleVo;
	
	@Override
	public String initAdd(){

		String result = INIT_ADD_SUCCESS;
		
		this.setOprt(AppConstant.ADD_OPRT);
		
		ReturnMessage<Module> returnMessage = moduleService.initAddLevel(moduleVo);
		result = setReturnMessage(returnMessage, INIT_ADD_SUCCESS, INIT_ADD_INPUT);
		
		return result;
	}
	
	@Override
	public String initModify() {

		String result = INIT_MODIFY_SUCCESS;

		this.setOprt(AppConstant.MDF_OPRT);
		
		String [] moduleIds = null;
		if(null != moduleVo){
			moduleIds = moduleVo.getModuleIds();
		}
		if(!validateIds0(moduleIds)){
			return INIT_MODIFY_INPUT;
		}
		
		Module module = getEntity(moduleIds[0]);
		ReturnMessage<Module> returnMessage = moduleService.query(module);
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);

		return result;
	}
	

	@Override
	public String add() {

		String result = ADD_SUCCESS;
		
		Module module = this.getEntity();
		ReturnMessage<Module> returnMessage = new ReturnMessage<Module>();
		
		if(!validateData()){
			returnMessage.setReturnObject(module);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage = moduleService.addModule(module);
		if(returnMessage.isSuccess()){
			Module module2 = new Module();
			module = returnMessage.getReturnObject();
			module2.setParentModule(module.getParentModule());
			module2.setApplication(module.getApplication());
			returnMessage.clearReturnObjects();
			returnMessage.setReturnObject(module2);
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		final String msg = "新增功能 ";
		logger.writeOperLog(AppConstant.ADD_OPRT, msg + module.getModuleCode());
		
		return result;
	}

	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		
		Module module = this.getEntity();
		ReturnMessage<Module> returnMessage = new ReturnMessage<Module>();

		if(!validateData()){
			returnMessage.setReturnObject(module);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		String moduleId = moduleVo.getModuleId();
		if(!validateId(moduleId)){
			returnMessage.setReturnObject(module);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		returnMessage = moduleService.updateModule(module);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		final String msg = "修改功能 ";
		logger.writeOperLog(AppConstant.MDF_OPRT, msg + module.getModuleCode());

		return result;
	}
	
	@Override
	public String delete(){
		
		String result = DEL_INPUT;
		
		String[] moduleIds = null;
		if(null != moduleVo){
			moduleIds = moduleVo.getModuleIds();
		}
		if(validateIds(moduleIds)){

			List<Module> modules = this.getEntities(moduleIds);
			ReturnMessage<Module> returnMessage = moduleService.deleteModuleList(modules);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
			
			final String msg = "删除功能";
			logger.writeOperLog(AppConstant.DEL_OPRT, msg);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		
		List<Module> modules = moduleService.queryList(new Module());
		Collections.sort(modules);
		
		int size = modules.size();
		if(size > 0){
			orderNo += DataConverter.stringToInteger(modules.get(size-1).getOrderNo());
		}
		
		setJsonValue(DataConverter.IntegerToString(orderNo));
		
		return JSON;
	}
	
	@Override
	protected Module getEntity() {
		
		Module module = new Module();
		module.setModuleId(DataConverter.stringToLong(moduleVo.getModuleId()));
		String moduleCode = moduleVo.getModuleCode();
		module.setModuleCode(moduleCode);
		module.setModuleName(moduleVo.getModuleName());
		module.setIsModuleOrButton(Integer.parseInt(moduleVo.getIsModuleOrButton()));
		Application application = new Application();
		application.setApplicationId(DataConverter.stringToLong(moduleVo.getApplicationId()));
		module.setApplication(application);
		module.setIco(moduleVo.getIco());
		module.setLink(moduleVo.getLink());
		module.setOrderNo(moduleVo.getOrderNo());
		module.setIsSeed(DataConverter.stringToInteger(moduleVo.getIsSeed()));
		//module.setOpenType(DataConverter.stringToInteger(moduleVo.getOpenType()));
		module.setTarget(moduleVo.getOpenType());
		module.setRemark(moduleVo.getRemark());
		module.setState(DataConverter.stringToInteger(moduleVo.getState()));
		module.setRemark(moduleVo.getRemark());
		module.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		module.setCreatedTime(new Date());
		module.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		module.setUpdatedTime(new Date());
		Module parentModule = null;
		String parentModuleCode = moduleVo.getParentModuleCode();
		if(DataValidater.isStrEmpty(parentModuleCode)
				|| moduleCode.equals(parentModuleCode)){
			parentModule = module;
		}else{
			parentModule = (Module) moduleService.querySingle(new Module(moduleVo.getParentModuleCode()));
		}
		module.setParentModule(parentModule);
		
		return module;
	}
	

	@Override
	protected List<Module> getEntities(String[] moduleIds){
		
		List<Module> modules = new ArrayList<Module>(); 
		
		for(String moduleId: moduleIds){
			Module module = new Module();
			module.setModuleId(DataConverter.stringToLong(moduleId));
			modules.add(module);
		}
		return modules;
	}

	/**
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
		if(DataValidater.isStrEmpty(moduleVo.getModuleCode())){
			result = this.setInputMessage(AfwConstant.MODULE_CODE_KEY, AfwConstant.MODULE_CODE_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getModuleName())){
			result = this.setInputMessage(AfwConstant.MODULE_NAME_KEY, AfwConstant.MODULE_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getParentModuleCode())){
			result = this.setInputMessage(AfwConstant.PARENT_MODULE_CODE_KEY, AfwConstant.PARENT_MODULE_CODE_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getIsModuleOrButton())){
			result = this.setInputMessage(AfwConstant.IS_MODULE_OR_BUTTON_KEY, AfwConstant.IS_MODULE_OR_BUTTON_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getApplicationId())){
			result = this.setInputMessage(AfwConstant.APPLICATION_ID_KEY, AfwConstant.APPLICATION_ID_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getOrderNo())){
			result = this.setInputMessage(AfwConstant.ORDER_NO_KEY, AfwConstant.ORDER_NO_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getIsSeed())){
			result = this.setInputMessage(AfwConstant.IS_SEED_KEY, AfwConstant.IS_SEED_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getOpenType())){
			result = this.setInputMessage(AfwConstant.OPEN_TYPE_KEY, AfwConstant.OPEN_TYPE_MSG);
		}
		if(DataValidater.isStrEmpty(moduleVo.getState())){
			result = this.setInputMessage(AfwConstant.STATE_KEY, AfwConstant.STATE_MSG);
		}
		
		return result;
	}
	
	@Override
	protected Module getEntity(String moduleId) {
		
		Module module = new Module();
		module.setModuleId(DataConverter.stringToLong(moduleId));
		return module;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public ModuleVo getModuleVo() {
		return moduleVo;
	}

	public void setModuleVo(ModuleVo moduleVo) {
		this.moduleVo = moduleVo;
	}

	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}
}
