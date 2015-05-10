<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:tvns>
  <head>
    <title>${_application_.applicationName }</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>

  </head>
  
  <frameset rows="115,*" frameborder="no" border="0" framespacing="0" >
	
	<frameset cols="*,1024,*" frameborder="no" border="0" framespacing="0">
		<frame src="about:blank" scrolling="no"></frame>
		<frame src="<%=basePath %>/mnu/outlook/top.jsp?_appContext_=${param._appContext_ }" name="top" scrolling="no" noresize id="top" ></frame>	
		<frame src="about:blank" scrolling="no"></frame>
	</frameset>
	
	<frameset id="bodyFrame"  cols="*,206,18,800,*" frameborder="no" border="0" framespacing="0" >
		<frame src="about:blank" scrolling="no"></frame>
	    <frame src="<%=basePath %>/mnu/outlook/left.jsp" name="left" scrolling="auto" noresize id="left" ></frame>
	    <frame src="<%=basePath %>/mnu/outlook/leftRight.jsp" name="leftRight" scrolling="no" noresize id="leftRight" ></frame>
	    <frame src="<%=basePath %>/mnu/common/main.jsp" name="main" scrolling="yes" noresize id="main"  ></frame>
	    <frame src="about:blank" scrolling="no"></frame>
	</frameset>
	
	<!-- 
	<frame src="<%=basePath %>/mnu/common/bottom.jsp" name="bottom" scrolling="no" noresize id="bottom" ></frame>
	 -->
  </frameset>
  <noframes>
	<body>
		此为框架页面，你的游览器不支持框架!
	</body>
  </noframes>
</html>
