/**
 * 
 */
package mikeheke.tadpole.frm.tag.exception;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;

/**
 * 
 *
 * 2011-10-19 下午04:16:02
 */
public class TagInfoException extends AmwayInfoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1134025526925865466L;
	
	private static final String code = "1210";

	/**
	 * 
	 */
	public TagInfoException() {
		super(code);

	}

	/**
	 * @param code2
	 */
	public TagInfoException(String code2) {
		super(code+AppConstant.EXP_SEP+code2);

	}

}
