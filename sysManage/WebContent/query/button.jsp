<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="query.button.title" /></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
  </head>
  
  <body style="margin: 10px">
   	<form action="buttonAction!" method="post" onsubmit="return Validator.Validate(this,4);">
	  	<div style="width:100%;height:220px;overflow: auto;" >
	  		<table id="qryList" class="query_list" style="width:200%">
	  			<thead>
	  				<tr>
	  					<th width="1%" >
	  						<input type="checkbox" onclick="setTabCBAllSel(this,'qryList');"/>
	  					</th>
	  					<th width="9%" ><s:text name="query.button.buttonNo" /></th>
	  					<th width="9%" ><s:text name="query.button.buttonName" /></th>
	  					<th width="35%" ><s:text name="query.button.subUrl" /></th>
	  					<th width="7%" ><s:text name="query.select.openType" /></th>
	  					<th width="20%" ><s:text name="query.button.executeJs" /></th>
	  					<th width="3%" ><s:text name="query.from.orderNo" /></th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<s:iterator value="%{getQueryButtons()}"  var="button" status="stat">
	  					
	  					<tr >
	  						<td id="id_<s:property value="#button.buttonId"/>" >
	  							<input type="checkbox" name="buttonVo.ids" value='${button.buttonId }|${button.oprtFlag }' onclick="setCurMdfIdValue(this.parentNode.parentNode);"/>
	  						</td>
	  						<td id='buttonNo_<s:property value="#button.buttonId"/>' >
	  							<s:property  value="#button.buttonNo"/>&nbsp;
	  						</td>
	  						<td id='buttonName_<s:property value="#button.buttonId"/>' >
	  							<s:property  value="#button.buttonName"/>&nbsp;
	  						</td>
	  						<td id='subUrl_<s:property value="#button.buttonId"/>' >
	  							<s:property  value="#button.subUrl"/>&nbsp;
	  						</td>
	  						<td id='openType_<s:property value="#button.buttonId"/>' >
	  							<s:property  value="#button.openType"/>&nbsp;
	  						</td>
	  						<td id='executeJs_<s:property value="#button.buttonId"/>' >
	  							<s:property  value="#button.executeJs"/>&nbsp;
	  						</td>
	  						<td id='orderNo_<s:property value="#button.buttonId"/>' >
	  							<s:property  value="#button.orderNo"/>&nbsp;
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
							<input type="hidden" id="id" name="buttonVo.id"  value="${buttonVo.id }"/>
							<div class="input_msg">${idMsg }</div>
						</td>
						
					</tr>
					<tr>
						<th><s:text name="query.button.buttonNo" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="buttonNo" name="buttonVo.buttonNo" value="${buttonVo.buttonNo }" 
								dataType="Require" maxLine="128" msg="请填写按钮名称" style="width: 200px"/>
							<div class="input_msg">${buttonNoMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.button.buttonName" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="buttonName" name="buttonVo.buttonName" value="${buttonVo.buttonName }"
								dataType="Require" maxLine="128" msg="请填写按钮描述" style="width: 200px"/>
							<div class="input_msg">${buttonNameMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.button.subUrl" />：</th>
						<td>
							<amway:textfield id="subUrl" name="buttonVo.subUrl" value="${buttonVo.subUrl }" 
								maxLine="128" style="width: 200px"/>
							<div class="input_msg">${subUrlMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.select.openType" />：</th>
						<td>
							<amway:select id="openType" name="buttonVo.openType"
								style="width: 200px"
								dictCode="button_open_type"/>
							<div class="input_msg">${openTypeMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.button.executeJs" />：</th>
						<td>
							<amway:textfield id="executeJs" name="buttonVo.executeJs" value="${buttonVo.executeJs }" 
								maxLine="128" style="width: 200px"/>
							<div class="input_msg">${executeJsMsg }</div>
						</td>
					</tr>
					<tr>
						<th><s:text name="query.from.orderNo" /><font color="red">*</font>：</th>
						<td>
							<amway:textfield id="orderNo" name="buttonVo.orderNo" value="${buttonVo.orderNo }"
								dataType="Integer" msg="排序应为整数" maxLine="8" style="width: 163px"/>
							<input type="button"  value="生成" 
								onclick="setEleValueJson('${pageContext.request.contextPath}/buttonAction!findOrderNo.action','orderNo');" />
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
