/**
 * 
 */
package mikeheke.tadpole.frm.mnu.exception;

import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

/**
 * 
 *
 * 2011-10-19 上午10:01:56
 */
public class MnuSysException extends AmwaySysException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7796527897361922687L;
	
	private static final String code = "1106";

	/**
	 * @param stack
	 */
	public MnuSysException(Throwable stack) {
		super(code, stack);
	}

}
