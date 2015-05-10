<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>切换语言</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
		window.name="myModalDialog";
		
		window.onbeforeunload=function window.onbeforeunload(){
			refreshParent();
		}
		function confirmBtnOnclick(rootPath, appPath){

			var langs = document.forms[0].requestLocales;
			var len = langs.length;
			var lang = "";
			for(var i=0; i<len; i++){
				if(langs[i].checked){
					lang = langs[i].value;
					break;
				}
			}
			if(lang == '' || lang == null){
				return;
			}
			
			var path = "/loginAction!json.action?request_locale="+lang;
			var url = rootPath+path;
			var url2 = appPath+path;
			var params = {};
			ajaxRequest(url, params, '');
			ajaxRequest(url2, params, 'winRefresh');
			
		}
		
		function winRefresh(){
			alert("语言切换成功");
			window.location = window.location;
		}
		
		function refreshParent(){
			var frms = window.opener.parent.frames;
			var len = frms.length;
			for(var i=0; i<len; i++){
				frms[i].location = frms[i].location;
			}
		}
	</script>
  </head>
  
  <body>
  	<form action="/userProfileAction!change3.action" method="post"
  			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  		<div>
  			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  				<caption>切换语言</caption>
  				<tr>
  					<td colspan="2" style="color: red">${retInfo}</td>
  				</tr>
  				<tr>
  					<td colspan="2">
  						<amway:radio id="requestLocales" name="requestLocales" dictCode="languages" value="${WW_TRANS_I18N_LOCALE}" />
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  				<tr>
  					<td colspan="2" align="center">
  						<input type="button" value="确定" onclick="confirmBtnOnclick('${pageContext.request.contextPath}','${_application_.goToUrl}');" />&nbsp;&nbsp;
  						<input type="button" value="取消" onclick="window.close();"/>
  					</td>
  				</tr>
  				<tr height="20"><td colspan="2">&nbsp;</td></tr>
  			</table>
  		</div>	
  	</form>
  </body>
</html>
