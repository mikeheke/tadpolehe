package mikeheke.tadpole.frm.afw.vo;

import java.sql.Timestamp;

/**
 * 
 * @author lenovo
 *
 */
public class BlackListVo {

	private static final long serialVersionUID = 8102612510192093373L;
	
	//自动生成ID
	private String blackListId;
	//form表单提交的ID数组
	private String [] blackListIds;
	//应用ID
	private String applicationId;
	//用户ID
	private String[] userProfileId;
	//设置时间
	private String blackListTime;
	//备注
	private String remark;
	//使用状态
	private Byte state;

	public String getBlackListId() {
		return blackListId;
	}

	public void setBlackListId(String blackListId) {
		this.blackListId = blackListId;
	}


	public String[] getBlackListIds() {
		return blackListIds;
	}

	public void setBlackListIds(String[] blackListIds) {
		this.blackListIds = blackListIds;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String[] getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String[] userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getBlackListTime() {
		return blackListTime;
	}

	public void setBlackListTime(String blackListTime) {
		this.blackListTime = blackListTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}


}
