
package mikeheke.kgem.vo;



/**
 * 视图vo
 * 用于收集前台的数据
 * 
 * @author mike
 *
 */
public class KnowledgeVo {
	
    private String knowledgeId;
    
    private String[] knowledgeIds; 
	
    private String title;
    
    private String content;
    
    private String remark;

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String[] getKnowledgeIds() {
		return knowledgeIds;
	}

	public void setKnowledgeIds(String[] knowledgeIds) {
		this.knowledgeIds = knowledgeIds;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
