package com.amway.frm.sms.exception;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.exception.exception.AmwayInfoException;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-4-16
 * Time: 10:53:54
 * Declare:
 */

public class SmsInfoException extends AmwayInfoException {
  /**
	 *
	 */
	private static final long serialVersionUID = -2328416274304841112L;

	private static final String code = "1209";
	
	/**
	 * @param code2
	 */
	public SmsInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);

	}
	
	public SmsInfoException() {
		super(code);

	}
	
}
