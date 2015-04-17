<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>fileUpDownload标签</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/basic_blue.css"></link>
		
	</head>

	<body>
		<center>
			<br>
			<br>
			amway:fileUpDownload 标签 测试页面
			<br />
			<br />
			<form id='testForm' name="testForm" action="${pageContext.request.contextPath}/testFileUpDownloadAction!test.action" method="post" >
				<input name="testName" />
				
				<amway:fileUpDownload bId="1" name="testFileName" />
				<amway:fileUpDownload bId="2" name="testFileName2" maximumSize="10"/>
				<input name="testName2" />
				<input type="submit" value="提交"  />
			</form>
			
		</center>
12222222222222

	</body>
</html>
