/**
 * 
 */
package mikeheke.tadpole.frm.query.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.exception.exception.AmwayInfoException;
import mikeheke.tadpole.frm.logging.interceptor.Monitor;
import mikeheke.tadpole.frm.query.entity.From;
import mikeheke.tadpole.frm.query.entity.GroupBy;
import mikeheke.tadpole.frm.query.entity.OrderBy;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.entity.Select;
import mikeheke.tadpole.frm.query.entity.Where;
import mikeheke.tadpole.frm.query.exception.QuerySysException;
import mikeheke.tadpole.frm.query.service.ShowService;
import mikeheke.tadpole.frm.query.util.DataServiceUtil;
import mikeheke.tadpole.frm.query.util.JDBCUtil;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.vo.Column;
import mikeheke.tadpole.frm.query.vo.Parameter;
import mikeheke.tadpole.frm.query.vo.Row;
import mikeheke.tadpole.frm.query.vo.SQL;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Filter;
import org.extremecomponents.table.limit.FilterSet;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;

/**
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：展示Service实现类
 */
public class ShowImpl extends QueryImpl implements ShowService {
	
	private static final String $USER_ID = "$USER_ID";
	private static final String $USER_NUMBER = "$USER_NUMBER";
	private static final String $UNIT_CODE = "$UNIT_CODE";
	//add by hyc for query application
	private static final String $APPLICATION = "$APPLICATION";
	
	protected void addConSqlToSql(List<String> conSql, String key, String sep, 
			SQL sql) {
		if(conSql.size()>0){
			sql.addSql(key);
		}
		
		List<String> conSql2 = new ArrayList<String>();
		//去除 conSql集合中空值  20150501
		for (String str : conSql) {
			if (!StringUtils.isBlank(str)) {
				conSql2.add(str);
			}
		}
		
		
		//sql.addSql(StringUtils.join(conSql.toArray(), sep));
		sql.addSql(StringUtils.join(conSql2.toArray(), sep));
	}

	/* 
	 * 生成查询选择Select SQL
	 * 
	 * @param query 查询
	 * @return String 选择SQL
	 */
	protected void addSelectToSql(Query query, SQL sql) {
		
		List<String> selectSql = new ArrayList<String>();
		List<Select> selects = query.getSelects();
		for(Select select: selects){
			StringBuffer selectResult = new StringBuffer(select.getSelectResult());
			String fieldAlias = select.getFieldAlias();
			if(!DataValidater.isStrEmpty(fieldAlias)){
				selectResult.append(QueryConstant.EMPTY_ONE_STR).append(fieldAlias);
			}
			selectSql.add(selectResult.toString());
		}
		final String SELECT = " SELECT ";
		addConSqlToSql(selectSql, SELECT, QueryConstant.DOU_SIGN,  sql);
	}
	
	/* 
	 * 生成来源From SQL
	 * 
	 * @param query 查询
	 * @return String 来源SQL
	 */
	protected void addFromToSql(Query query, SQL sql) {
		
		List<String> fromSql = new ArrayList<String>();
		List<From> froms = query.getFroms();
		for(From from: froms){
			StringBuffer fromResult = new StringBuffer(from.getFromResult());
			String tableAlias = from.getTableAlias();
			if(!DataValidater.isStrEmpty(tableAlias)){
				fromResult.append(QueryConstant.EMPTY_ONE_STR).append(from.getTableAlias());
			}
			fromSql.add(fromResult.toString());
		}
		final String FROM = " FROM ";
		addConSqlToSql(fromSql, FROM, QueryConstant.DOU_SIGN,  sql);
		
	}
	
	/* 
	 * 生成排序OrderBy SQL
	 * 
	 * @param query 查询
	 * @return String 排序SQL
	 */
	protected void addOrderByToSql(Query query, Sort sort, SQL sql) {
		
		List<String> orderBySql = new ArrayList<String>();
		
		if(null != sort){
			addEcToOrderBySql(query, sort, orderBySql);
		}

		if(null != query){
			addDftToOrderBySql(query, orderBySql);
		}
		
		final String ORDER_BY = " ORDER BY ";
		addConSqlToSql(orderBySql, ORDER_BY, QueryConstant.DOU_SIGN,  sql);
	}
	
	/* 
	 * 生成EC排序OrderBy SQL
	 * 
	 * @param query 查询
	 * @param sort EC排序
	 * @return String EC排序SQL
	 * 
	 * @modify by Mike He
	 * @modify date 2013-1-18
	 */
	private void addEcToOrderBySql(Query query, Sort sort,
			List<String> orderBySql) {
		
		String colName = sort.getProperty();
		if(null == colName){
			return;
		}
		Select select = getSelectByColName(query, colName);
		//此处不需要使用select别名字段
		//String fieldAlias = select.getFieldAlias();
		String fieldAlias = null;
		if(!DataValidater.isStrEmpty(fieldAlias)){
			colName = fieldAlias;
		}else{
			colName = select.getSelectResult();
		}
		String orderBy = sort.getSortOrder();
		orderBySql.add(colName + QueryConstant.EMPTY_ONE_STR + orderBy);
	}
	
	/* 
	 * 生成默认排序OrderBy SQL
	 * 
	 * @param query 查询
	 * @return String 默认排序SQL
	 */
	private void addDftToOrderBySql(Query query, List<String> orderBySql) {
		
		List<OrderBy> orderBys = query.getOrderBys();
		for(OrderBy orderByObj: orderBys){
			
			StringBuffer tabName = new StringBuffer(orderByObj.getTableName());
			String fieldName = orderByObj.getFieldName();
			StringBuffer colName = tabName.append(QueryConstant.DOT_SIGN).append(fieldName);
			Integer orderBy = orderByObj.getOrderBy();
			String orderByStr = QueryConstant.SQL_ORDERBY_ASC ;
			if(null != orderBy
					&& QueryConstant.ORDER_BY_DESC.intValue() == orderBy.intValue()){
				orderByStr = QueryConstant.SQL_ORDERBY_DESC;
			}
			colName.append(QueryConstant.EMPTY_ONE_STR).append(orderByStr);
			orderBySql.add(colName.toString());
		}
	}
	
	/* 
	 * 生成分组GroupBy SQL
	 * 
	 * @param query 查询
	 * @return String 分组SQL
	 */
	protected void addGroupByToSql(Query query, SQL sql) {
		
		List<String> groupBySql = new ArrayList<String>();
		
		List<GroupBy> groupBys = query.getGroupBys();
		for(GroupBy groupBy: groupBys){
			StringBuffer tabName = new StringBuffer(groupBy.getTableName());
			String fieldName = groupBy.getFieldName();
			String colName = tabName.append(QueryConstant.DOT_SIGN).append(fieldName).toString();
			groupBySql.add(colName);
		}
		
		final String GROUP_BY = " GROUP BY ";
		addConSqlToSql(groupBySql, GROUP_BY, QueryConstant.DOU_SIGN,  sql);
	}

	/* 
	 * 生成条件Where SQL
	 * 
	 * @param query 查询
	 * @return String 条件SQL
	 */
	protected void addWhereToSql(Query query, Limit limit, 
			Map<String, String[]> parNamesValues, SQL sql) {
		
		List<String> whereSql = new ArrayList<String>();
		
		addCfgToWhereSql(query, parNamesValues, whereSql, sql.getParamters());

		addEcToWhereSql(query, limit.getFilterSet(), whereSql, sql.getParamters());
		
		addGlToWhereSql(query, parNamesValues, whereSql, sql.getParamters());
		
		final String WHERE = " WHERE ";
		final String AND = " AND ";
		addConSqlToSql(whereSql, WHERE, AND,  sql);
	}

	private void addCfgToWhereSql(Query query, Map<String, String[]> parNamesValues,
			List<String> whereSql, List<Parameter> paramters){
		
		List<Where> wheres = query.getWheres();
		for(Where where: wheres){
			where.setGlobalWhere(query.getGlobalWhere());
			if(isGlobalWhere(where)){
				continue;
			}
			if(null == where.getIsUserIn()
					|| QueryConstant.WHERE_TYPE_IN.intValue() != where.getIsUserIn().intValue()){
				this.addComToWhereSql(where, parNamesValues, whereSql, paramters);
			}else{
				this.addInnerToWhereSql(where, whereSql, paramters);
			}
		}
	}
	
	/**
	 * @param query
	 * @param whereSql
	 * @param paramters
	 */
	private void addInnerToWhereSql(Where where, List<String> whereSql,
			List<Parameter> paramters) {
	
		addWhere(where, whereSql);
	}

	/* 
	 * 生成用户输入条件Where SQL
	 * 
	 * @param query 查询
	 * @param parNamesValues 输入条件
	 * @return String 输入条件SQL
	 */
	private void addComToWhereSql(Where where, Map<String, String[]> parNamesValues,
			List<String> whereSql, List<Parameter> paramters) {
		String[] parValuesArr = getParNamesValues(parNamesValues, where);
		addWhereResult(where, parValuesArr, whereSql);
		addWhereValue(where, parValuesArr, paramters);
		saveWhereConValues(where, parValuesArr);
		replaceWhereSql(whereSql,where);
		
	}
	
	/**
	 *  替换特殊变量
	 *  如下:
	 *  ${_seUserId_}
	 *  ${_seEmpNumber_}
	 *  ${_seApplicationId_}
	 *  ${_seApplicationCode_}
	 *  @param whereSql
	 *  @return
	 *  @see
	 *  @since
	 */
	private void replaceWhereSql(List<String> whereSqlList,Where where){
		if(whereSqlList == null || whereSqlList.isEmpty()){
			return ;
		}
		Integer isInnerLink=where.getIsInnerLink();
		if(isInnerLink !=  null && isInnerLink.intValue() ==0){
			List<String> replaceWhereSqlList=new ArrayList<String>();		
			UserProfile seUser = getSysInfo().getUserProfile();
			Application seApplication = getSysInfo().getApplication();
			if (seUser != null && seApplication != null) {			
				String searchArray[] = { AppConstant.SE_USER_ID,
						AppConstant.SE_EMP_NUMBER, AppConstant.SE_APPLICATION_ID,
						AppConstant.SE_APPLICATION_CODE };
				
				String replacementArray[] = { seUser.getUserProfileId().toString(),
						seUser.getEmpNumber(),
						seApplication.getApplicationId().toString(),
						seApplication.getApplicationCode() };
				
				for (String whereSql : whereSqlList) {
					whereSql = org.apache.commons.lang3.StringUtils.replaceEach(whereSql, searchArray,replacementArray);
					checkWhereSql(whereSql);
					replaceWhereSqlList.add(whereSql);
				}
				whereSqlList.clear();
				whereSqlList.addAll(replaceWhereSqlList);
			}
		}
			
	}
	
	/**
	 *  验查 whereSql里是否有 没替换的特殊变量
	 *  如有 抛错
	 *  @param whereSql
	 *  @see
	 *  @since
	 */
	private void checkWhereSql(String whereSql){
		String value=StringUtils.substringBetween(whereSql,AppConstant.DOLLOR_SIGN+AppConstant.DOLLOR_SIGN, AppConstant.RIGHT_D_KUO);
		if(StringUtils.isNotBlank(value)){
			final String CODE="0001";
			throw new AmwayInfoException();
		}
	}
	                
	private String[] getParNamesValues(Map<String, String[]> parNamesValues,
			Where where) {

		String[] parValues = null;

		if (QueryConstant.WHERE_TYPE_HIDDEN == where.getIsUserIn() || 
				!StringUtils.isBlank(where.getDefaultValue())) {//20150501
			UserProfile seUser = getSysInfo().getUserProfile();
			Application seApplication = getSysInfo().getApplication();

			if (AppConstant.SE_USER_ID.equals(where.getDefaultValue())) {
				parValues = new String[] { seUser.getUserProfileId().toString() };
			} else if (AppConstant.SE_EMP_NUMBER
					.equals(where.getDefaultValue())) {
				parValues = new String[] { seUser.getEmpNumber() };
			} else if (AppConstant.SE_APPLICATION_ID.equals(where
					.getDefaultValue())) {
				parValues = new String[] { seApplication.getApplicationId()
						.toString() };
			} else if (AppConstant.SE_APPLICATION_CODE.equals(where
					.getDefaultValue())) {
				parValues = new String[] { seApplication.getApplicationCode() };
			} else if (!StringUtils.isEmpty(where.getDefaultValue())) {
				
				/*
				 * 增加通过反射获取当前用户与当前应用对象里的属性值作为查询条件
				 * 黄波
				 * 2013-11-25
				 */
				parValues = getValues(where.getDefaultValue(), seUser, seApplication);
			}
		}

		if (null == parValues) {
			parValues = parNamesValues.get(where.getWhereCode());
			if (null == parValues) {
				parValues = new String[] { where.getDefaultValue() };
			}
		}

		return parValues;
	}
	
	private String[] getValues(String dv,UserProfile seUser,Application seApplication){
		
		Pattern p1 = Pattern.compile(AppConstant.CURRENT_USER_NAMES);
		Pattern p2 = Pattern.compile(AppConstant.CURRENT_APPLICATION_NAMES);
		
		if(p1.matcher(dv).matches()){
			
			 String field = dv.substring(dv.indexOf(".") + 1,dv.length() - 1);
			 Map<String, Method> ms = getBeanGetter(seUser.getClass());
			 Method m = ms.get(field);
			 
			if(m != null){
				Object val = getValue(seUser, m);
				if(val != null){
					return new String[]{String.valueOf(val)};
				}
			}
		}else if(p2.matcher(dv).matches()){
			
			 String field = dv.substring(dv.indexOf(".") + 1,dv.length() - 1);
			 Map<String, Method> ms = getBeanGetter(seApplication.getClass());
			 Method m = ms.get(field);
			 
			if(m != null){
				Object val = getValue(seApplication, m);
				if(val != null){
					return new String[]{String.valueOf(val)};
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 获取指定类的所有可读属性方法集合，键值为属性名称。
	 * 
	 * @param c
	 *            要获取属性的JavaBean类
	 * @return 类的所有可读属性方法集合
	 */
	public static Map<String, Method> getBeanGetter(Class<?> c) {
		LinkedHashMap<String, Method> map = new LinkedHashMap<String, Method>();
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(c);
		for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
			Method method = pd.getReadMethod();
			if (!"class".equals(name) && method != null) {
				map.put(name, method);
			}
		}
		return map;
	}
	
	private static Object getValue(Object o, Method m) {
		Object val = null;
		try {
			val = m.invoke(o, new Object[] {});
		} catch (Exception e) {
			throw new AmwayInfoException("调用 " + m.getName() + "出错:"
					+ e.getMessage());
		}
		return val;
	}
	
	
	
	private void saveWhereConValues(Where where, String[] parValuesArr){
		
		if(!DataValidater.isArrEmpty(parValuesArr)){
			where.setDefaultValue(parValuesArr[0]);
		}
		where.setDefaultValue2(parValuesArr);
	}
	
	private void addWhereResult(Where where, String[] values, 
			List<String> whereSql){
		
		Integer operator = where.getOperator();
		if(null != operator && operator.intValue() == QueryConstant.OPRT_BTA.intValue()){
			addBtaWhereResult(where, values, whereSql);
		}else if(null != operator && operator.intValue() == QueryConstant.OPRT_IN.intValue()){
			addInWhereResult(where, values, whereSql);
		}else{
			addComWhereResult(where, values, whereSql);
		}
	}
	
	private void addBtaWhereResult(Where where, String[] values, 
			List<String> whereSql){
		String value1 = (values.length > 0)?values[0]:null;
		String value2 = (values.length > 1)?values[1]:null;
		final String BETWEEN_AND = " BETWEEN ? AND ?";
		final String GE_PAR = " >=? ";
		final String L_PAR = " <? ";
		if(DataValidater.isFalse(DataValidater.isStrEmpty(value1))
				&& DataValidater.isFalse(DataValidater.isStrEmpty(value2))){
			whereSql.add(where.getWhereResult());
		}else if(DataValidater.isFalse(DataValidater.isStrEmpty(value1))
				&& DataValidater.isStrEmpty(value2)){
			whereSql.add(where.getWhereResult().toUpperCase().trim().replace(
					BETWEEN_AND, GE_PAR));
		}else if(DataValidater.isStrEmpty(value1)
				&& DataValidater.isFalse(DataValidater.isStrEmpty(value2))){
			whereSql.add(where.getWhereResult().toUpperCase().trim().replace(
					BETWEEN_AND, L_PAR));
		}
	}
	
	private void addInWhereResult(Where where, String[] values, 
			List<String> whereSql){
		StringBuffer whereResult = new StringBuffer(QueryConstant.EMPTY_ONE_STR);
		boolean isExitValue = false;
		for(String value: values){
			if(DataValidater.isStrEmptyOrNull(value)){
				continue;
			}
			isExitValue = true;
			whereResult.append(QueryConstant.QUS_SIGN).append(QueryConstant.DOU_SIGN);
		}
		if (isExitValue) {
			whereResult.delete(whereResult.length()-1, whereResult.length());
			whereSql.add(where.getWhereResult().replace(QueryConstant.XING_SIGN, whereResult.toString().trim()));
		}
	}
	
	private void addComWhereResult(Where where, String[] values, 
			List<String> whereSql){
		for(String value: values){
			if(DataValidater.isStrEmpty(value)){
				continue;
			}
			whereSql.add(where.getWhereResult());
		}
	}
	
	private void addWhereValue(Where where, String[] values,
			List<Parameter> paramters) {
		
		for(String value: values){
			if(DataValidater.isStrEmpty(value)){
				continue;
			}
			value = value.trim();
			value = fmtWhereValue(where, value);
			paramters.add(new Parameter(where.getDataType(), value));
		}
	}
	
	private void addWhere(Where where, List<String> whereSql){
		
		whereSql.add(where.getWhereResult());
	}
	
	private boolean isGlobalWhere(Where where){
		
		boolean result = true;
		String whereCode = where.getWhereCode();
		String globalWhere = where.getGlobalWhere();
		
		if(null == globalWhere){
			return false;
		}
		if (!globalWhere.contains(QueryConstant.GLOBAL_VAL_SEP_BEG + whereCode
				+ QueryConstant.GLOBAL_VAL_SEP_END)) {
			return false;
		}

		return result;
	}
	
	private void addGlToWhereSql(Query query,
			Map<String, String[]> parNamesValues, List<String> whereSql,
			List<Parameter> paramters) {
		
		String globalWhere = query.getGlobalWhere();
		if(null == globalWhere){
			return;
		}
		String globalWhereTmp = globalWhere;
		int beginIndex = globalWhereTmp.indexOf(QueryConstant.GLOBAL_VAL_SEP_BEG);
		while(beginIndex != -1){
			int endIndex = globalWhereTmp.indexOf(QueryConstant.GLOBAL_VAL_SEP_END);
			if(endIndex == -1){
				continue;
			}
			String whereNo = globalWhereTmp.substring(beginIndex+2, endIndex);
			Where where = getWhereByCode(query, whereNo);
			List<String> globalWhereSql = new ArrayList<String>();
			setGlobalWhereValue(where, parNamesValues, globalWhereSql, paramters);
			
			StringBuffer strTmp = new StringBuffer();
			strTmp.append(QueryConstant.GLOBAL_VAL_SEP_BEG);
			strTmp.append(whereNo);
			strTmp.append(QueryConstant.GLOBAL_VAL_SEP_END);
			globalWhere = StringUtils.replaceOnce(globalWhere,
					strTmp.toString(),globalWhereSql.get(0));
			
			globalWhereTmp = globalWhereTmp.substring(endIndex+1);
			beginIndex = globalWhereTmp.indexOf(QueryConstant.GLOBAL_VAL_SEP_BEG);
		}
		
		whereSql.add(globalWhere);
	}
	
	private void setGlobalWhereValue(Where where, Map<String, String[]> parNamesValues,
			List<String> whereSql, List<Parameter> paramters) {
		
		addComToWhereSql(where, parNamesValues, whereSql, paramters);
	}
	
	private Where getWhereByCode(Query query, String whereNo){
		
		Where whereRet = null;
		List<Where> wheres = query.getWheres();
		for(Where where: wheres){
			if(where.getWhereCode().equals(whereNo)){
				whereRet = where;
				break;
			}
		}
		
		return whereRet;
	}

	protected String fmtWhereValue(Integer operator, String valueIn){
		
		String value = valueIn;
		if(null != operator &&
				QueryConstant.OPRT_LK.intValue() == operator.intValue()){
			value = QueryConstant.PERCENT_SIGN+value+QueryConstant.PERCENT_SIGN;
		}
		
		return value;
	}
	
	protected String fmtWhereValue(Where where, String valueIn){
		
		String value = valueIn;
		if(null != where.getIsIgnoreCase()
				&& QueryConstant.YES.intValue() == where.getIsIgnoreCase().intValue()){
			value = value.toUpperCase();
		}
		return fmtWhereValue(where.getOperator(), value);
	}
	
	/* 
	 * 生成EC条件Where SQL
	 * 
	 * @param query 查询
	 * @param filterSet EC条件
	 * @return String EC条件SQL
	 */
	private void addEcToWhereSql(Query query, FilterSet filterSet,
			List<String> whereSql, List<Parameter> parameters) {
		
		if(null == filterSet){
			return;
		}
		Filter[] filters = filterSet.getFilters();
		if(null == filters){
			return;
		}
		for(Filter filter: filters){
			String colName = filter.getProperty();
			Select select = this.getSelectByColName(query, colName);
			
			colName = select.getSelectResult();
			String dataType = select.getDataType();
			
			String colValue = filter.getValue();
			colValue = colValue.trim();
			StringBuffer colNameOprt = new StringBuffer(colName);
			final String E_PAR = " =? ";
			final String LIKE_PAR = " LIKE ?";
			final String LT_PAR = " < ? ";
			final String AND = " and ";
			final String GE_PAR = " >= ? ";
			if(QueryConstant.DATA_TYPE_NUMBER.equals(dataType)){
				/*if(!DataValidater.isStrDouble(colValue) 
						&& !DataValidater.isStrEmpty(select.getDataCoding())){
					colValue = Integer.MAX_VALUE+"";
				}*/
				if(!DataValidater.isStrDouble(colValue)){
					colValue = DataConverter.IntegerToString(Integer.MIN_VALUE);
				}
				colNameOprt.append(E_PAR);
				whereSql.add(colNameOprt.toString());
			}else if(QueryConstant.DATA_TYPE_DATE.equals(dataType)){
				colNameOprt.append(GE_PAR).append(AND).append(colName).append(LT_PAR);
				whereSql.add(colNameOprt.toString());
				String[] beginAndEndDate = getDateInputRange(colValue);
				parameters.add(new Parameter(dataType, beginAndEndDate[0]));
				colValue = beginAndEndDate[1];
			}else{
				colValue = fmtWhereValue(QueryConstant.OPRT_LK, colValue);
				colNameOprt.append(LIKE_PAR);
				whereSql.add(colNameOprt.toString());
			}
			parameters.add(new Parameter(dataType, colValue));
		}
	}
	
	/**
	 * 根据输入的年月日条件，返回对应年月日的开始结束范围字符串
	 * @param colValue
	 * @return
	 */
	private String[] getDateInputRange(String colValue) {
		String[] beginAndEndDate = new String[2];
		Date beginDate, endDate;
		final String YEAR_PATTERN = "^\\d{4}$";
		final String MONTH_PATTERN = "^\\d{4}-\\d{2}$";
		final String DAY_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
		final String POSTFIX_YEAR_FIRST_TIME = "-01-01 00:00:00";
		final String POSTFIX_MONTH_FIRST_TIME = "-01 00:00:00";
		final String POSTFIX_DAY_FIRST_TIME = " 00:00:00";
		if (DataValidater.checkRegx(YEAR_PATTERN, colValue)) {
			beginDate = DataConverter.fmtStrToDatetime(colValue + POSTFIX_YEAR_FIRST_TIME);
			endDate = DateUtils.addYears(beginDate, 1);
		} else if (DataValidater.checkRegx(MONTH_PATTERN, colValue)) {
			beginDate = DataConverter.fmtStrToDatetime(colValue + POSTFIX_MONTH_FIRST_TIME);
			endDate = DateUtils.addMonths(beginDate, 1);
		} else if (DataValidater.checkRegx(DAY_PATTERN, colValue)) {
			beginDate = DataConverter.fmtStrToDatetime(colValue + POSTFIX_DAY_FIRST_TIME);
			endDate = DateUtils.addDays(beginDate, 1);
		} else {
			beginDate = new Date();
			endDate = beginDate;
		}
		beginAndEndDate[0] = DataConverter.fmtDateToDateStr(beginDate, QueryConstant.YYYY_MM_DD_HH_MM_SS);
		beginAndEndDate[1] = DataConverter.fmtDateToDateStr(endDate, QueryConstant.YYYY_MM_DD_HH_MM_SS);
		
		return beginAndEndDate;
	}
	
	private Select getSelectByColName(Query query, String colName){
		
		Select selectRet = null;
		List<Select> selects = query.getSelects();
		for(Select select: selects){
			StringBuffer colName2 = new StringBuffer();
			colName2.append(select.getTableName());
			colName2.append(select.getFieldName());
			colName2.append(select.getFieldAlias());
			if(colName.equals(colName2.toString())){
				selectRet = select;
				break;
			}
		}
		
		return selectRet;
	}
	
	/**
	 * Declare：生成查询数据
	 * 
	 * @param query 查询
	 * @param limit limit
	 * @param parNamesValues 参数值
	 * @param userInfo 当前登录者信息
	 * @return ReturnMessage 返回returnMessage
	 */
	@Override
	@Monitor(name="通用查询")
	public ReturnMessage<Row> getQueryShowDatas(Query query, Limit limit,
			Map<String, String[]> parNamesValues, Map<String, Object> userInfo) {

		ReturnMessage<Row> returnMessage = new ReturnMessage<Row>();
		SQL sql = generateQuerySql(query, limit, parNamesValues);
		//  把$USER_ID,$USER, $USER_NUMBER，$UNIT_CODE用问号代替
		//  并把para值在不影响原来paramters顺序的前提下设进paramters 
		sql.setSql(buildSql(query, userInfo, sql));
		try {
			List<Row> datas = getQueryDao().getQueryDatas(query, sql);
			fmtRetDatas(query, datas);

			returnMessage.setReturnObjects(datas);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		} catch (SQLException e) {
			throw new QuerySysException(e);
		}
		return returnMessage;
	}

	/**
	 * 把$USER_ID,$USER, $USER_NUMBER，$UNIT_CODE用问号代替
	 * 并把para值在不影响原来paramters顺序的前提下设进paramters 
	 * @param query
	 * @param userInfo
	 * @param sql
	 * @return
	 */
	private StringBuffer buildSql(Query query, Map<String, Object> userInfo, SQL sql) {
		HashMap para = new HashMap();
		if (userInfo != null) {
			UserProfile userProfile = (UserProfile)userInfo.get(AppConstant.USER_NAME);
			if(userProfile != null) {
				addPara(para, $USER_ID, userProfile.getEmpId());
				addPara(para, $USER_NUMBER, userProfile.getEmpNumber());
				addPara(para, $UNIT_CODE, userProfile.getDepartment().getUnitCode());
				
				//add by hyc for query application
				Application application = (Application) ServletActionContext.getRequest().getSession()
						.getAttribute(AppConstant.APPLICATION_NAME);
				Long appid = DataConverter.stringToLong(application.getApplicationId());
				addPara(para, $APPLICATION, appid+"");
			}
		}
		String sqlStr = sql.getSql().toString();
		if(StringUtils.indexOf(sqlStr, "$") != -1){
			sqlStr = JDBCUtil.generatePrecompileSQL(sqlStr, para, sql.getParamters(), query.getWheres());
		}
		return new StringBuffer(sqlStr);
	}
	/**
	 * 配置系统内置参数字典
	 * @param para
	 * @param key
	 * @param val
	 */
	private void addPara(HashMap para,String key, String val) {
		if (StringUtils.isBlank(val)) {
			val = "";
		}
		para.put(key, val);
	}
	
	/**
	 * 生成SQL
	 * @param query 查询
	 * @param limit limit
	 * @param parNamesValues 参数键值
	 * @return SQL
	 */
	protected SQL generateQuerySql(Query query, Limit limit,
			Map<String, String[]> parNamesValues){
		
		SQL sql = new SQL(query.getTable().getRowStart()+1, query.getTable().getRowEnd());
		
		addQueryConToSql(query, limit, parNamesValues, sql);
		
		return sql;
	}
	
	/**
	 * 添加查询条件到SQL
	 * @param query
	 * @param limit
	 * @param parNamesValues
	 * @param sql
	 */
	private void addQueryConToSql(Query query, Limit limit,
			Map<String, String[]> parNamesValues, SQL sql){
		
		addSelectToSql(query, sql);
		addFromToSql(query, sql);
		addWhereToSql(query, limit, parNamesValues, sql);
		addGroupByToSql(query, sql);
		addOrderByToSql(query, limit.getSort(), sql);
		
	}
	
	/* 
	 * 格式化返回数据
	 * 
	 * @param query 查询
	 * @param datas 数据
	 * @return List<Data> 格式化后数据
	 */
	private void fmtRetDatas(Query query, List<Row> datas) {
		
		List<Column> columns = query.getShowColumns();
		Map<String, Map<Object, Object>> dataCodingMap = getDataCodingMap(columns);
		
		for(Row data: datas){
			Row dataTmp = (Row) data.clone();
			
			for(Column column: columns){
				
				StringBuffer colName = new StringBuffer();
				colName.append(column.getTableName());
				colName.append(column.getFieldName());
				colName.append(column.getFieldAlias());
				
				String value = fmtRetDataToStr(column, data.get(colName.toString()));
				
				value = fmtDataCodingToRetData(column, dataCodingMap, value);
				
				value = fmtDataInputToRetData(column, value);
				
				value = fmtUrlToRetData(column, data, value);
				
				value = fmtVarToRetData(columns, dataTmp, value);
				
				value = fmtScopeToRetData(value);
				
				data.put(colName.toString(), value);
			}
		}
	}
	
	private String fmtRetDataToStr(Column column, Object value){
		
		String data = QueryConstant.EMPTY_STR;
		if(null == value){
			return data;
		}
		String dateType = column.getDataType();
		String formater = DataServiceUtil.getDataValue(
				QueryConstant.DATA_FORMAT, DataConverter.IntegerToString(column.getOutFormat()));
		if(QueryConstant.DATA_TYPE_DATE.equals(dateType)
				&& value instanceof Date){
			if(formater != null){
				data = DataConverter.fmtDateToDateStr((Date)value, formater);
			}else{
				data = value.toString();
			}
		}else if((QueryConstant.DATA_TYPE_LONG.equals(dateType) 
				|| QueryConstant.DATA_TYPE_NUMBER.equals(dateType))
				&& value instanceof Number) {
			if(formater != null){
				data = DataConverter.fmtToNumberStr(value, formater);
			}else{
				data = value.toString();
			}
		}else{
			data = value.toString();
		}
		
		return data;
	}

	private String fmtDataInputToRetData(Column column, String valueIn){
		
		String value = valueIn;
		String dataInput = column.getDataInput();
		//数据输入判断 modify by Mike 20150428 
		//exp: <input type=checkbox name='ids' value='$0' />
		if(null != dataInput && !"".equals(dataInput)){ 
			value = dataInput;
			//解决$问题
			value = replaceVarDollor(value);
		}
		
		return value;
	}
	
	private static String replaceVarDollor(String valueIn){
		
		String value = valueIn;
		StringBuffer valueRet = new StringBuffer(QueryConstant.EMPTY_STR);
		while(true){
			int index = value.indexOf(QueryConstant.VAL_SEP);
			if(index == -1){
				valueRet.append(value);
				break;
			}
			final int LEN = value.length();
			if(index >= LEN-1){
				valueRet.append(value);
				break;
			}
			valueRet.append(value.substring(0, index));
			String var = value.substring(index+1, index+2);
			if(DataValidater.isStrInteger(var)){
				valueRet.append(QueryConstant.VAR_SEP);
			}else{
				valueRet.append(QueryConstant.VAL_SEP);
			}
			value = value.substring(index+1);
		}
		
		return valueRet.toString();
	}
	
	private String fmtUrlToRetData(Column column, Row data, String valueIn){
		
		String value = valueIn;
		String url = column.getLinkUrl();
		if(!DataValidater.isStrEmpty(url)){
			Integer openType = column.getOpenType();
			String openTypeStr = DataServiceUtil.getDataValue(
					QueryConstant.OPEN_TYPE, DataConverter.IntegerToString(openType));
			final String A_HREF = "<a href=";
			final String TARGET_EQ = " target=";
			value = A_HREF + url + TARGET_EQ + openTypeStr + QueryConstant.EMPTY_ONE_STR
				+ QueryConstant.RIGHT_J_KUO + value + QueryConstant.TAG_A_END;
			
			//解决$问题
			value = replaceVarDollor(value);
		}
		
		return value;
	}
	
	/* 
	 * 格式化变量
	 * 
	 */
	private String fmtVarToRetData(List<Column> columns, Row data, String valueIn){
		
		String value = valueIn;
		String tmpValue = value;
		String key = QueryConstant.VAR_SEP;
		final int KEY_LEN = key.length();
		int index = -1;
		StringBuffer var = new StringBuffer(QueryConstant.EMPTY_STR);
		while(true){
			index = tmpValue.indexOf(key);
			if(-1 == index){
				break;
			}
			if(tmpValue.length() < (index+KEY_LEN)){
				break;
			}
			index = index+KEY_LEN-1;
			var = new StringBuffer(tmpValue.substring(index+1, index+2));
			if(DataValidater.isStrInteger(var.toString())){
				if(tmpValue.length()>=index+2){
					String tmpValue2 = tmpValue.substring(index+2);
					while(tmpValue2.length()>0){
						String tmpVar = tmpValue2.charAt(0)+QueryConstant.EMPTY_STR;
						if(!DataValidater.isStrInteger(tmpVar)){
							break;
						}
						var.append(tmpVar);
						tmpValue2 = tmpValue2.substring(1);
					}
				}
				Column column = columns.get(DataConverter.stringToInteger(var.toString()));
				if(null != column){
					StringBuffer colName = new StringBuffer(column.getTableName());
					colName.append(column.getFieldName()).append(column.getFieldAlias());
					
					StringBuffer keyVar = new StringBuffer(key);
					keyVar.append(var.toString());
					value = value.replace(keyVar.toString(), 
							DataConverter.valueOf(data.get(colName.toString())));
				}
			}
			if(tmpValue.length() < (index+2)){
				break;
			}
			tmpValue = tmpValue.substring(index+2);
		}
		
		return value;
	}
	
	/* 
	 * 格式化范围对象变量
	 * 
	 */
	private String fmtScopeToRetData(String valueIn){
		
		String value = valueIn;
		String tmpValue = valueIn;
		String startKey = QueryConstant.GLOBAL_VAL_SEP_BEG;
		String endKey = QueryConstant.GLOBAL_VAL_SEP_END;
		int startIndex = 0;
		int endIndex = 0;
		String var = QueryConstant.EMPTY_STR;
		Object varValue = null;
		while(true){
			startIndex = tmpValue.indexOf(startKey);
			if(-1 == startIndex){
				break;
			}
			if(tmpValue.length() < startIndex+4){
				break;
			}
			endIndex = tmpValue.indexOf(endKey, startIndex+2);
			if(-1 == endIndex){
				break;
			}
			var = tmpValue.substring(startIndex+2, endIndex);
			varValue = getVarFromScope(var);

			if(varValue != null){
				StringBuffer keyVar = new StringBuffer();
				keyVar.append(startKey).append(var).append(endKey);
				value = value.replace(keyVar.toString(), varValue.toString());
			}
			
			if(tmpValue.length() < endIndex){
				break;
			}
			tmpValue = tmpValue.substring(endIndex);
		}
		
		return value;
	}
	
	/**
	 * @param var
	 * @return
	 */
	private Object getVarFromScope(String var) {
		
		if(DataValidater.isStrEmpty(var)){
			return null;
		}
		
		final String paramKey = QueryConstant.PARAM_KEY+QueryConstant.DOT_SIGN;
		final String requestKey = QueryConstant.REQUEST_KEY+QueryConstant.DOT_SIGN;
		final String sessionKey = QueryConstant.SESSION_KEY+QueryConstant.DOT_SIGN;
		final String contextKey = QueryConstant.CONTEXT_KEY+QueryConstant.DOT_SIGN;
		
		String varName = null;
		if(var.startsWith(paramKey)){
			varName = var.substring(paramKey.length());
			return ContextFactory.getParamFromRequest(varName);
		}else if(var.startsWith(requestKey)){
			varName = var.substring(requestKey.length());
			return ContextFactory.getFromRequest(varName);
		}else if(var.startsWith(sessionKey)){
			varName = var.substring(sessionKey.length());
			return ContextFactory.getFromSession(varName);
		}else if(var.startsWith(contextKey)){
			varName = var.substring(contextKey.length());
			return ContextFactory.getFromContext(varName);
		}
		
		return null;
	}
	
	/**
	 * @param column
	 * @param value
	 * @return
	 */
	private String fmtDataCodingToRetData(Column column,
			Map<String, Map<Object, Object>> dataCodingMap, String valueIn) {
		
		String value = valueIn;
		String dataCoding = column.getDataCoding();
		if(DataValidater.isStrEmpty(dataCoding)){
			return value;
		}
		Map<Object, Object> keyValue = dataCodingMap.get(dataCoding);
		Object objValue = keyValue.get(value);
		if(null != objValue){
			value = DataConverter.valueOf(objValue);
		}
		
		return value;
	}
	
	/**
	 * 
	 * @Title: getDataCodingMap   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param columns
	 * @param: @return      
	 * @return: Map<String,Map<Object,Object>>      
	 * @throws
	 */
	private Map<String, Map<Object, Object>> getDataCodingMap(
			List<Column> columns) {
		
		Map<String, Map<Object, Object>> dataCodingMap = new HashMap<String, Map<Object, Object>>();
		
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		for(Column column: columns){
			String dataCoding = column.getDataCoding();
			if(DataValidater.isStrEmpty(dataCoding)){
				continue;
			}
			BaseDataSourceVo baseDataSourceVo = getBaseDataSourceService()
					.getBdsVoData(dataCoding, filterMap, QueryConstant.SQL_AND).getReturnObject();
			Map<Object, Object> keyValue = baseDataSourceVo.codingColumnKeyValue(
					BdsConstant.FIXED_COL_NAME_CODE, BdsConstant.FIXED_COL_NAME_DN);

			dataCodingMap.put(dataCoding, keyValue);
		}
		
		return dataCodingMap;
	}
}
