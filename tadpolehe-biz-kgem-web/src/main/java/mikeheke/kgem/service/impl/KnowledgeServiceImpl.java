package mikeheke.kgem.service.impl;

import mikeheke.kgem.entity.Knowledge;
import mikeheke.kgem.service.KnowledgeService;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

/**
 * 
 * @author mike
 *
 */
//@Transactional
public class KnowledgeServiceImpl extends BaseImpl<Knowledge> implements KnowledgeService {

	@Override
	public String doSomething(String param) {
		
		// TODO Auto-generated method stub
		
		if ("a".equals("a")) {
			throw new RuntimeException("RuntimeException");
			//throw new AmwaySysException(new RuntimeException("RuntimeException"));
		}
		
		
		return "";
	}
	
}
