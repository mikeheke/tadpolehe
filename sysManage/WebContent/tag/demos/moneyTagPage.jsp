<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ page import= "java.util.Date" %>
<%@ include file="/common/jsp/resInclude.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>moneyTag标签</title>
<%
	request.setAttribute("moneyDefault",2232.564353); //可在后台设置标签默认值，默认值可放置在valuestack、request、session中

 %>
     <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/common/js/validator.js"></script>
     
  </head>
  
  <body>
    <form>
    	<table>
    		<tr>
    			<td>
    				<amway:money 
    					id="money1" name="money1" 
    					dataType="Number" format="#,##0.00" msg="数字格式不正确" 
    					value="${moneyDefault}" maxLine="10"
    				>
    				</amway:money>
    			</td>
    		</tr>
    	</table>
	</form>
  </body>
</html>
