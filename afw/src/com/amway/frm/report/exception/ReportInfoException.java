/**
 * 
 */
package com.amway.frm.report.exception;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.exception.exception.AmwayInfoException;

/**
 * @author huangweijin
 *
 * 2011-9-16 下午12:22:38
 */
public class ReportInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1943342017558633964L;
	
	private static final String code = "1208";

	/**
	 * @param code2
	 */
	public ReportInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);

	}

	public ReportInfoException() {
		super(code);

	}
}
