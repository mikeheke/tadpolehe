package mikeheke.tadpole.frm.email.entity;

import java.io.Serializable;

import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.email.util.EmailConstant;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-23
 * Time: 14:17:46
 * Declare：邮件基本信息VO
 */
public class EmailBasicPara implements Serializable,Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 346772297983058961L;

	private Long id;
	
    // 邮箱服务器
    private String host = EmailConstant.EMAIL_HOST;
    
    // 邮箱用户名
    private String userName = EmailConstant.EMPTY_STR;
    
    // 邮箱密码
    private String password = EmailConstant.EMPTY_STR;
    
    //发件人地址
    private String mailFrom;
    
    //发件人姓名
    private String personalName = EmailConstant.EMAIL_PERSONAL_NAME;
    
    //收件人地址
    private String[] mailTo;
    
    //抄送人地址
    private String[] mailCC;
    
    //密送人地址
    private String[] mailBCC;
    
    //邮件标题
    private String mailSubject;
    
    //邮件内容
    private String mailContent;
    
    //附件地址
    private String[] accessory;
    
    //邮件模板
    private String templateCode;
    
    // 发送标志
    private Integer sendFlag = EmailConstant.TO_SEND;
    
    // 发送次数
    private Integer sendNum = 0;
    
    // 发送模式，"0"：同步，"1"：异步
    private String sendMode = EmailConstant.ASYN_SEND;

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Integer getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String[] getMailTo() {
		return mailTo;
	}
	
	public String getMailTo2() {
		if(DataValidater.isArrEmpty(mailTo)){
			return EmailConstant.EMPTY_STR;
		}
		return StringUtils.join(mailTo, EmailConstant.DOU_SIGN);
	}

	public void setMailTo(String[] mailTo) {
		this.mailTo = mailTo;
	}

	public String[] getMailCC() {
		return mailCC;
	}
	
	public String getMailCC2() {
		if(DataValidater.isArrEmpty(mailCC)){
			return EmailConstant.EMPTY_STR;
		}
		return StringUtils.join(mailCC, EmailConstant.DOU_SIGN);
	}

	public void setMailCC(String[] mailCC) {
		this.mailCC = mailCC;
	}

	public String[] getMailBCC() {
		return mailBCC;
	}
	
	public String getMailBCC2() {
		if(DataValidater.isArrEmpty(mailBCC)){
			return EmailConstant.EMPTY_STR;
		}
		return StringUtils.join(mailBCC, EmailConstant.DOU_SIGN);
	}

	public void setMailBCC(String[] mailBCC) {
		this.mailBCC = mailBCC;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String[] getAccessory() {
		return accessory;
	}

	public String getAccessory2() {
		if(DataValidater.isArrEmpty(accessory)){
			return EmailConstant.EMPTY_STR;
		}
		return StringUtils.join(accessory, EmailConstant.DOU_SIGN);
	}
	
	public void setAccessory(String[] accessory) {
		this.accessory = accessory;
	}
	
	public String getTemplateCode2() {
		if(DataValidater.isStrEmpty(templateCode)){
			return EmailConstant.EMPTY_STR;
		}
		return templateCode;
	}

	public String getSendMode() {
		return sendMode;
	}

	public void setSendMode(String sendMode) {
		this.sendMode = sendMode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	@Override
	public EmailBasicPara clone() throws CloneNotSupportedException {
		return (EmailBasicPara) super.clone();
	}
	
}
