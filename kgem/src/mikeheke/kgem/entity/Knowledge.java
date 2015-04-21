
package mikeheke.kgem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.UniqueKey;


/**
 * 知识entity
 * @author mike
 *
 */
@Entity(name="Knowledge")
@Table(name="MSTB_KGEM_KNOWLEDGE", schema=AppConstant.APP_DEAULT_SCHEMA)
public class Knowledge implements Serializable {

	private static final long serialVersionUID = -5421549791044368962L;
	
//	//自动生成ID
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MSSQ_KGEM_KNOWLEDGE_OR")
//	@SequenceGenerator(name="MSSQ_KGEM_KNOWLEDGE_OR", sequenceName="MSSQ_KGEM_KNOWLEDGE", initialValue=1, allocationSize=1)
//	@Column(name = "KNOWLEDGE_ID")
//    private String knowledgeId;
	
	//自动生成ID
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name = "KNOWLEDGE_ID")
    private String knowledgeId;

	
	//title
    @Column(name="TITLE")
    private String title;
    
	//content
    @Column(name="CONTENT")
    private String content;
    
    //备注
    @Column(name="REMARK")
    private String remark;

    //recordState
	@Column(name="RECORD_STATE")
    @UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	public Knowledge(){}

	public Knowledge(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
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

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((knowledgeId == null) ? 0 : knowledgeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (DataValidater.isFalse(getClass().equals(obj.getClass()))){
			return false;
		}
		Knowledge other = (Knowledge) obj;
		if (knowledgeId == null) {
			if (other.knowledgeId != null){
				return false;
			}
		} else if (!knowledgeId.equals(other.knowledgeId)){
			return false;
		}
		return true;
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Comparable#compareTo(java.lang.Object)
//	 */
//	@Override
//	public int compareTo(Knowledge o) {
//		if(null == o){
//			return 0;
//		}
//		return orderNo.compareTo(o.getOrderNo());
//	}

}
