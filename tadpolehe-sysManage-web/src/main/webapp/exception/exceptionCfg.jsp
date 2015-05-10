<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="exception.title" /></title> 
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript">
		function addOptionTo(fromObjectId, toObjectId){ 
       		var fromObject = document.getElementById(fromObjectId);
       		
       		if(fromObject.value == ""){
       			return;
       		}
       		var newOption = document.createElement("option");
	   		var toObject = document.getElementById(toObjectId);

			var len = toObject.options.length;
			for(i=0; i<len; i++){
				if(fromObject.value == toObject.options[i].value){
					return;			
				}		
		 	}
		 	
		 	var selIndex = fromObject.selectedIndex;
        	newOption.value = fromObject.title;
        	newOption.text = fromObject.title;
        	toObject.options.add(newOption);
    	}
    	function delOptionFrom(delObjectId){
    		var delObject = document.getElementById(delObjectId);
    		var len = delObject.options.length;
    		var num = 0;
    		for(i=0; i<len; i++){
				if(delObject.options[i-num].selected){
					delObject.options.remove(i-num);
					num = num+1;
				}		
		 	}
    		if (num == 0) {
    			alert("请选择要删除的Email用户。");
    			return false;
    		}
    	} 
    	function selAll(curEleId){
    		var curEle = document.getElementById(curEleId);
    		var len = curEle.options.length;
			for(i=0; i<len; i++){
				curEle.options[i].selected = true;		
		 	}
    	}
    	
    	function submitClick(){
    		var frm = document.forms[0];
    		if(Validator.Validate(frm,4)){
    			selAll("emailUsers");
    			frm.submit();
    		}
    	}
	</script>
  </head>
  <body>
	<form action="<%=basePath%>/exceptionCfgAction!${oprt }.action?oprt=${oprt }" method="post" 
		onsubmit="return Validator.Validate(this,4);">
		<table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5"  >
	  		<caption>异常信息</caption>
			<tbody>
				<tr>
					<td colspan="2">
						<input type="hidden" id="exceptionId" name="exceptionCfgVo.exceptionId" 
							value="${retObjs[0].exceptionId}" />
						<div class="input_msg">${idMsg }${exceptionIdMsg }${retInfo }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.code"></s:text>
						:
					</th>
					<td>
						<c:if test="${oprt=='add'}">
							<amway:textfield id="exceptionCode" name="exceptionCfgVo.exceptionCode" value="${retObjs[0].exceptionCode }" dataType="Require" msg="请填写异常编码" style="width:250px" maxLine="128"></amway:textfield>
						</c:if>
						<c:if test="${oprt!='add'}">
							<amway:textfield id="exceptionCode" name="exceptionCfgVo.exceptionCode" value="${retObjs[0].exceptionCode }" dataType="Require" msg="请填写异常编码" style="width:250px" maxLine="128" readonly="readonly"></amway:textfield>
						</c:if>
						<font color="red">*</font>
						<div class="input_msg">${exceptionCodeMsg }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.name"></s:text>
						:
					</th>
					<td>
						<amway:textfield id="exceptionName" name="exceptionCfgVo.exceptionName" value="${retObjs[0].exceptionName }" dataType="Require" msg="请填写异常名称" style="width:250px" maxLine="256"></amway:textfield>
						<font color="red">*</font>
						<div class="input_msg">${exceptionNameMsg }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.applicationId"></s:text>
						:
					</th>
					<td>
						<amway:select id="applicationId" name="exceptionCfgVo.applicationId" 
 								dictCode="app_001" listKey="applicationId" listValue="applicationName" 
 								value="${retObjs[0].application.applicationId }" 
 								defaultHK="true" dataType="Require" msg="请选择所属应用" style="width: 250px"/>
						<font color="red">*</font>
						<div class="input_msg">${applicationIdMsg }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.isSendEmail"></s:text>
						:
					</th>
					<td>
						<amway:select id="isSendEmail" name="exceptionCfgVo.isSendEmail" 
							dictCode="true_or_false" listValue="displayname" listKey="code"
							value="${retObjs[0].isSendEmail }"
							dataType="Require" msg="请选择是否发送邮件" style="width: 250px"></amway:select>
						<font color="red">*</font>
						<div class="input_msg">${isSendEmailMsg }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.emailList"></s:text>:
					</th>
					<td>
						<amway:autoComplete id="emailList" name="exceptionCfgVo.emailList" dsType="4" 
							source="user_profile"  filterColNames="chineseName|email" fillColKey="chineseName|userProfileId" style="width: 250px" ></amway:autoComplete>
						<input type="button" value="<s:text name="common.add"></s:text>"
							onclick="addOptionTo('emailList','emailUsers');" />
						&nbsp;&nbsp;
						<input type="button" value="<s:text name="common.delete"></s:text>"
							onclick="delOptionFrom('emailUsers');" />
						<div class="input_msg">${emailListMsg }</div>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<select id="emailUsers" name="exceptionCfgVo.emailUsers" 
							multiple="multiple" style="width: 250px;height: 180px" >
							<c:forEach items="${retObjs[0].emailUserArr }" var="emailUser" >
								<option value="${emailUser[0]}|${emailUser[1]}">${emailUser[0]}|${emailUser[1]}</option>
							</c:forEach>
						</select>
						<div class="input_msg">${emailUsersMsg }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.useState"></s:text>
						:
					</th>
					<td>
						<amway:select id="useState" name="exceptionCfgVo.useState" 
							dictCode="state_options" listValue="displayname" listKey="code"
							value="${retObjs[0].useState }" style="width: 250px" dataType="Require" msg="请选择状态"></amway:select>
						<font color="red">*</font>
						<div class="input_msg">${useStateMsg }</div>
					</td>
				</tr>
				<tr>
					<th>
						<s:text name="exception.remark"></s:text>:
					</th>
					<td>
						<amway:textarea rows="5" cols="40" id="remark" name="exceptionCfgVo.remark" value="${retObjs[0].remark }"
							style="width:250px" dataType="LimitB" max="255" msg="备注长度不能超过255个字符" maxLine="255"></amway:textarea>
						<div class="input_msg">${remarkMsg }</div>
					</td>
				</tr>
				<tr height="20"><td colspan="2">&nbsp;</td></tr>
				<tr align="center">
					<td colspan="2">
						<input type="button"
							value="<s:text name="common.save"></s:text>" onclick="submitClick();"
							style="display: ${oprt=='add'?(_rights_.sysManage_exception_add):(_rights_.sysManage_exception_modify) }"/>
						&nbsp;&nbsp;
						<input type="reset"
							value="<s:text name="common.reset"></s:text>" />
						&nbsp;&nbsp;
						<input type="button"
							value="<s:text name="common.cancel"></s:text>" onclick="window.close();" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:text name="exception.attention">
							<s:param>
								<font color="red">*</font>
							</s:param>
						</s:text>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
  </body>
</html>