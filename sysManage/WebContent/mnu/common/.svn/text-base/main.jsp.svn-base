<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.amway.frm.base.util.AppConstant"%>
<%@page import="com.amway.frm.afw.entity.Application"%>
<%@page import="com.amway.frm.afw.entity.Module"%>
<%@page import="java.util.List"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

Application app = (Application)request.getSession().getAttribute(AppConstant.APPLICATION_NAME);
String defaultPage = app.getDefaultPage();
if(null != defaultPage && !defaultPage.equals("")){
	//String defaultPageUrl = "http://"+app.getDeployServer()+":"+app.getPort()+"/"+app.getContext()+"/"+defaultPage;
	String defaultPageUrl = "/"+app.getContext()+"/"+defaultPage;
	List<Module> modules = (List<Module>)request.getSession().getAttribute(AppConstant.MODULES_NAME);
	for(Module module: modules){
		String[] moduleUrls = module.getModuleUrl();
		for(String moduleUrl: moduleUrls){
			if(moduleUrl.equals(defaultPageUrl)){
				response.sendRedirect(defaultPageUrl);
				break;
			}
		}
	}
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:tvns>
  <head>
    
    <title>main.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	
  </head>
  
  <body style="width: 100%">
    <div id="ind_right" style="width: 98%">
  		<div style="width: 98%">&nbsp;&nbsp;&nbsp;&nbsp;</div>
  		<div class="ind_right_title" style="width: 98%"><img src="<%=basePath %>/common/images/ind_right_ico8.gif" /><strong>我的中心</strong></div>
  		<div class="ind_right_box" style="width: 98%">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
      			<tr>
        			<td width="25%" align="right" bgcolor="#e4e4e4" class="td3"><strong>登录帐号：</strong></td>
        			<td width="75%" class="td3">${_sysUser_.empNumber }</td>
        		</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td3"><strong>登录名称：</strong></td>
        			<td class="td3">${_sysUser_.chineseName }</td>
      			</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td3"><strong>登录次数：</strong></td>
        			<td class="td3">${_sysUser_.loginNum }</td>
      			</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td3"><strong>本次登录时间：</strong></td>
        			<td class="td3"><amway:date value="${_sysUser_.loginTime }" format="yyyy年MM月dd日 HH:mm:ss" group="" /></td>
      			</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td3"><strong>本次登录IP：</strong></td>
        			<td class="td3">${_sysUser_.loginIp }</td>
      			</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td3"><strong>上次登录时间：</strong></td>
        			<td class="td3"><amway:date value="${_sysUser_.lastLoginTime }" format="yyyy年MM月dd日 HH:mm:ss" group=""/></td>
      			</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td3"><strong>上次登录IP：</strong></td>
        			<td class="td3">${_sysUser_.lastLoginIp }</td>
      			</tr>
      			<tr>
        			<td align="right" bgcolor="#e4e4e4" class="td4"><strong>版本号：</strong></td>
        			<td class="td4">v1.2011.12.28</td>
      			</tr>
    		</table>
  		</div>
  </div>
  </body>
</html>
