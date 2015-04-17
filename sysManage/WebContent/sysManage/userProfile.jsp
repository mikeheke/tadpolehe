<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="userProfile.page.title" /></title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
<body>
  	<form action="${pageContext.request.contextPath}/userProfileAction!${oprt }.action?oprt=${oprt }" 
  		method="post" onsubmit="return Validator.Validate(this,4);" >
  	  <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  <caption>用户信息</caption>
		<tbody>
			<tr>
				<td colspan="5">
					<input type="hidden" id="userProfileId" name="userProfileVo.userProfileId" value="${retObjs[0].userProfileId }"/>
					<div class="input_msg">${idMsg }${userProfileIdMsg }${retInfo }</div>
				</td>
			</tr>
			<tr >
				<th align="right"><s:text name="userProfile.accountType"></s:text>:
				</th>
				<td>
					<c:if test="${oprt=='add'}">
						<amway:select id="accountType" name="userProfileVo.accountType" value="0" 
							dictCode="account_types" listKey="code" listValue="displayname" 
							defaultHK="false" dataType="Require" msg="请选择用户类型" style="width: 250px" group=""/>
					</c:if>
					<c:if test="${oprt!='add'}">
						<amway:select id="accountType" name="userProfileVo.accountType" value="${retObjs[0].accountType }" 
							dictCode="account_types" listKey="code" listValue="displayname" 
							defaultHK="false" dataType="Require" msg="请选择用户类型" style="width: 250px" group=""/>
					</c:if>
					<div class="input_msg">${accountTypeMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.empNumber"></s:text>:
				</th>
				<td>
					<c:if test="${oprt=='add'}">
						<amway:textfield id="empNumber" name="userProfileVo.empNumber" value="${retObjs[0].empNumber }" dataType="Require" msg="请填写登录账号" style="width: 250px" maxLine="10"></amway:textfield>
					</c:if>
					<c:if test="${oprt!='add'}">
						<amway:textfield id="empNumber" name="userProfileVo.empNumber" value="${retObjs[0].empNumber }" dataType="Require" msg="请填写登录账号" readonly="readonly" style="width: 250px" maxLine="10"></amway:textfield>
					</c:if>
					
					<font color="red">*</font>
					<div class="input_msg">${empNumberMsg }</div>
				</td>
			</tr>
			<tr >
				<th align="right"><s:text name="userProfile.chineseName"></s:text>:
				</th>
				<td>
					<amway:textfield id="chineseName" name="userProfileVo.chineseName" value="${retObjs[0].chineseName }" dataType="Require" msg="请填写中文名"  style="width: 250px" maxLine="30"></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${chineseNameMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.orgUnitCode"></s:text>:
				</th>
				<td>
					<amway:autoComplete id="orgUnitCode" name="userProfileVo.orgUnitCode" value="${retObjs[0].department.deptNameCn}|${retObjs[0].department.unitCode }"  dsType="4" minLength="4"
						source="department_list" filterColNames="code|displayname" fillColKey="displayname|code" style="width: 250px" dataType="Require" msg="请选择部门编码"></amway:autoComplete>
					<font color="red">*</font>
					<div class="input_msg">${orgUnitCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text  name="userProfile.empId"></s:text>:
				</th>
				<td>
					<amway:textfield id="empId" name="userProfileVo.empId" value="${retObjs[0].empId }" style="width: 250px" maxLine="10"></amway:textfield>
					<div class="input_msg">${empIdMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.dateHired"></s:text>:
				</th>
				<td>
					<amway:date id="dateHired" name="userProfileVo.dateHired" value="${retObjs[0].dateHired }" 
						format="yyyy-MM-dd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 250px"></amway:date>
					<div class="input_msg">${dateHiredMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.dateTerminated"></s:text>:
				</th>
				<td>
					<amway:date id="dateTerminated" name="userProfileVo.dateTerminated" value="${retObjs[0].dateTerminated }" 
						format="yyyy-MM-dd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 250px"></amway:date>
					<div class="input_msg">${dateTerminatedMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.gender"></s:text>:
				</th>
				<td>
					<amway:select id="gender" name="userProfileVo.gender" value="${retObjs[0].gender }" 
						dictCode="gender_local_xml" listKey="code" listValue="displayname" 
						defaultHK="true" dataType="Require" msg="请选择性别" style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${genderMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.englishName"></s:text>:
				</th>
				<td>
					<amway:textfield id="englishName" name="userProfileVo.englishName" value="${retObjs[0].englishName }" dataType="Require" msg="请填写英文名" style="width: 250px" maxLine="30" ></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${englishNameMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.dateBirthday"></s:text>:
				</th>
				<td>
					<amway:date id="dateBirthday" name="userProfileVo.dateBirthday" value="${retObjs[0].dateBirthday }" 
						format="yyyy-MM-dd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 250px"></amway:date>
					<div class="input_msg">${dateBirthdayMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.provincialAddr"></s:text>:
				</th>
				<td>
					<amway:textfield id="provincialAddr" name="userProfileVo.provincialAddr" value="${retObjs[0].provincialAddr }" style="width: 250px" maxLine="90"></amway:textfield>
					<div class="input_msg">${provincialAddrMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.nativePlace" ></s:text>:
				</th>
				<td>
					<amway:textfield id="nativePlace" name="userProfileVo.nativePlace" value="${retObjs[0].nativePlace }" style="width: 250px" maxLine="30"></amway:textfield>
					<div class="input_msg">${nativePlaceMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.workCity"></s:text>:
				</th>
				<td>
					<amway:textfield id="workCiry" name="userProfileVo.workCity" value="${retObjs[0].workCity }" style="width: 250px" maxLine="30"></amway:textfield>
					<div class="input_msg">${workCiryMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.gradeCode"></s:text>:
				</th>
				<td>
					<amway:textfield id="gradeCode" name="userProfileVo.gradeCode" value="${retObjs[0].gradeCode }" style="width: 250px" maxLine="3"></amway:textfield>
					<div class="input_msg">${gradeCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.chinesePosition"></s:text>:
				</th>
				<td>
					<amway:textfield id="chinesePosition" name="userProfileVo.chinesePosition" value="${retObjs[0].chinesePosition }" style="width: 250px" maxLine="80"></amway:textfield>
					<div class="input_msg">${chinesePositionMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.englishPosition"></s:text>:
				</th>
				<td>
					<amway:textfield id="englishPosition" name="userProfileVo.englishPosition" value="${retObjs[0].englishPosition }" style="width: 250px" maxLine="50"></amway:textfield>
					<div class="input_msg">${englishPositionMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.workProvince"></s:text>:
				</th>
				<td>
					<amway:textfield id="workProvince" name="userProfileVo.workProvince" value="${retObjs[0].workProvince }" style="width: 250px" maxLine="70"></amway:textfield>
					<div class="input_msg">${workProvinceMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.emailAddr"></s:text>:
				</th>
				<td>
					<amway:textfield id="emailAddr" name="userProfileVo.emailAddr" value="${retObjs[0].emailAddr }" style="width: 250px" maxLine="100" 
						dataType="Custom" regexp="^(\w+([-+.]\w+)*@\w+([-.]\\w+)*\.\w+([-.]\w+)*){0,1}$" msg="邮箱格式不正确。"></amway:textfield>
					<div class="input_msg">${emailAddrMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.jobGrade"></s:text>:
				</th>
				<td>
					<amway:textfield id="jobGrade" name="userProfileVo.jobGrade" value="${retObjs[0].jobGrade }" style="width: 250px" maxLine="5"></amway:textfield>
					<div class="input_msg">${jobGradeMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.phone"></s:text>:
				</th>
				<td>
					<amway:textfield id="phone" name="userProfileVo.phone" value="${retObjs[0].phone }" style="width: 250px" maxLine="100"></amway:textfield>
					<div class="input_msg">${phoneMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.reportLine"></s:text>:
				</th>
				<td>
					<amway:textfield id="reportLine" name="userProfileVo.reportLine" value="${retObjs[0].reportLine }" style="width: 250px" maxLine="10"></amway:textfield>
					<div class="input_msg">${phoneMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.location"></s:text>:
				</th>
				<td>
					<amway:textfield id="location" name="userProfileVo.location" value="${retObjs[0].location }" style="width: 250px" maxLine="10"></amway:textfield>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="userProfile.state"></s:text>:
				</th>
				<td>
					<amway:select id="state" name="userProfileVo.state" value="${retObjs[0].state }" 
						dictCode="state_options" listKey="code" listValue="displayname" 
						defaultHK="true" dataType="Require"  msg="请选择状态"
						 style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${stateMsg }</div>
				</td>
				<td width="20">&nbsp;</td>
				<th align="right"><s:text name="userProfile.remark"></s:text>:
				</th>
				<td>
					<amway:textfield id="remark" name="userProfileVo.remark" value="${retObjs[0].remark }" style="width: 250px" maxLine="2048"></amway:textfield>
					<div class="input_msg">${remarkMsg }</div>
				</td>
			</tr>
			<tr><td colspan="5">&nbsp;</td></tr>
			<tr  align="center">
				<td colspan="5">
				<input type="submit" value="<s:text name="common.save"></s:text>" />&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
					<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
				</td>
			</tr>
  			</tbody>
  		</table>
  	</form>
</body>
</html>
