<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>本地数据编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 
  </head> 

  <body>
	<form id="form1" action="${pageContext.request.contextPath}/bdsXMLDataAction!${oprt}.action" 
		method="post" onsubmit="return Validator.Validate(this,4);" >
	  <table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
	    <caption>本地数据编辑</caption>
	    <tr>
	      <td colspan="2">
	        <input type="hidden" id="bdsSchemaInforId" name="bdsXmlDataVo.bdsSchemaInforId" 
	        	value="${xmlSchema.bdsSchemaInforId }">
	        <input type="hidden" id="bdsXmlDataId" name="bdsXmlDataVo.bdsXmlDataId" 
	        	value="${xmlData.bdsXmlDataId }">
	        <div class="input_msg">${idMsg }${bdsXmlDataIdMsg }${retInfo }</div>
	        <div class="input_msg">${requiredMsg }</div>
	        <div class="input_msg">${colTypeMsg }</div>
	        <div class="input_msg">${regexMsg }</div>
	      </td>
	    </tr>
	    
	    <c:forEach items="${xmlDatas }" var="xmlData" varStatus="status">
	   	<tr>
	   		<th>
	   			${xmlData.key }
	   			<c:if test="${colAtts[status.index].required == '是'}">
	   				<font color="red">*</font>
	   			</c:if>
	   			:
	   		</th>
	   		<td>
	   		  <c:choose>
	   		  	<c:when test="${colAtts[status.index].refCol != ''}">
	   		  		<amway:select id="${xmlData.key }" name="xmlDatas"
	   		  			dictCode="${colAtts[status.index].refCode}"
	   		  			listKey="${colAtts[status.index].refCol}" 
	   		  			value="${xmlData.value }"
	   		  			style="width:250px"
	   		  		/>
	   		  	</c:when>
	   		  	<c:when test="${colAtts[status.index].colType == '日期'}">
	   		  		<amway:date id="${xmlData.key }" name="xmlDatas" value="${xmlData.value }" 
						format="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						readonly="readonly" style="width:250px" />
	   		  	</c:when>
	   		  	<c:otherwise>
	   		  		<input type="text" id="${xmlData.key }" name="xmlDatas" 
	   		  	  		value="${xmlData.value }" maxlength="${colAtts[status.index].maxLen }" style="width:250px"/>
	   		  	</c:otherwise>
	   		  </c:choose>
	   		</td>
	   	</tr>
	   	</c:forEach>
	   	<tr><td colspan="2">&nbsp;</td></tr>
	   	<tr>
		  <td colspan="2" align="center">
		   	<input type="submit" value="确定" />&nbsp;&nbsp;&nbsp;&nbsp;
		   	<input type="button" value="取消" onclick="window.close();window.opener.location.reload();" />
		  </td>
	   	</tr>
	   </table>
	</form>
  </body>
</html>
