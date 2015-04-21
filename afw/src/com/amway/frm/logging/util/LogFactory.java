/**
 * 
 */
package com.amway.frm.logging.util;

import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.logging.service.LogService;

/**
 * 
 *
 * 2011-7-18 下午02:48:25
 */
public class LogFactory {

	/**
	 * 取得日志器
	 * @param cla
	 * @return
	 */
	public static LogService getLogger(Class cla){
		LogService logService = (LogService) ContextFactory
				.getBean(LogService.class.getSimpleName());
		logService.setLogger(cla);
		
		return logService;
	}
	
	/**
	 * 取得日志器
	 * @param cla
	 * @param applicationCode
	 * @return
	 */
	public static LogService getLogger(Class cla,String applicationCode){
		LogService logService = (LogService) ContextFactory
				.getBean(LogService.class.getSimpleName());
		logService.setLogger(cla);
		logService.setApplicationCode(applicationCode);
		return logService;
	}
}
