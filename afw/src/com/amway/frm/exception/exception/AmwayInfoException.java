/**
 * 
 */
package com.amway.frm.exception.exception;



/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：提示异常类
 */
public class AmwayInfoException extends AmwayRTException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2328416274304841112L;

	private static final String code = "1200";
	
	/**
	 * @param code 代码
	 * 
	 */
	public AmwayInfoException(String code) {
		super(code);
	}

	public AmwayInfoException() {
		
		super(code);
	}
}
