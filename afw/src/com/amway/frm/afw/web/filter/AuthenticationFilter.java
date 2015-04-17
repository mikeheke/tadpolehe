package com.amway.frm.afw.web.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amway.frm.afw.authentication.principal.Credentials;
import com.amway.frm.afw.authorization.IFWAuthorization;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.exception.AuthSysException;
import com.amway.frm.afw.service.ApplicationService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.web.action.BaseAction;

/**
 * 登录验证过滤器
 * @author huangweijin
 */

public class AuthenticationFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 303012457848860099L;
	
	//授权器
	private IFWAuthorization authorization;
	
	private String loginPage;
	private String rightPage;
	//private String errorPage;
	private String enable;
	private String noLoginAuthen;
	private String noRightAuthen;
	private String noAppLoginAuthen;
	private String noAppRightAuthen;
	private Properties noLoginAuthenUrls = new Properties();
	private Properties noRightAuthenUrls = new Properties();

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//System.out.println("url:" + httpRequest.getRequestURL());
		//System.out.println("servlet:" + httpRequest.getServletPath());
		
		//初始化系统信息
		initSystemInfo(httpRequest, httpResponse);
		
		if(DataValidater.isFalse(filterEnable())){
			chain.doFilter(request, response);
			return;
		}
		
		if(DataValidater.isFalse(needFilterLogin(httpRequest))){
			chain.doFilter(request, response);
			return;
		}
		
		if(DataValidater.isFalse(filterLogin(httpRequest, httpResponse))){
			request.setAttribute(BaseAction.RET_INFO, AfwConstant.LOGIN_NO);
			forwardToPage(request, response, loginPage);
			return;
		}
	
		if(DataValidater.isFalse(needFilterRight(httpRequest))){
			chain.doFilter(request, response);
			return;
		}
		
		if(DataValidater.isFalse(filterRight(httpRequest, httpResponse))){
			request.setAttribute(BaseAction.RET_INFO, AfwConstant.LOGIN_RIGHT_NO);
			forwardToPage(request, response, rightPage);
			return;
		}
		
		if(isQueryContextRequest(httpRequest)){
			redirectToPage(httpResponse, getQueryChangingPage(httpRequest));
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	private void initSystemInfo(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse){
		
		//设置管理平台
		authorization.setSysApplication(httpRequest, httpResponse);
		
		//设置当前应用
		setContextApplication(httpRequest);	//设置上下文应用
		
		//设置CSS值
		authorization.setSysCss(httpRequest, httpResponse);
		
		//设置当前模块
		authorization.setCurModule(httpRequest, httpResponse);
		
	}
	
	private String getQueryChangingPage(HttpServletRequest httpRequest){
		
		String servlet = httpRequest.getServletPath();
		StringBuffer params = new StringBuffer(getQueryParams(httpRequest));
		String context = httpRequest.getContextPath();
		params.append(AppConstant.CUR_APP_CONTEXT).append(AfwConstant.EQUAL_SIGN)
			.append(context.replace(AfwConstant.UNIX_SEP, AfwConstant.EMPTY_STR));
		Application application = getSysApplication(httpRequest);
		String page = application.getGoToUrl()+servlet+params.toString();
		
		return page;
	}
	
	private String getQueryParams(HttpServletRequest httpRequest){
		StringBuffer sbf = new StringBuffer(AfwConstant.QUS_SIGN);
		Map<String, String[]> paramMap = httpRequest.getParameterMap();
		Set<Entry<String, String[]>> paramSet = paramMap.entrySet();
		for(Entry<String, String[]> paramEntry: paramSet){
			String key = paramEntry.getKey();
			String[] values = paramEntry.getValue();
			for(String value: values){
				value = DataConverter.encodeUTF8Str(value);
				sbf.append(key).append(AfwConstant.EQUAL_SIGN)
					.append(value).append(AfwConstant.AND_SIGN);
			}
		}
		
		return sbf.toString();
	}
	
	private boolean isQueryContextRequest(HttpServletRequest httpRequest){
		String context = httpRequest.getContextPath()
			.replace(AfwConstant.UNIX_SEP, AfwConstant.EMPTY_STR);
		String servletPath = httpRequest.getServletPath()
			.replace(AfwConstant.UNIX_SEP, AfwConstant.EMPTY_STR);
		return !context.equals(getSysApplication(httpRequest).getContext())
				&& AfwConstant.QUERY_SHOW_ACTION.equals(servletPath);
	}
	
	private void setContextApplication(HttpServletRequest httpRequest){
		Application application = (Application) httpRequest.getSession()
				.getAttribute(AppConstant.APPLICATION_NAME);
		if(null == application){
			application = generateApplication(httpRequest);
		}
		
		httpRequest.setAttribute(AppConstant.CONTEXT_APP, application);
	}

	private boolean needFilterRight(HttpServletRequest httpRequest) {
		
		boolean result = false;
		
		String url = httpRequest.getServletPath();
		result = !(noRightAuthenUrls.containsKey(url) 
						|| noRightAuthenUrls.containsValue(url));
		
		return result;
	}

	private boolean needFilterLogin(HttpServletRequest httpRequest) {
		
		boolean result = false;
		
		String url = httpRequest.getServletPath();
		result = !(noLoginAuthenUrls.containsKey(url) 
						|| noLoginAuthenUrls.containsValue(url));
		
		return result;
	}
	
	private boolean filterEnable(){
		
		boolean result = true;
		
		if(!enable.equalsIgnoreCase(AfwConstant.YES_STR) 
				&& !enable.equalsIgnoreCase(AfwConstant.TRUE_STR)){
			result = false;
		}
		
		return result;
	}
	
	private boolean filterLogin(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException {
		
		String userCode = null;
		Credentials credentials = null;
		SysInfoBean sysInfoBean = authorization.getAuthorizateSysInfo(httpRequest, httpResponse);
		
		//切换用户
		if(sysInfoBean != null && sysInfoBean.getSysInfoOld()!=null){
			authorization.removeAuthorizateLocalCookie(httpRequest, httpResponse);
			return true;
		}
		
		//系统授权时
		if(sysInfoBean != null){
			userCode = sysInfoBean.getUserProfile().getEmpNumber();
			credentials = getCredentials(httpRequest, userCode);
			if(isAppGoToPage(httpRequest, credentials)){
				authorization.authorizateSysInfo(httpRequest, httpResponse, credentials);
			}
			authorization.authorizateLocalCookie(httpRequest, httpResponse, credentials);
			return true;
		}
		
		//本地授权时
		Cookie localCookie = authorization.getAuthorizateLocalCookie(httpRequest, httpResponse);
		if(localCookie != null){
			userCode = localCookie.getValue();
			credentials = getCredentials(httpRequest, userCode);
			authorization.authorizate(httpRequest, httpResponse, credentials);
			return true;
		}
		
		//SSO授权时
		userCode = authorization.getAuthorizateSSO(httpRequest, httpResponse);
		if(userCode != null){
			credentials = getCredentials(httpRequest, userCode);
			authorization.authorizate(httpRequest, httpResponse, credentials);
			return true;
		}

		return false;
	
	}
	
	private boolean isAppGoToPage(HttpServletRequest httpRequest, Credentials credentials){
		if(DataValidater.isStrEmpty(credentials.getApplicationCode())){
			return false;
		}
		String context = httpRequest.getParameter(AppConstant.CUR_APP_CONTEXT);
		if(!DataValidater.isStrEmpty(context)){
			return true;
		}
		return false;
	}
	
	private boolean filterRight(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		if(SysInfoBean.getSysInfoBean().isSuperRole()){
			return true;
		}
		
		if(SysInfoBean.getSysInfoBean().isAppAdmin()){
			return true;
		}
		//应用开发员有权限进行操作
		if(SysInfoBean.getSysInfoBean().isAppDeveloperModules()){
			return true;
		}
		
		
		String curModuleUrl = authorization.getCurRequestModuleUrl(httpRequest,
				httpResponse);
		
		HttpSession session = httpRequest.getSession();
		List<Module> modules = (List<Module>) session.getAttribute(AppConstant.MODULES_NAME);
		for(Module module: modules){
			String[] moduleUrls = module.getModuleUrl();
			for(String moduleUrl: moduleUrls){
				if(moduleUrl.indexOf(curModuleUrl) > -1){
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void redirectToPage(HttpServletResponse response, String page)
			throws IOException {
		response.sendRedirect(page);
	}
	
	private void forwardToPage(ServletRequest request,
			ServletResponse response, String page) throws ServletException,
			IOException {
		request.getRequestDispatcher(page).forward(request, response);
	}

	private Credentials getCredentials(HttpServletRequest request,
			String userCode) {
		
		Credentials credentials = new Credentials();
		credentials.setUserCode(userCode);
		Application application = generateApplication(request);
		if(application != null){
			credentials.setApplicationCode(application.getApplicationCode());
		}
		
		return credentials;
	}
	
	private Application generateApplication(HttpServletRequest request){
		
		Application applicationRet = null;
		
		//String server = DataConverter.getDomainByUrl(request.getRequestURL().toString());
		//String port = DataConverter.IntegerToString(request.getLocalPort());
		String context = request.getParameter(AppConstant.CUR_APP_CONTEXT);
		if(DataValidater.isStrEmpty(context)){
			context = DataConverter.replace(request.getContextPath(), 
					AfwConstant.UNIX_SEP, AfwConstant.EMPTY_STR);
		}
		
		Application application = new Application();
		//application.setDeployServer(server);
		//application.setPort(port);
		application.setContext(context);
		
		ApplicationService applicationService = (ApplicationService) ContextFactory
				.getBean(ApplicationService.class.getSimpleName());
		applicationRet = (Application) applicationService.querySingle(application);
		
		return applicationRet;
	}
	
	private Application getSysApplication(HttpServletRequest request){
		return (Application) request.getSession().getAttribute(AppConstant.SYS_APPLICATION_NAME);
	}

	public void init(FilterConfig config) {
		
		enable = config.getInitParameter(AfwConstant.ENABLE_KEY);
		loginPage = config.getInitParameter(AfwConstant.LOGIN_KEY);
		rightPage = config.getInitParameter(AfwConstant.RIGHT_KEY);
		//errorPage = config.getInitParameter(AfwConstant.ERROR_KEY);
		noLoginAuthen = config.getInitParameter(AfwConstant.AUTHEN_LOGIN_NO);
		noRightAuthen = config.getInitParameter(AfwConstant.AUTHEN_RIGHT_NO);
		noAppLoginAuthen = config.getInitParameter(AfwConstant.AUTHEN_LOGIN_APP_NO);
		noAppRightAuthen = config.getInitParameter(AfwConstant.AUTHEN_RIGHT_APP_NO);
		this.loadNoAuthenUrls();
		
		final String FWAuthorization = "FWAuthorization";
		IFWAuthorization authorization = (IFWAuthorization) ContextFactory.getBean(FWAuthorization);
		setAuthorization(authorization);
	}
	
	private void loadNoAuthenUrls(){
		
		loadInputStream(noLoginAuthenUrls, getClass().getResourceAsStream(noAppLoginAuthen));
		loadInputStream(noLoginAuthenUrls, getClass().getResourceAsStream(noLoginAuthen));
		loadInputStream(noRightAuthenUrls, getClass().getResourceAsStream(noAppRightAuthen));
		loadInputStream(noRightAuthenUrls, getClass().getResourceAsStream(noRightAuthen));
		
	}
	
	private void loadInputStream(Properties noAuthenUrls,
			InputStream noAuthenIs){
		if(null == noAuthenIs){
			return;
		}
		try {
			noAuthenUrls.load(noAuthenIs);
		} catch (IOException e) {
			throw new AuthSysException(e);
		}
	}
	
	private void clearNoAuthenUrls(){
		noLoginAuthenUrls.clear();
		noRightAuthenUrls.clear();
	}

	public void destroy() {
		
		/*
		enable = null;
		loginPage = null;
		rightPage = null;
		errorPage = null;
		noLoginAuthen = null;
		noRightAuthen = null;
		noAppLoginAuthen = null;
		noAppRightAuthen = null;
		*/
		clearNoAuthenUrls();
		setAuthorization(null);
	}
	
	public void setAuthorization(IFWAuthorization authorization) {
		this.authorization = authorization;
	}

}
