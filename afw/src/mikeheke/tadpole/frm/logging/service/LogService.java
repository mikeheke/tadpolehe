package mikeheke.tadpole.frm.logging.service;

import mikeheke.tadpole.frm.base.service.BaseService;


/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：日志Service接口
 */
public interface LogService extends BaseService {
	
	public static final String LOG_PATH = "log.path";
	public static final String LOG_LEVEL = "log.level";
	public static final String LOGGER_NAME = "mikeheke.tadpole";
	
	/**
	 * 设置日志器
	 * 
	 */
	void setLogger(Class cla);
	
	/**
	 * 设置application code
	 * */
	void setApplicationCode(String applicationCode);
	
	/**
	 * 写操作日志
	 * 
	 * @param type 类型
	 * @param method 方法
	 * @param msg 内容
	 */
	void writeOperLog(String method, String msg);

	void writeOperLog(String method, String msg, String empNumber, String location);

	/**
	 * 写性能日志
	 * @param obj
	 */
	void writePerfLog(Object obj);
	
	/**
	 * Declare：打印调试日志信息
	 * 
	 * @param Object 日志信息
	 * @return void
	 */
	void debug(Object message, Object[] objects);
	
	void debug(Object message, Object object); 
	
	void debug(Object message); 

	/**
	 * Declare：打印提示日志信息
	 * 
	 * @param Object 日志信息
	 * @return void
	 */
	void info(Object message, Object[] objects);
	
	void info(Object message, Object object);
	
	void info(Object message);
	
	/**
	 * Declare：打印警告日志信息
	 * 
	 * @param Object 日志信息
	 * @return void
	 */
	void warn(Object message, Object[] objects);
	
	void warn(Object message, Object object);
	
	void warn(Object message);
	
	/**
	 * Declare：打印错误日志信息
	 * 
	 * @param Object 日志信息
	 * @return void
	 */
	void error(Object message, Object[] objects);
	
	void error(Object message, Object object);
	
	void error(Object message);
	
}