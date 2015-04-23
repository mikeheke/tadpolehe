jee应用开发框架系统

struts2 + spring3 + hibernate3.6

1. 
src/resources/config/jpa/persistence.xml
<jar-file>WEB-INF/lib/afw.jar</jar-file>

2.
CharacterEncodingFilter
			request.setCharacterEncoding(ENCODING);
			response.setCharacterEncoding(ENCODING);

3.
风格4 支持firefox

4. 
tomcat context.xml:
db:
<!-- small app qa db -->
<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbcamway" password="amway" type="javax.sql.DataSource" url="jdbc:oracle:thin:@10.143.170.73:1521:sappqa" username="amway"/>
<!-- mikevm -->
<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbcamway" password="amway" type="javax.sql.DataSource" url="jdbc:oracle:thin:@127.0.0.1:21521:XE" username="amway"/>


5.
ajax 文件框　需要优化

6. 通用查询
showAction.action?queryCode=afw_role
../sysManage/showAction.action?queryCode=afw_role

7.
dataBaseContext.xml
<property name="persistenceXmlLocation" value="classpath:resources/config/jpa/persistence.xml" />


8.
<script type="text/javascript" src="${_sysApplication_.goToUrl}/common/js/jquery-1.4.4.min.js"></script>
	public String getGoToUrl(){
		//return AfwConstant.LINK_HTTP + deployServer + ":" + port + "/" + context;
		StringBuffer goToUrl = new StringBuffer();
		goToUrl.append(ContextFactory.getHttpDomain());
		goToUrl.append(AppConstant.UNIX_SEP);
		goToUrl.append(context);
		
		return goToUrl.toString();
	}
${_sysApplication_.goToUrl}   sysManage
${_application_.layout }      currentSys

		Application application = sysInfo.getApplication();
		Application sysApplication = sysInfo.getSysApplication();

9.　页面框架布局
mnu/layout/...
ex:
index.jsp ---> tree.jsp ---> ...

10. logger.info("login"); 之后
log模块才生效　tx

11.
功能菜单--连接URL:   =========关系=======  通用查询: 按钮－－提交Url：
注意：对应关系

12. important
persistence.xml
<class>mikeheke.kgem.entity.Knowledge</class>

13. web 范围：
ApplicationContext/ServletContext
Session
Request
PageContext

14. 
com.amway.frm.afw.web.filter.AuthenticationFilter

15. common query
<input type='checkbox' name='ids' value='$0'/>
<input type='checkbox' name='roleVo.roleIds' value='$0'/>
<input type='checkbox' name='knowledgeVo.knowledgeIds' value='$0'/>

delete 无窗口
checkDelete(1)

16. Entity---注解
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

17. 
struts2==> extends="default"
<package name="kgem" namespace="/" extends="default">

18. s2sh ajax json 注意事项：
+
	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
这个错误是因为我的Service层在注入Action的时候让自动生成了Get、Set方法，问题就出在Get方法上。返回的JSON对象也将这个Get方法当作类实体的属性来返回。

就会报错：org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: org.apache.struts2.json.JSONException: java.lang.reflect.InvocationTargetException
 org.apache.struts2.json.JSONWriter.bean(JSONWriter.java:243)
 org.apache.struts2.json.JSONWriter.process(JSONWriter.java:165)
 org.apache.struts2.json.JSONWriter.value(JSONWriter.java:131)
 org.apache.struts2.json.JSONWriter.write(JSONWriter.java:99)
 org.apache.struts2.json.JSONUtil.serialize(JSONUtil.java:112)
 org.apache.struts2.json.JSONResult.execute(JSONResult.java:198)
 com.opensymphony.xwork2.DefaultActionInvocation.executeResult(DefaultActionInvocation.java:362)
 com.opensymphony.xwork2.DefaultActionInvocation.invoke(DefaultActionInvocation.java:266)
 com.opensymphony.xwork2.interceptor.Defaul…
--------------------------------------------------------------------------------------------------------------------------------------------------------------

 最后将这个Get方法去掉，OK运行正常。

  折腾了我半天 记录下
  
19. view detail:
knowledgeAction!initModify.action?knowledgeVo.knowledgeIds=$0
  
  
20. auto generate id:
	//自动生成ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "KNOWLEDGE_ID")
    private String knowledgeId;

	//主键
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HSSQ_LOG_PERFORMANCE_OR")
	@SequenceGenerator(name="HSSQ_LOG_PERFORMANCE_OR", sequenceName="HSSQ_LOG_PERFORMANCE", 
			initialValue=1, allocationSize=1)
	@Column(name="LOG_PERFORMANCE_ID")
	private Long logPerformanceId;

  	