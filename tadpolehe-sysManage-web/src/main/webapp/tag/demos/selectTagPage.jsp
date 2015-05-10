<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<%
	String[] strArr = new String[]{"81","王五"};
	request.setAttribute("test", strArr);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>SelectTag标签</title>

	</head>

	<body>
		<form>
			<table>
				<tr>
					<td>部门:</td>
					<td>
						<amway:select id="departmentCode" name="applicationVo.departmentCode" 
							dictCode="department_list" value="000610" onchange="alert('')" multiple="true"
							defaultHK="false" />
					</td>
				</tr>
				<tr>
					<td>
						员工:
					</td>
					<td>
						<amway:select id="faultHandlerEmpNumber" name="applicationVo.faultHandlerEmpNumber"
							value="185" group="kjjj"
							dictCode="user_profile" listKey="userProfileId"
							listValue="chineseName" 
							parentItem="departmentCode"
							parentKey="departmentCode"
							defaultHK="false"
							/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
