/**
 * 
 */
package mikeheke.tadpole.frm.tag.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.tag.vo.Select;

/**
 *选择标签接口类
 *
 */
public interface SelectTagService extends BaseService{
	/**
	 * 获得select标签的数据集
	 * @param select  标签体
	 * @param jsonString  json数据集
	 * @return  json数据集
	 */
	ReturnMessage<String> getSelectTagDatas(Select select);
	
	List<Map> getDatasFromScope(Collection source);
	
	List<Map> getDatasFromBDS(Select select);
}
