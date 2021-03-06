/**
 * 
 */
package mikeheke.tadpole.frm.query.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.query.dao.IQueryDao;
import mikeheke.tadpole.frm.query.entity.Button;
import mikeheke.tadpole.frm.query.entity.From;
import mikeheke.tadpole.frm.query.entity.GroupBy;
import mikeheke.tadpole.frm.query.entity.OrderBy;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.entity.Select;
import mikeheke.tadpole.frm.query.entity.Where;
import mikeheke.tadpole.frm.query.exception.QueryInfoException;
import mikeheke.tadpole.frm.query.service.QueryService;
import mikeheke.tadpole.frm.query.util.QueryConstant;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 *         2011-4-30 上午09:54:13
 */
public class QueryImpl extends BaseImpl implements QueryService {

	private IQueryDao queryDao;
	
	private BaseDataSourceService baseDataSourceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mikeheke.tadpole.frm.query.service.ConfigService#deleteQuerys(java.util.List)
	 */
	@Transactional
	@Override
	public ReturnMessage<Query> deleteQuerys(List<Query> querys) {
		return this.deleteList(querys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mikeheke.tadpole.frm.query.service.ConfigService#getQueryByQuery(mikeheke.tadpole.frm
	 * .query.entity.Query)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReturnMessage<Query> getQueryByQuery(Query query) {
		
		ReturnMessage<Query> returnMessage = this.query(query);
		Query queryRet = returnMessage.getReturnObject();
		if (null == queryRet) {
			throw new QueryInfoException(AppConstant.RECORD_NEXISTS);
		}

		List<Select> selects = this.getSelectListByQuery(queryRet);
		Collections.sort(selects);
		queryRet.setSelects(selects);

		List<From> froms = this.getFromListByQuery(queryRet);
		Collections.sort(froms);
		queryRet.setFroms(froms);

		List<Where> wheres = this.getWhereListByQuery(queryRet);
		Collections.sort(wheres);
		queryRet.setWheres(wheres);

		List<OrderBy> orderBys = this.getOrderByListByQuery(queryRet);
		Collections.sort(orderBys);
		queryRet.setOrderBys(orderBys);

		List<GroupBy> groupBys = this.getGroupByListByQuery(queryRet);
		Collections.sort(groupBys);
		queryRet.setGroupBys(groupBys);

		List<Button> buttons = this.getButtonListByQuery(queryRet);
		Collections.sort(buttons);
		queryRet.setButtons(buttons);

		return returnMessage;
	}

	private List<Select> getSelectListByQuery(Query query) {

		Select select = new Select();
		select.setQuery(query);
		ReturnMessage<Select> returnMessage = this.query(select);
		
		return returnMessage.getReturnObjects();
	}

	private List<From> getFromListByQuery(Query query) {

		From from = new From();
		from.setQuery(query);
		ReturnMessage<From> returnMessage = this.query(from);
		
		return returnMessage.getReturnObjects();
	}

	private List<Where> getWhereListByQuery(Query query) {

		Where where = new Where();
		where.setQuery(query);
		ReturnMessage<Where> returnMessage = this.query(where);
		
		return returnMessage.getReturnObjects();
	}

	private List<OrderBy> getOrderByListByQuery(Query query) {

		OrderBy orderBy = new OrderBy();
		orderBy.setQuery(query);
		ReturnMessage<OrderBy> returnMessage = this.query(orderBy);
		
		return returnMessage.getReturnObjects();
	}

	private List<GroupBy> getGroupByListByQuery(Query query) {

		GroupBy groupBy = new GroupBy();
		groupBy.setQuery(query);
		ReturnMessage<GroupBy> returnMessage = this.query(groupBy);
		
		return returnMessage.getReturnObjects();
	}

	private List<Button> getButtonListByQuery(Query query) {

		Button button = new Button();
		button.setQuery(query);
		ReturnMessage<Button> returnMessage = this.query(button);
		
		return returnMessage.getReturnObjects();
	}
	
	public String getDisplayNameByCode(String dictCode, String code){
		
		String displayName = null;
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put(BdsConstant.FIXED_COL_NAME_CODE, new String[]{code});
		BaseDataSourceVo baseDataSourceVo = getBaseDataSourceVo(dictCode, filterMap);
		displayName = (String) baseDataSourceVo
			.getColumnSingle(BdsConstant.FIXED_COL_NAME_DN);
		
		return displayName;
	}
	
	protected BaseDataSourceVo getBaseDataSourceVo(String dictCode,
			Map<String, String[]> filterMap) {
		
		ReturnMessage<BaseDataSourceVo> returnMessage = getBaseDataSourceService()
				.getBdsVoData(dictCode, filterMap, QueryConstant.SQL_AND);
		BaseDataSourceVo baseDataSourceVo = returnMessage.getReturnObject();
		
		return baseDataSourceVo;
	}

	public BaseDataSourceService getBaseDataSourceService() {
		return baseDataSourceService;
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}

	public IQueryDao getQueryDao() {
		return queryDao;
	}

	public void setQueryDao(IQueryDao queryDao) {
		this.queryDao = queryDao;
	}
}
