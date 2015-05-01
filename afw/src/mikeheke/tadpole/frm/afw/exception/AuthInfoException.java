/**
 * 
 */
package mikeheke.tadpole.frm.afw.exception;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;

/**
 * 
 *
 * 2011-10-19 上午10:05:55
 */
public class AuthInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3680723245761695930L;

	private static final String code = "1201";
	
	/**
	 * @param code2
	 */
	public AuthInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}
	
	public AuthInfoException() {
		super(code);
	}

}
