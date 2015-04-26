package com.amway.frm.bds.util;

import com.amway.frm.base.util.AppConstant;

/**
 * 
 * @author lenovo
 *
 */
public interface BdsConstant extends AppConstant{
	
	//MSTB_BDS_XML_DATA表对应的Entity中，存在的固定列名
	public static final String FIXED_COL_NAME_CODE = "code";
	public static final String FIXED_COL_NAME_DN = "displayname";
	public static final String FIXED_COL_NAME_DN_EN = "displayname_en";
	public static final String FIXED_COL_NAME_DN_TC = "displayname_tc";
	public static final String FIXED_COL_NAME_DN_EN2 = "displaynameEn";
	public static final String FIXED_COL_NAME_DN_TC2 = "displaynameTc";
	
	//数据根元素
	public static final String XML_DATA_ROOT = "data";
	
	//数据来源类型
	public static final String SCHEMAINFO_TYPE_SQL = "sql";
	public static final String SCHEMAINFO_TYPE_WS = "ws";
	public static final String SCHEMAINFO_TYPE_LOCAL = "local";
	
	//列名
	public static final String XML_STRUCT_NAME = "name";
	//英文名称
	public static final String XML_STRUCT_EN_NAME = "enName";
	//中文名称
	public static final String XML_STRUCT_CN_NAME = "cnName";
	//字段类型
	public static final String XML_STRUCT_COL_TYPE = "colType";
	//是否必填
	public static final String XML_STRUCT_REQUIRED = "required";
	//最大长度
	public static final String XML_STRUCT_MAX_LEN = "maxLen";
	//是否唯一
	public static final String XML_STRUCT_UNIQUE = "unique";
	//关联编码
	public static final String XML_STRUCT_REF_CODE = "refCode";
	//关联字段
	public static final String XML_STRUCT_REF_COL = "refCol";
	//正则表达式
	public static final String XML_STRUCT_REGEX = "regex";
	
	//NO
	public static final String NO_CN = "否";
	//YES
	public static final String YES_CN = "是";
	
	//类型
	public static final String CHAR_TYPE = "字符";
	public static final String NUMBER_TYPE = "数字";
	public static final String DATE_TYPE = "日期";
	
	//默认连接符
	public static final String DEFAULT_JOIN_OPERATOR = "or";
	
	public static final String SCHEMA_NO_FOUND_MSG = "未找到与数据编码对应的数据服务";
	
	public static final String WS_ENVELOPE_BEGIN = "<soapenv:Envelope "
		+ " xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
		+ " xmlns:xsd=\"";
	public static final String WS_ENVELOPE_END = "</soapenv:Envelope>";
	public static final String WS_HEADER = " <soapenv:Header/>";
	public static final String WS_BODY_BEGIN = " <soapenv:Body>";
	public static final String WS_BODY_END = "</soapenv:Body>";
	public static final String WS_XSD_BEGIN = "<xsd:";
	public static final String WS_XSD_END = "</xsd:";
	public static final String WS_BODY = "body";
	
	public static final String APPLICATION_ID_KEY = "applicationIdMsg";
	public static final String APPLICATION_ID_MSG = "应用ID不允许为非数字";
	public static final String BDS_SCHEMAINFOR_CODE_KEY = "bdsSchemaInforCodeMsg";
	public static final String BDS_SCHEMAINFOR_CODE_MSG = "数据服务编码不允许为空";
	public static final String BDS_SCHEMAINFOR_NAME_KEY = "bdsSchemaInforNameEngMsg";
	public static final String BDS_SCHEMAINFOR_NAME_MSG = "数据服务编码不允许为空";
	public static final String BDS_SCHEMAINFOR_NAME_CNA_KEY = "bdsSchemaInforNameCnaMsg";
	public static final String BDS_SCHEMAINFOR_NAME_CNA_MSG = "中文名不允许为空";
	public static final String BDS_SCHEMAINFOR_TYPE_KEY = "bdsSchemaInforTypeMsg";
	public static final String BDS_SCHEMAINFOR_TYPE_MSG = "请选择数据来源";
	public static final String SQL_KEY = "sqlMsg";
	public static final String SQL_MSG = "请填写SQL语句";
	public static final String JNDI_KEY = "jndiMsg";
	public static final String JNDI_MSG = "请填写JNDI参数";
	public static final String WEBSERVICE_ADDRESS_KEY = "webserviceAddressMsg";
	public static final String WEBSERVICE_ADDRESS_MSG = "请填写WebService地址";
	public static final String WEBSERVICE_FUNCTION_KEY = "webserviceFunctionMsg";
	public static final String WEBSERVICE_FUNCTION_MSG = "请填写WebService方法";
	public static final String DATA_STRUCTURE_XML_KEY = "dataStructureXmlMsg";
	public static final String DATA_STRUCTURE_XML_MSG = "xml文件格式不合法";
	public static final String STATE_KEY = "stateMsg";
	public static final String STATE_MSG = "状态参数不允许为非数字";
	
	public static final String BDS_XML_DATA_ID = "BDS_XML_DATA_ID";
	public static final String BDS_SCHEMAINFOR_ID = "BDS_SCHEMAINFOR_ID";
	public static final String BDS_SCHEMAINFOR_CODE = "BDS_SCHEMAINFOR_CODE";
	public static final String BDS_SCHEMAINFOR_NAME_ENG = "BDS_SCHEMAINFOR_NAME_ENG";
	public static final String BDS_SCHEMAINFOR_NAME_CNA = "BDS_SCHEMAINFOR_NAME_CNA";
	public static final String BDS_SCHEMAINFOR_TYPE = "BDS_SCHEMAINFOR_TYPE";
	//public static final String SQL = "SQL";
	public static final String SQL = "BDS_SQL";
	public static final String JNDI = "JNDI";
	public static final String WEBSERVICE_ADDRESS = "WEBSERVICE_ADDRESS";
	public static final String WEBSERVICE_FUNCTION = "WEBSERVICE_FUNCTION";
	public static final String WEBSERVICE_USER = "WEBSERVICE_USER";
	public static final String WEBSERVICE_PWD = "WEBSERVICE_PWD";
	public static final String XML = "xml";
	public static final String XML_TYPE_DATA = "xmltypeData";
	
	public static final String REQUIRED = "required";
	public static final String REQUIRED_MSG = "requiredMsg";
	public static final String COL_TYPE = "colType";
	public static final String COL_TYPE_MSG = "colTypeMsg";
	public static final String REGEX = "regex";
	public static final String REGEX_MSG = "regexMsg";
	public static final String CN_NAME = "cnName";
	public static final String EN_NAME = "enName";
	public static final String MAX_LEN = "maxLen";
	public static final String REF_CODE = "refCode";
	public static final String REF_COL = "refCol";
	public static final String UNIQUE = "unique";
	
	public static final String CODE = "CODE";
	public static final String DISPLAYNAME = "DISPLAYNAME";
	public static final String DISPLAYNAME_EN = "DISPLAYNAME_EN";
	public static final String DISPLAYNAME_TC = "DISPLAYNAME_TC";

}
