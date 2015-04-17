<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编辑本地数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    	
    <script type="text/javascript">
    
    	function setTabCBAllSel(curCk, tabId){
			var tabElement = document.getElementById(tabId);
			var checkBox_contents = tabElement.getElementsByTagName("input");
			for(var i=0;i<checkBox_contents.length;i++){
				var inputType = checkBox_contents[i].getAttribute("type");
				if(inputType.toUpperCase() != "CHECKBOX")continue;
				if(curCk.checked){
					checkBox_contents[i].checked = true;
				}else{
					checkBox_contents[i].checked = false;
				}	
			}
		}
		
		function blankOpenWin(method, width, height){
		
			var left = (screen.width - width) / 2;
			var top = (screen.height - height) / 2; 
			
			var tar = 'blank';
			window.open("",tar,'height='+height+',width='+width+',top='+top+',left='+left
				+',toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes');
			
			var frm = document.forms[0];
			var oldAction = frm.action;
			var oldTarget = frm.target;
			frm.action = oldAction + method;
			frm.target = tar;
			frm.submit();
			
			frm.action = oldAction;
			frm.target = oldTarget;
		}
		
		function modifyBtnOnclick(method, width, height) {
			
			var count = 0;
			var checkBoxs = document.getElementsByName('bdsXmlDataVo.bdsXmlDataIds');
			for (var i = 0; i < checkBoxs.length; i++) {
				if (checkBoxs[i].checked) {
					count++;
				}
			}
			if (count == 0) {
				alert('请选择要修改的记录。');
				return false;
			} else if (count > 1) {
				alert('每次只能选择一条记录。');
				return false;
			}
			
			blankOpenWin(method, width, height);
		}
		
		function deleteBtnOnclick(method){
			
			var count = 0;
			var checkBoxs = document.getElementsByName('bdsXmlDataVo.bdsXmlDataIds');
			for (var i = 0; i < checkBoxs.length; i++) {
				if (checkBoxs[i].checked) {
					count++;
				}
			}
			if (count == 0) {
				alert('请选择要删除的记录。');
				return false;
			}
		
			var frm = document.forms[0];
			
			var oldAction = frm.action;
			frm.action = oldAction + method;
			//frm.target = 'ifrm';
			frm.submit();
			
			frm.action = oldAction;
			window.location = window.location;
		}
    </script>
  </head>
  <body style="margin: 10px;">
    <form id="query_form" action="<%=basePath %>/bdsXMLDataAction!" method="post" />
      <div>
        <table class="form_item2">
          <tr>
            <td colspan="2">
              <div class="input_msg">${idMsg }${bdsSchemaInforIdMsg }${retInfo }</div>
            </td>
          </tr>
          <tr>
            <th width="10%">数据编码：</th>
            <td>
              <amway:textfield id="bdsSchemaInforCode" name="bdsXmlDataVo.bdsSchemaInforCode" 
              		value="${xmlSchema.bdsSchemaInforCode }" readonly="readonly" style="width:160px" />
              <input type="hidden" id="bdsSchemaInforId" name="bdsXmlDataVo.bdsSchemaInforId" 
              		value="${xmlSchema.bdsSchemaInforId }" />
            </td>
          </tr>
        </table>
      </div>
      <c:if test="${idMsg != '非本地数据结构，不能添加数据'}">
      <div>
        <table id="query_table"  class="query_list">
          <tr>
            <th width="5%">
              <input type="checkbox" onclick="setTabCBAllSel(this, 'query_table');">
            </th>
			<c:forEach var="colName" items="${colNames}">
			<th>${colName }</th>
			</c:forEach>
          </tr>
          <c:forEach var="xmlData" items="${xmlDatas}">
		  <tr>
			<td>
			  <input type="checkbox" name="bdsXmlDataVo.bdsXmlDataIds" value="${xmlData[0]}">
			</td>
			<c:forEach var="data" items="${xmlData}" begin="1">
			<td>${data}</td>
			</c:forEach>
		  </tr>
		  </c:forEach>
        </table>
      </div>
      <div>
        <table>
          <tr>
            <td>
              <input type="button" id="add" name="initAdd.action" value="增加" onclick="blankOpenWin(this.name, 800, 600);" />&nbsp;&nbsp;
              <input type="button" id="delete" name="delete.action" value="删除" onclick="deleteBtnOnclick(this.name);" />&nbsp;&nbsp;
              <input type="button" id="modify" name="initModify.action" value="修改" onclick="modifyBtnOnclick(this.name, 800, 600);" />&nbsp;&nbsp;&nbsp;&nbsp;
              
              <input type="button" id="refresh" name="refresh" value="刷新" onclick="window.location.reload();" />
            </td>
          </tr>
        </table>
      </div>
      </c:if>
	</form>
  </body>
</html>
