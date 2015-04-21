/**
 * 
 */
package com.amway.frm.exception.service;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.exception.vo.ExceptionCfgVo;

/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：异常Service接口
 */
public interface ExceptionCfgService extends BaseService {

	void send(ExceptionCfgVo exceptionVo);
}
