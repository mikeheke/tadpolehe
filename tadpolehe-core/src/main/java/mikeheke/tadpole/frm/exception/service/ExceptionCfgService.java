/**
 * 
 */
package mikeheke.tadpole.frm.exception.service;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.exception.vo.ExceptionCfgVo;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：异常Service接口
 */
public interface ExceptionCfgService extends BaseService {

	void send(ExceptionCfgVo exceptionVo);
}
