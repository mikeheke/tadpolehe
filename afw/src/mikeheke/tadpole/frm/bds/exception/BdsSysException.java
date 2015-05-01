/**
 * 
 */
package mikeheke.tadpole.frm.bds.exception;

import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

/**
 * 
 * 
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
