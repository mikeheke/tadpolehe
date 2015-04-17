<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.review.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>

  </head>
  
  <body style="margin: 10px;">
  	<div style="width:100%;height:278px;overflow: auto;" >
  		<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
  			<caption>基本信息</caption>
			<thead>
				<tr>
					<th width="30%" >
						<s:text name="query.base.propertyName" />
					</th>
					<th width="70%" >
						<s:text name="query.base.propertyValue" />
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><s:text name="query.base.queryCode" />：</th>
					<td>${_query_.queryCode }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.queryName" />：</th>
					<td>${_query_.queryName }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.application" />：</th>
					<td>${_query_.application.applicationName }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.dsJndi" />：</th>
					<td>${dsJndi }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.isColHeaFil" />：</th>
					<td>${isColHeaFil }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.externalJs" />：</th>
					<td>${_query_.externalJs }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.isDefaultQry" />：</th>
					<td>${isDefaultQry }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.useState" />：</th>
					<td>${useState }&nbsp;</td>
				</tr>
				<tr>
					<th><s:text name="query.base.remark" />：</th>
					<td>${_query_.remark }&nbsp;</td>
				</tr>
			</tbody>
		</table>
  	</div>
  	<div>
  		<table class="form_item2" >
  			
			<tbody style="vertical-align: top">
				<tr><th style="font-weight: bold;text-align: center;"><s:text name="query.review.sqlReview" /></th></tr>
				<tr>
					<td width="30%"><b>SELECT<b/>
						<div>
							<table>
								<c:forEach items="${selects}" var="select" varStatus="status">
			  						<tr>
				  						<td>
				  						&nbsp;&nbsp;&nbsp;${select.selectResult } &nbsp;&nbsp;&nbsp;${select.fieldAlias }
				  						<c:if test="${!status.last}">,</c:if>
				  						</td>
			  						</tr>
			  					</c:forEach>
	  						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td><b>FROM<b/>
						<div>
							<table>
								<c:forEach items="${froms}" var="from" varStatus="status">
			  						<tr><td>&nbsp;&nbsp;&nbsp;${from.fromResult }&nbsp;&nbsp;&nbsp;${from.tableAlias }
			  						<c:if test="${!status.last}">&nbsp;, </c:if>
			  						</td></tr>
	  							</c:forEach>
	  						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td><b>WHERE<b/>
						<div>
							<table>
								<c:forEach items="${wheres}" var="where" varStatus="status">
			  						<tr><td>&nbsp;&nbsp;&nbsp;${where.whereResult }
			  						<c:if test="${!status.last}">&nbsp;and </c:if>
			  						</td></tr>
			  					</c:forEach>
	  						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td><b>ORDER BY<b/>
						<div>
							<table>
								<c:forEach items="${orderBys}" var="orderByObj" varStatus="status">
			  						<tr><td>
			  						&nbsp;&nbsp;&nbsp;${orderByObj.tableName }.${orderByObj.fieldName }&nbsp;&nbsp;&nbsp;${orderByObj.orderByName }
			  						<c:if test="${!status.last}">&nbsp;, </c:if>
			  						</td></tr>
	  							</c:forEach>
	  						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td><b>GROUP BY<b/> 
						<div>
							<table>
	  							<c:forEach items="${groupBys}" var="groupBy" varStatus="status">
			  						<tr><td>&nbsp;&nbsp;&nbsp;
			  						${groupBy.tableName }.${groupBy.fieldName }
			  						<c:if test="${!status.last}">&nbsp;, </c:if>
			  						</td></tr>
	  							</c:forEach>
	  						</table>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
  	</div>
  </body>
</html>
