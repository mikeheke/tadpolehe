/**
 * 
 */
package mikeheke.tadpole.frm.bds.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import mikeheke.tadpole.frm.base.dao.IBaseDao;
import mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor;
import mikeheke.tadpole.frm.bds.entity.BdsXmlData;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;

import org.apache.commons.httpclient.HttpException;
import org.jdom.JDOMException;

/**
 * 
 */
public interface IBdsXmlDataDao extends IBaseDao<BdsXmlData, String> {

	List<BdsXmlData> getBdsXmlDataList(BdsXmlData bdsXmlData)
			throws SQLException;

	List<BdsXmlData> getBdsXmlDataList(BdsSchemaInfor bdsSchemaInfor,
			Map<String, String[]> filterParams, String joinOperator)
			throws SQLException, NamingException;
	
	List<Map<String, String>> getBdsWsXmlDataList(
			BdsSchemaInfor bdsSchemaInfor, String requestXml)
			throws HttpException, IOException;

	BaseDataSourceVo getBdsVo(BdsSchemaInfor bdsSchemaInfor,
			List<String> colNameList, Map<String, String[]> filterParams,
			String joinOperator) throws NamingException, SQLException;

	BaseDataSourceVo getBdsVo(List<String> colNameList,
			List<BdsXmlData> bdsXmlDataList) throws JDOMException, IOException;

	BaseDataSourceVo getBdsVo2(List<String> colNameList,
			List<Map<String, String>> bdsWsXmlDataList);
}
