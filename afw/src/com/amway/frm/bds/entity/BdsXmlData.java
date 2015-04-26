package com.amway.frm.bds.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.UniqueKey;
import com.amway.frm.bds.util.BdsXmlUntil;

/**
 * 本地基础数据表实体类
 * 
 */
@Entity
@Table(name = "MSTB_BDS_XML_DATA", schema =AppConstant.APP_DEAULT_SCHEMA)
public class BdsXmlData implements java.io.Serializable {

	private static final long serialVersionUID = -6210010763031944561L;

	//自动生成ID
	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name = "BDS_XML_DATA_ID")
	private String bdsXmlDataId;
	
	//编码
	@Column(name = "CODE")
	@UniqueKey
	private String code;
	
	//显示名称
	@Column(name = "DISPLAYNAME")
	private String displayname;
	
	//英文名
	@Column(name = "DISPLAYNAME_EN")
	private String displaynameEn;
	
	//繁体中文名
	@Column(name = "DISPLAYNAME_TC")
	private String displaynameTc;
	
	//数据xml
	@Column(name = "BDS_DATA")
	private String bdsData;
	
	//备注
	@Column(name = "REMARK")
	private String remark;
	
	//使用状态
	@Column(name = "STATE")
	private Integer state;
	
	@Column(name="RECORD_STATE")
	@UniqueKey
	private Integer recordState = AppConstant.START;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="CREATED_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="CREATED_USER_ID",updatable=false)
	private String createdUserId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BDS_SCHEMAINFOR_ID")
	@UniqueKey
	private BdsSchemaInfor bdsSchemaInfor;
	
	//add by Mike He 20150425
	//存储xml数据  key:nodeName  value:nodeText
	//也包含了：　code,displayname,displaynameEn...值在里面
	@Transient //表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性. 
	private Map<String,String> bdsDataMap;

	public String getBdsXmlDataId() {
		return this.bdsXmlDataId;
	}

	public void setBdsXmlDataId(String bdsXmlDataId) {
		this.bdsXmlDataId = bdsXmlDataId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getDisplaynameEn() {
		return this.displaynameEn;
	}

	public void setDisplaynameEn(String displaynameEn) {
		this.displaynameEn = displaynameEn;
	}

	public String getDisplaynameTc() {
		return this.displaynameTc;
	}

	public void setDisplaynameTc(String displaynameTc) {
		this.displaynameTc = displaynameTc;
	}

	public String getBdsData() {
		return bdsData;
	}

	public void setBdsData(String bdsData) {
		this.bdsData = bdsData;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRecordState() {
		return this.recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return this.updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public BdsSchemaInfor getBdsSchemaInfor() {
		return bdsSchemaInfor;
	}

	public void setBdsSchemaInfor(BdsSchemaInfor bdsSchemaInfor) {
		this.bdsSchemaInfor = bdsSchemaInfor;
	}

	public Map<String, String> getBdsDataMap() {
		bdsDataMap = new HashMap<String, String>();
		
		bdsDataMap.put("code", this.code);
		bdsDataMap.put("displayname", this.displayname);
		bdsDataMap.put("displaynameEn", this.displaynameEn);
		bdsDataMap.put("displaynameTc", this.displaynameTc);	
		
		if (StringUtils.isBlank(this.bdsData) && this.bdsData.trim().length() < 10) {//空值不处理
			return bdsDataMap;
		}
		
		//解析xml data to map
		Document doc = BdsXmlUntil.getDocument(this.bdsData);
		Element root = doc.getRootElement();
		List<Element> eleList = root.getChildren();
		for (Element e: eleList) {
			String colName = e.getName();
			String colVal = e.getText();
			
			bdsDataMap.put(colName, colVal);
		}
		
		return bdsDataMap;
	}

	public void setBdsDataMap(Map<String, String> bdsDataMap) {
		this.bdsDataMap = bdsDataMap;
	}

}