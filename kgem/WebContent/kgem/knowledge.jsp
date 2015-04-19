<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <%-- 
    <title><s:text name="role.page.title" /></title>
     --%>
    <title>知识信息页面</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

</head>
  
<body>
  <form action="${pageContext.request.contextPath}/knowledgeAction!${oprt }.action?oprt=${oprt }" 
  		method="post" onsubmit="return Validator.Validate(this,4);" >
  	  <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  	<caption>
  	  		知识信息
  	  		<c:if test="${oprt=='add'}">-新增</c:if>
  	  		<c:if test="${oprt=='modify'}">-修改</c:if>
  	  	</caption>
		<tbody>
		
			<!-- ************************** input start ************************** -->
			<%-- 
			<tr>
				<td colspan="2">
					<input type="hidden" id="roleId" name="roleVo.roleId" value="${retObjs[0].roleId}">
					<div class="input_msg">${idMsg }${roleIdMsg }${retInfo }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="role.roleCode"></s:text>:
				</th>
				<td>
					<c:if test="${oprt=='add'}">
						<amway:textfield id="roleCode" name="roleVo.roleCode" value="${retObjs[0].roleCode }" dataType="Require" msg="请填写角色编码" style="width: 250px" maxLine="128"></amway:textfield>
					</c:if>
					<c:if test="${oprt!='add'}">
						<amway:textfield id="roleCode" name="roleVo.roleCode" value="${retObjs[0].roleCode }" dataType="Require" msg="请填写角色编码" readonly="readonly" style="width: 250px" maxLine="128"></amway:textfield>
					</c:if>
					<font color="red">*</font>
					<div class="input_msg">${roleCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="role.roleName"></s:text>:
				</th>
				<td>
					<amway:textfield id="roleName" name="roleVo.roleName" value="${retObjs[0].roleName }" dataType="Require" msg="请填写角色名称" style="width: 250px" maxLine="256" ></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${roleNameMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="role.application"></s:text>:</th>
				<td>
				<c:if test="${session._application_.applicationId == 1 }">
 					<amway:select id="applicationId" name="roleVo.applicationId" 
 						dictCode="app_001" listKey="applicationId" listValue="applicationName" 
 						value="${retObjs[0].application.applicationId }" 
 						defaultHK="true" dataType="Require" msg="请选择所属应用" style="width: 250px"/>
 					<font color="red">*</font>
 					<div class="input_msg">${applicationIdMsg }</div>
 				</c:if>
 				<c:if test="${session._application_.applicationId != 1 }">
						${session._application_.applicationName}
	   				<input type="hidden" name="roleVo.applicationId"  value="${session._application_.applicationId}" />
							
				</c:if>
 				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="role.state"></s:text>:
				</th>
				<td>
					<amway:select id="state" name="roleVo.state" value="${retObjs[0].state }" 
						dictCode="state_options" listKey="code" listValue="displayname" 
						defaultHK="true" dataType="Require" msg="请选择状态" style="width: 250px"/>
					<font color="red">*</font>
					<div class="input_msg">${stateMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="role.userSql"></s:text>:
				</th>
				<td>
					<amway:autoComplete id="bdsCode" name="roleVo.bdsCode" 
						dsType="4" source="default_role_data_source" filterColNames="code|displayname" fillColKey="code" 
						minLength="1" size="35" value="${retObjs[0].userSql }" style="width: 250px"/>
					<div class="input_msg">${bdsCodeMsg }</div>
  				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="role.remark"></s:text>:
				</th>
				<td>
					<amway:textarea rows="5" cols="40" id="remark" name="roleVo.remark" value="${retObjs[0].remark }"
									style="width:250px" maxLine="255" dataType="LimitB" max="255" msg="备注长度不能超过255个字符"></amway:textarea>
					<div class="input_msg">${remarkMsg }</div>
				</td>
			</tr>
			--%>
			
			
			
			
			<!-- id,msg info -->
			<tr>
				<td colspan="2">
					<input type="hidden" id="knowledgeId" name="knowledgeVo.knowledgeId" value="${retObjs[0].knowledgeId}">
					<div class="input_msg">${idMsg }${knowledgeIdMsg }${retInfo }</div>
				</td>
			</tr>
			<!-- input items -->
			<tr>
				<th align="right">标题:</th>
				<td>
					<!-- dataType="Require" -->
					<amway:textfield id="title" name="knowledgeVo.title" value="${retObjs[0].title }"  msg="请填写知识标题" style="width: 450px" maxLine="128"></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${titleMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right">内容:
				</th>
				<td>
					<amway:textarea rows="5" cols="40" id="content" name="knowledgeVo.content" value="${retObjs[0].content }"
									style="width:450px" maxLine="255" dataType="LimitB" max="255" msg="内容长度不能超过255个字符"></amway:textarea>
					<div class="input_msg">${contentMsg }</div>
				</td>
			</tr>
			
			<!-- ************************** input end ************************** -->
			
			
			
			<!-- ************************** operation button start ************************** -->
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr  align="center">
				<td colspan="2">
					<input type="submit" value="<s:text name="common.save"></s:text>" />&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
					<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
				</td>
			</tr>
			<!-- ************************** operation button end ************************** -->
			
		</tbody>
	  </table>
	</form>
</body>
</html>
