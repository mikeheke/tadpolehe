package com.amway.frm.sms.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.naming.NamingException;

import com.amway.frm.base.dao.impl.BaseDao;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.JDBCHelper;
import com.amway.frm.sms.dao.ISmsSendRecordDao;
import com.amway.frm.sms.entity.SmsBasicPara;
import com.amway.frm.sms.util.SmsConstant;

/**
 * Created by IntelliJ IDEA. 
 * 
 * Declare：短信表对应的DAO，适用于操作短信表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public class SmsSendRecordDao extends BaseDao implements ISmsSendRecordDao {

	/**
	 * Declare：根据参数信息保存数据
	 * 
	 * @param smsBasicPara
	 *            短信信息
	 * @return
	 * @throws SQLException
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	@Override
	public SmsBasicPara saveRecord(SmsBasicPara smsBasicPara)
			throws SQLException {

		SmsBasicPara smsBasicParaRet = null;
		try{
			smsBasicParaRet = saveRemotRecord(smsBasicPara);
		}finally{
			smsBasicParaRet = saveLocalRecord(smsBasicPara);
		}
		
		return smsBasicParaRet;
	}
	
	@Override
	public SmsBasicPara saveRemotRecord(SmsBasicPara smsBasicPara)
			throws SQLException {
		
		JDBCHelper jdbcHelper = null;
		try {
			Object[] val = getParamsValue(smsBasicPara);

			jdbcHelper = new JDBCHelper(AppConstant.SMS_DATASOURCE_NAME);

			StringBuffer sql = new StringBuffer();
			final String sql1 = "insert into ";
			sql.append(sql1).append(AppConstant.DB2_USER);
			final String sql2 = ".QLSMSSENT ";
			sql.append(sql2);
			final String sql3 = "(DMP,MSG,CREATETIME,STATE,ACCTID)";
			sql.append(sql3);
			final String sql4 = "values(?,?,?,0,?)";
			sql.append(sql4);

			// 插入数据库
			jdbcHelper.executeUpdate(sql.toString(), val);

			//sql = "insert into SMSQUEQUE "
			//		+ "(DMP,MSG,CREATETIME,STATE,USERID)" + "values(?,?,?,0,?)";
			// 插入数据库
			//jdbcHelper.executeUpdate(sql, val);
			
			smsBasicPara.setId(System.nanoTime());
		} catch (NamingException e) {
			throw new SQLException(e);
		} finally {
			if (jdbcHelper != null) {
				jdbcHelper.closeAll();
			}
		}

		return smsBasicPara;
	}
	
	@Override
	public SmsBasicPara saveLocalRecord(SmsBasicPara smsBasicPara)
			throws SQLException {
		
		JDBCHelper jdbcHelper = null;
		try {
			Object[] val = getParamsValue(smsBasicPara);

			jdbcHelper = new JDBCHelper(getDataSource());

			//插入本地数据库
			long id = getTableRecordId(jdbcHelper);
			StringBuffer sql = new StringBuffer();
			final String sql1 = "insert into HSTB_SMS_SEND_RECORD";
			sql.append(sql1);
			sql.append(DataConverter.fmtDateToDateStr(new Date(), SmsConstant._YYYYMM));
			final String sql2 = "(SMS_SEND_RECORD_ID,DMP,MSG,CREATETIME,STATE,USERID)";
			sql.append(sql2);
			final String sql3 = "values(";
			sql.append(sql3).append(id);
			final String sql4 = ",?,?,?,0,?)";
			sql.append(sql4);

			jdbcHelper.executeUpdate(sql.toString(), val);

			smsBasicPara.setId(id);
		} finally {
			if (jdbcHelper != null) {
				jdbcHelper.closeAll();
			}
		}
		
		return smsBasicPara;
	}
	
	private Object[] getParamsValue(SmsBasicPara smsBasicPara){
		
		Object[] val = new Object[] { smsBasicPara.getSmsTo(), // 收件人地址
				smsBasicPara.getSmsContent(), // 短信正文
				new Timestamp(new Date().getTime()), // 创建时间
				0 // ACCTID
		};
		return val;
	}
	
	private long getTableRecordId(JDBCHelper jdbcHelper)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		
		final String sql = "select HSSQ_SMS_SEND_RECORD.NEXTVAL SMS_SEND_RECORD_ID from DUAL";
		boolean result = jdbcHelper.getFirstDocument(sql);
		long id = 0;
		if (result) {
			id = jdbcHelper.getItemLongValue(SmsConstant.SMS_SEND_RECORD_ID);
		}
		
		return id;
	}
}
