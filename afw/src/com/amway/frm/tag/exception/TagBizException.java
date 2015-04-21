/**
 * 
 */
package com.amway.frm.tag.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * tag模块业务异常
 * 
 */
public class TagBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1551234586946038325L;
	
	private static final String message = "1310";
	
	/**
	 * 
	 */
	public TagBizException() {
		super(message);

	}

	/**
	 * @param msg
	 */
	public TagBizException(String msg) {
		super(msg);

	}
}
