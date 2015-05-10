<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="applicationPlus.page.title" /></title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

</head>
  
<body>
  <form action="${pageContext.request.contextPath}/applicationPlusAction!${oprt }.action?oprt=${oprt }" 
  		method="post" onsubmit="return Validator.Validate(this,4);" >
  	  <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  	<caption>扩展应用信息</caption>
		<tbody>
			<tr>
				<td colspan="2">
					<input type="hidden" id="applicationPlusId" name="applicationPlusVo.applicationPlusId" 
						value="${retObjs[0].applicationPlusId}">
					<input type="hidden" id="applicationId" name="applicationVo.applicationId" 
						value="${retObjs[0].application.applicationId}">
					<div class="input_msg">${applicationPlusIdMsg }${applicationIdMsg }${retInfo }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="applicationPlus.applicationCode"></s:text>:
				</th>
				<td>
					<amway:textfield id="applicationCode" name="applicationVo.applicationCode" value="${retObjs[0].application.applicationCode }" group="readonly" style="width:250px"></amway:textfield>
					<div class="input_msg">${applicationCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="applicationPlus.applicationName"></s:text>:
				</th>
				<td>
					<amway:textfield id="applicationName" name="applicationVo.applicationName" value="${retObjs[0].application.applicationName }" group="readonly" style="width:250px"></amway:textfield>
					<div class="input_msg">${applicationNameMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="applicationPlus.parameterCode"></s:text>:
				</th>
				<td>
					<amway:textfield id="parameterCode" name="applicationPlusVo.parameterCode" value="${retObjs[0].parameterCode }"
						dataType="Require" msg="请填写参数编码" maxLine="128" style="width:250"></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${parameterCodeMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="applicationPlus.parameterName"></s:text>:
				</th>
				<td>
					<amway:textfield id="parameterName" name="applicationPlusVo.parameterName" value="${retObjs[0].parameterName }"
						dataType="Require" msg="请填写参数名称" maxLine="256" style="width:250"></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${parameterNameMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="applicationPlus.parameterValue"></s:text>:
				</th>
				<td>
					<amway:textfield id="parameterValue" name="applicationPlusVo.parameterValue" value="${retObjs[0].parameterValue }"
						dataType="Require" msg="请填写参数值" maxLine="256" style="width:250"></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${parameterValueMsg }</div>
				</td>
			</tr>
			<tr>
				<th align="right"><s:text name="common.remark"></s:text>:
				</th>
				<td>
					<amway:textarea id="remark" name="applicationPlusVo.remark" value="${retObjs[0].remark }" dataType="LimitB" max="20" msg="备注长度不能超过2048个字符"
					 	rows="5" cols="40" maxLine="2048" style="width:250"></amway:textarea>
					<div class="input_msg">${remarkMsg }</div>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr  align="center">
				<td colspan="2">
					<input type="submit" value="<s:text name="common.save"></s:text>" 
							style="display: ${oprt=='add'?(_rights_.sysManage_applicationPlus_add):(_rights_.sysManage_applicationPlus_modify) }"
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
