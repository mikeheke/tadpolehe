package com.amway.frm.afw.web.action;

import org.apache.struts2.ServletActionContext;

import com.amway.frm.afw.authentication.principal.Credentials;
import com.amway.frm.afw.authorization.IFWAuthorization;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.service.LoginService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.LoginVo;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * 
 * @author lenovo
 *
 */
public class LoginAction extends BaseAction {

	/**
	 * 获取用户在登录页面输入的用户名，密码，应用系统后进行验证
	 */
	private static final long serialVersionUID = 4102862970239670316L;
	
	private LoginService loginService;
	
	private static LogService logger = LogFactory.getLogger(LoginAction.class);
	
	private IFWAuthorization authorization;
	
	private LoginVo loginVo;
	
	//private String goToUrl;

	/**
	 * 调用LoginService接口的webLogin()方法
	 * 
	 * @return SUCCESS跳转到应用系统对应的主窗口；ERROR跳转到error.jsp；VALIDATEFalse校验失败跳转回登录页面
	 */
	public String login() {
		
		//logger.info("") 调用了之后，　logback才生效
		logger.info("login...");
		
		String result = SUCCESS;
		
		if(!validateData()){
			return LOGIN;
		}
		//退出登录
		logout();
		
		//登录
		ReturnMessage<Credentials> returnMessageCre = null;
		returnMessageCre = loginService.webLogin(loginVo);
		
		final String loginMsg = "登录";
		logger.writeOperLog(AfwConstant.LOGIN, loginVo.getUserCode()+loginMsg, 
				loginVo.getUserCode(), AfwConstant.EMPTY_STR);
		
		if(returnMessageCre.isSuccess()){
			//授权
			authorization.authorizate(ServletActionContext.getRequest(), 
					ServletActionContext.getResponse(), returnMessageCre.getReturnObject());
			
			final String authenticateMsg = "授权";
			logger.writeOperLog(AfwConstant.AUTHENTICATE, loginVo.getUserCode()+authenticateMsg, 
					loginVo.getUserCode(), AfwConstant.EMPTY_STR);
		}
		
		result = setReturnMessage(returnMessageCre, SUCCESS, LOGIN);
		
		loginVo = null;
		return result;
	}
	
	public String logout() {
		
		//删除授权
		authorization.removeAuthorizate(ServletActionContext.getRequest(), 
				ServletActionContext.getResponse());
		
		return LOGIN;
	}

	@Override
	protected boolean validateData() {
		
		boolean result = true;
		
		if(DataValidater.isStrEmpty(loginVo.getUserCode())){
			this.setMessage(AfwConstant.USER_CODE_KEY, AfwConstant.USER_CODE_MSG);
			result = false;
		}else if(loginVo.getUserCode().length() > 10){
			this.setMessage(AfwConstant.USER_CODE_KEY, AfwConstant.USER_CODE_MSG_LEN);
			this.loginVo.setUserCode(this.loginVo.getUserCode().substring(0, 10));
			result = false;
		}
		if(DataValidater.isStrEmpty(loginVo.getPassword())){
			this.setMessage(AfwConstant.PASSWORD_KEY, AfwConstant.PASSWORD_MSG);
			result = false;
		}else if(loginVo.getPassword().length() > 20){
			this.setMessage(AfwConstant.PASSWORD_KEY, AfwConstant.PASSWORD_MSG_LEN);
			this.loginVo.setPassword(this.loginVo.getPassword().substring(0, 20));
			result = false;
		}
		String applicationCode = loginVo.getApplicationCode();
		if(DataValidater.isStrEmpty(applicationCode)){
			this.setMessage(AfwConstant.APPLICATION_CODE_KEY, AfwConstant.APPLICATION_CODE_MSG2);
			result = false;
		}
		
		Application application = new Application(applicationCode);
		Integer isKaptcha = ((Application) this.getSingle(application)).getIsCheckCode();
		if(null != isKaptcha
				&& isKaptcha.intValue() == AppConstant.YES.intValue()){
			String kaptcha = loginVo.getKaptcha();
			if(DataValidater.isStrEmpty(kaptcha)){
				this.setMessage(AfwConstant.KAPTCHA_KEY, AfwConstant.KAPTCHA_MSG);
				result = false;
			}
			String kaptchaSession = (String) ContextFactory.getFromSession(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if(!kaptcha.equalsIgnoreCase(kaptchaSession)){
				this.setMessage(AfwConstant.KAPTCHA_KEY, AfwConstant.KAPTCHA_MSG2);
				result = false;
			}	
		}
		
		return result;
	}
	
	public String isCheckCode(){
		
		Application application = (Application) loginService
				.querySingle(getEntity());
		String isCheckCode = DataConverter.valueOf(application.getIsCheckCode());
		
		setJsonValue(isCheckCode);
		
		return JSON;
	}

	@Override
	protected Application getEntity() {
		
		Application application = new Application();
		application.setApplicationCode(loginVo.getApplicationCode());
		
		return application;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setAuthorization(IFWAuthorization authorization) {
		this.authorization = authorization;
	}

	public LoginVo getLoginVo() {
		return loginVo;
	}

	public void setLoginVo(LoginVo loginVo) {
		this.loginVo = loginVo;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
	/**
	 * 跳转url
	 * @return
	 */
	public String getGoToUrl() {
		
		SysInfoBean sysInfo = getSysInfo();
		if(null == sysInfo){
			return LOGIN;
		}
		Application application = sysInfo.getApplication();
		if(null == application){
			return LOGIN;
		}
		String url = application.getGoToUrl();
		
		return url;
	}
	
	public String getGoToSysUrl() {
		
		SysInfoBean sysInfo = getSysInfo();
		if(null == sysInfo){
			return LOGIN;
		}
		Application application = sysInfo.getApplication();
		Application sysApplication = sysInfo.getSysApplication();
		if(null == application || null == sysApplication){
			return LOGIN;
		}
		String url = sysApplication.getGoToUrl() + AfwConstant.QUS_SIGN
				+ AppConstant.CUR_APP_CONTEXT + AfwConstant.EQUAL_SIGN + application.getContext();
		
		return url;
	}
}
