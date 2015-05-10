/**
 * 
 */
package mikeheke.tadpole.frm.bds.exception;

import mikeheke.tadpole.frm.exception.exception.AmwayBizException;

/**
 * 
 *
 * 2011-4-15 下午05:32:21
 */
public class BdsBizException extends AmwayBizException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7835554010368850804L;

	private static final String message = "1302";
	/**
	 * 
	 */
	public BdsBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public BdsBizException(String msg) {
		super(msg);
	}



}
