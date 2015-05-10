<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ page import= "java.util.Date" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>dateTag标签</title>
<%
	request.setAttribute("rightdate",new Date()); //可在后台设置标签默认值，默认值可放置在valuestack、request、session中
	request.setAttribute("finance","true");
	request.setAttribute("wrongDate","asdfad");
 %>
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/common/js/validator.js"></script>
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/common/My97DatePicker/WdatePicker.js"></script>
  
  </head>
  
  <body>
    <form>
    	<table>
    		<tr>
    			<td>
    				<amway:date 
    					id="date1" name="date1" 
    					group="finance2"
    					dataType="Date"  msg="日期格式不正确" oFormat="yyyy-MM" format="yyyy-MM-dd"
    					value="${rightdate}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
    				>
    				</amway:date>
    			</td>
    		</tr>
    	</table>
	</form>
  </body>
</html>
