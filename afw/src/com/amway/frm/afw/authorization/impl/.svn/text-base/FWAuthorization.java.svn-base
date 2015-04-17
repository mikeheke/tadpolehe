package com.amway.frm.afw.authorization.impl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.amway.frm.afw.authentication.principal.Credentials;
import com.amway.frm.afw.authorization.IFWAuthorization;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.service.ApplicationService;
import com.amway.frm.afw.service.RoleRightService;
import com.amway.frm.afw.service.RoleUserService;
import com.amway.frm.afw.vo.RightBean;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * 
 * @author lenovo
 *
 */
public class FWAuthorization implements IFWAuthorization {

	private RoleRightService roleRightService;
	
	private RoleUserService roleUserService;
	
	private ApplicationService applicationService;
	
	/**
	 * 授权
	 * @param request request
	 * @param response response
	 * @param credentials 验证
	 * @return ReturnMessage<SysInfoBean>
	 */
	@Override
	public ReturnMessage<SysInfoBean> authorizate(HttpServletRequest request,
			HttpServletResponse response, Credentials credentials) {
		
		ReturnMessage<SysInfoBean> returnMessage = null;
		
		//系统SysInfoBean授权
		returnMessage = authorizateSysInfo(request, response, credentials);
		if(!returnMessage.isSuccess()){
			return returnMessage;
		}
		
		//本地Cookie授权
		authorizateLocalCookie(request, response, credentials);
		
		return returnMessage;
	}
	
	/**
	 * 本地cookie授权
	 * @param request request
	 * @param response response
	 * @param credentials 验证
	 * @return ReturnMessage<SysInfoBean>
	 */
	@Override
	public ReturnMessage<SysInfoBean> authorizateLocalCookie(HttpServletRequest request,
			HttpServletResponse response, Credentials credentials) {

		ReturnMessage<SysInfoBean> returnMessage = new ReturnMessage<SysInfoBean>();
		
		String token = credentials.getUserCode();
		Application application = getApplication(credentials.getApplicationCode());
		Cookie cookie = new Cookie(LOCAL_COOKIE, token);		// 新建
//		cookie.setMaxAge(application.getSessionTimeOutInt());	// 设置有效期
//		cookie.setMaxAge(1);
//		cookie.setDomain(LOCAL_COOKIE_DOMAIN);					// 设置域名
		cookie.setPath(AppConstant.UNIX_SEP);									// 设置路径
		cookie.setSecure(request.isSecure());
		
		response.addCookie(cookie);								// 输出到客户端
		
		final String msg = "本地Cookie授权成功";
		returnMessage.setReturnMsg(msg);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);

		return returnMessage;
	}

	/**
	 * SysInfo系统授权
	 * @param request request
	 * @param response response
	 * @param credentials 验证
	 * @return ReturnMessage<SysInfoBean>
	 */
	@Override
	public ReturnMessage<SysInfoBean> authorizateSysInfo(HttpServletRequest request,
			HttpServletResponse response, Credentials credentials) {
		
		ReturnMessage<SysInfoBean> returnMessage = new ReturnMessage<SysInfoBean>();
		
		UserProfile userProfile = getUserProfile(credentials);
		Application application = getApplication(credentials.getApplicationCode());
		List<Role> roles = getRoles(userProfile, application);
		List<Application> applications = getApplications(userProfile, roles, application);
		List<Module> modules = getModules(roles, application);
		RightBean rights = getRights(modules);
		
		HttpSession session = request.getSession();
		
		String externalUserName = null;
		String shopId = null;
		if (application.getAuthenticateType() != null
				&& application.getAuthenticateType().intValue() == AppConstant.AUTHENTICATE_EXTERNAL) {
			Object o = session.getAttribute(AppConstant.USER_NAME);
			if(o != null){
				UserProfile userProfileTemp = (UserProfile)o;
				externalUserName = userProfileTemp.getExternalUserName();
				shopId = userProfileTemp.getShopId();
			}
		}
		
		session.setAttribute(AppConstant.USER_NAME, userProfile);
		session.setAttribute(AppConstant.ROLES_NAME, roles);
		session.setAttribute(AppConstant.APPLICATION_NAME, application);
		session.setAttribute(AppConstant.APPLICATION_NAMES, applications);
		session.setAttribute(AppConstant.MODULES_NAME, modules);
		session.setAttribute(AppConstant.RIGHTS_NAME, rights);
		session.setAttribute(AppConstant.CSS_NAME, application.getApplicationStyle());
		SysInfoBean sysInfoBean = SysInfoBean.getSysInfoBean();
		session.setAttribute(AppConstant.SYS_INFO, sysInfoBean);
		
		/*
		 * 增加设置外部系统人员code到当前系统作用域里
		 * 黄波
		 * 2013-11-21
		 */
		if (application.getAuthenticateType() != null
				&& application.getAuthenticateType().intValue() == AppConstant.AUTHENTICATE_EXTERNAL) {
			if(!StringUtils.isEmpty(externalUserName) && !StringUtils.isEmpty(shopId)){
				userProfile.setExternalUserName(externalUserName);
				userProfile.setShopId(shopId);
			}else{
				userProfile.setExternalUserName(request.getParameter("loginVo.userCode"));
				Object si = request.getAttribute(AppConstant.AUTHENTICATE_SHOP_REMARK);
				if(si != null){
					userProfile.setShopId(String.valueOf(si));
				}
			}
		}
		final String msg = "系统授权成功";
		returnMessage.setReturnMsg(msg);
		returnMessage.setReturnObject(sysInfoBean);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.authorization.IFWAuthorization#removeAuthorizate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ReturnMessage<SysInfoBean> removeAuthorizate(HttpServletRequest request,
			HttpServletResponse response) {
		
		removeAuthorizateSysInfo(request, response);
		
		removeAuthorizateLocalCookie(request, response);
		
		return null;
	}
	
	@Override
	public ReturnMessage<SysInfoBean> removeAuthorizateSysInfo(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		
		session.removeAttribute(AppConstant.USER_NAME);
		session.removeAttribute(AppConstant.ROLES_NAME);
		session.removeAttribute(AppConstant.APPLICATION_NAME);
		session.removeAttribute(AppConstant.APPLICATION_NAMES);
		session.removeAttribute(AppConstant.MODULES_NAME);
		session.removeAttribute(AppConstant.RIGHTS_NAME);
		session.removeAttribute(AppConstant.SYS_INFO);
		
		//移除"查询展示"对象
		session.removeAttribute(AppConstant.QUERY_SHOW_NAME);
		
		return null;
		
	}
	
	@Override
	public ReturnMessage<SysInfoBean> removeAuthorizateLocalCookie(HttpServletRequest request,
			HttpServletResponse response){
		
		Cookie localCookie = getAuthorizateLocalCookie(request, response);
		if(null != localCookie){
			Cookie cookie = new Cookie(localCookie.getName(), null); 
//			cookie.setDomain(LOCAL_COOKIE_DOMAIN);			// 设置域名
			cookie.setPath(AppConstant.UNIX_SEP);			// 设置路径
			cookie.setMaxAge(0);
			cookie.setSecure(request.isSecure());
			response.addCookie(cookie); 
		}
		
		return null;
	}
	
	private RightBean getRights(List<Module> modules){
		
		RightBean rightBean = new RightBean();
		for(Module module: modules){
			rightBean.put(module.getModuleCode(), module);
		}
		return rightBean;
	}
	
	@Override
	public String getCurRequestModuleUrl(HttpServletRequest request, 
			HttpServletResponse response){
		
		String servletPath = request.getServletPath();
		String curModuleUrl = servletPath;
		String queryCode = request.getParameter(AppConstant.QUERY_CODE);
		if(!DataValidater.isStrEmpty(queryCode)){
			curModuleUrl = servletPath+AppConstant.QUS_SIGN+AppConstant.QUERY_CODE
				+AppConstant.EQUAL_SIGN+queryCode;
		}
		
		String reportCode = request.getParameter(AppConstant.REPORT_CODE);
		if(servletPath.contains(AppConstant.UNIX_SEP+AppConstant.REPORT_ACTION) 
				&& !DataValidater.isStrEmpty(reportCode)){
			curModuleUrl = servletPath+AppConstant.QUS_SIGN+AppConstant.REPORT_CODE
				+AppConstant.EQUAL_SIGN+reportCode;
		}
		
		return curModuleUrl;
	}
	
	private UserProfile getUserProfile(Credentials credentials){
		
		UserProfile userProfileRet = null;
		
		UserProfile userProfile = new UserProfile();
		userProfile.setEmpNumber(credentials.getUserCode());
		userProfile.setAccountType(credentials.getUserType());
		
		userProfileRet = (UserProfile) roleRightService.querySingle(userProfile);
		
		return userProfileRet;
	}
	
	private List<Role> getRoles(UserProfile userProfile, Application application){
		
		return roleUserService.getRoleAndDefaultList(userProfile, application).getReturnObjects();
	}
	
	private Application getApplication(String applicationCode){
		
		Application applicationRet = null;
		
		Application application = new Application();
		application.setApplicationCode(applicationCode);
		
		applicationRet = (Application) roleRightService.querySingle(application);
		
		return applicationRet;
	}
	
	private List<Module> getModules(List<Role> roles, Application application){
		
		List<Module> modules = null;
		if(SysInfoBean.getSysInfoBean().isSuperRole(roles, application)){
			modules = roleRightService.getRightList(application).getReturnObjects();
		}else{
			modules = roleRightService.getRightList(roles).getReturnObjects();
		}
		Collections.sort(modules);
		
		return modules;
	}
	
	/**
	 * @param userProfile 用户
	 * @param roles 角色数组
	 * @param application 应用
	 * @return List<Application>
	 */
	private List<Application> getApplications(UserProfile userProfile,
			List<Role> roles, Application application) {
		
		List<Application> applications = null;
		SysInfoBean sysInfoBean = SysInfoBean.getSysInfoBean();
		if(sysInfoBean.isSuperRole(roles, application)){
			applications = applicationService.getApplicationsJDBC()
					.getReturnObjects();
		}else{
			applications = applicationService.getApplicationsJDBC(userProfile)
					.getReturnObjects();
		}
		
		return applications;
	}

	/**
	 * 取本地cookie
	 * @param request request
	 * @param response response
	 * @return Cookie
	 */
	@Override
	public Cookie getAuthorizateLocalCookie(HttpServletRequest request,
			HttpServletResponse response) {
	
		return getCookie(request, response, LOCAL_COOKIE);
	}

	/**
	 * 取SSO cookie
	 * @param request request
	 * @param response response
	 * @return Cookie
	 */
	@Override
	public Cookie getAuthorizateSSOCookie(HttpServletRequest request,
			HttpServletResponse response) {
		
		return getCookie(request, response, SSO_COOKIE);
	}
	
	/**
	 * 取SSO
	 * @param request request
	 * @param response response
	 * @return String
	 */
	public String getAuthorizateSSO(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
	    String userCode = (String)session.getAttribute(SSO_USER_NAME);
	    
	    // session 中取得的 userCode 是小写的，数据库中保存的 EMP_NUMBER 是大写的，在这里加大写转换
	    if (userCode != null) {
	    	userCode = userCode.toUpperCase();
	    }
	   
	    return userCode;
	}
	
	private Cookie getCookie(HttpServletRequest request, HttpServletResponse response,
			String cookieName){
		
		Cookie cookieRet = null;
		Cookie cookies[] = request.getCookies();
		if(null == cookies){
			return cookieRet;
		}
		for(Cookie cookie: cookies){
			if (cookieName.equals(cookie.getName())) {
				cookieRet = cookie;
				break;
			}
		}
		
		return cookieRet;
	}

	/**
	 * 取SysInfo系统信息
	 * @param request
	 * @param response
	 * @return SysInfoBean
	 */
	@Override
	public SysInfoBean getAuthorizateSysInfo(HttpServletRequest request,
			HttpServletResponse response) {
		return (SysInfoBean) request.getSession().getAttribute(AppConstant.SYS_INFO);
	}
	
	public Application getApplication(HttpServletRequest request,
			HttpServletResponse response){
		return (Application) request.getSession().getAttribute(AppConstant.APPLICATION_NAME);
	}
	
	@Override
	public void setSysApplication(HttpServletRequest request,
			HttpServletResponse response){
		if(request.getSession().getAttribute(AppConstant.SYS_APPLICATION_NAME)==null){
			request.getSession().setAttribute(AppConstant.SYS_APPLICATION_NAME,
				getApplication(AppConstant.SYS_APPLICATION_CODE));
		}
	}
	
	@Override
	public void setSysCss(HttpServletRequest request,
			HttpServletResponse response){
		if(request.getSession().getAttribute(AppConstant.CSS_NAME)==null){
			String css = AppConstant.CSS_DFT_VALUE;
			Application app = (Application) request.getAttribute(AppConstant.CONTEXT_APP);
			if(null != app){
				css = app.getApplicationStyle();
			}
			request.getSession().setAttribute(AppConstant.CSS_NAME, css);
		}
	}
	
	@Override
	public void setCurModule(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		List<Module> modules = (List<Module>) session.getAttribute(AppConstant.MODULES_NAME);
		if(null == modules){
			return;
		}
		String curModuleUrl = getCurRequestModuleUrl(request, response);
		for(Module module: modules){
			String[] moduleUrls = module.getModuleUrl();
			for(String moduleUrl: moduleUrls){
				if(moduleUrl.endsWith(curModuleUrl)){
					request.setAttribute(AppConstant.MODULE_NAME, module);
					break;
				}
			}
		}
	}

	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

	public void setRoleUserService(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
}