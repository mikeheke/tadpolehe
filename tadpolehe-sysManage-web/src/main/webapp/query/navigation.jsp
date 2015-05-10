<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/query.js" ></script>
	
	<style type="text/css">
		a:link,a:hover,a:visited {text-decoration : none;}
	</style>
  </head>
  
  <body style="border-right: 1 solid silver;">
  		<table style="margin-top: 10px;margin-left: 10px;">
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_0" 
  						onclick="navBtnOnclick(this, false, true);" 
  						href="${pageContext.request.contextPath}/baseAction!${param.oprt }.action?queryVo.ids=${param.ids }" 
  						target="query_main" >
  						<s:text name="query.navication.firstStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_1" 
  						onclick="navBtnOnclick(this, true, true);" 
  						href="fromAction!init.action" target="query_main" >
  						<s:text name="query.navication.secondStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_2" 
  						onclick="navBtnOnclick(this, true, true);" 
  						href="selectAction!init.action" target="query_main" >
  						<s:text name="query.navication.thirdStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_3" 
  						onclick="navBtnOnclick(this, true, true);" 
  						href="whereAction!init.action" target="query_main" >
  						<s:text name="query.navication.fourStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_4" 
  						onclick="navBtnOnclick(this, true, true);" 
  						href="orderByAction!init.action" target="query_main" >
  						<s:text name="query.navication.fiveStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_5" 
  						onclick="navBtnOnclick(this, true, true);" 
  						href="groupByAction!init.action" target="query_main" >
  						<s:text name="query.navication.sixStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_6" 
  						onclick="navBtnOnclick(this, true, true);" 
  						href="buttonAction!init.action" target="query_main" >
  						<s:text name="query.navication.sevenStep" />
  					</a>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<a name="navBtn" id="navBtn_7" 
  						onclick="navBtnOnclick(this, true, false);" 
  						href="reviewAction!init.action" target="query_main" >
  						<s:text name="query.navication.eightStep" />
  					</a>
  				</td>
  			</tr>
  		</table>
  </body>
</html>
