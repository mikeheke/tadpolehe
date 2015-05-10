<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><s:text name="module.page.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<form
			action="${pageContext.request.contextPath}/moduleAction!${oprt }.action?oprt=${oprt }"
			method="post" onsubmit="return Validator.Validate(this,4);">
			<table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
				<caption>
					菜单信息
				</caption>
				<tbody>
					<tr>
						<td colspan="2">
							<input type="hidden" id="moduleId" name="moduleVo.moduleId" value="${retObjs[0].moduleId }">
							<div class="input_msg">
								${idMsg }${moduleIdMsg }${retInfo }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.code"></s:text>:</th>
						<td>
							<c:if test="${oprt=='add'}">
								<amway:textfield id="moduleCode" name="moduleVo.moduleCode" value="${retObjs[0].moduleCode }" 
									dataType="Require" msg="请填写功能编码" style="width: 250px" maxLine="128" 
									onblur="if('${retObjs[0].parentModule.moduleCode }' == ''){document.getElementById('parentModuleCode').value=this.value;}"></amway:textfield>
							</c:if>
							<c:if test="${oprt!='add'}">
								<amway:textfield id="moduleCode" name="moduleVo.moduleCode" value="${retObjs[0].moduleCode }" 
								dataType="Require" msg="请填写功能编码" readonly="readonly" style="width: 250px" maxLine="128" ></amway:textfield>
							</c:if>
							<font color="red">*</font>
							<div class="input_msg">
								${moduleCodeMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.name"></s:text>:</th>
						<td>
							<amway:textfield id="moduleName" name="moduleVo.moduleName" value="${retObjs[0].moduleName }" 
							dataType="Require" msg="请填写功能名称" style="width: 250px" maxLine="256"></amway:textfield>
							<font color="red">*</font>
							<div class="input_msg">
								${moduleNameMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.parentModuleName"></s:text>:</th>
						<td>
 							<amway:textfield id="parentModuleCode" name="moduleVo.parentModuleCode" value="${retObjs[0].parentModule.moduleCode }"
								dataType="Require" readonly="readonly" msg="请填写父功能编码" style="width: 250px" maxLine="256" ></amway:textfield>
							<font color="red">*</font>
							<div class="input_msg">
								${parentModuleCodeMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.isModuleOrButton"></s:text>:</th>
						<td>
							<amway:select id="isModuleOrButton" name="moduleVo.isModuleOrButton" dictCode="module_or_button"
								listKey="code" listValue="displayname" value="${retObjs[0].isModuleOrButton }" defaultHK="true"
								dataType="Require" msg="请选择模块或按钮" style="width: 250px" />
							<font color="red">*</font>
							<div class="input_msg">
								${isModuleOrButtonMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.applicationId"></s:text>:</th>
						<td>
							<c:if test="${session._application_.applicationId == 1 }">
							<amway:select id="applicationId" name="moduleVo.applicationId" dictCode="app_001" listKey="applicationId"
								listValue="applicationName" value="${retObjs[0].parentModule.application.applicationId }"
								defaultHK="true" dataType="Require" msg="请选择所属应用" style="width: 250px" />
							<font color="red">*</font>
							<div class="input_msg">
								${applicationIdMsg }
							</div>
							</c:if>
							<c:if test="${session._application_.applicationId != 1 }">
								 ${session._application_.applicationName}
	   							<input type="hidden" name="moduleVo.applicationId"  value="${session._application_.applicationId}" />
							
							</c:if>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.ico"></s:text>:</th>
						<td>
							<amway:textfield id="ico" name="moduleVo.ico" value="${retObjs[0].ico }" style="width: 250px" maxLine="256" ></amway:textfield>
							<div class="input_msg">
								${icoMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.link"></s:text>:</th>
						<td>
							<amway:textfield id="link" name="moduleVo.link" value="${retObjs[0].link2 }" 
							style="width: 250px" maxLine="256"></amway:textfield>
							<div class="input_msg">
								${linkMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.orderNo"></s:text>:</th>
						<td>
							<amway:textfield id="orderNo" name="moduleVo.orderNo" value="${retObjs[0].orderNo }" dataType="Integer" msg="排序应为整数" style="width: 250px" maxLine="8"></amway:textfield>
							<input type="button" value="生成" onclick="setEleValueJson('<%=basePath%>/moduleAction!findOrderNo.action','orderNo');" />
							<font color="red">*</font>
							<div class="input_msg">
								${orderNoMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.isSeed"></s:text>:</th>
						<td>
							<amway:select id="isSeed" name="moduleVo.isSeed" dictCode="submodule_or_not" listKey="code"
								listValue="displayname" value="${retObjs[0].isSeed }" dataType="Require" msg="请选择是否子模块" style="width: 250px"></amway:select>
							<font color="red">*</font>
							<div class="input_msg">
								${isSeedMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.openType"></s:text>:</th>
						<td>
							<amway:select id="openType" name="moduleVo.openType" dictCode="window_open_type" listKey="code"
								listValue="displayname" value="${retObjs[0].target }" dataType="Require" msg="请选择打开方式" style="width: 250px"></amway:select>
							<font color="red">*</font>
							<div class="input_msg">
								${openTypeMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.state"></s:text>:</th>
						<td>
							<amway:select id="state" name="moduleVo.state" dictCode="state_options" listKey="code" listValue="displayname"
								value="${retObjs[0].state }" dataType="Require" msg="请选择状态" style="width: 250px" />
							<font color="red">*</font>
							<div class="input_msg">
								${stateMsg }
							</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="module.remark"></s:text>:</th>
						<td>
							<amway:textarea rows="5" cols="30" id="remark" name="moduleVo.remark" 
								value="${retObjs[0].remark }" style="width:250px" maxLine="2048" dataType="LimitB" max="2048" msg="备注长度不能超过2048个字符"></amway:textarea>
							<div class="input_msg">
								${remarkMsg }
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<input type="submit" value="<s:text name="common.save"></s:text>" />
							&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
					<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
