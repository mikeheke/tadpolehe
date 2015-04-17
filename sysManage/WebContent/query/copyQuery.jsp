<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>复制查询</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

</head>
  
<body>
  <form action="${pageContext.request.contextPath}/baseAction!copyQuery.action" 
  		method="post" onsubmit="return Validator.Validate(this,4);" >
  	  <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  	<caption>输入查询编码</caption>
		<tbody>
			<tr>
				<td colspan="2">
					<input type="hidden" id="oldQueryCode" name="oldQueryCode" value="${oldQueryCode}">
					<div class="input_msg">${oldQueryCodeMsg }${oldQueryCodeMsg }${retInfo }</div>
				</td>
			</tr>
			<tr>
				<th style="text-align: right;">新的查询编码:
				</th>
				<td>
					<amway:textfield id="newQueryCode" name="newQueryCode" value="${newQueryCode }" dataType="Require" msg="请填写查询编码" style="width: 250px" maxLine="128"></amway:textfield>
					<font color="red">*</font>
					<div class="input_msg">${newQueryCodeMsg }</div>
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr  align="center">
				<td colspan="2">
					<input type="submit" value="<s:text name="common.save"></s:text>" />&nbsp;&nbsp;
					<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
				</td>
			</tr>
		</tbody>
	  </table>
	</form>
</body>
</html>
