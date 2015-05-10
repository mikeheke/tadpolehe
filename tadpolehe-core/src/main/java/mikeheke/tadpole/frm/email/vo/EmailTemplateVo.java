package mikeheke.tadpole.frm.email.vo;


/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-22
 * Time: 11:50:52
 * VO
 */
public class EmailTemplateVo {

    //短信模板主键，用于唯一确定一条短信模板记录
    private String emailTemplateId;
    
    private String[] emailTemplateIds;
    
    //短信模板编号，用于客户标识短信模板记录
    private String templateCode;
    
    //短信模板名称，用于客户标识短信模板记录
    private String templateName;
    
    //短信模板主题
    private String emailTemplateSubject;
    
    //短信模板内容
    private String emailTemplateContent;
    
    //应用编码，用于标识短信模板模块属于的应用
    private String applicationId;
    
    //同异步标志，标识邮件采用同步还是异步发送
    private String sendFlag;
    
    //附件标志，标识邮件是否允许发送附件
    private String accessoryFlag;
    
    //邮件模板使用状态，用于用户设置邮件模板记录状态，区别于系统设置记录状态
    private String state;
    
    //备注，用于用户设置短信模板记录备注，
    private String remark;

	public String getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(String emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String[] getEmailTemplateIds() {
		return emailTemplateIds;
	}

	public void setEmailTemplateIds(String[] emailTemplateIds) {
		this.emailTemplateIds = emailTemplateIds;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getEmailTemplateSubject() {
		return emailTemplateSubject;
	}

	public void setEmailTemplateSubject(String emailTemplateSubject) {
		this.emailTemplateSubject = emailTemplateSubject;
	}

	public String getEmailTemplateContent() {
		return emailTemplateContent;
	}

	public void setEmailTemplateContent(String emailTemplateContent) {
		this.emailTemplateContent = emailTemplateContent;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getAccessoryFlag() {
		return accessoryFlag;
	}

	public void setAccessoryFlag(String accessoryFlag) {
		this.accessoryFlag = accessoryFlag;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
