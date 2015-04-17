package com.amway.frm.bds.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.amway.frm.base.util.DataValidater;
import com.amway.frm.bds.exception.BdsSysException;
import com.amway.frm.bds.vo.BaseDataSourceVo;

/**
 * 
 * @author lenovo
 *
 */
public class BdsXmlUntil {
	
	/**
	 * 将结果集合组装成JSON字符串返回
	 * @param bdsVo  包含<DataSourceVO>对象的结果集
	 * @return  String,其中的String 为json字符
	 */
	public static String assembleJsonStr(BaseDataSourceVo bdsVo) {
		
		if(bdsVo == null){
			return BdsConstant.EMPTY_STR;
		}

		return bdsVo.getJsonStr();
	}

	public static List<String> getColNamesFromXmlDoc(String xmlDoc) {

		List<String> colNames = new ArrayList<String>();

		Document doc = getDocument(xmlDoc);

		Element table = doc.getRootElement();
		List<Element> columns = table.getChildren();
		for (Element column : columns) {
			List<Element> colElems = column.getChildren();
			for (Element colElem : colElems) {
				String colName = colElem.getName();
				if (!BdsConstant.XML_STRUCT_NAME.equals(colName)) {
					continue;
				}
				colNames.add(colElem.getText());
			}
		}
		
		return colNames;
	}
	
	public static Map<String, Map<String, String>> getColAttNameValuesFromXmlDoc(String xmlDoc) {

		Map<String, Map<String, String>> colNames = new LinkedHashMap<String, Map<String, String>>();

		Document doc = getDocument(xmlDoc);

		Element table = doc.getRootElement();
		List<Element> columns = table.getChildren();
		for (Element column : columns) {
			List<Element> colElems = column.getChildren();
			Map<String, String> colNameValue = new LinkedHashMap<String, String>();
			String colKey = BdsConstant.EMPTY_STR;
			for (Element colElem : colElems) {
				String colName = colElem.getName();
				String colValue = colElem.getText();
				if (BdsConstant.XML_STRUCT_NAME.equals(colName)) {
					colKey = colValue;
				}
				colNameValue.put(colName, colValue);
				
			}
			colNames.put(colKey, colNameValue);
		}
		
		return colNames;
	}
	
	public static List<Map<String, String>> getColAttValuesFromXmlDoc(String xmlDoc) {

		List<Map<String, String>> colValues = new ArrayList<Map<String, String>>();

		Document doc = getDocument(xmlDoc);

		Element table = doc.getRootElement();
		List<Element> columns = table.getChildren();
		for (Element column : columns) {
			List<Element> colElems = column.getChildren();
			Map<String, String> colNameValue = new LinkedHashMap<String, String>();
			for (Element colElem : colElems) {
				String colName = colElem.getName();
				String colValue = colElem.getText();
				
				colNameValue.put(colName, colValue);
			}
			colValues.add(colNameValue);
		}
		
		return colValues;
	}

	public static Document getDocument(String xmlDoc){
		
		StringReader read = new StringReader(xmlDoc);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(read);
		} catch (Exception e) {
			throw new BdsSysException(e);
		} 
		
		return doc;
	}

	public static Map<String, String> tranXmlDocToMap(String xmlDoc){
		
		Map<String, String> xmlNameValueMap = new LinkedHashMap<String, String>();
		
		Document doc = getDocument(xmlDoc);
		Element root = doc.getRootElement();
		List<Element> children = root.getChildren();

		for (int i = 0; i < children.size(); i++) {
			Element element = children.get(i);
			String name = element.getName();
			String value = element.getText();
			xmlNameValueMap.put(name, value);
		}
		return xmlNameValueMap;
	}
	
	public static List<Map<String, String>> tranXmlNodeToMap(String docXml,
			String nodeName) {
		
		List<Map<String, String>> nodeMap = new ArrayList<Map<String, String>>();
		Document doc = BdsXmlUntil.getDocument(docXml);
		Element root = doc.getRootElement();
		List<Element> nodes = new ArrayList<Element>();
		findElements(nodes, root, nodeName);
		for(Element node: nodes){
			nodeMap.add(getElementChildMap(node));
		}
		
		return nodeMap;
	}
	
	public static void findElements(List<Element> nodes, Element ele,
			String nodeName) {
		
		if(ele.getName().equalsIgnoreCase(nodeName)){
			nodes.add(ele);
			return;
		}
		List<Element> children = ele.getChildren();
		if(DataValidater.isCollectionEmpty(children)){
			return;
		}
		for(Element child: children){
			findElements(nodes, child, nodeName);
		}
	}
	
	public static Map<String, String> getElementChildMap(Element pEle){
		
		Map<String, String> childMap = new HashMap<String, String>();
		List<Element> children = pEle.getChildren();
		for(Element child: children){
			childMap.put(child.getName(), child.getText());
		}
		
		return childMap;
	}
}
