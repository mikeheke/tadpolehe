package com.amway.frm.afw.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.BlackList;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.service.BlackListService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.BlackListVo;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;

/**
 *  应用系统黑名单信息Action
 *  @author huangweijin
 */
public class BlackListAction extends BaseAction{
	
	private static final long serialVersionUID = -2891775900107049963L;
	
	private BlackListService blackListService;
	
	private BlackListVo blackListVo;
	
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
		
		String[] blackListIds = null;
		if(null != blackListVo){
			blackListIds = blackListVo.getBlackListIds();
		}
		if(!validateIds0(blackListIds)){
			return INIT_MODIFY_INPUT;
		}
		
		BlackList blackList = this.getEntity(blackListIds[0]);
		
		ReturnMessage<BlackList> returnMessage = blackListService.query(blackList);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	

	@Override
	public String add() {

		String result = ADD_SUCCESS;
		
		BlackList blackList = this.getEntity();
		ReturnMessage<BlackList> returnMessage = new ReturnMessage<BlackList>();
		
		if(!validateData()){
			returnMessage.setReturnObject(blackList);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage =blackListService.addCom(blackList);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		return result;
	}
	

	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		
		BlackList blackList = this.getEntity();
		ReturnMessage<BlackList> returnMessage = new ReturnMessage<BlackList>();

		if(!validateData()){
			returnMessage.setReturnObject(blackList);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		String blackListId = blackListVo.getBlackListId();
		if(!validateId(blackListId)){
			returnMessage.setReturnObject(blackList);
			setReturnMessage(returnMessage);
			return MDF_SUCCESS;
		}	
		
		returnMessage = blackListService.update(blackList);

		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] blackListIds = null;
		if(null != blackListVo){
			blackListIds = blackListVo.getBlackListIds();
		}
		if(validateIds(blackListIds)){

			List<BlackList> blackLists = this.getEntities(blackListIds);
			ReturnMessage<BlackList> returnMessage = blackListService.logicDeleteList(blackLists);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	@Override
	protected BlackList getEntity() {
		
		BlackList blackList = new BlackList();
		blackList.setBlackListId(DataConverter.stringToLong(blackListVo.getBlackListId()));
		Application application = new Application();
		application.setApplicationId(DataConverter.stringToLong(blackListVo.getApplicationId()));
		blackList.setApplication(application);
		UserProfile userProfile = new UserProfile();
		userProfile.setUserProfileId(DataConverter.stringToLong(blackListVo.getUserProfileId()[1]));
		blackList.setState(AppConstant.START); //设置为启用状态
		blackList.setBlackListTime(new Date()); //黑名单设置时间
		blackList.setUserProfile(userProfile);
		blackList.setRemark(blackListVo.getRemark());
		blackList.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		blackList.setCreatedTime(new Date()); 
		blackList.setUpdatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		blackList.setUpdatedTime(new Date());

		return blackList;
	}

	@Override
	protected List<BlackList> getEntities(String[] blackListIds){
		
		List<BlackList> blackLists = new ArrayList<BlackList>(); 
		
		for(String blackListId: blackListIds){
			BlackList blackList = new BlackList();
			blackList.setBlackListId(DataConverter.stringToLong(blackListId));
			blackLists.add(blackList);
		}
		
		return blackLists;
	}

	@Override
	protected BlackList getEntity(String blackListId) {

		BlackList blackList = new BlackList();
		blackList.setBlackListId(DataConverter.stringToLong(blackListId));
		
		return blackList;
	}

	/**
	 * 表单输入校验
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
		if(DataValidater.isStrEmpty(blackListVo.getApplicationId())){
			result = this.setInputMessage(AfwConstant.APPLICATION_ID_KEY, AfwConstant.APPLICATION_ID_MSG);
		}
		if(DataValidater.isStrEmpty(blackListVo.getUserProfileId()[1])){
			result = this.setInputMessage(AfwConstant.USER_PROFILE_ID_KEY, AfwConstant.USER_PROFILE_ID_MSG);
		}
		
		return result;
	}

	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}
	
	public BlackListVo getBlackListVo() {
		return blackListVo;
	}

	public void setBlackListVo(BlackListVo blackListVo) {
		this.blackListVo = blackListVo;
	}

	public void setBlackListService(BlackListService blackListService) {
		this.blackListService = blackListService;
	}

	
}
