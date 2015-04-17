/**
 * 
 */
package com.amway.frm.bds.exception;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询异常
 */
public class BdsSysException extends AmwaySysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5021838968170791811L;

	private static final String code = "1102";
	
	public BdsSysException(Throwable stack) {
		super(code, stack);
	}
}
