<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>选择表名</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		window.name = "appPopupWindow";
		
		function confirmOnclick(){
			var tableNames = document.getElementsByName('tableName');
			var tableName = "";
			var len = tableNames.length;
			for(var i=0; i<len; i++){
				if(tableNames[i].checked){
					tableName = tableNames[i].value;
					break;
				}
			}
			setParDocElementValueById('tableName', tableName);
			window.close();
		}
	</script>
</head>
  
<body style="margin: 10px">
 
  <form id="qryForm" action="${pageContext.request.contextPath}/fromAction!popupSelTbName.action" 
  		method="post" onsubmit="return Validator.Validate(this,4);" target="appPopupWindow">
  	  <div>
  	  	<table id="formTable" class="form_item2" >
  	  		<tr><td colspan="2" style="color: red">${retInfo }</td></tr>
  	  		<tr>
  	  			<td width="10%">表名：</td>
  	  			<td>
				<amway:textfield  name="queryFromName" value="${queryFromName}"/>
  	  			&nbsp;&nbsp;
  	  			<input type="submit" value="查询"/>
  	  			</td>
  	  		</tr>
	  	</table>
  	  </div>
  	  <div style="height: 400px;border-bottom: solid 1px silver;overflow: auto;">
  	  	<table class="query_list">
  	  		<tr>
  	  			<th width="10%">选择</th>
  	  			<th width="90%">表名</th>
  	  			<%-- 
  	  			<th width="35%">应用名称</th>
  	  			<th width="30%">备注</th>
  	  			 --%>
  	  		</tr>
  	  		<c:forEach items="${tableNameList}" var="tableName">
  	  			<tr>
  	  				<td><input type="radio" name="tableName" value="${tableName}" ondblclick="confirmOnclick();"/></td>
  	  				<td>${tableName}</td>
  	  				<%--
  	  				<td>${retObj.applicationName}</td>
  	  				<td>${retObj.remark}</td>
  	  				--%>
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
