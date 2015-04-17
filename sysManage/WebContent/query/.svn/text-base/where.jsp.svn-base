<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.where.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
  </head>
  
  <body style="margin: 10px">
   	<form action="whereAction!" method="post" onsubmit="return Validator.Validate(this,4);">
	  	<div style="width:100%;height:220px;overflow: auto;">
	  		<table id="qryList" class="query_list" style="width:400%">
	  			<thead>
	  				<tr>
	  					<th width="1%" >
	  						<input type="checkbox" onclick="setTabCBAllSel(this,'qryList');"/>
	  					</th>
	  					<th width="5%" ><s:text name="query.where.whereCode" /></th>
	  					<th width="5%" ><s:text name="query.from.tableName" /></th>
	  					<th width="5%" ><s:text name="query.select.fieldName" /></th>
	  					<th width="3%" ><s:text name="query.select.dataType" /></th>
	  					<th width="5%" ><s:text name="query.select.des" /></th>
	  					<th width="2%" ><s:text name="query.from.orderNo" /></th>
	  					<th width="3%" ><s:text name="query.where.isInnerLink" /></th>
	  					<th width="3%" ><s:text name="query.where.isUserIn" /></th>
	  					<th width="3%" ><s:text name="query.where.controlType" /></th>
	  					<th width="5%" ><s:text name="query.select.dataCoding" /></th>
	  					<th width="3%" ><s:text name="query.where.operator" /></th>
	  					<th width="3%" ><s:text name="query.where.defaultValue" /></th>
	  					<th width="5%" ><s:text name="query.where.linkTable" /></th>
	  					<th width="5%" ><s:text name="query.where.linkField" /></th>
	  					<th width="5%" ><s:text name="query.where.filterField" /></th>
	  					<th width="3%" ><s:text name="query.where.isRequired" /></th>
	  					<th width="5%" ><s:text name="query.where.isIgnoreCase" /></th>
	  					<th width="8%" ><s:text name="query.where.regExp" /></th>
	  					<th width="4%" ><s:text name="query.where.parTagId" /></th>	
	  					<th width="8%" ><s:text name="query.from.fromResult" /></th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<s:iterator value="%{getQueryWheres()}"  var="where" status="stat">
	  					<tr>
	  						<td id="id_<s:property value="#where.whereId"/>" >
	  							<input type="checkbox" name="whereVo.ids" value='${where.whereId }|${where.oprtFlag }' onclick="setCurMdfIdValue(this.parentNode.parentNode);" />
	  						</td>
	  						<td id='whereCode_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.whereCode"/>&nbsp;
	  						</td>
	  						<td id='tableName_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.tableName"/>&nbsp;
	  						</td>
	  						<td id='fieldName_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.fieldName"/>&nbsp;
	  						</td>
	  						<td id='dataType_<s:property value="#where.dataType"/>' >
	  							<s:property  value="#where.dataType"/>&nbsp;
	  						</td>
	  						<td id='des_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.des"/>&nbsp;
	  						</td>
	  						<td id='orderNo_<s:property value="#where.whereId"/>' >
	  							<s:property value="#where.orderNo"/>&nbsp;
	  						</td>
	  						<td id='isInnerLink_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.isInnerLink"/>&nbsp;
	  						</td>
	  						<td id='isUserIn_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.isUserIn"/>&nbsp;
	  						</td>
	  						<td id='controlType_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.controlType"/>&nbsp;
	  						</td>
	  						<td id='dataCoding_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.dataCoding"/>&nbsp;
	  						</td>
	  						<td id='operator_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.operator"/>&nbsp;
	  						</td>
	  						<td id='defaultValue_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.defaultValue"/>&nbsp;
	  						</td>
	  						<td id='linkTable2_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.linkTable"/>&nbsp;
	  						</td>
	  						<td id='linkField_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.linkField"/>&nbsp;
	  						</td>
	  						<td id='filterField_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.filterField"/>&nbsp;
	  						</td>
	  						
	  						<td id='isRequired_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.isRequired"/>&nbsp;
	  						</td>
	  						<td id='isIgnoreCase_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.isIgnoreCase"/>&nbsp;
	  						</td>
	  						<td id='regExp_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.regExp"/>&nbsp;
	  						</td>
	  						<td id='parTagId_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.parTagId"/>&nbsp;
	  						</td>
	  						<td id='whereResult_<s:property value="#where.whereId"/>' >
	  							<s:property  value="#where.whereResult"/>&nbsp;
	  						</td>
	  					</tr>
	  				</s:iterator>
	  			</tbody>
	  		</table>
	  	</div>
	  	<div style="width: 100%">&nbsp;</div>
	  		<table class="form_item2" width="100%">
	  			<tr>
	  				<th><s:text name="query.where.globalWhere" />:</th>
		  			<td>
		  				<amway:textarea id="globalWhere" name="whereVo.globalWhere" 
		  					rows="2" cols="60" value="${_query_.globalWhere}" maxLine="2048" dataType="LimitB" max="2048" msg="全局表达式长度不能超过2048个字符"/>
		  			</td>
		  		</tr>
	  		</table>
	  	<div>
	  		<table id="editTable" class="form_item2">
				<thead>
					<tr>
						<th><s:text name="query.base.propertyName" /></th>
						<th width="180px"><s:text name="query.base.propertyValue" /></th>
						<th width="10px">&nbsp;</th>
						<th><s:text name="query.base.propertyName" /></th>
						<th width="180px"><s:text name="query.base.propertyValue" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5">
							<input type="hidden" id="id" name="whereVo.id"  value="${whereVo.id }"/>
							<div class="input_msg">${idMsg }</div>
						</td>
						
					</tr>
					<tr>
						<th><s:text name="query.where.whereCode" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="whereCode" name="whereVo.whereCode" 
								value="${whereVo.whereCode }" style="width: 180px"
								dataType="Custom" regexp="^[a-zA-Z]" maxLine="256" msg="条件代码应以字母开头"/>
							<div class="input_msg">${whereCodeMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.from.tableName" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="tableName" name="whereVo.tableName"
								dictCode="${froms}" listKey="tableName" listValue="tableAlias" 
								dataType="Require" msg="请选择表名" style="width: 180px;" />
							<div class="input_msg">${tableNameMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.fieldName" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="fieldName" name="whereVo.fieldName"
								dictCode="${sysTableFields }" parentItem="tableName"
								complete="setTableFieldValue();"
								dataType="Require" msg="请填写字段名" style="width: 141px;" />
								
							<input type="button" value="搜索" 
								onclick="popupSelFieldName('${sysTableFields }','tableName')"
							/>	
							<div class="input_msg">${fieldNameMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.dataType" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="dataType" name="whereVo.dataType" 
								dictCode="select_data_type" listKey="code" listValue="displayname" style="width:141" dataType="Require" msg="请选择数据类型"></amway:select>
							<input id="dataTypeButton" type="button"  value="生成" 
								onclick="setDataType('${pageContext.request.contextPath}/selectAction!findTableFieldType.action','${sysTableFields }');" />
							<div class="input_msg">${dataTypeMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.des" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="des" name="whereVo.des" value="${whereVo.des }"
								dataType="Require" maxLine="256" msg="请填写描述" style="width: 180px" />
							<div class="input_msg">${desMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.from.orderNo" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="orderNo" name="whereVo.orderNo" value="${whereVo.orderNo}"
								dataType="Integer" msg="排序应为整数" style="width: 141px" maxLine="8"/>
							<input type="button"  value="生成" 
								onclick="setEleValueJson('${pageContext.request.contextPath}/whereAction!findOrderNo.action','orderNo');" />
							<div class="input_msg">${orderNoMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.where.isInnerLink" />：</th>
						<td>
							<amway:select id="isInnerLink" name="whereVo.isInnerLink"
								dictCode="where_is_inner_link" 
								onchange="isInnerLinkOnchange(this);"
								style="width: 180px"/>
							<div class="input_msg">${isInnerLinkMsg }</div>
						</td>	
						<td>&nbsp;</td>
						<th><s:text name="query.where.isUserIn" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="isUserIn" name="whereVo.isUserIn"
								dictCode="whereIsUserIn" style="width: 180px"/>
							<div class="input_msg">${isUserInMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.where.controlType" />：</th>
						<td>
							<amway:select id="controlType" name="whereVo.controlType"
								dictCode="whereControlType" style="width: 180px" />
							<div class="input_msg">${controlTypeMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.dataCoding" />：</th>
						<td>
							<amway:autoComplete id="dataCoding" name="whereVo.dataCoding" 
							 	source="data_code" 
							 	filterColNames="code|displayname" fillColKey="code" 
							 	style="width: 180"
							/>
							<div class="input_msg">${dataCodingMsg }</div>
						</td>	
					</tr>
					<tr>
						<th><s:text name="query.where.operator" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="operator" name="whereVo.operator"
								dictCode="where_operator" style="width: 180px" dataType="Require" msg="请选择运算符"/>
							<div class="input_msg">${operatorMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.where.defaultValue" />：</th>
						<td>
							<amway:textfield id="defaultValue" name="whereVo.defaultValue" 
								value="${whereVo.defaultValue }" style="width: 180px" maxLine="256"/>
							<div class="input_msg">${defaultValueMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.where.linkTable" />：</th>
						<td>
							<input type="hidden" id="linkTable" name="whereVo.linkTable" />
							<amway:select id="linkTable2" 
								dictCode="${allFroms}" listKey="tableName" listValue="tableAlias" 
								onchange="setLinkTableValue();"
								style="width: 180px;" />
							<div class="input_msg">${linkTableMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.where.linkField" />：</th>
						<td>
							<amway:select id="linkField" name="whereVo.linkField"
								dictCode="${sysTableFields }" parentItem="linkTable2" parentKey="tableName"
								complete="setWhereFieldValue('linkField');"
								style="width: 180px"/>
							<div class="input_msg">${linkFieldMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.where.filterField" />：</th>
						<td>
							<amway:select id="filterField" name="whereVo.filterField"
								dictCode="${sysTableFields }" parentItem="linkTable2" parentKey="tableName"
								complete="setWhereFieldValue('filterField');"
								style="width: 180px"/>
							<div class="input_msg">${filterFieldMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.where.isRequired" />：</th>
						<td>
							<amway:select id="isRequired" name="whereVo.isRequired" 
								dictCode="true_or_false"
								style="width: 180px" value="${whereVo.isRequired}"
							/>
							<div class="input_msg">${isRequiredMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.where.isIgnoreCase" />：</th>
						<td>
							<amway:select id="isIgnoreCase" name="whereVo.isIgnoreCase"
								style="width: 180px"
								dictCode="true_or_false" 
							/>
							<div class="input_msg">${isIgnoreCaseMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.where.regExp" />：</th>
						<td>
							<amway:textfield id="regExp" name="whereVo.regExp" 
								value="${whereVo.regExp }" style="width: 180px" maxLine="256"/>
							<div class="input_msg">${regExpMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.where.parTagId" />：</th>
						<td>
							<amway:textfield id="parTagId" name="whereVo.parTagId" 
								value="${whereVo.parTagId }" style="width: 180px"/>
							<div class="input_msg">${parTagIdMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.from.fromResult" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="whereResult" name="whereVo.whereResult" 
								value="${whereVo.whereResult }"
								dataType="Require" maxLine="400" msg="组合结果不能为空" 
								style="width: 141px"/>
							<input type="button"  value="生成" onclick="setWhereResult();" />
							<div class="input_msg">${whereResultMsg }</div>
						</td>
					</tr>
					<tr><td colspan="5">&nbsp;</td></tr>
					<tr>
						<td colspan="5" align="center">
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
