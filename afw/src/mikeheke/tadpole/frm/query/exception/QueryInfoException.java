/**
 * 
 */
package mikeheke.tadpole.frm.query.exception;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;

/**
 * 
 *
 * 2011-4-15 下午05:25:35
 */
public class QueryInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3726930923126411545L;
	
	private static final String code = "1207";
	
	/**
	 * @param code2
	 */
	public QueryInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);
	}

	public QueryInfoException() {
		super(code);
	}
}
