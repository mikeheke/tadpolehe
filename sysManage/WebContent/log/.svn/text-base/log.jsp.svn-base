<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>日志配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
		function queryBtnOnclick(method){
		
			if(!Validator.Validate(document.forms[0],4)){
				return;
			}
			var oldAction = document.forms[0].action;
			document.forms[0].action = oldAction+method;
			document.forms[0].submit();
			document.forms[0].action = oldAction;
		}
		function applicationUrlOnclick(method){
			
			var url = document.forms[0].action+method;
			popup(800, 600, url);
		}
	</script>
  </head>
  
  <body style="margin:10px">
  	<form action="${pageContext.request.contextPath}/logAction!" 
  		method="post" onsubmit="return Validator.Validate(this,4);">
  		<div>
  			<table class="form_item2">
  				<tr>
  					<th width="12%">应用名称<font color="red">*</font>：</th>
  					<td width="10%">
  					<c:if test="${session._application_.applicationId == 1 }">
  						<amway:select id="applicationId" name="logVo.applicationId" 
  								dictCode="app_001" listKey="applicationId" listValue="applicationName" 
  								value="${logVo.applicationId }" defaultHK="true" 
  								dataType="Require" msg="此项不能为空"
  								/>
  					</c:if>
  					<c:if test="${session._application_.applicationId != 1 }">
				   								${session._application_.applicationName}
	   					<input type="hidden" name="logVo.applicationId"  value="${session._application_.applicationId}" />	
				   </c:if>
  					</td>
  					<td width="10%">&nbsp;</td>
  					<td width="50%">
  						<input type="button" name="query.action" value="查询" onclick="queryBtnOnclick(this.name);" />
  					</td>
  				</tr>
  			</table>
  		</div>
  		<div style="height: 10"></div>
  		<div>
  			<table class="query_list">
  				<thead>
  					<tr>
  						<th width="25%">应用名称</th>
  						<th width="15%">日志级别</th>
  						<th width="60%">日志文件地址</th>
  					</tr>
  				</thead>
  				<tbody>
  					<s:iterator value="#request.retObjs" var="retObj">
  						<tr>
  							<td>
  								<a href="#" onclick="applicationUrlOnclick('initModify.action?logVo.applicationId=${retObj.applicationId }&logVo.fixWay=${retObj.fixWay }&logVo.logLevel=${retObj.logLevel }&logVo.logFileAdr=${retObj.logFileAdr }')" >
  									${retObj.applicationName }
  								</a>
  							</td>
  							<td>${retObj.logLevel }</td>
  							<td>${retObj.logFileAdr }</td>
  						</tr>
  					</s:iterator>
  				</tbody>
  			</table>
  		</div>
  	</form>
  </body>
</html>
