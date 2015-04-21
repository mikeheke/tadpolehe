/**
 * 
 */
package com.amway.frm.query.web.action;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.util.QueryConstant;

/**
 * 
 *
 * 2011-5-22 下午01:51:00
 * 
 *  @modify by Mike He
 *  @modify date 2013-1-21
 */
public class ReviewAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1863270784712213418L;

	@Override
	public String init() {
		
		Query query = this.getQueryFromContext();
		String isColHeaFil = getConfigService().getDisplayNameByCode(
				QueryConstant.DS_TRUE_OR_FALSE, DataConverter.valueOf(query.getIsColHeaFil()));
		this.setMessage(QueryConstant.IS_COL_HEA_FIL, isColHeaFil);
		String dsJndi = getConfigService().getDisplayNameByCode(
				QueryConstant.DS_TABLE_COL_NAME, DataConverter.valueOf(query.getDsJndi()));
		this.setMessage(QueryConstant.DS_JNDI, dsJndi);
		String isDefaultQry = getConfigService().getDisplayNameByCode(
				QueryConstant.DS_TRUE_OR_FALSE, DataConverter.valueOf(query.getIsDefaultQry()));
		this.setMessage(QueryConstant.IS_DEFAULT_QRY, isDefaultQry);
		String useState = getConfigService().getDisplayNameByCode(
				QueryConstant.DS_STATE_OPTIONS, DataConverter.valueOf(query.getUseState()));
		this.setMessage(QueryConstant.USE_STATE, useState);
		
		//设置select,from,where,order by,group by到Request中
		this.setMessage("selects", getQuerySelects());
		this.setMessage("froms", getQueryFroms());
		this.setMessage("wheres", getQueryWheres());
		this.setMessage("orderBys", getQueryOrderBys());
		this.setMessage("groupBys", getQueryGroupBys());
		
		return SUCCESS;
	}

}
