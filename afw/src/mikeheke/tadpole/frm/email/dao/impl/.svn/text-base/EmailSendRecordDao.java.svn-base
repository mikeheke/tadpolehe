package mikeheke.tadpole.frm.email.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.base.dao.impl.BaseDao;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.JDBCHelper;
import mikeheke.tadpole.frm.email.dao.IEmailSendRecordDao;
import mikeheke.tadpole.frm.email.entity.EmailBasicPara;
import mikeheke.tadpole.frm.email.util.EmailConstant;

/**
 * Created by IntelliJ IDEA. 
 * @author lzm 
 * Date: 2011-3-25 
 * Time: 18:26:19
 * Declare：EmailSendRecord表对应的DAO
 * ，适用于操作EmailSendRecord表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public class EmailSendRecordDao extends BaseDao implements IEmailSendRecordDao {

	@Override
	public EmailBasicPara saveRecord(EmailBasicPara emailBasicPara)
			throws SQLException {

		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());
			long id = getAsynTableRecordId(jdbcHelper);
			Object[] val = getParamValues(emailBasicPara);

			String tableName = getAsynTableName();
			StringBuffer sql = new StringBuffer();
			final String sql1 = "insert into ";
			sql.append(sql1).append(tableName);
			final String sql2 = "(EMAIL_SEND_RECORD_ID,HOST,";
			sql.append(sql2);
			final String sql3 = "PERSONAL_NAME,MAIL_FROM_ADD,";
			sql.append(sql3);
			final String sql4 = "MAIL_TO_ADD,MAIL_CC_ADD,MAIL_BCC_ADD,";
			sql.append(sql4);
			final String sql5 = "TEMPLATES_ID,EMAIL_SUBJECT,EMAIL_CONTENT,ACCESSORY,";
			sql.append(sql5);
			final String sql6 = "SEND_FLAG,SEND_NUM,RECORD_STATE,STATE,";
			sql.append(sql6);
			final String sql7 = "SEND_DATE,CREATED_TIME,CREATED_USER_ID)";
			sql.append(sql7);
			final String sql8 = " values(";
			sql.append(sql8);
			final String sql9 = ",?,?,?,?,?,?,?,?,?,?,?,?,1,1,?,?,?)";
			sql.append(id).append(sql9);
			jdbcHelper.executeUpdate(sql.toString(), val);
			
			emailBasicPara.setId(id);
			
		} finally {
			jdbcHelper.closeAll();
		}
		return emailBasicPara;
	}
	
	private Object[] getParamValues(EmailBasicPara emailBasicPara){
		
		Object[] val = new Object[] { 
				emailBasicPara.getHost(), // 邮件服务器
				emailBasicPara.getPersonalName(), // 发件人名称
				emailBasicPara.getMailFrom(), // 发件人地址
				emailBasicPara.getMailTo2(), // 收件人地址
				emailBasicPara.getMailCC2(), // 抄送地址,无则为空
				emailBasicPara.getMailBCC2(), // 密送地址,无则为空
				emailBasicPara.getTemplateCode2(), // 模板编号,无则为空
				emailBasicPara.getMailSubject(), // 邮件主题
				emailBasicPara.getMailContent(), // 邮件正文
				emailBasicPara.getAccessory2(), // 附件地址,无则为空
				emailBasicPara.getSendFlag(),	// 邮件发送标志,0:待发送 1:成功 2: 失败
				emailBasicPara.getSendNum(),	// 邮件可发送次数,初始化为 2
				// 记录状态,初始化为 1 ,启用
				new Timestamp(new Date().getTime()), // 邮件发送日期
				new Timestamp(new Date().getTime()), // 创建时间
				EmailConstant.EMPTY_STR // 创建人
		};
		
		return val;
	}
	
	@Override
	public EmailBasicPara saveSendRecord(EmailBasicPara emailBasicPara) throws SQLException {
	
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());
			long id = getSendTableRecordId(jdbcHelper);
			Object[] val = getParamValues(emailBasicPara);

			String tableName = getSendTableName();
			StringBuffer sql = new StringBuffer();
			final String sql1 = "insert into ";
			sql.append(sql1).append(tableName);
			final String sql2 = "(EMAIL_SEND_RECORD_ID,HOST,";
			sql.append(sql2);
			final String sql3 = "PERSONAL_NAME,MAIL_FROM_ADD,";
			sql.append(sql3);
			final String sql4 = "MAIL_TO_ADD,MAIL_CC_ADD,MAIL_BCC_ADD,";
			sql.append(sql4);
			final String sql5 = "TEMPLATES_ID,EMAIL_SUBJECT,EMAIL_CONTENT,ACCESSORY,";
			sql.append(sql5);
			final String sql6 = "SEND_FLAG,SEND_NUM,RECORD_STATE,STATE,";
			sql.append(sql6);
			final String sql7 = "SEND_DATE,CREATED_TIME,CREATED_USER_ID)";
			sql.append(sql7);
			final String sql8 = "values(";
			sql.append(sql8);
			final String sql9 = ",?,?,?,?,?,?,?,?,?,?,?,?,1,1,?,?,?)";
			sql.append(id).append(sql9);
			jdbcHelper.executeUpdate(sql.toString(), val);
			
			emailBasicPara.setId(id);
		} finally {
			jdbcHelper.closeAll();
		}
		
		return emailBasicPara;
	}
	
	private long getSendTableRecordId(JDBCHelper jdbcHelper) throws SQLException{
		
		final String jql = "select MSSQ_EMAIL_SEND_RECORD.NEXTVAL EMAIL_SEND_RECORD_ID From DUAL";
		boolean result = jdbcHelper.getFirstDocument(jql);
		long id = 0;
		if (result) {
			id = jdbcHelper.getItemLongValue(EmailConstant.EMAIL_SEND_RECORD_ID);
		}
		
		return id;
	}
	
	private String getSendTableName(){
		
		final String tableName = "HSTB_EMAIL_SEND_RECORD";
		final String TimeStr = "_yyyyMM";
		
		return tableName+DataConverter.fmtDateToDateStr(new Date(), TimeStr);
	}
	
	private long getAsynTableRecordId(JDBCHelper jdbcHelper) throws SQLException{
		
		final String jql = "select MSSQ_EMAIL_ASYN_RECORD.NEXTVAL EMAIL_SEND_RECORD_ID From DUAL";
		boolean result = jdbcHelper.getFirstDocument(jql);
		long id = 0;
		if (result) {
			id = jdbcHelper.getItemLongValue(EmailConstant.EMAIL_SEND_RECORD_ID);
		}
		
		return id;
	}

	private String getAsynTableName(){
		
		final String tableName = "MSTB_EMAIL_ASYN_RECORD";
		
		return tableName;
	}

	@Override
	public int deleteRecord(EmailBasicPara emailBasicPara) throws SQLException {
		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());

			String tableName = getAsynTableName();

			StringBuffer sql = new StringBuffer();
			final String sql1 = "delete from ";
			sql.append(sql1).append(tableName);
			final String sql2 = " where EMAIL_SEND_RECORD_ID = ";
			sql.append(sql2);
			sql.append(emailBasicPara.getId());

			return jdbcHelper.executeUpdate(sql.toString());
			
		} finally {
			jdbcHelper.closeAll();
		}
	}

	@Override
	public List<EmailBasicPara> getEmailList() throws SQLException {
		List<EmailBasicPara> emailList = new ArrayList<EmailBasicPara>();

		JDBCHelper jdbcHelper = null;
		try {
			jdbcHelper = new JDBCHelper(getDataSource());

			String tableName = getAsynTableName();
			final String sql = "select * from ";
			//为修正邮件异常发送问题，增加5天前的邮件不作 发送 edit by Gary Long 2014-6-23
			final String wheresql=" where send_date>to_date(sysdate - 5) and RECORD_STATE=1 ";
			boolean result = jdbcHelper.getFirstDocument(sql+tableName+wheresql);
			while (result) {   
				EmailBasicPara emailBasicPara = getEmailBasicPara(jdbcHelper);
				emailList.add(emailBasicPara);
				result = jdbcHelper.getNextDocument();
			}
		} finally {
			jdbcHelper.closeAll();
		}
 
		return emailList;
	}

	private EmailBasicPara getEmailBasicPara(JDBCHelper jdbcHelper)
			throws SQLException {
		
		EmailBasicPara emailBasicPara = new EmailBasicPara();

		emailBasicPara.setId(jdbcHelper.getItemLongValue(EmailConstant.EMAIL_SEND_RECORD_ID));
		emailBasicPara.setHost(jdbcHelper.getItemValue(EmailConstant.HOST));
		emailBasicPara.setPersonalName(jdbcHelper.getItemValue(EmailConstant.PERSONAL_NAME));
		emailBasicPara.setMailFrom(jdbcHelper.getItemValue(EmailConstant.MAIL_FROM_ADD));
		emailBasicPara.setMailTo(jdbcHelper.getItemValue(EmailConstant.MAIL_TO_ADD).split(EmailConstant.DOU_SIGN));
		emailBasicPara.setMailCC(jdbcHelper.getItemValue(EmailConstant.MAIL_CC_ADD).split(EmailConstant.DOU_SIGN));
		emailBasicPara.setMailBCC(jdbcHelper.getItemValue(EmailConstant.MAIL_BCC_ADD).split(EmailConstant.DOU_SIGN));
		emailBasicPara.setTemplateCode(jdbcHelper.getItemValue(EmailConstant.TEMPLATES_ID));
		emailBasicPara.setMailSubject(jdbcHelper.getItemValue(EmailConstant.EMAIL_SUBJECT));
		emailBasicPara.setMailContent(jdbcHelper.getItemValue(EmailConstant.EMAIL_CONTENT));
		emailBasicPara.setAccessory(jdbcHelper.getItemValue(EmailConstant.ACCESSORY).split(EmailConstant.DOU_SIGN));
		emailBasicPara.setSendFlag(jdbcHelper.getItemIntegerValue(EmailConstant.SEND_FLAG));
		emailBasicPara.setSendNum(jdbcHelper.getItemIntegerValue(EmailConstant.SEND_NUM));

		return emailBasicPara;
	}
}
