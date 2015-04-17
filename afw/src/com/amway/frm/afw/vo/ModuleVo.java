/**
 * 
 */
package com.amway.frm.afw.vo;

/**
 * @author huangweijin
 *
 * 2011-5-13 下午01:54:38
 */
public class ModuleVo {

	//指定增加的模块等级（当前级：0、下一级：1）
	private String addLevel;
	//模块ID
	private String moduleId;
	
	//模块ID数组
	private String[] moduleIds;
	
	//模块代码
	private String moduleCode;
	
	//模块名称
	private String moduleName;
	
	//父模块Code
	private String parentModuleCode;
	
	//是否模块或按钮
	private String isModuleOrButton;
	
	//应用ID
	private String applicationId;
	
	//图标
	private String ico;
	
	//链接
	private String link;
	
	//排序
	private String orderNo;
	
	//是否子模块
	private String isSeed;
	
	//打开方式
	private String openType;
	
	//级别
	private String levelNo;
	
	//目标帧
	private String target;
	
	//备注
	private String remark;
	
	//使用状态
	private String state;

	

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String[] getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String[] moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}



	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getIsModuleOrButton() {
		return isModuleOrButton;
	}

	public void setIsModuleOrButton(String isModuleOrButton) {
		this.isModuleOrButton = isModuleOrButton;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsSeed() {
		return isSeed;
	}

	public void setIsSeed(String isSeed) {
		this.isSeed = isSeed;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddLevel() {
		return addLevel;
	}

	public void setAddLevel(String addLevel) {
		this.addLevel = addLevel;
	}

	public String getParentModuleCode() {
		return parentModuleCode;
	}

	public void setParentModuleCode(String parentModuleCode) {
		this.parentModuleCode = parentModuleCode;
	}

}
