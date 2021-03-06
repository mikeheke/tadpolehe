package mikeheke.tadpole.frm.logging.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.vo.SysInfoBean;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.util.XMLDomHelper;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.logging.dao.ILogDao;
import mikeheke.tadpole.frm.logging.entity.LogOperation;
import mikeheke.tadpole.frm.logging.entity.LogPerformance;
import mikeheke.tadpole.frm.logging.exception.LogSysException;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogConstant;
import mikeheke.tadpole.frm.logging.vo.LogLevel;
import mikeheke.tadpole.frm.logging.vo.LogVo;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * 
 * @author lenovo
 * 
 */
public class LogImpl extends BaseImpl implements LogService {

	private ILogDao logDao;
	private String applicationCode;

	private ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
	private LoggerContext loggerContext = null;

	private Class cla;

	@Override
	@Transactional
	public void writePerfLog(Object obj) {
		info(obj);
	}

	@Override
	@Transactional
	public void writeOperLog(String method, String msg) {

		writeOperLog(method, msg, null, null);
	}

	@Override
	@Transactional
	public void writeOperLog(String method, String msg, String empNumber,
			String location) {

		LogOperation operator = new LogOperation();
		Application application = null;
		SysInfoBean sysInfoBean = getSysInfo();
		if (sysInfoBean.getApplication() == null
				&& this.applicationCode != null) {

			List<Application> list = this.getApplications(sysInfoBean
					.getApplication());

			if (list != null && !list.isEmpty()) {
				application = list.get(0);
				operator.setApplication(application);
			}

		} else {
			operator.setApplication(sysInfoBean.getApplication());
		}

		operator.setModule(sysInfoBean.getCurModule());
		if (empNumber != null && location != null) {
			operator.setEmpNumber(empNumber);
			operator.setUserLocation(location);
		} else if (sysInfoBean.getUserProfile() != null) {
			operator.setEmpNumber(sysInfoBean.getUserProfile().getEmpNumber());
			operator.setUserLocation(sysInfoBean.getUserProfile().getLocation());
			operator.setCreatedUserId(sysInfoBean.getUserProfile()
					.getCreatedUserId());
		} else {
			operator.setEmpNumber(LogConstant.EMPTY_STR);
			operator.setUserLocation(LogConstant.EMPTY_STR);
		}
		operator.setEventAction(this.cla.getName());
		operator.setEventType(method);
		operator.setLogContent(msg);
		operator.setLogTime(new Date());
		operator.setRemark(LogConstant.EMPTY_STR);
		operator.setCreatedTime(new Date());

		info(operator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.LogService#debug(java.lang.Object)
	 */
	@Override
	public void debug(Object message) {
		writeLog(message, new Object[] {}, LogLevel.DEBUG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#debug(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void debug(Object message, Object param) {
		writeLog(message, new Object[] { param }, LogLevel.DEBUG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#debug(java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public void debug(Object message, Object[] params) {
		writeLog(message, params, LogLevel.DEBUG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.LogService#info(java.lang.Object)
	 */
	@Transactional
	@Override
	public void info(Object message) {
		writeLog(message, new Object[] {}, LogLevel.INFO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#info(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void info(Object message, Object param) {
		writeLog(message, new Object[] { param }, LogLevel.INFO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#info(java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public void info(Object message, Object[] params) {
		writeLog(message, params, LogLevel.INFO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.LogService#warn(java.lang.Object)
	 */
	@Transactional
	@Override
	public void warn(Object message) {
		writeLog(message, new Object[] {}, LogLevel.WARN);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#warn(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void warn(Object message, Object param) {
		writeLog(message, new Object[] { param }, LogLevel.WARN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#warn(java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public void warn(Object message, Object[] params) {
		writeLog(message, params, LogLevel.WARN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.LogService#error(java.lang.Object)
	 */
	@Transactional
	@Override
	public void error(Object message) {
		writeLog(message, new Object[] {}, LogLevel.ERROR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#error(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void error(Object message, Object param) {
		writeLog(message, new Object[] { param }, LogLevel.ERROR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#error(java.lang.Object,
	 * java.lang.Object[])
	 */
	@Override
	public void error(Object message, Object[] params) {
		writeLog(message, params, LogLevel.ERROR);
	}

	private void writeLog(Object log, Object[] params, LogLevel level) {

		if (isDBLog(log) && params.length == 0) {
			this.logToDB(log);
		}
		if (isFileLog(log)) {
			this.logToFile(log, params, level);
		}
	}

	private boolean isDBLog(Object log) {

		boolean result = false;
		if (log instanceof LogOperation) {
			result = true;
		} else if (log instanceof LogPerformance) {
			result = true;
		}

		return result;
	}

	private boolean isFileLog(Object log) {

		boolean result = true;
		if (log instanceof LogOperation) {
			result = false;
		} else if (log instanceof LogPerformance) {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#logToDB(java.lang.Object)
	 */
	@Transactional
	private void logToDB(Object log) {
		try {
			if (log instanceof LogOperation) {
				logDao.saveOperation((LogOperation) log);
			} else if (log instanceof LogPerformance) {
				logDao.savePerformace((LogPerformance) log);
			}
		} catch (SQLException e) {
			throw new LogSysException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mikeheke.tadpole.frm.logging.service.LogService#logToFile(java.lang.Object)
	 */
	private void logToFile(Object log, Object[] params, LogLevel level) {

		if (null == level) {
			return;
		}
		if (level.ordinal() == LogLevel.DEBUG.ordinal()) {
			getLogger().debug(log.toString(), params);
		} else if (level.ordinal() == LogLevel.INFO.ordinal()) {
			getLogger().info(log.toString(), params);
		} else if (level.ordinal() == LogLevel.WARN.ordinal()) {
			getLogger().warn(log.toString(), params);
		} else if (level.ordinal() == LogLevel.ERROR.ordinal()) {
			getLogger().error(log.toString(), params);
		}
	}

	private List<Application> getApplications(Application application) {

		if (application == null && this.applicationCode != null) {
			application = new Application();
			application.setApplicationCode(this.applicationCode);
		}
		// Frankie 20140403: fixed the NullPointerException
		else if (application == null && this.applicationCode == null){
			application = new Application();
			application.setApplicationCode("00");
		}

		return queryList(application);
	}

	@Override
	public ReturnMessage<LogVo> query(Object obj) {

		ReturnMessage<LogVo> returnMessage = new ReturnMessage<LogVo>();

		if (null == obj) {
			return returnMessage;
		}
		List<Application> applications = getApplications((Application) obj);
		List<LogVo> logVos = this.getLogList(applications);

		returnMessage.setReturnObjects(logVos);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);

		return returnMessage;
	}

	@Override
	public ReturnMessage<LogVo> update(Object obj) {

		ReturnMessage<LogVo> returnMessage = new ReturnMessage<LogVo>();
		LogVo logVo = (LogVo) obj;

		try {

			XMLDomHelper xmlHelper = new XMLDomHelper();
			Document document = xmlHelper.createDocument(logVo.getFixWay());
			NodeList nodeList = getLogNodeList(xmlHelper, document);
			Node logPathNode = getLogNode(xmlHelper, nodeList, LOG_PATH);
			logPathNode.getAttributes().getNamedItem(LogConstant.VALUE)
					.setNodeValue(logVo.getLogFileAdr());
			Node logLevelNode = getLogNode(xmlHelper, nodeList, LOG_LEVEL);
			logLevelNode.getAttributes().getNamedItem(LogConstant.VALUE)
					.setNodeValue(logVo.getLogLevel());
			xmlHelper.saveAsDocument(document, logVo.getFixWay());

			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			final String msg = "保存成功";
			returnMessage.setReturnMsg(msg);
			returnMessage.setReturnObject(logVo);

		} catch (Exception e) {
			throw new LogSysException(e);
		}

		return returnMessage;
	}

	private String getLogFilePath(Application application) {

		return application.getFixWay();
	}

	private List<LogVo> getLogList(List<Application> applications) {

		List<LogVo> logVos = new ArrayList<LogVo>();
		try {
			XMLDomHelper xmlHelper = new XMLDomHelper();
			for (Application application : applications) {
				String fixWay = getLogFilePath(application);
				Document document = xmlHelper.createDocument(fixWay);
				NodeList nodeList = getLogNodeList(xmlHelper, document);
				Node logPathNode = getLogNode(xmlHelper, nodeList, LOG_PATH);
				String logPath = logPathNode.getAttributes()
						.getNamedItem(LogConstant.VALUE).getNodeValue();
				Node logLevelNode = getLogNode(xmlHelper, nodeList, LOG_LEVEL);
				String logLevel = logLevelNode.getAttributes()
						.getNamedItem(LogConstant.VALUE).getNodeValue();

				LogVo logVo = new LogVo();
				logVo.setApplicationId((application
						.getApplicationId()));
				logVo.setApplicationName(application.getApplicationName());
				logVo.setFixWay(fixWay);
				logVo.setLogFileAdr(logPath);
				logVo.setLogLevel(logLevel);

				logVos.add(logVo);
			}
		} catch (Exception e) {
			throw new LogSysException(e);
		}

		return logVos;
	}

	private NodeList getLogNodeList(XMLDomHelper xmlHelper, Document document)
			throws SAXException, IOException {

		Element root = xmlHelper.getRootElement(document);
		NodeList nodeList = xmlHelper.getNodeListByName(root,
				LogConstant.PROPERTY);

		return nodeList;
	}

	private Node getLogNode(XMLDomHelper xmlHelper, NodeList nodeList,
			String nameAttrValue) {
		return xmlHelper.getNodeByNameAttrValue(nodeList, nameAttrValue);
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public void setLogger(Class cla) {
		this.setCla(cla);
	}

	private Logger getLogger() {

		initLogger();

		Logger logger = loggerFactory.getLogger(LOGGER_NAME);

		return logger;
	}

	private void initLogger() {

		String logbackCfg = getLoggerFile();
		LoggerContext loggerContext = getLoggerContext();

		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(loggerContext);

		if (!DataValidater.isStrEmpty(logbackCfg)
				&& new File(logbackCfg).exists()) {
			try {
				configurator.doConfigure(logbackCfg);

			} catch (JoranException e) {
				throw new LogSysException(e);
			}
		}

	}

	private String getLoggerFile() {
		String loggerFile = LogConstant.EMPTY_STR;
		Application application = SysInfoBean.getSysInfoBean().getApplication();
		if (null == application) {
			List<Application> list = this.getApplications(null);

			if (list != null && !list.isEmpty()) {
				application = list.get(0);
			}
		}
		if (null != application) {
			loggerFile = application.getFixWay();
		}
		return loggerFile;
		// return "D:\\logconfig\\logback.xml";
	}

	private LoggerContext getLoggerContext() {
		if (null == loggerContext) {
			loggerContext = (LoggerContext) loggerFactory;
		} else {
			loggerContext.reset();
		}

		return loggerContext;
	}

	public void setCla(Class cla) {
		this.cla = cla;
	}

	@Override
	public void setApplicationCode(String applicationCode) {
		// TODO Auto-generated method stub
		this.applicationCode = applicationCode;
	}

}