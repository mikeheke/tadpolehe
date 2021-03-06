package mikeheke.tadpole.frm.bds.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import mikeheke.tadpole.frm.base.dao.IBaseDao;
import mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor;

/**
 * 基础数据服务数据持久化接口类
 * 
 */
public interface IBdsSchemaInforDao extends IBaseDao<BdsSchemaInfor, String> {

	/**
	 * 以JDBC方式查询并返回BdsSchemaInfor实体
	 * 
	 * @param bdsSchemaInforVo
	 * @return ReturnMessage<BdsSchemaInforVo>
	 * @throws SQLException
	 * @throws NamingException
	 */
	List<BdsSchemaInfor> getListByJdbc(BdsSchemaInfor bdsSchemaInfor)
			throws SQLException;

}
