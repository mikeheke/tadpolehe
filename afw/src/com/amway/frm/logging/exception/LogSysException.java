/**
 * 
 */
package com.amway.frm.logging.exception;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 *
 * 2011-10-19 上午09:53:55
 */
public class LogSysException extends AmwaySysException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7092096524176755396L;
	
	private static final String code = "1105";

	/**
	 * @param stack
	 */
	public LogSysException(Throwable stack) {
		super(code, stack);
	}

}
