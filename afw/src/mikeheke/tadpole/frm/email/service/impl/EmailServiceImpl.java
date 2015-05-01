package mikeheke.tadpole.frm.email.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.namespace.QName;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.util.TemplateUtil;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.email.dao.IEmailSendRecordDao;
import mikeheke.tadpole.frm.email.entity.EmailBasicPara;
import mikeheke.tadpole.frm.email.entity.EmailTemplate;
import mikeheke.tadpole.frm.email.exception.EmailInfoException;
import mikeheke.tadpole.frm.email.exception.EmailSysException;
import mikeheke.tadpole.frm.email.service.EmailService;
import mikeheke.tadpole.frm.email.util.EmailConstant;

import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import emailSender.ArrayOfString;
import emailSender.ServiceResult;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：邮件服务接口类，适用于实现业务逻辑，包括发送邮件，保存发送信息
 */
public class EmailServiceImpl extends BaseImpl implements EmailService,
		EmailConstant {
    
    //邮件模板DAO
    private IEmailSendRecordDao emailSendRecordDao;
    
    private static Session session;
    
    //注入Spring封装的异步执行器
    @Resource
    TaskExecutor taskExecutor;

    private Session loginSmtp(String userName, String password, String smtpHost) {
    	
    	if(session == null){
    		session = getMailSessionByJndi(EMAIL_JNDI_NAME);
    	}
    	
    	return session;
    }
    
    private Session getMailSessionByJndi(String jndiName){
    	
    	InitialContext ic = null;
		Session session = null;
		try {
			ic = new InitialContext();
			session = (Session) ic.lookup(EMAIL_JNDI_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new EmailSysException(e);
		}

        return session;
    }
/*    
	private Session getMailSessionByHost(String userName, String password,
			String smtpHost) {
		
    	Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        
        // 设置session,和邮件服务器进行通讯。
       Session session = Session.getDefaultInstance(props, 
    		   new EmailAutherticator(userName, password));  //进行邮件服务器用户认证
      
        return session;
    }
*/
    @Override
    public ReturnMessage<EmailBasicPara> sendEmail(EmailBasicPara emailBasicPara){

    	ReturnMessage<EmailBasicPara> returnMessage = new ReturnMessage<EmailBasicPara>();
    	
        //参数校验
    	int result = validate(emailBasicPara);
    	if(result != 0){
    		returnMessage.setReturnCode(result);
    		return returnMessage;
    	}
    	
        //校验通过
    	try {
    		String sendMode = emailBasicPara.getSendMode();
    		if(ASYN_SEND.equals(sendMode)){
    			sendByAsynMode(emailBasicPara);
    		}else if(WSYN_SEND.equals(sendMode)){
    			sendByWsynMode(emailBasicPara);
    		}else{
    			sendBySynMode(emailBasicPara);
    		}
    	
			returnMessage.setReturnObject(emailBasicPara);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		} catch (Exception e) {
        	throw new EmailSysException(e);
        }
        
        return returnMessage;
    }
    
    @Override
	public ReturnMessage<EmailBasicPara> sendEmail(EmailBasicPara emailBasicPara,
			Map<String, Object> variableMap) {
		
		ReturnMessage<EmailBasicPara> returnMessage = new ReturnMessage<EmailBasicPara>();
		
        int result = validate(emailBasicPara);
    	if(result != 0){
    		returnMessage.setReturnCode(result);
    		return returnMessage;
    	}
    	
    	//获得邮件模板
    	if(!DataValidater.isStrEmpty(emailBasicPara.getTemplateCode())){
	        EmailTemplate emailTemplate = getEmailTemplate(emailBasicPara);
	        if (emailTemplate != null){
	        	emailBasicPara.setMailSubject(emailTemplate.getEmailTemplateSubject());
	        	emailBasicPara.setMailContent(emailTemplate.getEmailTemplateContent());
	            emailBasicPara.setSendMode(emailTemplate.getSendFlag()+EmailConstant.EMPTY_STR);
	        }
    	}
    	
    	try {
    		//替换变量
    		String mailSubject = TemplateUtil.replaceTemplateVars(emailBasicPara.getMailSubject(), variableMap);
    		String mailContent = TemplateUtil.replaceTemplateVars(emailBasicPara.getMailContent(), variableMap);
    		
    		emailBasicPara.setMailSubject(mailSubject);
    		emailBasicPara.setMailContent(mailContent);
		} catch (IOException e) {
			throw new EmailSysException(e);
		}
        
        return sendEmail(emailBasicPara);
    }

    //获得模板
    private EmailTemplate getEmailTemplate(EmailBasicPara emailBasicPara){
    	
    	String templateCode = emailBasicPara.getTemplateCode();
    	if(DataValidater.isStrEmpty(templateCode)){
    		return null;
    	}
    	EmailTemplate emailTemplate = new EmailTemplate();
    	emailTemplate.setTemplateCode(templateCode);
    	emailTemplate = (EmailTemplate) querySingle(emailTemplate);

    	return emailTemplate;
    }

/*
    private EmailTemplate getCloneEmailTemplate(EmailTemplate emailTemplate){
    	
    	if(null == emailTemplate){
    		return null;
    	}
    	
    	EmailTemplate emailTemplate2 = new EmailTemplate();
    	emailTemplate2.setAccessoryFlag(emailTemplate.getAccessoryFlag());
    	emailTemplate2.setApplication(emailTemplate.getApplication());
    	emailTemplate2.setCreatedTime(emailTemplate.getCreatedTime());
    	emailTemplate2.setCreatedUserId(emailTemplate.getCreatedUserId());
    	emailTemplate2.setEmailTemplateContent(emailTemplate.getEmailTemplateContent());
    	emailTemplate2.setEmailTemplateId(emailTemplate.getEmailTemplateId());
    	emailTemplate2.setEmailTemplateSubject(emailTemplate.getEmailTemplateSubject());
    	emailTemplate2.setRecordState(emailTemplate.getRecordState());
    	emailTemplate2.setRemark(emailTemplate.getRemark());
    	emailTemplate2.setSendFlag(emailTemplate.getSendFlag());
    	emailTemplate2.setState(emailTemplate.getState());
    	emailTemplate2.setTemplateCode(emailTemplate.getTemplateCode());
    	emailTemplate2.setTemplateName(emailTemplate.getTemplateName());
    	emailTemplate2.setUpdatedTime(emailTemplate.getUpdatedTime());
    	emailTemplate2.setUpdatedUserId(emailTemplate.getUpdatedUserId());
    	
    	return emailTemplate2;
    }
*/
/*
    //替换模板变量
    private String replaceTemplateVars(String content,
    		Map<String, Object> variableMap) throws IOException{
    	
    	VelocityContext ctx = new VelocityContext(variableMap);
		
		VelocityEngine engine = new VelocityEngine(); 
		engine.init(); 
		
		StringWriter writer = new StringWriter();
		engine.evaluate(ctx, writer, "mv", content);
		String contentRet = writer.toString();
		writer.close();

		return contentRet;
    }
*/	
	private void sendBySynMode(EmailBasicPara emailBasicPara) throws Exception {

		// 获取基础数据服务中设置的编码
		String encode = getEmainEncode();
		
		int result = -1;
		for(int i=0; i<EmailConstant.DEFAULT_SEND_NUM; i++){
			emailBasicPara.setSendNum(i);
			try{
				result = send(emailBasicPara, encode);
			}catch(Exception e){
				e.getMessage();//e.printStackTrace();
			}
			if(result == 0){
				break;
			}
		}
		if(result == 0){
			setSendSuccess(emailBasicPara);
		}else{
			setSendFail(emailBasicPara);
			throw new EmailInfoException(EmailConstant.EMAIL_INFO_EXP_0001);
		}
	}

    private void sendByAsynMode(EmailBasicPara emailBasicPara) throws Exception {
    	
		//emailBasicPara = emailSendRecordDao.saveRecord(emailBasicPara);
    	emailSendRecordDao.saveRecord(emailBasicPara);
    }
    
	private void sendByWsynMode(EmailBasicPara emailBasicPara) throws Exception {
		// 调用.net发邮件webservice
				String beanName = BaseDataSourceService.class.getSimpleName();
				BaseDataSourceService baseDataSourceService = (BaseDataSourceService) ContextFactory
						.getBean(beanName);
				BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
						"emailWevServiceUrl", new HashMap<String, String[]>(), AppConstant.SQL_AND)
						.getReturnObject();
				baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_CODE);
				String url=(String)baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_CODE);
				//EmailTemplate template = getEmailTemplate(emailBasicPara);
				emailSender.EmailService service = null;
				try{
				service = new emailSender.EmailService(
						new URL(url),
						new QName("http://tempuri.org/", "EmailService"));
				}catch(Exception e){
					e.printStackTrace();
				}
				ArrayOfString mt = new ArrayOfString();
				mt.getString().addAll(Arrays.asList(emailBasicPara.getMailTo()));
				ServiceResult r = service.getEmailServiceSoap().sendCustomizeEmail(
						"Default",
						EmailConstant.EMAIL_ADDRESS,
						mt,
						null,
						null,
						emailBasicPara.getMailSubject(),
						emailBasicPara.getMailContent(),
						false,
						DataConverter.fmtDateToDateStr(new Date(),
								"yyyy-MM-dd HH:mm:ss"), 3);
//				System.out.println("getMessage:"+r.getMessage()+"--Stauts:"+r.getStauts());
//				System.out.println(r.getStauts()==1);
	}
    
    @Override
    public void sendEmailByAsynMode() {

    	try{
    		// 获取基础数据服务中设置的编码
    		String encode = getEmainEncode();
    		
			List<EmailBasicPara> emailList = emailSendRecordDao.getEmailList();
			for (EmailBasicPara emailBasicPara : emailList) {
				
				EmailBasicPara emailBasicPara2 = getEmailBasicParaCopy(emailBasicPara);
				int result = -1;
				try{
					result = send(emailBasicPara2, encode);
				}catch(Exception e){
					e.getMessage();//e.printStackTrace();
				}
				if(result == 0){
					setAsySendSuccess(emailBasicPara, emailBasicPara2);
				}
	    	}
    	}catch(Exception e){
    		throw new EmailSysException(e);
    	}
    }
    
    private EmailBasicPara getEmailBasicParaCopy(EmailBasicPara emailBasicPara){
    	
    	EmailBasicPara emailBasicPara2 = new EmailBasicPara();
		emailBasicPara2.setId(emailBasicPara.getId());
		emailBasicPara2.setHost(emailBasicPara.getHost());
		emailBasicPara2.setMailFrom(emailBasicPara.getMailFrom());
		emailBasicPara2.setPersonalName(emailBasicPara.getPersonalName());
		emailBasicPara2.setMailTo(emailBasicPara.getMailTo());
		emailBasicPara2.setMailCC(emailBasicPara.getMailCC());
		emailBasicPara2.setMailBCC(emailBasicPara.getMailBCC());
		emailBasicPara2.setSendMode(emailBasicPara.getSendMode());
		emailBasicPara2.setMailSubject(emailBasicPara.getMailSubject());
		emailBasicPara2.setMailContent(emailBasicPara.getMailContent());
		
		return emailBasicPara2;
    }
    
    // 设置邮件发送者的地址和姓名
	private void setMessageMailFrom(Message message, String mailFrom,
			String personalName) throws UnsupportedEncodingException, MessagingException {
    	
		Address address = new InternetAddress(mailFrom, personalName);
		message.setFrom(address);
		
    }
	
	 // 设置邮件接收方的地址,支持多人接收
	private void setMessageMailTo(Message message, String mailTo)
			throws MessagingException {
		
        InternetAddress[] toAddress = InternetAddress.parse(mailTo);
        message.addRecipients(Message.RecipientType.TO, toAddress);
	}
	
	// 设置邮件抄送接收方的地址,支持多人接收
	private void setMessageMailCC(Message message, String mailCC)
			throws MessagingException {
		
        if (!DataValidater.isStrEmpty(mailCC)) {
            InternetAddress[] ccAddress = InternetAddress.parse(mailCC);
            message.addRecipients(Message.RecipientType.CC, ccAddress);
        }
	}
	
	// 设置邮件密送接收方的地址,支持多人接收
	private void setMessageMailBCC(Message message, String mailBCC)
			throws MessagingException {
		
		if (!DataValidater.isStrEmpty(mailBCC)) {
        	InternetAddress[] bccAddress = InternetAddress.parse(mailBCC);
            message.addRecipients(Message.RecipientType.BCC, bccAddress);
        }
	}

	//设置邮件主题
	private void setMessageMailSub(Message message, String sub, String encode)
			throws MessagingException {
		try {
			final String STR1 = "=?" + encode + "?B?";
			final String STR3 = "?=";
			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			message.setSubject(STR1 + enc.encode(sub.getBytes(encode))
					+ STR3);
		} catch (UnsupportedEncodingException e) {
			message.setSubject(sub);
		}
	}
	
	//设置邮件体
	private void setMessageBody(Message message, EmailBasicPara emailBasicPara, String charSet)
			throws MessagingException {
		
		MimeMultipart mp = new MimeMultipart();
        
		setBodyContent(mp, emailBasicPara.getMailContent(), charSet);
        
        setBodyAccessory(mp, emailBasicPara.getAccessory());
        
        message.setContent(mp);
	}
	
	//设置邮件内容
	private void setBodyContent(MimeMultipart mp, String mailContent, String charSet)
			throws MessagingException {

		BodyPart body = new MimeBodyPart();

		body.setContent(mailContent, charSet);
		mp.addBodyPart(body);
	}
	
	//设置附件
	private void setBodyAccessory(MimeMultipart mp, String[] accessorys)
			throws MessagingException {
		
        if (!DataValidater.isArrEmpty(accessorys)) {
            for (String accessory: accessorys) {
                BodyPart bodyAtt = new MimeBodyPart();
                DataSource source = new FileDataSource(accessory);
                //设置附件的数据处理器
                bodyAtt.setDataHandler(new DataHandler(source));
                // 设置附件文件名
                bodyAtt.setFileName(accessory);
                mp.addBodyPart(bodyAtt);
            }
        }
	}
	
	private int send(EmailBasicPara emailBasicPara, String encode)
			throws UnsupportedEncodingException, MessagingException {
 
    	Session session = loginSmtp(emailBasicPara.getUserName(), 
        		emailBasicPara.getPassword(), emailBasicPara.getHost());
    
        Message message = new MimeMessage(session);
        
    	setMessageMailFrom(message, emailBasicPara.getMailFrom(), 
    			emailBasicPara.getPersonalName());
   
    	setMessageMailTo(message, emailBasicPara.getMailTo2());

    	setMessageMailCC(message, emailBasicPara.getMailCC2());

    	setMessageMailBCC(message, emailBasicPara.getMailBCC2());

    	setMessageMailSub(message, emailBasicPara.getMailSubject(), encode);
 
    	String charSet = HTML_CHARSET + encode;
    	setMessageBody(message, emailBasicPara, charSet);
 
        message.setSentDate(new Date());	// 设置邮件发送日期
 
        message.saveChanges();
   
        Transport.send(message);	//发送邮件
   
        return 0;
    }

    @Transactional
    private void setSendFail(EmailBasicPara emailBasicPara) {
        try {
        	emailBasicPara.setSendFlag(EmailConstant.SEND_FAILURE);
			emailSendRecordDao.saveSendRecord(emailBasicPara);
		} catch (SQLException e) {
			throw new EmailSysException(e);
		}
    }

    @Transactional
    private void setSendSuccess(EmailBasicPara emailBasicPara) {
        try {
        	emailBasicPara.setSendFlag(EmailConstant.SEND_SUCCESS);
			emailSendRecordDao.saveSendRecord(emailBasicPara);
	
		} catch (SQLException e) {
			throw new EmailSysException(e);
		}
    }
    
    @Transactional
	private void setAsySendSuccess(EmailBasicPara emailBasicPara,
			EmailBasicPara emailBasicPara2) {
    	
		try {
			setSendSuccess(emailBasicPara);
			emailSendRecordDao.deleteRecord(emailBasicPara2);
			
		} catch (SQLException e) {
			throw new EmailSysException(e);
		}
    }

    private int validate(EmailBasicPara emailBasicPara) {
    	
        // 邮箱服务器
    	//if(!DataValidater.isStrEmpty(emailBasicPara.getHost())){
	        //if(DataValidater.isStrEmpty((emailBasicPara.getHost()))) {
	        //    return -1001;
	        //}
	        //邮箱用户名
	        //if(DataValidater.isStrEmpty(emailBasicPara.getUserName())) {
	        //    return -1002;
	        //}
	        //邮箱密码
	        //if(DataValidater.isStrEmpty(emailBasicPara.getPassword())) {
	        //    return -1003;
	        //}
	        //发件人地址
	        //if(DataValidater.isStrEmpty(emailBasicPara.getMailFrom())) {
	        //    return -1004;
	        //}
    		 //发件人名字
            //if (DataValidater.isStrEmpty(emailBasicPara.getPersonalName())) {
            //    return -1006;
            //}
    	//}
        //收件人地址
        if(DataValidater.isArrEmpty(emailBasicPara.getMailTo())) {
            return EmailConstant.MAIL_TO_EMPTY_CODE;
        }
       
        /*
        if(!DataValidater.isStrEmpty(emailBasicPara.getTemplateCode())){
        	 //主题
        	 if (DataValidater.isStrEmpty(emailBasicPara.getMailSubject())) {
                 return -1007;
             }
        	 //内容
             if (DataValidater.isStrEmpty(emailBasicPara.getMailContent())) {
                 return -1008;
             }
        }*/
        
        return 0;
    }

    public IEmailSendRecordDao getEmailSendRecordDao() {
        return emailSendRecordDao;
    }

    public void setEmailSendRecordDao(IEmailSendRecordDao emailSendRecordDao) {
        this.emailSendRecordDao = emailSendRecordDao;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
    
    private String getEmainEncode() {
		String beanName = BaseDataSourceService.class.getSimpleName();
		BaseDataSourceService baseDataSourceService = (BaseDataSourceService) ContextFactory.getBean(beanName);
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				EMAIL_ENCODE, new HashMap<String, String[]>(), AppConstant.SQL_AND).getReturnObject();
		if (null == baseDataSourceVo) {
			return DEFAULT_ENCODE;
		}
		String emailEncode = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_CODE);
		if (null == emailEncode) {
			return DEFAULT_ENCODE;
		}
		return emailEncode;
	}
}