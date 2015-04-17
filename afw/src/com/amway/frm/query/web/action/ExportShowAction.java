/**
 * 
 */
package com.amway.frm.query.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.entity.Select;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.Row;

/**
 * @author huangweijin
 *
 * 2011-6-17 下午01:19:24
 */
public class ExportShowAction extends ShowAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020812277115649588L;
	
	boolean firstQuery = true;
	
	public String export() throws Exception{
		
		this.firstQuery = true;
		
		super.execute();
		Query query = getQueryFromContext();
		
		OutputStream outputStream = genExportOs(query.getQueryName());
	
		exportBefore(outputStream);
		exportHead(query.getSelects(), outputStream);
		
		exportDatas(query, outputStream);
		
		int totalRow = query.getTable().getTotalRow();
		int rowEnd = query.getTable().getRowEnd();
		
		for( ; rowEnd <= totalRow ; ){
			
			super.execute();
			query = getQueryFromContext();
			
			exportDatas(query, outputStream);
			
			rowEnd = query.getTable().getRowEnd();
		}
		
		this.exportAfter(outputStream);
		
		outputStream.close();
		//outputStream = null;
		
		return NONE;
	}
	
	protected OutputStream genExportOs(String fileName) 
		throws IOException{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(QueryConstant.EXPORT_CONTENT_TYPE);
		response.setContentType(getMimeType());
		response.setHeader(QueryConstant.EXPORT_CONTENT_DISPOSITION, QueryConstant.EXPORT_ATTACHMENT
				+QueryConstant.QUO_D_SIGN+DataConverter.strGbkToIso(fileName)+getExtendType()+QueryConstant.QUO_D_SIGN);
		response.setHeader(QueryConstant.EXPORT_CACHE_CONTROL, QueryConstant.EXPORT_MUST_POST_PRE);
		response.setHeader(QueryConstant.EXPORT_PRAGMA, QueryConstant.EXPORT_PUBLIC);
		final Integer EXPORT_EXPIRES = 1000;
		response.setDateHeader(QueryConstant.EXPORT_EXPIRES, (System.currentTimeMillis() + EXPORT_EXPIRES));
		
		return response.getOutputStream();
	}
	
	protected String getExtendType(){
		return QueryConstant.XLS;
	}
	
	protected String getMimeType(){
		return QueryConstant.MIME_TEXT_CSV;
	}
	
	protected void exportBefore(OutputStream outputStream) 
		throws Exception {
		StringBuffer sbf = new StringBuffer();
		
		sbf.append(QueryConstant.EXPORT_HTML_BEGIN);
		sbf.append(QueryConstant.EXPORT_META);
		writeContent(outputStream , sbf);
	}
	
	protected void exportHead(List<Select> selects, OutputStream outputStream) 
		throws Exception{
		
		StringBuffer sbf = new StringBuffer();
		int size = selects.size();
		sbf.append(QueryConstant.ENTER_SIGN).append(QueryConstant.TR_BEGIN);
		for(int i = 0; i < size; i++){
			Select select = selects.get(i);
			if((null != select.getBtnType() 
					&& QueryConstant.BTN_TYPE_CHECKBOX.intValue()==select.getBtnType().intValue())
				|| 
				(null != select.getIsHidden() 
					&& QueryConstant.YES.intValue()==select.getIsHidden().intValue())){
				continue;
			}else{
				sbf.append(QueryConstant.ENTER_SIGN).append(QueryConstant.TD_BEGIN2);
				sbf.append(select.getDes());
				sbf.append(QueryConstant.TD_END);
			}
		}
		sbf.append(QueryConstant.ENTER_SIGN).append(QueryConstant.TR_END);
		writeContent(outputStream , sbf);
	}

	protected void exportDatas(Query query, OutputStream outputStream) 
		throws Exception {
		//数据单元格字体格式
		List<Select> selects = query.getSelects();
		List<Row> datas = query.getTable().getDatas();
		StringBuffer sbf = new StringBuffer();
		int rowSize = datas.size();
		int colSize = selects.size();
		for(int i=0; i<rowSize; i++){
			sbf.append(QueryConstant.ENTER_SIGN).append(QueryConstant.TR_BEGIN);
			Row row = datas.get(i);
			for(int j=0; j<colSize; j++){
				Select select = selects.get(j);
				StringBuffer colName = new StringBuffer();
				colName.append(select.getTableName());
				colName.append(select.getFieldName());
				colName.append(select.getFieldAlias());
				Object data = row.get(colName.toString());
				String content = this.formatString(data);
				if((null != select.getBtnType()
						&& QueryConstant.BTN_TYPE_CHECKBOX.intValue()==select.getBtnType().intValue())
					|| 
					(null != select.getIsHidden() 
						&& QueryConstant.YES.intValue()==select.getIsHidden().intValue())){
					continue;
				}else{
					sbf.append(QueryConstant.ENTER_SIGN).append(QueryConstant.TD_BEGIN);
					sbf.append(content);
					sbf.append(QueryConstant.TD_END);
				}
			}
			sbf.append(QueryConstant.ENTER_SIGN).append(QueryConstant.TR_END);
		}
		writeContent(outputStream , sbf);
	}
	
	protected void exportAfter(OutputStream os) throws Exception {
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(QueryConstant.EXPORT_HTML_END);
		writeContent(os, sbf);
	}
	
	protected void writeContent(OutputStream os, Object dataView) throws Exception{
		
		StringBuffer sbf = (StringBuffer)dataView;
		os.write(sbf.toString().getBytes(QueryConstant.UTF_8));
		os.flush();
		
	}
	
	private String formatString(Object obj) {
		
		String result = QueryConstant.EMPTY_STR;
		if(null == obj){
			return result;
		}
		result = DataConverter.NoHTML(obj.toString());
		final Integer len = 11;
		if (result.length() > len) {
			for (int i = 0; i < result.length(); i++) {
				if (!Character.isDigit(result.charAt(i))){
					return result;
				}
			}
			result = QueryConstant.QUO_S_SIGN + result;
		}
		return result;
	}
	
	@Override
	protected void setPageAttributes(Query query, int rowStart, int rowEnd,
			int pageNum) {
		
		if(firstQuery){
			super.setPageAttributes(query, 0, 5, 5);
			firstQuery = false;
		}else{
			super.setPageAttributes(query, 
					query.getTable().getRowStart()+query.getTable().getPageNum(), 
					query.getTable().getRowEnd()+query.getTable().getPageNum(), 
					query.getTable().getPageNum());
		}
	}
}
