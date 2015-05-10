/**
 * 
 */
package mikeheke.tadpole.frm.bds.web.action;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor;
import mikeheke.tadpole.frm.bds.service.BdsXmlStructureService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BdsXmlStructureVo;

import org.xml.sax.SAXException;

/**
 * 编辑xml数据结构属性Action
 * 
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
