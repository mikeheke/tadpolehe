package com.amway.frm.tag.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.tag.service.SelectTagService;
import com.amway.frm.tag.util.TagConstant;
import com.amway.frm.tag.vo.Select;

/**
 * 选择标签实现类
 * 
 */
public class SelectTagImpl extends BaseImpl implements SelectTagService {

	private BaseDataSourceService baseDataSourceService;

	/**
	 * 获得select标签的数据集
	 * @param select  标签体
	 * @return  ReturnMessage<String> json数据集
	 */
	@Override
	public ReturnMessage<String> getSelectTagDatas(Select select) {
		
		ReturnMessage<String> returnMessage = null;

		Map<String, String[]> filterMap = getFilterMapParams(select);
		String dictCode = select.getDictCode();
		returnMessage = baseDataSourceService.getJSONData(dictCode, filterMap,
				TagConstant.SQL_OR);
		
		return returnMessage;
		
	}
	
	private Map<String, String[]> getFilterMapParams(Select select){
		
		String parentKey = select.getParentKey();
		String parentValues = select.getParentValue();
		if(DataValidater.isStrEmpty(parentKey)){
			return new HashMap<String, String[]>();
		}
		parentValues = DataConverter.valueOf(parentValues);
		String[] parentValueArr = parentValues.split(TagConstant.DOU_SIGN);
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put(parentKey, parentValueArr);
		
		return filterMap;
	}
	
	@Override
	public List<Map> getDatasFromScope(Collection source){
		
		return DataConverter.convertColToListMap(source);
	}
	
	@Override
	public List<Map> getDatasFromBDS(Select select){
		
		ReturnMessage<String> returnMessage = null;
		returnMessage = getSelectTagDatas(select);
		if(!returnMessage.isSuccess()){
			return new ArrayList<Map>();
		}
		String jsonText = returnMessage.getReturnObject();
		
		List<Map> datas = null;
		datas = DataConverter.convertJsonToListMap(jsonText);
		
		return datas;
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}

}
