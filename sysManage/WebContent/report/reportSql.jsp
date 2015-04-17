<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>报表语句编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
	  <form action="${pageContext.request.contextPath}/reportSqlAction!${oprt }.action" 
	  		method="post" onsubmit="return Validator.Validate(this,4);" >
	    <table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
	  	  	<caption>报表语句编辑</caption>
			<tbody>
				<tr>
					<td colspan="2">
						<input type="hidden" id="reportSqlId" name="reportSqlVo.reportSqlId" 
							value="${retObjs[0].reportSqlId}">
						<div class="input_msg">${idMsg }${reportSqlIdMsg }${retInfo }</div>
					</td>
				</tr>
				<tr>
					<th>报表名称:</th>
					<td>
						<input type="hidden" id="reportInfoId" name="reportSqlVo.reportInfoId" 
							value="${reportInfo.reportInfoId }"/>
						${reportInfo.reportName }
						<font color="red">*</font>
						<div class="input_msg">${reportInfoIdMsg }</div>
					</td>
				</tr>
				<tr>
					<th>SQL语句:</th>
					<td>
						<amway:textarea rows="10" cols="70" id="sqlSelect" name="reportSqlVo.sqlSelect" 
									value="${retObjs[0].sqlSelect }" style="width:650px" maxLine="2000" dataType="Require" msg="请填写SQL语句"></amway:textarea>
						<font color="red">*</font>
						<div class="input_msg">${sqlSelectMsg }</div>
					</td>
				</tr>
				<tr>
					<th>Where语句:</th>
					<td>
						<amway:textarea rows="10" cols="70" id="sqlWhere" name="reportSqlVo.sqlWhere" 
									value="${retObjs[0].sqlWhere }" style="width:650px" maxLine="2000" dataType="LimitB" max="2000" msg="Where语句长度不能超过2000个字符"></amway:textarea>
						<div class="input_msg">${sqlWhereMsg }</div>
					</td>
				</tr>
				<tr>
					<th>排序语句:</th>
					<td>
						<amway:textarea rows="7" cols="70" id="sqlOrder" name="reportSqlVo.sqlOrder" 
									value="${retObjs[0].sqlOrder }" style="width:650px" maxLine="1000" dataType="LimitB" max="1000" msg="排序语句长度不能超过1000个字符"></amway:textarea>
						<div class="input_msg">${sqlOrderMsg }</div>
					</td>
				</tr>
				<tr>
					<th>SQL类型:</th>
					<td>
						<amway:select id="sqlType" name="reportSqlVo.sqlType" value="${retObjs[0].sqlType }" 
							dictCode="report_sql_type" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="请选择SQL类型" style="width: 250px" />
						<font color="red">*</font>
						<div class="input_msg">${sqlTypeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>Map键值:</th>
					<td>
						<amway:textfield id="mapKey" name="reportSqlVo.mapKey" value="${retObjs[0].mapKey }" 
							dataType="Require" msg="请填写Map键值" 
							style="width: 250px" maxLine="50"></amway:textfield>
						<font color="red">*</font>
						<div class="input_msg">${mapKeyMsg }</div>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td>
						<amway:textarea rows="5" cols="70" id="remark" name="reportSqlVo.remark" 
									value="${retObjs[0].remark }" style="width:650px" maxLine="1000" dataType="LimitB" max="1000" msg="备注长度不能超过1000个字符"></amway:textarea>
						<div class="input_msg">${remarkMsg }</div>
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name="common.save"></s:text>" 
							style="display: ${oprt=='add'?(_rights_.sysManage_report_sql_add):(_rights_.sysManage_report_sql_modify) }"
						/>&nbsp;&nbsp;
						<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
					</td>
				</tr>
			</tbody>
	    </table>
	  </form>
  </body>
</html>
