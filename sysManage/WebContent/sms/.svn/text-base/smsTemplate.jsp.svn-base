<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <title>短信模板编辑</title>
	
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/smsTemplateAction!${oprt }.action" 
	  	method="post" onsubmit="return Validator.Validate(this,4);">
	  <table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
        <caption>短信模板编辑</caption>
	    <tr>
		  <td colspan="4">
		    <input type="hidden" id="smsTemplateId" name="smsTemplateVo.smsTemplateId" 
		    	value="${retObjs[0].smsTemplateId }" />
		    <div class="input_msg">${idMsg }${smsTemplateIdMsg }${retInfo }</div>
		  </td>	 
		</tr>
        <tr>
          <td>模板编号<font color=red >*</font>：</td>
          <td colspan="3">
          	<amway:textfield id="templateCode" name="smsTemplateVo.templateCode" value="${retObjs[0].templateCode}"
          		dataType="Require" maxLine="128" style="width:180px" msg="请填写模板编号"></amway:textfield>
            <div class="input_msg">${templateCodeMsg }</div>
          </td>
        </tr>
        <tr>
          <td>短信模板内容<font color=red >*</font>：</td>
          <td colspan="3">
          	<amway:textarea rows="7" cols="106" id="smsTemplateContent" name="smsTemplateVo.smsTemplateContent"
          		value="${retObjs[0].smsTemplateContent}" dataType="Require|LimitB" max="256" msg="请填写模板内容"></amway:textarea>
            <div class="input_msg">${smsTemplateContentMsg }</div>
          </td>
        </tr>
        <tr>
          <td>所属应用<font color=red >*</font>：</td>
          <td>
          <c:if test="${session._application_.applicationId == 1 }">
   		    <amway:select id="applicationId" name="smsTemplateVo.applicationId" 
  				dictCode="app_001" listKey="applicationId" listValue="applicationName" 
  				value="${retObjs[0].application.applicationId }" style="width:180px"
  				defaultHK="true" dataType="Require" msg="请选择所属应用"/>
  			 <div class="input_msg">${applicationIdMsg }</div>
  			 </c:if>
  			 <c:if test="${session._application_.applicationId != 1 }">
  			 	  			 		${session._application_.applicationName}
	   			   <input type="hidden" name="smsTemplateVo.applicationId"  value="${session._application_.applicationId}" />	
  			 </c:if>
   		  </td>
          <th>状态<font color=red >*</font>：</th>
   		  <td>
   	        <amway:select id="state" name="smsTemplateVo.state" style="width:180px"
				dictCode="state_options" listKey="code" listValue="displayname" dataType="Require"
				value="${retObjs[0].state }"  msg="请选择状态" />
			<div class="input_msg">${stateMsg }</div>
   		  </td>
        </tr>
        <tr>
          <th>备注：</th>
		  <td colspan="3">
			<amway:textarea rows="3" cols="106" id="remark" name="smsTemplateVo.remark" value="${retObjs[0].remark}"
				dataType="LimitB" max="2048" msg="备注长度不能超过2048个字符"></amway:textarea>		  
		    <div class="input_msg">${remarkMsg }</div>
		  </td>
        </tr>
        <tr><td colspan="4">&nbsp;</td></tr>
	   	<tr>
	   	  <td align="center" colspan="4">
	   		<input type="submit" value="<s:text name="common.save"></s:text>"style="display: ${oprt=='add'?(_rights_.sysManage_smsTemplate_add):(_rights_.sysManage_smsTemplate_modify) }" >&nbsp;&nbsp;
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
