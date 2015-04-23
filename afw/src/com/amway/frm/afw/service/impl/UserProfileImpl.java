package com.amway.frm.afw.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.dao.IUserProfileDao;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleUser;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.service.ApplicationService;
import com.amway.frm.afw.service.RoleRightService;
import com.amway.frm.afw.service.RoleUserService;
import com.amway.frm.afw.service.UserProfileService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.RightBean;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.afw.vo.UserProfileVo;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.MD5EncodeUtil;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 * @author lenovo
 *
 */
public class UserProfileImpl extends BaseImpl implements UserProfileService {

	private IUserProfileDao userProfileDao;
	
	private RoleUserService roleUserService;
	
	private RoleRightService roleRightService;
	
	private ApplicationService applicationService;
	
	private BaseDataSourceService baseDataSourceService;
	
	public RoleUserService getRoleUserService() {
		return roleUserService;
	}

	public void setRoleUserService(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.services.UserProfileService#addUsersByWebService()
	 */
	@Override
	public ReturnMessage<UserProfile> leadInUsersByWebService() {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.services.UserProfileService#modifyPwd(com.amway.frm.afw.entity.UserProfile)
	 */
	@Transactional
	@Override
	public ReturnMessage<UserProfile> modifyPwd(UserProfileVo userProfileVo) {
		
		ReturnMessage<UserProfile> returnMessage = new ReturnMessage<UserProfile>();
		
		UserProfile userProfile = getSysInfo().getUserProfile();
		if(null != userProfile.getAccountType()
				&& userProfile.getAccountType().intValue() == AfwConstant.AD_ACCOUNT.intValue()){
			final String msg = "AD帐号不能修改密码";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		}
		if(!userProfileVo.getPassword().equals(userProfileVo.getPassword2())){
			final String msg = "您输入的新密码、重复密码不一致";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		}
		if (!MD5EncodeUtil.MD5Encode(
				AppConstant.MD5_KEY + userProfileVo.getOldPassword()).equals(
						userProfile.getPassword())) {
			final String msg = "您的原密码不正确";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		}
		
		
		userProfile.setPassword(MD5EncodeUtil.MD5Encode(AppConstant.MD5_KEY + userProfileVo.getPassword()));
		returnMessage = this.update(userProfile);
	
		return returnMessage;
	}

	@Transactional
	@Override
	public ReturnMessage<UserProfile> updateUserProfile(UserProfile userProfile) {
		
		UserProfile queryUserProfile = new UserProfile();
		queryUserProfile.setUserProfileId(userProfile.getUserProfileId());

		UserProfile userProfileRet = (UserProfile) querySingle(queryUserProfile);
		userProfile.setPassword(userProfileRet.getPassword());
		
		ReturnMessage<UserProfile> returnMessage = update(userProfile);
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.UserProfileService#resetPwd(com.amway.frm.afw.entity.UserProfile)
	 */
	@Transactional
	@Override
	public ReturnMessage<UserProfile> resetPwd(UserProfile userProfile) {
		
		UserProfile userProfileRet = (UserProfile) this.querySingle(userProfile);
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				AfwConstant.DEFAULT_PASSWORD, new HashMap<String, String[]>(),
				AfwConstant.SQL_AND).getReturnObject();
		String dbPasswd = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_DN);
		
		userProfileRet.setPassword(MD5EncodeUtil.MD5Encode(AppConstant.MD5_KEY
				+ dbPasswd));
		
		ReturnMessage<UserProfile> returnMessage = update(userProfileRet);
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.UserProfileService#changeUser(com.amway.frm.afw.vo.UserProfileVo)
	 */
	@Override
	public ReturnMessage<Module> changeUser(UserProfileVo userProfileVo) {
		
		ReturnMessage<Module> returnMessage = new ReturnMessage<Module>();
		
		SysInfoBean sysInfoBean = SysInfoBean.getSysInfoBean();
		if(!sysInfoBean.isChangeUser()){
			return returnMessage;
		}
		
		String userCodeOld = sysInfoBean.getUserProfile().getEmpNumber();
		String userCode = userProfileVo.getEmpNumber();
		
		if(userCodeOld.equals(userCode)){
			final String msg = "相同帐号，不能切换";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		}
		
		sysInfoBean = getSysInfoBean(userProfileVo, sysInfoBean);
		UserProfile userProfile = getUserProfile(userProfileVo, sysInfoBean);
		if(null == userProfile){
			final String msg = "账号不存在";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		}
		List<Role> roles = getRoles(userProfile, sysInfoBean.getApplication(), sysInfoBean);
		List<Application> applications = getApplications(userProfile, roles, sysInfoBean);
		List<Module> modules = getModules(userProfileVo, roles, sysInfoBean);
		RightBean rights = getRights(modules);
		
		ContextFactory.setToSession(AppConstant.USER_NAME, userProfile);
		ContextFactory.setToSession(AppConstant.ROLES_NAME, roles);
		ContextFactory.setToSession(AppConstant.APPLICATION_NAMES, applications);
		ContextFactory.setToSession(AppConstant.MODULES_NAME, modules);
		ContextFactory.setToSession(AppConstant.RIGHTS_NAME, rights);
		ContextFactory.setToSession(AppConstant.SYS_INFO, sysInfoBean);
		
		sysInfoBean.setSysInfoOld(userCode);
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		final String msg = "用户切换成功";
		returnMessage.setReturnMsg(msg);
		
		return returnMessage;
	}
	
	private SysInfoBean getSysInfoBean(UserProfileVo userProfileVo,
			SysInfoBean sysInfoBean) {
		
		SysInfoBean sysInfoBeanOld = sysInfoBean.getSysInfoOld();
		if (null != sysInfoBeanOld
				&& userProfileVo.getEmpNumber().equals(
						sysInfoBeanOld.getUserProfile().getEmpNumber())) {
			return sysInfoBean.getSysInfoOld();
		}
		
		return sysInfoBean;
	}
	
	private List<Application> getApplications(UserProfile userProfile,
			List<Role> roles, SysInfoBean sysInfoBean) {
		
		SysInfoBean sysInfoBeanOld = sysInfoBean.getSysInfoOld();
		if (null != sysInfoBeanOld
				&& userProfile.getEmpNumber().equals(
						sysInfoBeanOld.getUserProfile().getEmpNumber())) {
			return sysInfoBean.getApplicationsOld();
		}
		
		List<Application> applications = null;
		if(sysInfoBean.isSuperRole(roles)){
			applications = applicationService.getApplicationsJDBC()
					.getReturnObjects();
		}else{
			applications = applicationService.getApplicationsJDBC(userProfile)
					.getReturnObjects();
		}
		
		return applications;
	}
	
	private List<Module> getModules(UserProfileVo userProfileVo,
			List<Role> roles, SysInfoBean sysInfoBean) {
		
		SysInfoBean sysInfoBeanOld = sysInfoBean.getSysInfoOld();
		if (null != sysInfoBeanOld
				&& userProfileVo.getEmpNumber().equals(
						sysInfoBeanOld.getUserProfile().getEmpNumber())) {
			return sysInfoBean.getModulesOld();
		}
		
		ReturnMessage<Module> returnMessage = roleRightService.getMRightList(
				roles, sysInfoBean.getApplication());
		
		if(!returnMessage.isSuccess()){
			return new ArrayList<Module>();
		}
		
		return returnMessage.getReturnObjects();
	}
	
	private RightBean getRights(List<Module> modules){
		
		RightBean rightBean = new RightBean();
		for(Module module: modules){
			rightBean.put(module.getModuleCode(), module);
		}
		return rightBean;
	}
	
	private UserProfile getUserProfile(UserProfileVo userProfileVo,
			SysInfoBean sysInfoBean) {
		
		SysInfoBean sysInfoBeanOld = sysInfoBean.getSysInfoOld();
		if (null != sysInfoBeanOld
				&& userProfileVo.getEmpNumber().equals(
						sysInfoBeanOld.getUserProfile().getEmpNumber())) {
			return sysInfoBean.getUserProfileOld();
		}
	
		UserProfile userProfile = new UserProfile();
		userProfile.setEmpNumber(userProfileVo.getEmpNumber());
		
		userProfile = (UserProfile) querySingle(userProfile);
		
		return userProfile;
	}
	
	private List<Role> getRoles(UserProfile userProfile, Application application,
			SysInfoBean sysInfoBean){
		
		SysInfoBean sysInfoBeanOld = sysInfoBean.getSysInfoOld();
		if (null != sysInfoBeanOld
				&& userProfile.getEmpNumber().equals(
						sysInfoBeanOld.getUserProfile().getEmpNumber())) {
			return sysInfoBean.getRolesOld();
		}
		
		return roleUserService.getRoleList(userProfile, application)
				.getReturnObjects();
	}
	
	@Override
	@Transactional
	public ReturnMessage<UserProfile> logicDeleteList(List userProfiles) {
		
		for (Object userProfileObj : userProfiles) {
			UserProfile userProfile = (UserProfile)userProfileObj;
			RoleUser roleUser = new RoleUser();
			roleUser.setUserProfileId(userProfile.getUserProfileId());
			List<RoleUser> roleUsers = queryList(roleUser);
			roleUserService.logicDeleteList(roleUsers);
		}
		
		return super.logicDeleteList(userProfiles);
	}

	public void setUserProfileDao(IUserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}

	public IUserProfileDao getUserProfileDao() {
		return userProfileDao;
	}

	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}

	public RoleRightService getRoleRightService() {
		return roleRightService;
	}

	public ApplicationService getApplicationService() {
		return applicationService;
	}

	public BaseDataSourceService getBaseDataSourceService() {
		return baseDataSourceService;
	}

	@Override
	public UserProfile getUniqueUserProfile(
			UserProfile userProfile) throws AmwaySysException {
		
		try{
			return userProfileDao.getEntityById(userProfile.getUserProfileId());
		}catch(SQLException e){
			throw new AmwaySysException(e);
		}
		
	}

}
