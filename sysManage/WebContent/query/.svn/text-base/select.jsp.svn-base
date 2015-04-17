<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.select.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
  </head>
  
  <body style="margin: 10px">
 		<form action="selectAction!" method="post" 
 			onsubmit="return Validator.Validate(this,4);">
	  	<div style="width:100%;height:220px;overflow: auto;">
	  		<table id="qryList" class="query_list" style="width:300%">
	  			<thead>
	  				<tr>
	  					<th width="1%" >
	  						<input type="checkbox" onclick="setTabCBAllSel(this,'qryList');"/>
	  					</th>
	  					<th width="5%" ><s:text name="query.from.tableName" /></th>
	  					<th width="5%" ><s:text name="query.select.fieldName" /></th>
	  					<th width="3%" ><s:text name="query.select.fieldAlias" /></th>
	  					<th width="5%" ><s:text name="query.select.selectResult" /></th>
	  					<th width="3%" ><s:text name="query.select.dataType" /></th>
	  					<th width="5%" ><s:text name="query.select.des" /></th>
	  					<th width="3%" ><s:text name="query.from.orderNo" /></th>
	  					<th width="5%" ><s:text name="query.select.dataCoding" /></th>
	  					<th width="4%" ><s:text name="query.select.outFormat" /></th>
	  					<th width="3%" ><s:text name="query.select.btnType" /></th>
	  					<th width="12%" ><s:text name="query.select.dataInput" /></th>
	  					<th width="3%" ><s:text name="query.select.isHidden" /></th>
	  					<th width="8%" ><s:text name="query.select.linkUrl" /></th>
	  					<th width="5%" ><s:text name="query.select.openType" /></th>
	  					<th width="2%" ><s:text name="query.select.colWidth" /></th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<s:iterator value="%{getQuerySelects()}"  var="select" status="stat">
	  					<tr>
	  						<td id="id_<s:property value="#select.selectId"/>" >
	  							<input type="checkbox" name="selectVo.ids" value='${select.selectId }|${select.oprtFlag }' onclick="setCurMdfIdValue(this.parentNode.parentNode)"/>
	  						</td>
	  						<td id='tableName_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.tableName"/>&nbsp;
	  						</td>
	  						<td id='fieldName_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.fieldName"/>&nbsp;
	  						</td>
	  						<td id='fieldAlias_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.fieldAlias"/>&nbsp;
	  						</td>
	  						<td id='selectResult_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.selectResult"/>&nbsp;
	  						</td>
	  						<td id='dataType_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.dataType"/>&nbsp;
	  						</td>
	  						<td id='des_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.des"/>&nbsp;
	  						</td>
	  						<td id='orderNo_<s:property value="#select.selectId"/>' >
	  							<s:property value="#select.orderNo"/>&nbsp;
	  						</td>
	  						<td id='dataCoding_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.dataCoding"/>&nbsp;
	  						</td>
	  						<td id='outFormat_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.outFormat"/>&nbsp;
	  						</td>
	  						<td id='btnType_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.btnType"/>&nbsp;
	  						</td>
	  						<td id='dataInput_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.dataInput"/>&nbsp;
	  						</td>
	  						<td id='isHidden_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.isHidden"/>&nbsp;
	  						</td>
	  						<td id='linkUrl_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.linkUrl"/>&nbsp;
	  						</td>
	  						<td id='openType_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.openType"/>&nbsp;
	  						</td>
	  						<td id='colWidth_<s:property value="#select.selectId"/>' >
	  							<s:property  value="#select.colWidth"/>&nbsp;
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
						<th width="10px">&nbsp;</th>
						<th><s:text name="query.base.propertyName" /></th>
						<th><s:text name="query.base.propertyValue" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5">
							<input type="hidden" id="id" name="selectVo.id"  value="${selectVo.id }"/>
							<div class="input_msg">${idMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.tableName" />：</th>
						<td>
							<amway:select id="tableName" name="selectVo.tableName"
								dictCode="${froms}" listKey="tableName" listValue="tableAlias"
								
								onchange="setTableFieldVi(this.value);" style="width: 180px;" />
							<div class="input_msg">${tableNameMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.fieldName" />：</th>
						<td>
							<amway:select id="fieldName" name="selectVo.fieldName"
								dictCode="${sysTableFields }" parentItem="tableName" 
								
								complete="setTableFieldValue();"
								style="width: 141px;display: none" />
							<amway:textfield id="fieldName2" name="selectVo.fieldName2" 
								
								style="width: 141px;display: "/>
								
							<input type="button" value="搜索" 
								onclick="popupSelFieldName('${sysTableFields }','tableName')"
							/>		
							<div class="input_msg">${fieldNameMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.fieldAlias" />：</th>
						<td>
							<amway:textfield id="fieldAlias" name="selectVo.fieldAlias"  
								value="${selectVo.fieldAlias }" maxLine="128" style="width:180"/>
							<div class="input_msg">${fieldAliasMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.selectResult" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="selectResult" name="selectVo.selectResult"  
								value="${selectVo.selectResult }" style="width:141"
								dataType="Require" msg="请生成组合结果" maxLine="256"/>
							<input type="button"  value="生成" 
								onclick="setSelectResult();" />
							<div class="input_msg">${selectResultMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.dataType" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="dataType" name="selectVo.dataType"
								dictCode="select_data_type" value="${selectVo.dataType }"
								style="width:141" dataType="Require" msg="请选择数据类型"></amway:select>
							<input type="button"  value="生成" 
								onclick="setDataType('${pageContext.request.contextPath}/selectAction!findTableFieldType.action','${sysTableFields }');" />
							<div class="input_msg">${dataTypeMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.des" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="des" name="selectVo.des" value="${selectVo.des }"
								dataType="Require" maxLine="256" msg="请填写描述" style="width:180"/>
							<div class="input_msg">${desMsg }</div>
						</td>	
					</tr>
					<tr>
						<th><s:text name="query.from.orderNo" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="orderNo" name="selectVo.orderNo" value="${selectVo.orderNo}"
								dataType="Integer" msg="排序应为整数" style="width:141" maxLine="8"/>
							<input type="button"  value="生成" 
								onclick="setEleValueJson('${pageContext.request.contextPath}/selectAction!findOrderNo.action','orderNo');" />
							<div class="input_msg">${orderNoMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.dataCoding" />：</th>
						<td>
							<amway:autoComplete id="dataCoding" name="selectVo.dataCoding"
								source="data_code" value="${selectVo.dataCoding }"
								filterColNames="displayname|code" fillColKey="code" style="width:180"
							/>	
							<div class="input_msg">${dataCodingMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.outFormat" />：</th>
						<td>
							<amway:select id="outFormat" name="selectVo.outFormat"
								dictCode="select_out_format" value="${selectVo.outFormat }" style="width: 180" />
							<div class="input_msg">${outFormatMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.btnType" />：</th>
						<td>
							<amway:select id="btnType" name="selectVo.btnType" 
								onchange="btnTypeOnchange(this);"
								dictCode="select_btn_type" value="${selectVo.btnType }" style="width: 180"/>
							<div class="input_msg">${btnTypeMsg }</div>
						</td>	
					</tr>
					<tr>
						<th><s:text name="query.select.dataInput" />：</th>
						<td>
							<amway:textfield id="dataInput" name="selectVo.dataInput" 
								value="${selectVo.dataInput }" maxLine="256" style="width:180"/>
							<div class="input_msg">${dataInputMsg }</div>
						</td>
						<td>&nbsp;</td>
						<th><s:text name="query.select.isHidden" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="isHidden" name="selectVo.isHidden"
								dictCode="true_or_false" value="${selectVo.isHidden }"
								style="width:180" dataType="Require" msg="请选择是否隐藏"/>
							<div class="input_msg">${isHiddenMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.linkUrl" />：</th>
						<td>
							<amway:textfield id="linkUrl" name="selectVo.linkUrl" 
								value="${selectVo.linkUrl }" maxLine="256" style="width:180"/>
							<div class="input_msg">${linkUrlMsg }</div>
						</td>		
						<td>&nbsp;</td>
						<th><s:text name="query.select.openType" />：</th>
						<td>
							<amway:select id="openType" name="selectVo.openType"
								dictCode="dlg_open_type" value="${selectVo.openType }" style="width: 180" />
							<div class="input_msg">${openTypeMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.colWidth" />：</th>
						<td>
							<amway:textfield id="colWidth" name="selectVo.colWidth" 
								value="${selectVo.colWidth }" style="width:180" maxLine="256"/>
							<div class="input_msg">${colWidthMsg }</div>
						</td>
						<td colspan="3">&nbsp;</td>
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
