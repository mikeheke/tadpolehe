package com.amway.frm.afw.authorization;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amway.frm.afw.authentication.principal.Credentials;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * 
 * @author lenovo
 *
 */
public interface IFWAuthorization {
	
	public static final String LOCAL_COOKIE = "LocalCookie";
	//public static final String LOCAL_COOKIE_DOMAIN = "intranet.local";
	//public static final Integer LOCAL_COOKIE_ACTIVE_TIME = 60*60;
	public static final String SSO_COOKIE = "SSOCookie";
	public static final String SSO_USER_NAME = "userName";
	
	/**
	 * 授权本应用系统信息 
	 * 
	 * @param sysInfo 系统信息
	 * @return
	 */
	ReturnMessage<SysInfoBean> authorizateSysInfo(HttpServletRequest request,
			HttpServletResponse response, Credentials credentials);

	/**
	 * 授权跨应用本地cookie
	 * 
	 * @param request
	 * @return
	 */
	ReturnMessage<SysInfoBean> authorizateLocalCookie(HttpServletRequest request,
			HttpServletResponse response, Credentials credentials);

	/**
	 * 授权
	 * @param request
	 * @param response
	 * @param credentials
	 * @return
	 */
	ReturnMessage<SysInfoBean> authorizate(HttpServletRequest request,
			HttpServletResponse response, Credentials credentials);
	
	/**
	 * 删除授权
	 * @param request
	 * @param response
	 * @param credentials
	 * @return
	 */
	ReturnMessage<SysInfoBean> removeAuthorizate(HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * 删除系统信息授权
	 * @param request
	 * @param response
	 * @param credentials
	 * @return
	 */
	ReturnMessage<SysInfoBean> removeAuthorizateSysInfo(HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * 删除本地COOKIE授权
	 * @param request
	 * @param response
	 * @param credentials
	 * @return
	 */
	ReturnMessage<SysInfoBean> removeAuthorizateLocalCookie(HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * 得到客户端用户信息cookie
	 * 
	 * @param request
	 * @param response
	 * @return -1为没有找到cookie,其他为用户信息
	 */
	Cookie getAuthorizateLocalCookie(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 得到SSO用户信息cookie
	 * 
	 * @param request
	 * @param response
	 * @return -1为没有找到cookie,其他为用户信息
	 */
	Cookie getAuthorizateSSOCookie(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 得到SSO用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	String getAuthorizateSSO(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 得到系统信息
	 * 
	 * @param request
	 * @param response
	 * @return ReturnMessage
	 */
	SysInfoBean getAuthorizateSysInfo(HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * 得到应用信息
	 * 
	 * @param request
	 * @param response
	 * @return ReturnMessage
	 */
	Application getApplication(HttpServletRequest request,
			HttpServletResponse response);
	
	void setSysApplication(HttpServletRequest request,
			HttpServletResponse response);
	
	void setSysCss(HttpServletRequest request,
			HttpServletResponse response);
	
	void setCurModule(HttpServletRequest request, HttpServletResponse response);
	
	String getCurRequestModuleUrl(HttpServletRequest request, 
			HttpServletResponse response);
}
