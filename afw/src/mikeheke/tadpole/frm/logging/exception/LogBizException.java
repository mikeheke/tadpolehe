/**
 * 
 */
package mikeheke.tadpole.frm.logging.exception;

import mikeheke.tadpole.frm.exception.exception.AmwayBizException;

/**
 * 
 *
 * 2011-10-19 上午10:00:45
 */
public class LogBizException extends AmwayBizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3201654721917306808L;
	
	private static final String message = "1305";

	/**
	 * 
	 */
	public LogBizException() {
		super(message);
	}

	/**
	 * @param msg
	 */
	public LogBizException(String msg) {
		super(msg);
	}

}
