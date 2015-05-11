/**
 * 
 */
package mikeheke.tadpole.frm.query.service;

import java.util.List;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.query.entity.Query;

/**
 * 
 *
 * 2011-4-30 上午09:53:25
 */
public interface QueryService extends BaseService {

	ReturnMessage<Query> deleteQuerys(List<Query> querys);
	
	ReturnMessage<Query> getQueryByQuery(Query query);
	
	String getDisplayNameByCode(String dictCode, String code);
}
