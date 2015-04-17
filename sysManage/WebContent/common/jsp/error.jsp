<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/jsp/resInclude.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>error</title>
	
	<script type="text/javascript">
		function setStackVi(){
			var stack = document.getElementById("stackInfo");
			if(stack.style.visibility=="visible"){
				stack.style.visibility = "hidden"
			}else{
				stack.style.visibility = "visible"
			}
		}
		
		function sendEmail(curEle, url){
			
			var exceptionName = document.getElementById("exceptionName").innerText;
			var stackInfo = document.getElementById("stackInfo").innerText;
			var params = {'exceptionCfgVo.exceptionName':exceptionName,'exceptionCfgVo.remark':stackInfo};
			ajaxRequest(url, params, 'sendEmailBack');
			
		}
		
		function sendEmailBack(){
			alert("发送成功");
		}
	</script>
  </head>
  <body>
  	<form id="errorFrm" method="post">
  	<img src="${_sysApplication_.goToUrl}/common/images/404b_${_amwayCss_}.jpg" width="100%"/>
  	<div align="right">
  		<div><a onclick="setStackVi();" style="text-decoration: underline;cursor: pointer">异常信息</a>：
  			<font color="red" size="4">
  				<span id="exceptionName"><s:property value="%{exception.message}"/></span>
  				 &nbsp;&nbsp;&nbsp;&nbsp;
  			</font>
  		</div>
  	</div>   
  	<div style="width: 100%;">
  		<div align="right"></div>
		<div id="stackInfo" style="height: 230px;overflow: auto;visibility: hidden">
			<s:property value="%{exceptionStack}"/>
		</div>
		<div align="right" style="margin-top:5px;margin-right: 30px">
			<a href="javascript:history.back(-1);">
				<img src="${_sysApplication_.goToUrl}/common/images/404_btm.jpg" />
			</a>
 	  			&nbsp;&nbsp;
 	  		<a href="${pageContext.request.contextPath}" target="_parent">
 	  			<img src="${_sysApplication_.goToUrl}/common/images/404_btm2.jpg" />
 	  		</a>
 	  			&nbsp;&nbsp;
 	  		<img src="${_sysApplication_.goToUrl}/common/images/404_btm3.jpg" onclick="sendEmail(this,'<%=basePath %>/exceptionCfgAction!send.action');" style="cursor: pointer" />
	  	</div>
	</div>
	</form>
</body>
</html>
