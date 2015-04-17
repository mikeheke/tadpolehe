<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.from.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>

	<script type="text/javascript">
		//
	</script>

  </head>
  
  <body style="margin: 10px">
 	<form action="fromAction!" method="post" onsubmit="return Validator.Validate(this,4);">
	  	<div style="width:100%;height:220px;overflow: auto;" >
	  		<table id="qryList" class="query_list">
	  			<thead>
	  				<tr>
	  					<th width="5%" >
	  						<input type="checkbox" onclick="setTabCBAllSel(this,'qryList');"/>
	  					</th>
	  					<th width="30%" ><s:text name="query.from.tableName" /></th>
	  					<th width="10%" ><s:text name="query.from.tableAlias" /></th>
	  					<th width="10%" ><s:text name="query.from.orderNo" /></th>
	  					<th width="45%" ><s:text name="query.from.fromResult" /></th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<s:iterator value="%{getQueryFroms()}"  var="from" status="stat">
	  					<tr >
	  						<td id="fromId_<s:property value="#from.fromId"/>" >
	  							<input type="checkbox" name="fromVo.ids" value='${from.fromId }|${from.oprtFlag }' onclick="setCurMdfIdValue(this.parentNode.parentNode);"/>
	  						</td>
	  						<td id='tableName_<s:property value="#from.fromId"/>' >
	  							<s:property  value="#from.tableName"/>&nbsp;
	  						</td>
	  						<td id='tableAlias_<s:property value="#from.fromId"/>' >
	  							<s:property value="#from.tableAlias"/>&nbsp;
	  						</td>
	  						<td id='orderNo_<s:property value="#from.fromId"/>' >
	  							<s:property value="#from.orderNo"/>&nbsp;
	  						</td>
	  						<td id='fromResult_<s:property value="#from.fromId"/>' >
	  							<s:property value="#from.fromResult"/>&nbsp;
	  						</td>
	  					</tr>
	  				</s:iterator>
	  			</tbody>
	  		</table>
	  	</div>
	  	<div style="50px">&nbsp;</div>
	  	<div>
	  		<table id="editTable" class="form_item2">
				<thead>
					<tr>
						<th><s:text name="query.base.propertyName" /></th>
						<th><s:text name="query.base.propertyValue" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<input type="hidden" id="id" name="fromVo.id"  value="${fromVo.id }"/>
							<div class="input_msg">${idMsg }</div>
						</td>
						
					</tr>
					<tr>
						<th><s:text name="query.from.tableName" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="tableName" name="fromVo.tableName"
								dictCode="${sysTables }" value="${fromVo.tableName }"
								onchange="setElementsValueByIds('tableAlias|fromResult',this.value);"
								dataType="Require" msg="请选择表名" style="width:200" onchange="document.forms[0].fromResult.value=this.value;"
							/>
							<input type="button" value="搜索" 
								onclick="popup(600,500,'${_sysApplication_.goToUrl}/fromAction!popupSelTbName.action')"
							/>
							<div class="input_msg">${tableNameMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.tableAlias" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="tableAlias" name="fromVo.tableAlias" value="${fromVo.tableAlias }" 
								dataType="Require" maxLine="32" msg="请填写表别名" style="width:200"/>
							<div class="input_msg">${tableAliasMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.orderNo" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="orderNo" name="fromVo.orderNo" value="${fromVo.orderNo }"
								dataType="Integer" msg="排序应为整数" maxLine="8" style="width:200" />
							<input type="button"  value="生成" 
								onclick="setEleValueJson('${pageContext.request.contextPath}/fromAction!findOrderNo.action','orderNo');" />
							<div class="input_msg">${orderNoMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.fromResult" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="fromResult" name="fromVo.fromResult" value="${fromVo.fromResult }" 
								dataType="Require" style="width:200" msg="请选择表名" readonly="readonly"/>
							<div class="input_msg">${fromResultMsg }</div>
						</td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="add" name="add.action" value="<s:text name="common.add" />" 
								onclick="addBtnOnclick(this);" />&nbsp; 
							<input type="button" id="delete" name="delete.action" value="<s:text name="common.delete" />" 
								onclick="deleteBtnOnclick(this);" title="<s:text name="common.delete.info" />"/>&nbsp;
							<input type="button" id="modify" name="modify.action" value="<s:text name="common.modify" />" 
								onclick="modifyBtnOnclick(this);" />&nbsp;&nbsp;&nbsp;&nbsp;
							
							<input type="reset" id="cancel" name="cancel.action" value="<s:text name="common.cancel" />" 
								onclick="cancelBtnOnclick(this);" />&nbsp;
							<input type="submit" id="save" name="save.action" value="<s:text name="common.save" />" 
								disabled="disabled" />
						</td>
					</tr>
				</tbody>
			</table>
	  	</div>
	</form>
  </body>
</html>
