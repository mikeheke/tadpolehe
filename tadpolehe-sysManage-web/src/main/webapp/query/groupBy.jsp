<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.groupBy.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
  </head>
  
  <body style="margin: 10px">
   	<form action="groupByAction!" method="post" onsubmit="return Validator.Validate(this,4);">
	  	<div style="width:100%;height:220px;overflow: auto;" >
	  		<table id="qryList" class="query_list">
	  			<thead>
	  				<tr>
	  					<th width="5%" >
	  						<input type="checkbox" onclick="setTabCBAllSel(this,'qryList');"/>
	  					</th>
	  					<th width="30%" ><s:text name="query.from.tableName" /></th>
	  					<th width="30%" ><s:text name="query.select.fieldName" /></th>
	  					<th width="35%" ><s:text name="query.from.orderNo" /></th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<s:iterator value="%{getQueryGroupBys()}"  var="groupBy" status="stat">
	  					<tr >
	  						<td id="id_<s:property value="#groupBy.groupById"/>" >
	  							<input type="checkbox" name="groupByVo.ids" value='${groupBy.groupById }|${groupBy.oprtFlag }' onclick="setCurMdfIdValue(this.parentNode.parentNode);"/>
	  						</td>
	  						<td id='tableName_<s:property value="#groupBy.groupById"/>' >
	  							<s:property  value="#groupBy.tableName"/>&nbsp;
	  						</td>
	  						<td id='fieldName_<s:property value="#groupBy.groupById"/>' >
	  							<s:property  value="#groupBy.fieldName"/>&nbsp;
	  						</td>
	  						<td id='orderNo_<s:property value="#groupBy.groupById"/>' >
	  							<s:property  value="#groupBy.orderNo"/>&nbsp;
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
							<input type="hidden" id="id" name="groupByVo.id"  value="${groupByVo.id }"/>
							<div class="input_msg">${idMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.tableName" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="tableName" name="groupByVo.tableName"
								dictCode="${froms}" listKey="tableName" listValue="tableAlias"
								style="width: 200px;" 
								dataType="Require" msg="请选择表名"
							/>
							<div class="input_msg">${tableNameMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.fieldName" /><font color="red">*</font>：</th>
						<td>
							<amway:select id="fieldName" name="groupByVo.fieldName"
								dictCode="${sysTableFields }" parentItem="tableName" 
								complete="setTableFieldValue();"
								style="width: 200px;" 
								dataType="Require" msg="请选择字段名"
							/>
							
							<input type="button" value="搜索" 
								onclick="popupSelFieldName('${sysTableFields }','tableName')"
							/>
							
							<div class="input_msg">${fieldNameMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.orderNo" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="orderNo" name="groupByVo.orderNo" value="${groupByVo.orderNo }"
								dataType="Integer" msg="排序应为整数" maxLine="8" style="width: 163px"/>
							<input type="button"  value="生成" 
								onclick="setEleValueJson('${pageContext.request.contextPath}/groupByAction!findOrderNo.action','orderNo');" />
							<div class="input_msg">${orderNoMsg }</div>
						</td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="add" name="add.action" value="<s:text name="common.add" />" 
								onclick="addBtnOnclick(this);" />&nbsp; 
							<input type="button" id="delete" name="delete.action" value="<s:text name="common.delete" />" 
								onclick="deleteBtnOnclick(this);" title="<s:text name="common.delete.info" />"/>&nbsp;
							<input type="button" id="modify" name="modify" value="<s:text name="common.modify" />" 
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
