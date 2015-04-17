<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<script type="text/javascript">
	function initLanguage(rootPath, appPath, dftLang){
	
		var path = "/loginAction!json.action?request_locale="+dftLang;
		var url = rootPath+path;
		var url2 = appPath+path;
		var params = {};
		ajaxRequest(url, params, '');
		//ajaxRequest(url2, params, '');
	}
	initLanguage('${pageContext.request.contextPath}','${_application_.goToUrl}','${_application_.languange}');
</script>

<jsp:include page="layout/${_application_.layout }.jsp" flush="true"/>
