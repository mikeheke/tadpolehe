<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>定时任务设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
	  <form action="${pageContext.request.contextPath}/timeingJobAction!${oprt }.action" 
	  		method="post" onsubmit="return Validator.Validate(this,4);" >
	    <table id="formTable" class="form_item" width="95%" border="0" align="center" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5"; >
	  	  	<caption>定时任务配置</caption>
			<tbody>
				<tr>
					<td colspan="2">
						<input type="hidden" id="timeingJobId" name="timeingJobVo.timeingJobId" 
							value="${retObjs[0].timeingJobId}">
						<div class="input_msg">${idMsg }${timeingJobIdMsg }${retInfo }</div>
					</td>
				</tr>
				<tr>
					<th>任务编码:</th>
					<td>
					<c:if test="${oprt=='add'}">
						<amway:textfield id="timeingJobCode" name="timeingJobVo.timeingJobCode" value="${retObjs[0].timeingJobCode }" dataType="Require" msg="请填写任务编码" style="width: 250px" maxLine="50"></amway:textfield>
					</c:if>
					<c:if test="${oprt!='add'}">
						<amway:textfield id="timeingJobCode" name="timeingJobVo.timeingJobCode" value="${retObjs[0].timeingJobCode }" dataType="Require" msg="请填写任务编码" readonly="readonly" style="width: 250px" maxLine="50"></amway:textfield>
					</c:if>
						<font color="red">*</font>
						<div class="input_msg">${timeingJobCodeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>任务名称:</th>
					<td>
						<amway:textfield id="timeingJobName" name="timeingJobVo.timeingJobName" value="${retObjs[0].timeingJobName }" 
							dataType="Require" msg="请填写任务名称" style="width: 250px" maxLine="100"></amway:textfield>
						<font color="red">*</font>
						<div class="input_msg">${timeingJobNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>所属应用:</th>
					<td>
					<c:if test="${session._application_.applicationId == 1 }">
						<amway:select id="applicationId" name="timeingJobVo.applicationId" 
	  								dictCode="app_001" listKey="applicationId" listValue="applicationName" 
	  								value="${retObjs[0].application.applicationId }" 
	  								defaultHK="true" dataType="Require" msg="请选择所属应用" style="width: 250px"/>
						<font color="red">*</font>
						<div class="input_msg">${applicationIdMsg }</div>
						</c:if>	
			  			 <c:if test="${session._application_.applicationId != 1 }">
			  			 		${session._application_.applicationName}
				   			   <input type="hidden" name="timeingJobVo.applicationId"  value="${session._application_.applicationId}" />	
			  			  </c:if>						
					</td>
				</tr>
				<tr>
					<th>调用类名:</th>
					<td>
						<amway:textfield id="className" name="timeingJobVo.className" value="${retObjs[0].className }" 
							style="width:250px" maxLine="100" dataType="Require" msg="请填写调用类名"
						/>
						<font color="red">*</font>
						<div class="input_msg">${classNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>调用方法:</th>
					<td>
						<amway:textfield id="methodName" name="timeingJobVo.methodName" value="${retObjs[0].methodName }" 
							style="width:250px" maxLine="100"
						/>
						<div class="input_msg">${methodNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>启动时间:</th>
					<td>
						<amway:date id="startTime" name="timeingJobVo.startTime" value="${retObjs[0].startTime }" 
							format="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
							style="width: 250px" />
						<div class="input_msg">${startTimeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>结束时间:</th>
					<td>
						<amway:date id="endTime" name="timeingJobVo.endTime" value="${retObjs[0].endTime }" 
							format="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
							style="width: 250px" />
						<div class="input_msg">${endTimeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>周期:</th>
					<td>
						<amway:textfield id="cycle" name="timeingJobVo.cycle" value="${retObjs[0].cycle }" 
							style="width:250px" maxLine="8"
						/>
						<div class="input_msg">${cycleMsg }</div>
					</td>
				</tr>
				<tr>
					<th>周期单位:</th>
					<td>
						<amway:select id="cycleUnit" name="timeingJobVo.cycleUnit" value="${retObjs[0].cycleUnit }" 
							dictCode="job_cycle_unit" listKey="code" listValue="displayname" 
							defaultHK="true" style="width: 250px" />
						<div class="input_msg">${cycleUnitMsg }</div>
					</td>
				</tr>
				<tr>
					<th>cronJob串:</th>
					<td>
						<amway:textfield id="cronJobBunch" name="timeingJobVo.cronJobBunch" value="${retObjs[0].cronJobBunch }" 
							style="width:250px" maxLine="250"
						/>
						<div class="input_msg">${cronJobBunchMsg }</div>
					</td>
				</tr>
				<tr>
					<th>连续失败阀值:</th>
					<td>
						<amway:textfield id="failNum" name="timeingJobVo.failNum" value="${retObjs[0].failNum }" 
							style="width:250px" maxLine="8"
						/>
						<div class="input_msg">${failNumMsg }</div>
					</td>
				</tr>
				<tr>
					<th>状态:</th>
					<td>
						<amway:select id="state" name="timeingJobVo.state" value="${retObjs[0].state }" 
							dictCode="state_options" listKey="code" listValue="displayname" 
							defaultHK="true" dataType="Require" msg="请选择状态" style="width: 250px" />
						<font color="red">*</font>
						<div class="input_msg">${stateMsg }</div>
					</td>
				</tr>
				<tr>
					<th>备注:</th>
					<td>
						<amway:textarea rows="5" cols="30" id="remark" name="timeingJobVo.remark" 
									value="${retObjs[0].remark }" style="width:250px" maxLine="2048" dataType="LimitB" max="2048" msg="备注长度不能超过2048个字符"></amway:textarea>
						<div class="input_msg">${remarkMsg }</div>
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<s:text name="common.save"></s:text>" 
							style="display: ${oprt=='add'?(_rights_.sysManage_timeing_job_add):(_rights_.sysManage_timeing_job_modify) }"
						/>&nbsp;&nbsp;
						<input type="button" value="<s:text name="common.cancel"></s:text>" onclick="window.close();"/>
					</td>
				</tr>
			</tbody>
	    </table>
	  </form>
  </body>
</html>
