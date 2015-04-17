<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<div class="ind1_top_txt">
	欢迎您！
	<c:if test="${_application_.authenticateType eq 2 }">
		${_sysUser_.externalUserName }
	</c:if>
	<c:if test="${_application_.authenticateType eq null ||  _application_.authenticateType eq 1}">
		${_sysUser_.chineseName }|${_sysUser_.englishName }
	</c:if>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${_sysUser_.accountType == 0}">
		<a href="#" class="red" style="text-decoration:underline;" 
			onclick="popup(500,500,'${pageContext.request.contextPath}/userProfileAction!popup.action')">修改密码</a>
	</c:if>
	<c:if test="${_sysInfo_.isCacheClearUser}">
		<a href="#" class="red" style="text-decoration:underline;" 
			onclick="popup(500,500,'${pageContext.request.contextPath}/bdsSchemaInforAction!popup.action')">清除缓存</a>
	</c:if>
	<c:if test="${_sysInfo_.isChangeUser}">
		<a href="#" class="red" style="text-decoration:underline;" 
			onclick="popupRefresh(500,500,'${pageContext.request.contextPath}/userProfileAction!popup2.action')">切换用户</a>
	</c:if>
	<!-- 
	<a href="#" class="red" style="text-decoration:underline;" 
		onclick="openWin(500,500,'${pageContext.request.contextPath}/userProfileAction!popup3.action')">切换皮肤</a>
	 -->
	<!-- 隐藏 '切换语言' 链接
	<a href="#" class="red" style="text-decoration:underline;" 
		onclick="openWin(500,500,'${pageContext.request.contextPath}/userProfileAction!popup4.action')">切换语言</a>
	 -->
	<c:choose>
		<c:when test="${param._appContext_ != '' && param._appContext_ != null}">
			<a href="/${param._appContext_}/loginAction!logout.action" class="red" style="text-decoration:underline;">退出登录</a>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/loginAction!logout.action" class="red" style="text-decoration:underline;">退出登录</a>
		</c:otherwise>
	</c:choose>
</div>
<div class="ind1_top_title">
	<span class="blue s14 b">${_application_.applicationName }</span><br />
	<span class="blue s12 b">&nbsp;</span>
</div>
