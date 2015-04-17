<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据服务编辑</title>

    <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"> </script>
	<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"> </script>
	<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/bdsSchemaInforService.js"> </script>
	
    <script type="text/javascript">
    
    var selected = 0;
	
	function addBtnOnclick(curEle){
		document.getElementById("add").disabled = true;
		document.getElementById("modify").disabled = false;
		document.getElementById("save").disabled = false;
		
		document.getElementById("name").readOnly=false;
	}
	
	function modifyBtnOnclick(curEle){
		if (selected == 0) {
			alert('请选择要修改的记录。');
			return false;
		}
	
		document.getElementById("add").disabled = false;
		document.getElementById("modify").disabled = true;
		document.getElementById("save").disabled = false;
		
	}
	
	function deleteBtnOnclick(curEle){		
		var addState = document.getElementById("add");
		var modifyState = document.getElementById("modify");
		if(addState.disabled || modifyState.disabled){
			setInputMsg("增加或修改状态,不许删除");
			return ;
		}
		var ret = window.confirm("删除确认,否则取消!");
		if(ret == false){	
			return ;
		}
		curEle.parentNode.parentNode.removeNode(true);
	}
	
	function cancelBtnOnclick(curEle){
		document.getElementById("add").disabled = false;
		document.getElementById("modify").disabled = false;
		document.getElementById("save").disabled = true;
		
		document.getElementById("name").readOnly=true;
	}
	
	function saveBtnOnclick(curEle){
		
		var frm = document.forms[0];
		if(!Validator.Validate(frm,4)){
			return;
		}
		
		var addState = document.getElementById("add");
		var modifyState = document.getElementById("modify");
		var tabNameId = "query_tab";
		if(addState.disabled){
			addRow(tabNameId);
		}else if(modifyState.disabled){
			var tr = getRowByNameValue(tabNameId, "name");
			modifyRow(tr);
		}
		
		cancelBtnOnclick(null);
	}
	
	var colNames = new Array("name","enName","cnName","colType","required","maxLen","unique","regex","refCode","refCol");
	
	function addRow(tabId){
	
		if(getRowByNameValue(tabId, "name")){
			setInputMsg("记录已存在");
			return;
		}
		var tab = document.getElementById(tabId);
		var tr = tab.insertRow(tab.rows.length);
		for(var i=0; i<10; i++){
			var td = tr.insertCell(i);
			td.innerHTML = document.getElementById(colNames[i]).value;
		}
		var deleteFlagTd = tr.insertCell(10);
		deleteFlagTd.innerHTML = "<a href='#' onclick='deleteBtnOnclick(this);' >删除</a>";
	}
	
	function getRowByNameValue(tabId, nameId){
		var tab = document.getElementById(tabId);
		var na = document.getElementById(nameId).value;
		var rowLen = tab.rows.length;
		for(var i=1; i<rowLen; i++){
			var tr = tab.rows[i];
			var va = tr.cells[0].innerText;
			if(va == na){
				return tr;
			}
		}
	}
	
	function modifyRow(tr){

		if(!tr){
			setInputMsg("列字段名不存在");
			return;
		}
		for(var i=0; i<10; i++){
			tr.cells[i].innerHTML = document.getElementById(colNames[i]).value;
		}
	}
	
	function returnBtnOnclick(curEle){
	
		var dataXmlStr = "<table>\n";
	    var tab = document.getElementById("query_tab");
	    var rowLen = tab.rows.length;
	    for(var i=1; i<rowLen; i++){
	    	dataXmlStr += "  <column>\n";
	    	var colLen = tab.rows[i].cells.length -1;
	    	for(var j=0; j<colLen; j++){
	    		dataXmlStr += "    <"+colNames[j]+">"+tab.rows[i].cells[j].innerText+"</"+colNames[j]+">\n";
	    	}
	    	dataXmlStr += "  </column>\n";
	    }
	    dataXmlStr += "</table>";
		
		window.opener.document.getElementById("dataStructureXml").value = dataXmlStr;
		window.close();
	}

	function setInputMsg(msg){
		document.getElementById("input_msg").innerHTML = msg;
	}
	
	var curReCol = "";
	function curRowOnclick(curRow){
		var tds = curRow.childNodes;
		var len = tds.length;
		for(var i=0; i<10; i++){
			setElementValueById(colNames[i], tds[i].innerText);
		}
		curReCol = tds[9].innerText;
		
		selected = 1;
	}
	
	function setElementValueById(eleId, eleValue){
		if(!eleId){
			return;
		}
		eleValue = eleValue;
		var desEle = document.getElementById(eleId);
		if(!desEle){
			return;
		}
		var nname = desEle.nodeName;
		if(nname.toUpperCase() == "SELECT"){
			for(var j=0;j<desEle.length;j++){   
	    		var text = desEle.options[j].text; 
	    		var va = desEle.options[j].value; 
	   			if((va == eleValue) || (text == eleValue)){ 
	   				desEle.options[j].selected = true; 
	   			}else{
	   				desEle.options[j].selected = false; 
	   			}
			}  
			try{
				desEle.onchange();
			}catch(e){
				;
			}
		}else if(nname.toUpperCase() == "INPUT"){		
			desEle.value = eleValue;
		}
	}
	
	function refColOnchange(curEle){
		bdsSchemaInforService.getSchemaStructColNames(curEle.value, setRefColValue);
	}
	
	function setRefColValue(colNames){

		var refColId = "refCol";
		var refCol = document.getElementById(refColId);
		DWRUtil.removeAllOptions(refCol);
		refCol.options.add(new Option("--请选择--",""));
   		DWRUtil.addOptions(refCol, colNames);
   		setSelectSel(refCol, curReCol);
	}
	
	function setSelectSel(selEle, selValue){
		
		for(var i=0;i<selEle.length;i++){   
    		var text = selEle.options[i].text; 
    		var va = selEle.options[i].value;
   			if((va == selValue) || (text == selValue)){ 
   				selEle.options[i].selected = true; 
   			}
		}  
	}
    </script>
  </head>
  
  <body>
  	<div style="margin: 10px;width: 100%">
      <div>
        <table align="center" id="query_tab" class="query_list" >
  	      <tr>
	        <th>字段名</th>
	  	    <th>英文名称</th>
	  	    <th>中文名称</th>
	  	    <th>字段类型</th>
	  	    <th>是否必填</th>
	  	    <th>最大长度</th>
	  	    <th>是否唯一</th>
	  	    <th>正则表达式</th>
	  	    <th>关联编码</th>
	  	    <th>关联字段</th>
	  	    <th>删除标志</th>
  	      </tr>
  	      <c:forEach items="${retObjs}" var="xmlStructure" >
  	      <tr id="${xmlStructure.name }" onclick="curRowOnclick(this);" >
  		    <td>${xmlStructure.name }</td>
  		    <td>${xmlStructure.enName }</td>
  		    <td>${xmlStructure.cnName }</td>
  		    <td>${xmlStructure.colType }</td>
  		    <td>${xmlStructure.required }</td>
  		    <td>${xmlStructure.maxLen }</td>
  		    <td>${xmlStructure.unique }</td>
  		    <td>${xmlStructure.regex }</td>
  		    <td>${xmlStructure.refCode }</td>
  		    <td>${xmlStructure.refCol }</td>
  		    <td title="${xmlStructure.deleteFlag}">
		    	<c:if test="${xmlStructure.deleteFlag}">
		    		<a href="#" onclick="deleteBtnOnclick(this)">删除</a> 
		    	</c:if>
 		    </td>
  	      </tr>
  	      </c:forEach>
        </table>
      </div>
      <div>
      <form action="" method="post" 
      	onsubmit="return Validator.Validate(this,4);">
   	    <table class="form_item2">
   	      <tr>
   	        <td colspan="6"><div id="input_msg" class="input_msg">${idMsg }</div></td>
   	      </tr>
   	      <tr>
   		    <th>字段名<font color="red">*</font>：</th>
   	        <td colspan="5">
   	        	<amway:textfield id="name" name="name" style="width:160px"
   	        		dataType="Require" maxLine="128" msg="请填写字段名" readonly="readonly"></amway:textfield>
   	        </td>
   	      </tr>
   	      <tr>
   	        <th>英文名称<font color="red">*</font>：</th>
   	        <td>
   	        	<amway:textfield id="enName" name="enName" style="width:160px"
   	        		dataType="Require" maxLine="256" msg="请填写英文名称"></amway:textfield>
   	        </td>
   	        <th>中文名称<font color="red">*</font>：</th>
   	        <td>
   	        	<amway:textfield id="cnName" name="cnName" style="width:160px"
   	        		dataType="Require" maxLine="256" msg="请填写中文名称"></amway:textfield>
   	        </td>
   	        <th>正则表达式</th>
   	        <td>
   	        	<amway:textfield id="regex" name="regex" style="width:160px"
   	        		dataType="LimitB" maxLine="256" ></amway:textfield>
   	        </td>
   	      </tr>
   	      <tr>
   	        <th >字段类型：</th>
   	        <td>
   	        	<amway:select id="colType" name="colType" style="width:160px"
   	        		dictCode="data_type" listKey="code" listValue="code" defaultHK="false"></amway:select>
   	        </td>
   	        <th>是否必填：</th>
   	        <td colspan="3">
   	        	<amway:select id="required" name="required" style="width:160px"
   	        		dictCode="yes_or_not" listKey="code" listValue="code" defaultHK="false"></amway:select>
   	        </td>
   	      </tr>
   	      <tr>
   	        <th>最大长度<font color="red">*</font>：</th>
   	        <td>
   	        	<amway:textfield id="maxLen" name="maxLen" style="width:160px"
   	        		dataType="Integer" msg="最大长度应为整数" maxLine="256"></amway:textfield>
   	        </td>
   	        <th>是否唯一：</th>
   	        <td colspan="3">
   	        	<amway:select id="unique" name="unique" style="width:160px"
   	        		dictCode="yes_or_not" listKey="code" listValue="code" defaultHK="false"></amway:select>
   	        </td>
   	      </tr>
   	      <tr>
   		    <th><font color="blue"><b>关联字段</b></font></th>
   	        <td colspan="5">
   	    	  &nbsp;
   	    	</td>
   	      </tr>
   	      <tr>
   		    <th>数据编码：</th>
   	        <td>
   	        	<amway:select id="refCode" name="refCode" style="width:160px"
   	        		dictCode="data_code" listKey="code" listValue="displayname" onchange="refColOnchange(this);"></amway:select>
   	    	</td>
   	    	<th>关联字段：</th>
   	        <td colspan="3">
   	    	  <select id="refCol" name="refCol" style="width:160px">
   	    		<option value="">--请选择--</option>
   	    	  </select>
   	    	</td>
   	      </tr>
   	      <tr><td colspan="6">&nbsp;</td></tr>
   	      <tr>
   	  	    <td colspan="6" align="center">
   	  	      <input type="button" id="add" name="add" value="增加" onclick="addBtnOnclick(this);"/>&nbsp;&nbsp;
   	  	      <input type="button" id="modify" name="modify" value="修改" onclick="modifyBtnOnclick(this);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	  	      <input type="button" id="save" name="save" value="保存" disabled="disabled" onclick="saveBtnOnclick(this);"/>&nbsp;&nbsp;
   	  	      <input type="reset" id="cancel" name="cancel" value="取消" onclick="cancelBtnOnclick(this);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      
   	  	      <input type="button" id="return" name="return" value="返回" onclick="returnBtnOnclick(this);"/>
   	  	    </td>
   	      </tr>
   	    </table>
	  </form>
	  </div>
	</div>
  </body>
</html>
