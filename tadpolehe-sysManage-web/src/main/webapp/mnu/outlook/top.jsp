<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib prefix="mnu" uri="/mnu-tag"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:tvns>
  <head>
    
    <title>top.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>

  </head>
  
  <body>
    <div id="body" >
		<div class="ind1_top">
			<jsp:include page="../common/top.jsp"></jsp:include>
		</div>
	</div>
  </body>
</html>
