/**
 * 
 */
package mikeheke.tadpole.frm.mnu.exception;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;

/**
 * 
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
