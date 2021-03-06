package mikeheke.tadpole.frm.bds.service;


import java.util.Map;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.exception.BdsBizException;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
/**
 * 供内外部调用的基础数据服务接口类
 * 
 */
public interface BaseDataSourceService extends  BaseService<Object>{
	
	/**
	 * 基础数据服务对外以JSON格式提供数据的方法
	 * @param bdsKey    基础数据代码
	 * @param filterMap    过滤字段名:过滤字段值
	 * @param joinOperator   sql语句条件连接符and或者or
	 * @return ReturnMessage 返回JSON字符串
	 * @throws BdsBizException 
	 */
	ReturnMessage<String> getJSONData(String bdsKey,
			Map<String, String[]> filterMap, String joinOperator);
	
	/**
	 * 基础数据服务对外以BaseDataSourceVo格式提供数据的方法
	 * @param bdsKey    基础数据代码
	 * @param filterMap    过滤字段名:过滤字段值
	 * @param joinOperator   sql语句条件连接符and或者or
	 * @return ReturnMessage 返回BaseDataSourceVo对象
	 * @throws BdsBizException 
	 */
	ReturnMessage<BaseDataSourceVo> getBdsVoData(String bdsKey,
			Map<String, String[]> filterMap, String joinOperator);
}
