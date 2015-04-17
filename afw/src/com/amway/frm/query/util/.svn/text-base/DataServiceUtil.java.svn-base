/**
 * 
 */
package com.amway.frm.query.util;

import com.amway.frm.base.util.DataConverter;

/**
 * @author huangweijin
 *
 * 2011-4-5 下午02:11:09
 */
public class DataServiceUtil {

	public static String getDataValue(String key, String code){
		
		if(QueryConstant.DATA_TYPE.equals(key)){
			return getDataType(code);
		}else if(QueryConstant.DATA_FORMAT.equals(key)){
			return getOutFormat(code);
		}else if(QueryConstant.OPEN_TYPE.equals(key)){
			return getOpenType(code);
		}
		
		return null;
	}
	
	private static String getDataType(String dataType){
		
		String cell = null;
		if(QueryConstant.DATA_TYPE_NUMBER.equals(dataType)){
			cell = QueryConstant.NUMBER_FMT;
		}else if(QueryConstant.DATA_TYPE_DATE.equals(dataType)){
			cell = QueryConstant.DATE_FMT;
		}else if(QueryConstant.DATA_TYPE_LONG.equals(dataType)){
			cell = QueryConstant.CURRENCY_FMT;
		}else{
			cell = QueryConstant.DISPLAY_FMT;
		}
		
		return cell;
	}
	
	private static String getOutFormat(String outFormat){
		String tmpFormat = null;
		if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_DATE)).equals(outFormat)){
			tmpFormat = QueryConstant.YYYY_MM_DD;
		}else if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_TIME)).equals(outFormat)){
			tmpFormat = QueryConstant.HH_MM_SS;
		}else if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_DATETIME)).equals(outFormat)){
			tmpFormat = QueryConstant.YYYY_MM_DD_HH_MM_SS;
		}else if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_CURRENCY)).equals(outFormat)){
			tmpFormat = QueryConstant.OUT_FORMAT_CURRENCY_FMT;
		}else if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_CURRENCY_DOLLAR)).equals(outFormat)){
			tmpFormat = QueryConstant.OUT_FORMAT_CURRENCY_DOLLAR_FMT;
		}else if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_MILLTIME)).equals(outFormat)){
			tmpFormat = QueryConstant.YYYY_MM_DD_HH_MM_SS_SSS;
		}else if((DataConverter.IntegerToString(QueryConstant.OUT_FORMAT_MONTHDATE)).equals(outFormat)){
			tmpFormat = QueryConstant.YYYY_MM;
		}
		
		return tmpFormat;
	}
	
	private static String getOpenType(String openType){
		String tmpOpenType = null;
		if((DataConverter.IntegerToString(QueryConstant.OPEN_TYPE_BLANK)).equals(openType)){
			tmpOpenType = QueryConstant.OPEN_TYPE_BLANK_FMT;
		}else if((DataConverter.IntegerToString(QueryConstant.OPEN_TYPE_PARENT)).equals(openType)){
			tmpOpenType = QueryConstant.OPEN_TYPE_PARENT_FMT;
		}else if((DataConverter.IntegerToString(QueryConstant.OPEN_TYPE_TOP)).equals(openType)){
			tmpOpenType = QueryConstant.OPEN_TYPE_TOP_FMT;
		}else if((DataConverter.IntegerToString(QueryConstant.OPEN_TYPE_SELF)).equals(openType)){
			tmpOpenType = QueryConstant.OPEN_TYPE_SELF_FMT;
		}
		
		return tmpOpenType;
	}
	
	public static String getOrderByName(Integer orderBy) {
		if (null != orderBy &&
				QueryConstant.ORDER_BY_ASC.intValue() == orderBy.intValue()) {
			return QueryConstant.SQL_ORDERBY_ASC;
		} else {
			return QueryConstant.SQL_ORDERBY_DESC;
		}
	}
}
