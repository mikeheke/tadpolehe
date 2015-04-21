/**
 * 
 */
package com.amway.frm.job.exception;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 *
 * 2011-9-27 下午05:20:58
 */
public class JobSysException extends AmwaySysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6236963211727907638L;

	private static final String code = "1104";
	
	public JobSysException(Throwable stack) {
		super(code, stack);
	}

}
