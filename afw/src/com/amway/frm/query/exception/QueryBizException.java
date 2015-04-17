/**
 * 
 */
package com.amway.frm.query.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * @author huangweijin
 *
 * 2011-4-15 下午05:32:21
 */
public class QueryBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293129599073693528L;

	private static final String message = "1307";
	/**
	 * 
	 */
	public QueryBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public QueryBizException(String msg) {
		super(msg);
	}

}
