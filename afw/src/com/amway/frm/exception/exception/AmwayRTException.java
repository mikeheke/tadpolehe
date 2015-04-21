/**
 * 
 */
package com.amway.frm.exception.exception;


/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：运行异常类
 */
public class AmwayRTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4977190050350112389L;

	//异常代码
	private String code;
	
	//堆栈
	private Throwable stack;
	
	
	/**
	 * 
	 */
	public AmwayRTException() {
		super();
	}

	/**
	 * @param code
	 */
	public AmwayRTException(String code) {
		super(code);
		this.code = code;
	}
	
	/**
	 * @param stack
	 */
	public AmwayRTException(Throwable stack) {
		super(stack);
		this.stack = stack;
	}
	
	/**
	 * @param code
	 * @param stack
	 */
	public AmwayRTException(String code, Throwable stack) {
		super(code, stack);
		this.code = code;
		this.stack = stack;
	}

	public String getCode() {
		return code;
	}

	public Throwable getStack() {
		return stack;
	}

}
