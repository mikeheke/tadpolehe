package com.amway.frm.afw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.UniqueKey;

/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：用户
 */
@Entity(name="UserProfile")
@Table(name="MSTB_USERPROFILE", schema=AppConstant.APP_DEAULT_SCHEMA)
public class UserProfile  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5520987270142122440L;

	//用户ID
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MSSQ_USERPROFILE_OR")
	@SequenceGenerator(name="MSSQ_USERPROFILE_OR", sequenceName="MSSQ_USERPROFILE", 
			initialValue=1, allocationSize=1)
	@Column(name="USERPROFILE_ID")
	private Long userProfileId;
	
	//工号
	@Column(name="EMP_ID")
	private String empId;
	
	//密码
	@Column(name="PASSWORD")
	private String password;
	
	//账号类型
	@Column(name="ACCOUNT_TYPE")
	private Integer accountType;
	
	//中文名
	@Column(name="CHINESE_NAME")
	private String chineseName;
	
	//入职时间
	@Column(name="DATE_HIRED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateHired;
	
	//离职时间
	@Column(name="DATE_TERMINATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTerminated;
	
	//性别
	@Column(name="GENDER")
	private String gender;
	
	//英文名字
	@Column(name="ENGLISH_NAME")
	private String englishName;
	
	//生日
	@Column(name="DATE_BIRTHDAY")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBirthday;
	
	//住所
	@Column(name="PROVINCIAL_ADDR")
	private String provincialAddr;
	
	//籍贯
	@Column(name="NATIVE_PLACE")
	private String nativePlace;
	
	//工作城市
	@Column(name="WORK_CITY")
	private String workCity;
	
	//职级编码
	@Column(name="GRADE_CODE")
	private String gradeCode;
	
	//职位中文
	@Column(name="CHINESE_POSITION")
	private String chinesePosition;
	
	//职位英文
	@Column(name="ENGLISH_POSITION")
	private String englishPosition;
	
	//工作省份
	@Column(name="WORK_PROVINCE")
	private String workProvince;
	
	//部门编码
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_UNIT_CODE",referencedColumnName="UNIT_CODE")
	private Department department;
	
	//AD帐号
	@Column(name="EMP_NUMBER")
	@UniqueKey
	private String empNumber;
	
	//邮箱
	@Column(name="EMAIL_ADDR")
	private String emailAddr;
	
	//级别
	@Column(name="JOB_GRADE")
	private String jobGrade;
	
	//电话
	@Column(name="PHONE")
	private String phone;
	
	//直属上司AD
	@Column(name="REPORT_LINE")
	private String reportLine;
	
	//工作城市编码
	@Column(name="LOCATION")
	private String location;

	//备注
	@Column(name="REMARK")
	private String remark;
	
	//使用状态
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="SESSION_ID")
	private String sessionId;
	
	@Column(name="LAST_LOGIN_TIME")
	private Date lastLoginTime;
	
	@Column(name="LAST_LOGIN_IP")
	private String lastLoginIp;
	
	@Column(name="FAIL_NUM")
	private Integer failNum;
	
	@Column(name="LOGIN_TIME")
	private Date loginTime;
	
	@Column(name="LOGIN_IP")
	private String loginIp;
	
	@Column(name="LOGIN_NUM")
	private Integer loginNum;
	
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

	/*
	 * 为第三方认证增加店铺ID
	 * 增加人：黄波
	 * 时    间：2013-11-14
	 */
	@Transient
	private String shopId;
	@Transient
	private String externalUserName;
	
	public UserProfile(){}
	
	public UserProfile(Long userProfileId){
		this.userProfileId = userProfileId;
	}
	
	public UserProfile(String empId){
		this.empId = empId;
	}
	
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Date getDateHired() {
		return dateHired;
	}

	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	public Date getDateTerminated() {
		return dateTerminated;
	}

	public void setDateTerminated(Date dateTerminated) {
		this.dateTerminated = dateTerminated;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Date getDateBirthday() {
		return dateBirthday;
	}

	public void setDateBirthday(Date dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	public String getProvincialAddr() {
		return provincialAddr;
	}

	public void setProvincialAddr(String provincialAddr) {
		this.provincialAddr = provincialAddr;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}



	public String getWorkProvince() {
		return workProvince;
	}

	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}
	/**
	 * 工号
	 * @return
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * 工号
	 * @param empId
	 * @return
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReportLine() {
		return reportLine;
	}

	public void setReportLine(String reportLine) {
		this.reportLine = reportLine;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
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

	public String getChinesePosition() {
		return chinesePosition;
	}

	public void setChinesePosition(String chinesePosition) {
		this.chinesePosition = chinesePosition;
	}

	public String getEnglishPosition() {
		return englishPosition;
	}

	public void setEnglishPosition(String englishPosition) {
		this.englishPosition = englishPosition;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getLoginNum() {
		if(loginNum == null){
			return 0;
		}
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getExternalUserName() {
		return externalUserName;
	}

	public void setExternalUserName(String externalUserName) {
		this.externalUserName = externalUserName;
	}

}
