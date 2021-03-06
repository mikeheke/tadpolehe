package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import mikeheke.tadpole.frm.base.dao.IBaseDao;

/**
 * Created by 
 * @author hyc
 * Date: 2013-12-11
 * Time: 11:11:33
 * Declare: 配置导入 导出
 */
public interface IExportConfigDao  extends IBaseDao<Object, String> {

	/**
     * Declare：根据表名获得列信息
     *
     * @param String table
     * @return List
     * @throws java.sql.SQLException
     */	
	List<HashMap> fetchColumnByTableName(String table) throws SQLException;

	/**
     * 根据表名获取数据
     *
     * @param String table
     * @param String condition
     * @param List columns
     * @return List
     * @throws java.sql.SQLException
     */	
	List<HashMap> fetchDataByTableName(String table, String condition,
			List<HashMap> columns) throws SQLException;

	/**
     * 导入sql语句
     *
     * @param List sqlList
     * @return List
     */
	List<HashMap> importSqlList(List<String> sqlList);


	/**
     * 根据应用id获得导出时间
     *
     * @param String appid
     * @return
     */
	String fetchExportDate(String appid) throws SQLException;
	
	/**
     * 记录应用的导出时间
     *
     * @param String appid
     * @return
     */
	void markExportTime(String appid);
	
}
