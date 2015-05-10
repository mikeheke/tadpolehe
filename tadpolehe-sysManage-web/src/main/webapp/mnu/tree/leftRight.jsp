<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:tvns>
  <head>
    
    <title>leftRight.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<script type="text/javascript">
		
		function hiddeLeftFrm(basePath){
	
			var scrollImg = document.getElementById("scrollImg");
			if(top.bodyFrame.cols == "*,204,18,802,*"){
				top.bodyFrame.cols = "*,0,18,1006,*";
				scrollImg.src = basePath+"/common/images/button_right.gif";
			}else{
				top.bodyFrame.cols = "*,204,18,802,*";
				scrollImg.src = basePath+"/common/images/button_left.gif";
			}
		}
	</script>
  </head>

  <body style="margin: 0px;padding: 0px;" >
	<div onclick="hiddeLeftFrm('${pageContext.request.contextPath}')" style="margin-top: 50px;cursor:pointer">
	  <img id=scrollImg src="${pageContext.request.contextPath}/common/images/button_left.gif" />
	</div>
  </body>
</html>