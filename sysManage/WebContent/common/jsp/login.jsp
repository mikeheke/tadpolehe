<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/resInclude.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:tvns>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	
	<script type="text/javascript">
	
		//防止页面套入框架
		<!--
			if (self!=top){
				top.location=self.location;
			}
		//-->
	
		function changeKaptcha(kaptchaId){
			var kaptcha = document.getElementById(kaptchaId);
			kaptcha.src = kaptcha.src;
		}
		
		function login(submitId){
			var submit = document.getElementById(submitId);
			submit.click();
		}
		
		function cancel(resetId){
			var reset = document.getElementById(resetId);
			reset.click();
		}
		
		function cleanMsgs() {
			document.getElementById('retInfo').innerHTML = '';
			document.getElementById('userCode').value = '';
			var elements = document.getElementsByTagName("DIV");
			for (var i = 0; i < elements.length; i++) {
				if (elements[i].className == 'input_msg') {
					elements[i].innerHTML = '';
				}
			}
		}
		
		function getKaptchaJson(url, curEle){
			
			var applicationCode = curEle.value;
			if(applicationCode!=''){
				var params = {'loginVo.applicationCode':applicationCode};
				ajaxRequest(url, params, 'setKaptchaVi');
			}else{
				setKaptchaVi('0');
			}
		}
		
		function setKaptchaVi(kaptcha){
			if(kaptcha=='0'){
				setEleDisplay('kaptcha_tr', false);
				setEleDisplay('kaptcha_tr2', false);
			}else{
				setEleDisplay('kaptcha_tr', true);
				setEleDisplay('kaptcha_tr2', true);
			}
		}
		
		function initDoc(){
			var applicationCode = document.getElementById("applicationCode");
			applicationCode.fireEvent("onchange");
			
			document.getElementById('userCode').focus();
		}
		//回车提交
		function submitDefault(event){
			
			 //如果按了回车键也提交
            if(event.keyCode==13){//键盘按下Enter键   
                login('submitId');
            }   	
		}
	</script>
  </head>
  
  <body onload="initDoc();" onkeypress="submitDefault(event);">
  	<form action="${_sysApplication_.goToUrl}/loginAction!login.action" method="post" 
  		onsubmit="return Validator.Validate(this,4);">
	<div align="center">
		<div id="login">
			<div class="login_right">
				<ul>
    				<li>
      					<table width="100%" border="0" cellspacing="0" cellpadding="0">
  							<tr>
  								<td height="46" colspan="3" valign="top">
  									<img src="${_sysApplication_.goToUrl}/common/images/login_title2_${_amwayCss_}.gif" />
  								</td>
  							</tr>
  							<tr><td colspan="3" style="color: red" id="retInfo">${retInfo }</td></tr>
  							<tr>
  								<td width="14%" height="35" align="right" class="white">用户名：</td>
    							<td colspan="2">
    								<amway:textfield id="userCode" name="loginVo.userCode" 
    									size="25" style="width:160px" value="${loginVo.userCode}"
    									dataType="Require" maxLine="10" msg="用户名不能为空！" cssClass="login_input"/>
    								<div class="input_msg">${userCodeMsg }</div>
    							</td>
  							</tr>
  							<tr>
    							<td height="35" align="right" class="white">密码：</td>
    							<td colspan="2">
    								<input type="password" id="password" name="loginVo.password"  
    									size="25" style="width:160px" 
    									dataType="Require" maxlength="20" msg="密码不能为空！" class="login_input" />
    								<div class="input_msg">${passwordMsg }</div>
    							</td>
  							</tr>
  							<tr>
    							<td height="35" align="right" class="white">选择应用：</td>
    							<td colspan="2">
   									<amway:select id="applicationCode" name="loginVo.applicationCode" 
    									dictCode="app_001" listKey="applicationCode" listValue="applicationName" 
    									value="${loginVo.applicationCode == null ? contextApp.applicationCode : loginVo.applicationCode}"
    									onchange="getKaptchaJson('${_sysApplication_.goToUrl}/loginAction!isCheckCode.action',this);"
    									dataType="Require" msg="应用不能为空" style="width:160px"/>
    								
    								<input type="button" value="搜索" onclick="popup(600,500,'${_sysApplication_.goToUrl}/applicationAction!popup.action')"/>
    								<div class="input_msg">${applicationCodeMsg }</div>
    							</td>
  							</tr>
  							<tr id="kaptcha_tr" style="display: none">
    							<td height="35" align="right" class="white">验证码：</td>
    							<td width="18%">
    								<amway:textfield id="kaptcha" name="loginVo.kaptcha" 
    									cssClass="login_input" size="8" maxLine="6" cssClass="login_input" />
    								<div class="input_msg">${kaptchaMsg }</div>
    							</td>
    							<td width="68%">
    								<img width="100" height="30" id="kaptchaId" src="${_sysApplication_.goToUrl}/Kaptcha.jpg" />
    							</td>
  							</tr>
  							<tr id="kaptcha_tr2" style="display: none">
    							<td height="30" align="right" class="white">&nbsp;</td>
    							<td colspan="2">
    								<a href="#" onclick="changeKaptcha('kaptchaId')" class="blue" style="text-decoration:underline;">看不清楚？ 换一个</a>
    							</td>
  							</tr>
  							<tr>
    							<td height="35" align="right" class="white">&nbsp;</td>
    							<td colspan="2">
    								<a href="#" onclick="javascript:cancel('resetId');cleanMsgs();document.getElementById('userCode').focus();"><img src="${_sysApplication_.goToUrl}/common/images/login_btm_${_amwayCss_}.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
    								<input id="resetId" type="reset" style="display: none" />
    								<a href="#" onclick="login('submitId');"><img src="${_sysApplication_.goToUrl}/common/images/login_btm2_${_amwayCss_}.gif" /></a>
    								<input id="submitId" type="submit" style="display: none" />
    							</td>
  							</tr>
      					</table>
      				</li>
    			</ul>
  			</div>
		</div>
	</div>
	</form>
</body>
</html>
