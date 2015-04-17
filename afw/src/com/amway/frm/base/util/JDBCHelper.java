package com.amway.frm.base.util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Help developer to access database
 * @author huangweijin
 */
public class JDBCHelper {
	
	
	public static final Integer DEFAULT_BATCHSIZE=1000;
	
	
	/**
	 * Null sql type
	 */
	public static final NullSQLType NULL_CHAR = new NullSQLType(Types.CHAR);
	public static final NullSQLType NULL_DATE = new NullSQLType(Types.DATE);
	public static final NullSQLType NULL_DECIMAL = new NullSQLType(Types.DECIMAL);
	public static final NullSQLType NULL_DOUBLE = new NullSQLType(Types.DOUBLE);
	public static final NullSQLType NULL_FLOAT = new NullSQLType(Types.FLOAT);
	public static final NullSQLType NULL_INTEGER = new NullSQLType(Types.INTEGER);
	public static final NullSQLType NULL_LONGVARCHAR = new NullSQLType(Types.LONGVARCHAR);
	public static final NullSQLType NULL_TIME = new NullSQLType(Types.TIME);
	public static final NullSQLType NULL_TIMESTAMP = new NullSQLType(Types.TIMESTAMP);
	public static final NullSQLType NULL_VARCHAR = new NullSQLType(Types.VARCHAR);

	/**
	 * use driver,database,login id,password to new a connection.
	 */
	private static final String JNDI_PRE = "java:comp/env/";
	private DataSource ds = null;
	private Connection con = null; // database connection
	private ResultSet rs; // result set
	private PreparedStatement stmt = null; // statement
	private boolean rsScroll = false;

	/**
	 * Constructor : Use driver name, database url, login id, password to new a
	 * connection.
	 * 
	 * @param driverName
	 *            - The driver name of the database server e.g.
	 *            "com.sybase.jdbc2.jdbc.SybDriver"
	 * @param serverName
	 *            - The url to the dataabse server e.g.
	 *            "jdbc:sybase:Tds:123.123.123.123:5000"
	 * @param dbName
	 *            - The database name in the server
	 * @param userId
	 *            - The user Id to login the database server
	 * @param password
	 *            - The password of the user
	 * 
	 * @exception SQLException
	 *                - Database error occurs
	 * @exception ClassNotFoundException
	 *                - The driver class is not found
	 */
	/*
	public JDBCHelper(String driverName, String serverName, String dbName,
			String userId, String password) throws SQLException,
			ClassNotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("driverName: " + driverName);
			logger.debug("serverName: " + serverName);
			logger.debug("dbName: " + dbName);
			logger.debug("userId: " + userId);
			logger.debug("password: " + password);
		}

		Class.forName(driverName);
		con = DriverManager.getConnection(serverName + "/" + dbName, userId,
				password);
	}
	*/

	/**
	 * Constructor : Use driver name, database url, login id, password to new a
	 * connection.
	 * 
	 * @param driverName
	 *            - The driver name of the database server e.g.
	 *            "com.sybase.jdbc2.jdbc.SybDriver"
	 * @param aURL
	 *            - The url to the database server e.g.
	 *            "jdbc:sybase:Tds:123.123.123.123:5000/sa"
	 * @param userId
	 *            - The user Id to login the database server
	 * @param password
	 *            - The password of the user
	 * 
	 * @exception SQLException
	 *                - Database error occurs
	 * @exception ClassNotFoundException
	 *                - The driver class is not found
	 */
	/*
	public JDBCHelper(String driverName, String aURL, String userId,
			String password) throws SQLException, ClassNotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("driverName: " + driverName);
			logger.debug("URL: " + aURL);
			logger.debug("userId: " + userId);
			logger.debug("password: " + password);
		}

		Class.forName(driverName);
		this.con = DriverManager.getConnection(aURL, userId, password);
	}
	*/
	
	/**
	 * Use the given data source name aDataSourceName to look up a datbase and
	 * get a connection.
	 * @param aDataSourceName
	 */
	public JDBCHelper(String aDataSourceName) throws NamingException,
			SQLException {
		InitialContext ctx = null;
		try {
			// Logger.log(false,
			// "Debug log : get data source with default context.");
			ctx = new InitialContext();
			ds = lookup(ctx, aDataSourceName);

			this.con = getConnection(ds);
		} catch (NamingException ne) {
			throw ne;
		} catch (SQLException se) {
			throw se;
		} finally {
			close(ctx);
		}

	}
	
	private static void close(InitialContext ctx) throws NamingException{
		ctx.close();
	}
	
	private static DataSource lookup(InitialContext ctx, String aDataSourceName)
			throws NamingException {
		return (DataSource) ctx.lookup(JNDI_PRE + aDataSourceName);
	}

	/**
	 * Use the given data source aDataSource to get a connection.
	 * @param aDataSource
	 */
	public JDBCHelper(DataSource aDataSource) throws SQLException {
		this.con = getConnection(aDataSource);
	}
	
	private static Connection getConnection(DataSource aDataSource) throws SQLException{
		return aDataSource.getConnection();
	}

	/**
	 * Get the value of a given column in the current row of the result set. The
	 * column must be string type. The value is trimmed. Empty string is
	 * returned if the value is null.
	 * @param columnName
	 * @return String
	 */
	public String getItemValue(String columnName) throws SQLException {
		String value = this.rs.getString(columnName);
		value = (value == null ? AppConstant.EMPTY_STR : value.trim()); // for handling null value
		// in the table
		return value;
	}
	
	/**
	 * Get the value of a given column in the current row of the result set. The
	 * column must be string type.
	 * @param columnName
	 * @return String
	 */
	public String getItemValueNullable(String columnName) throws SQLException {
		String value = this.rs.getString(columnName);
		return value;
	}
	
	/**
	 * Get the value of a given column in the current row of the result set. The
	 * column must be clob type.
	 * @param columnName
	 * @return String
	 */
	public String getItemXMLValue(String columnName) throws SQLException {
		
		Object value =this.rs.getObject(columnName);
		
		String strValue=null;
		
		try {
			Method method=value.getClass().getDeclaredMethod("toBytes");
			
			Object o=method.invoke(value);
			
			String s=new String((byte[])o,"Unicode");
			
			strValue=s.substring(s.indexOf("<",s.indexOf("<")+1));		
			
		
		} catch (Exception e) {
			strValue=null;
		} 
		
		
		if(value!=null){
			return strValue;
		}
		else{
			return null;
		}
		
	}

	/**
	 * Check whether the value of given column is null or not. The column must
	 * be string type.
	 * @param columnName
	 * @return boolean
	 */
	public boolean itemIsNull(String columnName) throws SQLException {
		String value = this.rs.getString(columnName);
		boolean result = false;
		if (value == null) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

	/**
	 * Get the value of a given column index in the current row of the result
	 * set.
	 * @param index
	 * @return String - The value is NOT trimmed, and null if the value in
	 *         database if sql null.
	 */
	public String getItemTrueValue(int index) throws SQLException {
		String value = this.rs.getString(index);
		return value;
	}
	
	public Object getItemObjTrueValue(int index) throws SQLException {
		Object value = this.rs.getObject(index);
		return value;
	}

	// public String getXmlColumnName(String columnName) throws SQLException{
	// // OracleResultSet orset = (OracleResultSet)this.rs;
	// this.rs.getObject(columnName);
	// // XMLType poxml = XMLType.createXML(orset.getOPAQUE(columnName));
	// return "";
	// }

	/**
	 * Get the value of a given column (String Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return String - The value is NOT trimmed, and null if the value in
	 *         database if sql null.
	 */
	public String getItemTrueValue(String columnName) throws SQLException {
		String value = this.rs.getString(columnName);
		return value;
	}

	public Blob getItemBlobValue(String columnName) throws SQLException {
		Blob value = this.rs.getBlob(columnName);

		return value;
	}

	/**
	 * Get the value of a given column (Short Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return short
	 */
	public short getItemShortValue(String columnName) throws SQLException {
		return rs.getShort(columnName);
	}

	/**
	 * Get the value of a given column (int Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return int
	 */
	public int getItemIntValue(String columnName) throws SQLException {
		return rs.getInt(columnName);
	}

	/**
	 * Get the value of a given column (Integer Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return Integer
	 */
	public Integer getItemIntegerValue(String columnName) throws SQLException,
			NumberFormatException {
		Integer integer = null;
		String value = this.rs.getString(columnName);
		value = (value == null ? AppConstant.EMPTY_STR : value.trim());

		if (value != null && !AppConstant.EMPTY_STR.equals(value)) {
			integer = new Integer(value);
		}

		return integer;
	}

	/**
	 * Get the value of a given column (Long Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return long
	 */
	public long getItemLongValue(String columnName) throws SQLException {
		return rs.getLong(columnName);
	}
	
	/**
	 * Get the value of a given column (Long Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return long
	 */
	public Long getItemLongValueNullable(String columnName) throws SQLException {
		BigDecimal b=rs.getBigDecimal(columnName);
		if(b==null){
			return null;
		}
		else{
			return b.longValue();
		}
		
	}

	/**
	 * Get the value of a given column (Float Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return float
	 */
	public float getItemFloatValue(String columnName) throws SQLException {
		return rs.getFloat(columnName);
	}

	/**
	 * Get the value of a given column (Double Type) in the current row of the
	 * result set.
	 * @param columnName
	 * @return double
	 */
	public double getItemDoubleValue(String columnName) throws SQLException {
		return rs.getDouble(columnName);
	}

	/**
	 * Get the value of a given column (BigDecimal Type) in the current row of
	 * the result set.
	 * @param columnName
	 * @return BigDecimal
	 */
	public BigDecimal getItemBigDecimalValue(String columnName)
			throws SQLException {
		return rs.getBigDecimal(columnName);
	}

	/**
	 * Get the value of a given column (java.sql.Date Type) in the current row
	 * of the result set.
	 * @param columnName
	 * @return Date
	 */
	public java.sql.Date getItemDateValue(String columnName)
			throws SQLException {
		java.sql.Date date = rs.getDate(columnName);
		return date;
	}
	
	/**
	 * Get the value of a given column (java.sql.Date Type) in the current row
	 * of the result set.
	 * @param columnName
	 * @return Date
	 */
	public java.sql.Timestamp getItemTimeStampValue(String columnName)
			throws SQLException {
		java.sql.Timestamp date = rs.getTimestamp(columnName);
		
		return date;
	}

	/**
	 * Get the value of a given column (java.sql.Time Type) in the current row
	 * of the result set.
	 * @param columnName
	 * @return Time
	 */
	public java.sql.Time getItemTimeValue(String columnName)
			throws SQLException {
		java.sql.Time time = rs.getTime(columnName);
		return time;
	}

	/**
	 * Get the value of a given column (java.sql.Timestamp Type) in the current
	 * row of the result set.
	 * @param columnName
	 * @return Timestamp
	 */
	public Timestamp getItemTimestampValue(String columnName)
			throws SQLException {
		return rs.getTimestamp(columnName);
	}

	/**
	 * Get the value of a given column (java.util.Date Type) in the current row
	 * of the result set.
	 * @param columnName
	 * @return Date
	 */
	public java.util.Date getItemDateTimeValue(String columnName)
			throws SQLException {
		java.util.Date utilDate = null;
		java.sql.Timestamp utilTimestamp = getItemTimestampValue(columnName);

		final Integer T = 1000000;
		if (utilTimestamp != null){
			utilDate = new java.util.Date(utilTimestamp.getTime()
					+ utilTimestamp.getNanos() / T);
		}

		return utilDate;
	}
	
	public java.util.Date getItemDateTimeValue(int index)
			throws SQLException {
		java.util.Date utilDate = null;
		java.sql.Timestamp utilTimestamp = rs.getTimestamp(index);

		final Integer T = 1000000;
		if (utilTimestamp != null){
			utilDate = new java.util.Date(utilTimestamp.getTime()
					+ utilTimestamp.getNanos() / T);
		}

		return utilDate;
	}

	/**
	 * Get the value of a given column (java.sql.Time Type) in the current row
	 * of the result set.
	 * @param columnName
	 * @return byte[]
	 */
	public byte[] getItemBytesValue(String columnName) throws SQLException {
		return rs.getBytes(columnName);
	}

	/**
	 * Get the value of a given column (java.io.InputStream Type) in the current
	 * row of the result set.
	 * @param columnName
	 * @return InputStream
	 */
	public InputStream getItemInputStreamValue(String columnName)
			throws SQLException {
		return rs.getAsciiStream(columnName);
	}

	/**
	 * Run the SQL select statment and make the result set ready for developer
	 * to call getItemXXXXValue() to get value.
	 * 
	 * @param sql
	 *            the SQL query string to execute
	 * 
	 * @return true if record exists. Developer should call getNextDocument() to
	 *         fetch next record. Only one result set is expected
	 */
	public boolean getFirstDocument(String sql)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		return getFirstDocument(sql, null);
	}

	/**
	 * Run the SQL select statment and make the result set ready for developer
	 * to call getItemXXXXValue() to get value.
	 * 
	 * @param sql
	 *            the SQL query string to execute
	 * @param pStmtValues
	 *            the values(passed by sequence) to enter into the prepared
	 *            statement. If there are no values, use <code>null</code>
	 * 
	 *            e.g. Assumes the data type for COLUMN_A is VARCHAR, for
	 *            COLUMN_B is NUMBER. sql ="select COLUMN_A, COLUMN_B, COLUMN_C from TABLE where COLUMN_A = ? and COLUMN_B = ?"
	 *            ; DbHelper.getFirstDocument(sql, new Object[]{new
	 *            String(COLUMN_A_VALUE), new Integer(COLUMN_B_VALUE)});
	 * 
	 * @return true if record exists. Developer should call getNextDocument() to
	 *         fetch next record. Only one result set is expected
	 */

	public boolean getFirstDocument(String sql, Object[] pStmtValues)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		boolean result = false;
		retrieve(sql, pStmtValues);
		result = getNextDocument();

		return result;
	}

	public boolean getFirstDocument(String sql, Object[] pStmtValues, boolean isScroll)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		this.rsScroll = isScroll;

		return getFirstDocument(sql, pStmtValues);
	}

	/**
	 * Fetch next record and make the result set ready for developer to call
	 * getItemXXXXValue() to get value. Return true if next record exists.
	 * Developer should call getFirstDocument() firstly before calling this
	 * function.
	 * @param void
	 * @return boolean
	 */

	public boolean getNextDocument() throws SQLException {
		boolean withDoc = false;

		if (this.rs == null) {
			return withDoc;
		}
		withDoc = rs.next();
		return withDoc;
	}

	/**
	 * Run stored procedure or update sql statement. The method is not used now,
	 * it may need to remove later.
	 * 
	 * @param sql
	 *            the SQL query or update string to execute
	 * 
	 * @return true if the next result is a ResultSet.
	 * @return false if it is an update count or there are no more results.
	 */

	public boolean runStoreProc(String sql) throws IllegalArgumentException,
			IllegalStateException, SQLException {
		boolean result = runStoreProc(sql, null);
		return result;
	}

	/**
	 * Run stored procedure or update sql statement. The method is not used now,
	 * it may need to remove later.
	 * 
	 * @param sql
	 *            the SQL query or update string to execute
	 * @param pStmtValues
	 *            the values(passed by sequence) to enter into the prepared
	 *            statement. If there are no values, use <code>null</code>
	 * 
	 * @return true if the next result is a ResultSet.
	 * @return false if it is an update count or there are no more results.
	 */
	public boolean runStoreProc(String sql, Object[] pStmtValues)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		boolean result = false;

		stmt = this.con.prepareStatement(sql);
		if (pStmtValues != null) {
			buildStatement(stmt, pStmtValues);
		}
		result = stmt.execute();

		return result;
	}

	/**
	 * Executes an SQL INSERT, UPDATE or DELETE statement. In addition, SQL
	 * statements that return nothing, such as SQL DDL statements, can be
	 * executed.
	 * 
	 * @param sql
	 *            a SQL INSERT, UPDATE or DELETE statement or a SQL statement
	 *            that returns nothing
	 * 
	 * @returns either the row count for INSERT, UPDATE or DELETE or 0 for SQL
	 *          statements that return nothing
	 */

	public int executeUpdate(String sql) throws IllegalArgumentException,
			IllegalStateException, SQLException {
		int result = executeUpdate(sql, null);
		return result;
	}

	/**
	 * Executes an SQL INSERT, UPDATE or DELETE statement. In addition, SQL
	 * statements that return nothing, such as SQL DDL statements, can be
	 * executed.
	 * 
	 * @param sql
	 *            a SQL INSERT, UPDATE or DELETE statement or a SQL statement
	 *            that returns nothing
	 * @param pStmtValues
	 *            the values(passed by sequence) to enter into the prepared
	 *            statement. If there are no values, use <code>null</code>
	 * 
	 * @returns either the row count for INSERT, UPDATE or DELETE or 0 for SQL
	 *          statements that return nothing
	 */

	public int executeUpdate(String sql, Object[] pStmtValues)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		int result = -1;

		stmt = this.con.prepareStatement(sql);
		if (pStmtValues != null) {
			buildStatement(stmt, pStmtValues);
		}
		result = stmt.executeUpdate();

		return result;
	}
	
	
	/**
	 *  default batch size =1000
	 * 
	 *  @param sql
	 *  @param listStmtValues
	 *  @return
	 *  @throws IllegalArgumentException
	 *  @throws IllegalStateException
	 *  @throws SQLException
	 *  @see
	 *  @since
	 */
	public int executeBatchUpdate(String sql, List<Object[]> listStmtValues) 
	throws IllegalArgumentException, IllegalStateException,
	SQLException{
		return executeBatchUpdate(sql,listStmtValues,null);
	}
	
	/**
	 * default batch size =1000
	 * if batch size <1  set default value(1000)
	 *  @param sql
	 *  @param listStmtValues
	 *  @param batchSize
	 *  @return
	 *  @throws IllegalArgumentException
	 *  @throws IllegalStateException
	 *  @throws SQLException
	 *  @see
	 *  @since
	 */
	public int executeBatchUpdate(String sql, List<Object[]> listStmtValues,Integer batchSize) 
	throws IllegalArgumentException, IllegalStateException,
	SQLException{
		if(batchSize == null || batchSize.intValue()<1){
			batchSize=DEFAULT_BATCHSIZE;
		}
		int resultLength = 0;		
		int count = 0;		
		stmt = this.con.prepareStatement(sql);
		for(Object[] pStmtValues:listStmtValues){
			if (pStmtValues != null) {
				buildStatement(stmt, pStmtValues);
			}
			stmt.addBatch();
		    if(++count % batchSize == 0) {
		    	int [] intArray=stmt.executeBatch();
		    	if(intArray != null){
		    		resultLength=resultLength+intArray.length;
		    	}
		    }
		}
		int [] intArray=stmt.executeBatch();    
		if(intArray != null){
			resultLength=resultLength+intArray.length;
		}		
		return resultLength;
	}

	/**
	 * Run stored procedure or update sql statement.
	 * 
	 * @param sql
	 *            the SQL query or update string to execute
	 * 
	 *            get the first resultset to local varible 'rs' Return true if
	 *            record is ready for developer to access
	 * @return boolean
	 */
	public boolean getFirstDocumentSP(String sql)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		boolean result = getFirstDocumentSP(sql, null);
		return result;
	}

	/**
	 * Run stored procedure or update sql statement.
	 * 
	 * @param sql
	 *            the SQL query or update string to execute
	 * @param pStmtValues
	 *            the values(passed by sequence) to enter into the prepared
	 *            statement. If there are no values, use <code>null</code>
	 * 
	 *            get the first resultset to local varible 'rs' Return true if
	 *            record is ready for developer to access
	 * @return boolean
	 */
	public boolean getFirstDocumentSP(String sql, Object[] pStmtValues)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		boolean result = false;

		stmt = this.con.prepareStatement(sql);
		if (pStmtValues != null) {
			buildStatement(stmt, pStmtValues);
		}
		stmt.execute();

		// get the first resultset
		while (true) {
			setResultSet(); // get next resultset
			if (isResultSet()){
				break;
			}
			if (!getMoreResults() && getUpdateCount() == -1){
				break;
			}
		}

		result = getNextDocument();

		return result;
	}

	/**
	 * Close the connection, ResultSet and PreparedStatement.
	 * 
	 */
	public void closeConnection() throws SQLException {
		// if(!this.getAutoCommit())
		// this.setAutoCommit(true);
		if (this.con != null && DataValidater.isFalse(this.con.isClosed())) {
			close();
			this.con.close();
		}

	}
/*
	public void closeConnection(boolean isClosed) throws SQLException {
		// if(!this.getAutoCommit())
		// this.setAutoCommit(true);
		if (this.con != null && con.isClosed() == false) {
			close();
			this.con.close();

		}
	}
*/
	/**
	 * Close the ResultSet and PreparedStatement.
	 */
	public void close() throws SQLException {
		if (this.rs != null) {
			this.rs.close();
			//rs = null;
		}
		if (this.stmt != null) {
			this.stmt.close();
			//this.stmt = null;
		}
	}

	/**
	 * Close the ResultSet and PreparedStatement and Connection.
	 */
	public void closeAll() throws SQLException {
		this.close();
		this.closeConnection();
	}

	/**
	 * Moves to a Statement's next ResultSet.
	 * 
	 * @return true if the next result is a ResultSet
	 * @return false if it is an update count or there are no more results
	 */
	public boolean getMoreResults() throws SQLException {
		return stmt.getMoreResults();
	}

	/**
	 * Check whether the ResultSet is null or not.
	 */
	public boolean isResultSet() {
		boolean result = false;
		if (this.rs != null){
			result = true;
		}
		return result;
	}

	/**
	 * Get the number of updated records.
	 */
	public int getUpdateCount() throws SQLException {
		return this.stmt.getUpdateCount();
	}

	/**
	 * Get the ResultSet.
	 */
	public void setResultSet() throws SQLException {
		if (stmt != null){
			this.rs = stmt.getResultSet();
		}
	}

	/**
	 * Get the number of columns in the ResultSet.
	 */
	public int getColumnCount() throws SQLException {
		ResultSetMetaData rsmd = this.rs.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();

		return numberOfColumns;
	}

	/**
	 * Get a column's type. The method is not used now, it may need to remove
	 * later.
	 * @param col
	 * @return int
	 */
	public int getColumnType(int col) throws SQLException {
		ResultSetMetaData rsmd = this.rs.getMetaData();
		int colType = rsmd.getColumnType(col);

		return colType;
	}

	/**
	 * Get a column's name.
	 * @param col
	 * @return String
	 */
	public String getColumnName(int col) throws SQLException {
		String colName = AppConstant.EMPTY_STR;
		ResultSetMetaData rsmd = this.rs.getMetaData();
		colName = rsmd.getColumnName(col);

		return colName;
	}

	/**
	 * Get the connection's auto-commit mode.
	 */
	public boolean getAutoCommit() throws SQLException {
		boolean result = false;
		if (this.con != null){
			result = this.con.getAutoCommit();
		}
		return result;
	}

	/**
	 * Set the connection's auto-commit mode.
	 * @param autoCommit
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		if (!autoCommit) {
			if (!this.con.getAutoCommit()) {
				try {
					this.con.commit();
				} catch (SQLException e) {
					con.rollback();
					throw e;
				} finally {
					this.setAutoCommit(true);
				}
			}

		}
		if (this.con != null) {
			this.con.setAutoCommit(autoCommit);
		}
	}

	/**
	 * Commit a transaction.
	 */
	public void commitTran() throws SQLException {
		if (this.con != null){
			this.con.commit();
		}
	}

	/**
	 * Rollback a transaction.
	 */
	public void rollbackTran() throws SQLException {
		if (this.con != null){
			this.con.rollback();
		}
	}

	/**
	 * Representing a null value in a prepared statement.
	 * @author lenovo
	 */
	public static class NullSQLType {

		// The expected sql type of the field
		private int type;

		/**
		 * Default constructor. Takes the expected sql type of the field that
		 * will be null.
		 * 
		 * @param fieldType
		 *            the expected field type in the db. Should be pulled from
		 *            java.sql.Types.
		 * @see java.sql.Types
		 */
		public NullSQLType(int fieldType) {
			type = fieldType;
		}

		/**
		 * Get the sql type of the field.
		 * 
		 * @return the sql type of the field
		 */
		public int getFieldType() {
			return type;
		}
	}

	/**
	 * Execute the passed SQL statement and return a DOM object holding all the
	 * query results
	 * 
	 * @param sql
	 *            - the SQL statement to execute
	 * @param aElementName
	 *            - a child node's name, its parent node is "Result"
	 * @param rtnNull
	 *            - indicate whether to return null or not if the query result
	 *            not found, true for yes
	 * @return a DOM object or null
	 */
	public Document getDOM(String sql, String aElementName, boolean rtnNull)
			throws ParserConfigurationException, DOMException, SQLException {
		Document document;
		document = getDOM(sql, null, aElementName, rtnNull);
		return document;
	}

	/**
	 * Execute the passed SQL statement and return a DOM object holding all the
	 * query results
	 * 
	 * @param sql
	 *            - the SQL statement to execute
	 * @param pStmtValues
	 *            - the values(passed by sequence) to enter into the prepared
	 *            statement. If there are no values, use <code>null</code>
	 * @param aElementName
	 *            - a child node's name, its parent node is "Result"
	 * @param rtnNull
	 *            - indicate whether to return null or not if the query result
	 *            not found, true for yes
	 * @return a DOM object or null
	 */
	public Document getDOM(String sql, Object[] pStmtValues,
			String aElementName, boolean rtnNull)
			throws ParserConfigurationException, DOMException, SQLException {
		Document document;
		int colCnt = 0;
		String colName = AppConstant.EMPTY_STR;
		Object colValueObject;
		String colValue = AppConstant.EMPTY_STR;
		boolean isResult;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();

		// Get ResultSet according to the passed SQL statement
		isResult = getFirstDocumentSP(sql, pStmtValues);

		// Set DOM to null if no ResultSet found and developer requires to
		// return null
		if (DataValidater.isFalse(isResult) && rtnNull) {
			return null;
		} else {
			// Create root element "Result"
			final String RESULT = "Result";
			Element root = (Element) document.createElement(RESULT);
			document.appendChild(root);

			if (isResult) {
				colCnt = getColumnCount();

				do {
					// Validate aElementName
					String aElementName2 = aElementName;
					if (aElementName2 != null) {
						aElementName2 = aElementName2.trim();
					}
					if (aElementName2 == null || AppConstant.EMPTY_STR.equals(aElementName2)) {
						final String ROW = "Row";
						aElementName2 = ROW;
					}

					// Create a child element using the passed element name as
					// TagName
					// if the ElementName is null or empty then use "Row" as
					// default
					Element resultItem = document.createElement(aElementName2);
					root.appendChild(resultItem);

					for (int i = 1; i <= colCnt; i++) {
						// Get Column Name
						colName = getColumnName(i);
						// Get Column value as an object
						colValueObject = this.rs.getObject(i);

						// Return a string column value
						if (colValueObject == null) {
							colValue = AppConstant.EMPTY_STR;
						} else {
							colValue = colValueObject.toString().trim();
						}

						// Create child node (column and value)
						Element colItem = (Element) document
								.createElement(colName);
						resultItem.appendChild(colItem);
						colItem.appendChild(document.createTextNode(colValue));
					}

				} while (this.rs.next());

			}
		}

		return document;

	}

	/**
	 * Check whether the connection is closed.
	 * 
	 * @return boolean - true if connection is closed or is not initilized false
	 *         if connection is active
	 * 
	 * @exception SQLException
	 */
	public boolean isConClosed() throws SQLException {
		boolean result = true;
		if (this.con != null){
			result = this.con.isClosed();
		}
		return result;
	}

	/**
	 * Excute select SQL statement. Returns one resultset
	 * 
	 * @param sql
	 *            the SQL query string to execute
	 * @param pStmtValues
	 *            the values(passed by sequence) to enter into the prepared
	 *            statement. If there are no values, use <code>null</code>
	 */
	private void retrieve(String sql, Object[] pStmtValues)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {
		if (rsScroll) {
			stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} else {
			stmt = con.prepareStatement(sql);
		}
		if (pStmtValues != null) {
			buildStatement(stmt, pStmtValues);
		}
		this.rs = stmt.executeQuery();
	}

	/**
	 * Build prepared statements using only a connection and an array of
	 * objects.
	 */
	private void buildStatement(PreparedStatement stmt, Object[] values)
			throws IllegalArgumentException, IllegalStateException,
			SQLException {

		// If we have a null value here, then bail.
		if (values == null || stmt == null) {
			final String msg = "Cannot pass a null value array into buildStatement().";
			throw new IllegalArgumentException(msg);
		}

		// Loop through each value, determine it's corresponding SQL type,
		// and stuff that value into the prepared statement.
		Object value = null;
		for (int i = 0; i < values.length; i++) {
			value = values[i];

			// Have to handle null values seperately
			if (value != null) {

				// If the object is our representation of a null value, then
				// handle it seperately
				if (value instanceof NullSQLType) {
					stmt.setNull(i + 1, ((NullSQLType) value).getFieldType());
				} else {
					if (byte[].class.equals(value.getClass())) {
						byte[] byteArray = (byte[]) value;
						stmt.setBytes(i + 1, byteArray);
					} else {

						if (Integer.class.equals(value.getClass())) {
							stmt.setInt(i + 1, ((Integer) value).intValue());
							continue;
						}
						if (Long.class.equals(value.getClass())) {
							stmt.setLong(i + 1, ((Long) value).longValue());
							continue;
						}

						stmt.setObject(i + 1, value);
					}
				}
			} else {
				//hwj modify 20110929
				// Can't do anything with a null value.
				//throw new IllegalStateException(
				//		"Can't use a null value in a prepared statement.");
				stmt.setObject(i+1, value);
			}
		}

	}

	/**
	 * Declare：拼接sql查询条件
	 * 
	 * @param params
	 *            key值为字段名，值就是字段对应的值
	 * @param pStmtValues
	 *            prepareStatement的值
	 * @return Stirng where a=? and b=?
	 */
	public String getWhereParam(Map params, List pStmtValues) {
		if (params == null || params.isEmpty()){
			return AppConstant.EMPTY_STR;
		}
		String strOb = null;
		StringBuffer whereParams = new StringBuffer(AppConstant.SQL_WHERE);
		whereParams.append(AppConstant.EMPTY_ONE_STR);
		Set keys = params.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object ob = params.get(key);
			if (ob == null){
				continue;
			}
			if (String.class.equals(ob.getClass())) {
				strOb = (String) ob;
				if (strOb.trim().length() == 0){
					continue;
				}
			}
			pStmtValues.add(ob);
			whereParams.append(key).append(AppConstant.EQUAL_SIGN)
				.append(AppConstant.QUS_SIGN).append(AppConstant.EMPTY_ONE_STR)
				.append(AppConstant.SQL_AND).append(AppConstant.EMPTY_ONE_STR);
		}
		if (whereParams.length() == 6){
			return AppConstant.EMPTY_STR;
		}
		whereParams.delete(whereParams.length() - 5, whereParams.length());
		
		return whereParams.toString();
	}

	/**
	 * Declare：拼接sql查询条件
	 * 
	 * @param params
	 *            key值为字段名，值就是字段对应的值
	 * @param pStmtValues
	 *            prepareStatement的值
	 * @param joinOperator
	 * @return String where a=? and b=?
	 */
	public String getWhereParamWithOperator(Map params,
			List pStmtValues, String joinOperator) {
		if (params == null || params.isEmpty()){
			return AppConstant.EMPTY_STR;
		}
		if (joinOperator == null
				|| !(AppConstant.SQL_OR.equals(joinOperator.trim().toLowerCase())
				|| AppConstant.SQL_AND.equals(joinOperator.trim().toLowerCase()))){
			return AppConstant.EMPTY_STR;
		}
		String strOb = null;
		StringBuffer whereParams = new StringBuffer(AppConstant.EMPTY_ONE_STR);
		whereParams.append(AppConstant.SQL_WHERE).append(AppConstant.EMPTY_ONE_STR);
		whereParams.append(AppConstant.LEFT_X_KUO).append(AppConstant.EMPTY_ONE_STR);
		Set keys = params.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object ob = params.get(key);
			if (ob == null){
				continue;
			}
			if (String.class.equals(ob.getClass())) {
				strOb = (String) ob;
				if (strOb.trim().length() == 0){
					continue;
				}
			}
			pStmtValues.add(ob);
			whereParams.append(key).append(AppConstant.EQUAL_SIGN).append(AppConstant.QUS_SIGN);
			whereParams.append(AppConstant.EMPTY_ONE_STR).append(joinOperator)
				.append(AppConstant.EMPTY_ONE_STR);
		}
		if (whereParams.length() == 6){
			return AppConstant.EMPTY_STR;
		}
		whereParams.delete(whereParams.length() - 5, whereParams.length());
		whereParams.append(AppConstant.EMPTY_ONE_STR).append(AppConstant.RIGHT_X_KUO)
			.append(AppConstant.EMPTY_ONE_STR);

		return whereParams.toString();
	}

	/**
	 * Declare：拼接sql查询条件
	 * 
	 * @param params
	 *            key值为字段名，值就是字段对应的值
	 * @param pStmtValues
	 *            prepareStatement的值
	 * @param joinOperator
	 * @return String where a like ? and b like ?
	 */
	public String getWhereParamUsedLikeCompare(Map params,
			List pStmtValues, String joinOperator) {
		
		return getWhereParamUsedLikeCompare(params, pStmtValues,
				AppConstant.SQL_WHERE, joinOperator);
	}
	
	public String getWhereParamUsedLikeCompare(Map params,
			List pStmtValues, String where, String joinOperatorIn) {
		
		if (params == null || params.isEmpty()){
			return AppConstant.EMPTY_STR;
		}
		String joinOperator = joinOperatorIn;
		if (joinOperator == null || AppConstant.EMPTY_STR.equals(joinOperator)){
			joinOperator = AppConstant.SQL_OR;
		}
	
		String whereParams = AppConstant.EMPTY_ONE_STR+where+AppConstant.EMPTY_ONE_STR
			+AppConstant.LEFT_X_KUO;
		Set<String> keys = params.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object obs = params.get(key);
			if (obs == null){
				continue;
			}
			if(obs instanceof Object[]){
				Object[] obArr = (Object[])obs;
				for(Object ob: obArr){
					whereParams = addObjectSqlParam(key, ob, pStmtValues,
							whereParams, joinOperator);
				}
			}else{
				whereParams = addObjectSqlParam(key, obs, pStmtValues,
						whereParams, joinOperator);
			}
		}
		if (whereParams.length() == 8){
			return AppConstant.EMPTY_STR;
		}
		if (whereParams.length() == 3){
			return AppConstant.EMPTY_STR;
		}
		whereParams = whereParams.substring(0, whereParams.length()
				- joinOperator.length() - 2);
		whereParams = whereParams+AppConstant.EMPTY_ONE_STR+AppConstant.RIGHT_X_KUO
			+AppConstant.EMPTY_ONE_STR;
		
		return whereParams;
	}
	
	private String addObjectSqlParam(Object key, Object value, 
			List pStmtValues, String whereParams, String joinOperator){
		
		String operator = AppConstant.EMPTY_ONE_STR+AppConstant.SQL_LIKE+AppConstant.EMPTY_ONE_STR;
		if (!String.class.equals(value.getClass())) {
			operator = AppConstant.EMPTY_ONE_STR+AppConstant.EQUAL_SIGN+AppConstant.EMPTY_ONE_STR;
		}
		pStmtValues.add(value);
		String whereParamsRet = whereParams + key + operator + AppConstant.QUS_SIGN
			+ AppConstant.EMPTY_ONE_STR + joinOperator + AppConstant.EMPTY_ONE_STR;
		
		return whereParamsRet;
	}

	public CallableStatement getCallabelStatement(String produceName)
			throws Exception {
		CallableStatement stmt = this.con.prepareCall(produceName);
		return stmt;
	}

	public Connection getConn() {
		return con;
	}

	public int getTotalRow() throws SQLException {
         int oldRow=this.rs.getRow();
		this.rs.last();
		int rowNum = this.rs.getRow();
        if (oldRow!=0){
        	this.rs.absolute(oldRow);
        }

		return rowNum;
	}

	public void moveToRowNum(int rowNum) throws SQLException {
		this.rs.absolute(rowNum);
	}

	public DataSource getDs() {
		return ds;
	}
	
}
