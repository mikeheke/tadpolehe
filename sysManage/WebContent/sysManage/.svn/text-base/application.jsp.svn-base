<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:text name="application.page.title" /></title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
</head>
  
<body>
  <form action="${pageContext.request.contextPath}/applicationAction!${oprt }.action" 
  		method="post" onsubmit="return Validator.Validate(this,4);" >
  	  <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  	<caption>应用信息</caption>
		<tbody>
			<tr>
				<td colspan="2">
					<input type="hidden" id="applicationId" name="applicationVo.applicationId" 
						value="${retObjs[0].applicationId}">
					<div class="input_msg">${idMsg }${applicationIdMsg }${retInfo }</div>
				</td>
			</tr>
			<tr>
				<th><s:text name="application.applicationCode"></s:text>:</th>
				<td>
					<c:if test="${oprt=='add'}">
						<amway:textfield id="applicationCode" name="applicationVo.applicationCode" value="${retObjs[0].applicationCode }" dataType="Require" msg="请填写应用编码" style="width: 250px" maxLine="40"></amway:textfield>
					</c:if>
					<c:if test="${oprt!='add'}">
						<amway:textfield id="applicationCode" name="applicationVo.applicationCode" value="${retObjs[0].applicationCode }" dataType="Require" msg="请填写应用编码" readonly="readonly" style="width: 250px" maxLine="40"></amway:textfield>
					</c:if>
					<font color="red">*</font>
					<div class="input_msg">${applicationCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.applicationName"></s:text>:</th>
				<td>
					<amway:textfield id="applicationName" name="applicationVo.applicationName" value="${retObjs[0].applicationName }" dataType="Require" msg="请填写应用名称" style="width: 250px" maxLine="80"></amway:textfield>	
					<font color="red">*</font>
					<div class="input_msg">${applicationNameMsg }</div>
				</td>
			</tr>
			<tr>
				<th  align="right"><s:text name="application.departmentCode"></s:text>:</th>
				<td>
					<amway:autoComplete id="departmentCode" name="applicationVo.departmentCode" value="${retObjs[0].departmentName}|${retObjs[0].departmentCode}" minLength="2"
						source="department_list" filterColNames="code|displayname" fillColKey="displayname|code" style="width: 250px" dataType="Require" msg="请选择部门编码"></amway:autoComplete>
					<div class="input_msg">${departmentCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th  align="right"><s:text name="application.faultHandlerEmpNumber"></s:text>:</th>
				<td>
					<amway:autoComplete id="faultHandlerEmpNumber" name="applicationVo.faultHandlerEmpNumber" 
						value="${retObjs[0].empNumber}|${retObjs[0].faultHandlerEmpNumber}" source="user_profile" filterColNames="empNumber|chineseName|englishName" fillColKey="empNumber|userProfileId" 
						 style="width: 250px" dataType="Require" msg="请选择容错联系人" filterPattern="((?i)\\\v).*"></amway:autoComplete>
					<div class="input_msg">${faultHandlerEmpNumberMsg }</div>
				</td>
			</tr>
			
			<!-- 
			
				 	增加应用的认证方式(authenticateType)，并当是外部认证时(matchInnerUser)，
					给应用绑定一个用户
					增加人：黄波
					时    间：2013-11-14
				
			 -->
			<tr>
				<th  align="right"><s:text name="application.authenticateType"></s:text>:</th>
				<td>
					<amway:select id="authenticateType" name="applicationVo.authenticateType" 
						value="${retObjs[0].authenticateType }" dictCode="application_authenticateType" listKey="code" listValue="displayname" 
						defaultHK="true" dataType="Require" msg="请选择认证方式"  style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${authenticateTypeMsg }</div>
				</td>
			</tr>
			<tr>
				<th  align="right"><s:text name="application.match.inner.user"></s:text>:</th>
				<td>
					<amway:autoComplete id="matchInnerUser" name="applicationVo.matchInnerUser" dsType="4" value="${retObjs[0].matchInnerUser }"
						source="user_profile"  filterColNames="empNumber|englishName|chineseName" fillColKey="userProfileId" style="width: 250px"  ></amway:autoComplete>
					<div class="input_msg">${matchInnerUserMsg }</div>
				</td>
			</tr>
			
			<tr>
				<th align="right"><s:text name="application.ejb.auth.stateless"></s:text>:</th>
				<td>
					<amway:textfield id="ejbAuthStateless" name="applicationVo.ejbAuthStateless" value="${retObjs[0].ejbAuthStateless }"  style="width: 250px" maxLine="80"></amway:textfield>	
					<div class="input_msg">${ejbAuthStatelessMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.isCheckCode"></s:text>:</th>
				<td>
					<amway:select id="isCheckCode" name="applicationVo.isCheckCode" 
						value="${retObjs[0].isCheckCode }" dictCode="true_or_false" listKey="code" listValue="displayname" 
						defaultHK="true" dataType="Require" msg="请选择是否有校验码"  style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${isCheckCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.applicationLayout"></s:text>:</th>
				<td>
					<amway:select id="applicationLayout" name="applicationVo.applicationLayout" 
						value="${retObjs[0].applicationLayout }" dictCode="UI_Style" listKey="code" listValue="displayname" 
						defaultHK="true"  dataType="Require" msg="请选择界面风格"  style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${applicationLayoutMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.applicationStyle"></s:text>:</th>
				<td>
					<amway:select id="applicationStyle" name="applicationVo.applicationStyle" 
						value="${retObjs[0].applicationStyle }" dictCode="styleCss" listKey="code" listValue="displayname" 
						defaultHK="true"  dataType="Require" msg="请选择界面皮肤"  style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${applicationStyleMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.fixWay"></s:text>:</th>
				<td>
					<amway:textfield id="dixWay" name="applicationVo.fixWay" value="${retObjs[0].fixWay }" style="width: 250px" maxLine="128"></amway:textfield>
					<div class="input_msg">${fixWayMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.failNum"></s:text>:</th>
				<td>
					<amway:textfield id="failNum" name="applicationVo.failNum" value="${retObjs[0].failNum }" style="width: 250px" maxLine="8"></amway:textfield>
					<div class="input_msg">${failNumMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.deployServer"></s:text>:</th>
				<td>
					<amway:textfield id="deployServer" name="applicationVo.deployServer" value="${retObjs[0].deployServer }" style="width: 250px" maxLine="128"></amway:textfield>
					<div class="input_msg">${deployServerMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.port"></s:text>:</th>
				<td>
					<amway:textfield id="port" name="applicationVo.port" value="${retObjs[0].port }" style="width: 250px" maxLine="80"></amway:textfield>
					<div class="input_msg">${portMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.context"></s:text>:</th>
				<td>
					<amway:textfield id="context" name="applicationVo.context" value="${retObjs[0].context }" style="width: 250px" maxLine="80"></amway:textfield>
					<div class="input_msg">${contextMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.orderNo"></s:text>:</th>
				<td>
					<amway:textfield id="orderNo" name="applicationVo.orderNo" value="${retObjs[0].orderNo }" 
						style="width: 250px" dataType="Integer" maxLine="8"
						msg="输入的格式不正确" ></amway:textfield>
					<font color="red">*</font>
					<input type="button"  value="生成" 
						onclick="setEleValueJson('<%=basePath %>/applicationAction!findOrderNo.action','orderNo');" />
					<div class="input_msg">${orderNoMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.timeZone"></s:text>:</th>
				<td>
					<amway:select id="timeZone" name="applicationVo.timeZone" value="${retObjs[0].timeZone }" 
						dictCode="time_zone" listKey="code" listValue="displayname" 
						defaultHK="true"  dataType="Require" msg="请选择时区" style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${timeZoneMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.languange"></s:text>:</th>
				<td>
					<amway:select id="languange" name="applicationVo.languange" value="${retObjs[0].languange }" 
						dictCode="languages" listKey="code" listValue="displayname" 
						defaultHK="true"  dataType="Require" msg="请选择语言" style="width: 250px" />
					<font color="red">*</font>
					<div class="input_msg">${languangeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.state"></s:text>:</th>
				<td>
					<amway:select id="state" name="applicationVo.state" value="${retObjs[0].state }" 
						dictCode="state_options" listKey="code" listValue="displayname" 
						defaultHK="true" dataType="Require" msg="请选择状态" style="width: 250px" />
					<font color="red">*</font>
					<div class="input_msg">${stateMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.sessionTimeOut"></s:text>:</th>
				<td>
					<amway:textfield id="sessionTimeOut" name="applicationVo.sessionTimeOut" value="${retObjs[0].sessionTimeOut }" style="width: 250px" maxLine="80"></amway:textfield>
					<div class="input_msg">${sessionTimeOutMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.defaultPage"></s:text>:</th>
				<td>
					<amway:textfield id="defaultPage" name="applicationVo.defaultPage" value="${retObjs[0].defaultPage }" style="width: 250px" maxLine="500"></amway:textfield>	
					<div class="input_msg">${defaultPageMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="application.remark"></s:text>:</th>
				<td>
					<amway:textarea rows="5" cols="30" id="remark" name="applicationVo.remark" 
								value="${retObjs[0].remark }" style="width:250px" maxLine="2048" dataType="LimitB" max="2048" msg="备注长度不能超过2048个字符"></amway:textarea>
					<div class="input_msg">${remarkMsg }</div>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr  align="center">
				<td colspan="2">
					
					<input type="submit" value="<s:text name="common.save"></s:text>" 
							style="display: ${oprt=='add'?(_rights_.sysManage_application_add):(_rights_.sysManage_application_modify) }"
							/>&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
					<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
				</td>
			</tr>
		</tbody>
	  </table>
	</form>
</body>
</html>
