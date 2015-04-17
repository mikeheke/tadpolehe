<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>密码修改</title>
    
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
	<form action="${pageContext.request.contextPath}/userProfileAction!modifyPwd.action" method="post"
			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
		<div>
			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
				<caption>密码修改</caption>
				<tr>
					<td colspan="2" style="color: red">${retInfo}</td>
				</tr>
				<tr>
					<th>原密码<font color="red">*</font>：</th>
					<td>
						<input type="password" id="oldPassword" name="userProfileVo.oldPassword" 
							dataType="Require" msg="原密码不能为空" maxlength="256" style="width: 180px"/>
						<div class="input_msg">${oldPasswordMsg }</div>
					</td>
				</tr>
				<tr>
					<th>新密码<font color="red">*</font>：</th>
					<td>
						<input type="password" id="password" name="userProfileVo.password"
							dataType="LimitB" min="6" msg="新密码至少为6位" maxlength="256" style="width: 180px"/>&nbsp;&nbsp;&nbsp;&nbsp;
						新密码至少为6位
						<div class="input_msg">${passwordMsg }</div>
					</td>
				</tr>
				<tr>
					<th>重复新密码<font color="red">*</font>：</th>
					<td>
						<input type="password" id="password2" name="userProfileVo.password2" 
							dataType="Repeat" to="Password" msg="您输入的新密码、重复新密码不一致" maxlength="256" style="width: 180px"/>
						<div class="input_msg">${password2Msg }</div>
					</td>
				</tr>
				<tr height="20"><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="修改" />&nbsp;&nbsp;
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
