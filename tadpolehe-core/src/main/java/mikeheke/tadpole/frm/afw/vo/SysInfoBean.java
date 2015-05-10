/**
 * 
 */
package mikeheke.tadpole.frm.afw.vo;

import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;

/**
 * 
 * 
 *         2011-5-9 下午02:22:30
 */
public class SysInfoBean {
	
	private SysInfoBean sysInfoOld;
	
	private UserProfile userProfileOld;
	
	private List<Role> rolesOld;
	
	private List<Application> applicationsOld;
	
	private List<Module> modulesOld;
	
	private SysInfoBean(){}
	
	public void setSysInfoBean(SysInfoBean sysInfoBean){
		ContextFactory.setToSession(AppConstant.SYS_INFO, sysInfoBean);
	}
	
	public static SysInfoBean getSysInfoBean(){
		
		SysInfoBean sysInfoBean = (SysInfoBean) ContextFactory
				.getFromSession(AppConstant.SYS_INFO);
		if(null == sysInfoBean){
			sysInfoBean = new SysInfoBean();
		}
		
		return sysInfoBean;
	}
	
	public UserProfile getUserProfile() {
		
		return (UserProfile) ContextFactory
				.getFromSession(AppConstant.USER_NAME);
	}

	public void setUserProfile(UserProfile userProfile) {
		ContextFactory.setToSession(AppConstant.USER_NAME, userProfile);
	}
	
	public void setCurModule(Module module){
		ContextFactory.setToRequest(AppConstant.MODULE_NAME, module);
	}
	
	public Module getCurModule(){

		return (Module) ContextFactory.getFromRequest(AppConstant.MODULE_NAME);
	}

	public Application getApplication() {
		
		return (Application) ContextFactory
				.getFromSession(AppConstant.APPLICATION_NAME);
	}

	public void setApplication(Application application) {
		ContextFactory.setToSession(AppConstant.APPLICATION_NAME, application);
	}
	
	public Application getSysApplication(){
		
		return (Application) ContextFactory
		.getFromSession(AppConstant.SYS_APPLICATION_NAME);
	}

	public List<Role> getRoles() {
		return (List<Role>) ContextFactory
				.getFromSession(AppConstant.ROLES_NAME);
	}

	public void setRoles(List<Role> roles) {
		ContextFactory.setToSession(AppConstant.ROLES_NAME, roles);
	}

	public List<Module> getModules() {
		
		return (List<Module>) ContextFactory
				.getFromSession(AppConstant.MODULES_NAME);
		
	}

	public void setModules(List<Module> modules) {
		ContextFactory.setToSession(AppConstant.MODULES_NAME, modules);
	}
	
	public boolean getIsCacheClearUser(){
		
		return isCacheClearUser();
	}
	
	public boolean isCacheClearUser(){
		
		return isCacheClearRole();
	}
	
	public boolean isCacheClearRole(){
		
		List<Role> roles = SysInfoBean.getSysInfoBean().getRoles();
		
		return isCacheClearRole(roles);
	}
	
	public boolean isCacheClearRole(List<Role> roles){
		
		for(Role role: roles){
			if(isCacheClearRole(role)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isCacheClearRole(Role role){
		boolean result = false;
		if(AfwConstant.CLEAR_ROLE_CODE.equals(role.getRoleCode())){
			result = true;
		}
		
		return result;
	}
	
	public boolean getIsChangeUser(){
		return isChangeUser();
	}
	
	public boolean isChangeUser(){
		
		if(null != sysInfoOld){
			return true;
		}
		
		if(isChangeRole()){
			return true;
		}
		
		return false;
	}
	
	public boolean isChangeRole(){
		
		List<Role> roles = SysInfoBean.getSysInfoBean().getRoles();
		
		return isChangeRole(roles);
	}
	
	public boolean isChangeRole(List<Role> roles){
		
		for(Role role: roles){
			if(isChangeRole(role)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isChangeRole(Role role){
		boolean result = false;
		if(AfwConstant.CHANGE_ROLE_CODE.equals(role.getRoleCode())){
			result = true;
		}
		
		return result;
	}
	
	public boolean isSuperRole(List<Role> roles){
		
		return isSuperRole(roles, getApplication());
	}

	public boolean isSuperRole(List<Role> roles, Application application){
		
		if(null == roles){
			return false;
		}
		for(Role role: roles){
			if(isSuperRole(role, application)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isSuperRole(){
		
		List<Role> roles = this.getRoles();
		Application application = this.getApplication();
		
		return isSuperRole(roles, application);
	}
	
	public boolean isSuperRole(Role role){
		
		return isSuperRole(role, getApplication());
	}
	
	public boolean isSuperRole(Role role, Application application){
		
		boolean result = false;
		if(null == role.getApplication().getApplicationId()
				|| null == application.getApplicationId()){
			result = false;
		}else if(AfwConstant.SUPER_ROLE_CODE.equals(role.getRoleCode())
				&& role.getApplication().getApplicationId().equals(application.getApplicationId())){
			result = true;
		}
		
		return result;
	}
	
	public boolean isAppAdmin() {

		List<Role> roles = this.getRoles();
		if (roles == null) {
			return false;
		}

		Application app = this.getApplication();
		String code;
		for (Role role : roles) {
			code = role.getRoleCode().toLowerCase();
			if (code.indexOf(AfwConstant.APP_ROLE_CODE) > -1
					&& role.getApplication().getApplicationId().equals(app
							.getApplicationId())) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 判断用户是否应用开发员
	 * 
	 * @return boolean 
	 */
	public boolean isAppDeveloperModules() {
		List<Role> roles = this.getRoles();
		if (roles == null) {
			return false;
		}
		

		String code;
		for (Role role : roles) {
			code = role.getRoleCode().toLowerCase();
			if (code.indexOf("developer") > -1) {
				return true;
			}
		}
		return false;
	}
	
	public void setSysInfoOld(String userCode) {
		
		if(null == sysInfoOld){
			sysInfoOld = this;
			modulesOld = this.getModules();
			userProfileOld = this.getUserProfileOld();
			rolesOld = this.getRolesOld();
			applicationsOld = this.getApplicationsOld();
			return;
		}
		if(userCode.equals(sysInfoOld.getUserProfile().getEmpNumber())){
			setSysInfoOld2(null);
			setModulesOld(null);
			setUserProfileOld(null);
			setRolesOld(null);
			setApplicationsOld(null);
		}
	}

	public SysInfoBean getSysInfoOld() {
		return sysInfoOld;
	}

	public List<Module> getModulesOld() {
		return modulesOld;
	}

	public UserProfile getUserProfileOld() {
		return userProfileOld;
	}

	public List<Role> getRolesOld() {
		return rolesOld;
	}

	public List<Application> getApplicationsOld() {
		return applicationsOld;
	}

	public void setSysInfoOld2(SysInfoBean sysInfoOld) {
		this.sysInfoOld = sysInfoOld;
	}

	public void setUserProfileOld(UserProfile userProfileOld) {
		this.userProfileOld = userProfileOld;
	}

	public void setRolesOld(List<Role> rolesOld) {
		this.rolesOld = rolesOld;
	}

	public void setApplicationsOld(List<Application> applicationsOld) {
		this.applicationsOld = applicationsOld;
	}

	public void setModulesOld(List<Module> modulesOld) {
		this.modulesOld = modulesOld;
	}

}
