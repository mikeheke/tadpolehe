package com.amway.frm.email.exception;

import com.amway.frm.exception.exception.AmwayBizException;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-4-16
 * Time: 10:52:41
 * Declare:
 */

public class EmailBizException extends AmwayBizException {

    /**
     *
     */
    private static final long serialVersionUID = -6936009124674008086L;

    private static final String message = "1303";
    
	/**
	 * 
	 */
	public EmailBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public EmailBizException(String msg) {
		super(msg);
	}

}
