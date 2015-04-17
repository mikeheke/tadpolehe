<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/jsp/resInclude.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>info</title>
		
  </head>
  <body>
  	<img src="${_sysApplication_.goToUrl}/common/images/404a_${_amwayCss_}.jpg" width="100%"/>
  	<div align="right">
  		<div>提示信息：
  			<font color="red" size="4">
  				<s:property value="#request.retInfo"/>&nbsp;...&nbsp;
  				<s:text name="Exp.%{exception.message}" />&nbsp;
  				&nbsp;&nbsp;&nbsp;&nbsp;
  			</font>
  		</div>
  	</div>
  	<div style="width: 100%;">
		<div style="height: 150px;overflow: auto;">	
		</div>
		<div align="right" style="margin-top:5px;margin-right: 30px">
			<a href="javascript:history.back(-1);">
				<img src="${_sysApplication_.goToUrl}/common/images/404_btm.jpg" />
			</a>
 	  			&nbsp;&nbsp;
 	  		<a href="${pageContext.request.contextPath}" target="_parent">
 	  			<img src="${_sysApplication_.goToUrl}/common/images/404_btm2.jpg" />
 	  		</a>
	  	</div>
	</div>
</body>
</html>
