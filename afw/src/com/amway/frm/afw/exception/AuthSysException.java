/**
 * 
 */
package com.amway.frm.afw.exception;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 *
 * 2011-10-19 上午10:05:12
 */
public class AuthSysException extends AmwaySysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8603850461803621854L;
	
	private static final String code = "1101";
	
	public AuthSysException(Throwable stack) {
		super(code, stack);
	}
}
