package mikeheke.tadpole.frm.afw.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.ApplicationPlus;
import mikeheke.tadpole.frm.afw.service.ApplicationPlusService;
import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.afw.vo.ApplicationPlusVo;
import mikeheke.tadpole.frm.afw.vo.ApplicationVo;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;

/**
 *  
 */
public class ApplicationPlusAction extends BaseAction{
	
	private static final long serialVersionUID = -2891775900107049963L;
	
	private ApplicationPlusService applicationPlusService;
	
	private ApplicationVo applicationVo;
	
	private ApplicationPlusVo applicationPlusVo;
	
	@Override
	public String initAdd(){
		
		String result = INIT_ADD_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		String [] applicationIds = null;
		if(null != applicationVo){
			applicationIds = applicationVo.getApplicationIds();
		}
		if(!validateIds0(applicationIds)){
			this.setMessage(AfwConstant.APPLICATION_ID_KEY,
					AfwConstant.APPLICATION_ID_MSG);
			return INIT_ADD_INPUT;
		}
		
		ReturnMessage<ApplicationPlus> returnMessage = null;
		
		ApplicationPlus applicationPlus = this.getEntityByApplicationId(applicationIds[0]);
		
		returnMessage = new ReturnMessage<ApplicationPlus>();
		returnMessage.setReturnObject(applicationPlus);
		
		result = setReturnMessage(returnMessage, INIT_ADD_SUCCESS, INIT_ADD_INPUT);
		
		return result;
		
	}
	
	@Override
	public String initModify() {
		
		String result = INIT_MODIFY_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String[] applicationPlusIds = null;
		if(null != applicationPlusVo){
			applicationPlusIds = applicationPlusVo.getApplicationPlusIds();
		}
		if(!validateIds0(applicationPlusIds)){
			return INIT_MODIFY_INPUT;
		}
		
		String applicationPlusId = (applicationPlusVo.getApplicationPlusIds()[0]);
		ApplicationPlus applicationPlus = getEntityByApplicationPlusId(applicationPlusId);
		ReturnMessage<ApplicationPlus> returnMessage = null;
		returnMessage = applicationPlusService.query(applicationPlus);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}

	@Override
	public String add() {

		String result = ADD_SUCCESS;

		ApplicationPlus applicationPlus = this.getEntity();
		ReturnMessage<ApplicationPlus> returnMessage = new ReturnMessage<ApplicationPlus>();

		if(!validateData()){
			returnMessage.setReturnObject(applicationPlus);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		returnMessage = applicationPlusService.addCom(applicationPlus);
		if(returnMessage.isSuccess()){
			returnMessage.repReturnObject(this.getEntityByApplicationId(applicationVo.getApplicationId()));
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		return result;
	}

	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		String applicationPlusId = applicationPlusVo.getApplicationPlusId();
		
		ApplicationPlus applicationPlus = this.getEntity();
		ReturnMessage<ApplicationPlus> returnMessage = new ReturnMessage<ApplicationPlus>();
		
		if(!validateId(applicationPlusId)){
			returnMessage.setReturnObject(applicationPlus);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		returnMessage = applicationPlusService.update(applicationPlus);

		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] applicationPlusIds = null;
		if(null != applicationPlusVo){
			applicationPlusIds = applicationPlusVo.getApplicationPlusIds();
		}
		if(validateIds(applicationPlusIds)){

			List<ApplicationPlus> applicationPluss = this.getEntities(applicationPlusIds);
			ReturnMessage<ApplicationPlus> returnMessage = applicationPlusService.logicDeleteList(applicationPluss);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	@Override
	protected ApplicationPlus getEntity() {
		
		ApplicationPlus applicationPlus = new ApplicationPlus();
		applicationPlus.setApplicationPlusId((applicationPlusVo.getApplicationPlusId()));
		Application application = new Application();
		application.setApplicationId((applicationVo.getApplicationId()));
		applicationPlus.setApplication(application);
		applicationPlus.setParameterCode(applicationPlusVo.getParameterCode());
		applicationPlus.setParameterName(applicationPlusVo.getParameterName());
		applicationPlus.setParameterValue(applicationPlusVo.getParameterValue());
		applicationPlus.setState(AppConstant.START); //设置为启用状态
		applicationPlus.setRemark(applicationPlusVo.getRemark());
		applicationPlus.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		applicationPlus.setCreatedTime(new Date());
		applicationPlus.setUpdatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		applicationPlus.setUpdatedTime(new Date());

		return applicationPlus;
	}

	@Override
	protected List<ApplicationPlus> getEntities(String[] applicationPlusIds){
		
		List<ApplicationPlus> applicationPluss = new ArrayList<ApplicationPlus>(); 
		
		for(String applicationPlusId: applicationPlusIds){
			ApplicationPlus applicationPlus = new ApplicationPlus();
			applicationPlus.setApplicationPlusId((applicationPlusId));
			applicationPluss.add(applicationPlus);
		}
		
		return applicationPluss;
	}
	
	//@Override
	protected ApplicationPlus getEntityByApplicationId(String applicationId) {
		
		ApplicationPlus applicationPlus = new ApplicationPlus();
		Application application = new Application();
		application.setApplicationId((applicationId));
		application = (Application) applicationPlusService.querySingle(application);
		applicationPlus.setApplication(application);
		
		return applicationPlus;
	}
	
	protected ApplicationPlus getEntityByApplicationPlusId(String applicationPlusId) {
		
		ApplicationPlus applicationPlus = new ApplicationPlus();
		applicationPlus.setApplicationPlusId(applicationPlusId);
		
		return applicationPlus;
	}

	/**
	 * 表单输入校验
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
	
		if(DataValidater.isStrEmpty(applicationVo.getApplicationId())){
			result = this.setInputMessage(AfwConstant.APPLICATION_PLUS_NAME_KEY,
					AfwConstant.APPLICATION_PLUS_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(applicationPlusVo.getParameterCode())){
			result = this.setInputMessage(AfwConstant.PARAMETER_CODE_KEY, 
					AfwConstant.PARAMETER_CODE_MSG);
		}
		if(DataValidater.isStrEmpty(applicationPlusVo.getParameterName())){
			result = this.setInputMessage(AfwConstant.PARAMETER_NAME_KEY, 
					AfwConstant.PARAMETER_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(applicationPlusVo.getParameterValue())){
			result = this.setInputMessage(AfwConstant.PARAMETER_VALUE_KEY,
					AfwConstant.PARAMETER_VALUE_MSG);
		}
		
		return result;
	}
	
	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
	public ApplicationPlusVo getApplicationPlusVo() {
		return applicationPlusVo;
	}

	public void setApplicationPlusVo(ApplicationPlusVo applicationPlusVo) {
		this.applicationPlusVo = applicationPlusVo;
	}

	public ApplicationVo getApplicationVo() {
		return applicationVo;
	}

	public void setApplicationVo(ApplicationVo applicationVo) {
		this.applicationVo = applicationVo;
	}

	public void setApplicationPlusService(ApplicationPlusService applicationPlusService) {
		this.applicationPlusService = applicationPlusService;
	}
}
