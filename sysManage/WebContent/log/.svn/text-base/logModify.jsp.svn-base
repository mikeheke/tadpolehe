<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>日志配置编辑</title>
    
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
  	<form action="${pageContext.request.contextPath}/logAction!modify.action" method="post" 
  		onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  				<caption>日志配置编辑</caption>
  				<tr>
					<td colspan="2">
						<div class="input_msg">${retInfo }</div>
					</td>
				</tr>
  				<tr>
  					<th>应用名称<font color="red">*</font>：</th>
  					<td>
  						<amway:textfield id="applicationName" name="logVo.applicationName" 
  							value="${logVo.applicationName}" readonly="readonly" style="width:250px"/>
  						<input type="hidden" id="fixWay" name="logVo.fixWay" value="${logVo.fixWay }"/>
  						<div class="input_msg">${applicationNameMsg }</div>
  					</td>
  				</tr>
  				<tr>
  					<th>日志级别<font color="red">*</font>：</th>
  					<td>
  						<amway:select id="logLevel" name="logVo.logLevel" value="${logVo.logLevel }" 
							dictCode="log_level" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="日志级别不能为空" style="width: 250px" />
  						<div class="input_msg">${logLevelMsg }</div>
  					</td>
  				</tr>
  				<tr>
  					<th>日志文件地址<font color="red">*</font>：</th>
  					<td>
  						<amway:textfield id="logFileAdr" name="logVo.logFileAdr" value="${logVo.logFileAdr }"
  							dataType="Require" msg="日志文件地址不能为空" style="width:250px"/>
  						<div class="input_msg">${logFileAdrMsg }</div>
  					</td>
  				</tr>
  				<tr><td colspan="2">&nbsp;</td></tr>
				<tr  align="center">
  					<td colspan="2" align="center">
  						<input type="submit" value="保存"/>&nbsp;&nbsp;
  						<input type="reset" value="取消" onclick="window.close();"/>
  					</td>
  				</tr>
  			</table>
  	</form>
  </body>
</html>
