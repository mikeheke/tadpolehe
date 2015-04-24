/**
 * 
 */
package com.amway.frm.base.util;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：应用常量接口
 */
public interface AppConstant {
	
	//默认Schema
	//public static final String APP_DEAULT_SCHEMA = "amway";
	public static final String APP_DEAULT_SCHEMA = "amway2";
	
	//Db2数据库用户
	public static final String DB2_USER = "DB2ADMIN";
	
	// PORTAL 前缀
	public static final String PORTAL_PRE = "AEP";

	//MD5 KEY
	public static final String MD5_KEY = "amway";
	
	//启用
	public static final Integer START = 1;
	
	//停用
	public static final Integer STOP = 0;
	
	//记录已存在
	public static final String RECORD_YEXISTS = "记录已存在";
	
	//记录不存在
	public static final String RECORD_NEXISTS = "记录不存在";
	
	//记录禁用
	public static final String RECORD_STOP = "记录已禁用";
	
	//记录启用
	public static final String RECORD_START = "记录已启用";
	
	//数据源名称，配置在容器中的数据源
	public static final String DATASOURCE_NAME = "jdbcamway"; 
    
	//db2数据源名称，配置在容器中的数据源
	public static final String DB2_DATASOURCE_NAME = "jdbcDb2";
    
	//sms数据源名称，配置在容器中的数据源
	public static final String SMS_DATASOURCE_NAME = "jdbcSms"; 
    
    //email jndi
	public static final String EMAIL_JNDI_NAME = "mail/afwMailSession";
    
    //持久化单元
	public static final String JPA_DB_UNIT = "DB2PU";
    
    //当前系统信息
	public static final String SYS_INFO = "_sysInfo_";
    
    //当前用户
	public static final String USER_NAME = "_sysUser_";
	
	public static final String EXTERNAL_USER_NAME = "_externalUser_";
	
	public static final String SE_USER_ID = "${_seUserId_}";
	
	public static final String SE_EMP_NUMBER = "${_seEmpNumber_}";
	
	//当前角色
	public static final String ROLES_NAME = "_roles_";
	
	//当前应用
	public static final String APPLICATION_NAME = "_application_";
	
	//范围"查询展示"对象
	public final static String QUERY_SHOW_NAME = "_query_show_";
	
	public static final String SE_APPLICATION_ID = "${_seApplicationId_}";
	
	public static final String SE_APPLICATION_CODE = "${_seApplicationCode_}";
	
	public static final String APPLICATION_NAMES = "_applications_";
	
	public static final String CURRENT_USER_NAMES = "\\$\\{currentUser.(\\S*)\\}";
	public static final String CURRENT_APPLICATION_NAMES = "\\$\\{application.(\\S*)\\}";
	
	//当前模块
	public static final String MODULE_NAME = "_module_";
	
	public static final String P_MODULE_RIGHT_NAME = "pModuleNo";
	
	//当前模块列表
	public static final String MODULES_NAME = "_modules_";
	
	//当前模块权限列表
	public static final String RIGHTS_NAME = "_rights_";
	
	//CSS皮肤名称
	public static final String CSS_NAME = "_amwayCss_";
	
	//通用查询请求跳转前上下文
	public static final String CUR_APP_CONTEXT = "_appContext_";
	
	//临时当前应用
	public static final String CONTEXT_APP = "contextApp";
	
	//管理平台上下文
	public static final String SYS_APPLICATION_NAME = "_sysApplication_";
	
	//管理平台代码
	public static final String SYS_APPLICATION_CODE = "00";
	
	//默认CSS皮肤值
	public static final String CSS_DFT_VALUE = "basic_blue";
	
	//操作
	public static final String OPRT = "oprt";
	
	//查询操作
	public static final String QRY_OPRT = "query";
	
	//增加操作
	public static final String ADD_OPRT = "add";
	
	//删除操作
	public static final String DEL_OPRT = "delete";
	
	//修改操作
	public static final String MDF_OPRT = "modify";
	
	//初始化操作
	public static final String INIT_OPRT = "init";
	
	//分隔字符"|"
	public static final String SPILT_OPER = "\\|";
	
	public static final String SPILT_DOT_OPER = "\\.";
	
	//NO
	public static final Integer NO = 0;
	
	//YES
	public static final Integer YES = 1;
	
	//提示信息
	public static final String TIPS_LIST = "tipsList";
	
	//数据类型
	public static final String DATA_TYPE_NUMBER = "Number";
	public static final String DATA_TYPE_STRING = "String";
	public static final String DATA_TYPE_DATE = "Date";
	public static final String DATA_TYPE_LONG = "Long";
	
	//异常分隔
	public static final String EXP_SEP = "-";
	
	public static final String VER_LINE_SIGN = "|";
	
	public static final String EQUAL_SIGN = "=";
	
	public static final String UNDER_SEP = "_";
	
	public static final String UNIX_SEP = "/";
	
	public static final String UNIX_SEP2 = "//";
	
	public static final String WINDOW_SEP = "\\";
	
	public static final String WINDOW_SEP2 = "\\/";
	
	public static final String QUO_D_SIGN = "\"";
	
	public static final String QUO_S_SIGN = "'";
	
	public static final String DOT_SIGN = ".";
	
	public static final String DOU_SIGN = ",";
	
	public static final String PERCENT_SIGN = "%";
	
	public static final String DOLLOR_SIGN = "$";
	
	public static final String JING_SIGN = "#";
	
	public static final String FEN_SIGN = ";";
	
	public static final String MAO_SIGN = ":";
	
	public static final String LEFT_X_KUO = "(";
	
	public static final String RIGHT_X_KUO = ")";
	
	public static final String LEFT_Z_KUO = "[";
	
	public static final String RIGHT_Z_KUO = "]";
	
	public static final String LEFT_D_KUO = "{";
	
	public static final String RIGHT_D_KUO = "}";
	
	public static final String LEFT_J_KUO = "<";
	
	public static final String RIGHT_J_KUO = ">";
	
	public static final String ENTER_SIGN = "\n";
	
	public static final String EMPTY_STR = "";
	
	public static final String EMPTY_ONE_STR = " ";
	
	public static final String QUS_SIGN = "?";
	
	public static final String AND_SIGN = "&";
	
	public static final String XING_SIGN = "*";
	
	public static final String YES_STR = "yes";
	
	public static final String NO_STR = "no";
	
	public static final String TRUE_STR = "true";
	
	public static final String FALSE_STR = "false";
	
	public static final String _0_STR = "0";
	
	public static final String _1_STR = "1";
	
	public static final String _2_STR = "2";
	
	public static final String _3_STR = "3";
	
	public static final String _4_STR = "4";
	
	public static final String _5_STR = "5";
	
	public static final String _6_STR = "6";
	
	public static final String _7_STR = "7";
	
	public static final String _8_STR = "8";
	
	public static final String _9_STR = "9";
	
	public static final String _10_STR = "10";
	
	public static final Integer _0 = 0;
	
	public static final Integer _1 = 1;
	
	public static final Integer _2 = 2;
	
	public static final Integer _3 = 3;
	
	public static final Integer _4 = 4;
	
	public static final Integer _5 = 5;
	
	public static final Integer _6 = 6;
	
	public static final Integer _7 = 7;
	
	public static final Integer _8 = 8;
	
	public static final Integer _9 = 9;
	
	public static final Integer _10 = 10;
	
	public static final Integer _16 = 16;
	
	public static final Integer _32 = 32;
	
	public static final Integer _64 = 64;
	
	public static final Integer _128 = 128;
	
	public static final Integer _256 = 256;
	
	public static final Integer _512 = 512;
	
	public static final Integer _1024 = 1024;
	
	public static final Integer _2048 = 2048;
	
	public static final String SQL_OR = "or";
	
	public static final String SQL_AND = "and";
	
	public static final String SQL_FROM = "from";
	
	public static final String SQL_WHERE = "where";
	
	public static final String SQL_SELECT = "select";
	
	public static final String SQL_ORDERBY = "order by";
	
	public static final String SQL_GROUPBY = "group by";
	
	public static final String SQL_ORDERBY_ASC = "ASC";
	
	public static final String SQL_ORDERBY_DESC = "DESC";
	
	public static final String SQL_AS = "as";
	
	public static final String SQL_LIKE = "like";
	
	public static final String ID_MSG = "idMsg";
	
	public static final String ID = "id";
	
	public static final String NAME = "name";
	
	public static final String PROPERTY = "property";
	
	public static final String TITLE = "title";
	
	public static final String TARGET = "target";
	
	public static final String DELETE_ERROR_INFO_KEY = "common.delete.error.info";
	public static final String MODIFY_ERROR_INFO_KEY = "common.modify.error.info";
	public static final String NO_EMPTY_KEY = "common.no.empty";
	public static final String YES_INT_KEY = "common.yes.Integer";
	public static final String YES_LONG_KEY = "common.yes.long";
	
	public static final String RECORD_STATE = "recordState";
	public static final String UPDATED_TIME = "updatedTime";
	public static final String UPDATED_USER_ID = "updatedUserId";
	
	public static final String FIELD_REMARK = "REMARK";
	public static final String FIELD_STATE = "STATE";
	public static final String FIELD_USE_STATE = "USE_STATE";
	public static final String FIELD_RECORD_STATE = "RECORD_STATE";
	public static final String FIELD_CREATED_USER_ID = "CREATED_USER_ID";
	public static final String FIELD_CREATED_TIME = "CREATED_TIME";
	public static final String FIELD_UPDATED_USER_ID = "UPDATED_USER_ID";
	public static final String FIELD_UPDATED_TIME = "UPDATED_TIME";
	public static final String FIELD_APPLICATION_ID = "APPLICATION_ID";
	public static final String FIELD_APPLICATION_NAME = "APPLICATION_NAME";
	public static final String FIELD_APPLICATION_CODE = "APPLICATION_CODE";
	
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSS = "yyyyMMdd HHmmss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDD_X = "yyyy/MM/dd";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String HHMMSS = "HHmmss";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String _YYYYMM = "_yyyyMM";
	public static final Integer DATE_TIME_LEN_19 = 19;
	public static final Integer DATE_TIME_LEN_15 = 15;
	
	public static final String GBK = "GBK";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	
	public static final String OUT_FORMAT_CURRENCY_FMT = "##,###,###,###,##0.00";
	public static final String OUT_FORMAT_CURRENCY_DOLLAR_FMT = "$##,###,###,###,##0.00";
	
	public static final String OPEN_TYPE_BLANK_FMT = "_blank";
	public static final String OPEN_TYPE_PARENT_FMT = "_parent";
	public static final String OPEN_TYPE_TOP_FMT = "_top";
	public static final String OPEN_TYPE_SELF_FMT = "_self";
	
	public static final String JPG = "jpg";
	public static final String JPG_VALUE = "FFD8FF"; // JPEG (jpg)
	public static final String PNG = "png";
	public static final String PNG_VALUE = "89504E47"; // PNG (png)
	public static final String GIF = "gif";
	public static final String GIF_VALUE = "47494638"; // GIF (gif)
	public static final String TIF = "tif";
	public static final String TIF_VALUE = "49492A00"; // TIFF (tif)
	public static final String BMP = "bmp";
	public static final String BMP_VALUE = "424D"; // Windows Bitmap (bmp)
	public static final String DWG = "dwg";
	public static final String DWG_VALUE = "41433130"; // CAD (dwg)
	public static final String HTML = "html";
	public static final String HTML_VALUE = "68746D6C3E"; // HTML (html)
	public static final String RTF= "rtf";
	public static final String RTF_VALUE = "7B5C727466"; // Rich Text Format (rtf)
	public static final String XML = "xml";
	public static final String XML_VALUE = "3C3F786D6C";
	public static final String ZIP = "zip";
	public static final String ZIP_VALUE = "504B0304"; 
	public static final String RAR = "rar";
	public static final String RAR_VALUE = "52617221";
	public static final String PSD = "psd";
	public static final String PSD_VALUE = "38425053";// Photoshop (psd)
	public static final String EML = "eml";
	public static final String EML_VALUE = "44656C69766572792D646174653A";// Email
	public static final String DBX = "dbx";
	public static final String DBX_VALUE = "CFAD12FEC5FD746F";// Outlook Express (dbx)
	public static final String PST = "pst";
	public static final String PST_VALUE = "2142444E";// Outlook (pst)
	public static final String XLS = "xls";
	public static final String XLS_VALUE = "D0CF11E0";// MS Word
	public static final String DOC = "doc";
	public static final String DOC_VALUE = "D0CF11E0";// MS Excel 注意：word 和 excel的文件头一样
	public static final String MDB = "mdb";
	public static final String MDB_VALUE = "5374616E64617264204A";// MS Access (mdb)
	public static final String WPD = "wpd";
	public static final String WPD_VALUE = "FF575043";// WordPerfect (wpd)
	public static final String EPS = "eps";
	public static final String EPS_VALUE = "252150532D41646F6265";
	public static final String PS = "ps";
	public static final String PS_VALUE = "252150532D41646F6265";
	public static final String PDF = "pdf";
	public static final String PDF_VALUE = "255044462D312E";// Adobe Acrobat (pdf)
	public static final String QDF = "qdf";
	public static final String QDF_VALUE = "AC9EBD8F";// Quicken (qdf)
	public static final String PWL = "pwl";
	public static final String PWL_VALUE = "E3828596";// Windows Password (pwl)
	public static final String WAV = "wav";
	public static final String WAV_VALUE = "57415645";// Wave (wav)
	public static final String AVI = "avi";
	public static final String AVI_VALUE = "41564920";
	public static final String RAM = "ram";
	public static final String RAM_VALUE = "2E7261FD";// Real Audio (ram)
	public static final String RM = "rm";
	public static final String RM_VALUE = "2E524D46";// Real Media (rm)
	public static final String MPG = "mpg";
	public static final String MPG_VALUE = "000001BA";//     
	public static final String MOV = "mov";
	public static final String MOV_VALUE = "6D6F6F76";// Quicktime (mov)
	public static final String ASF = "asf";
	public static final String ASF_VALUE = "3026B2758E66CF11";// Windows Media (asf)
	public static final String MID = "mid";
	public static final String MID_VALUE = "4D546864";// MIDI (mid)
	
	public static final String TR_BEGIN = "<tr>";
	public static final String TR_BEGIN2 = "<tr";
	public static final String TR_END = "</tr>";
	public static final String TD_BEGIN = "<td>";
	public static final String TD_BEGIN2 = "<td";
	public static final String TD_END = "</td>";
	public static final String TAG_DIV_BEGIN = "<div";
	public static final String TAG_DIV_END = "</div>";
	public static final String TAG_TABLE_BEGIN = "<table";
	public static final String TAG_TABLE_END = "</table>";
	public static final String TAG_A_BEGIN = "<a>";
	public static final String TAG_A_END = "</a>";
	public static final String TAG_SPAN_BEGIN = "<span>";
	public static final String TAG_SPAN_BEGIN2 = "<span";
	public static final String TAG_SPAN_END = "</span>";
	public static final String CHECKBOX = "checkbox";
	public static final String RADIO = "radio";
	public static final String TAG_BEGIN = "<";
	public static final String TAG_END = "/>";
	public static final String TAG_INPUT = "input";
	public static final String TAG_INPUT_BEGIN = "<input";
	public static final String VALUE = "value";
	public static final String TYPE = "type";
	public static final String TEXT = "text";
	public static final String HIDDEN = "hidden";
	public static final String BUTTON = "button";
	public static final String SIZE = "size";
	public static final String CHECKED = "checked";
	public static final String SELECTED = "selected";
	public static final String ON_CLICK = "onclick";
	public static final String JS_BEGIN = "<script type=\"text/javascript\">";
	public static final String JS_END = "</script>";
	public static final String TAG_SELECT_BEGIN = "<select";
	public static final String TAG_SELECT_END = "</select>";
	public static final String TAG_OPTION_BEGIN = "<option";
	public static final String TAG_OPTION_END = "</option>";
	
	public static final String TAG_UL_BEGIN = "<ul>";
	public static final String TAG_UL_END = "</ul>";
	public static final String TAG_LI_BEGIN = "<li>";
	public static final String TAG_LI_END = "</li>";
	
	public static final String TAG_TEXTAREA_BEGIN = "<textarea";
	public static final String TAG_TEXTAREA_END = "</textarea>";
	
	public static final String STYLE_DISPLAY_NONE = "none";
	
	public static final String STYLE_DISPLAY_BLOCK = "block";
	
	public static final String QUERY_CODE = "queryCode";
	public static final String REPORT_CODE = "reportCode";
	public static final String REPORT_ACTION = "reportShowAction";

	public static final String CLASS_BYTE_ARR = "[B";
	public static final String CLASS_INTEGER = "java.lang.Integer";
	public static final String CLASS_LOGN = "java.lang.Long";
	public static final String CLASS_STRING = "java.lang.String";
	
	public static final String _100PERCENT = "100%";
	public static final String PX_UNIT = "px";
	
	public static final String INLINE = "inline";
	public static final String ATTACHMENT = "attachment";
	
	public static final String XLS_DOT = ".xls";
	public static final String PDF_DOT = ".pdf";

	public static final Integer RET_MAX_NUM = 1000;
	
	public static final String NULL_VALUE = null;
	
	public static final String PARAM_KEY = "param";
	public static final String REQUEST_KEY = "request";
	public static final String SESSION_KEY = "session";
	public static final String CONTEXT_KEY = "context";
	
	public static final String VAL_SEP = "$";
	public static final String GLOBAL_VAL_SEP_BEG = VAL_SEP+"{";
	public static final String GLOBAL_VAL_SEP_END = "}";
	
	
	public static final String LIMIT_NAME = "autoCompleteLimit";
	public static final String ORACLE_DATABASE_NAME = "ORACLE";
	public static final String DB2_DATABASE_NAME = "DB2";
	
	/*
	 * 黄波
	 */
	//应用内部认证
	public static final Integer AUTHENTICATE_INNER = 1;
	//应用外部认证
	public static final Integer AUTHENTICATE_EXTERNAL = 2;
	
	public static final String AUTHENTICATE_ADMIN_REMARK = "admin";
	
	public static final String AUTHENTICATE_SHOP_REMARK = "external_shop_id";
}
