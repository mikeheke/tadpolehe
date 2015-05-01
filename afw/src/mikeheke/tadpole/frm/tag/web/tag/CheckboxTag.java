package mikeheke.tadpole.frm.tag.web.tag;

import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.tag.util.TagConstant;

/**
 * 复选框标签
 * 
 * 
 */
public class CheckboxTag extends RadioTag {

	private static final long serialVersionUID = -5196480943592588920L;

	// 供筛选的数据集中的子对象ID值
	private String listKey = BdsConstant.FIXED_COL_NAME_CODE;

	// 供筛选的数据集中的子对象显示值
	private String listValue = BdsConstant.FIXED_COL_NAME_DN;

	public String getListKey() {
		return listKey;
	}

	public void setListKey(String listKey) {
		this.listKey = listKey;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	@Override
	protected String getControlType() {
		return TagConstant.CHECKBOX;
	}

	@Override
	protected Object getHiddenLabel(Object label) {

		List<Map> datas = getControlDatas2();
		for (Map data : datas) {
			Object key = data.get(listKey);
			Object value = data.get(listValue);
			if (key.toString().equals(label.toString())) {
				return value;
			}
		}

		return label;
	}
}
