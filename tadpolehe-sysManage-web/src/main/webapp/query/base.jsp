<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.base.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
  </head>
  
  <body style="margin: 10px">
  	<form action="baseAction!${oprt }.action" method="post" 
  		onsubmit="return Validator.Validate(this,4);">
		<table class="form_item2">
			<thead>
				<tr>
					<th><s:text name="query.base.propertyName" /></th>
					<th><s:text name="query.base.propertyValue" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2">
						<input type="hidden" id="queryId"  name="queryVo.id"  value="${_query_.queryId }"/>
						<div class="input_msg">${idMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.queryCode" /><font color="red">*</font>：</th>
					<td>
						<amway:textfield id="queryCode" name="queryVo.queryCode" value="${_query_.queryCode }" 
							dataType="Require" maxLine="128" msg="请填写查询代码" style="width:200px"/>
						<div class="input_msg">${queryCodeMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.queryName" /><font color="red">*</font>：</th>
					<td>
						<amway:textfield id="queryName" name="queryVo.queryName" value="${_query_.queryName }" 
							dataType="Require" maxLine="256" msg="请填写查询名称" style="width:200px"/>
						<div class="input_msg">${queryNameMsg }</div>
					</td>
				</tr>
				<tr>
					<c:if test="${_query_.application.applicationId == 1 }">
	   									<th><s:text name="query.base.application" /><font color="red">*</font>：</th>
					<td>
						<amway:select id="applicationId" name="queryVo.applicationId" value="${_query_.application.applicationId}"
							dictCode="sysManageApp"
    						dataType="Require" msg="请选择所属应用" style="width: 200px"/>
						<div class="input_msg">${applicationIdMsg }</div>
					</td>
	   				</c:if>
	   				<c:if test="${_query_.application.applicationId != 1 }">
	   				<th><s:text name="query.base.application" /></th>
	   				<td>
	   						${_query_.application.applicationName}
	   						<input type="hidden" name="queryVo.applicationId"  value="${_query_.application.applicationId}" />
	   				</td>
					</c:if>
				</tr>
				<tr>
					<th><s:text name="query.base.dsJndi" /><font color="red">*</font>：</th>
					<td>
						<amway:select id="dsJndi" name="queryVo.dsJndi" value="${_query_.dsJndi }"
							dictCode="ds_table_col"
							dataType="Require" msg="请选择数据源JNDI" style="width:200px"/>&nbsp;
						<input type="button"  value="test" 
							onclick="testBtnOnclick('${pageContext.request.contextPath}/baseAction!checkDsJndi.action','dsJndi');" />
						<div id="dsJndiMsg" class="input_msg">${dsJndiMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.isColHeaFil" /><font color="red">*</font>：</th>
					<td>
						<amway:select id="isColHeaFil" name="queryVo.isColHeaFil" value="${_query_.isColHeaFil}"
							dictCode="true_or_false" dataType="Require" msg="请选择是否显示列头过滤" style="width:200px"/>
						<div class="input_msg">${isColHeaFilMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.externalJs" />：</th>
					<td>
						<amway:textfield id="externalJs" name="queryVo.externalJs" value="${_query_.externalJs }" 
							maxLine="256" style="width:400px"/>
						<div class="input_msg">${externalJsMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.isDefaultQry" /><font color="red">*</font>：</th>
					<td>
						<amway:select id="isDefaultQry" name="queryVo.isDefaultQry" value="${_query_.isDefaultQry}"
							dictCode="true_or_false" dataType="Require" msg="请选择默认是否查询" style="width:200px"/>
						<div class="input_msg">${isDefaultQryMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.isRefresh" /><font color="red">*</font>：</th>
					<td>
						<amway:select id="isRefresh" name="queryVo.isRefresh" value="${_query_.isRefresh}"
							dictCode="true_or_false" dataType="Require" msg="请选择是否自动刷新" style="width:200px"/>
						<div class="input_msg">${isRefreshMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.useState" /><font color="red">*</font>：</th>
					<td>
						<amway:select id="useState" name="queryVo.useState" value="${_query_.useState}"
							dictCode="state_options" dataType="Require" msg="请选择状态" style="width:200px"/>
						<div class="input_msg">${useStateMsg }</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="query.base.remark" />：</th>
					<td>
						<amway:textfield id="remark" name="queryVo.remark" value="${_query_.remark }" 
							maxLine="2048" style="width:400px"/>
						<div class="input_msg">${remarkMsg }</div>
					</td>
				</tr>
				<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name="common.save" />" />&nbsp;&nbsp;	
						<input type="reset" value="<s:text name="common.reset" />" onclick="document.getElementById('queryCode').focus();" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
  </body>
</html>
