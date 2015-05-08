<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="a" uri="/core-tag" %>
<%@ include file="/common/jsp/resInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>roleRight</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dtree.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/basic.js" ></script>
	
	<script type="text/javascript">
		function submitForm(method){
			var oldAction = document.forms[0].action;
			document.forms[0].action = oldAction+method;
			document.forms[0].submit();
			document.forms[0].action = oldAction;
		}
		function queryBtnOnclick(method){
			submitForm(method);
		}
		function roleOnclick(curEle){
			if(curEle.value=="")return;
			queryBtnOnclick('query.action');
		}
		function saveBtnOnclick(method){
			if(document.getElementById("roleId").value==""){
				alert("角色不能为空");
				return;
			}
			var moduleIds = document.getElementsByName('roleRightVo.moduleIds');
			var count = 0;
			for (var i = 0; i < moduleIds.length; i++) {
				if (moduleIds[i].checked) {
					count++;
					break;
				}
			}
			if (count == 0) {
				method = method + "?roleRightVo.moduleId=0";
			}
			submitForm(method);
		}
		function copyBtnOnclick(method){
			var applicationId = document.getElementById("applicationId").value;
			popup(500, 500, document.forms[0].action+method+"?roleRightVo.applicationId="+applicationId);
		}
		

	</script>
  </head>
  
  <body onload="doqery()">
	<div style="margin-top: 10px">
	<form action="${pageContext.request.contextPath}/roleRightAction!" method="post" 
		onsubmit="return Validator.Validate(this,4);">
		<div style="margin-left: 10px">
			<table class="form_item2" style="width: 98%">
				<tr>
					<th>所属应用:</th>
					<td>
					<c:if test="${session._application_.applicationId == 1 }">
						<amway:select id="applicationId" name="roleRightVo.applicationId" dictCode="app_001" 
								listKey="applicationId" listValue="applicationName" value="${roleRightVo.applicationId }" 
								defaultHK="true" style="width: 180px"/>
				   </c:if>
				   <c:if test="${session._application_.applicationId != 1 }">
				   								${session._application_.applicationName}
	   					<input type="hidden" name="roleRightVo.applicationId"  value="${session._application_.applicationId}" />	
				   </c:if>
					</td>
					<td>
						<c:if test="${session._application_.applicationId == 1 }">
						<input type="button" id="query" name="query.action"  value="查询" onclick="queryBtnOnclick(this.name);"/>
						</c:if>
					</td>
					<td>
						<input type="button" id="save" name="save.action" value="保存授权" onclick="saveBtnOnclick(this.name);" />
						&nbsp;&nbsp;
						<input type="button" id="popup" name="popup.action" value="权限复制" onclick="copyBtnOnclick(this.name);"/>
					</td>
				</tr>
			</table>
		</div>
		<div style="height:10;margin-left: 10px;" class="input_msg">${retInfo}${idMsg}</div>
		
		
		<div style="width:100%;" style="margin-left: 10px;">
		<table>
			<tr>
				<!-- 1 -->
				<td style="vertical-align: top;">
				<div style="display:inline;width:200;height:350;vertical-align: top;">
					<table border="0"  width="100%">
						<tr>
							<th align="left" >应用角色信息(双击选择)</th>
						</tr>
						<tr>
							<td>
								<select id="roleId" name="roleRightVo.roleId" multiple="multiple" 
									style="width: 200;height:350;" ondblclick="roleOnclick(this);">
									<s:iterator value="%{getRoles()}" var="role">
										<c:if test="${role.roleCode != '00'}">
											<option value="${role.roleId}" 
												<c:if test="${roleRightVo.roleId==role.roleId}">selected</c:if>>${role.roleName}</option>
										</c:if>
									</s:iterator>	
								</select>
							</td>
						</tr>
					</table>
				</div>
				</td>
				
				<!-- 2 -->
				<td style="vertical-align: top;">
				<div style="display:inline;width:350;height:350;">
					<table border="0"  width="100%" >
						<tr><th align="left">应用角色权限列表</th></tr>
						<tr>
							<td>
								<div style="border: solid 1px silver;width: 350;height: 350;overflow: auto;">
									<a:roleRightTreeTag treeName="dc" check="true" 
										imgPath="${pageContext.request.contextPath}/common/images/dtree" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				</td>
				
				<!-- 3 -->
				<td style="vertical-align: top;">
				<div style="display:inline;width:350;height:350">
					<table border="0" width="100%">
						<tr>
							<th align="left">应用角色权限预览</th>
						</tr>
						<tr>
							<td>
								<div style="border: solid 1px silver;width: 350;height: 350;overflow: auto;">
									<a:roleRightTreeTag treeName="d" check="false" 
										imgPath="${pageContext.request.contextPath}/common/images/dtree" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				</td>
				
			</tr>
		</table>
		</div>
		
		
	</form>
	</div>
  </body>
</html>
