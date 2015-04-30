<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.show.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="cache-control" content="no-store">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
	<s:if test="#session._query_show_.externalJs != null">
	<script type="text/javascript" src="${_query_show_.externalJs }"></script>
	</s:if>
	
	<script type="text/javascript">
		function pressEnter(event) {
			document.forms.ec.ec_eti.value='';
			document.forms.ec.ec_f_a.value='fa';
			document.forms.ec.ec_p.value='1';
			document.forms.ec.setAttribute('action','${pageContext.request.contextPath}/showAction.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}');
			document.forms.ec.setAttribute('method','post');
			document.forms.ec.setAttribute('target','_self');
			document.forms.ec.submit();
		}
		
		//点击查询按钮
		function clickQueryBtn() {
			document.forms.ec.ec_eti.value='';
			document.forms.ec.ec_f_a.value='fa';
			document.forms.ec.ec_p.value='1';
			document.forms.ec.setAttribute('action','${pageContext.request.contextPath}/showAction.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}');
			document.forms.ec.setAttribute('method','post');
			document.forms.ec.setAttribute('target','_self');
			
			if(Validator.Validate(document.forms[0],4)) {
				document.forms.ec.submit();
			}
		}
		
		/**
		* 当各个小应用在页面上有引用“所属应用”下拉框时，
		* 禁用该下拉选项框。
		* 增加人：黄波
		*/
		function initAppSelect(){
			
			var appCode = '${_application_.applicationCode}';
			var appId = '${_application_.applicationId}';
			if(appCode == "00"){
				return;
			}
			
			var japp = $("#applicationId");
			var jrole= $("#roleId");
			
			//当页面上没有 “所属应用” 或者 “角色名称”下拉框时，返回不做操作。
			if(!japp.get(0) || !jrole.get(0)){
				return;
			}
			
			//如果不是单选的下拉选项框类型，返回不做操作。
			if(japp.attr("type") != "select-one" || jrole.attr("type") != "select-one"){
				return;
			}
			
			japp.val(appId);
			japp.hide();
			japp.trigger("change");
			
			var jtd = japp.parent();
			var jth = jtd.prev();
			jtd.hide();
			jth.hide();
			
			$("#ec").submit(function(){
				japp.attr("disabled",false);
				return true;
			});
		}
		
		/**
		* 一、功能说明(提出应用为《财务报表数据库系统》)
		* 当配置的通用查询有复选框时,而这些复选框中的一些有可能会被禁用,
		* 所以在此判断如果被选中的复选框(除列头的复选框)都是被禁用的,则返回false.
		* 否则返回true.
		* 二、使用说明
		* 在通用查询的按钮配置步骤,如配置"删除"按钮时.
		* 在"执行JavaScript"右侧的输入框里添加checkAllDisabled()即可.
		* 如果输入框里有其他的方法,则在其他的方法后面加上 
		* if(!ret){return false} ret = checkAllDisabled()
		* 增加人：黄波
		* 时间 ：2013/10/8
		*/
		function checkAllDisabled() {
			var flag = false;
			$('input:checkbox').each(
					function() {
						var jitem = $(this);
						if (jitem.attr('checked') == true
								&& !jitem.attr('disabled')
								&& jitem.parent().attr('class') != 'tableHeader') {
							flag = true;
						}
					});
			if(!flag){
				alert("请选择一个可编辑的行.");
			}
			return flag;
		}
		
	</script>
	
  </head>
  
  <body style="margin: 0px" onload="initAppSelect();initQueryShow('${pageContext.request.contextPath}')">
  	<form id="ec" method="post">
	<div>
		<div>
			<table width="100%" class="form_item2">
				<c:set var="num" value="0"></c:set>
				<c:forEach items="${_query_show_.queryUIWheres }" var="where" varStatus="status">
					<c:if test="${num == 0 || num mod 3 == 0}"><tr></c:if>
					    <th style="width: 12%">
							${where.des }<c:if test="${where.isRequired==1 || where.regExp != null}"><font color=red>*</font></c:if>
						</th>
						<td style="width: 18%">
							<c:choose>
							<c:when test="${where.controlType == 0 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" onkeypress="javascript:if (event.keyCode == 13) {pressEnter(event);return false;}" />
							</c:when>
							<c:when test="${where.controlType == 0 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" dataType="Require" msg="此项不能为空" style="width: 160px" onkeypress="javascript:if (event.keyCode == 13) {pressEnter(event);return false;}"/>
							</c:when>
							<c:when test="${where.controlType == 0 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" style="width: 160px" onkeypress="javascript:if (event.keyCode == 13) {pressEnter(event);return false;}"/>
							</c:when>
							<c:when test="${where.controlType == 1 && where.parTagId == null && where.regExp != null}">
								<amway:select id="${where.whereCode }" name="${where.whereCode }" dictCode="${where.dataCoding }" value="${where.defaultValue }" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px"/>
							</c:when>
							<c:when test="${where.controlType == 1 && where.parTagId == null && where.isRequired==1}">
								<amway:select id="${where.whereCode }" name="${where.whereCode }" dictCode="${where.dataCoding }" value="${where.defaultValue }" dataType="Require" msg="此项不能为空" style="width: 160px"/>
							</c:when>
							<c:when test="${where.controlType == 1 && where.parTagId == null}">
								<amway:select id="${where.whereCode }" name="${where.whereCode }" dictCode="${where.dataCoding }" value="${where.defaultValue }" style="width: 160px"/>
							</c:when>
							<c:when test="${where.controlType == 1 && where.parTagId != null && where.regExp != null}">
								<amway:select id="${where.whereCode }" name="${where.whereCode }" dictCode="${where.dataCoding }" value="${where.defaultValue }" parentItem="${where.parTagId}" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px"/>
							</c:when>
							<c:when test="${where.controlType == 1 && where.parTagId != null && where.isRequired==1}">
								<amway:select id="${where.whereCode }" name="${where.whereCode }" dictCode="${where.dataCoding }" value="${where.defaultValue }" parentItem="${where.parTagId}" dataType="Require" msg="此项不能为空" style="width: 160px"/>
							</c:when>
							<c:when test="${where.controlType == 1 && where.parTagId != null}">
								<amway:select id="${where.whereCode }" name="${where.whereCode }" dictCode="${where.dataCoding }" value="${where.defaultValue }" parentItem="${where.parTagId}" style="width: 160px"/>
							</c:when>
							<c:when test="${where.controlType == 2 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 2 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项不能为空" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 2 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 3 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 3 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Require" msg="此项不能为空" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 3 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 4 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 4 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" dataType="Require" msg="此项不能为空" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 4 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 5 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 5 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" dataType="Require" msg="此项不能为空" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 5 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 6 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 6 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项不能为空" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 6 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 7 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 7 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Require" msg="此项不能为空" style="width: 160px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 7 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" style="width: 160px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 8 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 8 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Require" msg="此项不能为空" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 8 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 9 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 9 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Require" msg="此项不能为空" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 9 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" style="width: 160px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							</c:when>
							<c:when test="${where.controlType == 10 && where.regExp != null}">
								<amway:autoComplete id="${where.whereCode }" name="${where.whereCode }" source="${where.dataCoding }" value="${where.defaultValue }" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 10 && where.isRequired==1}">
								<amway:autoComplete id="${where.whereCode }" name="${where.whereCode }" source="${where.dataCoding }" value="${where.defaultValue }" dataType="Require" msg="此项不能为空" style="width: 160px" />
							</c:when>
							<c:when test="${where.controlType == 10}">
								<amway:autoComplete id="${where.whereCode }" name="${where.whereCode }" source="${where.dataCoding }" value="${where.defaultValue }" style="width: 160px" />
							</c:when>
							
							
							<c:when test="${where.controlType == 11 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							            到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" />	
							</c:when>
							<c:when test="${where.controlType == 11 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" dataType="Require" msg="此项不能为空" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							           到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 dataType="Require" msg="此项不能为空" />
							</c:when>
							<c:when test="${where.controlType == 11 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							          到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 />
							</c:when>
							<c:when test="${where.controlType == 12 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							          到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" />	
							</c:when>
							<c:when test="${where.controlType == 12 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" dataType="Require" msg="此项不能为空" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							          到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'yyyy-MM'})"  dataType="Require" msg="此项不能为空" />	
							</c:when>
							<c:when test="${where.controlType == 12 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
								到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />				 
							</c:when>							
							<c:when test="${where.controlType == 13 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
								 到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" />	
							</c:when>
							<c:when test="${where.controlType == 13 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项不能为空" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}', '${where.controlType}');"/>
							          到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项不能为空" />
							</c:when>
							<c:when test="${where.controlType == 13 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							           到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</c:when>
							<c:when test="${where.controlType == 14 && where.regExp != null}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							           到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确" />				 
							</c:when>
							<c:when test="${where.controlType == 14 && where.isRequired==1}">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Require" msg="此项不能为空" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							          到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'HH:mm:ss'})"  dataType="Require" msg="此项不能为空" />				 
							</c:when>
							<c:when test="${where.controlType == 14 }">
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" style="width: 71px" onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"/>
							           到&nbsp;<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 71px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"	 onfocus="WdatePicker({dateFmt:'HH:mm:ss'})" />				 
							</c:when>
							
							<c:otherwise>
								<amway:textfield id="${where.whereCode }" name="${where.whereCode }" value="${where.defaultValue }" style="width: 160px" onkeypress="javascript:if (event.keyCode == 13) {pressEnter(event);return false;}"/>
							</c:otherwise>
							</c:choose>
						</td>
					
					<c:choose>
						<c:when test="${(num+1) mod 3 == 0}">
							<td style="width: *%">&nbsp;</td></tr>
						</c:when>
						<c:when test="${status.last }">
							<td style="width: *%">&nbsp;</td>
						</c:when>
						<c:otherwise>
							<td style="width: 4%">&nbsp;</td>
						</c:otherwise>
					</c:choose>
					<c:set var="num" value="${num+1}" ></c:set>
					
					<c:if test="${where.controlType == 5 || where.controlType == 6 || where.controlType == 7 || where.controlType == 9}">
						<c:if test="${num != 0 && num mod 3 == 0}"><tr></c:if>
						
						<th style="width: 10%">
							到<c:if test="${where.isRequired==1 || where.regExp != null}"><font color=red>*</font></c:if>
						</th>	
						<td>
							<c:choose>
								<c:when test="${where.regExp != null}">
									<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 160px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"
										dataType="Custom" regexp="${where.regExp}" msg="此项格式不正确"
										<c:if test="${where.controlType == 6}"> onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'})</c:if> 
										<c:if test="${where.controlType == 7}"> onfocus=WdatePicker({dateFmt:'yyyy-MM'})</c:if> 
										<c:if test="${where.controlType == 9}"> onfocus=WdatePicker({dateFmt:'HH:mm:ss'})</c:if>  
									/>
								</c:when>
								<c:when test="${where.isRequired==1}">
									<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 160px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"
										dataType="Require" msg="此项不能为空"
										<c:if test="${where.controlType == 6}"> onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'})</c:if> 
										<c:if test="${where.controlType == 7}"> onfocus=WdatePicker({dateFmt:'yyyy-MM'})</c:if> 
										<c:if test="${where.controlType == 9}"> onfocus=WdatePicker({dateFmt:'HH:mm:ss'})</c:if>  
										
									/>
								</c:when>
								<c:otherwise>
									<input name="${where.whereCode }" value="${where.defaultValue2[1] }" style="width: 160px"  onchange="checkBetween(this, '${where.whereCode}', '${where.controlType}');"
										<c:if test="${where.controlType == 6}"> onfocus=WdatePicker({dateFmt:'yyyy-MM-dd'})</c:if> 
										<c:if test="${where.controlType == 7}"> onfocus=WdatePicker({dateFmt:'yyyy-MM'})</c:if> 
										<c:if test="${where.controlType == 9}"> onfocus=WdatePicker({dateFmt:'HH:mm:ss'})</c:if>  
									/>
								</c:otherwise>
							</c:choose>
						</td>
						
						<c:choose>
							<c:when test="${(num+1) mod 3 == 0}">
								<td style="width: *%">&nbsp;</td></tr>
							</c:when>
							<c:when test="${status.last }">
								<td style="width: *%">&nbsp;</td>
							</c:when>
							<c:otherwise>
								<td style="width: 4%">&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<c:set var="num" value="${num+1}" ></c:set>
					</c:if>
				</c:forEach>
				
				<%-- 隐藏'查询'按钮
				<tr>
					<td colspan="20" style="text-align: left;">
					<input type="button" value="&nbsp;查询&nbsp;" onclick="clickQueryBtn();" />
						&nbsp;&nbsp;
					</td>
				</tr>
				 --%>
			</table>
			
			<c:forEach items="${_query_show_.queryHiddenWheres }" var="where">
				<c:forEach items="${where.defaultValue2 }" var="value">
					<input type="hidden" name="${where.whereCode }" value="${value }" />
				</c:forEach>
			</c:forEach>
		</div>
		<div id="oprtInfo" style="height: 10px;color: red">${retInfo }</div>
		<div style="border: buttonface 1px solid;">
			<table>
				<tr>
					<td>
						<s:iterator value="#session._query_show_.buttons"  var="button" >
							<input type="submit" name='${button.buttonNo }' value='${button.buttonName }'
								style="display: ${_rights_[button.buttonNo ]}"
								onclick="
									<c:if test="${button.executeJs!=null && button.executeJs!=''}">
										var ret = ${button.executeJs };
										if(!ret){
											return false;	
										}
									</c:if>
									<c:choose>
										<c:when test="${button.openType!=null && button.openType!='' && fn:startsWith(param._appContext_,'http:')}">
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:when test="${button.openType!=null && button.openType!='' && param._appContext_!='' && param._appContext_!=null}">
											return buttonOnclick(this,'/${param._appContext_}/${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:otherwise>
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:otherwise>
									</c:choose>
								" />&nbsp;
						</s:iterator>				
					</td>
				</tr>
			</table>
		</div>
		<div>
			<ec:table 
				items="retObjs"
				var = "p"
				action="${pageContext.request.contextPath}/showAction.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}"
				imagePath="${pageContext.request.contextPath}/common/images/table/${_amwayCss_ }/*.gif"
				title="${_query_show_.queryName }"
				width="${_query_show_.tableWidth}"
				retrieveRowsCallback="limit"
				filterRowsCallback="limit"
				sortRowsCallback="limit"
				autoIncludeParameters="false"
				locale="${WW_TRANS_I18N_LOCALE}"
			>
				<ec:exportXls view="xls" fileName="${_query_show_.queryName}.xls"
					action="${pageContext.request.contextPath}/exportShowAction!export.action?queryCode=${param.queryCode }&_appContext_=${param._appContext_}">
				</ec:exportXls>
				<ec:row style="height:20px">
					<ec:columns autoGenerateColumns="com.amway.frm.query.vo.AutoGenerateColumns" />
				</ec:row>
			</ec:table>
		</div>
		<div style="border: buttonface 1px solid;">
			<table>
				<tr>
					<td>
						<s:iterator value="#session._query_show_.buttons"  var="button" >
							<input type="submit" name='${button.buttonNo }' value='${button.buttonName }'
								style="display: ${_rights_[button.buttonNo ]}"
								onclick="
									<c:if test="${button.executeJs!=null && button.executeJs!=''}">
										var ret = ${button.executeJs };
										if(!ret){
											return false;	
										}
									</c:if>
									<c:choose>
										<c:when test="${button.openType!=null && button.openType!='' && fn:startsWith(param._appContext_,'http:')}">
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:when test="${button.openType!=null && button.openType!='' && param._appContext_!='' && param._appContext_!=null}">
											return buttonOnclick(this,'/${param._appContext_}/${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:when>
										<c:otherwise>
											return buttonOnclick(this,'${button.subUrl }','${button.openType }', ${_query_show_.isRefresh});
										</c:otherwise>
									</c:choose>
								" />&nbsp;
						</s:iterator>				
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form>
  </body>
</html>