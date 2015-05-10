<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>checkboxTag标签</title>
<%
	request.setAttribute("defaultLan","c2"); //可在后台设置标签默认值，默认值可放置在valuestack、request、session中
	List<Map> listMap = new ArrayList<Map>();
	Map<String, String> map = new HashMap<String, String>();
	map.put("code", "c1");
	map.put("displayname", "d1");
	listMap.add(map);
	
	Map<String, String> map2 = new HashMap<String, String>();
	map2.put("code", "c2");
	map2.put("displayname", "d2");
	listMap.add(map2);
	
	Map<String, String> map3 = new HashMap<String, String>();
	map3.put("code", "c3");
	map3.put("displayname", "d3");
	listMap.add(map3);
	
	request.setAttribute("lanMap", listMap);
 %>
    
  </head>
  
  <body>
    <form>
    	<table>
    		<tr>
    			<td>
    				<amway:checkbox
				    	id="accountType" name="accountType" 
				    	dictCode="${lanMap}" listKey="code" listValue="displayname" 
				    	value="${defaultLan}">
				    </amway:checkbox>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
