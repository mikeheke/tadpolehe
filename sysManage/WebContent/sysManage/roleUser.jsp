<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色用户</title>
    
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
  		<form action="${pageContext.request.contextPath}/roleUserAction!save.action" method="post"
  			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  		<div>
  			<table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
  	  			<caption>角色用户信息</caption>
  				<tr>
  					<td colspan="2" style="color: red">${retInfo}</td>
  				</tr>
  				<tr>
  					<th>角色名称<font color="red">*</font>：</th>
  					<td>
  						<amway:textfield id="roleName" name="roleUserVo.roleName" value="${role.roleName }" group="modify" dataType="Require" msg="角色名称不能为空" style="width:250px"></amway:textfield>
  						<input type="hidden" id="roleCode" name="roleUserVo.roleCode" value="${role.roleCode }"
  							dataType="Require" msg="角色编码不能为空"/>
  						<div class="input_msg">${roleCodeMsg}</div>
  					</td>
  				</tr>
  				<tr>
  					<th>用户名称<font color="red">*</font>：</th>
  					<td>
  						<amway:autoComplete id="userProfileId" name="roleUserVo.userProfileId" dsType="4" value="${retObjs[0].displayName }"
						source="user_profile"  filterColNames="empNumber|englishName|chineseName" fillColKey="englishName|userProfileId" style="width: 250px" dataType="Require" msg="请填写用户名称" ></amway:autoComplete>
						<div class="input_msg">${userProfileIdMsg}</div>
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  				<tr>
  					<td colspan="2" align="center">
  						<input type="submit" value="保存" />&nbsp;&nbsp;
  						<input type="button" value="关闭" onclick="window.close();"/>
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
