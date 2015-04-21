package com.amway.frm.afw.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.amway.frm.afw.entity.Department;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.service.UserProfileService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.UserProfileVo;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.util.MD5EncodeUtil;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * Created by MyElipse 
 * 
 * Declare：用户Action
 */
public class UserProfileAction extends BaseAction {
	
	private static LogService logger = LogFactory.getLogger(UserProfileAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737927642795229273L;

	// 业务Service
	private UserProfileService userProfileService;

	private UserProfileVo userProfileVo;
	
	private BaseDataSourceService baseDataSourceService;
	
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
		
		String [] userProfileIds = null;
		if(null != userProfileVo){
			userProfileIds = userProfileVo.getUserProfileIds();
		}
		if(!validateIds0(userProfileIds)){
			return INIT_MODIFY_INPUT;
		}
		
		UserProfile userProfile = this.getEntity(userProfileIds[0]);
		ReturnMessage<UserProfile> returnMessage = userProfileService.query(userProfile);
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	

	@Override
	public String add() {

		String result = ADD_SUCCESS;
		
		UserProfile userProfile = this.getEntity();
		ReturnMessage<UserProfile> returnMessage = new ReturnMessage<UserProfile>();
		
		if(!validateData()){
			returnMessage.setReturnObject(userProfile);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				AfwConstant.DEFAULT_PASSWORD, new HashMap<String, String[]>(), AfwConstant.SQL_AND).getReturnObject();
		String dbPasswd = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_DN);
		
		userProfile.setPassword(MD5EncodeUtil.MD5Encode(AppConstant.MD5_KEY
				+ dbPasswd));
		
		returnMessage = userProfileService.addCom(userProfile);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		final String msg = "新增用户";
		logger.writeOperLog(AppConstant.ADD_OPRT, msg + userProfile.getEmpNumber());
		
		return result;
	}
	

	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;

		UserProfile userProfile = this.getEntity();
		ReturnMessage<UserProfile> returnMessage = new ReturnMessage<UserProfile>();
		
		if(!validateData()){
			returnMessage.setReturnObject(userProfile);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String userProfileId = userProfileVo.getUserProfileId();
		if(!validateId(userProfileId)){
			returnMessage.setReturnObject(userProfile);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = this.userProfileService.updateUserProfile(userProfile);
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		final String msg = "修改用户";
		logger.writeOperLog(AfwConstant.MDF_OPRT, msg  + userProfile.getEmpNumber());
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] userProfileIds = null;
		if(null != userProfileVo){
			userProfileIds = userProfileVo.getUserProfileIds();
		}
		if(validateIds(userProfileIds)){

			List<UserProfile> userProfiles = this.getEntities(userProfileIds);
			ReturnMessage<UserProfile> returnMessage = userProfileService.logicDeleteList(userProfiles);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	public String resetPwd(){
		
		final String PWD_RESET_INPUT = "pwdResetInput";
		final String PWD_RESET_SUCCESS = "pwdResetSuccess";
		String result = PWD_RESET_INPUT;
		
		String[] userProfileIds = null;
		if(null != userProfileVo){
			userProfileIds = userProfileVo.getUserProfileIds();
		}
		if(validateIds0(userProfileIds)){

			UserProfile userProfile = this.getEntity(userProfileIds[0]);
			ReturnMessage<UserProfile> returnMessage = userProfileService.resetPwd(userProfile);
			result = setReturnMessage(returnMessage, PWD_RESET_SUCCESS, PWD_RESET_INPUT);
			
			final String key = "resetPwd";
			final String msg = "重置密码";
			logger.writeOperLog(key, userProfile.getEmpNumber() + msg);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	/**
	 * 导入用户
	 * @return
	 */
	public String leadIn() {
		
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modifyPwd() {
		
		final String PWD_MDF_SUCCESS = "pwdMdfSuccess";
		final String PWD_MDF_INPUT = "pwdMdfInput";
		
		ReturnMessage<UserProfile> returnMessage = null;
		returnMessage = userProfileService.modifyPwd(userProfileVo);
		String result = setReturnMessage(returnMessage, PWD_MDF_SUCCESS, PWD_MDF_INPUT);
		
		final String msg = "修改密码";
		logger.writeOperLog(AfwConstant.MDF_OPRT, userProfileVo.getEmpNumber() + msg);
		
		return result;
	}
	
	public String popup2() {
		
		final String POPUP2_SUCCESS = "popup2Success";
		return POPUP2_SUCCESS;
	}

	/**
	 * Declare：切换用户
	 * 
	 * @param void
	 * @return String
	 */
	public String change() {

		final String USER_CHANGE_SUCCESS = "userChangeSuccess";
		final String USER_CHANGE_INPUT = "userChangeInput";
		
		ReturnMessage<Module> returnMessage = null;
		returnMessage = userProfileService.changeUser(userProfileVo);
		
		String result = setReturnMessage(returnMessage, USER_CHANGE_SUCCESS, USER_CHANGE_INPUT);
		
		return result;
	}
	
	public String popup3() {
		
		final String POPUP3_SUCCESS = "popup3Success";
		
		return POPUP3_SUCCESS;
	}
	
	/**
	 * Declare：切换皮肤
	 * 
	 * @param void
	 * @return String
	 */
	public String change2() {

		final String STYLE_CSS = "styleCss";
		String cssValueNew = getParam(STYLE_CSS);
		ContextFactory.setToSession(AppConstant.CSS_NAME, cssValueNew);
		
		final String STYLE_CHANGE_SUCCESS = "styleChangeSuccess";
		return STYLE_CHANGE_SUCCESS;
	}
	
	public String popup4() {
		
		final String POPUP4_SUCCESS = "popup4Success";
		
		return POPUP4_SUCCESS;
	}
	
	/**
	 * Declare：切换语言
	 * 
	 * @param void
	 * @return String
	 */
	public String change3() {

		final String msg = "切换语言成功";
		this.setMessage(RET_INFO, msg);
		
		final String LANG_CHANGE_SUCCESS = "langChangeSuccess";
		return LANG_CHANGE_SUCCESS;
	}
	
	@Override
	protected UserProfile getEntity() {
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUserProfileId((userProfileVo.getUserProfileId()));
		userProfile.setEmpId(userProfileVo.getEmpId());
		userProfile.setChineseName(userProfileVo.getChineseName());
		userProfile.setAccountType(Integer.parseInt(userProfileVo.getAccountType()));
		Department department = new Department();
		final long _1L = 1L;
		//department.setDepartmentId(_1L);
		department.setDepartmentId("_1L");
		if(userProfileVo.getOrgUnitCode()==null){
			department.setUnitCode(AfwConstant._1_STR);
		}else{
			department.setUnitCode(userProfileVo.getOrgUnitCode()[1]);
		}
		userProfile.setDepartment(department);//设置部门
		userProfile.setEmpNumber(userProfileVo.getEmpNumber());
		userProfile.setDateHired(DataConverter.fmtStrToDay(userProfileVo.getDateHired()));
		userProfile.setDateTerminated(DataConverter.fmtStrToDay(userProfileVo.getDateTerminated()));
		userProfile.setGender(userProfileVo.getGender());
		userProfile.setEnglishName(userProfileVo.getEnglishName());
		userProfile.setDateBirthday(DataConverter.fmtStrToDay(userProfileVo.getDateBirthday()));
		userProfile.setProvincialAddr(userProfileVo.getProvincialAddr());
		userProfile.setNativePlace(userProfileVo.getNativePlace());
		userProfile.setWorkCity(userProfileVo.getWorkCity());
		userProfile.setGradeCode(userProfileVo.getGradeCode());
		userProfile.setChinesePosition(userProfileVo.getChinesePosition());
		userProfile.setEnglishPosition(userProfileVo.getEnglishPosition());
		userProfile.setWorkProvince(userProfileVo.getWorkProvince());
		userProfile.setEmailAddr(userProfileVo.getEmailAddr());
		userProfile.setJobGrade(userProfileVo.getJobGrade());
		userProfile.setPhone(userProfileVo.getPhone());
		userProfile.setReportLine(userProfileVo.getReportLine());
		userProfile.setLocation(userProfileVo.getLocation());
		userProfile.setState(Integer.parseInt(userProfileVo.getState()));
		userProfile.setRemark(userProfileVo.getRemark());
		userProfile.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		userProfile.setCreatedTime(new Date());
		userProfile.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		userProfile.setUpdatedTime(new Date());
		
		return userProfile;
	}
	
	
	@Override
	protected UserProfile getEntity(String userProfileId) {
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUserProfileId((userProfileId));
		
		return userProfile;
	}

	@Override
	protected List<UserProfile> getEntities(String[] userProfileIds){
		
		List<UserProfile> userProfiles = new ArrayList<UserProfile>(); 
		
		for(String userProfileId: userProfileIds){
			UserProfile userProfile = new UserProfile();
			userProfile.setUserProfileId((userProfileId));
			userProfiles.add(userProfile);
		}
		
		return userProfiles;
	}

	/**
	 * 表单输入校验
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
		
		if(DataValidater.isStrEmpty(userProfileVo.getEmpNumber())){
			result = this.setInputMessage(AfwConstant.EMP_NUMBER_KEY, AfwConstant.EMP_NUMBER_MSG);
		}
		if(DataValidater.isStrEmpty(userProfileVo.getChineseName())){
			result = this.setInputMessage(AfwConstant.CHINESE_NAME_KEY, AfwConstant.CHINESE_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(userProfileVo.getState())){
			result = this.setInputMessage(AfwConstant.STATE_KEY, AfwConstant.STATE_MSG);
		}
		if(DataValidater.isStrEmpty(userProfileVo.getAccountType())){
			result = this.setInputMessage(AfwConstant.ACCOUNT_TYPE_KEY, AfwConstant.ACCOUNT_TYPE_MSG);
		}
		if(DataConverter.IntegerToString(AfwConstant.COMMON_ACCOUNT).equals(userProfileVo.getAccountType())){
			
			if(DataValidater.isStrEmpty(userProfileVo.getOrgUnitCode()[1])){
				result = this.setInputMessage(AfwConstant.ORG_UNIT_CODE_KEY, AfwConstant.ORG_UNIT_CODE_MSG);
			}
			if(DataValidater.isStrEmpty(userProfileVo.getEnglishName())){
				result = this.setInputMessage(AfwConstant.ENGLISH_NAME_KEY, AfwConstant.ENGLISH_NAME_MSG);
			}
			if(DataValidater.isFalse(DataValidater.isStrInteger(userProfileVo.getState()))){
				result = this.setInputMessage(AfwConstant.STATE_KEY, AfwConstant.STATE_MSG2);
			}
			if(userProfileVo.getDateHired() != null
					&& DataValidater.isFalse(DataValidater.isDay(userProfileVo.getDateHired()))){
				result = this.setInputMessage(AfwConstant.DATE_HIRED_KEY, AfwConstant.DATE_HIRED_MSG);
			}
			if(userProfileVo.getDateTerminated() != null
					&& DataValidater.isFalse(DataValidater.isDay(userProfileVo.getDateTerminated()))){
				result = this.setInputMessage(AfwConstant.DATE_TERMINATED_KEY, AfwConstant.DATE_TERMINATED_MSG);
			}
			if(userProfileVo.getDateBirthday() != null
					&& DataValidater.isFalse(DataValidater.isDay(userProfileVo.getDateBirthday()))){
				result = this.setInputMessage(AfwConstant.DATE_BIRTH_DAY_KEY, AfwConstant.DATE_BIRTH_DAY_MSG);
			}
		}
		
		return result;
	}	

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public UserProfileVo getUserProfileVo() {
		return userProfileVo;
	}

	public void setUserProfileVo(UserProfileVo userProfileVo) {
		this.userProfileVo = userProfileVo;
	}
	
	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}
}
