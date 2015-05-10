<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>报表参数编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
	  <form action="${pageContext.request.contextPath}/reportParaAction!${oprt }.action" 
	  		method="post" onsubmit="return Validator.Validate(this,4);" >
	    <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
	  	  	<caption>报表参数编辑</caption>
			<tbody>
				<tr>
					<td colspan="2">
						<input type="hidden" id="reportParaId" name="reportParaVo.reportParaId" 
							value="${retObjs[0].reportParaId}">
						<div class="input_msg">${idMsg }${reportParaIdMsg }${retInfo }</div>
					</td>
				</tr>
				<tr>
					<th>报表名称:</th>
					<td>
						<input type="hidden" id="reportInfoId" name="reportParaVo.reportInfoId" 
							value="${reportInfo.reportInfoId }"/>
						${reportInfo.reportName }
						<font color="red">*</font>
						<div class="input_msg">${reportInfoIdMsg }</div>
					</td>
				</tr>
				<tr>
					<th>数据类型:</th>
					<td>
						<amway:select id="dataType" name="reportParaVo.dataType" value="${retObjs[0].dataType }" 
							dictCode="report_data_type" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="请选择数据类型" style="width: 250px" />
						<font color="red">*</font>
						<div class="input_msg">${dataTypeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>显示名称:</th>
					<td>
						<amway:textfield id="paraDisplayName" name="reportParaVo.paraDisplayName" value="${retObjs[0].paraDisplayName }" 
							dataType="Require" msg="请填写显示名称" 
							style="width: 250px" maxLine="100"></amway:textfield>
						<font color="red">*</font>
						<div class="input_msg">${paraDisplayNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>显示类型:</th>
					<td>
						<amway:select id="dataDisplayType" name="reportParaVo.dataDisplayType" value="${retObjs[0].dataDisplayType }" 
							dictCode="whereControlType" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="请选择显示类型" style="width: 250px" />
						<font color="red">*</font>
						<div class="input_msg">${dataDisplayTypeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>参数名:</th>
					<td>
						<amway:textfield id="paraName" name="reportParaVo.paraName" value="${retObjs[0].paraName }" 
							dataType="Require" msg="请填写参数名" 
							style="width: 250px" maxLine="50"></amway:textfield>
						<font color="red">*</font>
						<div class="input_msg">${paraNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>数据转码:</th>
					<td>
						<amway:autoComplete id="dataCoding" name="reportParaVo.dataCoding" 
						 	source="data_code" value="${retObjs[0].dataCoding}"
						 	filterColNames="code|displayname" fillColKey="code" 
						 	style="width: 250px" 
						/>
						<div class="input_msg">${dataCodingMsg }</div>
					</td>
				</tr>
				<tr>
					<th>排列顺序：</th>
					<td>
						<amway:textfield id="paraOrder" name="reportParaVo.paraOrder" value="${retObjs[0].paraOrder}"
							dataType="Integer" msg="排列顺序为整数" style="width: 250px" maxLine="8"/>
						<font color="red">*</font>
						<div class="input_msg">${paraOrderMsg }</div>
					</td>
				</tr>
				<tr><td colspan="2" >&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name="common.save"></s:text>" 
							style="display: ${oprt=='add'?(_rights_.sysManage_report_para_add):(_rights_.sysManage_report_para_modify) }"
						/>&nbsp;&nbsp;
						<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
					</td>
				</tr>
			</tbody>
	    </table>
	  </form>
  </body>
</html>
