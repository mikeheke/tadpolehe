/**
 * 
 */
package com.amway.frm.report.exception;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 *
 * 2011-9-16 下午12:23:19
 */
public class ReportSysException extends AmwaySysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 437244794805238139L;

	private static final String code = "1108";
	
	/**
	 * @param stack
	 */
	public ReportSysException(Throwable stack) {
		super(code, stack);

	}

}
