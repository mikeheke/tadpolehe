package mikeheke.tadpole.frm.exception.exception;


/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：异常类
 */
public class AmwayException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9086443901539734252L;

	private String message;
	
	/**
	 * 
	 */
	public AmwayException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public AmwayException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
