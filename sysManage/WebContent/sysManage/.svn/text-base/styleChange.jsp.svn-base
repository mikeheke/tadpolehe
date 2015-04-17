<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>切换风格</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
		window.name="myModalDialog";
		
		function changeParentCss(styCss){

			styCss = styCss+".css";
			var frms = window.opener.parent.frames;
			var len = frms.length;
			for(var i=0; i<len; i++){
				var doc = frms[i].document;
				var styCssEle = doc.getElementById('amwayCss');
				if(!styCssEle){
					continue;
				}
				var styCssHref = styCssEle.href;
				var filename = styCssHref.replace(/(.*\/){0,}([^\.]+.*)/ig, "$2");
				if(filename == styCss){
					continue;
				}
				styCssHref = styCssHref.replace(filename, styCss);
				styCssEle.href = styCssHref;
			}
		}
	</script>
  </head>
  
  <body onload="changeParentCss('${_amwayCss_}');">
  	<form action="${pageContext.request.contextPath}/userProfileAction!change2.action" method="post"
  			onsubmit="return Validator.Validate(this,4);" target="myModalDialog">
  		<div>
  			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  				<caption>切换风格</caption>
  				<tr>
  					<td colspan="2" style="color: red">${retInfo}</td>
  				</tr>
  				<tr>
  					<td colspan="2">
  						<amway:radio id="styleCss" name="styleCss" dictCode="styleCss" value="${_amwayCss_}"/>
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
  	</form>
  </body>
</html>
