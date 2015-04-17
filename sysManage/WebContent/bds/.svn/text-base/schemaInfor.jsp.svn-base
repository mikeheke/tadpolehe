<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据服务编辑</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<script type="text/javascript">
		function selectDataSource(curEle){
			var curEleValue = curEle.value;
			
			var sql1 = document.getElementById("sql1");
			var sql2 = document.getElementById("sql2");
			var sql3 = document.getElementById("sql3");
			
			var sql = document.getElementById("sql");
			var jndi = document.getElementById("jndi");
			
			var ws1 = document.getElementById("ws1");
			var ws2 = document.getElementById("ws2");
			var ws3 = document.getElementById("ws3");
			var ws4 = document.getElementById("ws4");
			var ws5 = document.getElementById("ws5");
			var ws6 = document.getElementById("ws6");
			var ws7 = document.getElementById("ws7");
			var ws8 = document.getElementById("ws8");
			
			var wa = document.getElementById("webserviceAddress");
			var wf = document.getElementById("webserviceFunction");
			
			if(curEleValue == "sql"){
				sql1.style.display = '';
				sql2.style.display = '';
				sql3.style.display = '';
				
				sql.dataType = 'Require';
				jndi.dataType = 'Require';
				
				ws1.style.display = 'none';
				ws2.style.display = 'none';
				ws3.style.display = 'none';
				ws4.style.display = 'none';
				ws5.style.display = 'none';
				ws6.style.display = 'none';
				ws7.style.display = 'none';
				ws8.style.display = 'none';
				
				wa.dataType='';
				wf.dataType='';
			}else if(curEleValue == "ws"){
				sql1.style.display = 'none';
				sql2.style.display = 'none';
				sql3.style.display = 'none';
				
				sql.dataType = '';
				jndi.dataType = '';
				
				ws1.style.display = '';
				ws2.style.display = '';
				ws3.style.display = '';
				ws4.style.display = '';
				ws5.style.display = '';
				ws6.style.display = '';
				ws7.style.display = '';
				ws8.style.display = '';
				
				wa.dataType='Require';
				wf.dataType='Require';
			}else{
				sql1.style.display = 'none';
				sql2.style.display = 'none';
				sql3.style.display = 'none';
				
				sql.dataType = '';
				jndi.dataType = '';
				
				ws1.style.display = 'none';
				ws2.style.display = 'none';
				ws3.style.display = 'none';
				ws4.style.display = 'none';
				ws5.style.display = 'none';
				ws6.style.display = 'none';
				ws7.style.display = 'none';
				ws8.style.display = 'none';
				
				wa.dataType='';
				wf.dataType='';
			}
		}
		
		//编辑xml数据结构
		function xmlStructureEdit(url){
		
			var submitForm = document.createElement("<form " 
				+ " action='" + url + "'"
				+ " method='POST' " 
				+ " target='_blank'>" 
				+ " </form>");
			var dataStructureXml = document.getElementById("dataStructureXml");
			var dataStructureXmlNew = dataStructureXml.cloneNode(true);
			submitForm.appendChild(dataStructureXmlNew);
    		document.body.appendChild(submitForm);
    		submitForm.submit();
			submitForm.parentNode.removeChild(submitForm);
		}
	</script>
  </head>

  <body>
  	<div>
  	  <form action="${pageContext.request.contextPath}/bdsSchemaInforAction!${oprt }.action"
  	 		method="post" onsubmit="return Validator.Validate(this,4);">
   		<table class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5">
   		  <caption>数据服务编辑</caption>
   		  <tbody>
   			<tr>
			  <td colspan="2">
				<input type="hidden" id="bdsSchemaInforId" name="bdsSchemaInforVo.bdsSchemaInforId" 
					value="${retObjs[0].bdsSchemaInforId }">
				<div class="input_msg">${idMsg }${bdsSchemaInforIdMsg }${retInfo }</div>
			  </td>
			</tr>
   			<tr>
			  <th>数据服务编码<font color=red >*</font>：</th>
   			  <td>
   			  	<amway:textfield id="bdsSchemaInforCode" name="bdsSchemaInforVo.bdsSchemaInforCode" 
   			  		value="${retObjs[0].bdsSchemaInforCode }" style="width:250px"  
   			  		dataType="Require" maxLine="128" msg="请填写数据服务编码"></amway:textfield>
   			  	<div class="input_msg">${bdsSchemaInforCodeMsg }</div>
   			  </td>
   			</tr>
   			<tr>
   			  <th>英文名称<font color=red >*</font>：</th>
   			  <td>
   			  	<amway:textfield id="bdsSchemaInforNameEng" name="bdsSchemaInforVo.bdsSchemaInforNameEng" 
   			  		value="${retObjs[0].bdsSchemaInforNameEng }" style="width:250px" 
   			  		dataType="Require" maxLine="256" msg="请填写英文名称"></amway:textfield>
   			    <div class="input_msg">${bdsSchemaInforNameEngMsg }</div>
   			  </td>
   			</tr>
   			<tr>
   			  <th>中文名称<font color=red >*</font>：</th>
   			  <td>
   			  	<amway:textfield id="bdsSchemaInforNameCna" name="bdsSchemaInforVo.bdsSchemaInforNameCna" 
   			  		value="${retObjs[0].bdsSchemaInforNameCna }" style="width:250px" 
   			  		dataType="Require" maxLine="256" msg="请填写中文名称"></amway:textfield>
   			  	<div class="input_msg">${bdsSchemaInforNameCnaMsg }</div>
   			  </td>
   			</tr>
   			<tr>
   			  <th>所属应用<font color=red >*</font>：</th>
   			  <td>
   			   <c:if test="${session._application_.applicationId == 1 }">
   				<amway:select id="applicationId" name="bdsSchemaInforVo.applicationId" 
  								dictCode="app_001" listKey="applicationId" listValue="applicationName" 
  								value="${retObjs[0].application.applicationId }"  style="width:250px" 
  								defaultHK="true" dataType="Require" msg="请选择所属应用"/>
  				<div class="input_msg">${applicationIdMsg }</div>
  				</c:if>
  				<c:if test="${session._application_.applicationId != 1 }">
  			 		${session._application_.applicationName}
	   			   <input type="hidden" name="bdsSchemaInforVo.applicationId"  value="${session._application_.applicationId}" />	
  			  	</c:if>
   			  </td>
   			</tr>
   			<tr>
   			  <th>数据来源类型<font color=red >*</font>：</th>
   			  <td>
   			  	<amway:select id="bdsSchemaInforType" name="bdsSchemaInforVo.bdsSchemaInforType"  value="${retObjs[0].bdsSchemaInforType}"
   			  		dictCode="bds_schemaInfor_type" listKey="code" listValue="displayname" style="width:250px"
   			  		dataType="Require" msg="请选择数据来源类型" onchange="selectDataSource(this);"></amway:select>
	   			<div class="input_msg">${bdsSchemaInforTypeMsg }</div>
   		 	</td>
   		  </tr>
   		  <tr id="sql1" style="display: ${retObjs[0].bdsSchemaInforType=='sql'?'':'none'}">
   			<td><font color="blue"><b>SQL数据源参数设置</b></font></td>
   			<td>&nbsp;</td>
   		  </tr>
   		  <tr id="sql2" style="display: ${retObjs[0].bdsSchemaInforType=='sql'?'':'none'}">
   			<th>SQL<font color=red >*</font>：</th>
   			<td>
   				<amway:textarea id="sql" name="bdsSchemaInforVo.sql" value="${retObjs[0].sql }" rows="5" cols="80"
   					dataType="${retObjs[0].bdsSchemaInforType=='sql'?'Require|LimitB':'LimitB'}" max="1000" maxLine="1000" 
   					msg="请填写SQL"></amway:textarea>
   			  <div class="input_msg">${sqlMsg }</div>
   			</td>
   		  </tr>
   		  <tr id="sql3" style="display: ${retObjs[0].bdsSchemaInforType=='sql'?'':'none'}">
   			<th>JNDI<font color=red >*</font>：</th>
   			<td>
   				<amway:textfield id="jndi" name="bdsSchemaInforVo.jndi" value="${retObjs[0].jndi }"
   					dataType="${retObjs[0].bdsSchemaInforType=='sql'?'Require':''}" maxLine="256" 
   					msg="请填写JNDI" style="width:250px"></amway:textfield>
   			  <div class="input_msg">${jndiMsg }</div>
   			</td>
   		  </tr>
		  <tr id="ws1" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<td><font color="blue"><b>WebService数据源参数设置</b></font></td>
   			<td>&nbsp;</td>
   		  </tr>
   		  <tr id="ws2" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>WebService地址<font color=red >*</font>：</th>
   			<td>
   				<amway:textfield id="webserviceAddress" name="bdsSchemaInforVo.webserviceAddress" 
   					value="${retObjs[0].webserviceAddress }" dataType="${retObjs[0].bdsSchemaInforType=='ws'?'Require':''}" maxLine="256" 
   					msg="请填写WebService地址" style="width:250px"></amway:textfield>
			  <div class="input_msg">${webserviceAddressMsg }</div>
			</td>
   		  </tr>
   		  <tr id="ws3" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>WebService命名空间：</th>
   			<td>
   				<amway:textfield id="webserviceNamespace" name="bdsSchemaInforVo.webserviceNamespace" 
   					value="${retObjs[0].webserviceNamespace }" maxLine="256" 
   					style="width:250px"></amway:textfield>
			  <div class="input_msg">${webserviceNamespaceMsg }</div>
			</td>
   		  </tr>
   		  <tr id="ws4" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>WebService方法<font color=red >*</font>：</th>
   			<td>
   				<amway:textfield id="webserviceFunction" name="bdsSchemaInforVo.webserviceFunction" 
   					value="${retObjs[0].webserviceFunction }" dataType="${retObjs[0].bdsSchemaInforType=='ws'?'Require':''}" maxLine="256" 
   					msg="请填写WebService方法" style="width:250px"></amway:textfield>
			  <div class="input_msg">${webserviceFunctionMsg }</div>
			</td>
   		  </tr>
   		  <tr id="ws5" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>WebService请求封装节点：</th>
   			<td>
   				<amway:textfield id="webserviceReqRoot" name="bdsSchemaInforVo.webserviceReqRoot" 
   					value="${retObjs[0].webserviceReqRoot }" maxLine="50" 
   					style="width:250px"></amway:textfield>
			  <div class="input_msg">${webserviceReqRootMsg }</div>
			</td>
   		  </tr>
   		  <tr id="ws6" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>WebService返回封装节点：</th>
   			<td>
   				<amway:textfield id="webserviceResRoot" name="bdsSchemaInforVo.webserviceResRoot" 
   					value="${retObjs[0].webserviceResRoot }" maxLine="50" 
   					style="width:250px"></amway:textfield>
			  <div class="input_msg">${webserviceResRootMsg }</div>
			</td>
   		  </tr>
   		  <tr id="ws7" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>认证名：</th>
   			<td>
   				<amway:textfield id="webserviceUser" name="bdsSchemaInforVo.webserviceUser" 
   					value="${retObjs[0].webserviceUser }" maxLine="20"
   					style="width:250px"></amway:textfield>
   			  <div class="input_msg">${webserviceUserMsg }</div>
   			</td>
   		  </tr>
   		  <tr id="ws8" style="display: ${retObjs[0].bdsSchemaInforType=='ws'?'':'none'}">
   			<th>密码：</th>
   			<td>
   				<amway:textfield id="webservicePwd" name="bdsSchemaInforVo.webservicePwd" 
   					value="${retObjs[0].webservicePwd }" maxLine="20"
   					style="width:250px"></amway:textfield>
			  <div class="input_msg">${webservicePwdMsg }</div>
			</td>
   		  </tr>
   		  <tr>
   			<td><font color="blue"><b>返回数据定义<font color=red >*</font></b></font></td>
   			<td><div class="input_msg">${dataStructureXmlMsg }</div></td>
   		  </tr>
   		  <tr>
   		  	<td width="40%">&nbsp;</td>
   			<td align="left" width="60%">
<c:choose>
  <c:when test="${oprt=='add'}">
<textarea id="dataStructureXml" name="bdsSchemaInforVo.dataStructureXml" 
   	dataType="Require" msg="请填写数据服务xml文件" rows="12" cols="80">
<table>
  <column>
    <name>code</name>
    <enName>code</enName>
    <cnName>编码</cnName>
    <colType>字符</colType>
    <required>是</required>
    <maxLen>128</maxLen>
    <unique>是</unique>
    <regex></regex>
    <refCode></refCode>
    <refCol></refCol>
  </column>
  <column>
    <name>displayname</name>
    <enName>displayname</enName>
    <cnName>名称</cnName>
    <colType>字符</colType>
    <required>是</required>
    <maxLen>256</maxLen>
    <unique>否</unique>
    <regex></regex>
    <refCode></refCode>
    <refCol></refCol>
  </column>
  <column>
    <name>displayname_en</name>
    <enName>displaynameEn</enName>
    <cnName>英文名称</cnName>
    <colType>字符</colType>
    <required>是</required>
    <maxLen>256</maxLen>
    <unique>否</unique>
    <regex></regex>
    <refCode></refCode>
    <refCol></refCol>
  </column>
  <column>
    <name>displayname_tc</name>
    <enName>displaynameTc</enName>
    <cnName>繁体中文名称</cnName>
    <colType>字符</colType>
    <required>是</required>
    <maxLen>256</maxLen>
    <unique>否</unique>
    <regex></regex>
    <refCode></refCode>
    <refCol></refCol>
  </column>
</table>
</textarea>&nbsp;&nbsp;
  </c:when>
  
  <c:otherwise>
<textarea id="dataStructureXml" name="bdsSchemaInforVo.dataStructureXml" 
   	dataType="Require" msg="请填写数据服务xml文件" rows="12" cols="80">
${retObjs[0].dataStructureXml }
</textarea>&nbsp;&nbsp;
  </c:otherwise>
</c:choose>	
	   			  <input type="button" id="list" name="list" style="height: 75;width: 90" value="编辑数据结构" 
	   				onclick="xmlStructureEdit('${pageContext.request.contextPath}/bdsXmlStructureAction!list.action');"/>
	   			</td>
	   		  </tr>
	   		  <tr>
	   			<th>状态<font color=red >*</font>：</th>
	   			<td>
	   			  <amway:select id="state" name="bdsSchemaInforVo.state" 
						dictCode="state_options" listKey="code" listValue="displayname" 
						value="${retObjs[0].state }" dataType="Require" msg="请选择状态"  style="width:250px"/>
				  <div class="input_msg">${stateMsg }</div>
	   			</td>
	   		  </tr>
			  <tr>
	   			<th>备注：</th>
	   			<td>
	   				<amway:textfield id="remark" name="bdsSchemaInforVo.remark" value="${retObjs[0].remark }" 
	   				dataType="LimitB" max="2048" msg="备注长度超过2048个字符" style="width:250px"></amway:textfield>
	   				<div class="input_msg">${remarkMsg }</div>
				</td>
	   		  </tr>
	   		  <tr><td colspan="2">&nbsp;</td></tr>
	   		  <tr>
	   			<td align="center" colspan="2">
	   			  <input type="submit" value="保存" >&nbsp;&nbsp;&nbsp;&nbsp;
	   			  <input type="button" value="取消" onclick="window.close();">
	   			</td>
	   		  </tr>
	   		  <tr>
	   			<td align="left" colspan="2">
	   			  [注]带<font color=red >*</font>号为必填项
	   		    </td>
	   		  </tr>
   		  </tbody>
   		</table>
	  </form>
	</div>
  </body>
</html>