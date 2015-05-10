<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>缓存清除</title>
    
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
  	<form action="${pageContext.request.contextPath}/bdsSchemaInforAction!clear.action" method="post"
  			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  		<div>
  			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  				<caption>缓存清除</caption>
  				<tr>
  					<td colspan="2" style="color: red">${retInfo}${idMsg}</td>
  				</tr>
  				<tr>
  					<th>数据编码<font color="red">*</font>：</th>
  					<td>
  						<amway:autoComplete id="bdsSchemaInforCode" name="bdsSchemaInforVo.bdsSchemaInforCode" 
							 	source="data_code" 
							 	filterColNames="code|displayname" fillColKey="code" 
							 	dataType="Require" msg="数据编码不能为空"
							 	style="width: 180"
						/>
						<div class="input_msg">${bdsSchemaInforCodeMsg }</div>
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  				<tr>
  					<td colspan="2" align="center">
  						<input type="submit" value="清除" />&nbsp;&nbsp;
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
