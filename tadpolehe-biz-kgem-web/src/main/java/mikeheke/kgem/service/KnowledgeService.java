package mikeheke.kgem.service;

import mikeheke.kgem.entity.Knowledge;
import mikeheke.tadpole.frm.base.service.BaseService;

/**
 * 
 * @author mike
 *
 */
public interface KnowledgeService extends BaseService<Knowledge> {

	public String doSomething(String param);
	
}
