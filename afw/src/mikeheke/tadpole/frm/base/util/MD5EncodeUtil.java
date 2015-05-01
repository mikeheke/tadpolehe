package mikeheke.tadpole.frm.base.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author lenovo
 *
 */
public class MD5EncodeUtil {

	/**
	 * @param args
	 * 
	 * 
	 */
	static Logger logger = LoggerFactory.getLogger(MD5EncodeUtil.class);

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {

		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * J 转换byte到16进制
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		
		final Integer _256 = 256;
		final Integer _16 = 16;
		int n = b;
		if (n < 0) {
			n = _256 + n;
		}
		int d1 = n / _16;
		int d2 = n % _16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * J 编码
	 * 
	 * @param origin
	 * @return
	 */

	// MessageDigest 为 JDK 提供的加密类
	public static String MD5Encode(String origin) {

		if (origin == null){
			return null;
		}
		String resultString = null;
		try {
			resultString = new String(origin);
//			MessageDigest md = MessageDigest.getInstance("SHA-1");
			final String ENCODE_TYPE = "SHA-1";
			MessageDigest md = MessageDigest.getInstance(ENCODE_TYPE);
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes(AppConstant.UTF_8)));
		} catch (Exception ex) {
			final String msg = " --MD5加密异常：";
			logger.error(MD5EncodeUtil.class + msg + ex);
		}
		return resultString;
	}

	public static void main(String[] args) {
		//System.out.println(MD5Encode(AppConstant.MD5_KEY+"111111"));
		// String a = null;
		// System.out.println( new String(a) );
	}
}
