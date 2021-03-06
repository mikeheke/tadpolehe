/**
 * 
 */
package mikeheke.tadpole.frm.base.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * 
 *         2011-6-1 下午02:30:45
 */
public class XMLDomHelper {
	
	public Document createDocument(String xmlFileName) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFileName);
		
		return document;
	}
	
	public Element getRootElement(Document document){
		return document.getDocumentElement();
	}
	
	public NodeList getNodeListByName(Element ele, String nodeName){
		return ele.getElementsByTagName(nodeName);
	}
	
	public Node getNodeByNameAttrValue(NodeList nodeList, String nameAttrValue){
		
		for (int i=0; i < nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			NamedNodeMap nnm = node.getAttributes();
			String nav = nnm.getNamedItem(AppConstant.NAME).getNodeValue();
			if(nav!=null && nav.equals(nameAttrValue)){
				return node;
			}
		}
		return null;
	}
	
	public void saveAsDocument(Document document, String xmlFileName)
			throws TransformerException {

		/** 编码 */
		// transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(xmlFileName);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.transform(source, result);
	}
}
