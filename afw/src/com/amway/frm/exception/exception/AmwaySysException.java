/**
 * 
 */
package com.amway.frm.exception.exception;





/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：系统异常类
 */
public class AmwaySysException extends AmwayRTException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -517464440242026036L;

	private static final String code = "1100";
	
	/**
	 * @param code 代码
	 * @param stack 堆栈
	 */
	public AmwaySysException(String code, Throwable stack) {
		super(code, stack);
	}
	

	/**
	 * @param stack 堆栈
	 */
	public AmwaySysException(Throwable stack) {
		super(code, stack);
	}
}
