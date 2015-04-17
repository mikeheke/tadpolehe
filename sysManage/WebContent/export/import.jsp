<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>配置数据导入
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<form
			action="${pageContext.request.contextPath}/exportConfigAction!importSql.action?oprt=import"
			method="post"  enctype='multipart/form-data' target="_blank">
			<table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
				<caption>
					配置数据导入
				</caption>
				<tbody>
					<tr>
						<th>SQL文件:</th>
						<td>
							<input type="file" name="sqlfile"/>
						</td>
					</tr>					
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<input type="submit" value="导入" />
							&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
