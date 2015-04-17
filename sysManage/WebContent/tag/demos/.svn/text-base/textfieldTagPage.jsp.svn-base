<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>textfieldTag标签</title>
		<%
			//String[] strs = new String[]{"a","b"};
			request.setAttribute("emailDefault", ""); //可在后台设置标签默认值，默认值可放置在valuestack、request、session中
			request.setAttribute("finance", "true");
		%>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/validator.js">
		</script>
		<script type="text/javascript">

		</script>
	</head>

	<body>
		<center>
			<br>
			<br>
			amway:textfield 标签 测试页面
			<br />
			<br />
			<div align="left" style="width: 80%;">
				<input type="text" readonly value="66666" disabled="disabled">
				<form onSubmit="return Validator.Validate(this,1)"
					action="www.google.com">
					邮箱：
					<amway:textfield id="email" name="email" dataType="Email" maxLine="3"
						msg="信箱格式不正确" group="p55,o0" cssClass="defaultCss" size="20"
						value="${emailDefault}"></amway:textfield>
					<br />
					<input type="submit" value="提交">
				</form>
			</div>
		</center>
	</body>
</html>
