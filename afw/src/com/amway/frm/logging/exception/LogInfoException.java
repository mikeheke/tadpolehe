/**
 * 
 */
package com.amway.frm.logging.exception;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.exception.exception.AmwayInfoException;

/**
 * @author huangweijin
 *
 * 2011-10-19 上午09:59:52
 */
public class LogInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5622502147679955988L;
	
	private static final String code = "1205";

	/**
	 * @param code2
	 */
	public LogInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}
	
	/**
	 * @param code
	 */
	public LogInfoException() {
		super(code);
	}

}
