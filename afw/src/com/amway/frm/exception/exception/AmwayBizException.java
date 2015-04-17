/**
 * 
 */
package com.amway.frm.exception.exception;


/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54 
 * Declare：业务异常类
 */
public class AmwayBizException extends AmwayException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296942231200674627L;

	private static final String message = "1300";
	/**
	 * 
	 */
	public AmwayBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public AmwayBizException(String msg) {
		super(msg);
	}

}
