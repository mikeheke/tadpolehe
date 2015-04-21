/**
 * 
 */
package com.amway.frm.afw.util;

import com.amway.frm.base.util.AppConstant;

/**
 * 
 *
 * 2011-6-10 下午03:32:59
 */
public interface AfwConstant extends AppConstant {
	
	//默认联结
	public static final String LINK_DEFAULT = "#";
	
	public static final String LINK_HTTP = "http://";
	
	//左帧
	public static final String LEFT_DEFAULT_TARGET = "left";
	
	//主帧
	public static final String MAIN_DEFAULT_TARGET = "main";
	
	//顶帧
	public static final String TOP_DEFAULT_TARGET = "top";
	
	//AD 帐户
	public static final Integer AD_ACCOUNT = 1;
	
	//非AD 帐户
	public static final Integer COMMON_ACCOUNT = 0;
	
	//超级角色
	public static final String SUPER_ROLE_CODE = "00";
	
	
	//增加人：黄波
	/**
	 * 各个应用管理员角色
	 */
	public static final String APP_ROLE_CODE = "admin";
	
	public static final String CHANGE_ROLE_CODE = SUPER_ROLE_CODE;
	
	public static final String CLEAR_ROLE_CODE = SUPER_ROLE_CODE;
	
	//TREE 风格
	public static final Integer LAYOUT_TREE = 0;
	public static final String LAYOUT_TREE_STR = "tree";
	
	//OUTLOOK 风格
	public static final Integer LAYOUT_OUTLOOK = 1;
	public static final String LAYOUT_OUTLOOK_STR = "outlook";
	
	//WINDOW 风格
	public static final Integer LAYOUT_WINDOW = 2;
	public static final String LAYOUT_WINDOW_STR = "window";
	
	//支持browser 风格
	public static final Integer LAYOUT_BROWSER = 3;
	public static final String LAYOUT_BROWSER_STR = "browser";
	
	//增加当前级
	public static final String ADD_LEVEL_CURRENT = "0";
	
	//增加下一级
	public static final String ADD_LEVEL_NEXT = "1";
	
	//ldap验证
	public static final String FWD_LDAP = "LDAP";
	
	//db验证
	public static final String FWD_DB = "DB";
	
	//sso验证
	public static final String FWD_SSO = "SSO";

	//权限拥有标志
	public static final String RIGHT_NO = "none"; //不拥有
	public static final String RIGHT_YES = "";    //拥有
	
	//模块类型
	public static final Integer MODULE_TYPE_M = 0; //模块
	public static final Integer MODULE_TYPE_B = 1; //按钮
	
	public static final String SPILT_OPER_RU = "\\+";
	
	//public static final String EX_RIGHT_PARAM = "queryCode";
	
	public static final String DEFAULT_ADMIN_ROLE_AFT = "Admin";
	public static final String DEFAULT_ADMIN_ROLE_NAME_AFT = "应用管理员";
	public static final String DEFAULT_USER_ROLE_AFT = "User";
	public static final String DEFAULT_USER_ROLE_NAME_AFT = "默认用户";
	public static final String DEFAULT_DEV_ROLE_AFT = "Developer";
	public static final String DEFAULT_DEV_ROLE_NAME_AFT = "应用开发员";
	
	//Application
	public static final String APPLICATION_CODE_KEY = "applicationCodeMsg";
	public static final String APPLICATION_CODE_MSG = "请填写应用编码";
	public static final String APPLICAITON_NAME_KEY = "applicationNameMsg";
	public static final String APPLICATION_NAME_MSG = "请填写应用名称";
	public static final String IS_CHECK_CODE_KEY = "isCheckCodeMsg";
	public static final String IS_CHECK_CODE_MSG = "请选择是否有检验码";
	public static final String APPLICATION_LAYOUT_KEY = "applicationLayoutMsg";
	public static final String APPLICATION_LAYOUT_MSG = "请选择界面风格";
	public static final String APPLICATION_STYLE_KEY = "applicationStyleMsg";
	public static final String APPLICATION_STYLE_MSG = "请选择界面皮肤";
	public static final String TIME_ZONE_KEY = "timeZoneMsg";
	public static final String TIME_ZONE_MSG = "请选择时区";
	public static final String LANGUANGE_KEY = "languangeMsg";
	public static final String LANGUANGE_MSG = "请选择语言";
	public static final String STATE_KEY = "stateMsg";
	public static final String STATE_MSG = "请选择状态";
	public static final String MATHINNERUSER_KEY = "matchInnerUserMsg";
	public static final String MATHINNERUSER_MSG = "请输入外部认证人员";
	public static final String EJBAUTHSTATE_KEY = "ejbAuthStatelessMsg";
	public static final String EJBAUTHSTATE_MSG = "请输入ejb接口映射名称";
	
	
	//ApplicationPlus
	public static final String APPLICATION_ID_KEY = "applicationIdMsg";
	public static final String APPLICATION_ID_MSG = "请选择应用";
	public static final String APPLICATION_PLUS_NAME_KEY = "applicationNameMsg";
	public static final String APPLICATION_PLUS_NAME_MSG = "请选择应用";
	public static final String PARAMETER_CODE_KEY = "parameterCodeMsg";
	public static final String PARAMETER_CODE_MSG = "请填写参数编码";
	public static final String PARAMETER_NAME_KEY = "parameterNameMsg";
	public static final String PARAMETER_NAME_MSG = "请填写参数名称";
	public static final String PARAMETER_VALUE_KEY = "parameterValueMsg";
	public static final String PARAMETER_VALUE_MSG = "请填写参数值";
	
	public static final String LOGIN_NO = "您还没登录，请登录";
	public static final String LOGIN_RIGHT_NO = "对不起，您无此功能权限";
	
	public static final String QUERY_SHOW_ACTION = "showAction.action";
	
	public static final String USER_PROFILE_ID_KEY = "userProfileIdMsg";
	public static final String USER_PROFILE_ID_MSG = "请选择用户";
	
	public static final String LDAP_PQSSWD = "ldapPasswd";
	public static final String LDAP_URL = "ldapUrl";
	public static final String LDAP_HOST = "ldap-cn-adam-apps-uc.intranet.local";
	public static final String LADP_USER = "cn=ldap-aff-cn-portal,ou=users,o=alticor";
	
	public static final String LOGIN = "login";
	public static final String AUTHENTICATE = "authenticate";
	public static final String USER_CODE_KEY = "userCodeMsg";
	public static final String USER_CODE_MSG = "用户名不能为空!";
	public static final String USER_CODE_MSG_LEN = "用户名长度不能超过10!";
	public static final String USER_CODE_MSG_ILLEGAL = "用户名有非法字符";
	public static final String PASSWORD_KEY = "passwordMsg";
	public static final String PASSWORD_MSG = "密码不能为空!";
	public static final String PASSWORD_MSG_LEN = "密码长度不能超过20!";
	public static final String PASSWORD_MSG_ILLEGAL = "密码有非法字符";
	public static final String APPLICATION_CODE_MSG2 = "应用不能为空!";
	public static final String KAPTCHA_KEY = "kaptchaMsg";
	public static final String KAPTCHA_MSG = "验证码不能为空!";
	public static final String KAPTCHA_MSG2 = "验证码不正确!";
	
	public static final String MODULE_CODE_KEY = "moduleCodeMsg"; 
	public static final String MODULE_CODE_MSG = "请填写功能编号";
	public static final String MODULE_NAME_KEY = "moduleNameMsg"; 
	public static final String MODULE_NAME_MSG = "请填写功能名称";
	public static final String PARENT_MODULE_CODE_KEY = "parentModuleCodeMsg"; 
	public static final String PARENT_MODULE_CODE_MSG = "请填写父功能编号";
	public static final String IS_MODULE_OR_BUTTON_KEY = "isModuleOrButtonMsg"; 
	public static final String IS_MODULE_OR_BUTTON_MSG = "请选择模块或按钮";
	public static final String ORDER_NO_KEY = "orderNoMsg"; 
	public static final String ORDER_NO_MSG = "请填写排序号";
	public static final String IS_SEED_KEY = "isSeedMsg"; 
	public static final String IS_SEED_MSG = "请选择是否子模块";
	public static final String OPEN_TYPE_KEY = "openTypeMsg"; 
	public static final String OPEN_TYPE_MSG = "请选择打开方式";
	
	public static final String ROLE_CODE_KEY = "roleCodeMsg";
	public static final String ROLE_CODE_MSG = "请填写角色编码";
	public static final String ROLE_NAME_KEY = "roleNameMsg";
	public static final String ROLE_NAME_MSG = "请填写角色名称";

	public static final String ROLE_ID_KEY = "roleIdMsg";
	public static final String ROLE_ID_MSG = "角色不能为空";
	
	public static final String SRC_ROLE_ID_KEY = "srcRoleIdMsg";
	public static final String SRC_ROLE_ID_MSG = "源角色不能为空";
	public static final String DES_ROLE_ID_KEY = "desRoleIdMsg";
	public static final String DES_ROLE_ID_MSG = "目标角色不能为空";
	public static final String DES_ROLE_ID_MSG2 = "目标角色和源角色不能相同";
	
	public static final String USER_PROFILE_ID_MSG2 = "用户不能为空";
	
	public static final String ROLE = "role";
	public static final String ROLE_ID = "roleId";
	public static final String USER_PROFILE_ID = "userprofile_id";
	
	public static final String EMP_NUMBER_KEY = "empNumberMsg";;
	public static final String EMP_NUMBER_MSG = "请填写用户登录账号";
	public static final String CHINESE_NAME_KEY = "chineseName";
	public static final String CHINESE_NAME_MSG = "请填写中文名";
	public static final String ACCOUNT_TYPE_KEY = "accountType";
	public static final String ACCOUNT_TYPE_MSG = "请账号类型";
	public static final String ORG_UNIT_CODE_KEY = "orgUnitCode";
	public static final String ORG_UNIT_CODE_MSG = "请部门编码";
	public static final String ENGLISH_NAME_KEY = "englishName";
	public static final String ENGLISH_NAME_MSG = "请填写英文名";
	public static final String STATE_MSG2 = "状态值为数字类型";
	public static final String DATE_HIRED_KEY = "dateHired";
	public static final String DATE_HIRED_MSG = "入职时间格式不正确";
	public static final String DATE_TERMINATED_KEY = "dateTerminated";
	public static final String DATE_TERMINATED_MSG = "离职时间格式不正确";
	public static final String DATE_BIRTH_DAY_KEY = "dateBirthDay";
	public static final String DATE_BIRTH_DAY_MSG = "生日时间格式不正确";
	
	public static final String DEFAULT_PASSWORD = "default_passwd";
	
	public static final String ENABLE_KEY = "enable";
	public static final String LOGIN_KEY = "login";
	public static final String RIGHT_KEY = "right";
	public static final String ERROR_KEY = "error";
	public static final String AUTHEN_LOGIN_NO = "noLoginAuthen";
	public static final String AUTHEN_RIGHT_NO = "noRightAuthen";
	public static final String AUTHEN_LOGIN_APP_NO = "noAppLoginAuthen";
	public static final String AUTHEN_RIGHT_APP_NO = "noAppRightAuthen";
}
