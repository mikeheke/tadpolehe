/**
 * 
 */
package com.amway.frm.report.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * @author huangweijin
 *
 * 2011-9-16 下午12:21:51
 */
public class ReportBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1697123946488059051L;
	
	private static final String message = "1308";

	/**
	 * 
	 */
	public ReportBizException() {
		super(message);

	}

	/**
	 * @param msg
	 */
	public ReportBizException(String msg) {
		super(msg);

	}

}
