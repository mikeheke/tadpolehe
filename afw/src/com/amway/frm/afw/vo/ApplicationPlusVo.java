/**
 * 
 */
package com.amway.frm.afw.vo;


/**
 * 应用系统扩展信息表页面数据封装VO
 * @author Feng Hanhao
 * Created date: 2011-5-31
 */
public class ApplicationPlusVo {

	private String applicationPlusId;
	
	//所属应用
	private String applicationId;
	
	//通用查询提交的formID数组
	private String [] applicationPlusIds;
	
	//参数编码
	private String parameterCode;
	
	//参数名
	private String parameterName;
	
	//参数值
	private String parameterValue;
	
	//备注
	private String remark;

	public String getApplicationPlusId() {
		return applicationPlusId;
	}

	public void setApplicationPlusId(String applicationPlusId) {
		this.applicationPlusId = applicationPlusId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getApplicationPlusIds() {
		return applicationPlusIds;
	}

	public void setApplicationPlusIds(String[] applicationPlusIds) {
		this.applicationPlusIds = applicationPlusIds;
	}
}
