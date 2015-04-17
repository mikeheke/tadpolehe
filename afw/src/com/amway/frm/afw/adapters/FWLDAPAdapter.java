package com.amway.frm.afw.adapters;

import java.util.HashMap;

import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchResults;

import com.amway.frm.afw.authentication.IFWAuthenticator;
import com.amway.frm.afw.authentication.principal.Credentials;
import com.amway.frm.afw.exception.AuthSysException;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BaseDataSourceVo;

/**
 * LDAP用户验证实现类
 * @author huangweijin
 */

public class FWLDAPAdapter implements IFWAuthenticator {
	
	private static LogService logger = LogFactory.getLogger(FWLDAPAdapter.class);
	private LogService logService;

	/**
	 * 验证LDAP的用户信息
	 * 
	 * @param credentials
	 *            被验证的信息
	 * @return ReturnMessage ReturnMessage.getReturnCode等于1为成功其他值为失败
	 */
	public ReturnMessage<Credentials> authenticate(Credentials credentials) {
		
		ReturnMessage<Credentials> returnMessage = new ReturnMessage<Credentials>();
		
		String account = credentials.getUserCode(); 
		String password = credentials.getPassword();
		final String CN = "CN=";
		final String OU_O =  ",ou=users,o=alticor";
		String root = CN + account + OU_O;

		final int ldapPort = 389;	// 端口

		// 指定返回属性的列表
		//String returnAttrs[] = new String[] { "cn", "initials", "mail", "displayName" };
		
		LDAPConnection conn = null;
		try {
			String ldapPasswd = getLdapPasswd();
			if (DataValidater.isStrEmptyOrNull(ldapPasswd)) {
				returnMessage.setReturnCode(ReturnMessage.FAIL_CODE);
				final String msg = "LDAP密码获取失败";
				returnMessage.setReturnMsg(msg);
				return returnMessage;
			}
			String ldapUrl = getLdapUrl();
			if (DataValidater.isStrEmptyOrNull(ldapUrl)) {
				returnMessage.setReturnCode(ReturnMessage.FAIL_CODE);
				final String msg = "LDAP地址获取失败";
				returnMessage.setReturnMsg(msg);
				return returnMessage;
			}
			
			// 连接到LDAP
			conn = new LDAPConnection();
			
			// 连接LDAP
			conn.connect(ldapUrl, ldapPort, AfwConstant.LADP_USER, ldapPasswd);

			// 查询指定AD
			LDAPSearchResults resultObj = conn.search(root,
					LDAPConnection.SCOPE_BASE, null, null, false);
			if (resultObj.hasMoreElements()) {
				String dn = resultObj.next().getDN(); 
				if ((dn == null) || (dn.length() == 0)){
					return returnMessage; 
				}
				 
				if ((password != null) && (!(AppConstant.EMPTY_STR.equals(password.trim())))) 
				{ 
					 conn.authenticate(3, dn, password); 
				}
			}
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			final String msg = "AD验证成功";
			returnMessage.setReturnMsg(msg);
			returnMessage.setReturnObject(credentials);
			
		} catch (LDAPException e) {
			logService.error("LDAPException:", e);
			final String msg = " 用户验证失败";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		} finally {
			if (conn != null && conn.isConnected()){
				try {
					conn.disconnect();
				} catch (LDAPException e) {
					logService.error("LDAPException:", e);
				}
			}
		}

		return returnMessage;
	}
	
	private String getLdapPasswd() {
		String beanName = BaseDataSourceService.class.getSimpleName();
		BaseDataSourceService baseDataSourceService = (BaseDataSourceService) ContextFactory.getBean(beanName);
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				AfwConstant.LDAP_PQSSWD, new HashMap<String, String[]>(), AppConstant.SQL_AND).getReturnObject();
		String ldapPasswd = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_DN);
		return ldapPasswd;
	}

	private String getLdapUrl() {
		String beanName = BaseDataSourceService.class.getSimpleName();
		BaseDataSourceService baseDataSourceService = (BaseDataSourceService) ContextFactory.getBean(beanName);
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				AfwConstant.LDAP_URL, new HashMap<String, String[]>(), AppConstant.SQL_AND).getReturnObject();
		String ldapPasswd = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_DN);
		return ldapPasswd;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	
}
