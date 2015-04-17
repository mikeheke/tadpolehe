/**
 * 
 */
package com.amway.frm.bds.web.action;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.entity.BdsSchemaInfor;
import com.amway.frm.bds.service.BdsXmlStructureService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BdsXmlStructureVo;

/**
 * 编辑xml数据结构属性Action
 * @author huangweijin
 */
public class BdsXmlStructureAction extends BdsSchemaInforAction {

	private BdsXmlStructureService bdsXmlStructureService;

	/**
	 * 列表本地数据
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public String list() {
		
		String dataStructureXml =getBdsSchemaInforVo().getDataStructureXml();
		if(!vailidateColNames(dataStructureXml)){
			final String msg = "xml格式不正确";
			this.setMessage(BdsConstant.ID_MSG, msg);
			return INPUT;
		}
		
		ReturnMessage<BdsXmlStructureVo> returnMessage = null;
		returnMessage = bdsXmlStructureService.parseXmlStructure(dataStructureXml);
		setReturnMessage(returnMessage);
		
		return SUCCESS;
	}
	
	public List<BdsSchemaInfor> getShemaInforList(){
		return bdsXmlStructureService.queryList(new BdsSchemaInfor());
	}

	public void setBdsXmlStructureService(
			BdsXmlStructureService bdsXmlStructureService) {
		this.bdsXmlStructureService = bdsXmlStructureService;
	}

	public BdsXmlStructureService getBdsXmlStructureService() {
		return bdsXmlStructureService;
	}

}
