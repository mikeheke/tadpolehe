<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.show.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="cache-control" content="no-store">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
	<s:if test="#session._query_show_.externalJs != null">
	<script type="text/javascript" src="${_query_show_.externalJs }"></script>
	</s:if>
	
	<script type="text/javascript">
		function pressEnter(event) {
			document.forms.ec.ec_eti.value='';
			document.forms.ec.ec_f_a.value='fa';
			document.forms.ec.ec_p.value='1';
			document.forms.ec.setAttribute('action','${pageContext.request.contextPath}/showTwoPageAction.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}&showType=result');
			document.forms.ec.setAttribute('method','post');
			document.forms.ec.setAttribute('target','_self');
			document.forms.ec.submit();
		}
		
		//点击查询按钮
		//function clickQueryBtn() {
		//	document.forms.ec.ec_eti.value='';
		//	document.forms.ec.ec_f_a.value='fa';
		//	document.forms.ec.ec_p.value='1';
		//	document.forms.ec.setAttribute('action','${pageContext.request.contextPath}/showTwoPageAction.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}&showType=result');
		//	document.forms.ec.setAttribute('method','post');
		//	document.forms.ec.setAttribute('target','_self');
		//	
		//	if(Validator.Validate(document.forms[0],4)) {
		//		document.forms.ec.submit();
		//	}
		//}
	</script>
	
  </head>
  
  <body style="margin: 10px" onload="initQueryShow('${pageContext.request.contextPath}')">
  	<form id="ec" method="post" >
  	
  	<%-- 查询条件页面传过来的查询条件(hidden) start --%>
  	<c:forEach items="${_query_show_.queryUIWheres }" var="where" varStatus="status">
	  	<c:choose>
		<c:when test="${where.controlType == 0 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 0 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 0 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 1 && where.parTagId == null && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"  value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 1 && where.parTagId == null && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"  value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 1 && where.parTagId == null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 1 && where.parTagId != null && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"  value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 1 && where.parTagId != null && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"  value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 1 && where.parTagId != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 2 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 2 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 2 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 3 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 3 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 3 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 4 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 4 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 4 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 5 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 5 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 5 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 6 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 6 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 6 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 7 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 7 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 7 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 8 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 8 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 8 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 9 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 9 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 9 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 10 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"  value="${where.defaultValue }" />
		</c:when>
		<c:when test="${where.controlType == 10 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"   value="${where.defaultValue }"  />
		</c:when>
		<c:when test="${where.controlType == 10}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }"  value="${where.defaultValue }" />
		</c:when>
		
		<c:when test="${where.controlType == 11 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		    <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" />	
		</c:when>
		<c:when test="${where.controlType == 11 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		    <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  />
		</c:when>
		<c:when test="${where.controlType == 11 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		    <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  />
		</c:when>
		<c:when test="${where.controlType == 12 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		    <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  />	
		</c:when>
		<c:when test="${where.controlType == 12 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		    <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"   />	
		</c:when>
		<c:when test="${where.controlType == 12 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
			<input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  />				 
		</c:when>							
		<c:when test="${where.controlType == 13 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
			 <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" />	
		</c:when>
		<c:when test="${where.controlType == 13 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		          <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" />
		</c:when>
		<c:when test="${where.controlType == 13 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" />
		           <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" />
		</c:when>
		<c:when test="${where.controlType == 14 && where.regExp != null}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		          <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" />				 
		</c:when>
		<c:when test="${where.controlType == 14 && where.isRequired==1}">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		          <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  />				 
		</c:when>
		<c:when test="${where.controlType == 14 }">
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		    <input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  />				 
		</c:when>
		
		<c:otherwise>
			<input type="hidden" id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }"  />
		</c:otherwise>
		</c:choose>
	
		<c:if test="${where.controlType == 5 || where.controlType == 6 || where.controlType == 7 || where.controlType == 9}">
		<c:choose>
			<c:when test="${where.regExp != null}">
				<input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" 
				/>
			</c:when>
			<c:when test="${where.isRequired==1}">
				<input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }"  
				/>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="${where.whereCode }" value="${where.defaultValue2[1] }" 
				/>
			</c:otherwise>
		</c:choose>
	</c:if>
	</c:forEach>
	<%-- 查询条件页面传过来的查询条件 end --%>
  	
	<div>
		<div>
	
			<%-- 
			<table width="100%" class="form_item2">
				<tr>
					<td colspan="20" style="text-align: left;">
					<input type="button" value="&nbsp;查询&nbsp;" onclick="clickQueryBtn();" />
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			 --%>
			
			<%-- 隐藏的查询条件 --%>
			<c:forEach items="${_query_show_.queryHiddenWheres }" var="where">
				<c:forEach items="${where.defaultValue2 }" var="value">
					<input type="hidden" name="${where.whereCode }" value="${value }" />
				</c:forEach>
			</c:forEach>
		</div>
		<div id="oprtInfo" style="height: 10px;color: red">${retInfo }</div>
		<div style="border: buttonface 1px solid;">
			<table>
				<tr>
					<td>
						<s:iterator value="#session._query_show_.buttons"  var="button" >
							<input type="submit" name='${button.buttonNo }' value='${button.buttonName }'
								style="display: ${_rights_[button.buttonNo ]}"
								onclick="
									<c:if test="${button.executeJs!=null && button.executeJs!=''}">
										var ret = ${button.executeJs };
										if(!ret){
											return false;	
										}
									</c:if>
									<c:choose>
										<c:when test="${button.openType!=null && button.openType!='' && fn:startsWith(param._appContext_,'http:')}">
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:when test="${button.openType!=null && button.openType!='' && param._appContext_!='' && param._appContext_!=null}">
											return buttonOnclick(this,'/${param._appContext_}/${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:otherwise>
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:otherwise>
									</c:choose>
								" />&nbsp;
						</s:iterator>				
					</td>
				</tr>
			</table>
		</div>
		<div>
			<ec:table 
				items="retObjs"
				var = "p"
				action="${pageContext.request.contextPath}/showTwoPageAction.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}&showType=result"
				imagePath="${pageContext.request.contextPath}/common/images/table/${_amwayCss_ }/*.gif"
				title="${_query_show_.queryName }"
				width="${_query_show_.tableWidth}"
				retrieveRowsCallback="limit"
				filterRowsCallback="limit"
				sortRowsCallback="limit"
				autoIncludeParameters="false"
				locale="${WW_TRANS_I18N_LOCALE}"
			>
				<ec:exportXls view="xls" fileName="${_query_show_.queryName}.xls"
					action="${pageContext.request.contextPath}/exportShowAction!export.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}">
				</ec:exportXls>
				<ec:row style="height:20px">
					<ec:columns autoGenerateColumns="mikeheke.tadpole.frm.query.vo.AutoGenerateColumns" />
				</ec:row>
			</ec:table>
		</div>
		<div style="border: buttonface 1px solid;">
			<table>
				<tr>
					<td>
						<s:iterator value="#session._query_show_.buttons"  var="button" >
							<input type="submit" name='${button.buttonNo }' value='${button.buttonName }'
								style="display: ${_rights_[button.buttonNo ]}"
								onclick="
									<c:if test="${button.executeJs!=null && button.executeJs!=''}">
										var ret = ${button.executeJs };
										if(!ret){
											return false;	
										}
									</c:if>
									<c:choose>
										<c:when test="${button.openType!=null && button.openType!='' && fn:startsWith(param._appContext_,'http:')}">
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:when test="${button.openType!=null && button.openType!='' && param._appContext_!='' && param._appContext_!=null}">
											return buttonOnclick(this,'/${param._appContext_}/${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:otherwise>
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:otherwise>
									</c:choose>
								" />&nbsp;
						</s:iterator>				
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form>
  </body>
</html>