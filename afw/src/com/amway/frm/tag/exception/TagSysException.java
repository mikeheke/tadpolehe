/**
 * 
 */
package com.amway.frm.tag.exception;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 *
 * 2011-6-10 上午10:24:44
 */
public class TagSysException extends AmwaySysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489281142218849229L;

	private static final String code = "1110";
	
	/**
	 * @param stack
	 */
	public TagSysException(Throwable stack) {
		super(code, stack);

	}

}
