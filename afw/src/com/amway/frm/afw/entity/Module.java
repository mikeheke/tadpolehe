package com.amway.frm.afw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.UniqueKey;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：模块
 */
@Entity(name="Module")
@Table(name="MSTB_MODULE", schema=AppConstant.APP_DEAULT_SCHEMA)
public class Module implements Serializable, Comparable<Module>{
	
	private static final long serialVersionUID = -5580976048646042424L;

	public Module(){}
	
	//模块ID
	@Id
	@Column(name="MODULE_ID")
	private String moduleId;
	
	//模块代码
	@Column(name="MODULE_CODE")
	@UniqueKey
	private String moduleCode;
	
	//模块名称
	@Column(name="MODULE_NAME")
	private String moduleName;

	//是否权限或模块
	@Column(name="IS_MODULE_OR_MENU")
	private Integer isModuleOrButton;
	
	//应用ID
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	@UniqueKey
	private Application application;
	
	//图标
	@Column(name="ICO")
	private String ico;
	
	//链接
	@Column(name="LINK")
	private String link;
	
	//排序
	@Column(name="ORDER_NO")
	private String orderNo;
	
	//是否种子
	@Column(name="IS_SEED")
	private Integer isSeed;
	
	//打开方式
	@Column(name="OPEN_TYPE")
	private Integer openType;
	
	//级别
	@Column(name="LEVEL_NO")
	private Integer levelNo;
	
	//目标帧
	@Column(name="TARGET")
	private String target;
	
	//备注
	@Column(name="REMARK")
	private String remark;
	
	//使用状态
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="RECORD_STATE")
	@UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	//父模块
	@ManyToOne()
	@JoinColumn(name="PARENT_MODULE_ID")
	private Module parentModule;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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

	
	public Integer getIsModuleOrButton() {
		return isModuleOrButton;
	}

	public void setIsModuleOrButton(Integer isModuleOrButton) {
		this.isModuleOrButton = isModuleOrButton;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getLink() {
		return DataConverter.valueOf(link).split(AfwConstant.SPILT_OPER_RU)[0];
	}
	
	public String getLink2() {
		return link;
	}
	
	public String[] getModuleUrl(){
		
		String[] moduleUrlArr = DataConverter.valueOf(link).split(
				AfwConstant.SPILT_OPER_RU);
			
		int len = moduleUrlArr.length;
		String[] moduleUrlRet = new String[len];
		for(int i=0; i<len; i++){
			String moduleUrl = moduleUrlArr[i];
			if(!moduleUrl.toLowerCase().startsWith(AfwConstant.LINK_HTTP)){
				//moduleUrl = AfwConstant.LINK_HTTP + getApplication().getDeployServer() 
				//+ ":" + getApplication().getPort()
				//+ "/" + getApplication().getContext() + "/"+ moduleUrl;
				StringBuffer moduleUrlTmp = new StringBuffer();
				moduleUrlTmp.append(AfwConstant.UNIX_SEP).append(getApplication().getContext());
				moduleUrlTmp.append(AfwConstant.UNIX_SEP).append(moduleUrl);
				moduleUrl = moduleUrlTmp.toString();
			}
			moduleUrlRet[i] = moduleUrl;
		}
		
		
		return moduleUrlRet;
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

	public Integer getIsSeed() {
		return isSeed;
	}

	public void setIsSeed(Integer isSeed) {
		this.isSeed = isSeed;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public Integer getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(Integer levelNo) {
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


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Module getParentModule() {
		return parentModule;
	}

	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((moduleCode == null) ? 0 : moduleCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (DataValidater.isFalse(getClass().equals(obj.getClass()))){
			return false;
		}
		Module other = (Module) obj;
		if (moduleCode == null) {
			if (other.moduleCode != null){
				return false;
			}
		} else if (!moduleCode.equals(other.moduleCode)){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Module o) {
		if(null == o){
			return 0;
		}
		Integer orderNo = Integer.parseInt(this.getOrderNo());
		Integer orderNoOld = Integer.parseInt(o.getOrderNo());
		return orderNo.compareTo(orderNoOld);
	}
}
