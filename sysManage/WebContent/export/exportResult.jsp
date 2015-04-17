<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>日志配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">

	</script>
  </head>
  
  <body style="margin:10px">

  		<div style="height: 10"></div>
  		<div>
  			<table class="query_list">
  				<thead>
  					<tr>
  						<th width="85%">sql</th>
  						<th width="15%">结果</th>
  					</tr>
  				</thead>
  				<tbody>
  					<s:iterator value="#request.retObjs" var="retObj">
  						<tr>
  							<td>
    							${retObj.sql}
  							</td>
  							<td>${retObj.result}</td>
  						</tr>
  					</s:iterator>
  				</tbody>
  			</table>
  		</div>

  </body>
</html>
