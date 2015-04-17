<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>切换用户</title>
    
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
  	<form action="${pageContext.request.contextPath}/userProfileAction!change.action" method="post"
  			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  		<div>
  			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  				<caption>切换用户</caption>
  				<tr>
  					<td colspan="2" style="color: red">${retInfo}</td>
  				</tr>
  				<tr>
  					<th>登录帐号：</th>
  					<td>
  						<input type="text" value="${_sysUser_.empNumber}" readonly="readonly" style="width: 180"/>
  					</td>
  				</tr>
  				<tr>
  					<th>切换帐号<font color="red">*</font>：</th>
  					<td>
  						<input type="text" id="empNumber" name="userProfileVo.empNumber"
  							dataType="Require" msg="切换帐号不能为空" style="width: 180"/>&nbsp;&nbsp;&nbsp;&nbsp;
  						<div class="input_msg">${empNumberMsg }</div>
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  				<tr>
  					<td colspan="2" align="center">
  						<input type="submit" value="确定" />&nbsp;&nbsp;
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
