package mikeheke.tadpole.frm.sms.entity;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-23
 * Time: 14:17:46
 * Declare：短信信息VO
 */
public class SmsBasicPara {
	
	private Long id;
	
    //收件人手机号
    private String smsTo;
    
    //短信内容
    private String smsContent;
    
    //短信模板
    private String templateCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSmsTo() {
		return smsTo;
	}

	public void setSmsTo(String smsTo) {
		this.smsTo = smsTo;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

}
