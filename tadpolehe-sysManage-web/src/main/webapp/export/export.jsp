<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>配置数据导出
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
	<script type="text/javascript">
		function changedate(){
	
			var path = "${pageContext.request.contextPath}/exportConfigAction!fetchExportDate.action";
			
			
			var params = {applicationId:document.getElementById('applicationId').value};
			$.ajax({
				url : path,
				type : "POST",
				data : params,
				dataType : "text",
				error: function(request) {
					alert("出错了，请稍候再试");
				},
				success : function(returnData) {
					
					var obj=document.getElementById('startdate');
					
					var obj2=document.getElementById('enddate');
					
					obj.options.length=0;
					
					obj2.options.length=0;
					
					obj.options.add(new Option("--请选择--","")); 
					
					obj2.options.add(new Option("--请选择--",""));
					
					if(returnData!=""){
						var array=returnData.split(",");
					
						for(var i=0;i<array.length;i++){
						
							obj.options.add(new Option(array[i],array[i])); 
							
							obj2.options.add(new Option(array[i],array[i])); 
						
						}
					} 

				}
			});

		}

	</script>

	</head>

	<body>
		<form
			action="${pageContext.request.contextPath}/exportConfigAction!export.action?oprt=export"
			method="post" onsubmit="return Validator.Validate(this,4);" target="_blank">
			<table id="formTable" class="form_item" cellspacing="1" cellpadding="1" bgcolor="#c7c7c5" >
				<caption>
					配置数据导出
				</caption>
				<tbody>
					<tr>
						<th>应用:<font color="red">*</font></th>
						<td>
							<amway:select id="applicationId" name="applicationId" dictCode="app_001" listKey="applicationId"
								listValue="applicationName"
								defaultHK="true" dataType="Require" msg="请选择应用" style="width: 250px" onchange="changedate()" />
						</td>
					</tr>
					<!-- 
					<tr>
						<th>模块:</th>
						<td>
							<amway:checkbox   id="module"  name="startdate"  
										dictCode="all_app_tables" listKey="code" listValue="displayname"  >
							</amway:checkbox>
						</td>
					</tr>
					 -->
					<tr>
						<th>开始日期:</th>
						<td>
							<select   id="startdate"  name="startdate"  style="width: 250px" >
								<option value="">--请选择--</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>结束日期:</th>
						<td>
							<select   id="enddate"  name="enddate"  style="width: 250px"   >
								<option value="">--请选择--</option>
							</select>
						</td>
					</tr>					
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<input type="submit" value="导出" />
							&nbsp;&nbsp;
					<input type="reset" value="<s:text name="common.reset"></s:text>" />&nbsp;&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
