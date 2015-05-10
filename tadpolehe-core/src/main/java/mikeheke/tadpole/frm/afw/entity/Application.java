
package mikeheke.tadpole.frm.afw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.UniqueKey;

import org.hibernate.annotations.GenericGenerator;


/**
 * 应用系统信息表Entity
 * 
 */
@Entity(name="Application")
@Table(name="MSTB_APPLICATION", schema=AppConstant.APP_DEAULT_SCHEMA)
public class Application implements Serializable, Comparable<Application>{
	
	private static final long serialVersionUID = 2391373323940029292L;

	//自动生成ID   (手工通过uuid生成)
	@Id
	////@GenericGenerator(name="systemUUID",strategy="uuid")
	////@GeneratedValue(generator="systemUUID")
	////@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "APPLICATION_ID")
    private String applicationId;

	//应用编码
    @Column(name="APPLICATION_CODE")
    @UniqueKey
    private String applicationCode;
    
    //部门编码
    @Column(name="DEPARTMENT_CODE")
    private String departmentCode;
    
    //应用名称
    @Column(name="APPLICATION_NAME")
    private String applicationName;
    
    //容错处理人(目前下拉菜单中保存的是用户ID)
    @Column(name="FAULT_HANDLER_EMP_NUMBER")
    private String faultHandlerEmpNumber;
    
    //是否有校验码
    @Column(name="IS_CHECK_CODE")
    private Integer isCheckCode;
    
    //界面风格
    @Column(name="APPLICATION_LAYOUT")
    private Integer applicationLayout;
    
    //界面皮肤
    @Column(name="APPLICATION_STYLE")
    private String applicationStyle;
    
    //安装路径
    @Column(name="FIX_WAY")
    private String fixWay;
    
    //失败容错次数
    @Column(name="FAIL_NUM")
    private Integer failNum;
    
    //部署服务器
    @Column(name="DEPLOY_SERVER")
    private String deployServer;
    
    //端口
    @Column(name="PORT")
    private String port;
    
    //上下文根
    @Column(name="CONTEXT")
    private String context;
    
    //排序
    @Column(name="ORDER_NO")
    private Integer orderNo;
    
    //时区
    @Column(name="TIME_ZONE")
    private String timeZone;  
    
    //语言
    @Column(name="LANGUANGE")
    private String languange;
    
    //使用状态
    @Column(name="STATE")
    private Integer state;
    
    //会话时长
    @Column(name="SESSION_TIME_OUT")
    private String sessionTimeOut;
    
    //备注
    @Column(name="REMARK")
    private String remark;
    
    //默认页面
    @Column(name="DEFAULT_PAGE")
    private String defaultPage;
    
	@Column(name="RECORD_STATE")
    @UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	/*
	 * 增加应用的认证方式，并当是外部认证时，
	 * 给应用绑定一个用户
	 * 增加人：黄波
	 * 时    间：2013-11-14
	 */
	@Column(name="AUTHENTICATE_TYPE")
	private Integer authenticateType;
	
	@Column(name="INNER_MATCH_USER")
	private String matchInnerUser;
	//ejb接口映射名称
	@Column(name="EJB_AUTH_STATE_LESS")
	private String ejbAuthStateless;
	
	@Transient
	private String code;
	
	@Transient
	private String displayname;
	
	@Transient
	private String departmentName;
	
	@Transient
	private String empNumber;
	
	
	
	public Application(){}
	
//	public Application(String applicationCode){
//		this.applicationCode = applicationCode;
//	}
//	
//	public Application(String applicationId){
//		this.applicationId = applicationId;
//	}
	
	//get、set 方法......
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getFaultHandlerEmpNumber() {
		return faultHandlerEmpNumber;
	}

	public void setFaultHandlerEmpNumber(String faultHandlerEmpNumber) {
		this.faultHandlerEmpNumber = faultHandlerEmpNumber;
	}

	public Integer getIsCheckCode() {
		return isCheckCode;
	}

	public void setIsCheckCode(Integer isCheckCode) {
		this.isCheckCode = isCheckCode;
	}

	public Integer getApplicationLayout() {
		return applicationLayout;
	}

	public void setApplicationLayout(Integer applicationLayout) {
		this.applicationLayout = applicationLayout;
	}
	
	public String getLayout(){
		String layout = AfwConstant.LAYOUT_TREE_STR;
		if(null != applicationLayout){
			if(applicationLayout.intValue() == AfwConstant.LAYOUT_TREE.intValue()){
				layout = AfwConstant.LAYOUT_TREE_STR;
			}else if(applicationLayout.intValue() == AfwConstant.LAYOUT_OUTLOOK.intValue()){
				layout = AfwConstant.LAYOUT_OUTLOOK_STR;
			}else if(applicationLayout.intValue() == AfwConstant.LAYOUT_WINDOW.intValue()){
				layout = AfwConstant.LAYOUT_WINDOW_STR;
			}else if(applicationLayout.intValue() == AfwConstant.LAYOUT_BROWSER.intValue()){
				layout = AfwConstant.LAYOUT_BROWSER_STR;
			}
		}
		return layout;
	}

	public String getFixWay() {
		return fixWay;
	}

	public void setFixWay(String fixWay) {
		this.fixWay = fixWay;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public String getDeployServer() {
		return deployServer;
	}

	public void setDeployServer(String deployServer) {
		this.deployServer = deployServer;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getLanguange() {
		return languange;
	}

	public void setLanguange(String languange) {
		this.languange = languange;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSessionTimeOut() {
		return sessionTimeOut;
	}
	
	public Integer getSessionTimeOutInt() {
		if(!DataValidater.isStrInteger(sessionTimeOut)){
			return -1;
		}
		return DataConverter.stringToInteger(sessionTimeOut);
	}

	public void setSessionTimeOut(String sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}


	public String getGoToUrl(){
		//return AfwConstant.LINK_HTTP + deployServer + ":" + port + "/" + context;
		StringBuffer goToUrl = new StringBuffer();
		goToUrl.append(ContextFactory.getHttpDomain());
		goToUrl.append(AppConstant.UNIX_SEP);
		goToUrl.append(context);
		
		return goToUrl.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
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
		Application other = (Application) obj;
		if (applicationId == null) {
			if (other.applicationId != null){
				return false;
			}
		} else if (!applicationId.equals(other.applicationId)){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Application o) {
		if(null == o){
			return 0;
		}
		return orderNo.compareTo(o.getOrderNo());
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}

	public String getApplicationStyle() {
		return applicationStyle;
	}

	public void setApplicationStyle(String applicationStyle) {
		this.applicationStyle = applicationStyle;
	}

	public Integer getAuthenticateType() {
		return authenticateType;
	}

	public String getMatchInnerUser() {
		return matchInnerUser;
	}

	public void setAuthenticateType(Integer authenticateType) {
		this.authenticateType = authenticateType;
	}

	public void setMatchInnerUser(String matchInnerUser) {
		this.matchInnerUser = matchInnerUser;
	}

	public String getEjbAuthStateless() {
		return ejbAuthStateless;
	}

	public void setEjbAuthStateless(String ejbAuthStateless) {
		this.ejbAuthStateless = ejbAuthStateless;
	}

}
