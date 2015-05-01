/**
 * 
 */
package mikeheke.tadpole.frm.bds.exception;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;

/**
 * 
 *
 * 2011-4-15 下午05:25:35
 */
public class BdsInfoException extends AmwayInfoException {



	/**
	 * 
	 */
	private static final long serialVersionUID = -8803295414298716615L;

	private static final String code = "1202";
	
	/**
	 * @param code2 代码
	 */
	public BdsInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}

	public BdsInfoException() {
		super(code);
	}
}
