<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>textareaTag标签</title>
<%
	request.setAttribute("emailDefault","fenghanhao@revenco.com"); //可在后台设置标签默认值，默认值可放置在valuestack、request、session中
 %>
  </head>
  
  <body>
    <form>
		<table>
			<tr>
				<td>
					<amway:textarea 
						id="email" name="email" 
						dataType="Email" msg="信箱格式不正确"
						rows="10" cols="30" 
						value="${emailDefault}"
						maxLine="100"
					>
					</amway:textarea>
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>