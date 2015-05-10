<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<%
	String[] strArr = new String[]{"testDisName显示名1","testDisCode"};
	request.setAttribute("test", strArr);	
	
	List<Map> listMap = new ArrayList<Map>();
	Map map = new HashMap();
	map.put("regionCode", "\"r001\"");
	map.put("shopCode", "\"s001\"");
	map.put("shopNameCn", "\"中国\"");
	Map map2 = new HashMap();
	map2.put("regionCode", "\"r002\"");
	map2.put("shopCode", "\"s002\"");
	map2.put("shopNameCn", "\"a中国2\"");
	Map map3 = new HashMap();
	map3.put("regionCode", "\"r003\"");
	map3.put("shopCode", "\"s003\"");
	map3.put("shopNameCn", "\"中国3\"");
	Map map4 = new HashMap();
	map4.put("regionCode", "\"r004\"");
	map4.put("shopCode", "\"s004\"");
	map4.put("shopNameCn", "\"中国4\"");
	
	Map map5 = new HashMap();
	map5.put("regionCode", "\"r005\"");
	map5.put("shopCode", "\"s005\"");
	map5.put("shopNameCn", "\"中国aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa5\"");
	Map map6 = new HashMap();
	map6.put("regionCode", "\"r006\"");
	map6.put("shopCode", "\"s006\"");
	map6.put("shopNameCn", "\"a中国6\"");
	Map map7 = new HashMap();
	map7.put("regionCode", "\"r007\"");
	map7.put("shopCode", "\"s007\"");
	map7.put("shopNameCn", "\"中国7\"");
	Map map8 = new HashMap();
	map8.put("regionCode", "\"r008\"");
	map8.put("shopCode", "\"s008\"");
	map8.put("shopNameCn", "\"中国8\"");
	
	listMap.add(map);
	listMap.add(map2);
	listMap.add(map3);
	listMap.add(map4);
	listMap.add(map5);
	listMap.add(map6);
	listMap.add(map7);
	listMap.add(map8);
	listMap.add(map);
	listMap.add(map2);
	listMap.add(map3);
	listMap.add(map4);
	listMap.add(map5);
	listMap.add(map6);
	listMap.add(map7);
	listMap.add(map8);
	
	request.setAttribute("testScope", listMap);
	
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>AutoCompleteTag标签</title>
	</head>

	<body>
		<form id="ssss" name="bbbbb">
			<table>
				<tr>
					<td>
						自动完成输入框
						<amway:autoComplete id="code" name="bankRefundVo.unitCode"
							
							source="data_code" beforeSend="alert('1');" complete="alert('2');"
							>
						</amway:autoComplete>
						<amway:autoComplete id="unitCode" name="bankRefundVo.unitCode"
							filterPattern="((?i)\\\v).*"
							dsType="4" source="shop_code_and_region"
							filterColNames="shopNameCn|regionCode" fillColKey="shopNameCn|shopCode"
							fillForm="true">
						</amway:autoComplete>
					</td>
					<td>
						<amway:autoComplete id="unitCode2" name="bankRefundVo.unitCode2"
							dsType="1" source="testScope"
							filterColNames="regionCode|shopNameCn" fillColKey="shopNameCn|shopCode"
							value="${test}" size="32" showHeight="100" showWidth="227">
						</amway:autoComplete>
					</td>
				</tr>
				<tr>
					<td height="50px"><Select style="width: 300"><option>&nbsp;</option><option>&nbsp;</option><option>&nbsp;</option><option>&nbsp;</option></Select></td>
					<td><select style="width:500px;height:300px" multiple="multiple">
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
						<option>aaaaaaaaaaaaaaaaaaaaaaaaaaa</option>
					</select></td>
				</tr>
				<tr>
					<td>
						充填元素1-->code：
						<input type="radio" id="regionCode2" name="bankRefundVo.regionCode" value="NC"/>
						<br>
						充填元素2-->name：
						<input type="text" id="shopNameCn" name="bankRefundVo.shopNameCn" />
						<br>
						<select id="regionCode" name="bankRefundVo.regionCode2">
							<option value="aa">aaaa</option>
							<option value="NC">bbbb</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
