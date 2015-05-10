<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="blackList.page.title" /></title> 
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		window.name="myModalDialog";
	</script>
</head>
  
<body>
  <form action="${pageContext.request.contextPath}/blackListAction!${oprt }.action?oprt=${oprt }" 
  		method="post" onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  	  <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  	<caption>黑名单信息</caption>
		<tbody>
			<tr>
				<td colspan="2">
					<input type="hidden" name="blackListVo.blackListId" value="${retObjs[0].blackListId}">
					<div class="input_msg">${idMsg }${blackListIdMsg }${retInfo }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="blackList.applicationId"></s:text>:
				</th>
				<td>
					<amway:select id="applicationId" name="blackListVo.applicationId" 
  								dictCode="app_001" listKey="applicationId" listValue="applicationName" 
  								value="${retObjs[0].application.applicationId }" 
  								defaultHK="true" dataType="Require" msg="请选择所属应用" style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${applicationIdMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="blackList.userProfileId"></s:text>:
				</th>
				<td>
					<amway:autoComplete id="userProfileId" name="blackListVo.userProfileId" dsType="4" value="${retObjs[0].userProfile.chineseName }"
						source="user_profile" filterColNames="empNumber|englishName|chineseName" fillColKey="chineseName|userProfileId" style="width: 250px" dataType="Require" msg="请填写用户姓名" ></amway:autoComplete>
					<font color="red">*</font>
					<div class="input_msg">${userProfileIdMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="blackList.remark"></s:text>:
				</th>
				<td>
					<amway:textarea rows="5" cols="40" id="remark" name="blackListVo.remark" value="${retObjs[0].remark }"
						style="width:250px" maxLine="255" dataType="LimitB" max="255" msg="备注长度不能超过255个字符"></amway:textarea>
					<div class="input_msg">${remarkMsg }</div>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr  align="center">
				<td colspan="2">
					<input type="submit" value="<s:text name="common.save"></s:text>" style="display: ${_rights_.sysManage_application_add}"/>&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
					<input type="button" value="<s:text name="common.close"></s:text>" onclick="window.close();"/>
				</td>
			</tr>
		</tbody>
		</table>
	</form>
</body>
</html>
