/**
 * 
 */
package mikeheke.tadpole.frm.bds.service;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.vo.BdsXmlStructureVo;

/**
 * xml数据结构service接口类
 * 
 * 
 */
public interface BdsXmlStructureService extends BaseService{
	
	/**
	 * 解析xml数据结构，并返回消息对象
	 * @param xmlString
	 * @return  包含XmlStructure对象的RetrunMessage
	 */
	ReturnMessage<BdsXmlStructureVo> parseXmlStructure(String xmlString);
	

}
