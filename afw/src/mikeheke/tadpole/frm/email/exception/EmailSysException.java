package mikeheke.tadpole.frm.email.exception;

import mikeheke.tadpole.frm.exception.exception.AmwaySysException;


/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-18
 * Time: 17:07:16
 * Declare：会议室Exception
 */
public class EmailSysException extends AmwaySysException {

    /**
     *
     */
    private static final long serialVersionUID = -6936009124674008086L;

    private static final String code = "1103";

	public EmailSysException(Throwable stack) {
		super(code, stack);
	}
 
}