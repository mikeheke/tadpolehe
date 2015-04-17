<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
  <head>
	<title>邮件模板编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>

  <body>
    <form action="${pageContext.request.contextPath}/emailTemplateAction!${oprt }.action" 
	  	method="post" onsubmit="return Validator.Validate(this,4);">
	  <table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
	    <caption>邮件模板编辑</caption>
	    <tr>
		  <td colspan="4">
		    <input type="hidden" id="emailTemplateId" name="emailTemplateVo.emailTemplateId" 
		    	value="${retObjs[0].emailTemplateId }" />
		    <div class="input_msg">${idMsg }${emailTemplateIdMsg }${retInfo }</div>
		  </td>	 
		</tr>
		<tr>
		  <th>模板编号<font color=red >*</font>：</th>
		  <td>
		  	<c:if test="${oprt=='add'}">
		  		<amway:textfield id="templateCode" name="emailTemplateVo.templateCode" value="${retObjs[0].templateCode}" 
		  		dataType="Require" msg="请填写模板编号" maxLine="128" style="width:180px"  ></amway:textfield>
		  	</c:if>
		  	<c:if test="${oprt!='add'}">
		  		<amway:textfield id="templateCode" name="emailTemplateVo.templateCode" value="${retObjs[0].templateCode}" 
		  		dataType="Require" msg="请填写模板编号" readonly="readonly" maxLine="128" style="width:180px" ></amway:textfield>
		  	</c:if>
			<div class="input_msg">${templateCodeMsg }</div>
		  </td>
		  <th>模板名称<font color=red >*</font>：</th>
		  <td>
		  	<amway:textfield  id="templateName" name="emailTemplateVo.templateName" value="${retObjs[0].templateName}"
		  		dataType="Require" msg="请填写模板名称" maxLine="256" style="width:180px"></amway:textfield>
			<div class="input_msg">${templateNameMsg }</div>
		  </td>
		</tr>
		<tr>
		  <th>邮件模板标题<font color=red >*</font>：</th>
		  <td colspan="3">
		  	<amway:textfield id="emailTemplateSubject" name="emailTemplateVo.emailTemplateSubject" value="${retObjs[0].emailTemplateSubject}"
		  		dataType="Require" msg="请填写邮件模板标题" maxLine="256" style="width:180px"></amway:textfield>
			<div class="input_msg">${emailTemplateSubjectMsg }</div>
		  </td>
		</tr>
		<tr>
		  <th>邮件模板内容<font color=red >*</font>：</th>
		  <td colspan="3">
		  	<amway:textarea rows="7" cols="107" id="emailTemplateContent" name="emailTemplateVo.emailTemplateContent" 
		  		value="${retObjs[0].emailTemplateContent}" dataType="Require|LimitB" msg="请填写邮件模板内容" max="2048" maxLine="2048"></amway:textarea>
		    <div class="input_msg">${emailTemplateContentMsg }</div>
		  </td>
        </tr>
		<tr>
		  <th>所属应用<font color=red >*</font>：</th>
		  <td>
		  	<c:if test="${session._application_.applicationId == 1 }">
   		    <amway:select id="applicationId" name="emailTemplateVo.applicationId" 
  				dictCode="app_001" listKey="applicationId" listValue="applicationName" 
  				value="${retObjs[0].application.applicationId }" style="width:180px"
  				defaultHK="true" dataType="Require" msg="请选择所属应用"/>
  			 <div class="input_msg">${applicationIdMsg }</div>
  			 </c:if>
  			 <c:if test="${session._application_.applicationId != 1 }">
  			 		${session._application_.applicationName}
	   			   <input type="hidden" name="emailTemplateVo.applicationId"  value="${session._application_.applicationId}" />	
  			  </c:if>
   		  </td>
		  <th>状态<font color=red >*</font>：</th>
   		  <td>
   	        <amway:select id="state" name="emailTemplateVo.state" style="width:180px"
				dictCode="state_options" listKey="code" listValue="displayname" 
				value="${retObjs[0].state }" dataType="Require" msg="请选择状态" />
			<div class="input_msg">${stateMsg }</div>
   		  </td>
		</tr>
		<tr>
		  <th>发送形式：</th>
		  <td>
		  	<amway:select id="sendFlag" name="emailTemplateVo.sendFlag" style="width:180px"
				dictCode="synOrAsyn" listKey="code" listValue="displayname" 
				value="${retObjs[0].sendFlag }" />
			<div class="input_msg">${sendFlagMsg }</div>
		  </td>
		  <th>附件：</th>
		  <td>
		  	<amway:select id="accessoryFlag" name="emailTemplateVo.accessoryFlag" style="width:180px"
				dictCode="supportOrNot" listKey="code" listValue="displayname" 
				value="${retObjs[0].accessoryFlag }" />
			<div class="input_msg">${accessoryFlagMsg }</div>
		  </td>
		</tr>
		<tr>
		  <th>备注：</th>
		  <td colspan="3">
		  	<amway:textarea rows="3" cols="107" id="remark" name="emailTemplateVo.remark" value="${retObjs[0].remark}" 
		  		maxLine="2048"></amway:textarea>
		    <div class="input_msg">${remarkMsg }</div>
		  </td>
		</tr>
		<tr><td colspan="4">&nbsp;</td></tr>
	   	<tr>
	   	  <td align="center" colspan="4">
	   		<input type="submit" value="<s:text name="common.save"></s:text>"style="display: ${oprt=='add'?(_rights_.sysManage_emailTemplate_add):(_rights_.sysManage_emailTemplate_modify) }" >&nbsp;&nbsp;
			<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
	   		<input type="button" value="取消" onclick="window.close();">
	   	  </td>
	   	</tr>
	   	<tr>
	   	  <td align="left" colspan="4">
	   	      [注]带<font color=red >*</font>号为必填项
	   	  </td>
	   	</tr>
	  </table>
	</form>
  </body>
</html>
