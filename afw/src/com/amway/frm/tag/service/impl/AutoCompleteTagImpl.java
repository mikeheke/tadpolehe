package com.amway.frm.tag.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsXmlUntil;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.tag.exception.TagSysException;
import com.amway.frm.tag.filter.TagDataFilter;
import com.amway.frm.tag.service.AutoCompleteTagService;
import com.amway.frm.tag.util.TagConstant;
import com.amway.frm.tag.vo.AutoComplete;

/**
 * 自动充填标签实现类
 * @author huangweijin
 */
public class AutoCompleteTagImpl extends BaseImpl implements
		AutoCompleteTagService {
	
	//数据源service接口类
	private  BaseDataSourceService baseDataSourceService;

	/**
	 * 获得自动充填的下拉提示列表数据
	 * @param autoComplete  数据编码
	 * @return  json数据
	 */
	@Override
	public ReturnMessage<String> getAutoCompleteDatas(AutoComplete autoComplete) {
		
		ReturnMessage<String> returnMessage = new ReturnMessage<String>();
		
		Map<String, String[]> filterMap = getFilterMap(autoComplete);
		String bdsKey = autoComplete.getSource();
		ReturnMessage<BaseDataSourceVo> returnMessageDBS = null;
		returnMessageDBS = baseDataSourceService.getBdsVoData(
				bdsKey, filterMap, TagConstant.SQL_OR);
		
		String jsonValue = null;
		if(returnMessageDBS.isSuccess()){
			BaseDataSourceVo baseDataSourceVo = filterRetDatas(returnMessageDBS
					.getReturnObject(), autoComplete);
			jsonValue = BdsXmlUntil.assembleJsonStr(baseDataSourceVo);
		}
		
		returnMessage.setReturnObject(jsonValue);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		
		return returnMessage;
	}
	
	private BaseDataSourceVo filterRetDatas(BaseDataSourceVo baseDataSourceVo,
			AutoComplete autoComplete) {
	
		if(!isFilterPattern(autoComplete)){
			return baseDataSourceVo;
		}
		Object[][] bdsDatas = baseDataSourceVo.getBdsDatas();
		if(null == bdsDatas || 0 == bdsDatas.length){
			return baseDataSourceVo;
		}
		TagDataFilter tagDataFilter = getTagDataFilter(autoComplete);
		List<String> filterColNames = Arrays.asList(autoComplete
				.getFilterColNames().split(AppConstant.SPILT_OPER));
		String filterPattern = getFilterPattern(autoComplete);
		List<String> bdsColNames = baseDataSourceVo.getBdsColNames();
		List<Object[]> listArr = new ArrayList<Object[]>();
		for (Object[] row : bdsDatas) {
			int colLen = bdsColNames.size();
			int i = 0;
			for (; i < colLen; i++) {
				if(!filterColNames.contains(bdsColNames.get(i))){
					continue;
				}
				Object value = row[i];
				boolean result = (Boolean)tagDataFilter.matches(value, filterPattern);
				if(result){
					break;
				}
			}
			if(i == colLen){
				continue;
			}
			listArr.add(row);
		}
		
		BaseDataSourceVo baseDataSourceVoRet = new BaseDataSourceVo();
		baseDataSourceVoRet.setBdsDatas(DataConverter.convertListArrToTwoArr(listArr));
		baseDataSourceVoRet.setBdsColNames(bdsColNames);
		
		return baseDataSourceVoRet;
	}
	
	private String getFilterPattern(AutoComplete autoComplete){
		
		String filterValue = autoComplete.getFilterColValue();
		String filterPattern = autoComplete.getFilterPattern();
		final String patternLower = "\\v";
		final String patternUpper = "\\V";
		filterPattern = filterPattern.replace(patternLower, filterValue);
		filterPattern = filterPattern.replace(patternUpper, filterValue);
		
		return filterPattern;
	}
	
	private TagDataFilter getTagDataFilter(AutoComplete autoComplete){
		
		TagDataFilter tagDataFilter = null;
		try {
			String filterClass = autoComplete.getFilterClass();
			tagDataFilter = (TagDataFilter) Class.forName(filterClass).newInstance();
		} catch (Exception e) {
			throw new TagSysException(e);
		}
		
		return tagDataFilter;
	}
	
	private Map<String, String[]> getFilterMap(AutoComplete autoComplete) {
		
		if(isFilterPattern(autoComplete)){
			return new LinkedHashMap<String, String[]>();
		}
		
		String filterColNames = autoComplete.getFilterColNames();
		String filterColValue = autoComplete.getFilterColValue()+TagConstant.PERCENT_SIGN;
		String[] filterColNameArr = filterColNames.split(AppConstant.SPILT_OPER);
		Map<String, String[]> filterMap = new LinkedHashMap<String, String[]>();
		for(String colName : filterColNameArr){
			filterMap.put(colName, new String[]{filterColValue});
		}
		//黄波 2013/10/22
		if(!StringUtils.isEmpty(autoComplete.getAutoCompleteLimit())){
			filterMap.put(AutoComplete.LIMIT_NAME, new String[]{autoComplete.getAutoCompleteLimit()});
		}
		return filterMap;
	}
	
	private boolean isFilterPattern(AutoComplete autoComplete){
		boolean result = false;
		if(DataValidater.isStrEmpty(autoComplete.getFilterPattern())){
			result = false;
		}else{
			result = true;
		}
		return result;
	}
	
	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}
}
