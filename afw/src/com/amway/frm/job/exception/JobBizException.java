/**
 * 
 */
package com.amway.frm.job.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * 
 *
 * 2011-9-27 下午05:22:16
 */
public class JobBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2702986597147374311L;

	private static final String message = "1304";
	
	/**
	 * 
	 */
	public JobBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public JobBizException(String msg) {
		super(msg);
	}

}
