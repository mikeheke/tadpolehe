
package com.amway.frm.email.web.action;

import java.util.HashMap;
import java.util.Map;

import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.email.entity.EmailBasicPara;
import com.amway.frm.email.service.EmailService;
import com.amway.frm.email.util.EmailConstant;

public class TestEmailSendAction extends BaseAction {

//	@Override
//	public String execute() throws Exception {
//		
//		EmailService emailService = (EmailService) ContextFactory.getBean(EmailService.class.getSimpleName());
//		EmailBasicPara emailBasicPara = new EmailBasicPara();
//		//emailBasicPara.setHost("smtp.revenco.com");
//		emailBasicPara.setUserName("huangweijin");
//		emailBasicPara.setMailFrom("huangweijin@revenco.com");
//		emailBasicPara.setPassword("weijin323627");
//		emailBasicPara.setPersonalName("黄伟金");
//		emailBasicPara.setMailTo(new String[]{"huangweijin@revenco.com"});
//		emailBasicPara.setMailCC(new String[]{"huangweijin@revenco.com"});
//		emailBasicPara.setMailBCC(new String[]{"tenghao@revenco.com"});
//		emailBasicPara.setSendMode(EmailConstant.ASYN_SEND);
//		emailBasicPara.setMailSubject("hi2222222222");
//		emailBasicPara.setMailContent("吃饭啦222222222222222222222222！");
////		emailBasicPara.setAccessory(new String[]{});
////		emailBasicPara.setTemplateCode("TE0002");
////		Map<String, Object> vars = new HashMap<String, Object>();
////		vars.put("USER_NAME", "用户变量");
////		vars.put("PASSWORD", "密码变量");
//		
//		try{
////			ReturnMessage<EmailBasicPara> returnMessage = emailService.sendEmail(emailBasicPara);
//			ReturnMessage<EmailBasicPara> returnMessage = emailService.sendEmail(emailBasicPara);
//			System.out.println(returnMessage.getReturnCode());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return NONE;
//	}

	
}
