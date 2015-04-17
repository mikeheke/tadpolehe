package com.amway.frm.email.util;

import com.amway.frm.base.util.AppConstant;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-3-28
 * Time: 16:01:52
 * To change this template use File | Settings | File Templates.
 */
public interface EmailConstant extends AppConstant{
	
	public static final String CHARSET = "text/plain;charset=";
	public static final String HTML_CHARSET  = "text/html;charset=";
	public static final String DEFAULT_ENCODE = "GBK";
	
	public static final String EMAIL_ENCODE = "emailEncode";
    
	public static final String SYN_SEND = "0";   //同步发送
	public static final String ASYN_SEND = "1";  //异步发送
	public static final String WSYN_SEND = "2";  //接口发送
    
	public static final Integer TO_SEND = 0;		// 待发送
	public static final Integer SEND_SUCCESS = 1;	// 发送成功
	public static final Integer SEND_FAILURE = 2;	// 发送失败
    
	public static final Integer DEFAULT_SEND_NUM = 2;	// 默认发送次数
    
	public static final String EMAIL_HOST = "";
	public static final String EMAIL_PERSONAL_NAME = "wpsadmin";
    
	public static final String EMAIL_SEND_RECORD_ID = "EMAIL_SEND_RECORD_ID";
	public static final String HOST = "HOST";
	public static final String PERSONAL_NAME = "PERSONAL_NAME";
	public static final String MAIL_FROM_ADD = "MAIL_FROM_ADD";
	public static final String MAIL_TO_ADD = "MAIL_TO_ADD";
	public static final String MAIL_CC_ADD = "MAIL_CC_ADD";
	public static final String MAIL_BCC_ADD = "MAIL_BCC_ADD";
	public static final String TEMPLATES_ID = "TEMPLATES_ID";
	public static final String EMAIL_SUBJECT = "EMAIL_SUBJECT";
	public static final String EMAIL_CONTENT = "EMAIL_CONTENT";
	public static final String ACCESSORY = "ACCESSORY";
	public static final String SEND_FLAG = "SEND_FLAG";
	public static final String SEND_NUM = "SEND_NUM";
	
	public static final String EMAIL_INFO_EXP_0001 = "0001";
	
	public static final String TEMPLATES_CODE_KEY = "templatesCodeMsg";
	public static final String TEMPLATES_CODE_MSG = "模块代码不能为空";
	public static final String TEMPLATES_NAME_KEY = "templatesNameMsg";
	public static final String TEMPLATES_NAME_MSG = "模块名称不能为空";
	public static final String TEMPLATES_SUBJECT_KEY = "templatesSubjectMsg";
	public static final String TEMPLATES_SUBJECT_MSG = "模块主题不能为空";
	public static final String TEMPLATES_CONTENT_KEY = "templatesContentMsg";
	public static final String TEMPLATES_CONTENT_MSG = "模块内容不能为空";
	
	public static final Integer MAIL_TO_EMPTY_CODE = -1005;
	
	/**
	 * 系统发邮件邮箱地址
	 */
	public static final String EMAIL_ADDRESS = "wpsadmin@amway.com";
	
}
