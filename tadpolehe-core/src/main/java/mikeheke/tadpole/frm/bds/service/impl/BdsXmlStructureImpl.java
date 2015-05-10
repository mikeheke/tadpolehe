/**
 * 
 */
package mikeheke.tadpole.frm.bds.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.exception.BdsSysException;
import mikeheke.tadpole.frm.bds.service.BdsXmlStructureService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BdsXmlStructureVo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * xml数据结构service实现类
 * 
 * 
 */
public class BdsXmlStructureImpl extends BaseImpl implements
		BdsXmlStructureService {
	
	/**
	 * 通过 xml 字符串，创建jdom的Document对象
	 * @param xmlString
	 * @return
	 */
	private Document getDocumentByString(String xmlString){
		StringReader read = new StringReader(xmlString);
		InputSource source = new InputSource(read);
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(source);
		} catch (Exception e) {
			throw new BdsSysException(e);
		}
		return document;
	}

	/**
	 * 解析xml数据结构，并返回消息对象
	 * @param xmlString
	 * @return  包含XmlStructure对象的RetrunMessage
	 */
	@Override
	public ReturnMessage<BdsXmlStructureVo> parseXmlStructure(String xmlString){
		
		ReturnMessage<BdsXmlStructureVo> returnMessage = new ReturnMessage<BdsXmlStructureVo>();
		if(!DataValidater.isStrEmpty(xmlString)){
			Document document = getDocumentByString(xmlString);
			List<BdsXmlStructureVo> props = this.parseDocument(document);
			returnMessage.setReturnObjects(props);
		}
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);

		return returnMessage;
	}

	/**
	 * 将org.w3c.dom.Document 对象解析成包含XmlStructure对象类型的数据集合List
	 * @param document
	 * @return
	 */
	private List<BdsXmlStructureVo> parseDocument(Document document) {
		List<BdsXmlStructureVo> datas = new ArrayList<BdsXmlStructureVo>();
		Element element = document.getDocumentElement();
		NodeList nodes = element.getChildNodes();

		for(int i=0;i<nodes.getLength();i++){
			if(!(nodes.item(i) instanceof Element)){ //非element类型节点
				continue;
			}
			Node node = (Element)nodes.item(i);
			NodeList attrNodes = node.getChildNodes();
			Map<String,String> attrMap = new HashMap<String,String>();
			BdsXmlStructureVo xs = new BdsXmlStructureVo();
			for(int x=0;x<attrNodes.getLength();x++){
				Node attrNode = attrNodes.item(x);
				if(!(attrNodes.item(x) instanceof Element)){
					continue;
				}
				//attrMap.put(attrNode.getNodeName(), attrNode.getTextContent());
				attrMap.put(attrNode.getNodeName(), attrNode.getNodeValue());
			}
			xs.setCnName(attrMap.get(BdsConstant.CN_NAME));
			xs.setEnName(attrMap.get(BdsConstant.EN_NAME));
			xs.setColType(attrMap.get(BdsConstant.COL_TYPE));
			xs.setMaxLen(attrMap.get(BdsConstant.MAX_LEN));
			xs.setName(attrMap.get(BdsConstant.NAME));
			xs.setRefCode(attrMap.get(BdsConstant.REF_CODE));
			xs.setRefCol(attrMap.get(BdsConstant.REF_COL));
			xs.setRegex(attrMap.get(BdsConstant.REGEX));
			xs.setRequired(attrMap.get(BdsConstant.REQUIRED));
			xs.setUnique(attrMap.get(BdsConstant.UNIQUE));
			datas.add(xs);
		}

		return datas;
	}
}
