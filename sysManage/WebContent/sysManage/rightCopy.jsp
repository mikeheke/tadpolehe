<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>权限复制</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
		window.name="myModalDialog";
	</script>
  </head>
  
  <body>
  	<form action="${pageContext.request.contextPath}/roleRightAction!copy.action" method="post"
  			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  		<div>
  			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  				<caption>权限复制</caption>
  				<tr>
  					<td colspan="2" style="color: red">${retInfo}${idMsg}</td>
  				</tr>
  				<tr>
  					<th>源角色<font color="red">*</font>：</th>
  					<td>
  						<select id="srcRoleId" name="roleRightVo.srcRoleId" style="width: 180px" 
  							dataType="Require" msg="源角色不能为空">
  								<option value=""></option>
							<s:iterator value="%{getRoles()}" var="role">
								<c:if test="${role.roleCode != '00'}">
									<option value="${role.roleId}" >${role.roleName}</option>
								</c:if>
							</s:iterator>	
						</select>
						<div class="input_msg">${srcRoleIdMsg }</div>
  					</td>
  				</tr>
  				<tr>
  					<th>目标角色<font color="red">*</font>：</th>
  					<td>
  						<select id="desRoleId" name="roleRightVo.desRoleId" style="width: 180px" 
  							dataType="Require" msg="目标角色不能为空">
  								<option value=""></option>
							<s:iterator value="%{getRoles()}" var="role">
								<c:if test="${role.roleCode != '00'}">
									<option value="${role.roleId}" >${role.roleName}</option>
								</c:if>
							</s:iterator>	
						</select>
						<div class="input_msg">${desRoleIdMsg }</div>
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  				<tr>
  					<td colspan="2" align="center">
  						<input type="submit" value="复制" />&nbsp;&nbsp;
  						<input type="button" value="取消" onclick="window.close();"/>
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  			</table>
  		</div>	
  		<div>
  			<table>
  				<tr>
  					<td colspan="2" align="left">[注]带<font color="red">*</font>为必选项</td>
  				</tr>
  			</table>
  		</div>
  	</form>
  </body>
</html>
