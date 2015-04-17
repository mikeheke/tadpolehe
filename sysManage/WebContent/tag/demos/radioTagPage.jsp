<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="amway" uri="/amway-tag"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>RadioTag标签</title>
  </head>
  
  <body>
  <form>
  	<table border="1" >
  		<tr>
			<td>部门:</td>
			<td>
				<amway:select id="departmentCode" name="applicationVo.departmentCode" 
					dictCode="department_list" />
			</td>
		</tr>
		<tr>
			<td>
				员工:
			</td>
			<td>
				<amway:radio id="faultHandlerEmpNumber" name="applicationVo.faultHandlerEmpNumber"
					value="${test}" 
					dictCode="user_profile" listKey="userProfileId"
					listValue="chineseName" 
					value = "000846"
					parentItem="departmentCode"
					cssClass="abcCssClass"
					/>
			</td>
		</tr>
       <tr>
         <td>账户类型：</td>
         <td>
		    <amway:radio 
		    	id="accountType" name="accountType" 
		    	dictCode="languages"  listKey="code" listValue="displayname" 
		    	value="002" onclick="alert(this.value);">
		    </amway:radio>
	     </td>
       </tr>
   	  </table>
    </form>
  </body>
</html>
