1. 
src/resources/config/jpa/persistence.xml
<jar-file>WEB-INF/lib/afw.jar</jar-file>

2.
CharacterEncodingFilter
			request.setCharacterEncoding(ENCODING);
			response.setCharacterEncoding(ENCODING);

3.
风格4 支持firefox

4. db:
	<!-- small app qa db -->
<Resource auth="Container" driverClassName="oracle.jdbc.OracleDriver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbcamway" password="amway" type="javax.sql.DataSource" url="jdbc:oracle:thin:@10.143.170.73:1521:sappqa" username="amway"/>

5.
ajax 文件框　需要优化

6. 通用查询
showAction.action?queryCode=afw_role

