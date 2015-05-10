/**
 * 
 */
package mikeheke.tadpole.frm.afw.vo;

/**
 * 
 *
 * 2011-5-10 下午03:29:43
 */
public class UserProfileVo {

	//用户ID
	private String userProfileId;
	
	//用户ID数组
	private String[] userProfileIds; 
	
	//工号
	private String empId;
	
	private String oldPassword;
	
	//密码
	private String password;
	
	//确认密码
	private String password2;
	
	//账号类型
	private String accountType;
	
	//中文名
	private String chineseName;
	
	//入职时间
	private String dateHired;
	
	//离职时间
	private String dateTerminated;
	
	//性别
	private String gender;
	
	//英文名字
	private String englishName;
	
	//生日
	private String dateBirthday;
	
	//住所
	private String provincialAddr;
	
	//籍贯
	private String nativePlace;
	
	//工作城市
	private String workCity;
	
	//职级编码
	private String gradeCode;
	
	//职位中文
	private String chinesePosition;
	
	//职位英文
	private String englishPosition;
	
	//工作省份
	private String workProvince;
	
	//部门编码
	private String[] orgUnitCode;
	
	//AD帐号
	private String empNumber;;
	
	//邮箱
	private String emailAddr;
	
	//级别
	private String jobGrade;
	
	//电话
	private String phone;
	
	//直属上司AD
	private String reportLine;
	
	//工作城市编码
	private String location;
	
	//使用状态
	private String state;
	
	//备注
	private String remark;

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String[] getUserProfileIds() {
		return userProfileIds;
	}

	public void setUserProfileIds(String[] userProfileIds) {
		this.userProfileIds = userProfileIds;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getDateHired() {
		return dateHired;
	}

	public void setDateHired(String dateHired) {
		this.dateHired = dateHired;
	}

	public String getDateTerminated() {
		return dateTerminated;
	}

	public void setDateTerminated(String dateTerminated) {
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

	public String getDateBirthday() {
		return dateBirthday;
	}

	public void setDateBirthday(String dateBirthday) {
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

	public String getWorkProvince() {
		return workProvince;
	}

	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}

	public String[] getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(String[] orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public String getEmpId() {
		return empId;
	}

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

}
