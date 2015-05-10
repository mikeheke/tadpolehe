<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>定时报表展示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  	<form action="${pageContext.request.contextPath}/reportShowAction!make.action?reportCode=${reportInfo.reportCode }" 
  		method="post" onsubmit="return Validator.Validate(this,4);" target="reportShowIfrm">
	<div>
		<div>
			<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
				<caption>${reportInfo.reportName }</caption>
				<c:forEach items="${retObjs }" var="para" varStatus="status">
					<tr>
						<th>${para.paraDisplayName }</th>
						<td>
							<c:choose>
							<c:when test="${para.dataDisplayType == 0 }">
								<input id="${para.paraName }" name="${para.paraName }" style="width: 180px"/>
							</c:when>
							<c:when test="${para.dataDisplayType == 1 }">
								<amway:select id="${para.paraName }" name="${para.paraName }" dictCode="${para.dataCoding }" style="width: 180px"/>
							</c:when>
							<c:when test="${para.dataDisplayType == 2 }">
								<input id="${para.paraName }" name="${para.paraName }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 180px" />
							</c:when>
							<c:when test="${para.dataDisplayType == 3 }">
								<input id="${para.paraName }" name="${para.paraName }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" style="width: 180px" />
							</c:when>
							<c:when test="${para.dataDisplayType == 4 }">
								<input id="${para.paraName }" name="${para.paraName }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 180px" />
							</c:when>
							<c:when test="${para.dataDisplayType == 5 }">
								<input id="${para.paraName }From" name="${para.paraName }From" style="width: 180px" />
								&nbsp;&nbsp;到&nbsp;&nbsp;
								<input id="${para.paraName }To" name="${para.paraName }To" style="width: 180px"/>
							</c:when>
							<c:when test="${para.dataDisplayType == 6 }">
								<input id="${para.paraName }From" name="${para.paraName }From" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 180px" />
								&nbsp;&nbsp;到&nbsp;&nbsp;
								<input id="${para.paraName }To" name="${para.paraName }To" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 180px" />
							</c:when>
							<c:when test="${para.dataDisplayType == 7 }">
								<input id="${para.paraName }From" name="${para.paraName }From" onfocus="WdatePicker({dateFmt:'yyyy-MM-01'})" style="width: 180px" />
								&nbsp;&nbsp;到&nbsp;&nbsp;
								<input id="${para.paraName }To" name="${para.paraName }To" onfocus="WdatePicker({dateFmt:'yyyy-MM-01'})" style="width: 180px" />
							</c:when>
							<c:when test="${para.dataDisplayType == 8 }">
								<input id="${para.paraName }" name="${para.paraName }" onfocus="WdatePicker({dateFmt:'yyyy-MM-01'})" style="width: 180px" />
							</c:when>
							<c:when test="${where.controlType == 9 }">
								<input id="${para.paraName }From" name="${para.paraName }From" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" style="width: 180px" />
								&nbsp;&nbsp;到&nbsp;&nbsp;
								<input id="${para.paraName }To" name="${para.paraName }To" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" style="width: 180px" />
							</c:when>
							<c:when test="${para.dataDisplayType == 10 }">
								<amway:autoComplete id="${para.paraName }" name="${para.paraName }" source="${para.dataCoding }" style="width: 180px" />
							</c:when>
							<c:otherwise>
								<input id="${para.paraName }" name="${para.paraName }" style="width: 180px" />
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<th>生成格式</th>
					<td>
						<amway:radio id="makeType" name="makeType" dictCode="report_make_type" value="0"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="生成"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form>
	<iframe name="reportShowIfrm" style="display: none" ></iframe>
  </body>
</html>
