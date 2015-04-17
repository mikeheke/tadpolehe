<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.bottom.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="cache-control" content="no-store">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
  </head>
  
  <body style="border-top: 1 solid silver;">
  	<table width="100%">
  		<tr><td>&nbsp;</td></tr>
  		<tr>
  			<td align="left">
  				<input type="button" value="<s:text name="query.bottom.saveModify" />" 
  					onclick="document.location='configAction!save.action';window.parent.close();" />&nbsp;&nbsp;
  				<input type="button" value="<s:text name="query.bottom.leaveModify" />" 
  					onclick="document.location='configAction!leave.action';window.parent.close();" />
  			</td>
  			<td align="right">
  				<input type="button" value="<s:text name="query.bottom.lastPage" />" name="lastPage" id="lastPage"  
  					onclick="pageBtnOnclick(this)" />&nbsp;&nbsp;
  				<input type="button" value="<s:text name="query.bottom.nextPage" />" name="nextPage" id="nextPage" 
  					onclick="pageBtnOnclick(this)" />
  			</td>	
  		</tr>
  	</table>
  </body>
</html>
