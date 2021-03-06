/**
 * 
 */
package mikeheke.tadpole.frm.job.exception;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;

/**
 * 
 *
 * 2011-9-27 下午05:21:42
 */
public class JobInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5103590590054017520L;
	
	private static final String code = "1204";

	/**
	 * @param code2
	 */
	public JobInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}

	public JobInfoException() {
		super(code);
	}
}
