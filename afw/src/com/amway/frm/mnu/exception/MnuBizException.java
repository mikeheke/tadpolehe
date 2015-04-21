/**
 * 
 */
package com.amway.frm.mnu.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * 
 *
 * 2011-10-19 上午10:03:45
 */
public class MnuBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9177134216167268764L;
	
	private static final String message = "1306";

	/**
	 * 
	 */
	public MnuBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public MnuBizException(String msg) {
		super(msg);
	}

}
