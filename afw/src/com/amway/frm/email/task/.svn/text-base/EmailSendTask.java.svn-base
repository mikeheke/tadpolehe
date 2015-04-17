package com.amway.frm.email.task;

import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.email.service.EmailService;

/**
 * 
 * @author lenovo
 *
 */
public class EmailSendTask {
	
	private EmailService emailService;
	
	public EmailSendTask(){
		final String beanName = "EmailService";
		EmailService emailService = (EmailService) ContextFactory.getBean(beanName);
		this.emailService = emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	private static Object lock = new Object();;
	
	private static boolean start = true;
	
	public void sendEmailByAsynMode() {

		if(start){
			synchronized (lock) {
				try{
					start = false;
					emailService.sendEmailByAsynMode();
				}finally{
					start = true;
				}
			}
		}
	}
}
