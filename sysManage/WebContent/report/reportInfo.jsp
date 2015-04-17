<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>定时报表设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
	  <form action="${pageContext.request.contextPath}/reportInfoAction!${oprt }.action" 
	  		method="post" onsubmit="return Validator.Validate(this,4);" >
	    <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
	  	  	<caption>定时报表设置</caption>
			<tbody>
				<tr>
					<td colspan="2">
						<input type="hidden" id="reportInfoId" name="reportInfoVo.reportInfoId" 
							value="${retObjs[0].reportInfoId}">
						<div class="input_msg">${idMsg }${reportInfoIdMsg }${retInfo }</div>
					</td>
				</tr>
				<tr>
					<th>报表编码:</th>
					<td>
					<c:if test="${oprt=='add'}">
						<amway:textfield id="reportCode" name="reportInfoVo.reportCode" value="${retObjs[0].reportCode }" dataType="Require" msg="请填写报表编码" style="width: 250px" maxLine="50"></amway:textfield>
					</c:if>
					<c:if test="${oprt!='add'}">
						<amway:textfield id="reportCode" name="reportInfoVo.reportCode" value="${retObjs[0].reportCode }" dataType="Require" msg="请填写报表编码" readonly="readonly" style="width: 250px" maxLine="50"></amway:textfield>
					</c:if>
						<font color="red">*</font>
						<div class="input_msg">${reportCodeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>报表名称:</th>
					<td>
						<amway:textfield id="reportName" name="reportInfoVo.reportName" value="${retObjs[0].reportName }" 
							dataType="Require" msg="请填写报表名称" 
							style="width: 250px" maxLine="128"></amway:textfield>
						<font color="red">*</font>
						<div class="input_msg">${reportNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>所属应用:</th>
					<td>
					<c:if test="${session._application_.applicationId == 1 }">
						<amway:select id="applicationId" name="reportInfoVo.applicationId" 
	  								dictCode="app_001" listKey="applicationId" listValue="applicationName" 
	  								value="${retObjs[0].application.applicationId }" 
	  								defaultHK="true" dataType="Require" msg="请选择所属应用" style="width: 250px"/>
						<font color="red">*</font>
						<div class="input_msg">${applicationIdMsg }</div>
					</c:if>
		  			 <c:if test="${session._application_.applicationId != 1 }">
		  			 		${session._application_.applicationName}
			   			   <input type="hidden" name="reportInfoVo.applicationId"  value="${session._application_.applicationId}" />	
		  			  </c:if>					
					</td>
				</tr>
				<tr>
					<th>报表类型:</th>
					<td>
						<amway:select id="reportType" name="reportInfoVo.reportType" value="${retObjs[0].reportType }" 
							dictCode="report_type" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="请选择报表类型" style="width: 250px" />
						<font color="red">*</font>
						<div class="input_msg">${reportTypeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>模板名称:</th>
					<td>
						<amway:textfield id="reportUrl" name="reportInfoVo.reportUrl" value="${retObjs[0].reportUrl }" 
							style="width:250px" maxLine="128" dataType="Require" msg="请填写模板名称"
						/>
						<font color="red">*</font>
						<div class="input_msg">${reportUrlMsg }</div>
					</td>
				</tr>
				<tr>
					<th>上传模板:</th>
					<td>
						<amway:fileUpDownload name="fileList" size="21" 
							appCode="${_application_.applicationId}"
							moduleCode="sysManage_reportInfo_${retObjs[0].reportCode }" 
							path="${report_file_path }" isEnc="false" isFileList="true" />
					</td>
				</tr>
				<tr>
					<th>状态:</th>
					<td>
						<amway:select id="state" name="reportInfoVo.state" value="${retObjs[0].state }" 
							dictCode="state_options" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="请选择状态" style="width: 250px" />
						<font color="red">*</font>
						<div class="input_msg">${stateMsg }</div>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td>
						<amway:textarea rows="5" cols="30" id="remark" name="reportInfoVo.remark" 
									value="${retObjs[0].remark }" style="width:250px" maxLine="1000" dataType="LimitB" max="1000" msg="备注长度不能超过1000个字符"></amway:textarea>
						<div class="input_msg">${remarkMsg }</div>
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name="common.save"></s:text>" 
							style="display: ${oprt=='add'?(_rights_.sysManage_report_info_add):(_rights_.sysManage_report_info_modify) }"
						/>&nbsp;&nbsp;
						<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
					</td>
				</tr>
			</tbody>
	    </table>
	  </form>
  </body>
</html>
