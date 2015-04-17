<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib prefix="mnu" uri="/mnu-tag"%>
<link rel="stylesheet" href="${_sysApplication_.goToUrl}/common/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!--<link rel="stylesheet" href="${_sysApplication_.goToUrl}/common/zTree/css/mnu/mnu.css" type="text/css">-->

<script type="text/javascript" src="${_sysApplication_.goToUrl}/common/zTree/js/jquery.ztree.core-3.1.min.js"></script>
<script type="text/javascript" src="${_sysApplication_.goToUrl}/common/js/mnu.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:tvns>
  <head>
    
    <title>left.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	
	
  </head>
  
  <body>
  
  <div>
  <ul id="treeDemo" class="ztree"></ul>
  </div>
  <script type="text/javascript">  
  <mnu:ZTreeTag type="MODDLENODE" 
			module="${param.module}"
			imgPath="${pageContext.request.contextPath}/common/images/treeView" 
			jsPath="${pageContext.request.contextPath}/common/js"
			className="ind_left_menu"
			bExpanded="true"
			iconSkin="pIcon"  />	
	
		
   		$(document).ready(function(){			
			$.fn.zTree.init($("#"+TREEDIVID), setting, zNodes);
			// addFirstClass();
		});
		
  </script>
   
  </body>
</html>
