/**
 * 
 */
package com.amway.frm.mnu.exception;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.exception.exception.AmwayInfoException;

/**
 * @author huangweijin
 *
 * 2011-10-19 上午10:02:47
 */
public class MnuInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 909611338161231797L;
	
	private static final String code = "1206";

	/**
	 * @param code2
	 */
	public MnuInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}

	public MnuInfoException() {
		super(code);
	}
}