<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.frame.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="cache-control" content="no-store">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
  </head>
  
  <frameset rows="*,50" frameborder="no" border="0" framespacing="0" onload="initQuery();">

	<frameset  cols="160,640" frameborder="no" border="0" framespacing="0" >
	    <frame src="${pageContext.request.contextPath}/baseAction!init.action?oprt=${param.oprt }&ids=${param.ids }" 
	    	name="query_left" scrolling="auto" noresize id="query_left" >
	    <frame src="" name="query_main" scrolling="auto" noresize id="query_main"  >
	</frameset>
	
	<frame src="bottom.jsp" name="query_bottom" scrolling="no" noresize id="query_bottom" >
  </frameset> 
  
</html>
