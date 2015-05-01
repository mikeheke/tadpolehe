package mikeheke.tadpole.frm.email.dao;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.base.dao.IBaseDao;
import mikeheke.tadpole.frm.email.entity.EmailBasicPara;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-25
 * Time: 18:26:30
 * Declare：EmailSendRecord表对应的DAO，适用于操作EmailSendRecord表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public interface IEmailSendRecordDao extends IBaseDao{
	
    //保存邮件发送数据
    EmailBasicPara saveRecord(EmailBasicPara emailBasicPara) throws SQLException;
    
    //保存邮件发送数据
    EmailBasicPara saveSendRecord(EmailBasicPara emailBasicPara) throws SQLException;
    
    // 删除邮件
    public int deleteRecord(EmailBasicPara emailBasicPara) throws SQLException;

    // 查询邮件
    public List<EmailBasicPara> getEmailList() throws SQLException;
    

}
