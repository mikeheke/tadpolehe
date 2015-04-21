/**
 * 
 */
package com.amway.frm.afw.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * 
 *
 * 2011-10-19 上午10:06:29
 */
public class AuthBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2670380960578185544L;

	private static final String message = "1301";
	
	/**
	 * 
	 */
	public AuthBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public AuthBizException(String msg) {
		super(msg);
	}

}
