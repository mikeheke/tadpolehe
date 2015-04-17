/**
 * 
 */
package com.amway.frm.logging.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * @author huangweijin
 *
 * 2011-10-19 上午10:00:45
 */
public class LogBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3201654721917306808L;
	
	private static final String message = "1305";

	/**
	 * 
	 */
	public LogBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public LogBizException(String msg) {
		super(msg);
	}

}
