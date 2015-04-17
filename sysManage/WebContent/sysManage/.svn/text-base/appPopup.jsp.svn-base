<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>选择应用</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		window.name = "appPopupWindow";
		
		function confirmOnclick(){
			var applicationCodes = document.getElementsByName('applicationCode');
			var applicationCode = "";
			var len = applicationCodes.length;
			for(var i=0; i<len; i++){
				if(applicationCodes[i].checked){
					applicationCode = applicationCodes[i].value;
					break;
				}
			}
			setParDocElementValueById('applicationCode', applicationCode);
			window.close();
		}
	</script>
</head>
  
<body style="margin: 10px">
  <form id="qryForm" action="${pageContext.request.contextPath}/applicationAction!query.action" 
  		method="post" onsubmit="return Validator.Validate(this,4);" target="appPopupWindow">
  	  <div>
  	  	<table id="formTable" class="form_item2" >
  	  		<tr><td colspan="8" style="color: red">${retInfo }</td></tr>
  	  		<tr>
  	  			<td>应用编码：</td>
  	  			<td><amway:textfield  name="applicationVo.applicationCode" value="${applicationVo.applicationCode}"/></td>
  	  			<td>&nbsp;</td>
  	  			<td>应用名称：</td>
  	  			<td><amway:textfield  name="applicationVo.applicationName" value="${applicationVo.applicationName}"/></td>
  	  			<td>&nbsp;</td>
  	  			<td><input type="submit" value="查询"/></td>
  	  			<td>&nbsp;</td>
  	  		</tr>
	  	</table>
  	  </div>
  	  <div style="height: 360px;border-bottom: solid 1px silver;">
  	  	<table class="query_list">
  	  		<tr>
  	  			<th width="8%">选择</th>
  	  			<th width="32%">应用编码</th>
  	  			<th width="35%">应用名称</th>
  	  			<th width="30%">备注</th>
  	  		</tr>
  	  		<c:forEach items="${retObjs}" var="retObj">
  	  			<tr>
  	  				<td><input type="radio" name="applicationCode" value="${retObj.applicationCode}" ondblclick="confirmOnclick();"/></td>
  	  				<td>${retObj.applicationCode}</td>
  	  				<td>${retObj.applicationName}</td>
  	  				<td>${retObj.remark}</td>
  	  			</tr>
  	  		</c:forEach>
  	  	</table>
  	  </div>
  	  <div align="center">
  	  	<table >
  	  		<tr>
  	  			<td>
  	  				<input type="button" value="确定" onclick="confirmOnclick();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  				<input type="button" value="取消" onclick="window.close();"/>
  	  			</td>
  	  		</tr>
  	  	</table>
  	  </div>
	</form>
</body>
</html>
