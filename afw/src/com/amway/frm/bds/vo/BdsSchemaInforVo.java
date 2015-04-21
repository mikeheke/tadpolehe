/**
 * 
 */
package com.amway.frm.bds.vo;



/**
 *  baseDataServicesDefine.jsp 页面表单封装对象
 *  
 */
public class BdsSchemaInforVo {
	
	//id时使用
	private String bdsSchemaInforId;
	
	//id组
	private String[] bdsSchemaInforIds;	

	//应用代码
	private String applicationId;
	
	//数据服务编码
	private String bdsSchemaInforCode;
	
	//英文名称
	private String bdsSchemaInforNameEng;
	
	//中文名称
	private String bdsSchemaInforNameCna;
	
	//数据来源类型
	private String bdsSchemaInforType;
	
	//SQL
	private String sql;
	
	//JNDI
	private String jndi;
	
	//WebService地址
	private String webserviceAddress;
	
	//WebService方法
	private String webserviceFunction;
	
	//WebService命名空间
	private String webserviceNamespace;
	
	//WebService请求根节点
	private String webserviceReqRoot;
	
	//WebService应答根节点
	private String webserviceResRoot;
	
	//认证名
	private String webserviceUser;
	
	//密码
	private String webservicePwd;
	
	//数据结构xml
	private String dataStructureXml;
	
	//使用状态
	private String state;
	
	//备注
	private String remark;

	public String getBdsSchemaInforId() {
		return bdsSchemaInforId;
	}

	public void setBdsSchemaInforId(String bdsSchemaInforId) {
		this.bdsSchemaInforId = bdsSchemaInforId;
	}

	public String[] getBdsSchemaInforIds() {
		return bdsSchemaInforIds;
	}

	public void setBdsSchemaInforIds(String[] bdsSchemaInforIds) {
		this.bdsSchemaInforIds = bdsSchemaInforIds;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getBdsSchemaInforCode() {
		return bdsSchemaInforCode;
	}

	public void setBdsSchemaInforCode(String bdsSchemaInforCode) {
		this.bdsSchemaInforCode = bdsSchemaInforCode;
	}

	public String getBdsSchemaInforNameEng() {
		return bdsSchemaInforNameEng;
	}

	public void setBdsSchemaInforNameEng(String bdsSchemaInforNameEng) {
		this.bdsSchemaInforNameEng = bdsSchemaInforNameEng;
	}

	public String getBdsSchemaInforNameCna() {
		return bdsSchemaInforNameCna;
	}

	public void setBdsSchemaInforNameCna(String bdsSchemaInforNameCna) {
		this.bdsSchemaInforNameCna = bdsSchemaInforNameCna;
	}

	public String getBdsSchemaInforType() {
		return bdsSchemaInforType;
	}

	public void setBdsSchemaInforType(String bdsSchemaInforType) {
		this.bdsSchemaInforType = bdsSchemaInforType;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getJndi() {
		return jndi;
	}

	public void setJndi(String jndi) {
		this.jndi = jndi;
	}

	public String getWebserviceAddress() {
		return webserviceAddress;
	}

	public void setWebserviceAddress(String webserviceAddress) {
		this.webserviceAddress = webserviceAddress;
	}

	public String getWebserviceFunction() {
		return webserviceFunction;
	}

	public void setWebserviceFunction(String webserviceFunction) {
		this.webserviceFunction = webserviceFunction;
	}

	public String getWebserviceNamespace() {
		return webserviceNamespace;
	}

	public void setWebserviceNamespace(String webserviceNamespace) {
		this.webserviceNamespace = webserviceNamespace;
	}

	public String getWebserviceReqRoot() {
		return webserviceReqRoot;
	}

	public void setWebserviceReqRoot(String webserviceReqRoot) {
		this.webserviceReqRoot = webserviceReqRoot;
	}

	public String getWebserviceResRoot() {
		return webserviceResRoot;
	}

	public void setWebserviceResRoot(String webserviceResRoot) {
		this.webserviceResRoot = webserviceResRoot;
	}

	public String getWebserviceUser() {
		return webserviceUser;
	}

	public void setWebserviceUser(String webserviceUser) {
		this.webserviceUser = webserviceUser;
	}

	public String getWebservicePwd() {
		return webservicePwd;
	}

	public void setWebservicePwd(String webservicePwd) {
		this.webservicePwd = webservicePwd;
	}

	public String getDataStructureXml() {
		return dataStructureXml;
	}

	public void setDataStructureXml(String dataStructureXml) {
		this.dataStructureXml = dataStructureXml;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
