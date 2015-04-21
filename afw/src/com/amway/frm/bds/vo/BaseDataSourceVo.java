
package com.amway.frm.bds.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amway.frm.bds.util.BdsConstant;

/**
 * 供内外部调用的基础数据服务实现类中，封装的返回对象
 * 
 */
public class BaseDataSourceVo {
	
	//基础数据编码
	private String bdsCode;

	//返回数据中的列名信息集合
	private List<String> bdsColNames;
	
	//Object[][]为返回包含数据的二维数组，其第二维数据保存的列名顺序与colNameList的列名顺序一致
	private Object[][] bdsDatas;
	
	//行列反转的二维数组
	private Object[][] bdsReversalDatas;
	
	//取行大小
	public int getRowLength(){
		
		if(null == bdsDatas){
			return -1;
		}
		return bdsDatas.length;
	}
	
	//取列大小
	public int getColumnLength(){
		
		if(null == bdsReversalDatas){
			return -1;
		}
		return bdsReversalDatas.length;
	}
	
	//取所有整行方法
	public List<List<Object>> getRows(){
		
		if(null == bdsDatas){
			return null;
		}
		
		List<List<Object>> rows = new ArrayList<List<Object>>();
		for(Object[] bdsData: bdsDatas){
			List<Object> columns =  new ArrayList<Object>();
			for(Object data: bdsData){
				columns.add(data);
			}
			rows.add(columns);
		}
		
		return rows;
	}
	
	//取所有整行方法map
	public List<Map<String, Object>> getRowsMap(){
		
		if(null == bdsDatas){
			return null;
		}
		
		List<Map<String, Object>> rows = 
			new ArrayList<Map<String, Object>>();
		for(Object[] bdsData: bdsDatas){
			Map<String, Object> columns =  
				new LinkedHashMap<String, Object>();
			int len = bdsColNames.size();
			for(int i=0; i<len; i++){
				String colName = bdsColNames.get(i);
				Object data = bdsData[i];
				columns.put(colName, data);
			}
			rows.add(columns);
		}
		
		return rows;
	}
	
	//取某整行方法
	public List<Object> getRow(int i){
		
		if(null == bdsDatas){
			return null;
		}
		if(i < 0 || i > bdsDatas.length){
			return null;
		}
		
		List<Object> row = new ArrayList<Object>();
		Object[] bdsData = bdsDatas[i];
		for(Object data: bdsData){
			row.add(data);
		}
		
		return row;
	}
	
	//取某整行某对象方法
	public Object getRowSingle(String colName){
		
		List<Map<String, Object>> listMap = this.getRowsMap();
		if(null == listMap || listMap.size() == 0){
			return null;
		}
		Map<String, Object> map = listMap.get(0);
		
		return map.get(colName);

	}
	
	//取所有整列方法
	public List<List<Object>> getColumns(){
		
		if(null == bdsReversalDatas){
			return null;
		}
		
		List<List<Object>> columns = new ArrayList<List<Object>>();
		for(Object[] bdsReversalData: bdsReversalDatas){
			List<Object> rows =  new ArrayList<Object>();
			for(Object data: bdsReversalData){
				rows.add(data);
			}
			columns.add(rows);
		}
		
		return columns;
	}
	
	//取所有整列方法map
	public Map<String, List<Object>> getColumnsMap(){
		
		if(null == bdsReversalDatas){
			return null;
		}
		
		Map<String, List<Object>> columns = 
			new LinkedHashMap<String, List<Object>>();
		int len = bdsColNames.size();
		for(int i=0; i<len; i++){
			String colName = bdsColNames.get(i);
			Object[] bdsReversalData = bdsReversalDatas[i];
			List<Object> rows =  new ArrayList<Object>();
			for(Object data: bdsReversalData){
				rows.add(data);
			}
			columns.put(colName, rows);
		}
		
		return columns;
	}
	
	//取某整列方法
	public List<Object> getColumn(String colName){
		
		if(bdsColNames == null){
			return null;
		}
		int index = getColumnIndex(colName);
		if(index == -1){
			return null;
		}
		
		List<Object> rows = null;
		int i=0;
		for(Object[] bdsReversalData: bdsReversalDatas){
			if(index != i++){
				continue;
			}
			rows =  new ArrayList<Object>();
			for(Object data: bdsReversalData){
				rows.add(data);
			}
		}
		
		return rows;
	}
	
	//取某整列单对象方法
	public Object getColumnSingle(String colName){
		
		List<Object> list = this.getColumn(colName);
		if(null == list || list.size() == 0){
			return null;
		}
		
		return list.get(0);
	}
	
	//取某列列名
	public String getColumnName(int i){
		
		if(bdsColNames == null){
			return null;
		}
		if(i<0 || i>bdsColNames.size()){
			return null;
		}
		
		return bdsColNames.get(i);
	}
	
	/*
	 * 取列索引
	 */
	public int getColumnIndex(String colName){
		
		int index = -1;
		if(colName == null){
			return -1;
		}
		if(bdsColNames == null){
			return -1;
		}
		int i = 0;
		for(String bdsColName: bdsColNames){
			if(colName.equals(bdsColName)){
				index = i;
				break;
			}
			i++;
		}
		
		return index;
	}
	
	/*
	 * 取JSON字符串
	 */
	public String getJsonStr() {
		
		if(null == bdsColNames){
			return BdsConstant.EMPTY_STR;
		}
		if(null == bdsDatas){
			return BdsConstant.EMPTY_STR;
		}
		
		StringBuffer buffer = new StringBuffer(BdsConstant.EMPTY_ONE_STR);
		for (Object[] row : bdsDatas) {
			buffer.append(BdsConstant.LEFT_D_KUO);
			Object[] col = row;
			int colLen = bdsColNames.size();
			for (int i = 0; i < colLen; i++) {
				buffer.append(BdsConstant.QUO_D_SIGN);
				buffer.append(bdsColNames.get(i));
				buffer.append(BdsConstant.QUO_D_SIGN).append(BdsConstant.MAO_SIGN);
				buffer.append(BdsConstant.QUO_D_SIGN);
				buffer.append(col[i]);
				buffer.append(BdsConstant.QUO_D_SIGN).append(BdsConstant.DOU_SIGN);
			}
			buffer = buffer.delete(buffer.length() - 1, buffer.length());
			buffer.append(BdsConstant.RIGHT_D_KUO).append(BdsConstant.DOU_SIGN);
		}
		buffer = buffer.delete(buffer.length() - 1, buffer.length());
		
		String jsonStr = buffer.toString().trim();
		jsonStr = BdsConstant.LEFT_Z_KUO+jsonStr+BdsConstant.RIGHT_Z_KUO;

		return jsonStr;
	}
	
	/**
	 * 取编码KEY和VALUE值
	 * @param keyColumn
	 * @param valueColumn
	 * @return
	 */
	public Map<Object, Object> codingColumnKeyValue(String keyColumn, 
			String valueColumn){
		
		if(null == bdsDatas){
			return new LinkedHashMap<Object, Object>();
		}
		
		Map<Object, Object> rows = new LinkedHashMap<Object, Object>();
		for(Object[] bdsData: bdsDatas){
			Object key = null;
			Object value = null;
			int len = bdsColNames.size();
			for(int i=0; i<len; i++){
				String colName = bdsColNames.get(i);
				if(colName.equals(keyColumn)){
					key = bdsData[i];
				}else if(colName.equals(valueColumn)){
					value = bdsData[i];
				}
			}
			if(null != key && null != value){
				rows.put(key, value);
			}
		}
		
		return rows;
	}

	public List<String> getBdsColNames() {
		return bdsColNames;
	}

	public void setBdsColNames(List<String> colNames) {
		this.bdsColNames = colNames;
	}

	public Object[][] getBdsDatas() {
		return bdsDatas;
	}

	public void setBdsDatas(Object[][] bdsDatas) {
		this.bdsDatas = bdsDatas;
	}
	
	public String getBdsCode() {
		return bdsCode;
	}
	
	public void setBdsCode(String bdsCode) {
		this.bdsCode = bdsCode;
	}
	
	public Object[][] getBdsReversalDatas() {
		return bdsReversalDatas;
	}

	public void setBdsReversalDatas(Object[][] bdsReversalDatas) {
		this.bdsReversalDatas = bdsReversalDatas;
	}

}
