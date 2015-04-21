package com.amway.frm.email.exception;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.exception.exception.AmwayInfoException;

/**
 * Created by IntelliJ IDEA. 
 * 
 */

public class EmailInfoException extends AmwayInfoException {
	/**
	 *
	 */
	private static final long serialVersionUID = -2328416274304841112L;
	
	private static final String code = "1203";

	/**
	 * @param code2
	 */
	public EmailInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}

	/**
	 * @param code
	 */
	public EmailInfoException() {
		super(code);
	}
	
}
