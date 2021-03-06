package mikeheke.tadpole.frm.job.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.UniqueKey;

/**
 * 
 * @author Xie Jinnan
 *
 */
@Entity(name = "ShopAuth")
@Table(name = "MSTB_SA_SHOP_AUTH", schema = AppConstant.APP_DEAULT_SCHEMA)
public class ShopAuth implements java.io.Serializable {

	private static final long serialVersionUID = 7964381508497317886L;

	@Id
	@Basic(optional = false)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSSQ_SA_SHOP_AUTH_OR")
	@SequenceGenerator(name = "MSSQ_SA_SHOP_AUTH_OR", sequenceName = "MSSQ_SA_SHOP_AUTH", initialValue = 1, allocationSize = 1)
	@Column(name = "SHOP_AUTH_ID")
	private Long shopAuthId;

	// 店铺编码
	@Column(name = "SHOP_CODE")
	@UniqueKey
	private String shopCode;

	// 应用编码
	@Column(name = "APPLICATION_CODE")
	@UniqueKey
	private String applicationCode;

	// 权限1编码
	@Column(name = "RIGHT_CODE_1")
	private String rightCode1;

	// 权限1店长标志位
	@Column(name = "RIGHT1_SHOP_HEAD")
	private Integer right1ShopHead;

	// 权限1副店长标志位
	@Column(name = "RIGHT1_SHOP_HEAD2")
	private Integer right1ShopHead2;

	// 权限1店铺员工标志位
	@Column(name = "RIGHT1_SHOP_ASSISTANT")
	private Integer right1ShopAssistant;

	// 权限1省长标志位
	@Column(name = "RIGHT1_PRV_HEAD")
	private Integer right1PrvHead;

	// 权限1市长标志位
	@Column(name = "RIGHT1_CITY_HEAD")
	private Integer right1CityHead;

	// 权限1办税员标志位
	@Column(name = "RIGHT1_TAX_OPERATOR")
	private Integer right1TaxOperator;

	// 权限1手工添加
	@Column(name = "RIGHT1_MANUAL")
	private String right1Manual;

	// 权限2编码
	@Column(name = "RIGHT_CODE_2")
	private String rightCode2;

	// 权限2店长标志位
	@Column(name = "RIGHT2_SHOP_HEAD")
	private Integer right2ShopHead;

	// 权限2副店长标志位
	@Column(name = "RIGHT2_SHOP_HEAD2")
	private Integer right2ShopHead2;

	// 权限2手工添加
	@Column(name = "RIGHT2_MANUAL")
	private String right2Manual;

	// 权限3编码
	@Column(name = "RIGHT_CODE_3")
	private String rightCode3;

	// 权限3手工添加
	@Column(name = "RIGHT3_MANUAL")
	private String right3Manual;

	// 权限4编码
	@Column(name = "RIGHT_CODE_4")
	private String rightCode4;
	
	@Column(name = "STATE")
	private Integer state;

	// 记录状态
	@Column(name = "RECORD_STATE", nullable = false)
	@UniqueKey
	private Integer recordState = AppConstant.START;
	// 创建人
	@Column(name = "CREATED_USER_ID", updatable = false)
	private String createdUserId;
	// 创建时间
	@Column(name = "CREATED_TIME", updatable = false)
	private Date createdTime;
	// 更新人
	@Column(name = "UPDATED_USER_ID")
	private String updatedUserId;
	// 更新时间
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@Transient
	private String email;
	
	@Transient
	private String shopName;

	@Transient
	private String applicationName;
	
	@Transient
	private String shopHead;
	
	@Transient
	private String shopHead2;

	public String getShopHead() {
		return shopHead;
	}

	public void setShopHead(String shopHead) {
		this.shopHead = shopHead;
	}

	public String getShopHead2() {
		return shopHead2;
	}

	public void setShopHead2(String shopHead2) {
		this.shopHead2 = shopHead2;
	}

	public List<String> getRight1ManualArr() {
		final String STR_COMMON = ", ";
		
		List<String> right1ManualList = new ArrayList<String>();
		if (DataValidater.isStrEmpty(right1Manual)) {
			return right1ManualList;
		}

		String[] right1ManualArr = right1Manual.split(STR_COMMON);
		if (DataValidater.isArrEmpty(right1ManualArr)) {
			return right1ManualList;
		}
		for (String user : right1ManualArr) {
			if (DataValidater.isStrEmpty(user)) {
				continue;
			}
			right1ManualList.add(user);
		}

		return right1ManualList;
	}

	public List<String> getRight2ManualArr() {
		final String STR_COMMON = ", ";
		
		List<String> right2ManualList = new ArrayList<String>();
		if (DataValidater.isStrEmpty(right2Manual)) {
			return right2ManualList;
		}

		String[] right2ManualArr = right2Manual.split(STR_COMMON);
		if (DataValidater.isArrEmpty(right2ManualArr)) {
			return right2ManualList;
		}
		for (String user : right2ManualArr) {
			if (DataValidater.isStrEmpty(user)) {
				continue;
			}
			right2ManualList.add(user);
		}

		return right2ManualList;
	}

	public List<String> getRight3ManualArr() {
		final String STR_COMMON = ", ";
		
		List<String> right3ManualList = new ArrayList<String>();
		if (DataValidater.isStrEmpty(right3Manual)) {
			return right3ManualList;
		}

		String[] right3ManualArr = right3Manual.split(STR_COMMON);
		if (DataValidater.isArrEmpty(right3ManualArr)) {
			return right3ManualList;
		}
		for (String user : right3ManualArr) {
			if (DataValidater.isStrEmpty(user)) {
				continue;
			}
			right3ManualList.add(user);
		}

		return right3ManualList;
	}

	public Long getShopAuthId() {
		return shopAuthId;
	}

	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getRightCode1() {
		return rightCode1;
	}

	public void setRightCode1(String rightCode1) {
		this.rightCode1 = rightCode1;
	}

	public Integer getRight1ShopHead() {
		return right1ShopHead;
	}

	public void setRight1ShopHead(Integer right1ShopHead) {
		this.right1ShopHead = right1ShopHead;
	}

	public Integer getRight1ShopHead2() {
		return right1ShopHead2;
	}

	public void setRight1ShopHead2(Integer right1ShopHead2) {
		this.right1ShopHead2 = right1ShopHead2;
	}

	public Integer getRight1ShopAssistant() {
		return right1ShopAssistant;
	}

	public void setRight1ShopAssistant(Integer right1ShopAssistant) {
		this.right1ShopAssistant = right1ShopAssistant;
	}

	public Integer getRight1PrvHead() {
		return right1PrvHead;
	}

	public void setRight1PrvHead(Integer right1PrvHead) {
		this.right1PrvHead = right1PrvHead;
	}

	public Integer getRight1TaxOperator() {
		return right1TaxOperator;
	}

	public void setRight1TaxOperator(Integer right1TaxOperator) {
		this.right1TaxOperator = right1TaxOperator;
	}

	public String getRight1Manual() {
		return right1Manual;
	}

	public void setRight1Manual(String right1Manual) {
		this.right1Manual = right1Manual;
	}

	public String getRightCode2() {
		return rightCode2;
	}

	public void setRightCode2(String rightCode2) {
		this.rightCode2 = rightCode2;
	}

	public Integer getRight2ShopHead() {
		return right2ShopHead;
	}

	public void setRight2ShopHead(Integer right2ShopHead) {
		this.right2ShopHead = right2ShopHead;
	}

	public Integer getRight2ShopHead2() {
		return right2ShopHead2;
	}

	public void setRight2ShopHead2(Integer right2ShopHead2) {
		this.right2ShopHead2 = right2ShopHead2;
	}

	public String getRight2Manual() {
		return right2Manual;
	}

	public void setRight2Manual(String right2Manual) {
		this.right2Manual = right2Manual;
	}

	public String getRightCode3() {
		return rightCode3;
	}

	public void setRightCode3(String rightCode3) {
		this.rightCode3 = rightCode3;
	}

	public String getRight3Manual() {
		return right3Manual;
	}

	public void setRight3Manual(String right3Manual) {
		this.right3Manual = right3Manual;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRight1CityHead() {
		return right1CityHead;
	}

	public void setRight1CityHead(Integer right1CityHead) {
		this.right1CityHead = right1CityHead;
	}

	public String getRightCode4() {
		return rightCode4;
	}

	public void setRightCode4(String rightCode4) {
		this.rightCode4 = rightCode4;
	}

}